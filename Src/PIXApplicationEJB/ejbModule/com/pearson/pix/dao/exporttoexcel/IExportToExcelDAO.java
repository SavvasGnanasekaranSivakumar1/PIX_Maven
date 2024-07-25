package com.pearson.pix.dao.exporttoexcel;

import com.pearson.pix.dto.exporttoexcel.ExportToExcelDTO;
import com.pearson.pix.exception.AppException;

/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 */
public interface IExportToExcelDAO 
{
	/**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getReportDetail(ExportToExcelDTO objDTO)throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getDetail(ExportToExcelDTO objDTO)throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getMillDetail(ExportToExcelDTO objDTO)throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getPurchaseDetail(ExportToExcelDTO objDTO)throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getPurchaseMillDetail(ExportToExcelDTO objDTO)throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getGoodReceiptDetail(ExportToExcelDTO objDTO)throws AppException;
    
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     * @author anshu.bhardwaj
     */
    
    public ExportToExcelDTO getGoodReceiptPaperHistory(ExportToExcelDTO objDTO)throws AppException;    
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     * @author anshu.bhardwaj
     */
    
    public ExportToExcelDTO getGoodReceiptPaperNew(ExportToExcelDTO objDTO)throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getDeliveryDetail(ExportToExcelDTO objDTO)throws AppException;
    
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getBookSpecDetail(ExportToExcelDTO objDTO)throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the Report details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getOrderStatusDetail(ExportToExcelDTO objDTO)throws AppException;
    
    /**
     * This method returns ExportToExcelDTO for displaying the Delivery Message Approval details onto Excel sheet.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     */
    
    public ExportToExcelDTO getDeliveryMessageApprovalDetail(ExportToExcelDTO objDTO)throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the Outstanding Delivery Report.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     * @author anshu bhardwaj
     */
    public ExportToExcelDTO getOutstandingDetail(ExportToExcelDTO objDTO) throws AppException;    
    /**
     * This method returns ExportToExcelDTO for displaying the New DM Screen.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     * @author anshu bhardwaj
     */
    public ExportToExcelDTO getDeliveryMessageNewMill(ExportToExcelDTO objDTO) throws AppException;
    /**
     * This method returns ExportToExcelDTO for displaying the DM History Screen.
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @throws Exception in case of error.
     * @author anshu bhardwaj
     */
    public ExportToExcelDTO getDeliveryMessageHistory(ExportToExcelDTO objDTO) throws AppException;
}
