package com.pearson.pix.dto.mismatchreport;

import java.io.Serializable;
import java.math.BigDecimal;

public class MismatchDetailsDTO implements Serializable {
	
	
	private String poNumber;
	private String merchantName;
	private String sapPlantCode;
	private String materialNo;
	private BigDecimal requestedQty;
	private BigDecimal poLineNo;
	private BigDecimal dmQty;
	private String dmDoc;
	private BigDecimal grQty;
	private String grDoc;
	private BigDecimal varianceQty;
	
	public String getDmDoc() {
		return dmDoc;
	}
	public void setDmDoc(String dmDoc) {
		this.dmDoc = dmDoc;
	}
	public BigDecimal getDmQty() {
		return dmQty;
	}
	public void setDmQty(BigDecimal dmQty) {
		this.dmQty = dmQty;
	}
	public String getGrDoc() {
		return grDoc;
	}
	public void setGrDoc(String grDoc) {
		this.grDoc = grDoc;
	}
	public BigDecimal getGrQty() {
		return grQty;
	}
	public void setGrQty(BigDecimal grQty) {
		this.grQty = grQty;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public BigDecimal getPoLineNo() {
		return poLineNo;
	}
	public void setPoLineNo(BigDecimal poLineNo) {
		this.poLineNo = poLineNo;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public BigDecimal getRequestedQty() {
		return requestedQty;
	}
	public void setRequestedQty(BigDecimal decimal) {
		this.requestedQty = decimal;
	}
	public String getSapPlantCode() {
		return sapPlantCode;
	}
	public void setSapPlantCode(String sapPlantCode) {
		this.sapPlantCode = sapPlantCode;
	}
	public BigDecimal getVarianceQty() {
		return varianceQty;
	}
	public void setVarianceQty(BigDecimal varianceQty) {
		this.varianceQty = varianceQty;
	}

}
