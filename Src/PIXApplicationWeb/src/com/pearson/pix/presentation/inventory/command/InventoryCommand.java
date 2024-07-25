package com.pearson.pix.presentation.inventory.command;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.inventory.Inventory;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.inventory.action.InventoryForm;
import com.pearson.pix.presentation.inventory.delegate.InventoryDelegate;
import com.pearson.pix.business.common.PIXUtil;

/**
 * Contains the definitions of methods for different commands raised for 
 * InventoryCommand.
 * @author sudam.sahu
 */
public class InventoryCommand extends BaseCommand 
{
	/**
     * Logger.
     */
	
	private static Log log = LogFactory.getLog(InventoryCommand.class.getName());
   /**
    * Constructor for Initializing InventoryCommand
    */
   public InventoryCommand() 
   {
    
   }
   
   /**
    * Method for display of Detail screens
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
 * @throws AppException 
    */
   public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages)  
   {
	   User objUser = null;
	   int userId=0;
		 if(request.getSession().getAttribute("USER_INFO")!=null)// to check whether the user info field is null or not
		 {
			 objUser = (User)request.getSession().getAttribute("USER_INFO");
			 userId = objUser.getUserId().intValue();//to get the user id from login
		 }
	   try
	   {
		   
	   InventoryForm objInventoryForm=(InventoryForm)form;
	   Collection objCollection=null;
	   InventoryDelegate  objInventoryDelegate=new InventoryDelegate();
	   String isbn=objInventoryForm.getIsbn10();
	   //objInventoryForm.setIsbn10(isbn);
	   log.debug("isbn no------->"+isbn);
	   String isbn13=objInventoryForm.getIsbn10();
	   log.debug("isbn no------->"+isbn13);
	   String rawMaterialNumber=objInventoryForm.getRawMaterialNo();
	   //objInventoryForm.setRawMaterialNo(rawMaterialNumber);
	   log.debug("raw material number---->"+rawMaterialNumber);
	   String san=objInventoryForm.getParty().getSan();
	   log.debug("san number--->"+san);
	   /* Change Start by shweta for session handling in case of session timeout */
	   Vector objPartyName = new Vector();	
	   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
	   {
		   objPartyName=(Vector)objInventoryDelegate.displayPartyName(userId);
		  
	   }
	   String accessRight = "";
	   /*Change End */
	   request.setAttribute("PartyName",objPartyName);//sets the value of the vector into request
	   if(isbn.length()>10)//to check the length of the isbn number entered by the user in the form
	   {
		   
		  isbn="";
	   }
	   else if(isbn.length()==10)//to check the length of the isbn number entered by the user in the form
	   {
		  isbn13="";
		    
	   }
	   if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.INVENTORY_CODE))//to check the access writes for admin,buyer and vendor
	   {
		   accessRight = PIXConstants.WRITE;
	   }
	   else
	   {
		   accessRight = PIXConstants.READ;
	   }
	   
	   objCollection=objInventoryDelegate.displayInventory(isbn,isbn13,rawMaterialNumber,san);
	   objInventoryForm.setInventoryCollection(objCollection);//setting the collection into setInventoryCollection methofd of the form
	   Vector inventoryItem = (Vector)objCollection;
	   Inventory objInventory = (Inventory)inventoryItem.get(0);
	   objInventoryForm.setInventoryId(objInventory.getInventoryId());
	   objInventoryForm.setInventoryVersion(objInventory.getInventoryVersion());
	   objInventoryForm.setLineItemNo(objInventory.getLineItemNo());
	   request.setAttribute("InventoryAccessRight",accessRight);
	   return FrontEndConstants.DISPLAY;
	   }
	   catch(AppException e)
		 {
			  String errMsg = e.getSErrorDescription();
			  log.info("The Error Mesage is = " +errMsg);
			  request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			  return FrontEndConstants.ERROR;
		 }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"InventoryCommand,executeDisplay",e);
		   String errMsg = ae.getSErrorDescription();
		   log.debug("The Error Mesage is = " +errMsg);
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	   
   }
   
   /**
    * Method for display of  party name in drop down of inventory search page
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
  @throws AppException 
    */
   
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse reponse, final ActionMessages messages) 
  	{
	   User objUser = null;
	   int userId=0;
		 if(request.getSession().getAttribute("USER_INFO")!=null)// to check whether the user info field is null or not
		 {
			 objUser = (User)request.getSession().getAttribute("USER_INFO");
			 userId = objUser.getUserId().intValue();//to get the user id from login
		 }
	   try
	   {
		   InventoryDelegate objInventoryDelegate=new InventoryDelegate();
		   /*Change started by Shweta for handling userId in case of session timeout*/
		   Vector objPartyName = new Vector();
		   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
		   {
			   objPartyName=(Vector)objInventoryDelegate.displayPartyName(userId);
		   }
		   /*Change end by Shweta */
		   request.setAttribute("PartyName",objPartyName);
		   request.setAttribute("PATH", request.getContextPath());
		   return FrontEndConstants.RELATEDLIST; 
	   }
	   catch(AppException e)
	   {
			  String errMsg = e.getSErrorDescription();
			  log.info("The Error Mesage is = " +errMsg);
			  request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			  return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"InventoryCommand,executeRelatedList",e);
		   String errMsg = ae.getSErrorDescription();
		   log.debug("The Error Mesage is = " +errMsg);
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	   
	   
  	}
  
   
   /**
    * Method for Insert operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return String
    */
   public String executeInsert(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
	   InventoryForm objInventoryForm=(InventoryForm)form;
	   User objUser = null;
	   String createdBy=null;
	   Integer printNumber=null;
	   try
		  {
			   if(request.getSession().getAttribute("USER_INFO")!=null)//to check the null field value for user info
				 {
					 objUser = (User)request.getSession().getAttribute("USER_INFO");
					 createdBy=objUser.getFirstName();//to get the user name from login page
				 }
			   request.setAttribute(PIXConstants.OK_URL,"/inventory/inventorysearch.do");				
			   BigDecimal inventoryId=objInventoryForm.getInventoryId();
			   log.debug("inventory id---->"+inventoryId);
			   BigDecimal inventoryVersion=objInventoryForm.getInventoryVersion();
			   log.debug("inventory version----->"+inventoryVersion);
			   if(PIXUtil.checkNullField(objInventoryForm.getPrintNum()))
			   {
				    printNumber=new Integer(objInventoryForm.getPrintNum()); 
			   }
			   
			   log.debug("print number---->"+printNumber);
			   String san=objInventoryForm.getParty().getSan();
			   log.debug("san number--->"+san);
			   Collection inventoryCollection=objInventoryForm.getInventoryCollection();
			   InventoryDelegate objInventoryDelegate=new InventoryDelegate();
			   objInventoryDelegate.saveInventory(printNumber,san,createdBy,inventoryCollection,inventoryId,inventoryVersion);
			   String messagekey="inventory details has been successfully inserted";
		       request.setAttribute(PIXConstants.SUCCESS_STRING,messagekey);	
			   
		}
		catch(AppException e)
		   {
			      String errMsg = e.getSErrorDescription();
			      log.info("The Error Mesage is = " +errMsg);
				  request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
				  return FrontEndConstants.ERROR;
		   }
		catch(Exception e)
		   {
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.EXCEPTION,"InventoryCommand,executeInsert",e);
			   String errMsg = ae.getSErrorDescription();
			   log.debug("The Error Mesage is = " +errMsg);
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }	  
		
		return  FrontEndConstants.INSERT;
   }
}
