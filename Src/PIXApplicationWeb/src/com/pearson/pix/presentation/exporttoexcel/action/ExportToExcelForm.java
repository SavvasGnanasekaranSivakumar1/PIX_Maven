package com.pearson.pix.presentation.exporttoexcel.action;

import java.util.Vector;
import com.pearson.pix.presentation.base.action.BaseForm;

/**
* Contains the ExportToExcel Information
* @author Ajay Jain
*/
public class ExportToExcelForm extends BaseForm{
	
	/*
	 * default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/* 
	 * Variables required to set or get values
	 * for generating excel from
	 * report.
	 */
	private String isbn;
	private String porderNo;
	private String printNo;
	private String item;
	private String orderBy;
	private String sort;
	private String userId;
	private String pagination;
	private Vector report_details;
	private Vector ExportElementVec;
	
	/*
	 * Variables required to set or get values
	 * for generating excel from
	 * planning.
	 */
	private String inputString;
	private String pageNo;
	
	/* 
	 * Variables required to set or get values
	 * for generating excel from
	 * purchase.
	 */
	private String orderType;
	
	/*
	 * setter and getters methods
	 * for above declared variables
	 * generated by using 
	 * Eclipse Property of generating setters and getters.
	 */
	public Vector getExportElementVec() {
		return ExportElementVec;
	}
	public void setExportElementVec(Vector exportElementVec) {
		ExportElementVec = exportElementVec;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getPorderNo() {
		return porderNo;
	}
	public void setPorderNo(String porderNo) {
		this.porderNo = porderNo;
	}
	public String getPrintNo() {
		return printNo;
	}
	public void setPrintNo(String printNo) {
		this.printNo = printNo;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getPagination() {
		return pagination;
	}
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Vector getReport_details() {
		return report_details;
	}
	public void setReport_details(Vector report_details) {
		this.report_details = report_details;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getInputString() {
		return inputString;
	}
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	
}
