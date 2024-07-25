//Source file: E:\\PIXProject\\PIXApplicationEJB\\src\\com\\pearson\\pix\\dao\\base\\BaseDAO.java

package com.pearson.pix.dao.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.jboss.ejb.client.EJBClient;

import com.arjuna.ats.jbossatx.jta.TransactionManagerDelegate;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

import oracle.toplink.sessions.DatabaseLogin;
import oracle.toplink.sessions.Session;
import oracle.toplink.sessions.UnitOfWork;
import oracle.toplink.threetier.ClientSession;
import oracle.toplink.threetier.Server;
import oracle.toplink.threetier.ServerSession;
import oracle.toplink.tools.sessionconfiguration.XMLSessionConfigLoader;
import oracle.toplink.tools.sessionmanagement.SessionManager;



/**
 * Base DAO class containing the methods for server and client session creation to 
 * toplink. It can also contains the common methods to be used.
 * @author hcl
 */
@Stateless(name = "XAProducerBMTSB")
@TransactionManagement(value=TransactionManagementType.BEAN)
public class BaseDAO 
{
	private static Server serversession;
	private static Server serverarpsession;
	private static Context ctx = null; 
	private static DataSource ds = null;

   /**
    * Constructor for initializing BaseDAO
    */
   public BaseDAO() 
   {
    
   }
   
   /**
    * Gets the Client session from the Server session and returns it
    * 
    * @return oracle.toplink.sessions.Session
    */
   public Session getClientSession() 
   {
	   initializeServerSession();
	   ClientSession clientsession = serversession.acquireClientSession();
	   return clientsession;
  
   }
   
   /**
    * Method to build Server Session with database and login into it.
    */
   private static void initializeServerSession() 
   {
	   if(serversession == null)
	   {
		   serversession = (Server) SessionManager.getManager().getSession(new XMLSessionConfigLoader(),"Session", Thread.currentThread().getContextClassLoader(), false, true);
		   DatabaseLogin dbLogin= (DatabaseLogin)serversession.getProject().getLogin();
		   dbLogin.useStringBinding(4000);
		   serversession.setLogin(dbLogin);
		   serversession.login();
       }
	   else
	   {
		    return;
	   }	   
   }   
   //@Resource
   //UserTransaction uow;
   @Resource
   SessionContext sessionContext;
   /**
    * Gets the Data Source Connection
    * 
    * @return javax.transaction.UserTransaction
    */
   public UserTransaction getUserTransaction() throws AppException
   {
	   
	   UserTransaction ux = null;
	   try
	   {
		   
		  // ux=RemoteTransactionContext.getInstance().getUserTransaction();//ux=sessionContext.getUserTransaction();
		   ux = EJBClient.getUserTransaction("node1");
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
		   e.printStackTrace();
	   }
 
return ux;	   
   }
   
   /**
    * Gets the Data Source Connection
    * 
    * @return java.sql.Connection
    */
   public Connection getDataSourceConnection() throws AppException
   {
	    
	   Connection conn = null;
	   try
	   {
		   if(ctx == null){
			   ctx = new InitialContext(null);
		   }
		   if(ds == null){
			   ds = (DataSource) ctx.lookup("java:jboss/datasources/OracleDB");//java:jboss/datasources/OracleDB//OracleDB
		   }
		   
		   conn = ds.getConnection();
		   return conn;
	   }
	   catch(NamingException ne)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.NAMING_EXCEPTION,"BaseDAO,getDataSourceConnection",ne);
		   throw ae;
	   }
	   catch(SQLException se)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"BaseDAO,getDataSourceConnection",se);
		   throw ae;
	   }
	   
   }
   
   /**
    * Gets the UnitOfWork
    * 
    * @param objSession
    * @return oracle.toplink.sessions.UnitOfWork
    */
   public UnitOfWork getUnitOfWork(Session objsession)
   {
	   UnitOfWork uow = null;
	   uow = objsession.getActiveUnitOfWork();
       if(uow == null)
           uow = objsession.acquireUnitOfWork();
       return uow;
   }
   
   /**/
   public Session getARPClientSession() 
   {
	    try {
			currentARPSession();
		} catch (AppException e) {
			return null;
		}
		  ClientSession clientsession = serverarpsession.acquireClientSession();
		  clientsession.dontLogMessages();
		  return clientsession;
  }
   
   /**
    */
   public static void currentARPSession() throws AppException {
       if (serverarpsession == null) {
           /*SessionManager manager = SessionManager.getManager();
           session = (ServerSession)manager.getSession(new XMLSessionConfigLoader(), "Session", Thread.currentThread().getClass().getClassLoader());
           session.login();*/
           try{
        	   serverarpsession = (ServerSession) SessionManager.getManager().getSession(new XMLSessionConfigLoader(),"PIX_ARPSession", Thread.currentThread().getContextClassLoader(), false, true);
           //session.setServerPlatform(new oracle.toplink.platform.server.jboss.JBossPlatform());
           DatabaseLogin login = (DatabaseLogin)serverarpsession.getProject().getLogin();
           Context context = new InitialContext();
           javax.sql.DataSource ds = (javax.sql.DataSource) context.lookup("SybaseDB");
           oracle.toplink.jndi.JNDIConnector connector = new oracle.toplink.jndi.JNDIConnector(ds);
           connector.setDataSource(ds);
           login.setPlatformClassName("oracle.toplink.internal.databaseaccess.SybasePlatform");
           login.setTransactionIsolation(1);
           login.setConnector(connector);
           login.useSybase();
           login.useNativeSQL();
           login.useNativeSequencing();
           System.out.println("base dao toplink sesiion..........."+serverarpsession);
           //login.setSequencePreallocationSize(1);
           //Project proj = session.getProject();
           login.setUsesExternalConnectionPooling(true);
           login.setUsesExternalTransactionController(true);
           //session.getProject().setUsesExternalConnectionPooling(true);
           //login.set
           serverarpsession.setLogin(login);
           System.out.println("base dao toplink..........."+serverarpsession.getLogin());
           serverarpsession.login();
           }catch(Exception e){
        	   serverarpsession = null;
        	   throw new AppException("Not able to connect to ARP Database.");
           }
           /*SessionManager manager = SessionManager.getManager();
           session = (ServerSession)manager.getSession(new XMLSessionConfigLoader(), "Session", Thread.currentThread().getClass().getClassLoader(),true,false);*/
       }
       return;
   }
    
	 	   
}
