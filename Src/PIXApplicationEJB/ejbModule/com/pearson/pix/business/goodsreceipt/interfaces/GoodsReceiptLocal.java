package com.pearson.pix.business.goodsreceipt.interfaces;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;

import com.pearson.pix.dto.goodsreceipt.GoodsReceipt;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;

import javax.ejb.*;

/**
 * EJB Home interface for Goods Receipt
 */
public interface GoodsReceiptLocal extends EJBLocalObject
{
    
    /**
     * Business method for displaying the Goods Receipt List
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayGoodsReceiptList
     */
    public Collection displayGoodsReceiptList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId) throws AppException;
    
    /**
     * Business method for displaying the Goods Receipt Detail
     * 
     * @param poNumber
     * @param receiptNumber
     * @param orderPaper //in case of Paper FO
    * @param roleType //in case mill user is logged in
     * @return com.pearson.pix.dto.goodsreceipt.GoodsReceipt
     * @J2EE_METHOD  --  displayGoodsReceiptDetail
     * @author anshu.bhardwaj
     */
    public Collection displayNewPaperGoodsReceipt(POHeader poHeaderTemp,int pagination,Integer userId, String ponumber) throws AppException;
    
    /**
     * Business method for displaying the Goods Receipt History
     * 
     * @param poNumber
     * @param receiptNumber
     * @param orderPaper //in case of Paper FO
    * @param roleType //in case mill user is logged in
     * @return com.pearson.pix.dto.goodsreceipt.GoodsReceipt
     * @J2EE_METHOD  --  displayGoodsReceiptDetail
     * @author anshu.bhardwaj
     */
    public Collection displayHistoryPaperGoodsReceipt(POHeader poHeaderTemp,int pagination,Integer userId,String msgId,String actionKey) throws AppException;

    
    
    /**
     * Business method for displaying the Goods Receipt Detail
     * 
     * @param poNumber
     * @param receiptNumber
     * @param orderPaper //in case of Paper FO
    * @param roleType //in case mill user is logged in
     * @return com.pearson.pix.dto.goodsreceipt.GoodsReceipt
     * @J2EE_METHOD  --  displayGoodsReceiptDetail
     */
    public GoodsReceipt displayGoodsReceiptDetail(String poNumber, Integer receiptNumber,String orderPaper,String roleType) throws AppException;
    
    /**
     * Business method for displaying the Basic Information for Goods Receipt 
     * submit.
     * 
     * @param poHeaderTemp
     * @param paperFo
     * @param userID
     * @param String msgId for paper FO
     * @return java.util.HashMap
     * @J2EE_METHOD  --  displayBasicGoodsReceiptInfo
     */
    public java.util.HashMap displayBasicGoodsReceiptInfo(POHeader poHeaderTemp,String objUser,GoodsReceipt objGoodsReceipt,String paperFo,Integer userId,String msgId,String msgLineNo) throws AppException ;
    
    /**
     * Business method for saving the information for Goods Receipt.
     * @param objGoodsReceipt
     * @J2EE_METHOD  --  saveGoodsReceipt
     */
    public String saveGoodsReceipt(GoodsReceipt objGoodsReceipt,POHeader poHeaderTemp,int size,String objUser,String msgId,String paperFo) throws AppException ;
    
    /**
     * Business method for saving the information for Goods Receipt HIstory.
     * @param objGoodsReceipt
     * @J2EE_METHOD  --  saveGoodsReceipt
     * @author anshu.bhardwaj
     */
    public String saveGoodsReceiptHistory(Collection goodsReceiptVector,POHeader poHeaderTemp,String objUser) throws AppException ;
    
    /**
     * Business method for saving the information for Goods Receipt HIstory.
     * @param objGoodsReceipt
     * @J2EE_METHOD  --  saveGoodsReceipt
     * @author anshu.bhardwaj
     */
    Vector getGoodsReceiptRollInfo(String poNumber,String productCode, String DMGRMode) throws AppException ;
    
    int printerRtDmNrtHist(String msgId, String DMGRMode) throws AppException ;
    
    int getRoleTrackingFlag(String login_Id) throws AppException;
}