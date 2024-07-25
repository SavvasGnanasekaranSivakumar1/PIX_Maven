package com.pearson.pix.dao.exporttoexcel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.DatabaseRecord;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.exporttoexcel.ExportToExcelDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

// Referenced classes of package com.pearson.pix.dao.exporttoexcel:
//            IExportToExcelDAO

public class ExportToExcelDAO extends BaseDAO
    implements IExportToExcelDAO
{

    private static Log log;

    public ExportToExcelDAO()
    {
    }

    public ExportToExcelDTO getReportDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Vector argNames = new Vector();
        Session objSession = null;
        Vector reportDetails = new Vector(); 
        try
        {
            objSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("Pix_Report_Proc");
            call.addNamedArgumentValue("i_isbn", objDTO.getIsbn());
            call.addNamedArgumentValue("i_po_no", objDTO.getPorderNo());
            call.addNamedArgumentValue("i_print_no", objDTO.getPrintNo());
            call.addNamedArgumentValue("i_item_type", objDTO.getItem());
            call.addNamedArgumentValue("i_user_id", new Integer(objDTO.getUserId()));
            call.addNamedArgumentValue("i_pagination", new Integer(objDTO.getPagination()));
            call.addNamedArgumentValue("i_order_by", objDTO.getOrderBy());
            call.addNamedArgumentValue("i_sort", objDTO.getSort());
            call.addNamedArgumentValue("i_start_date", objDTO.getsdate());
            call.addNamedArgumentValue("i_end_date", objDTO.getedate());
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                if(objDatabaseRecord.get("ISBN") != null)
                {
                    reportDetails.addElement(objDatabaseRecord.get("ISBN").toString());
                } else
                {
                    reportDetails.addElement("");
                }
                if(objDatabaseRecord.get("PRINT_NO") != null)
                {
                    reportDetails.addElement(objDatabaseRecord.get("PRINT_NO").toString());
                } else
                {
                    reportDetails.addElement("");
                }
                if(objDatabaseRecord.get("PO_NO") != null)
                {
                    reportDetails.addElement(objDatabaseRecord.get("PO_NO").toString());
                } else
                {
                    reportDetails.addElement("");
                }
                if(objDatabaseRecord.get("ITEM_TYPE") != null)
                {
                    reportDetails.addElement(objDatabaseRecord.get("ITEM_TYPE").toString());
                } else
                {
                    reportDetails.addElement("");
                }
                if(objDatabaseRecord.get("ITEM_NO") != null)
                {
                    reportDetails.addElement(objDatabaseRecord.get("ITEM_NO").toString());
                } else
                {
                    reportDetails.addElement("");
                }
                if(objDatabaseRecord.get("VERSION_NO") != null)
                {
                    reportDetails.addElement(objDatabaseRecord.get("VERSION_NO").toString());
                } else
                {
                    reportDetails.addElement("");
                }
                Date objRequestedDate;
                if((objRequestedDate = (Date)objDatabaseRecord.get("POSTED_DATE")) != null)
                {
                    reportDetails.addElement(PIXUtil.sqlToStandardDate(objRequestedDate.toString()));
                } else
                {
                    reportDetails.addElement("");
                }
                if((String)objDatabaseRecord.get("POSTED_BY") != null)
                {
                    reportDetails.addElement((String)objDatabaseRecord.get("POSTED_BY"));
                } else
                {
                    reportDetails.addElement("");
                }
                if((String)objDatabaseRecord.get("VENDOR") != null)
                {
                    reportDetails.addElement((String)objDatabaseRecord.get("VENDOR"));
                } else
                {
                    reportDetails.addElement("");
                }
                objDTO.setExportElementVec(argNames);
                objDTO.setReport_details(reportDetails);
            }

        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
            throw appException;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(objSession != null)
            {
                objSession.release();
            }
        }
        return objDTO;
    }

    public ExportToExcelDTO getDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Session objSession = null;
        Vector details = new Vector();
        Vector argNames = new Vector();
        try
        {
            objSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("Pix_Planning_To_Excel_Proc");
            call.addNamedArgumentValue("i_input", objDTO.getInputString());
            call.addNamedArgumentValue("i_page", objDTO.getPageNo());
            call.addNamedArgumentValue("i_po_no", "");
            call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
            call.addNamedArgumentValue("i_order_by", "1");
            call.addNamedArgumentValue("i_sort", "ASC");
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                details.addElement(objDatabaseRecord.get("ISBN10") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ISBN10"))));
                details.addElement(objDatabaseRecord.get("PRINT_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("PRINT_NO"))));
                details.addElement(objDatabaseRecord.get("TOTAL_QUANTITY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("TOTAL_QUANTITY"))));
                Date bbd;
                if((bbd = (Date)objDatabaseRecord.get("BBD")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(bbd.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("STATUS_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("STATUS_DESCRIPTION"))));
                Date postedDate;
                if((postedDate = (Date)objDatabaseRecord.get("POSTED_DATE")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(postedDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("POSTED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("POSTED_BY"))));
                details.addElement(objDatabaseRecord.get("ACKNOWLEDGE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ACKNOWLEDGE"))));
                details.addElement(objDatabaseRecord.get("TITLE_DESC") == null ? "" : ((Object) ((String)objDatabaseRecord.get("TITLE_DESC"))));
                details.addElement(objDatabaseRecord.get("ISBN13") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ISBN13"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("BUYER_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CITY"))));
                details.addElement(objDatabaseRecord.get("BUYER_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_STATE"))));
                details.addElement(objDatabaseRecord.get("BUYER_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CITY"))));
                details.addElement(objDatabaseRecord.get("VENDOR_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_STATE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_1"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_2"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_3"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CITY"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_STATE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_EMAIL"))));
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
        }
        finally
        {
            if(objSession != null)
            {
                objSession.release();
            }
        }
        return objDTO;
    }

    public ExportToExcelDTO getMillDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Session objSession = null;
        Vector details = new Vector();
        Vector argNames = new Vector();
        try
        {
            objSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("Pix_Paper_Plan_To_Excel_Proc");
            call.addNamedArgumentValue("i_input", objDTO.getInputString());
            call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                if(objDatabaseRecord.get("PLAN_NO") != null)
                {
                    details.addElement(objDatabaseRecord.get("PLAN_NO").toString());
                } else
                {
                    details.addElement("");
                }
                if(objDatabaseRecord.get("RELEASE_NO") != null)
                {
                    details.addElement(objDatabaseRecord.get("RELEASE_NO").toString());
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("MATERIAL_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MATERIAL_NO"))));
                details.addElement(objDatabaseRecord.get("PAPER_BW_GRD") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PAPER_BW_GRD"))));
                details.addElement(objDatabaseRecord.get("TOTAL_QUANTITY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("TOTAL_QUANTITY"))));
                details.addElement(objDatabaseRecord.get("STATUS_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("STATUS_DESCRIPTION"))));
                details.addElement(objDatabaseRecord.get("MATERIAL_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MATERIAL_DESCRIPTION"))));
                Date postedDate;
                if((postedDate = (Date)objDatabaseRecord.get("POSTED_DATE")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(postedDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("ACKNOWLEDGE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ACKNOWLEDGE"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("BUYER_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CITY"))));
                details.addElement(objDatabaseRecord.get("BUYER_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_STATE"))));
                details.addElement(objDatabaseRecord.get("BUYER_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CITY"))));
                details.addElement(objDatabaseRecord.get("VENDOR_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_STATE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_EMAIL"))));
                if(objDatabaseRecord.get("RN") != null)
                {
                    details.addElement(objDatabaseRecord.get("RN").toString());
                } else
                {
                    details.addElement("");
                }
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
        }
        finally
        {
            if(objSession != null)
            {
                objSession.release();
            }
        }
        return objDTO;
    }
    
    
    

    public ExportToExcelDTO getPurchaseDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Session objSession = null;
        Vector details = new Vector();
        Vector argNames = new Vector();
        try
        {
            objSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            if(objDTO.getOrderType() != null && "FO".equals(objDTO.getOrderType()))
            {
                call.setProcedureName("Pix_Fo_To_Excel_Proc");
            } else
            {
                call.setProcedureName("Pix_Po_To_Excel_Proc");
            }
            System.out.println("hello");
            call.addNamedArgumentValue("i_input", objDTO.getInputString());
            call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
            call.addNamedArgumentValue("i_po_no", "");
            call.addNamedArgumentValue("i_order_type", objDTO.getOrderType());
            call.addNamedArgumentValue("i_page", objDTO.getPageNo());
            call.addNamedArgumentValue("i_order_by", "1");
            call.addNamedArgumentValue("i_sort", "ASC");
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                //System.out.println(objDatabaseRecord);
                argNames = objDatabaseRecord.getFields();
                details.addElement(objDatabaseRecord.get("PO_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PO_NO"))));
                details.addElement(objDatabaseRecord.get("ISBN10") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ISBN10"))));
                if((BigDecimal)objDatabaseRecord.get("PRINT_NO") != null)
                {
                    details.addElement((BigDecimal)objDatabaseRecord.get("PRINT_NO"));
                } else
                {
                    details.addElement("");
                }
                if(!"PO".equals(objDTO.getOrderType()) || !"V".equalsIgnoreCase(objDTO.getRoleType()))
                {
                    details.addElement(objDatabaseRecord.get("PAPER_GRADE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PAPER_GRADE"))));
                }
                if((BigDecimal)objDatabaseRecord.get("TOTAL_QUANTITY") != null)
                {
                    details.addElement((BigDecimal)objDatabaseRecord.get("TOTAL_QUANTITY"));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("TITLE_DESC") == null ? "" : ((Object) ((String)objDatabaseRecord.get("TITLE_DESC"))));
                Date bbd;
                if((bbd = (Date)objDatabaseRecord.get("BBD")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(bbd.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("STATUS_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("STATUS_DESCRIPTION"))));
                Date postDate;
                if((postDate = (Date)objDatabaseRecord.get("POSTED_DATE")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(postDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("POSTED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("POSTED_BY"))));
                details.addElement(objDatabaseRecord.get("ISBN13") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ISBN13"))));
                details.addElement(objDatabaseRecord.get("JOB_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("JOB_NO"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("BUYER_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CITY"))));
                details.addElement(objDatabaseRecord.get("BUYER_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_STATE"))));
                details.addElement(objDatabaseRecord.get("BUYER_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CITY"))));
                details.addElement(objDatabaseRecord.get("VENDOR_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_STATE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_1"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_2"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_3"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CITY"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_STATE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_EMAIL"))));
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
        }
        finally
        {
            if(objSession != null)
            {
                objSession.release();
            }
        }
        return objDTO;
    }

    public ExportToExcelDTO getPurchaseMillDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Session objSession = null;
        Vector details = new Vector();
        Vector argNames = new Vector();
        try
        {
            objSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("Pix_Paper_Po_To_Excel_Proc");
            call.addNamedArgumentValue("i_input", objDTO.getInputString());
            call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                details.addElement(objDatabaseRecord.get("PO_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PO_NO"))));
                if((BigDecimal)objDatabaseRecord.get("RELEASE_NO") != null)
                {
                    details.addElement((BigDecimal)objDatabaseRecord.get("RELEASE_NO"));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("MATERIAL_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MATERIAL_NO"))));
                details.addElement(objDatabaseRecord.get("JOB_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("JOB_NO"))));
                details.addElement(objDatabaseRecord.get("PAPER_BW_GRD") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PAPER_BW_GRD"))));
                if((BigDecimal)objDatabaseRecord.get("TOTAL_QUANTITY") != null)
                {
                    details.addElement((BigDecimal)objDatabaseRecord.get("TOTAL_QUANTITY"));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("STATUS_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("STATUS_DESCRIPTION"))));
                details.addElement(objDatabaseRecord.get("MATERIAL_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MATERIAL_DESCRIPTION"))));
                Date postDate;
                if((postDate = (Date)objDatabaseRecord.get("POSTED_DATE")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(postDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("ACKNOWLEDGE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ACKNOWLEDGE"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("BUYER_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CITY"))));
                details.addElement(objDatabaseRecord.get("BUYER_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_STATE"))));
                details.addElement(objDatabaseRecord.get("BUYER_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CITY"))));
                details.addElement(objDatabaseRecord.get("VENDOR_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_STATE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_1"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_2"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_3"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CITY"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_STATE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_COMMENTS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_COMMENTS"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_LAST_NAME"))));
                if((BigDecimal)objDatabaseRecord.get("rn") != null)
                {
                    details.addElement((BigDecimal)objDatabaseRecord.get("rn"));
                } else
                {
                    details.addElement("");
                }
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
        }
        finally
        {
            if(objSession != null)
            {
                objSession.release();
            }
        }
        return objDTO;
    }

    public ExportToExcelDTO getGoodReceiptDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Vector argNames = new Vector();
        Session objSession = null;
        Vector details = new Vector();
        try
        {
            objSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("Pix_gre_to_excel_Proc");
            call.addNamedArgumentValue("i_input", objDTO.getInputString());
            call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
            call.addNamedArgumentValue("i_po_id", objDTO.getPoId());
            call.addNamedArgumentValue("i_po_version", objDTO.getPoVersion());
            call.addNamedArgumentValue("i_order_by", "1");
            call.addNamedArgumentValue("i_sort", "ASC");
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                details.addElement(objDatabaseRecord.get("RECEIPT_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("RECEIPT_NO"))));
                details.addElement(objDatabaseRecord.get("MSG_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MSG_NO"))));
                Date arrivalDate;
                if((arrivalDate = (Date)objDatabaseRecord.get("ACTUAL_ARRIVAL_DATE")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(arrivalDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("COMP_VENDOR") == null ? "" : ((Object) ((String)objDatabaseRecord.get("COMP_VENDOR"))));
                details.addElement(objDatabaseRecord.get("SHIP_TO_VENDOR") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIP_TO_VENDOR"))));
                Date creationDate;
                if((creationDate = (Date)objDatabaseRecord.get("CREATION_DATE_TIME")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(creationDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("CREATED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("CREATED_BY"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CITY"))));
                details.addElement(objDatabaseRecord.get("VENDOR_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_STATE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_1"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_2"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_NAME_3"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CITY"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_STATE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("SHIPTO_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SHIPTO_CONTACT_EMAIL"))));
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
            throw appException;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(objSession != null)
            {
                objSession.release();
            }
        }
        return objDTO;
    }

    public ExportToExcelDTO getDeliveryDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Vector argNames = new Vector();
        Session objSession = null;
        Vector details = new Vector();
        try
        {
            objSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("Pix_dm_to_excel_Proc");
            call.addNamedArgumentValue("i_input", objDTO.getInputString());
            call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
            call.addNamedArgumentValue("i_po_id", objDTO.getPoId());
            call.addNamedArgumentValue("i_po_version", objDTO.getPoVersion());
            call.addNamedArgumentValue("i_order_by", "1");
            call.addNamedArgumentValue("i_sort", "ASC");
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                details.addElement(objDatabaseRecord.get("MSG_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MSG_NO"))));
                Date creationDate;
                if((creationDate = (Date)objDatabaseRecord.get("CREATION_DATE_TIME")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(creationDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("CREATED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("CREATED_BY"))));
                details.addElement(objDatabaseRecord.get("DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("DESCRIPTION"))));
                if((BigDecimal)objDatabaseRecord.get("MSG_ID") != null)
                {
                    details.addElement((BigDecimal)objDatabaseRecord.get("MSG_ID"));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CITY"))));
                details.addElement(objDatabaseRecord.get("VENDOR_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_STATE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_EMAIL"))));
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
            throw appException;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(objSession != null)
            {
                objSession.release();
            }
        }
        return objDTO;
    }

    public ExportToExcelDTO getBookSpecDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Vector argNames = new Vector();
        Session objSession = null;
        Vector details = new Vector();
        try
        {
            objSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("Pix_bs_to_excel_Proc");
            call.addNamedArgumentValue("i_input", objDTO.getInputString());
            call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
            call.addNamedArgumentValue("i_page", objDTO.getPageNo());
            call.addNamedArgumentValue("i_order_by", "1");
            call.addNamedArgumentValue("i_sort", "ASC");
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                details.addElement(objDatabaseRecord.get("SPEC_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SPEC_NO"))));
                details.addElement(objDatabaseRecord.get("ISBN10") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ISBN10"))));
                if((BigDecimal)objDatabaseRecord.get("PRINT_NO") != null)
                {
                    details.addElement((BigDecimal)objDatabaseRecord.get("PRINT_NO"));
                } else
                {
                    details.addElement("");
                }
                Date issueDate;
                if((issueDate = (Date)objDatabaseRecord.get("SPEC_ISSUE_DATE")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(issueDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("STATUS_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("STATUS_DESCRIPTION"))));
                details.addElement(objDatabaseRecord.get("ACKNOWLEDGE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ACKNOWLEDGE"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_NAME_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("BUYER_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("BUYER_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CITY"))));
                details.addElement(objDatabaseRecord.get("BUYER_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_STATE"))));
                details.addElement(objDatabaseRecord.get("BUYER_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("BUYER_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("BUYER_CONTACT_EMAIL"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_NAME_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_NAME_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("VENDOR_ADDRESS_4") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_ADDRESS_4"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CITY"))));
                details.addElement(objDatabaseRecord.get("VENDOR_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_STATE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FIRST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_LAST_NAME"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_PHONE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_PHONE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_MOBILE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_MOBILE"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_FAX") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_FAX"))));
                details.addElement(objDatabaseRecord.get("VENDOR_CONTACT_EMAIL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("VENDOR_CONTACT_EMAIL"))));
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
            throw appException;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(objSession != null)
            {
                objSession.release();
            }
        }
        return objDTO;
    }

    public ExportToExcelDTO getOrderStatusDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Session objClientSession = getClientSession();
        Vector argNames = new Vector();
        Vector details = new Vector();
        try
        {
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("Pix_Os_To_Excel_Proc");
            call.addNamedArgumentValue("i_input", objDTO.getInputString());
            call.addNamedArgumentValue("i_po_id", objDTO.getPoid());
            call.addNamedArgumentValue("i_po_version", objDTO.getPoversion());
            call.addNamedArgumentValue("i_order_by", "1");
            call.addNamedArgumentValue("i_sort", "ASC");
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = objClientSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                details.addElement((String)objDatabaseRecord.get("STATUS_NO"));
                Date creationDate;
                if((creationDate = (Date)objDatabaseRecord.get("CREATION_DATE_TIME")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(creationDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement((String)objDatabaseRecord.get("CREATED_BY"));
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "OrderStatusDAOImpl,getOrderStatusList", e);
            throw appException;
        }
        finally
        {
            if(objClientSession != null)
            {
                objClientSession.release();
            }
        }
        return objDTO;
    }

    public ExportToExcelDTO getDeliveryMessageApprovalDetail(ExportToExcelDTO objDTO)
        throws AppException
    {
        Vector argNames = new Vector();
        Session clientSession = null;
        Vector details = new Vector();
        Date creationDate = null;
        try
        {
            clientSession = getClientSession();
            StoredProcedureCall call = new StoredProcedureCall();
            call.setProcedureName("pix_cost_actng_proc");
            call.addNamedArgumentValue("i_id", null);
            call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
            call.addNamedArgumentValue("i_input_1", objDTO.getInputString());
            call.addNamedArgumentValue("i_request", "EXCEL");
            call.addNamedArgumentValue("i_pagination", null);
            call.addNamedArgumentValue("i_order_by", null);
            call.addNamedArgumentValue("i_sort", null);
            call.addNamedArgumentValue("i_po_no", null);
            call.addNamedArgumentValue("i_msg_no", null);
            call.addNamedArgumentValue("i_start_date", null);
            call.addNamedArgumentValue("i_end_date", null);
            call.addNamedArgumentValue("i_cons_flag", null);
            call.addNamedArgumentValue("i_mat_no", null);
            call.addNamedArgumentValue("i_mill", null);
            call.useNamedCursorOutputAsResultSet("list_refcursor");
            Vector objVector = clientSession.executeSelectingCall(call);
            for(int i = 0; i < objVector.size(); i++)
            {
                DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
                argNames = objDatabaseRecord.getFields();
                details.addElement(objDatabaseRecord.get("PO_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PO_NO"))));		
                details.addElement(objDatabaseRecord.get("PO_LINE_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("PO_LINE_NO"))));
                details.addElement(objDatabaseRecord.get("MATERIAL_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MATERIAL_NO"))));
                details.addElement(objDatabaseRecord.get("MSG_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MSG_NO"))));
                details.addElement(objDatabaseRecord.get("DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("DESCRIPTION"))));
                details.addElement(objDatabaseRecord.get("REQUESTED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("REQUESTED_QTY"))));
                details.addElement(objDatabaseRecord.get("DELIVERED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DELIVERED_QTY"))));
                
                details.addElement(objDatabaseRecord.get("RECEIVED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("RECEIVED_QTY"))));
                details.addElement(objDatabaseRecord.get("OWNERSHIP_MODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("OWNERSHIP_MODE"))));
                details.addElement(objDatabaseRecord.get("OWNED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("OWNED_QTY"))));
                details.addElement(objDatabaseRecord.get("TO_BE_OWNED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("TO_BE_OWNED_QTY"))));
                details.addElement(objDatabaseRecord.get("OWNING_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("OWNING_QTY"))));
                
                
                
                
                details.addElement(objDatabaseRecord.get("STATUS_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("STATUS_DESCRIPTION"))));
                details.addElement(objDatabaseRecord.get("MILL_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_NAME"))));
                details.addElement(objDatabaseRecord.get("MILL_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("MILL_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("MILL_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("MILL_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_CITY"))));
                details.addElement(objDatabaseRecord.get("MILL_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_STATE"))));
                details.addElement(objDatabaseRecord.get("MILL_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("MILL_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_COUNTRY_CODE"))));
                details.addElement(objDatabaseRecord.get("PRINTER_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER_NAME"))));
                details.addElement(objDatabaseRecord.get("PRINTER_ADDRESS_1") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER_ADDRESS_1"))));
                details.addElement(objDatabaseRecord.get("PRINTER_ADDRESS_2") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER_ADDRESS_2"))));
                details.addElement(objDatabaseRecord.get("PRINTER_ADDRESS_3") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER_ADDRESS_3"))));
                details.addElement(objDatabaseRecord.get("PRINTER_CITY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER_CITY"))));
                details.addElement(objDatabaseRecord.get("PRINTER_STATE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER_STATE"))));
                details.addElement(objDatabaseRecord.get("PRINTER_POSTAL_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER_POSTAL_CODE"))));
                details.addElement(objDatabaseRecord.get("PRINTER_COUNTRY_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER_COUNTRY_CODE"))));
                if((creationDate = (Date)objDatabaseRecord.get("CREATION_DATE_TIME")) != null)
                {
                    details.addElement(PIXUtil.sqlToStandardDate(creationDate.toString()));
                } else
                {
                    details.addElement("");
                }
                details.addElement(objDatabaseRecord.get("CREATED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("CREATED_BY"))));
            }

            objDTO.setExportElementVec(argNames);
            objDTO.setReport_details(details);
        }
        catch(TopLinkException e)
        {
            AppException appException = new AppException();
            appException.performErrorAction("9001", "ExportToExcelDAO,getDeliveryMessageApprovalDetail", e);
            throw appException;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(clientSession != null)
            {
                clientSession.release();
            }
        }
        return objDTO;
    }
    /**
     * Method for Displaying the Outstanding Delivery Report from database 
     * 
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @author Anshu Bhardwaj 
     */
    public ExportToExcelDTO getOutstandingDetail(ExportToExcelDTO objDTO)
    throws AppException
{
    Vector argNames = new Vector();
    Session clientSession = null;
    Vector details = new Vector();
    try
    {
        clientSession = getClientSession();        
        StoredProcedureCall call = new StoredProcedureCall();        
        call.setProcedureName("Pix_Pending_DM_Report_Proc");      
        call.addNamedArgumentValue("i_user_id", objDTO.getUserId());        
        call.useNamedCursorOutputAsResultSet("list_refcursor");
        Vector objVector = clientSession.executeSelectingCall(call);
        Date reqDeliveryDate;
        
        for(int i = 0; i < objVector.size(); i++)
        {
            DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
            argNames = objDatabaseRecord.getFields();
            
            details.addElement(objDatabaseRecord.get("PO_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PO_NO"))));
            //details.addElement(objDatabaseRecord.get("L3_MATERIAL_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("L3_MATERIAL_NO"))));
            //details.addElement(objDatabaseRecord.get("SUPPLIER_SAN") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SUPPLIER_SAN"))));
            details.addElement(objDatabaseRecord.get("SUPPLIER_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SUPPLIER_NAME"))));
            details.addElement(objDatabaseRecord.get("PO_HEADER_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("PO_HEADER_QTY"))));
            details.addElement(objDatabaseRecord.get("PO_LINE_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("PO_LINE_NO"))));
            details.addElement(objDatabaseRecord.get("L4_MATERIAL_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("L4_MATERIAL_NO"))));
            details.addElement(objDatabaseRecord.get("L4_MATERIAL_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("L4_MATERIAL_DESCRIPTION"))));
            
            if((reqDeliveryDate = (Date)objDatabaseRecord.get("REQUESTED_DELIVERY_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(reqDeliveryDate.toString()));
            } else
            {
                details.addElement("");
            }           
            details.addElement(objDatabaseRecord.get("SAP_PLANT_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SAP_PLANT_CODE"))));            
            details.addElement(objDatabaseRecord.get("LINE_REQUESTED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("LINE_REQUESTED_QTY"))));
            details.addElement(objDatabaseRecord.get("DELIVERED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DELIVERED_QTY"))));            
            details.addElement(objDatabaseRecord.get("RECEIVED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("RECEIVED_QTY"))));                       
            details.addElement(objDatabaseRecord.get("QTY_VARIANCE") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("QTY_VARIANCE"))));
            
                       
        }      
        
        objDTO.setExportElementVec(argNames);
        objDTO.setReport_details(details);
    }
    catch(TopLinkException e)
    {
        AppException appException = new AppException();
        appException.performErrorAction("9001", "ExportToExcelDAO,getOutstandingDetail", e);
        throw appException;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        if(clientSession != null)
        {
            clientSession.release();
        }
    }
    return objDTO;
} 
    /*End of changes for Displaying the Outstanding Delivery Report*/
    /**
     * Method for Displaying the GR History from database 
     * 
     * @param objDTO object of ExportToExcelDTO
     * @return objDTO object of ExportToExcelDTO
     * @author Anshu Bhardwaj 
     */
    public ExportToExcelDTO getGoodReceiptPaperHistory(ExportToExcelDTO objDTO)
    throws AppException
{
    Vector argNames = new Vector();
    Session objSession = null;
    Vector details = new Vector();
    argNames.add("PO LINE#");
    argNames.add("MILL/ MERCHANT");
    argNames.add("PRINTER");
    argNames.add("GOODS RECEIPT#");
    argNames.add("L4 MATERIAL DESC");
    argNames.add("DELIVERED DATE");
    argNames.add("REQ PO LINE QTY");
    argNames.add("DEL PO LINE QTY");
    argNames.add("TOTAL REC QTY");
    argNames.add("DEL QTY");
    argNames.add("DEL BY");
    argNames.add("APPROVAL FLAG");
    argNames.add("RECEIVED QTY");
    argNames.add("RECEIVED DATE");
    try
    {
        objSession = getClientSession();
        StoredProcedureCall call = new StoredProcedureCall();
        call.setProcedureName("Pix_paper_hist_gr_excel_Proc");
       // call.addNamedArgumentValue("i_input", objDTO.getInputString());
        call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
        call.addNamedArgumentValue("i_po_id", objDTO.getPoId());
        call.addNamedArgumentValue("i_po_version", objDTO.getPoVersion());
       // call.addNamedArgumentValue("i_order_by", "1");
       // call.addNamedArgumentValue("i_sort", "ASC");
        call.useNamedCursorOutputAsResultSet("list_refcursor");
        Vector objVector = objSession.executeSelectingCall(call);
        Date deliveredDate;
        Date recDate;
        
        for(int i = 0; i < objVector.size(); i++)
        {
            DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
       //     argNames = objDatabaseRecord.getFields();
            
            details.addElement(objDatabaseRecord.get("PO_LINE_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("PO_LINE_NO"))));
            details.addElement(objDatabaseRecord.get("MILL_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_NAME"))));
            details.addElement(objDatabaseRecord.get("PRINTER") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER"))));
            details.addElement(objDatabaseRecord.get("RECEIPT_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("RECEIPT_NO"))));
            details.addElement(objDatabaseRecord.get("L4_MATERIAL_DESC") == null ? "" : ((Object) ((String)objDatabaseRecord.get("L4_MATERIAL_DESC"))));
            if((deliveredDate = (Date)objDatabaseRecord.get("DELIVERED_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(deliveredDate.toString()));
            } else
            {
                details.addElement("");
            }            
            details.addElement(objDatabaseRecord.get("BUYER_REQUESTED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("BUYER_REQUESTED_QTY"))));
            details.addElement(objDatabaseRecord.get("TOTAL_DELIVERED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("TOTAL_DELIVERED_QTY"))));
            details.addElement(objDatabaseRecord.get("TOTAL_RECEIVED_QUANTITY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("TOTAL_RECEIVED_QUANTITY"))));
            details.addElement(objDatabaseRecord.get("DM_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DM_QTY"))));
            details.addElement(objDatabaseRecord.get("DELIVERED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("DELIVERED_BY"))));
            details.addElement(objDatabaseRecord.get("APPROVAL_FLAG") == null ? "" : ((Object) ((String)objDatabaseRecord.get("APPROVAL_FLAG"))));
            details.addElement(objDatabaseRecord.get("REC_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("REC_QTY"))));
            //details.addElement(objDatabaseRecord.get("RECEIVED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("RECEIVED_BY"))));
            if((recDate = (Date)objDatabaseRecord.get("REC_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(recDate.toString()));
            } else
            {
                details.addElement("");
            }
            //details.addElement(objDatabaseRecord.get("MSG_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MSG_NO"))));            
        }

        objDTO.setExportElementVec(argNames);
        objDTO.setReport_details(details);
    }
    catch(TopLinkException e)
    {
        AppException appException = new AppException();
        appException.performErrorAction("9001", "ReportDAOImpl,getGoodReceiptPaperHistory", e);
        throw appException;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        if(objSession != null)
        {
            objSession.release();
        }
    }
    return objDTO;
}   
    public ExportToExcelDTO getGoodReceiptPaperNew(ExportToExcelDTO objDTO)
    throws AppException
{
    Vector argNames = new Vector();
    Session objSession = null;
    Vector details = new Vector();
    argNames.add("PO LINE#");
    argNames.add("MILL / MERCHANT");
    argNames.add("PRINTER");
    argNames.add("DELIVERY MESSAGE# ");
    argNames.add("L4 MATERIAL DESC");
    argNames.add("DELIVERED DATE");
    argNames.add("REQUESTED QTY");
    argNames.add("DELIVERED QTY");
    argNames.add("RECEIVED QTY");
    argNames.add("GR TO BE POSTED");
    
    
    
    try
    {
        objSession = getClientSession();
        StoredProcedureCall call = new StoredProcedureCall();
        call.setProcedureName("Pix_paper_new_gr_excel_Proc");       
        call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
        call.addNamedArgumentValue("i_po_id", objDTO.getPoId());
        call.addNamedArgumentValue("i_po_version", objDTO.getPoVersion());       
        call.useNamedCursorOutputAsResultSet("list_refcursor");
        Vector objVector = objSession.executeSelectingCall(call);
        Date arrivalDate;
        
        for(int i = 0; i < objVector.size(); i++)
        {
            DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
//            argNames = objDatabaseRecord.getFields();
            
            details.addElement(objDatabaseRecord.get("PO_LINE_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("PO_LINE_NO"))));
            details.addElement(objDatabaseRecord.get("MILL_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MILL_NAME"))));
            details.addElement(objDatabaseRecord.get("PRINTER") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER"))));
            details.addElement(objDatabaseRecord.get("MSG_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MSG_NO"))));
            details.addElement(objDatabaseRecord.get("L4_MATERIAL_DESC") == null ? "" : ((Object) ((String)objDatabaseRecord.get("L4_MATERIAL_DESC"))));
            if((arrivalDate = (Date)objDatabaseRecord.get("ACTUAL_ARRIVAL_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(arrivalDate.toString()));
            } else
            {
                details.addElement("");
            }
            details.addElement(objDatabaseRecord.get("BUYER_REQUESTED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("BUYER_REQUESTED_QTY"))));
            //details.addElement(objDatabaseRecord.get("DELIVERED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DELIVERED_QTY"))));            
            details.addElement(objDatabaseRecord.get("DM_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DM_QTY"))));            
            details.addElement(objDatabaseRecord.get("CUMULATIVE_RECEIVED_QUANTITY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("CUMULATIVE_RECEIVED_QUANTITY"))));         
            details.addElement(objDatabaseRecord.get("TO_BE_POSTED") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("TO_BE_POSTED"))));
        }

        objDTO.setExportElementVec(argNames);
        objDTO.setReport_details(details);
    }
    catch(TopLinkException e)
    {
        AppException appException = new AppException();
        appException.performErrorAction("9001", "ReportDAOImpl,getGoodReceiptPaperHistory", e);
        throw appException;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        if(objSession != null)
        {
            objSession.release();
        }
    }
    return objDTO;
} 
    
    // costuser owned/to Be Owned
    public ExportToExcelDTO cuOwnedQtyPopup(ExportToExcelDTO objDTO) throws AppException   // String msgId, String ownershipMode, String pono
    {
 	   Session clientSession = null;
 	   DeliveryMessageLine objDeliveryMessageLine = null;
 	   Vector cuPOP = new Vector();
   	Vector argNames = new Vector();
    Vector details = new Vector();
    
    argNames.add("PO Number");
    argNames.add("PO Line Number");
    argNames.add("L4 Material Number");
    argNames.add("Material Description");
    argNames.add("Mill / Merchant");
    argNames.add("SAP Printer Number");
//      argNames.add("SAP Printer Name");
    argNames.add("Roll Number");
    argNames.add("Delivered Quantity");
    argNames.add("Received Quantity");

 	   
 	   
 	   try
 	   {
 		   Record objRecord = null;
 		   clientSession = getClientSession();
 		   
 		   String msgId = objDTO.getCuMsgId();
 		   String ownershipMode = objDTO.getCuOwnershipMode();
 		   String pono = objDTO.getCuPono();
 		   
 		   BigDecimal bMsgId = new BigDecimal(msgId);

 		   StoredProcedureCall call = new StoredProcedureCall(); 
 	   		call.setProcedureName("PIX_COST_ACCNT_HYPER_LINE_PROC");
 	   		call.addNamedArgumentValue("I_PO_NO",pono);
 	   		call.addNamedArgumentValue("I_KEY_ID",bMsgId);
 	   		call.addNamedArgumentValue("I_MODE",ownershipMode);
 	   		
 	   		call.useNamedCursorOutputAsResultSet("o_sys_cursor");
 	   		Vector objVector = (Vector)clientSession.executeSelectingCall(call);
    
 		  if(objVector != null && objVector.size()>0)
 		  {
 			  for (int i = 0;i < objVector.size();i++)
        	      {
 				  objRecord = (Record)objVector.get(i);
        		   
        		   objDeliveryMessageLine = new DeliveryMessageLine();
        		   
        		   details.addElement(objRecord.get("PO_NO") == null ? "" : ((Object) ((String)objRecord.get("PO_NO")))); 
        		   details.addElement(objRecord.get("PO_LINE_NO") == null ? "" : ((Object) ((BigDecimal)objRecord.get("PO_LINE_NO"))));
        		   details.addElement(objRecord.get("PRODUCT_CODE") == null ? "" : ((Object) ((String)objRecord.get("PRODUCT_CODE"))));
        		   details.addElement(objRecord.get("PRODUCT_DESCRIPTION") == null ? "" : ((Object) ((String)objRecord.get("PRODUCT_DESCRIPTION"))));
        		   details.addElement(objRecord.get("SUPPLIER_NAME") == null ? "" : ((Object) (String)objRecord.get("SUPPLIER_NAME")));
        		   details.addElement(objRecord.get("PRINTER") == null ? "" : ((Object) (String)objRecord.get("PRINTER")));
        		   details.addElement(objRecord.get("ROLL_NO") == null ? "" : ((Object) ((String)objRecord.get("ROLL_NO"))));
        		   details.addElement(objRecord.get("DELIVERED_QTY") == null ? "" : ((Object) ((BigDecimal)objRecord.get("DELIVERED_QTY"))));
        		   details.addElement(objRecord.get("RECEIVED_QTY") == null ? "" : ((Object) ((BigDecimal)objRecord.get("RECEIVED_QTY"))));
      //  		   details.addElement(objRecord.get("STATUS") == null ? "" : ((Object) ((String)objRecord.get("STATUS"))));
        		   
        	      }
 			  
 			 objDTO.setExportElementVec(argNames);
		        objDTO.setReport_details(details);

 		  }
           
 		 
 	   }
 	 
 	   catch(TopLinkException e)
 	   {
 		   log.debug("In DeliverlymessageDaoImpl in list");
 		   AppException appException = new AppException();
 		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
 	                "DeliveryMessageDAOImpl,getDeliveryMessageDetail",e);
 		   throw appException;
 	   }
 	   finally
 	   {
 		   if(clientSession!=null)
 		   {
 			  
 			   clientSession.release();
 		   }
 	   }    
 	   
     return objDTO;
    }

    
    
    
    // GR popup
    public ExportToExcelDTO getGoodReceiptPaperPop(ExportToExcelDTO objDTO)
    throws AppException
    {
    	Vector listVector = null;
    	Vector popUpVector = new Vector();
    	Session objSession = null;
    	Record objRecord = null;
        String []argNamesArr = {"PO Number","PO Line Number","L4 Material Number",
        		"Material Description","Mill / Merchant","SAP Printer Number","SAP Printer Name","Roll Number","Delivered Quantity","Status"};
    	Vector argNames = new Vector();
        argNames.add("PO Number");
        argNames.add("PO Line Number");
        argNames.add("L4 Material Number");
        argNames.add("Material Description");
        argNames.add("Mill / Merchant");
        argNames.add("SAP Printer Number");
  //      argNames.add("SAP Printer Name");
        argNames.add("Roll Number");
        argNames.add("Delivered Quantity");
        argNames.add("Received Quantity");
        System.out.println(argNames);
        
        BigDecimal poid = objDTO.getPoId();
        String productCode = objDTO.getProductCode();
        String msgId = objDTO.getMsgID();
        Vector details = new Vector();
        String dmGRMode = objDTO.getDmGRMode();
        String pono = objDTO.getPono();
    	
    	try {
    		objSession = getClientSession();
    		StoredProcedureCall call = new StoredProcedureCall();
    		if(dmGRMode != null && dmGRMode.equals("DM"))
    		{
    			call.setProcedureName("PIX_DM_GR_ROLL_HYPER_LINK_PROC");
    			call.addNamedArgumentValue("I_PO_NO",pono);
    			call.addNamedArgumentValue("I_KEY_ID",msgId);
    			call.useNamedCursorOutputAsResultSet("O_REF_CURSOR");
    			listVector = (Vector)objSession.executeSelectingCall(call);
    		}
    		if(dmGRMode != null && dmGRMode.equals("GR"))
    		{
    			call.setProcedureName("PIX_GR_ROLL_HYPER_LINK_PROC");
    			call.addNamedArgumentValue("I_PO_NO",pono);
    			call.addNamedArgumentValue("I_KEY_ID",msgId);
    			call.useNamedCursorOutputAsResultSet("O_REF_CURSOR");
    			listVector = (Vector)objSession.executeSelectingCall(call);
    			System.out.println(listVector.size());
    		}
    		
    		/*		listVector = (Vector) objSession
    				.executeSelectingCall(new SQLCall(
    						" SELECT " +
    						" PPLS.PO_NO,PPL.PO_LINE_NO, " +
    						" PPL.PRODUCT_CODE, " +
    						" PPL.PRODUCT_DESCRIPTION, " +
    						" PPLS.SUPPLIER_NAME, " +
    						" '('||PPLP.org_unit_code||')' || '-' || PPLP.name_1 AS PRINTER, " +
    						" PGRR.ROLL_NO, " +
    						" PDMR.QUANTITY      DELIVERED_QTY, " +
    						" PGRR.QUANTITY        RECEIVED_QTY, " +
    						" 'STATUS' " +
    						" FROM " +
    						" PIX_DELIVERY_MSG_ROLL PDMR, " +
    						" PIX_DELIVERY_MSG_LINE PDML,PIX_PO_LINE PPL,PIX_PO_LIST_SUMMARY PPLS, " +
    						" PIX_PO_LINE_PARTY PPLP,PIX_GOOD_RECEIPT_LINE PGRL,PIX_GOOD_RECEIPT_ROLL PGRR " +
    						" WHERE " +
    						" PGRL.RECEIPT_ID = " + "'" + msgId +"'"+
    						" AND PGRL.RECEIPT_ID = PGRR.RECEIPT_ID " +
    						" AND PGRR.ROLL_NO = PDMR.ROLL_NO " +
    						" AND PDMR.MSG_ID = PDML.MSG_ID " +
    						" AND PGRL.MSG_ID = PDML.MSG_ID " +
    						" AND PGRL.PO_ID = PDML.PO_ID " +
    						" AND PGRL.PO_LINE_NO = PDML.PO_LINE_NO " +
    						" AND PGRL.PO_VERSION = PDML.PO_VERSION " +
    						" AND PDML.PO_ID = PPL.PO_ID " +
    						" AND PDML.PO_LINE_NO = PPL.PO_LINE_NO " +
    						" AND PPL.PO_ID = PPLS.PO_ID " +
    						" AND PPL.PO_VERSION = PPLS.PO_VERSION " +
    						" AND PPL.PO_ID = PPLP.PO_ID " +
    						" AND PPL.PO_LINE_NO = PPLP.PO_LINE_NO " +
    						" AND PPL.PO_VERSION = PPLP.PO_VERSION " +
    						" AND (PPLS.PO_NO LIKE '7%' OR PPLS.PO_NO LIKE '9%')"));
*/

    		if (listVector != null && listVector.size() > 0) {
    			for (int i = 0; i < listVector.size(); i++) {
    				
    	            DatabaseRecord objDatabaseRecord = (DatabaseRecord)listVector.get(i);
    	       //     argNames = objDatabaseRecord.getFields();
    				
    	            details.addElement(objDatabaseRecord.get("PO_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PO_NO"))));
    	            details.addElement(objDatabaseRecord.get("PO_LINE_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("PO_LINE_NO"))));
    	            details.addElement(objDatabaseRecord.get("PRODUCT_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRODUCT_CODE"))));
    	            details.addElement(objDatabaseRecord.get("PRODUCT_DESCRIPTION") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRODUCT_DESCRIPTION"))));
    	            details.addElement(objDatabaseRecord.get("SUPPLIER_NAME") == null ? "" : ((Object) ((String)objDatabaseRecord.get("SUPPLIER_NAME"))));
    	       //     details.addElement(objDatabaseRecord.get("ORG_UNIT_CODE") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ORG_UNIT_CODE"))));
    				details.addElement(objDatabaseRecord.get("PRINTER") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER"))));
    				details.addElement(objDatabaseRecord.get("ROLL_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ROLL_NO"))));
    				details.addElement(objDatabaseRecord.get("DELIVERED_QTY") == null ? "" : ((Object) ((BigDecimal)objDatabaseRecord.get("DELIVERED_QTY"))));
    				details.addElement(objDatabaseRecord.get("RECEIVED_QTY") == null ? "" : ((Object) ((BigDecimal)objDatabaseRecord.get("RECEIVED_QTY"))));
    			//	details.addElement(objDatabaseRecord.get("STATUS") == null ? "" : ((Object) ((String)objDatabaseRecord.get("STATUS"))));
    			}
    		        
    		    
    			}
    		objDTO.setReport_details(details);
    		objDTO.setExportElementVec(argNames);
    		}
    		    catch(TopLinkException e)
    		    {
    		        AppException appException = new AppException();
    		        appException.performErrorAction("9001", "ReportDAOImpl,getGoodReceiptPaperHistory", e);
    		        throw appException;
    		    }
    		    catch(Exception e)
    		    {
    		        e.printStackTrace();
    		    }
    		    finally
    		    {
    		        if(objSession != null)
    		        {
    		            objSession.release();
    		        }
    		    }
    		    return objDTO;
    } 

    
 // DM popup
    public ExportToExcelDTO getDMPaperPop(ExportToExcelDTO objDTO)
    throws AppException
    {
    	Vector listVector = null;
    	Vector popUpVector = new Vector();
    	Session objSession = null;
    	Record objRecord = null;
    	Vector argNames = new Vector();
    	argNames.add("PO #");
    	argNames.add("Material Description ");
    	argNames.add("Printer");
        argNames.add("Roll #");
        argNames.add("Roll Weight");
        argNames.add("DM Posted By");
        argNames.add("DM Posted Date");
        System.out.println(argNames);
        
        String pono = objDTO.getPono();
        String productCode = objDTO.getProductCode();
        String lineNo = objDTO.getLineNo();
        
        
        Vector details = new Vector();
    	
    	try {
    		objSession = getClientSession();
    		listVector = (Vector) objSession
    				.executeSelectingCall(new SQLCall(	
    						" select  ppls.po_no po, "+
    						" '(' || TO_NUMBER(ppl.product_code) || ')' ||ppl.product_classif_description  material, "+
    						" pplp.org_unit_code || '-' || pplp.name_1 printer, "+
    						" pdmr.roll_no, "+
    						" pdmr.quantity , "+
    						" pdmr.created_by, "+
    						" pdmr.creation_date_time "+ 
    						" from "+
    						" pix_delivery_msg_roll pdmr, "+
    						" pix_delivery_msg_line pdml,  "+
    						" pix_po_list_summary ppls,  "+
    						" pix_po_line ppl, "+
    						" pix_po_line_party pplp "+
    						" where  ppls.po_no = " + "'"+pono+"'" + " and " +
    						" ppls.po_id = ppl.po_id  "+
    						" and  ppls.po_version = ppl.po_version "+ 
    						" and ppl.product_code = " + "'"+productCode+"'" + " and " +
    						" ppl.po_id = pplp.po_id "+
    						" and ppl.po_line_no = pplp.po_line_no "+
    						" and pplp.po_version = ppl.po_version "+
    						" and ppl.po_id = pdml.po_id  "+
    						" and ppl.po_line_no = pdml.po_line_no "+ 
//    						" and ppl.po_version = pdml.po_version  "+
    						" and pdml.msg_id = pdmr.msg_id  "+
    						" and pdml.msg_line_no = pdmr.msg_line_no "+ 
    						" and ppl.po_line_no = "+ "'"+lineNo+"'" +
    						" order by pdmr.roll_no"));
    						
    			/*			"select pdmr.roll_no, pdmr.quantity, pdmr.created_by, pdmr.creation_date_time from"+
  		                  " pix_delivery_msg_roll pdmr,pix_delivery_msg_line pdml, pix_po_list_summary ppls, pix_po_line ppl where " + 
                            " ppls.po_no = " + "'"+pono+"'" + " and " +
                            " ppl.product_code =  " + "'"+productCode+"'" + " and " +
                            " ppls.po_id = ppl.po_id and " + 
                            " ppls.po_version = ppl.po_version " +
                            " and ppl.po_id = pdml.po_id " +
                            " and ppl.po_line_no = pdml.po_line_no " +
                            " and ppl.po_version = pdml.po_version " +
                            " and pdml.msg_id = pdmr.msg_id " +
                            " and pdml.msg_line_no = pdmr.msg_line_no " +
                            " and ppl.po_line_no = "+ "'"+lineNo+"'" +
                            " order by pdmr.roll_no "));
                            
                            po
                            material
                            printer
    					*/	

    		if (listVector != null && listVector.size() > 0) {
    			for (int i = 0; i < listVector.size(); i++) {
    				
    	            DatabaseRecord objDatabaseRecord = (DatabaseRecord)listVector.get(i);
    	       //     argNames = objDatabaseRecord.getFields();
    	            details.addElement(objDatabaseRecord.get("PO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PO"))));
    	            details.addElement(objDatabaseRecord.get("MATERIAL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MATERIAL"))));
    	            details.addElement(objDatabaseRecord.get("PRINTER") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER"))));
    	            details.addElement(objDatabaseRecord.get("ROLL_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ROLL_NO"))));
    	            details.addElement(objDatabaseRecord.get("QUANTITY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("QUANTITY"))));
    	            details.addElement(objDatabaseRecord.get("CREATED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("CREATED_BY"))));
    	            details.addElement(objDatabaseRecord.get("CREATION_DATE_TIME") == null ? "" : ((Object) ((Date)objDatabaseRecord.get("CREATION_DATE_TIME"))));
    	            
    			}
    		        
    		    
    			}
    		objDTO.setReport_details(details);
	        objDTO.setExportElementVec(argNames);

    		System.out.println("Hi");
    		}
    		    catch(TopLinkException e)
    		    {
    		        AppException appException = new AppException();
    		        appException.performErrorAction("9001", "ReportDAOImpl,getGoodReceiptPaperHistory", e);
    		        throw appException;
    		    }
    		    catch(Exception e)
    		    {
    		        e.printStackTrace();
    		    }
    		    finally
    		    {
    		        if(objSession != null)
    		        {
    		            objSession.release();
    		        }
    		    }
    		    return objDTO;
    } 
    
    
 // DM History popup
    public ExportToExcelDTO getDMHistoryPaperPop(ExportToExcelDTO objDTO)
    throws AppException
    {
    	Vector listVector = null;
    	Vector popUpVector = new Vector();
    	Session objSession = null;
    	Record objRecord = null;
    	Vector argNames = new Vector();
    	argNames.add("PO Number");
    	argNames.add("Material Description ");
    	argNames.add("Printer");    	
        argNames.add("Roll Number");
        argNames.add("Roll Weight");
        argNames.add("DM Posted By");
        argNames.add("DM Posted Date");
        System.out.println(argNames);
        
        String msgID = objDTO.getMsgID();
        String DMGRMode = objDTO.getCuDMGRMode();
     //   String productCode = objDTO.getProductCode();
     //   String lineNo = objDTO.getLineNo();
        
        
        Vector details = new Vector();
    	String msgIdFlag = msgID;
    	try {
    		objSession = getClientSession();
    		
    		
    		if(DMGRMode != null && DMGRMode.equals("DM"))
    		{
    			msgIdFlag = msgID;
    		}
			if(DMGRMode != null && DMGRMode.equals("GR"))
			{
				Vector tempVector = null;
				tempVector = (Vector) objSession.executeSelectingCall(new SQLCall("select msg_id from pix_GOOD_RECEIPT_line where receipt_id = "+ msgID));
				int msgId1 = 0;
				for(int k = 0; k<tempVector.size(); k++)
				{
					objRecord = (Record) tempVector.get(k);
					BigDecimal bMsgId = (BigDecimal) objRecord.get("MSG_ID");
					int tempMsgIdFlag = bMsgId.intValue();
					msgIdFlag = Integer.toString(tempMsgIdFlag);
//					System.out.println(msgId);
//					System.out.println("Hi");  Integer.toString(aInt)
				}

			}

    		
    		
    		
    		
    		listVector = (Vector) objSession
    				.executeSelectingCall(new SQLCall(
    						" select " + 
                            " ppls.po_no po, " +
                            " '(' || TO_NUMBER(ppl.product_code) || ')' ||ppl.product_classif_description  material, " +
                            " pplp.org_unit_code || '-' || pplp.name_1 printer, " +
                            " pdmr.roll_no,  " +
                            " pdmr.quantity,  " +
                            " pdmr.created_by,  " +
                            " pdmr.creation_date_time " + 
                            " from pix_delivery_msg_roll pdmr, " +
                            " pix_delivery_msg_line pdml, " +
                            " pix_po_list_summary ppls, " +
                            " pix_po_line ppl, " +
                            " pix_po_line_party pplp " +
                            " where pdml.msg_id = " + "'"+msgIdFlag+"'" +
                            " and  ppls.po_id = ppl.po_id  " +
                             " and ppls.po_version = ppl.po_version " +
                             " and ppl.po_id = pplp.po_id " +
                             " and ppl.po_line_no = pplp.po_line_no " +
                             " and ppl.po_version  = pplp.po_version " +
                             " and ppl.po_id = pdml.po_id  " +
                             " and ppl.po_line_no = pdml.po_line_no " +
                             " and pdml.msg_id = pdmr.msg_id " +
                             " and pdml.msg_line_no = pdmr.msg_line_no " + 
                             " order by pdmr.roll_no"));
    						
    						
    						/*		" select " + 
    						" ppls.po_no po, " +
    						" '(' || TO_NUMBER(ppl.product_code) || ')' ||ppl.product_classif_description  material, " +
    						" pplp.org_unit_code || '-' || pplp.name_1 printer, " +
    						" pdmr.roll_no,  " +
    						" pdmr.quantity,  " +
    						" pdmr.created_by,  " +
    						" pdmr.creation_date_time " + 
    						" from pix_delivery_msg_roll pdmr, " +
    						" pix_delivery_msg_line pdml, " +
    						" pix_po_list_summary ppls, " +
    						" pix_po_line ppl, " +
    						" pix_po_line_party pplp " +
    						" where pdml.msg_id = " + "'"+msgIdFlag+"'" +
    						" and  ppls.po_id = ppl.po_id  " +
    						 " and ppls.po_version = ppl.po_version " +
    						 " and ppl.po_id = pplp.po_id " +
    						 " and ppl.po_line_no = pplp.po_line_no " +
    						 " and ppl.po_version  = pplp.po_version " +
    						 " and ppl.po_id = pdml.po_id  " +
    						 " and ppl.po_line_no = pdml.po_line_no " +
    						 " and ppl.po_version = pdml.po_version  " +
    						 " and pdml.msg_id = pdmr.msg_id " +
    						 " and pdml.msg_line_no = pdmr.msg_line_no " + 
    						 " order by pdmr.roll_no"));*/

    		if (listVector != null && listVector.size() > 0) {
    			for (int i = 0; i < listVector.size(); i++) {
    				
    	            DatabaseRecord objDatabaseRecord = (DatabaseRecord)listVector.get(i);
    	       //     argNames = objDatabaseRecord.getFields();
    	            details.addElement(objDatabaseRecord.get("PO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PO"))));
    	            details.addElement(objDatabaseRecord.get("MATERIAL") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MATERIAL"))));
    	            details.addElement(objDatabaseRecord.get("PRINTER") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER"))));
    	            details.addElement(objDatabaseRecord.get("ROLL_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("ROLL_NO"))));
    	            details.addElement(objDatabaseRecord.get("QUANTITY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("QUANTITY"))));
    	            details.addElement(objDatabaseRecord.get("CREATED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("CREATED_BY"))));
    	            details.addElement(objDatabaseRecord.get("CREATION_DATE_TIME") == null ? "" : ((Object) ((Date)objDatabaseRecord.get("CREATION_DATE_TIME"))));
    	            
    			}
    		        
    		    
    			}
    		objDTO.setExportElementVec(argNames);
	        objDTO.setReport_details(details);
    		System.out.println("Hi");
    		}
    		    catch(TopLinkException e)
    		    {
    		        AppException appException = new AppException();
    		        appException.performErrorAction("9001", "ReportDAOImpl,getGoodReceiptPaperHistory", e);
    		        throw appException;
    		    }
    		    catch(Exception e)
    		    {
    		        e.printStackTrace();
    		    }
    		    finally
    		    {
    		        if(objSession != null)
    		        {
    		            objSession.release();
    		        }
    		    }
    		    return objDTO;
    } 
    
    
    
    public ExportToExcelDTO getDeliveryMessageNewMill(ExportToExcelDTO objDTO)
    throws AppException
{
    Vector argNames = new Vector();
    Session objSession = null;
    Vector details = new Vector();
    argNames.add("PO LINE#");
    argNames.add("PRINTER");
    argNames.add("L4 MATERIAL DESCRIPTION");
    argNames.add("REQUESTED DEL DATE");
    argNames.add("REQUESTED QTY");
    argNames.add("DM POSTED");
    argNames.add("DM TO BE POSTED");
    
    
    try
    {
        objSession = getClientSession();
        StoredProcedureCall call = new StoredProcedureCall();
        call.setProcedureName("PIX_PAPER_NEW_DM_EXCEL_PROC");       
        call.addNamedArgumentValue("I_PO_ID", objDTO.getPoId());
        call.addNamedArgumentValue("i_po_version", objDTO.getPoVersion()); 
        call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
        call.useNamedCursorOutputAsResultSet("list_refcursor");
        Vector objVector = objSession.executeSelectingCall(call);
        Date deliveryDate;
        
        for(int i = 0; i < objVector.size(); i++)
        {
            DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
      //      argNames = objDatabaseRecord.getFields();
            
            details.addElement(objDatabaseRecord.get("LINE_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("LINE_NO"))));
            details.addElement(objDatabaseRecord.get("PRINTER") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER"))));
            details.addElement(objDatabaseRecord.get("L4_MATERIAL_DESC") == null ? "" : ((Object) ((String)objDatabaseRecord.get("L4_MATERIAL_DESC"))));
            if((deliveryDate = (Date)objDatabaseRecord.get("REQUESTED_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(deliveryDate.toString()));
            } else
            {
                details.addElement("");
            }
            details.addElement(objDatabaseRecord.get("BUYER_REQUESTED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("BUYER_REQUESTED_QTY"))));
            details.addElement(objDatabaseRecord.get("DM_POSTED") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DM_POSTED"))));            
            details.addElement(objDatabaseRecord.get("DM_TO_BE_POSTED") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DM_TO_BE_POSTED"))));            
                     
        }

        objDTO.setExportElementVec(argNames);
        objDTO.setReport_details(details);
    }
    catch(TopLinkException e)
    {
        AppException appException = new AppException();
        appException.performErrorAction("9001", "ReportDAOImpl,getDeliveryMessageNewMill", e);
        throw appException;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        if(objSession != null)
        {
            objSession.release();
        }
    }
    return objDTO;
}
    public ExportToExcelDTO getDeliveryMessageHistory(ExportToExcelDTO objDTO)
    throws AppException
{
    Vector argNames = new Vector();
    Session objSession = null;
    Vector details = new Vector();
    try
    {
        objSession = getClientSession();
        StoredProcedureCall call = new StoredProcedureCall();
        call.setProcedureName("PIX_PAPER_DM_HIST_EXCEL_PROC");       
        call.addNamedArgumentValue("I_PO_ID", objDTO.getPoId());
        call.addNamedArgumentValue("i_po_version", objDTO.getPoVersion());  // 1
        call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
        call.useNamedCursorOutputAsResultSet("list_refcursor");
        Vector objVector = objSession.executeSelectingCall(call);
        Date deliveryDate;
        Date dmPostedDate;
        
        for(int i = 0; i < objVector.size(); i++)
        {
            DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
            argNames = objDatabaseRecord.getFields();
            
            details.addElement(objDatabaseRecord.get("LINE_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("LINE_NO"))));
            details.addElement(objDatabaseRecord.get("MSG_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MSG_NO"))));
            details.addElement(objDatabaseRecord.get("PRINTER") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER"))));
            details.addElement(objDatabaseRecord.get("L4_MATERIAL_DESC") == null ? "" : ((Object) ((String)objDatabaseRecord.get("L4_MATERIAL_DESC"))));
            if((deliveryDate = (Date)objDatabaseRecord.get("REQUESTED_DELIVERY_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(deliveryDate.toString()));
            } else
            {
                details.addElement("");
            }
            if((dmPostedDate = (Date)objDatabaseRecord.get("DM_POSTED_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(dmPostedDate.toString()));
            } else
            {
                details.addElement("");
            }
            details.addElement(objDatabaseRecord.get("BUYER_REQUESTED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("BUYER_REQUESTED_QTY"))));
            details.addElement(objDatabaseRecord.get("TOTAL_DELIVERED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("TOTAL_DELIVERED_QTY"))));
            details.addElement(objDatabaseRecord.get("DELIVERED_QUANTITY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DELIVERED_QUANTITY"))));
            details.addElement(objDatabaseRecord.get("RECEIVED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("RECEIVED_QTY"))));
            details.addElement(objDatabaseRecord.get("APPROVAL_FLAG") == null ? "" : ((Object) ((String)objDatabaseRecord.get("APPROVAL_FLAG"))));
            details.addElement(objDatabaseRecord.get("DM_POSTED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("DM_POSTED_BY"))));
                     
        }

        objDTO.setExportElementVec(argNames);
        objDTO.setReport_details(details);
    }
    catch(TopLinkException e)
    {
        AppException appException = new AppException();
        appException.performErrorAction("9001", "ReportDAOImpl,getDeliveryMessageNewMill", e);
        throw appException;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        if(objSession != null)
        {
            objSession.release();
        }
    }
    return objDTO;
}
    public ExportToExcelDTO getInboxDeliveryMessageHistory(ExportToExcelDTO objDTO)
    throws AppException
{
    Vector argNames = new Vector();
    Session objSession = null;
    Vector details = new Vector();
    try
    {
        objSession = getClientSession();
        StoredProcedureCall call = new StoredProcedureCall();
        call.setProcedureName("PIX_INBOX_MILL_EXCEL_PROC");       
        call.addNamedArgumentValue("I_ORDER_BY ","CREATION_DATE_TIME" );
        call.addNamedArgumentValue("i_SORT", "DESC"); 
        call.addNamedArgumentValue("i_user_id", objDTO.getUserId());
        call.useNamedCursorOutputAsResultSet("list_refcursor");
        Vector objVector = objSession.executeSelectingCall(call);
        Date deliveryDate;
        Date dmPostedDate;
        for(int i = 0; i < objVector.size(); i++)
        {
            DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
            argNames = objDatabaseRecord.getFields();
            
            details.addElement(objDatabaseRecord.get("MSG_NO") == null ? "" : ((Object) ((String)objDatabaseRecord.get("MSG_NO"))));
            details.addElement(objDatabaseRecord.get("LINE_NO") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("LINE_NO"))));
            details.addElement(objDatabaseRecord.get("PRINTER") == null ? "" : ((Object) ((String)objDatabaseRecord.get("PRINTER"))));
            details.addElement(objDatabaseRecord.get("L4_MATERIAL_DESC") == null ? "" : ((Object) ((String)objDatabaseRecord.get("L4_MATERIAL_DESC"))));
            if((deliveryDate = (Date)objDatabaseRecord.get("REQUESTED_DELIVERY_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(deliveryDate.toString()));
            } else
            {
                details.addElement("");
            }
            if((dmPostedDate = (Date)objDatabaseRecord.get("DM_POSTED_DATE")) != null)
            {
                details.addElement(PIXUtil.sqlToStandardDate(dmPostedDate.toString()));
            } else
            {
                details.addElement("");
            }
            details.addElement(objDatabaseRecord.get("BUYER_REQUESTED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("BUYER_REQUESTED_QTY"))));
            details.addElement(objDatabaseRecord.get("TOTAL_DELIVERED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("TOTAL_DELIVERED_QTY"))));
            details.addElement(objDatabaseRecord.get("DELIVERED_QUANTITY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("DELIVERED_QUANTITY"))));
            details.addElement(objDatabaseRecord.get("RECEIVED_QTY") == null ? ((Object) (BigDecimal.valueOf(0L))) : ((Object) ((BigDecimal)objDatabaseRecord.get("RECEIVED_QTY"))));
            details.addElement(objDatabaseRecord.get("APPROVAL_FLAG") == null ? "" : ((Object) ((String)objDatabaseRecord.get("APPROVAL_FLAG"))));
            details.addElement(objDatabaseRecord.get("DM_POSTED_BY") == null ? "" : ((Object) ((String)objDatabaseRecord.get("DM_POSTED_BY"))));
                     
        }

        objDTO.setExportElementVec(argNames);
        objDTO.setReport_details(details);
    }
    catch(TopLinkException e)
    {
        AppException appException = new AppException();
        appException.performErrorAction("9001", "ReportDAOImpl,getDeliveryMessageNewMill", e);
        throw appException;
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        if(objSession != null)
        {
            objSession.release();
        }
    }
    return objDTO;
}

    public ExportToExcelDTO getPotentialARPDetail(ExportToExcelDTO objDTO)
    throws AppException
	{
	    Session objSession = null;
	    Vector details = new Vector();
	    Vector argNames = new Vector();
	    SimpleDateFormat dateObj=new SimpleDateFormat("MM/dd/yyyy");
	    try
	    {
	        objSession = getClientSession();
	        StoredProcedureCall call = new StoredProcedureCall();
	        call.setProcedureName("PIX_ARP_EXCL_GENRT_PROC");
	        call.addNamedArgumentValue("I_INPUT_STRING", objDTO.getInputString());
	        call.addNamedArgumentValue("I_STAT_CD", objDTO.getStatusCode());
	        call.addNamedArgumentValue("I_USER_ID", objDTO.getUserId());
	        call.addNamedArgumentValue("I_PAGENAT_NBR", "1");
	        call.useNamedCursorOutputAsResultSet("O_REF_CURSOR");
	        Vector objVector = objSession.executeSelectingCall(call);
	        for(int i = 0; i < objVector.size(); i++)
	        {
	            DatabaseRecord objDatabaseRecord = (DatabaseRecord)objVector.get(i);
	            argNames = objDatabaseRecord.getFields();
	            
	            details.addElement(objDatabaseRecord.get(PIXConstants.ISBN_NUMBER) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.ISBN_NUMBER))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.PRINTING_NO) == null ? "" : ((Object) ((BigDecimal)objDatabaseRecord.get(PIXConstants.PRINTING_NO))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.TITLE_DESCRIPTION) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.TITLE_DESCRIPTION))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.AUTHOR) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.AUTHOR))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.EDITION) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.EDITION))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.BUYER_NAME) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.BUYER_NAME))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.INVENT_REQUEST_DATE) == null ? "" : ((Object)dateObj.format((Date)objDatabaseRecord.get(PIXConstants.INVENT_REQUEST_DATE))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.INVENT_DUE_DATE) == null ? "" : ((Object)dateObj.format((Date)objDatabaseRecord.get(PIXConstants.INVENT_DUE_DATE))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.BUYER_PAGE_COUNT) == null ? "" : ((Object) ((BigDecimal)objDatabaseRecord.get(PIXConstants.BUYER_PAGE_COUNT))));	            
	            details.addElement(objDatabaseRecord.get(PIXConstants.VENDOR_PAGE_COUNT) == null ? "" : ((Object) ((BigDecimal)objDatabaseRecord.get(PIXConstants.VENDOR_PAGE_COUNT))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.PROOF_REQUESTED) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.PROOF_REQUESTED))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.PROOF_PROVIDED) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.PROOF_PROVIDED))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.PROOF_TYPE) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.PROOF_TYPE))));
                details.addElement(objDatabaseRecord.get(PIXConstants.VENDOR_COMMENTS) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.VENDOR_COMMENTS))));
	            details.addElement(objDatabaseRecord.get(PIXConstants.BUYER_COMMENTS) == null ? "" : ((Object) ((String)objDatabaseRecord.get(PIXConstants.BUYER_COMMENTS))));
	        }
	
	        objDTO.setExportElementVec(argNames);
	        objDTO.setReport_details(details);
	    }
	    catch(TopLinkException e)
	    {
	        AppException appException = new AppException();
	        appException.performErrorAction("9001", "ReportDAOImpl,getReportList", e);
	    }
	    finally
	    {
	        if(objSession != null)
	        {
	            objSession.release();
	        }
	    }
	    return objDTO;
	}

    
    static 
    {
        log = LogFactory.getLog(com.pearson.pix.dao.orderstatus.OrderStatusDAOImpl.class.getName());
    }
}
