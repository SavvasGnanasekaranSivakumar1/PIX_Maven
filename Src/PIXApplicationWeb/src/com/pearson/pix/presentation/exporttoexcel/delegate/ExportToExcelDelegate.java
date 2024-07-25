package com.pearson.pix.presentation.exporttoexcel.delegate;

import com.pearson.pix.business.exporttoexcel.interfaces.ExportToExcelLocal;
import com.pearson.pix.business.exporttoexcel.interfaces.ExportToExcelLocalHome;
import com.pearson.pix.dto.exporttoexcel.ExportToExcelDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExportToExcelDelegate
{

    private static Log log;

    public ExportToExcelDelegate()
    {
    }

    public ExportToExcelDTO getReportDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getReportDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }

    public ExportToExcelDTO getDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }

    public ExportToExcelDTO getMillDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getMillDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }

    public ExportToExcelDTO getPurchaseDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getPurchaseDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }

    public ExportToExcelDTO getPurchaseMillDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getPurchaseMillDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }

    public ExportToExcelDTO getGoodReceiptDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getGoodReceiptDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }
    //RFS GR chnages @anshu.bhardwaj    
    public ExportToExcelDTO getGoodReceiptPaperHistory(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getGoodReceiptPaperHistory(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}
    
    
    public ExportToExcelDTO getGoodReceiptPaperNew(ExportToExcelDTO objDTO)  // getGoodReceiptPaperPop, getGoodReceiptPaperNew
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getGoodReceiptPaperNew(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}
    
    
    // GR popup
    
    public ExportToExcelDTO getGoodReceiptPaperPop(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getGoodReceiptPaperPop(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}
    
    // cost user GR pop
    public ExportToExcelDTO cuOwnedQtyPopup(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().cuOwnedQtyPopup(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}


    // DM popup
    public ExportToExcelDTO getDMPaperPop(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getDMPaperPop(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}

    // DM popup
    public ExportToExcelDTO getDMHistoryPaperPop(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getDMHistoryPaperPop(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}

    
    
    //end of RFS GR changes

    public ExportToExcelDTO getDeliveryDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getDeliveryDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }
//RFS New DM    
    public ExportToExcelDTO getDeliveryMessageNewMill(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getDeliveryMessageNewMill(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}
    
    public ExportToExcelDTO getDeliveryMessageHistory(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getDeliveryMessageHistory(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}
    
    public ExportToExcelDTO getInboxDeliveryMessageHistory(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getInboxDeliveryMessageHistory(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}
    //end of RFS DM
    public ExportToExcelDTO getBookSpecDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getBookSpecDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }

    public ExportToExcelDTO getOrderStatusDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getOrderStatusDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }

    public ExportToExcelDTO getDeliveryMessageApprovalDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        ExportToExcelDTO returnDTO = null;
        try
        {
            returnDTO = getExportToExcelLocal().getDeliveryMessageApprovalDetail(objDTO);
        }
        catch(AppException re)
        {
            log.error("Error" + re);
        }
        return returnDTO;
    }
    /**
     * For Displaying the Outstanding Delivery Report 
     *          
     * @author Anshu Bhardwaj 
     */
    public ExportToExcelDTO getOutstandingDetail(ExportToExcelDTO objDTO)
    throws AppException
{
    ExportToExcelDTO returnDTO = null;
    try
    {
        returnDTO = getExportToExcelLocal().getOutstandingDetail(objDTO);
    }
    catch(AppException re)
    {
        log.error("Error" + re);
    }
    return returnDTO;
}
    
    public ExportToExcelDTO getPotentialARPDetail(ExportToExcelDTO objDTO) throws AppException
	{
	    ExportToExcelDTO returnDTO = null;
	    try
	    {
	        returnDTO = getExportToExcelLocal().getPotentialARPDetail(objDTO);
	    }
	    catch(AppException re)
	    {
	        log.error("Error" + re);
	    }
	    return returnDTO;
	}
    /*end of changes for Displaying Outstanding Delivery Report*/

    private ExportToExcelLocal getExportToExcelLocal()
        throws AppException
    {
        ExportToExcelLocal objExportToExcelLocal = null;
        try
        {
            ExportToExcelLocalHome objExportToExcelLocalHome = ServiceLocator.getExportToExcelLocalHome();
            objExportToExcelLocal = objExportToExcelLocalHome.create();
        }
        catch(NamingException ne)
        {
            ne.printStackTrace();
        }
        catch(CreateException ce)
        {
            ce.printStackTrace();
        }
        return objExportToExcelLocal;
    }

    static 
    {
        log = LogFactory.getLog(com.pearson.pix.dao.orderstatus.OrderStatusDAOImpl.class.getName());
    }
}
