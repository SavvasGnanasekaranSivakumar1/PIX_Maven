package com.pearson.pix.dao.goodsreceipt;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import com.pearson.pix.dto.goodsreceipt.GoodsReceipt;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;

/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 */
public interface GoodsReceiptDAO 
{
   
   /**
    * Gets the Goods Receipt List of PO from the database
    * @return java.util.Collection
    */
   public Collection getGoodsReceiptList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId) throws AppException;
   
   /**
    * Gets the Goods Receipt List of PO from the database
    * @return java.util.Collection
    * @author anshu.bhardwaj
    */
   public Collection getNewPaperGoodsReceipt(POHeader poHeaderTemp,int pagination,Integer userId, String ponumber) throws AppException;

   /**
    * Gets the Goods Receipt List of PO from the database
    * @return java.util.Collection
    * @author anshu.bhardwaj
    */
   public Collection getPaperGoodsHistory(POHeader poHeaderTemp,int pagination,Integer userId,String msgId,String actionKey) throws AppException;
    
   /**
    * Gets the Goods Receipt Detail for a goods receipt number of PO from the database
    * 
    * @param poNumber
    * @param receiptNumber
    * @param orderPaper //in case of Paper FO
    * @param roleType //in case mill user is logged in
    * @return com.pearson.pix.dto.goodsreceipt.GoodsReceipt
    */
   public GoodsReceipt getGoodsReceiptDetail(String poNumber,Integer receiptNumber,String orderPaper,String roleType) throws AppException;
   
   
   /**
    * Gets the Basic Goods Receipt information from the database
    * 
    * @param POHeader poHeaderTemp
    * @param String objUser
    * @param GoodsReceipt objGoodsReceipt
    * @param Integer userId
    * @param String msgId for paper FO
    * 
    * @return java.util.HashMap
    */
   public HashMap getBasicGoodsReceiptPaperInfo(POHeader poHeaderTemp,String objUser,GoodsReceipt objGoodsReceipt,Integer userId,String msgId,String msgLineNo) throws AppException;
   
   /**
    * Gets the Basic Goods Receipt information from the database
    * 
    * @param poNumber
    * @return java.util.HashMap
    */
   public HashMap getBasicGoodsReceiptInfo(POHeader poHeaderTemp,String objUser,GoodsReceipt objGoodsReceipt) throws AppException;
   
   /**
    * Saves the Goods Receipt information into the database
    * @param objGoodsReceipt
    */
   public String insertGoodsReceipt(GoodsReceipt objGoodsReceipt,POHeader poHeaderTemp,int size,String objUser) throws AppException;
   
   /**
    * Saves the Goods Receipt information into the database for Paper fo
    * @param objGoodsReceipt
    */
   public String insertGoodsReceiptPaperFo(GoodsReceipt objGoodsReceipt,POHeader poHeaderTemp,int size,String objUser,String msgId) throws AppException;
   
   /**
    * Saves the Goods Receipt History information into the database
    * @param objGoodsReceipt
    */
   public String insertGoodsReceiptHistory(Collection goodsReceiptVector,POHeader poHeaderTemp,String objUser) throws AppException;
   /**
    * Saves the Goods Receipt History information into the database
    * @param objGoodsReceipt
    */
   public Vector getGoodsReceiptRollInfo(String poNumber,String productCode, String DMGRMode) throws AppException;
   
   public int printerRtDmNrtHist(String msgId, String DMGRMode) throws AppException;
   
   // roletracking
   int getRoleTrackingFlag(String login_Id) throws AppException;
}
