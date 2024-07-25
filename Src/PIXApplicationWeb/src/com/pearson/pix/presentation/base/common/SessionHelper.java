package com.pearson.pix.presentation.base.common;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

/**
 * Contains all the helper methods for Session Removal.
 */
public class SessionHelper{
    /**
     * Builds the list of forms that are set into session state in struts-config
     * 
     * @return list ArrayList
     */
    public static final ArrayList buildStaticFormsList(){
        ArrayList formsNameList = new ArrayList();
        
        formsNameList.add("userForm");
        formsNameList.add("supplierForm");
        formsNameList.add("addPubUnitForm");
        formsNameList.add("orderStatusForm");
        formsNameList.add("usageForm");
        formsNameList.add("orderDetailForm");
        formsNameList.add("deliveryMessageForm");
        formsNameList.add("goodsReceiptForm");
        formsNameList.add("orderListForm");
        formsNameList.add("bookspecform");
        formsNameList.add("planningForm");
        formsNameList.add("homePageForm");
        formsNameList.add("inventoryForm");
        formsNameList.add("reportForm");
        formsNameList.add("fileUploadingForm");
                
        return formsNameList;
    }

   /**
    * Removes the unnecessary form object from the session.
    * 
    * @param Session
    * @param formOfInterest
   */
   public static HttpSession cleanSession(HttpSession session, String formOfInterest){
       ArrayList formsList = buildStaticFormsList();
       boolean removeFlag = false;
       StringTokenizer tokenizer = new StringTokenizer(formOfInterest,"~");
       String strForm = null;
       if(formOfInterest == "")
       {
    	   removeFlag = true;
       }
       else
       {
    	   do {
    		   strForm = tokenizer.nextToken();
    		   if (formsList.remove(strForm))
                   removeFlag = true;
    	   }while (tokenizer.hasMoreTokens());
       }
       
       if(removeFlag){
           for(int i=0; i < formsList.size(); i++){
               String strFormName = (String)formsList.get(i);
			   session.removeAttribute(strFormName);
           }
           return session;
       }else{
           return null;
       }        
   }   
   
   /**
    * Returns the form name based on the URL in the request
    * @param request
    */
   public static String getCurrentRequestedURLForm(String curURL){

	   if((curURL.indexOf("/purchaseorder/purchaseorderlist") != -1)
               || (curURL.indexOf("/purchaseorder/purchaseorderlist") != -1)
               || (curURL.indexOf("purchaseorder/millpurchaseorderlist") != -1)
               || (curURL.indexOf("/purchaseorder/purchaseorderfilter") != -1)
               || (curURL.indexOf("/purchaseorder/ordersAcceptMessage") != -1)
               || (curURL.indexOf("/purchaseorder/furnishedorderlist") != -1)
               || (curURL.indexOf("/purchaseorder/furnishedorderfilter") != -1)
               ){
           return "orderListForm";
       }
       else if((curURL.indexOf("/purchaseorder/orderdetail") != -1)
               || (curURL.indexOf("/purchaseorder/orderdetailmessage") != -1)
               || (curURL.indexOf("/purchaseorder/orderdetailhistory") != -1)
               || (curURL.indexOf("/pdf/purchaseorder") != -1)
               || (curURL.indexOf("/purchaseorder/suppliedcomponent") != -1)
               || (curURL.indexOf("/purchaseorder/millorderdetail") != -1)
               || (curURL.indexOf("/purchaseorder/purchaseorderhistorylist") != -1)){
           return "orderDetailForm~orderDetailForm1";
       }  
	  
       else if((curURL.indexOf("/planning/planningdetail") != -1)
               || (curURL.indexOf("/planning/planninghistorydetail") != -1)
               || (curURL.indexOf("/planning/millplanningdetail") != -1)
               || (curURL.indexOf("/planning/millplanninghistorydetail") != -1)
               || (curURL.indexOf("/planning/planningdetailsave") != -1)){
           return "orderDetailForm";
       }
       
       else if((curURL.indexOf("/admin/adduserinfo") != -1)
    		   || (curURL.indexOf("/admin/supplierpopup") != -1)
    		   || (curURL.indexOf("/admin/pubunitpopup") != -1)
    		   || (curURL.indexOf("/admin/insertuser") != -1)
    		   || (curURL.indexOf("/admin/deletesupplierfromuser") != -1)
    		   || (curURL.indexOf("/admin/deletepublisherfromuser") != -1)
    		   || (curURL.indexOf("/admin/edituser") != -1)
    		   || (curURL.indexOf("/admin/updateuser") != -1)
    		   || (curURL.indexOf("/admin/supplierpopupedit") != -1)
    		   || (curURL.indexOf("/admin/pubunitpopupedit") != -1)
    		   || (curURL.indexOf("/admin/deletesupplierfromuseredit") != -1)
    		   || (curURL.indexOf("/admin/deletepublisherfromuseredit") != -1)){
    	   return "userForm~supplierForm~addPubUnitForm";
       }
       
       else if((curURL.indexOf("/admin/listuser") != -1)){
    	   return "";
       }
       
       /* Planning */
         
       else if((curURL.indexOf("/planning/planninglist") != -1)
               || (curURL.indexOf("/planning/planningfilter") != -1)
               || (curURL.indexOf("/planning/planningpoAcceptMessage") != -1)
               || (curURL.indexOf("/home/planninglist") != -1)){
           return "planningForm";
       }
       
       else if((curURL.indexOf("/home/planninglist") != -1)
               || (curURL.indexOf("/home/purchaseorderlist") != -1)){
    	   
           return "homePageForm";
       }
       else if((curURL.indexOf("/orderStatus/orderStatusList") != -1)
    		   || (curURL.indexOf("/orderStatus/orderStatusDetail") != -1)
    		   || (curURL.indexOf("/orderStatus/orderStatusNew") != -1)
    		   || (curURL.indexOf("/orderStatus/orderStatusInsert") != -1)){
    	   return "orderStatusForm";
       }
       else if((curURL.indexOf("/usage/usageList") != -1)
    		   || (curURL.indexOf("/usage/usageDetail") != -1)
    		   || (curURL.indexOf("/usage/usagehistorylist") != -1)
    		   || (curURL.indexOf("/usage/usagehistorydetail") != -1)
    		   || (curURL.indexOf("/usage/newusage") != -1)
    		   || (curURL.indexOf("/usage/insertusage") != -1)){
    	   return "usageForm";
       }
      /* else if((curURL.indexOf("/deliverymessage/deliverymessagelist") != -1)
    		   || (curURL.indexOf("/deliverymessage/deliverymessagedetail") != -1)
    		   || (curURL.indexOf("/deliverymessage/deliverymessagenew") != -1)
    		   || (curURL.indexOf("/deliverymessage/milldeliverymessagenew") != -1)
    		   || (curURL.indexOf("/purchaseorder/downloadfile") != -1)
    		   || (curURL.indexOf("/deliverymessage/insertnewdeliverymessage") != -1)){
    	   return "deliveryMessageForm~fileUploadingForm";
       }*/
       else if((curURL.indexOf("/deliverymessage/deliverymessagelist") != -1)
    		   || (curURL.indexOf("/deliverymessage/deliverymessagedetail") != -1)
    		   || (curURL.indexOf("/deliverymessage/deliverymessagenew") != -1)
    		   || (curURL.indexOf("/deliverymessage/milldeliverymessagenew") != -1)
    		   //|| (curURL.indexOf("/purchaseorder/downloadfile") != -1)
    		   || (curURL.indexOf("/deliverymessage/insertnewdeliverymessage") != -1)){
    	   return "deliveryMessageForm";
       }
       else if((curURL.indexOf("/goodsreceipt/goodsreceiptlist") != -1)
    		   || (curURL.indexOf("/goodsreceipt/goodsreceiptdetial") != -1)
    		   || (curURL.indexOf("/goodsreceipt/deliverymessagedetail") != -1)
    		   || (curURL.indexOf("/goodsreceipt/goodsreceiptnew") != -1)
    		   || (curURL.indexOf("/goodsreceipt/millgoodsreceiptnew") != -1)
    		   || (curURL.indexOf("/goodsreceipt/insertnewgoodsreceipt") != -1)){
    	   return "goodsReceiptForm";
       }
       else if((curURL.indexOf("/bookspecification/bookspeclist") != -1)
    		   ||(curURL.indexOf("/bookspecification/bookspecdetail") != -1)
    		   || (curURL.indexOf("/bookspecification/bookspecsave") != -1)
    		   || (curURL.indexOf("/bookspecification/bookspecfilter") != -1)
    		   || (curURL.indexOf("/bookspecification/bookspechistorylist") != -1)
    		   || (curURL.indexOf("/bookspecification/bookspechistorydetail") != -1)){
    	   return "bookspecform";
       }
       else if((curURL.indexOf("/inventory/inventorysearching") != -1)
    		   || (curURL.indexOf("/inventory/inventorysave") != -1)){
    	   return "inventoryForm";
       }
       else if(curURL.indexOf("/inventory/inventorysearch") != -1)
       {
    	   return "";
       }
       else if((curURL.indexOf("/reports/reportsearch") != -1)
    		   || (curURL.indexOf("/reports/reportsearching") != -1)
    		   || (curURL.indexOf("/reports/reportgoodsreceiptdetail") != -1)
    		   || (curURL.indexOf("/reports/reportusagedetail") != -1)
    		   || (curURL.indexOf("/reports/reportinventorydetail") != -1)
    		   || (curURL.indexOf("/reports/reportorderstatusdetail") != -1)
    		   || (curURL.indexOf("/reports/reportplanningdetail") != -1)
    		   || (curURL.indexOf("/reports/reportdeliverymessagedetail") != -1)
    		   || (curURL.indexOf("/reports/reportpurchaseorderdetail") != -1)
    		   || (curURL.indexOf("/reports/reportbookspecdetail") != -1)){
    	   return "reportForm";
       }
       else
       {
    	   return null;
       }
       /*
       if((curURL.indexOf("schedule/start/details") != -1)
                || (curURL.indexOf("schedule/start/resource") != -1)
                || (curURL.indexOf("schedule/start/template") != -1)){
            return "ScheduleDetailsForm~ScheduleResourceForm~scheduleTemplateForm";
       }
       */
       
       /*
       if(curURL.indexOf("editrole/") != -1){
           return "editRoleForm";
       }
       */
   
   }
}
