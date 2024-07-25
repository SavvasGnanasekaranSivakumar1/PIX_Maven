package com.pearson.pix.dao.inventory;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dao.reports.ReportDAOImpl;
import com.pearson.pix.dto.admin.Party;
import com.pearson.pix.dto.inventory.Inventory;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.sessions.Session;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;
import javax.transaction.UserTransaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.sessions.Record;

/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink
 * @author sudam.sahu
 */
public class InventoryDAOImpl extends BaseDAO implements InventoryDAO 
{
	/**
     * Logger.
     */
	
	private static Log log = LogFactory.getLog(ReportDAOImpl.class.getName());
   /**
    * Constructor for initializing InventoryDAOImpl
    */
   public InventoryDAOImpl() 
   {
    
   }
   
   /**
    * Gets the Inventory Information
    * @param isbn
    * @param rawMaterialNumber
    * @param san
    * @return java.util.Collection
    */
   public Collection getInventory(String isbn,String isbn13, String rawMaterialNumber,String san)throws AppException 
   {
	   Inventory objInventory=null;
	   Session clientSession=null;
	   Vector vectorInventoryDetail=new Vector();
	   try
	   {
		 clientSession = getClientSession();
		 Record objRecord=null;
		 //the following query is used for getting the inventory details 
		 Vector objVector=(Vector)clientSession.executeSelectingCall(new SQLCall("SELECT PIM.INVENTORY_ID," +
                    "PIM.LINE_ITEM_NO,PIM.SAN,PIM.ISBN10,PIM.ISBN13,PIM.COMPONENT_ID,PIM.COMPONENT_SEQ_NO," +
                    "PIM.PRODUCT_CODE,PIM.PRODUCT_DESCRIPTION,(SELECT PIL.STOCK_IN_HAND FROM PIX_INVENTORY_LINE " +
                    "PIL WHERE PIL.INVENTORY_ID = PIS.INVENTORY_ID AND PIL.INVENTORY_VERSION =PIS.INVENTORY_VERSION " +
                    "AND PIL.LINE_ITEM_NO = PIM.LINE_ITEM_NO)as DELIVERED_QUANTITY," +
                    "NVL((PIS.inventory_version + 1),1) AS inventory_version," +
                    "NVL((SELECT PRINT_NO FROM PIX_INVENTORY WHERE INVENTORY_ID = PIS.INVENTORY_ID " +
                    "AND INVENTORY_VERSION =PIS.INVENTORY_VERSION),0) PRINT_NO FROM PIX_INVENTORY_MASTER PIM," +
                    "PIX_INVENTORY_SUMMARY PIS WHERE PIM.INVENTORY_ID = PIS.INVENTORY_ID(+) AND " +
                    "nvl(PIM.ISBN10,'?') = NVL('"+ isbn +"', nvl(pim.ISBN10,'?')) " +
                    "AND nvl(PIM.ISBN13,'?') = NVL('"+ isbn13 +"', nvl(pim.ISBN13,'?')) AND " +
                    "PIM.SAN = '"+ san +"' AND PIM.PRODUCT_CODE = NVL('"+ rawMaterialNumber +"', PIM.PRODUCT_CODE)" +
                    "ORDER BY PIM.LINE_ITEM_NO"));
		 if (objVector != null && objVector.size()>0)//to check the size of the vector whether it is null or not
	       {
	      	   for (int i = 0;i < objVector.size();i++)//to iterate values from the vector returned by the above query 
	    	   {
	      		   objInventory=new Inventory();
	      		   objRecord = (Record)objVector.get(i);
	      		   objInventory.setProductCode((String)objRecord.get(PIXConstants.PRODUCT_CODE));
	      		   objInventory.setProductDescription((String)objRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
	      		   if((((BigDecimal)objRecord.get(PIXConstants.DELIVERED_QUANTITY)) != null))//to check whether the DELIVERED_QUANTITY field is null or not
	      		     {
	      			  objInventory.setStockInHand(new Integer(((BigDecimal)objRecord.get(PIXConstants.DELIVERED_QUANTITY)).intValue()));
	      		     }
	      		   objInventory.setInventoryId((BigDecimal)objRecord.get(PIXConstants.INVENTORY_ID));
	      		   objInventory.setInventoryVersion((BigDecimal)objRecord.get(PIXConstants.INVENTORY_VERSION));
	      		   objInventory.setLineItemNo((BigDecimal)objRecord.get(PIXConstants.LINE_ITEM_NO));
	      		   vectorInventoryDetail.add(objInventory);
	    	   }
	       }
		 else if(objVector.size()== 0 || objVector == null)
		   {
	       	     	 AppException appException=new AppException();
	       	     	 log.info("log info  :no records ");
	 				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_INVENTORY,"InventoryDAOImpl");  
	 				 throw appException;
	       } 
	   }
	   catch(TopLinkException e)
	     {
	        AppException appException = new AppException();
	        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"InventoryDAOImpl,getInventory",e);
	        throw appException;
	     }
	   finally
	    {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	    }
	   return vectorInventoryDetail;
   }
   
   /**
    * inserts  the Inventory details into the database
    * @param printNumber
    * @param  san
    * @param inventoryCollection
    * @param inventoryId
    * @param inventoryVersion
    * @return String
    */
   public String insertInventory(Integer printNumber,String san,String createdBy,Collection inventoryCollection,BigDecimal inventoryId,BigDecimal inventoryVersion)throws AppException 
   {
	  
	   UserTransaction transaction = null; 
	   Connection conn = null;
	   if(printNumber==null)
	   {
		   printNumber=new Integer(0);
	   }
	   try
       {   
	   
	   transaction = getUserTransaction();
	   conn=getDataSourceConnection(); 
	   PreparedStatement pstmt1=null;
	   PreparedStatement pstmt2=null;
	   PreparedStatement pstmt3=null;
	   PreparedStatement pstmt4=null;
	   //the following four queris are used to insert the inventory details into the database
	   pstmt1=conn.prepareStatement( "insert into pix_inventory"
			   						+"(inventory_id,inventory_version,Print_no,"
			   						+"transaction_history_no,created_by,modified_by,"
			   						+"creation_date_time,mod_date_time)values"
			   						+"(?,?,?,null,?,?,sysdate,sysdate)");
	   pstmt1.setObject(1,inventoryId);
	   pstmt1.setObject(2,inventoryVersion);
	   pstmt1.setObject(3,printNumber);
	   pstmt1.setObject(4,createdBy);
	   pstmt1.setObject(5,createdBy);
	   pstmt1.executeUpdate();
	   pstmt2=conn.prepareStatement("insert into pix_inventory_party(inventory_id," 
			   						+"inventory_version,party_line_no,san,name_1,name_2,name_3,"
			   						+"org_unit_code,org_unit_name,address_1,address_2,"
			   						+" address_3,address_4,city, state,postal_code,party_type)"
			   						+"select ?,?,1,san,name_1,name_2,name_3,org_unit_code,org_unit_name,"
			   						+"address_1,address_2,address_3,address_4,city," 
			   						+"state,postal_code,party_type from pix_admin_party where" 
			   						+" san = ? and party_type = 'V'");
	   pstmt2.setObject(1,inventoryId);
	   pstmt2.setObject(2,inventoryVersion);
	   pstmt2.setObject(3,san);
	   pstmt2.executeUpdate();
	   pstmt3=conn.prepareStatement("insert into pix_inventory_party_contact(inventory_id,"
			   						+"inventory_version,party_line_no,contact_no," 
			   						+"contact_type,contact_first_name,contact_last_name,"
			   						+"mobile)select ?,?,1,1," 
			   						+"'PRIMARY',contact_first_name,"
			   						+"contact_last_name,mobile from pix_admin_party "
			   						+"where san = ? and party_type = 'V'");
	   pstmt3.setObject(1,inventoryId);
	   pstmt3.setObject(2,inventoryVersion);
	   pstmt3.setObject(3,san);
	   pstmt3.executeUpdate();
	   int inventoryItem=inventoryCollection.size();	   
		   for(int i=0; i<inventoryItem; i++)//to iterate values from the inventoryCollection
		   {
			   	
			    Inventory objInventory=(Inventory)((Vector)inventoryCollection).get(i);
			    pstmt4=conn.prepareStatement("insert into pix_inventory_line(inventory_id,"
			    							+"inventory_version,line_item_no,stock_in_hand)values " +
				   							"(?,?,?,?)");
			    pstmt4.setObject(1,inventoryId);
				pstmt4.setObject(2,inventoryVersion);
				pstmt4.setObject(3,objInventory.getLineItemNo());
				pstmt4.setObject(4,objInventory.getStockInHand());
				pstmt4.executeUpdate();
		   }
		   return "";
   }
	   catch(TopLinkException te)
	   {
	 	   AppException ae = new AppException();
	 	   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"InventoryDAOImpl,insertInventory",te);
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
	 	   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"InventoryDAOImpl,insertInventory",se);
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
	 		   
	 	   } 
	 	   catch(Throwable tu) 
	 	   { 
	 		   throw new RuntimeException(tu); 
	 	   }
	 	   
	  }
	   
   }
   
   /**
    * gets the party names from pix_admin_party table
    * to show the fields in the drop down of inventory search page
    * @return java.util.Collection
    */
   
   public Collection getPartyName(int userId)throws AppException
   {
  	
  	 	  	 Collection objPartyName = null;
  	 	  	 Session clientSession = null;
  	 try
  	 {		
  		 	 clientSession = getClientSession();
  		  	 objPartyName = (Vector)getClientSession().readAllObjects(Party.class,new SQLCall("select san,name_1" +
  		  	 		" from pix_admin_party where party_type in('V','S')" +
  		  	 		" and san in (select san from pix_admin_user_party where user_id="+ userId +")order by name_1"));
  	 }
  	 catch(TopLinkException e)
  	 {
  		   	 AppException ae = new AppException();
  		   	 ae.performErrorAction("Exceptions.SQL_EXCEPTION","InventoryDAOImpl,getPartyName",e);
  		   	 throw ae;
     }
  	 finally
  	 {
  		    if(clientSession!=null)
  		    {
  		    	clientSession.release();
  		    }
  	 }
  	   
  	 return objPartyName;
   }
}
