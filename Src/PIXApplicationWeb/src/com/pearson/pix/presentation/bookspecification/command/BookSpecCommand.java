package com.pearson.pix.presentation.bookspecification.command;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Vector;
import com.lowagie.text.Document;
import com.pearson.pix.presentation.home.action.HomePageForm;
import com.pearson.pix.presentation.home.delegate.HomePageDelegate;
import com.pearson.pix.presentation.pdf.BookSpecificationPdf;
import com.pearson.pix.presentation.base.command.BaseCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.bookspecification.BookSpec;
import com.pearson.pix.dto.bookspecification.BookSpecLine;
import com.pearson.pix.dto.bookspecification.BookSpecMargin;
import com.pearson.pix.dto.bookspecification.BookSpecMisc;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.bookspecification.action.BookSpecForm;
import com.pearson.pix.presentation.bookspecification.delegate.BookSpecDelegate;
/**
	* Contains the definitions of methods for different commands raised by the user 
	* for BookSpec.
	* @author sudam.sahu
	*/
public class BookSpecCommand extends BaseCommand 
{
	
	/**
     * Logger.
     */
	
	private static Log log = LogFactory.getLog(BookSpecCommand.class.getName());
	 /**
	    * Constructor for initializing BookSpecCommand
	    */
   public BookSpecCommand() 
   {
    
   }
   
	/**
	* Method for display of List screens
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @param messages
	* @return String
	*/
   
   public String executeList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   	{
	     BookSpecDelegate objBookSpecDelegate=new BookSpecDelegate();
	     Vector objVector = null;
		 String orderby=PIXConstants.SPECIFICATION_DATE;
		 String sort="DESC";
		 String page="";
		 String status=request.getParameter("status");
		 User objUser = null;
		 int userId=0;
		 //added....
		   String idHidden="";
			if(request.getParameter("idHidden")!=null){
				idHidden=request.getParameter("idHidden");
			}
			
		   //....
		 if(request.getSession().getAttribute("USER_INFO")!=null)// to check whether the user info field is null or not
		 {
			 objUser = (User)request.getSession().getAttribute("USER_INFO");
			 userId = objUser.getUserId().intValue();//to get the user id from login
		 }
		 if(request.getParameter("pageFilter")!=null)
		 {
			 page=request.getParameter("pageFilter");
		 }
		 else
		 {
		   page=request.getParameter("page"); //catching the page parameter
		 }
		 
		 request.setAttribute("pageFilter", page);
		 if(PIXUtil.checkNullField(page)==false)//to check whethter it is list page or history page is calling
		 {
			 page= "C";
		 }
		 int currentValue=1;
		 BookSpecForm objBookSpecForm=null;
		 BookSpec objBookSpec=null;
		 String startdate=null;
		 String enddate=null;
		 String specno=null;
		 String flipStatusFilter = null;
		 try
		 {	 
			 if(request.getSession().getAttribute("homeStatus")!=null)
			 {
				 request.getSession().removeAttribute("homeStatus");
			 }
			 if (form instanceof BookSpecForm)// to check the form instance  
			  {
				 objBookSpecForm = (BookSpecForm)form;
				 TitlePrinting titlePrinting= new TitlePrinting();
				 Status objstatus=new Status();
				 objBookSpec=new BookSpec();
				 if((PIXUtil.checkNullField(status)==false))//to check null field value for status
				 {	 
				  objstatus.setStatusDescription(objBookSpecForm.getBookSpec().getStatusDetail().getStatusDescription());
				 }
				 else
				 {
					objstatus.setStatusDescription(status);
				 }
				   request.setAttribute("PATH", request.getContextPath());
				   String specnoFilter = request.getParameter("bookSpec.specNo");//to cache the specNo from from
				   String isbnFilter = request.getParameter("bookSpec.titleDetail.isbn10");//to cache the isbn number from from
				   String printNoFilter = request.getParameter("bookSpec.titleDetail.printNo");//to cache the print no. from form
				   String statusFilter = request.getParameter("bookSpec.statusDetail.statusDescription");//to cache the status description from form
				   String startDateFilter=(String)objBookSpecForm.getStartDate();
				   String endDateFilter=(String)objBookSpecForm.getEndDate();
				   
				   
				   if(PIXUtil.checkNullField(specnoFilter))//to check null field value for specnoFilter
				   {
					   request.setAttribute("specnoFilter",specnoFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("specnoFilter")))
				   {
					   request.setAttribute("specnoFilter",request.getParameter("specnoFilter"));
					   specnoFilter = request.getParameter("specnoFilter");
					  
				   }
				   if(PIXUtil.checkNullField(isbnFilter))//to check null field value for isbnFilter
				   {
					   request.setAttribute("isbnFilter",isbnFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("isbnFilter")))
				   {
					   request.setAttribute("isbnFilter",request.getParameter("isbnFilter"));
					   isbnFilter = request.getParameter("isbnFilter");
					  
				   }
				   if(PIXUtil.checkNullField(printNoFilter))//to check null field value for printNoFilter
				   {
					   request.setAttribute("printNoFilter",printNoFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("printNoFilter")))
				   {
					   request.setAttribute("printNoFilter",request.getParameter("printNoFilter"));
					   printNoFilter = request.getParameter("printNoFilter");
					  
				   }
				   if(PIXUtil.checkNullField(statusFilter))//to check null field value for statusFilter
				   {
					   request.setAttribute("statusFilter",statusFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))//to check null field value for statusFilter
				   {
					   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
					   statusFilter = request.getParameter("statusFilter");
					   objstatus.setStatusDescription(statusFilter);
				   }
				   
				   if(PIXUtil.checkNullField(startDateFilter))//to check null field value for startDateFilter
				   {
					   request.setAttribute("startDateFilter",startDateFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("startDateFilter")))
				   {
					   request.setAttribute("startDateFilter",request.getParameter("startDateFilter"));
					   startDateFilter = request.getParameter("startDateFilter");
					  
				   }
				   if(PIXUtil.checkNullField(endDateFilter))//to check null field value for endDateFilter
				   {
					   request.setAttribute("endDateFilter",endDateFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("endDateFilter")))
				   {
					   request.setAttribute("endDateFilter",request.getParameter("endDateFilter"));
					   endDateFilter = request.getParameter("endDateFilter");
					  
				   }
				   if(PIXUtil.checkNullField(flipStatusFilter))  
				   {
					   request.setAttribute("flipStatusFilter",flipStatusFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("flipStatusFilter")))
				   {
					   request.setAttribute("flipStatusFilter",request.getParameter("flipStatusFilter"));
					   flipStatusFilter = request.getParameter("flipStatusFilter");
					  
				   }
				 
				 titlePrinting.setIsbn10(isbnFilter);
				 titlePrinting.setPrintNo(printNoFilter);
				 objBookSpec.setTitleDetail(titlePrinting);
				 objBookSpec.setSpecNo(specnoFilter);
				 startdate=startDateFilter;
				 enddate=endDateFilter;
				 objBookSpec.setTitleDetail(titlePrinting);
				 objBookSpec.setStatusDetail(objstatus);
				 currentValue =Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
				 specno= request.getParameter(PIXConstants.BOOK_SPEC_NO);
		      }
			  else if (form instanceof HomePageForm)//to check the form instance of HomePageForm
			   {
				 String statusFilter=""; 
				 objBookSpecForm = new BookSpecForm(); 
				 HomePageForm homePageForm = (HomePageForm)form;
				 objBookSpec=new BookSpec();
				 String isbnFilter = homePageForm.getIsbn();
				 String printNoFilter = homePageForm.getPrintno();
				 TitlePrinting td= new TitlePrinting();
				 td.setIsbn10(homePageForm.getIsbn());
				 td.setPrintNo(homePageForm.getPrintno());
				 objBookSpec.setTitleDetail(td);
				 Status objStatus=new Status();
				   if(request.getParameter("status")!=null)//to get the value status from request 
				   {
				   statusFilter=request.getParameter("status");
				   }
				   else
				   {
					  statusFilter=homePageForm.getStatusDescription();
				   }
				   if(PIXUtil.checkNullField(statusFilter))//to check the null field value for statusFilter
				   {
					   request.setAttribute("statusFilter",statusFilter);
					   request.getSession().setAttribute("homeStatus",statusFilter);
					   objStatus.setStatusDescription(statusFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
				   {
					   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
					   statusFilter = request.getParameter("statusFilter");
					   objStatus.setStatusDescription(statusFilter);
				   }
				   if(PIXUtil.checkNullField(isbnFilter))
				   {
					   request.setAttribute("isbnFilter",isbnFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("isbnFilter")))
				   {
					   request.setAttribute("isbnFilter",request.getParameter("isbnFilter"));
					   isbnFilter = request.getParameter("isbnFilter");
					  
				   }
				   if(PIXUtil.checkNullField(printNoFilter))
				   {
					   request.setAttribute("printNoFilter",printNoFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("printNoFilter")))
				   {
					   request.setAttribute("printNoFilter",request.getParameter("printNoFilter"));
					   printNoFilter = request.getParameter("printNoFilter");
					  
				   }
				   if(PIXUtil.checkNullField(flipStatusFilter))  
				   {
					   request.setAttribute("flipStatusFilter",flipStatusFilter);
				   }
				   else if(PIXUtil.checkNullField(request.getParameter("flipStatusFilter")))
				   {
					   request.setAttribute("flipStatusFilter",request.getParameter("flipStatusFilter"));
					   flipStatusFilter = request.getParameter("flipStatusFilter");
					  
				   }
				   objBookSpec.setStatusDetail(objStatus);
				   request.setAttribute("bookspecform", objBookSpecForm);
				   request.setAttribute("PATH", request.getContextPath());
			   }
			 if(request.getSession().getAttribute("USER_INFO")!=null){
					 objVector = objBookSpecDelegate.displayBookSpecList( 
		             objBookSpec,userId,page,currentValue,orderby,sort,startdate,enddate,specno); 
					 int size = objVector.size();
					 PIXUtil.getNextPage(request,currentValue,size);
					 PIXUtil.getPrevPage(request,currentValue);
					 if(size > PIXConstants.PAGE_SIZE)//to check the page size value
				       {           
						 objVector.remove(((Vector)objVector).get(size-1));
				       } 
					 objBookSpecForm.setBookSpecCollection(objVector);
			 }
				 request.setAttribute("checkBoxIdList",idHidden);
				 return FrontEndConstants.LIST;
			 
		 } 
		 catch(AppException e)
		 {
			  String errMsg = e.getSErrorDescription();
			  log.info("log info  :no records ");
			  log.info("The Error Mesage is = " +errMsg);
			  request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			  return FrontEndConstants.ERROR;
		 }
		 catch(Exception e)
		   {
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.EXCEPTION,"BookSpecCommand,executeList",e);
			   String errMsg = ae.getSErrorDescription();
			   log.info("The Error Mesage is = " +errMsg);
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }	   
		
  	}
   /**
    * Method for General purpose if any 
    * @param command
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeGeneral(final String actioncommand,final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   	{
	   User objUser = null;
	   String login = "";
	   String status = "";
	   try
	   {
		   
		   if("multiplebookspecAccept".equals(actioncommand))//to check the value of check box selected in the bookspec list page
		   {
			   if(request.getSession().getAttribute("homeStatus")!=null) 
			   {		   
				   status = (String)request.getSession().getAttribute("homeStatus");
				   if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
					{
						String page_value = request.getParameter("PAGE_VALUE");
						request.setAttribute(PIXConstants.OK_URL,"/home/bookspeclist.do?page=I&status="+status+"&PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page called this url when coming from homepage
					}
				   else
				   {
					   request.setAttribute(PIXConstants.OK_URL,"/home/bookspeclist.do?page=I&status="+status+"&PAGE_VALUE=1");	//For "Ok" button on Success/Error page called this url when coming from homepage
				   }
				   request.getSession().removeAttribute("homeStatus");
			   }
			   else
			   {
				   if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
					{
						String page_value = request.getParameter("PAGE_VALUE");
						String list_page ="?PAGE_VALUE="+page_value;
						if(!PIXUtil.checkNullField(request.getParameter("pageFilter")) & !PIXUtil.checkNullField(request.getParameter("specnoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
					    {
							request.setAttribute(PIXConstants.OK_URL,"/bookspecification/bookspeclist.do?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
					    }
					    else
					    {
					    	if(request.getParameter("pageFilter")!="")
					    	{
					    		list_page += "&pageFilter="+request.getParameter("pageFilter");
					    	}
					    	if(request.getParameter("specnoFilter")!="")
					    	{
					    		list_page += "&specnoFilter="+request.getParameter("specnoFilter");
					    	}
					    	if(request.getParameter("isbnFilter")!="")
					    	{
					    		list_page += "&isbnFilter="+request.getParameter("isbnFilter");
					    	}
					    	if(request.getParameter("printNoFilter")!="")
					    	{
					    		list_page += "&printNoFilter="+request.getParameter("printNoFilter");
					    	}
					    	if(request.getParameter("statusFilter")!="")
					    	{
					    		list_page += "&statusFilter="+request.getParameter("statusFilter");
					    	}
					    	if(request.getParameter("startDateFilter")!="")
					    	{
					    		list_page += "&startDateFilter="+request.getParameter("startDateFilter");
					    	}
					    	if(request.getParameter("endDateFilter")!="")
					    	{
					    		list_page += "&endDateFilter="+request.getParameter("endDateFilter");
					    	}
					    	request.setAttribute(PIXConstants.OK_URL,"/bookspecification/bookspeclist.do"+list_page);	//For "Ok" button on Success/Error page
					    }
					}
				   else
				   {
					   request.setAttribute(PIXConstants.OK_URL,"/bookspecification/bookspeclist.do?PAGE_VALUE=1");	//For "Ok" button on Success/Error page called this url when coming from active list
				   }
			   }
			   String strSelectedCheckboxes = request.getParameter("checksArr");
			   if(request.getSession().getAttribute("USER_INFO")!=null)//to get the user id  from login
			   {
				   objUser = (User)request.getSession().getAttribute("USER_INFO");
				   login = objUser.getLogin();
			   }
			   BookSpecDelegate  objBookSpecDelegate=new BookSpecDelegate();
			   if(request.getSession().getAttribute("USER_INFO")!=null){
                   objBookSpecDelegate.insertMultipleBookSpecAccept(strSelectedCheckboxes,login);
                 }
			   String messageKey = "Book Specification(s) has been successfully acknowledged.";
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	  
			   return "multiplebookspecAccept";
		   }		   
		   return "";
	   }
	   catch(AppException ae)
	   {
		   String errMsg = ae.getSErrorDescription();
		   log.info("The Error Mesage is = " +errMsg);
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"BookSpecCommand,executeGeneral",e);
		   String errMsg = ae.getSErrorDescription();
		   log.info("The Error Mesage is = " +errMsg);
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	   
	   
   	}
   
	   /**
	    * Method for display of Detail screens. 
	    * Command will call displayBookSpec twice first time to get the latest Book spec 
	    * detail and second time to get the record previous to latest for comparison to 
	    * highlight the changes in UI.
	    * 
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @param messages
	    * @return String
	    */
   public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   	{	
	   BookSpec objBookSpec=null;
	   BookSpec objBookSpecPrev=null;
	   LinkedHashMap objPrevDetHM=null;
	   String accessRight = "";
	   try
	   {
		   BookSpecForm objBookSpecForm =(BookSpecForm)form;
		   String specficationid = request.getParameter(PIXConstants.SPEC_ID);
		   
		   /*For differentiating that the url hit from which screen.*/
		   String module = request.getParameter(PIXConstants.MODULE);
		   
		   Integer specid=new Integer(String.valueOf(specficationid));
		   String specificationversion = request.getParameter(PIXConstants.SPEC_VERSION);
		   Integer specversion=new Integer(String.valueOf(specificationversion));
		   BookSpecDelegate objBookSpecDelegate = new BookSpecDelegate();
		   if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.BOOKSPEC_CODE))//to check the access writes for admin,buyer and vendor
		   {
			   accessRight = PIXConstants.WRITE;
		   }
		   else
		   {
			   accessRight = PIXConstants.READ;
		   }
		   
		   if(specversion.intValue()> 1)/*to check the  specversion,if specversion >1 then it will call 
			   								the bookspec last acknowledgement version to be displayed in the 
			   								detail page of book spec*/
		   {
			   objBookSpecPrev =(BookSpec)objBookSpecDelegate.displayBookSpecLastAckDetail(specid,new Integer(specversion.intValue()));
			   objPrevDetHM =prevDetail(objBookSpecPrev);
			   
		   }
		   else 
		   {
			   
			   objBookSpecPrev =(BookSpec)objBookSpecDelegate.displayBookSpecDetail(specid,
					   new Integer(specversion.intValue()));
			   objPrevDetHM =prevDetail(objBookSpecPrev);  
			   
		   }
		   
		   objBookSpec =(BookSpec)objBookSpecDelegate.displayBookSpecDetail(specid,specversion);    
		   objBookSpecForm.setBookSpec(objBookSpec);
		   objBookSpecForm.setModule(module);
		   objBookSpecForm.setBookSpecPrev(objPrevDetHM);
		   request.setAttribute("BookspecAccessRight",accessRight);
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
		   e.printStackTrace();
		   ae.performErrorAction(Exceptions.EXCEPTION,"BookSpecCommand,executeDisplay",e);
		   String errMsg = ae.getSErrorDescription();
		   log.info("The Error Mesage is = " +errMsg);
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	 
	    
   	}
   
   /**
    * Private Method to get the  just previous version of current version of Detail screens. 
    * @param BookSpec
    * @return LinkedHashMap
    */
   
   private LinkedHashMap prevDetail(BookSpec objBookSpecPrev)throws AppException
   {
	   LinkedHashMap objHashMap=new LinkedHashMap();
	   LinkedHashMap objHashMap1=null;
	     
	   int lineItem=objBookSpecPrev.getLineItemCollection().size();
	   for(int i=0;i<lineItem;i++)
	   {
		   objHashMap1=new LinkedHashMap();
		   BookSpecLine objLine = (BookSpecLine)((Vector)objBookSpecPrev.getLineItemCollection()).get(i);
		   String prod_code=objLine.getProductCode();
		   int miscCollection=objLine.getMiscCollection().size();
		   for(int k=0;k<miscCollection;k++)//this for loop is used to get the miscellaneous item collection
			{
				BookSpecMisc bookSpecMisc = (BookSpecMisc)((Vector)objLine.getMiscCollection()).get(k);
				String label=bookSpecMisc.getLabel();
				String value=bookSpecMisc.getValue();
				objHashMap1.put(label,value);
			}
			
		   	objHashMap.put(prod_code,objHashMap1);
		   	
		   	
	}
			
		
	   return objHashMap;	   
		  
   }
	  
	   /**
	    * Method for Insert operation
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @return String
	    */
   public String executeInsert(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response)
   {

	   return null;
   	}
   
	   /**
	    * Method for Update operation
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @param messages
	    * @return String
	    */
   public String executeUpdate(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   	{	
	   	try
	   	{
	   		if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
	   		{
	   			String page_value = request.getParameter("PAGE_VALUE");
	   			String list_page ="?PAGE_VALUE="+page_value;
				if(!PIXUtil.checkNullField(request.getParameter("pageFilter")) & !PIXUtil.checkNullField(request.getParameter("specnoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
			    {
					request.setAttribute(PIXConstants.OK_URL,"/bookspecification/bookspeclist.do?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
			    }
			    else
			    {
			    	if(request.getParameter("pageFilter")!="")
			    	{
			    		list_page += "&pageFilter="+request.getParameter("pageFilter");
			    	}
			    	if(request.getParameter("specnoFilter")!="")
			    	{
			    		list_page += "&specnoFilter="+request.getParameter("specnoFilter");
			    	}
			    	if(request.getParameter("isbnFilter")!="")
			    	{
			    		list_page += "&isbnFilter="+request.getParameter("isbnFilter");
			    	}
			    	if(request.getParameter("printNoFilter")!="")
			    	{
			    		list_page += "&printNoFilter="+request.getParameter("printNoFilter");
			    	}
			    	if(request.getParameter("statusFilter")!="")
			    	{
			    		list_page += "&statusFilter="+request.getParameter("statusFilter");
			    	}
			    	if(request.getParameter("startDateFilter")!="")
			    	{
			    		list_page += "&startDateFilter="+request.getParameter("startDateFilter");
			    	}
			    	if(request.getParameter("endDateFilter")!="")
			    	{
			    		list_page += "&endDateFilter="+request.getParameter("endDateFilter");
			    	}
			    	request.setAttribute(PIXConstants.OK_URL,"/bookspecification/bookspeclist.do"+list_page);	//For "Ok" button on Success/Error page
			      }
			}
	   		else
	   		{
	   			request.setAttribute(PIXConstants.OK_URL,"/bookspecification/bookspeclist.do?PAGE_VALUE=1");	//For "Ok" button on Success/Error page
	   		}
	   		BookSpecForm objBookSpecForm = (BookSpecForm)form;
	   		BookSpec objBookSpec=objBookSpecForm.getBookSpec();
	   		BookSpecDelegate objBookSpecDelegate = new BookSpecDelegate();
	   		objBookSpecDelegate.saveBookSpecAknowledgement(objBookSpec);
	   		String messagekey="Book Specification No. "+""+ objBookSpec.getSpecNo() + " has been successfully acknowledged.";
	   		request.setAttribute(PIXConstants.SUCCESS_STRING,messagekey);	
	   		return FrontEndConstants.UPDATE;
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"BookSpecCommand,executeUpdate",e);
		   String errMsg = ae.getSErrorDescription();
		   log.info("The Error Mesage is = " +errMsg);
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
		}	
}
	
	   /**
	    * This should be overridden by subclasses.
	    * The subclass's executeRelatedLists() is called before any other executeXXXX() 
	    * by BaseAction to ensure required lists of related data are available in some 
	    * context to the JSPs when each page is called.
	    * @param command
	    * @param mapping
	    * @param form
	    * @param request
	    * @param reponse
	    * @param messages
	    */
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form,final HttpServletRequest request, final HttpServletResponse reponse, final ActionMessages messages) 
   	{
	   try
	   {
		   User objUser=null;
		   int userId=0;
		   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
		   {
				   objUser =(User)request.getSession().getAttribute("USER_INFO");
				   userId = objUser.getUserId().intValue();//to get  the user id from login page 
			   }
		   if(form instanceof BookSpecForm)//to check the form instatnce
		   {
		   	  BookSpecDelegate objBookSpecDelegate = new BookSpecDelegate();  
		   	  Vector objAllStatus=(Vector)objBookSpecDelegate.displayBookSpecStatus();
		   	  request.setAttribute("BookSpecAllStatus",objAllStatus);
		    }
		   else if((form instanceof HomePageForm)&&(request.getSession().getAttribute("USER_INFO")!=null))//to check the home page form instance
		   {
			   BookSpecDelegate objBookSpecDelegate = new BookSpecDelegate();  
			   Vector objAllStatus=(Vector)objBookSpecDelegate.displayBookSpecStatus();
			   request.setAttribute("BookSpecAllStatus",objAllStatus);
			   Vector objInboxdetail=new Vector();
			   HomePageDelegate objHomePageDelegate=new HomePageDelegate();
			   if(request.getSession().getAttribute("USER_INFO")!=null){		//Added by Shweta for Session hadling in case of userId as 0
			   	   objInboxdetail=(Vector)objHomePageDelegate.displayInboxdetail(userId);
			   	if(objInboxdetail.size()==0) //Checking condition for no records in inbox
				{
					request.setAttribute("norecordsmsg","Currently there are no messages to display in your inbox.");
				}
			   }
			       request.setAttribute("inboxdetails",objInboxdetail);
		   }
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"BookSpecCommand,executeRelatedList",e);
		   String errMsg = ae.getSErrorDescription();
		   log.info("The Error Mesage is = " +errMsg);
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	
	     
   	}
   
   public String executeFinished(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
   	{	   
	   return FrontEndConstants.FORWARD_FINISHED;
   	}
   
   /**
    * Method for pdf generation for book spec
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   
   public String executepdf(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
      Document objDocument = null;
      String reportName="BookSpecification";
      try
      {
           BookSpecificationPdf objBookSpecificationPdf = new BookSpecificationPdf();
           request.getSession().setAttribute("PDF_OBJECT",objBookSpecificationPdf);
   	    request.getSession().setAttribute("PDF_Name",reportName);
   	    response.sendRedirect("../pdfFileDownloadServlet");
          /* objDocument = objBookSpecificationPdf.display(request,response);
           objDocument.close();*/
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
   	  AppException ae = new AppException();
   	   ae.performErrorAction(Exceptions.EXCEPTION,"ProcurementPlanCommand,executePDF",e);
   	   String errMsg = ae.getSErrorDescription();
   	   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
      }
   	  return FrontEndConstants.EXPORTPDF;
    }   
}
