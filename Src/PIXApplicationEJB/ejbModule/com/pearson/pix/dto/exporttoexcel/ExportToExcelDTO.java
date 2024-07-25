package com.pearson.pix.dto.exporttoexcel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Vector;

public class ExportToExcelDTO
    implements Serializable
{

    private static final long serialVersionUID = 1L;
    private String isbn;
    private String porderNo;
    private String sdate;
    private String edate;
    private String printNo;
    private String item;
    private String orderBy;
    private String sort;
    private String userId;
    private String pagination;
    private Vector report_details;
    private Vector ExportElementVec;
    private String inputString;
    private String pageNo;
    private String orderType;
    private String roleType;
    private BigDecimal poId;
    private BigDecimal poVersion;
    private Integer poid;
    private Integer poversion;
    private String statusCode;

    private String pono;
    private String productCode;
    private String lineNo;
    private String dmGRMode;
    
    private String cuOwnershipMode;
    private String cuPono;
    private String cuMsgId;
    
    private String msgID;
    private String cuDMGRMode;
    
    public String getCuDMGRMode() {
		return cuDMGRMode;
	}

	public void setCuDMGRMode(String cuDMGRMode) {
		this.cuDMGRMode = cuDMGRMode;
	}

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public ExportToExcelDTO()
    {
    }

    public Integer getPoid()
    {
        return poid;
    }

    public void setPoid(Integer poid)
    {
        this.poid = poid;
    }

    public Integer getPoversion()
    {
        return poversion;
    }

    public void setPoversion(Integer poversion)
    {
        this.poversion = poversion;
    }

    public BigDecimal getPoId()
    {
        return poId;
    }

    public void setPoId(BigDecimal poId)
    {
        this.poId = poId;
    }

    public BigDecimal getPoVersion()
    {
        return poVersion;
    }

    public void setPoVersion(BigDecimal poVersion)
    {
        this.poVersion = poVersion;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public Vector getExportElementVec()
    {
        return ExportElementVec;
    }

    public void setExportElementVec(Vector exportElementVec)
    {
        ExportElementVec = exportElementVec;
    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    public String getPagination()
    {
        return pagination;
    }

    public void setPagination(String pagination)
    {
        this.pagination = pagination;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getRoleType()
    {
        return roleType;
    }

    public void setRoleType(String roleType)
    {
        this.roleType = roleType;
    }

    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    public String getPorderNo()
    {
        return porderNo;
    }

    public void setPorderNo(String porderNo)
    {
        this.porderNo = porderNo;
    }

    public String getPrintNo()
    {
        return printNo;
    }

    public void setPrintNo(String printNo)
    {
        this.printNo = printNo;
    }

    public Vector getReport_details()
    {
        return report_details;
    }

    public void setReport_details(Vector report_details)
    {
        this.report_details = report_details;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getInputString()
    {
        return inputString;
    }

    public void setInputString(String inputString)
    {
        this.inputString = inputString;
    }

    public String getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(String pageNo)
    {
        this.pageNo = pageNo;
    }

    public String getsdate()
    {
        return sdate;
    }

    public void setsdate(String sdate)
    {
        this.sdate = sdate;
    }

    public String getedate()
    {
        return edate;
    }

    public void setedate(String edate)
    {
        this.edate = edate;
    }

	public String getPono() {
		return pono;
	}

	public void setPono(String pono) {
		this.pono = pono;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getCuOwnershipMode() {
		return cuOwnershipMode;
	}

	public void setCuOwnershipMode(String cuOwnershipMode) {
		this.cuOwnershipMode = cuOwnershipMode;
	}

	public String getCuPono() {
		return cuPono;
	}

	public void setCuPono(String cuPono) {
		this.cuPono = cuPono;
	}

	public String getCuMsgId() {
		return cuMsgId;
	}

	public void setCuMsgId(String cuMsgId) {
		this.cuMsgId = cuMsgId;
	}

	public String getDmGRMode() {
		return dmGRMode;
	}

	public void setDmGRMode(String dmGRMode) {
		this.dmGRMode = dmGRMode;
	}

}
