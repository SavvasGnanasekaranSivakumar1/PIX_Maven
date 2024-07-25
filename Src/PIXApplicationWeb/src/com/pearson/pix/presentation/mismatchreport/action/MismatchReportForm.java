/**
 * 
 */
package com.pearson.pix.presentation.mismatchreport.action;

import java.math.BigDecimal;
import java.util.Vector;
import com.pearson.pix.presentation.base.action.BaseForm;


public class MismatchReportForm extends BaseForm {
	
	
	private String poNo;
	
	private Vector mismatchReportDetails;
	
	private String merchantName;
	
	private String materialNo;
	
	private BigDecimal quantity;
	
	private String buttonName; 
	
	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public Vector getMismatchReportDetails() {
		return mismatchReportDetails;
	}

	public void setMismatchReportDetails(Vector mismatchReportDetails) {
		this.mismatchReportDetails = mismatchReportDetails;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
