package com.pearson.pix.dao.bookspecification;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.exception.AppException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.Collection;
import javax.transaction.UserTransaction;
import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.ReadAllQuery;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import com.pearson.pix.dto.bookspecification.BookSpec;
import com.pearson.pix.dto.bookspecification.BookSpecParty;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.exception.Exceptions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



	/**
	* Implementation of Admin Data Access Object containing all the methods required 
	* for DB access through Toplink.
	* @author sudam.sahu
	*/
 	public class BookSpecDAOImpl extends BaseDAO implements BookSpecDAO 
	{
   
 		/**
 	     * Logger.
 	     */
 		
 		private static Log log = LogFactory.getLog(BookSpecDAOImpl.class.getName());
 		/**
 	    * Constructor to initialize BookSpecDAOImpl
 	    */
		public BookSpecDAOImpl() 
		{
    
		}
   	/** This method is for calling the specific procedure 
    * for displaying Book Specification List page
    * @return java.util.Vector
    */ 
	public Vector getBookSpecList(BookSpec objBookSpec,int userId,String page,int currentValue,String orderby,String sort,String startdate,String enddate,String specno)throws AppException
	{
   
	  
	   Session objSession = null;
	   Vector objListVector = new Vector();
	   
	  /**foloowing are the toplink code to pass the parameteres to call a procedure and execute a procedure
	   */
	   try
	   	{  
		   BookSpec objBookSpec1 = null;
		   objSession = getClientSession();
		   StoredProcedureCall call = new StoredProcedureCall(); 
           
           if(page.equals("C")||page.equals("I"))//to check the page value whether it is calling from list page or history page
           {
        	   call.setProcedureName("Pix_book_spec_Proc");
        	   call.addNamedArgumentValue("i_spec_no",objBookSpec.getSpecNo());
           }
           else if(page.equals("H"))
           {
        	   call.setProcedureName("Pix_book_spec_Hist_Proc");
        	   call.addNamedArgumentValue("i_spec_no",specno);
           }
		   call.addNamedArgumentValue("i_isbn",objBookSpec.getTitleDetail().getIsbn10());
		   call.addNamedArgumentValue("i_print_number",objBookSpec.getTitleDetail().getPrintNo());
		   call.addNamedArgumentValue("i_status",objBookSpec.getStatusDetail().getStatusDescription());
		   call.addNamedArgumentValue("i_start_date",startdate);
		   call.addNamedArgumentValue("i_end_date",enddate);
		   call.addNamedArgumentValue("i_user_id",new Integer(userId));
		   call.addNamedArgumentValue("i_page",page);
		   call.addNamedArgumentValue("i_pagination",new Integer(currentValue));
		   call.addNamedArgumentValue("i_order_by",orderby);
		   call.addNamedArgumentValue("i_sort",sort);
           call.useNamedCursorOutputAsResultSet("list_refcursor");
           Vector objVector = (Vector)objSession.executeSelectingCall(call);
           objSession.logMessages();
           if (objVector.size()>0 && objVector != null)
           {
			/** this loop is to get the following values from the database if they are null
			* BOOK_SPEC_NO
			* ISBN_NUM
			* PRINT_NUM
			* SPECIFICATION_DATE
			* STATUS_DESC
			* NAME_1
			* SPEC_ID
			* SPEC_VERSION
			*/
			 for (int i = 0; i < objVector.size(); i++)
			 {   
        	   Record objDatabaseRecord = (Record)objVector.get(i);
        	   objBookSpec1 = new BookSpec();
        	   TitlePrinting titlePrinting= new TitlePrinting();
        	   Status objstatus=new Status();
        	   BookSpecParty bookParty=new BookSpecParty();
        	   objBookSpec1.setSpecNo((String)objDatabaseRecord.get(PIXConstants.BOOK_SPEC_NO));
        	   objBookSpec1.setAckflag((String)objDatabaseRecord.get(PIXConstants.ACK_FLAG));
        	   titlePrinting.setIsbn10((String)objDatabaseRecord.get(PIXConstants.ISBN_NUM));
        	   titlePrinting.setPrintNo(objDatabaseRecord.get(PIXConstants.PRINT_NUM).toString());
           	   objBookSpec1.setSpecIssueDate((Date)objDatabaseRecord.get(PIXConstants.SPECIFICATION_DATE));
        	   objstatus.setStatusDescription((String)objDatabaseRecord.get(PIXConstants.STATUS_DESC));
        	   bookParty.setName1((String)objDatabaseRecord.get(PIXConstants.NAME_1));
        	   bookParty.setName2((String)objDatabaseRecord.get(PIXConstants.NAME_2));
        	   objBookSpec1.setSpecId((BigDecimal)objDatabaseRecord.get(PIXConstants.SPEC_ID));
        	   objBookSpec1.setSpecVersion((BigDecimal)objDatabaseRecord.get(PIXConstants.SPEC_VERSION));
        	   objBookSpec1.setTitleDetail(titlePrinting);
        	   objBookSpec1.setStatusDetail(objstatus);
        	   Vector vec=new Vector();
        	   vec.add(bookParty);
        	   objBookSpec1.setPartyCollection(vec);
        	   objListVector.add(objBookSpec1);
        	   }  	
          	
           } 
		   else if(objVector.size()== 0 || objVector == null)
	        {
			   AppException appException=new AppException();
			   log.info("log info  :no records ");
			   appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_BOOK_SPEC,"BookSpecDAOImpl");  
			   throw appException;
	        }
    }
	 catch(TopLinkException e)
	 {
		   	   AppException appException = new AppException();
		   	   log.debug("TopLink Exception");
		   	   appException.performErrorAction(Exceptions.SQL_EXCEPTION, "BookSpecDAOImpl,getBookSpecList",e);
		   	   throw appException;
	 }
	 
	 finally
	 {
		   if(objSession!=null)
		   {
			   objSession.release();
		   }
	 }    
	 return objListVector;
}
   
	/** This method is for getting the book spec status 
	 * @ return java.util.Collection;
	*/
   public Collection getBookSpecStatus()throws AppException
   {
  	
  	 	  	 Collection objallStatus = null;
  	 	  	 Session clientSession = null;
  	 try
  	 {		
  		 	 
  		  	 clientSession = getClientSession();
  		  	 ExpressionBuilder builder = new ExpressionBuilder(); 
  		  	 Expression expStatus = builder.get("tableDetail").get("tableName").equal(PIXConstants.PIX_BOOK_SPEC).and(builder.get("activeFlag").equal("Y"));
  		  	 ReadAllQuery query = new ReadAllQuery(Status.class);
  		  	 query.setSelectionCriteria(expStatus);
  		  	 query.addAscendingOrdering("statusDescription");
  		  	 objallStatus = (Vector)clientSession.executeQuery(query);
  	 }
  	 catch(TopLinkException e)
  	 {
  		   	 AppException ae = new AppException();
  		     log.info("log info  :no records ");
  		   	 ae.performErrorAction(Exceptions.SQL_EXCEPTION,"BookSpecDAOImpl,getBookSpeStatus",e);
  		   	 throw ae;
     }
  	 finally
  	 {
  		    if(clientSession!=null)
  		    {
  		    	
  		    	clientSession.release();
  		    }
  	 }
  	   
  	 return objallStatus;
   }
   

	/** This method is to get the detail page of book spec 
	 * @param specId,specVersion
	 * @return com.pearson.pix.dto.bookspecification.BookSpec
	*/
   
   public BookSpec getBookSpecDetail(Integer specid,Integer specversion) throws AppException
   {   
	   BookSpec objBookSpec=null;
	   Session clientSession = null;
	   try
	   {
		   
		   clientSession = getClientSession();
		   ExpressionBuilder builder = new ExpressionBuilder();
		   Expression BookSpecExp = builder.get("specId").equal(specid).and(builder.get("specVersion").equal(specversion));
		   objBookSpec= (BookSpec)clientSession.readObject(BookSpec.class,BookSpecExp);
	   }
	   catch(TopLinkException te)
	   {
		   AppException ae = new AppException();
		   log.info("log info  :no records ");
		   ae.performErrorAction("Exceptions.SQL_EXCEPTION","BookSpecDAOImpl,getBookSpecDetail",te);
		   throw ae;
	   }
	  finally
	   {
		   if(clientSession!=null)
		   {
			    clientSession.release();
		   }
	   }
	   
	   return objBookSpec;
	   
   }
   
   /** This method is to get the last acknowledgement version of bookspec 
	 * @param specId,specVersion
	 * @return com.pearson.pix.dto.bookspecification.BookSpec
	*/
  
  public BookSpec getBookSpecLastAckDetail(Integer specid,Integer specversion) throws AppException
  {   
	   BookSpec objBookSpec=null;
	   Session clientSession = null;
	   try
	   {
		   clientSession = getClientSession();
		   ExpressionBuilder builder = new ExpressionBuilder();
		   Record objRecord=null;
		   clientSession = getClientSession();
		  /* Vector objVector=(Vector)clientSession.executeSelectingCall(new SQLCall("select max(spec_version)as spec_version " +
		   		" from pix_book_spec_party where spec_id="+ specid +" and   spec_version< "+ specversion +" and   acknowledge_flag='Y'"));*/
		   /*Vector objVector=(Vector)clientSession.executeSelectingCall(new SQLCall("select nvl(max(spec_version)," +specversion+ "-1) as spec_version"+ 
				 " from pix_book_spec_party where spec_id="+ specid +" and  spec_version< "+ specversion +" and  acknowledge_flag='Y'"));*/
			 
		   // changes for tracker#436178 - release date: Nov 18, 2011
		   StoredProcedureCall call = new StoredProcedureCall();
		   call.setProcedureName("Pix_bs_compare_version_Proc");
		   call.addNamedArgumentValue("i_spec_id",specid);
		   call.addNamedArgumentValue("i_spec_version",specversion);
		   call.useNamedCursorOutputAsResultSet("o_compare_version_cursor");
		   
		   Vector objVector=(Vector)clientSession.executeSelectingCall(call);
		   
		   if (objVector != null && objVector.size()>0)//to check the size of the values returned by the above query
	       {
	      	   for (int i = 0;i < objVector.size();i++)//to iterate values from the vector 
	    	   {
	      		 objBookSpec=new BookSpec();
	      		 objRecord = (Record)objVector.get(i);
	      		 System.out.println("objRecord:  "+objRecord);
	      		 if((BigDecimal)objRecord.get(PIXConstants.SPEC_VERSION) != null)//to check for null values of specversion
	      		 {
	      			Expression BookSpecExp = builder.get("specId").equal(specid).and(builder.get("specVersion").equal((BigDecimal)objRecord.get(PIXConstants.SPEC_VERSION)));
	      			objBookSpec= (BookSpec)clientSession.readObject(BookSpec.class,BookSpecExp);
	      		}
	      		 else
	      		 {
	      		   Expression BookSpecExp = builder.get("specId").equal(specid).and(builder.get("specVersion").equal(specversion));
	     		   objBookSpec= (BookSpec)clientSession.readObject(BookSpec.class,BookSpecExp); 
	      		 }
	      		clientSession.logMessages();
	    	   }
	       }
		   else if(objVector.size()== 0 || objVector == null)
		   {
	       	     	 AppException appException=new AppException();
	       	     	 log.info("log info  :no records ");
	 				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_BOOK_SPEC,"BookSpecDAOImpl");  
	 				 throw appException;
	       } 
		   
		   
	   }
	   catch(TopLinkException e)
	     {
	        AppException appException = new AppException();
	        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"BookSpecDAOImpl,getBookSpecLastAckDetail",e);
	        throw appException;
	     }
	   finally
	    {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	    }
	   return objBookSpec;
	   
  }
   
   /** This method is to update the bookspec acknowledgement from the detail page
	 * @param  com.pearson.pix.dto.bookspecification.BookSpec
	*/
   
   public String saveVendorDetail(BookSpec objBookSpec) throws AppException
   {
	   Session clientSession = null;
	   UserTransaction transaction = null; 
	   Connection conn = null;
	   String comments = null;
	   BigDecimal specid=null;
	   BigDecimal specversion=null;
	   BookSpecParty objBookSpecParty=null;
	   try
	   	{
		   clientSession = getClientSession();
		   transaction = getUserTransaction();
		   conn=getDataSourceConnection();
		   PreparedStatement pstmtBookSpec=null;
		   PreparedStatement pstmtParty=null;
		   //Statement stmt=conn.createStatement();
		   specid=objBookSpec.getSpecId();
		   specversion=objBookSpec.getSpecVersion();
		   Collection bookSpecCollection=objBookSpec.getPartyCollection();
		   ExpressionBuilder builder = new ExpressionBuilder();
		   Expression wcPO = builder.get("specId").equal(specid).and(builder.get("specVersion").equal(specversion));
           BookSpec BookSpecCurrent = (BookSpec)clientSession.readObject(BookSpec.class,wcPO);
           for(int i=0;i<bookSpecCollection.size();i++)//Loop for iterating party collection
           { 
        	   objBookSpecParty = (BookSpecParty)((Vector)bookSpecCollection).get(i);
               if((PIXConstants.VENDOR_ROLE).equals(objBookSpecParty.getPartyType()))//Checking party for vendor role
                {
            	   	comments =PIXUtil.escapeHTMLChars(objBookSpecParty.getComments()) ;
            	   	if("".equals(comments))
            	   		comments=PIXConstants.Acknowledged;
                 }

           }
           if(objBookSpec.getModDateTime().before(BookSpecCurrent.getModDateTime()))

           {
	           AppException ae = new AppException();
	           ae.performErrorAction(Exceptions.DATA_CUNCURRENCY, "BookSpecDAOImpl,saveVendorDetail");
	           throw ae;
           }
           pstmtBookSpec=conn.prepareStatement("update PIX_BOOK_SPEC " +
           							   "set mod_date_time=sysdate " +
           							   "where spec_id=? and " +
           							   "spec_version=?");
           pstmtBookSpec.setObject(1,objBookSpec.getSpecId());
           pstmtBookSpec.setObject(2,objBookSpec.getSpecVersion());
           pstmtBookSpec.executeUpdate();
           
           pstmtParty=conn.prepareStatement("update PIX_BOOK_SPEC_PARTY " +
           							   "set comments=?,acknowledge_flag='Y' " +
           							   "where spec_id=? and spec_version=? " +
           							   "and party_type='V'");
           pstmtParty.setObject(1,comments);
           pstmtParty.setObject(2,objBookSpec.getSpecId());
           pstmtParty.setObject(3,objBookSpec.getSpecVersion());
           pstmtParty.executeUpdate();
           //stmt.executeUpdate("update PIX_BOOK_SPEC set mod_date_time=sysdate where spec_id="+specid+" and spec_version="+specversion+"");
           //stmt.executeUpdate("update PIX_BOOK_SPEC_PARTY set comments='"+comments+"',acknowledge_flag='Y' where spec_id="+specid+" and spec_version="+specversion+" and party_type='V'");
          


		}
	   catch(TopLinkException te)
	   {
	 	   AppException ae = new AppException();
	 	   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"BookSpecDAOImpl,saveVendorDetail",te);
	 	   try 
	 	   { 
	 		   transaction.setRollbackOnly();
	 	   } 
	 	   catch(Throwable tu) 
	 	   { 
	 		   throw ae; 
	 	   }
	 	   throw ae;
	   }
	  catch(SQLException se)
	  {
	 	   AppException ae = new AppException();
	 	   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"BookSpecDAOImpl,saveVendorDetail",se);
	 	   try 
	 	   { 
	 		   transaction.setRollbackOnly();
	 	   } 
	 	   catch(Throwable tu) 
	 	   { 
	 		   throw ae; 
	 	   }
	 	   throw ae;
	  }
	  finally{
	 	 
	 	 try 
	 	   { 
	 		   conn.close();
	 		   if(clientSession!=null)
	 		   {
	 			    clientSession.release();
	 		   }
	 	   } 
	 	   catch(Throwable tu) 
	 	   { 
	 		   throw new RuntimeException(tu); 
	 	   }
	 	   
	  }
	   return "";
   }
   
   /** This method is to insert multiple  bookspec acceptance for acknowledgement from the list page
	 * @param idVersionString,login
	*/
  
   public String insertMultipleBookSpecAccept(String idVersionString,String login) throws AppException
   {
  	   Session clientSession = null;
  	   try
  	   {
  		   clientSession = getClientSession();
  		   StoredProcedureCall call = new StoredProcedureCall(); 
  		   call.setProcedureName("pix_bsp_split_proc");//to call the procedure
  		   call.addNamedArgumentValue("v_in",idVersionString);//puting the parameters in the procedure
  		   call.addNamedArgumentValue("v_user",login);//puting the parameters in the procedure
  		   clientSession.executeNonSelectingCall(call);//executing the procedure
  	   }
  	   catch(TopLinkException te)
  	   {
  		   AppException ae = new AppException();
  		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"BookSpecDAOImpl,insertMultipleBookSpecAccept",te);
  		   throw ae;
  	   }
  	   finally
  	   {
  		   if(clientSession!=null)
  		   {
  			   clientSession.release();
  		   }
  	   }
  	   return "";
   }
  
}
 