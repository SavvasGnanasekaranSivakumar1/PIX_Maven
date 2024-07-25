package com.pearson.pix.presentation.goodsreceipt.delegate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import com.pearson.pix.dto.goodsreceipt.GoodsReceipt;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocal;
import com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocalHome;
import com.pearson.pix.presentation.base.common.ServiceLocator;

/**
 * Business Delegate for Goods Receipt. It acts as the Client for EJB invocation.
 */
public class GoodsReceiptDelegate 
{
   
   /**
    * Constructor to initialize GoodsReceiptDelegate
    */
   public GoodsReceiptDelegate() 
   {
       
   }
   
   /**
    * Calls corresponding Business method of Goods Receipt EJB for the purpose of 
    * Goods Receipt List. This method also takes care of filtering, pagination by 
    * passing page no, filter criteria and its value respectively.
    * 
    * @return java.util.Collection 
    */
   public Collection displayGoodsReceiptList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId) throws AppException 
   {
	   Collection objCollection = null;
	   objCollection = getGoodsReceiptLocal().displayGoodsReceiptList(poHeaderTemp,pagination,objorderby,objsort,userId); 
	   return objCollection;
   }
   
   /**    
    * Calls corresponding Business method of Paper Goods Receipt EJB for the purpose of 
    * Goods Receipt List. This method also takes care of filtering, pagination by 
    * passing page no, filter criteria and its value respectively.
    * @author anshu.bhardwaj
    * 
    * @return java.util.Collection 
    */
   public Collection displayNewPaperGoodsReceipt(POHeader poHeaderTemp,int pagination,Integer userId, String ponumber) throws AppException 
   {
	   Collection objCollection = null;
	   objCollection = getGoodsReceiptLocal().displayNewPaperGoodsReceipt(poHeaderTemp,pagination,userId, ponumber); 
	   return objCollection;
   }
   
	// roleTracking
	public int getRoleTrackingFlag(String login_Id) throws AppException
	{
		int roleTrackingFlag = getGoodsReceiptLocal().getRoleTrackingFlag(login_Id);
		return roleTrackingFlag;
	}

   
   
  /**    
    * Calls corresponding Business method of Paper Goods Receipt EJB for the purpose of 
    * Goods Receipt History. This method also takes care of filtering, pagination by 
    * passing page no, filter criteria and its value respectively.
    * @author anshu.bhardwaj
    * 
    * @return java.util.Collection 
    */
   public Collection displayHistoryPaperGoodsReceipt(POHeader poHeaderTemp,int pagination,Integer userId,String msgId,String actionKey) throws AppException 
   {
	   Collection objCollection = null;
	   objCollection = getGoodsReceiptLocal().displayHistoryPaperGoodsReceipt(poHeaderTemp,pagination,userId,msgId,actionKey); 
	   return objCollection;
   }
   /**
    * Calls corresponding Business method of Goods Receipt EJB for the purpose of 
    * Goods Receipt Detail. 
    * 
    * @param poNumber
    * @param receiptNumber
    * @param orderPaper //in case of Paper FO
    * @param roleType //in case mill user is logged in
    * @return com.pearson.pix.dto.goodsreceipt.GoodsReceipt
    */
   public GoodsReceipt displayGoodsReceiptDetail(String poNumber,Integer receiptId,String orderPaper,String roleType) throws AppException
   {
	   GoodsReceipt objGoodsReceipt = null;
	   objGoodsReceipt = getGoodsReceiptLocal().displayGoodsReceiptDetail(poNumber,receiptId,orderPaper,roleType);
	   return objGoodsReceipt;
   }
   
   /**
    * Calls corresponding Business method of Goods Receipt EJB for the purpose of 
    * Basic Information for Goods Receipt submit.
    * @param poNumber
    * @param paperFo
    * @param Integer userId
    * @param String msgId for paper FO
    * @return java.util.HashMap
    */
   public HashMap displayBasicGoodsReceiptInfo(POHeader poHeaderTemp,String objUser,GoodsReceipt objGoodsReceipt,String paperFo,Integer userId,String msgId,String msgLineNo) throws AppException
   {
	   HashMap objHashMap = null;
	   objHashMap = getGoodsReceiptLocal().displayBasicGoodsReceiptInfo(poHeaderTemp,objUser,objGoodsReceipt,paperFo,userId,msgId,msgLineNo);
       return objHashMap;
   }
   
   /**
    * Calls corresponding Business method of Goods Receipt EJB for the purpose of 
    * saving information of Goods Receipt.
    * 
    * @param objGoodsReceipt
    */
   public String saveGoodsReceipt(GoodsReceipt objGoodsReceipt,POHeader poHeaderTemp,int size,String objUser,String msgId,String paperFo) throws AppException
   {
	   
	   return getGoodsReceiptLocal().saveGoodsReceipt(objGoodsReceipt,poHeaderTemp,size,objUser,msgId,paperFo);
    
   }
   
   /**
    * Calls corresponding Business method of Goods Receipt EJB for the purpose of 
    * saving information of Goods Receipt.
    * 
    * @param objGoodsReceipt
    * @author anshu.bhardwaj
    */
   public String saveGoodsReceiptHistory(Collection goodsReceiptVector,POHeader poHeaderTemp,String objUser) throws AppException
   {
	   
	   return getGoodsReceiptLocal().saveGoodsReceiptHistory(goodsReceiptVector,poHeaderTemp,objUser);
    
   }
   
   public Vector getGoodsReceiptRollInfo(String poNumber,String productCode, String DMGRMode) throws AppException
   {
	   return getGoodsReceiptLocal().getGoodsReceiptRollInfo(poNumber,productCode, DMGRMode);  
   }
   
   public int printerRtDmNrtHist(String msgId, String DMGRMode) throws AppException
   {
	   return getGoodsReceiptLocal().printerRtDmNrtHist(msgId, DMGRMode);  
   }
   
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * 
    * @return com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocal
    */
   private GoodsReceiptLocal getGoodsReceiptLocal() throws AppException
   {
	   GoodsReceiptLocal objGoodsReceiptLocal = null;
	   try
	    {
			GoodsReceiptLocalHome objGoodsReceiptLocalHome = ServiceLocator.getGoodsReceiptLocalHome();
			objGoodsReceiptLocal = objGoodsReceiptLocalHome.create();
		} 
	   catch(NamingException ne)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.NAMING_EXCEPTION,"GoodsReceiptDelegate,getGoodsReceiptLocal",ae);
		   throw ae;
	   }
	   catch(CreateException ce)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.CREATE_EXCEPTION,"GoodsReceiptDelegate,getGoodsReceiptLocal",ae);
		   throw ae;
	   }
    return objGoodsReceiptLocal;
   }
}
