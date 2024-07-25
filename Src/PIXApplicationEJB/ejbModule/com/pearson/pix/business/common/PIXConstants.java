package com.pearson.pix.business.common;


public interface PIXConstants {

	
	public static final String PIX_LOG_FILE_PATH = "PIX_LOG_FILE_PATH";
    public static final String PIX_ERROR = "PIX_ERROR";
    /**
     * properties file name
     */
    
    public static final String ENVPROPS = "PIXENV" ;
   
    /* Login constants  */
    
    public static final String USER_INFO =  "USER_INFO";
    /*  Planning Constants */
    
    public static final String PIX_PLANNING =  "PIX_PLANNING";
    public static final String PIX_BOOK_SPEC =  "PIX_BOOK_SPEC";
    public static final String ISBN_NO =  "ISBN10";
    public static final String PRINT_NUMBER ="PRINT_NO";
    public static final String QUANTITY ="TOTAL_QUANTITY";
    public static final String BOOK_BOUND_DATE ="BBD";
    public static final String STATUS = "STATUS_DESCRIPTION";
    public static final String PLAN_POSTED_DATE = "ISSUE_DATE";
    public static final String PLAN_POSTED_BY = "POSTED_BY";
    public static final String PIX_ADMIN_ARTY =  "PIX_ADMIN_ARTY";
    public static final String ACKNOWLEDGE_FLAG =  "ACKNOWLEDGE_FLAG";
    public static final String MOD_DATE_TIME =  "MOD_DATE_TIME";
    public static final String TITLE_DESC =  "TITLE_DESC";
    
    
    /*Added by Arvind for ARP Title Setup*/
    public static final String WQSTATES_ARP =  "WQSTATES_ARP";
    public static final String O_RET_CODE ="O_RET_CODE";
    public static final String O_RET_MSG ="O_RET_MSG";
    public static final String ARP_MODULE_CODE ="ARP";
    
    //Added by Sujeet Kumar tracker 330943-- 30 Nov 2009
    public static final String TRANSACTION_TYPE = "TRANSACTION_TYPE";
    public static final String TRANSACTION_DATE = "TRANSACTION_DATE";
    public static final String VENDOR_DATE =  "VENDOR_DATE";
    public static final String PRESS_DATE =  "PRESS_DATE";
    public static final String ARRIVAL_DATE =  "ARRIVAL_DATE";
    public static final String TRANSACTION_ID =  "TRANSACTION_ID";
    //End of Tracker - 330943

    

    /*  Delivery Message List Constants */
      
       public static final String DELIVERY_MESSAGE_NO =  "MSG_NO";
       public static final String CREATION_DATE_TIME ="CREATION_DATE_TIME";
       public static final String CREATED_BY ="CREATED_BY";
       public static final String MESSAGE_TYPE ="DESCRIPTION";
       
       public static final int PAGE_SIZE = 10;
       public static final String PAGING_RECORDS = "PAGING_RECORDS";
       public static final String MSG_ID = "MSG_ID";
       public static final String PAGE_VALUE = "PAGE_VALUE";
      
      
      // Delivery Message Detail Constants
       
       public static final String LINE_NO = "LINE_NO";
       public static final String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
       public static final String DELIVERED_QUANTITY = "DELIVERED_QTY";
       public static final String ORIGINAL_QTY = "ORIGINAL_QTY";
       public static final String REQUESTED_DELIVERY_DATE = "REQUESTED_DELIVERY_DATE";
       public static final String ESTIMATED_DELIVERY_DATE ="ESTIMATED_DELIVERY_DATE";
       public static final String LINE_ITEM_NO ="LINE_ITEM_NO";
       public static final String INVENTORY_ID ="INVENTORY_ID";
       public static final String INVENTORY_VERSION ="INVENTORY_VERSION";
       public static final String PO_NO = "PO_NO";
       public static final String RELEASE_NO = "RELEASE_NO";
       public static final String MSG_NO = "MSG_NO";
       public static final String DESCRIPTION = "DESCRIPTION";
       public static final String DELIVERY_DESTINATION = "NAME_1";
       public static final String ADDRESS_1 = "ADDRESS_1";
       public static final String CITY ="CITY";
       public static final String POSTAL_CODE ="POSTAL_CODE";
       public static final String STATE = "STATE";
       public static final String COUNTRY_CODE = "COUNTRY_CODE";
       public static final String CONTACT_FIRST_NAME = "CONTACT_FIRST_NAME";
       public static final String PHONE = "PHONE";
       public static final String PO_ID = "PO_ID";
       public static final String PO_VERSION = "PO_VERSION";
       public static final String MOBILE = "MOBILE";
       public static final String FAX = "FAX";
       public static final String EMAIL = "EMAIL";
       public static final String SAN = "SAN";
       public static final String SAP_PLANT_CODE = "SAP_PLANT_CODE";      
       
       /*Added by Arvind for ARP Title */
       
       public static final String EVENT_ID="TRANSACTION_HISTORY_NO";
       public static final String TITLE_ID="TITLE_ID";
       public static final String TITLE_ISBN="ISBN_10";
       public static final String PRINTING_NO="PRINTING_NO";
       public static final String COPYRIGHT_YEAR="COPYRIGHT_YEAR";
       public static final String BUYER_ID="BUYER_ID";
       public static final String BUYER_NAME="BUYER_NAME";
       public static final String POSTED_DATE_TIME="POSTED_DATE_TIME";
       public static final String INVENT_DUE_DATE="ARP_EXPCTED_DATE";
       public static final String INVENT_REQUEST_DATE="INVENTORY_REQUSTED_DATE";
       public static final String PAGE_COUNT="PAGE_COUNT";
       public static final String TITLE_DESCRIPTION="TITLE_DESCRIPTION";
       public static final String AUTHOR="AUTHOR";
       public static final String EDITION="EDITION";
       public static final String TITLE_STATUS="TITLE_STATUS";
       public static final String UNIT_COST="UNIT_COST";
       public static final String VENDOR_PAGE_COUNT="VENDOR_PAGE_COUNT";
       public static final String BUYER_PAGE_COUNT="BUYER_PAGE_COUNT";
       public static final String REVIEW_COPY_REQ="REVIEW_COPY_REQ";  
       public static final String REVIEW_COPY_PROVIDE="REVIEW_COPY_PROVIDE";
       public static final String REVIEW_COPY_TYPE="REVIEW_COPY_TYPE_DESCRIPTION";
       public static final String REVIEW_FLAG="REVIEW_FLAG";
       public static final String VENDOR_COMMENTS = "VENDOR_COMMENTS";
       public static final String BUYER_COMMENTS = "BUYER_COMMENTS";
       public static final String STATUS_CODE = "STATUS_CODE";  
       public static final String SPEC_NO = "SPEC_NO";
       public static final String MODULE = "module";
       public static final String PROOF_REQUESTED="PROOF_REQUESTED";
       public static final String PROOF_PROVIDED="PROOF_PROVIDED";
       public static final String PROOF_TYPE="PROOF_TYPE";  
              
      //Constants for Good's Receipt
       
       public static final String RECEIPT_NO = "RECEIPT_NO";
       public static final String ACTUAL_ARRIVAL_DATE = "ACTUAL_ARRIVAL_DATE";
       public static final String COMP_VENDOR = "COMP_VENDOR";
       public static final String SHIP_TO_VENDOR = "SHIP_TO_VENDOR";
       public static final String RECEIPT_ID = "RECEIPT_ID";
       public static final String PARTY_TYPE = "PARTY_TYPE";
       public static final String REF_ID = "REF_ID";
       public static final String PO_LINE_NO = "PO_LINE_NO";
       public static final String MSG_LINE_NO = "MSG_LINE_NO";
       public static final String RECEIPT_LINE_NO = "RECEIPT_LINE_NO";
       
       // Constants for Good's Receipt Line
       
       public static final String RECEIVED_QUANTITY = "RECEIVED_QUANTITY";
       public static final String INTRANSIT_DAMAGED_QTY = "INTRANSIT_DAMAGED_QTY";
       public static final String UOM_ID = "UOM_ID";
       public static final String ACCEPTANCE_DESCRIPTION = "DESCRIPTION";
       public static final String HEADER_ACCEPTANCE_DESCRIPTION = "DESCRIPTION";
       
    //Constants for Purchase Order
    public static final String PIX_PO_HEADER ="PIX_PO_HEADER";
    public static final String PIX_PO_LINE ="PIX_PO_LINE";
    public static final String PIX_PO_PARTY ="PIX_PO_PARTY";
    public static final String STATUS_PARTY_NEW = "NEW";
    public static final String STATUS_PARTY_AMENDED = "AMENDED";
    public static final String STATUS_PARTY_CANCELLED = "CANCELLED";
    public static final String STATUS_PARTY_NOACTION = "NOACTION";
    public static final String STATUS_PARTY_ORDERDELIVERED = "ORDDELIVER";
    public static final String STATUS_PARTY_ACCEPTED = "ACCEPTED";
    public static final String STATUS_PARTY_REJECTED = "REJECTED";
    public static final String STATUS_PARTY_PENDING = "PENDING";
    public static final String STATUS_LINE_NEW = "NEW";
    public static final String STATUS_LINE_AMENDED = "AMENDED";
    public static final String STATUS_LINE_CANCELLED = "CANCELLED";
    public static final String STATUS_LINE_NOACTION = "NOACTION";
    public static final String STATUS_LINE_ORDERDELIVERED = "ORDDELIVER";
    public static final String STATUS_LINE_ACCEPTED = "ACCEPTED";
    public static final String STATUS_LINE_REJECTED = "REJECTED";
    public static final String STATUS_LINE_PENDING = "PENDING";
    public static final String STATUS_HEADER_ORIGINAL = "ORIGINAL";
    public static final String STATUS_HEADER_AMENDED = "AMENDED";
    public static final String STATUS_HEADER_CANCELLED = "CANCELLED";
    public static final String STATUS_HEADER_ORDERDELIVERED = "ORDDELIVER";
    public static final String STATUS_HEADER_ACCEPTED = "ACCEPTED";
    public static final String STATUS_HEADER_REJECTED = "REJECTED";
    public static final String STATUS_HEADER_NOACTION = "NOACTION";
    public static final String POSTED_DATE = "POSTED_DATE";
    public static final String POID = "PO_ID";
    public static final String POVERSION = "PO_VERSION";
    public static final String VENDOR_CONTACT = "VENDOR_CONTACT";
    public static final String PO_LINE_COUNT = "PO_LINE_COUNT";
    public static final String ORDER_TYPE = "ORDER_TYPE";
    
//Constants for HomePage
    public static final String MODULE_ID="MODULE_ID";
    public static final String SUM="SUM";
    public static final String DESC="DESCRIPTION";
    public static final String STATUS_ID="STATUS_ID";
    public static final String DISPLAY_ORDER="DISPLAY_ORDER";
    public static final String STATUS_DESCRIPTION="STATUS_DESCRIPTION";
    
    
//  Constants for OrderStatus
    public static final String ORDER_STATUS_ID="ORDER_STATUS_ID";
    public static final String ORDER_STATUS_NO="ORDER_STATUS_NO";
    public static final String STATUS_NO="STATUS_NO";
    public static final String TIMELINE="TIMELINE";
    public static final String TIMELINE_DATE="TIMELINE_DATE";
    public static final String TIMELINE_ID="TIMELINE_ID";
    public static final String COMMENTS="COMMENTS";
    
//  Constants for Usage    
    public static final String USAGE_ID="USAGE_ID";
    public static final String USAGE_NO="USAGE_NO";
    public static final String PRODUCT_CODE="PRODUCT_CODE";
    public static final String PARENT_PRODUCT_CODE="PARENT_PRODUCT_CODE";
    public static final String PARENT_PRODUCT_DESC="PARENT_PRODUCT_DESC";
    public static final String USAGE_QUANTITY="USAGE_QUANTITY";
    public static final String DAMAGED_QUANTITY="DAMAGED_QUANTITY";
    
    
    
    //constants for property files made for customer feedback
    
    //Constants for property files made for admin 
    public static final String PUBLISHER_SAN ="SAN";
    public static final String PUBLISHER_NAME ="NAME_1";
    public static final String PUBLISHER_STATUS ="ACTIVE_FLAG";
    public static final String PUBLISHING_UNIT_PARTY_TYPE ="B";
	public static final String SUPPLIER_PARTY_TYPE ="V";
	public static final String COUNTRY_LIST ="COUNTRY_LIST";
	public static final String ACCESS_METHOD ="ACCESS_METHOD";
	
	public static final String LOGIN ="LOGIN";
	public static final String FIRST_NAME ="FIRST_NAME";
	public static final String LAST_NAME ="LAST_NAME";
	public static final String ROLE_TYPE ="ROLE_TYPE";
	public static final String ACTIVE_FLAG ="ACTIVE_FLAG";
	public static final String USER_ID ="USER_ID";
	
	public static final String ROLE_TYPE_LIST ="ROLE_TYPE_LIST";
	public static final String ROLE_READ_WRITE ="B";
	public static final String ROLE_READ_WRITE_DENIED ="N";
	public static final String ROLE_READ ="R";
	public static final String ROLE_WRITE ="W";
	
	public static final String ROLE_ACCESS ="Y";
	public static final String ROLE_DENIED ="N";
	
	public static final String PUB_EDIT_CANCEL ="PubEditCancel";
	public static final String SUPPLIER_EDIT_CANCEL ="SupplierEditCancel";
	public static final String USER_EDIT_CANCEL ="UserEditCancel";
	
	public static final String MODULE_PUBLISHER ="PUB";
	public static final String MODULE_SUPPLIER ="SUP";
	public static final String MODULE_USER ="USER";
	
	public static final String ADMIN_MODULE ="ADMIN_MODULE";
	
	public static final String MODIFIED_BY ="MODIFIED_BY";

    //Constants for property files made for BookSpec 
    public static final String BOOK_SPEC_NO ="SPEC_NO";
    public static final String ISBN_NUM ="ISBN10";
    public static final String PRINT_NUM ="PRINT_NO";
    public static final String SPECIFICATION_DATE ="SPEC_ISSUE_DATE";
    public static final String STATUS_DESC ="STATUS_DESCRIPTION";
    public static final String NAME_1="NAME_1";
    public static final String NAME_2="NAME_2";
    public static final String SPEC_ID="SPEC_ID";
    public static final String SPEC_VERSION="SPEC_VERSION";
    public static final String ACK_FLAG="ACK_FLAG";
   
    //  Constants for property files made for read write access
    public static final String WRITE="WRITE";
    public static final String READ="READ";
    
    
    //Constants for property files made for Report
    public static final String ITEM_TYPE="ITEM_TYPE";
    public static final String ITEM_NUMBER="ITEM_NO";
    public static final String VERSION_NUMBER="VERSION_NO";
    public static final String POSTED_BY="POSTED_BY";
    public static final String VENDOR_NAME="VENDOR";
    public static final String ID="ID";
    public static final String REF_CODE="REF_CODE";
    public static final String PIX_REF="PIX_REF";
    public static final String ISBN_NUMBER ="ISBN";
    
    
    public static final String SUCCESS_STRING="SUCCESS_STRING";
    public static final String OK_URL="OK_URL";
    
    public static final String READ_RIGHT="R";
    public static final String WRITE_RIGHT="W";
    public static final String BOTH_RIGHT="B";
    public static final String NONE_RIGHT="N";
    
    public static final String PO_CODE="POR";
    public static final String BOOKSPEC_CODE="BSP";
    public static final String PLANNING_CODE="PLA";
    public static final String ORDERSTATUS_CODE="OST";
    public static final String DELMSG_CODE="DME";
    public static final String GOODSREC_CODE="GRE";
    public static final String USAGE_CODE="USG";
    public static final String ADMIN_CODE="ADM";
    public static final String INVENTORY_CODE="INV";
    public static final String ORDERCONFIRM_CODE="OCO";
    public static final String REPORTS_CODE="REP";
    public static final String DROPSHIP_CODE="DS";
    
    public static final String ADMIN_ROLE="A";
    public static final String BUYER_ROLE="B";
    public static final String VENDOR_ROLE="V";
    public static final String SHIPTO_ROLE="S";
    public static final String MILL_ROLE ="M" ;
    public static final String COST_ACCOUNTING_ROLE ="C" ;
    
    public static final String PO_HEADER="PO_HEADER";
    public static final String PO_ALL_HEADER_STATUS="PO_ALL_HEADER_STATUS";
    public static final String PO_ALL_LINE_STATUS="PO_ALL_LINE_STATUS";
    public static final String PO_PRICE_DETAILS="PO_PRICE_DETAILS";
    
    public static final String Acknowledged="Acknowledged";
    
    public static final String BASE_COMMAND="com.pearson.pix.presentation.base.command.BaseCommand";
    
    public static final String MATERIAL_NO="MATERIAL_NO";
    public static final String PAPER_BW_GRD="PAPER_BW_GRD";
    public static final String ORDER_DELIVERY_DATE = "ORDER_DELIVERY_DATE" ;
    public static final String LINE_DETAIL = "LINE_DETAIL" ;
    public static final String SHIPTO = "PARTY" ;
    public static final String SUPPLIER_TYPES = "SUPPLIER_TYPES" ;
    
    //Constants for Cost Accounting
    public static final String DELIVERED_QTY = "DELIVERED_QTY" ;
    public static final String REQUESTED_QTY = "REQUESTED_QTY" ;
    public static final String RECEIVED_QTY = "RECEIVED_QTY" ;
    public static final String OWNED_QTY = "OWNED_QTY" ;
    public static final String TO_BE_OWNED_QTY = "TO_BE_OWNED_QTY" ;
    public static final String OWNING_QTY = "OWNING_QTY" ;
    public static final String OWNERSHIP_MODE = "OWNERSHIP_MODE" ;
    public static final String MILL_NAME = "MILL_NAME" ;
    public static final String PRINTER_NAME = "PRINTER_NAME" ;
    public static final String MILL_ASSOCIATED_TO_USER = "MILL_ASSOCIATED_TO_USER" ;
    public static final String UPDATE_QUANTITY = "UPDATE_QUANTITY" ;
    public static final String APPROVAL_FLAG = "APPROVAL_FLAG" ;
    public static final String FILE_EXISTS = "FILE_EXISTS" ;
    
    //  Constants for Mismatch Report Excel Sheet
    
    public static final String PO_EXCEL = "PO#";
    public static final String MERCHANT_EXCEL = "MILL/MERCHANT";
    public static final String MATERIAL_EXCEL = "MATERIAL NO";
    public static final String QUANTITY_EXCEL = "QUANTITY";
    public static final String LINE_NO_EXCEL = "PO LINE#";
    public static final String PLANT_EXCEL = "PLANT";
    public static final String ORDERED_QTY_EXCEL = "ORDERED QTY";
    public static final String DM_QTY_EXCEL = " DELV.QTY";
    public static final String DM_DOC_EXCEL = "DELV.DOC NUMBER";
    public static final String GR_QTY_EXCEL = "GR QTY";
    public static final String GR_DOC_NO_EXCEL = "GR DOC.NUMBER";
    
    
    public static final String QTY_VARIANCE_EXCEL = "QTY VARIANCE";
    
    public static final String MAX_DM_QTY = "MAX_DM_QTY";
    public static final String MAX_GR_QTY = "MAX_GR_QTY";
    
    //Constants for RFS
    public static final String PRINTER = "PRINTER";
    public static final String MATERIAL_DESC = "L4_MATERIAL_DESC";
    public static final String DM_POSTED = "DM_POSTED";
    public static final String DM_TO_BE_POSTED = "DM_TO_BE_POSTED";
    public static final String COLOUR_FLAG = "COLOUR_FLAG";
    public static final String BUYER_REQUESTED_QTY = "BUYER_REQUESTED_QTY" ;
    //Anshu
    public static final String CUMULATIVE_RECEIVED_QUANTITY = "CUMULATIVE_RECEIVED_QUANTITY" ;  
    public static final String DM_QTY = "DM_QTY"; 
    public static final String L4_MATERIAL_DESC = "L4_MATERIAL_DESC";
    public static final String DELIVERED_DATE="DELIVERED_DATE";
    public static final String TOTAL_DELIVERED_QTY="TOTAL_DELIVERED_QTY";
    public static final String TOTAL_RECEIVED_QUANTITY="TOTAL_RECEIVED_QUANTITY";
    public static final String DELIVERED_BY="DELIVERED_BY";
    public static final String RECEIVED_BY="RECEIVED_BY";
    public static final String DEFAULT_QTY="DEF_QTY";
    public static final String GR_TO_BE_POSTED="TO_BE_POSTED";
    public static final String L4_MATERIAL_TOOLTIP = "L4_MATERIAL_TOOL_TIP";
    public static final String REC_DATE="REC_DATE";
}
