package com.pearson.pix.presentation.dropship.dropshipinstruction.action;
import java.util.ArrayList;
import java.util.List;

import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.dropship.DropInstDTO;
import com.pearson.pix.presentation.base.action.BaseForm;
/**
 * 
 * @author vishal sinha
 */
public class DropInstForm extends BaseForm{
	public static final long serialVersionUID = 001011L;
	    
	   
	
	public DropInstForm(){
    }
	
	DropInstDTO dropshipdto;
	List<DropInstDTO> dropshipdtolist;
	private Integer[] checkIndex;
	private String checkIndexStr;
	private String startDate = null;
	private String endDate = null;
	private String quickfindSelectbox;
	private String quickfindTxtbox;
	private List<DropInstDTO> status;
	private Integer statusId = null;
	private String statusDescription = null;
	private String bkNumber = null;
	private String docCtrlNo = null;
	private String isbnNo = null;
	private String custAccountName = null;
	private String poNumber = null;
	private String carrierLevel = null;
	private Integer totalQty = null;
	private String printNo = null;
	private String statusDetail = null;
	private Integer userId= null;
	private User user;
	 private String activeFlag;
	  
	
	
	// private String[] checkIndex;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}
	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the statusDetail
	 */
	public String getStatusDetail() {
		return statusDetail;
	}
	/**
	 * @param statusDetail the statusDetail to set
	 */
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}
	/**
	 * @return the printNo
	 */
	public String getPrintNo() {
		return printNo;
	}
	/**
	 * @param printNo the printNo to set
	 */
	public void setPrintNo(String printNo) {
		this.printNo = printNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer[] getCheckIndex() {
		return checkIndex;
	}
	public void setCheckIndex(Integer[] checkIndex) {
		this.checkIndex = checkIndex;
	}
	/**
	 * @return the dropshipdto
	 */
	public DropInstDTO getDropshipdto() {
		return this.dropshipdto;
	}
	/**
	 * @param dropshipdto the dropshipdto to set
	 */
	public void setDropshipdto(DropInstDTO dropshipdto) {
		this.dropshipdto = dropshipdto;
	}
	
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
		
	}
	public String getCheckIndexStr() {
		return checkIndexStr;
	}
	public void setCheckIndexStr(String checkIndexStr) {
		this.checkIndexStr = checkIndexStr;
	}
	public List<DropInstDTO> getDropshipdtolist() {
		return dropshipdtolist;
	}
	public void setDropshipdtolist(List<DropInstDTO> dropshipdtolist) {
		this.dropshipdtolist = dropshipdtolist;
	}
	public String getBkNumber() {
		return bkNumber;
	}
	public void setBkNumber(String bkNumber) {
		this.bkNumber = bkNumber;
	}
	public String getDocCtrlNo() {
		return docCtrlNo;
	}
	public void setDocCtrlNo(String docCtrlNo) {
		this.docCtrlNo = docCtrlNo;
	}
	public String getIsbnNo() {
		return isbnNo;
	}
	public void setIsbnNo(String isbnNo) {
		this.isbnNo = isbnNo;
	}
	public String getCustAccountName() {
		return custAccountName;
	}
	public void setCustAccountName(String custAccountName) {
		this.custAccountName = custAccountName;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getCarrierLevel() {
		return carrierLevel;
	}
	public void setCarrierLevel(String carrierLevel) {
		this.carrierLevel = carrierLevel;
	}
	
	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}
	public Integer getTotalQty() {
		return totalQty;
	}
	public String getQuickfindSelectbox() {
		return quickfindSelectbox;
	}
	public void setQuickfindSelectbox(String quickfindSelectbox) {
		this.quickfindSelectbox = quickfindSelectbox;
	}
	public String getQuickfindTxtbox() {
		return quickfindTxtbox;
	}
	public void setQuickfindTxtbox(String quickfindTxtbox) {
		this.quickfindTxtbox = quickfindTxtbox;
	}
	////////////
	/**
	 * @return the statusId
	 */
	/*public String getStatusId() {
		return statusId;
	}
	*//**
	 * @param statusId the statusId to set
	 *//*
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}*/
	/**
	 * @return the status
	 *//*
	public String getStatus() {
		return status;
	}
	*//**
	 * @param status the status to set
	 *//*
	public void setStatus(String status) {
		this.status = status;
	}*/
	/**
	 * @return the statusId
	 */
	public Integer getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}
	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	/**
	 * @return the status
	 */
	public List<DropInstDTO> getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(List<DropInstDTO> status) {
		this.status = status;
	}
	
		
		  
	  }





