package com.pearson.pix.presentation.orderstatus.action;


import java.util.Vector;

import com.pearson.pix.dto.orderstatus.OrderStatus;
import com.pearson.pix.dto.orderstatus.OrderStatusLine;
import com.pearson.pix.presentation.base.action.BaseForm;

/**
 * The OrderStatus Form 
 * @author Dandu Thirupathi
 */

public class OrderStatusForm extends BaseForm{
	private OrderStatusLine orderStatusLine;
	private OrderStatus orderStatus;
	private Vector orderStatusCollection;
	private String selectedEntry;
	private String poNo;
	private String releaseNo;
	private String statusNo;
		 
	public final void reset(org.apache.struts.action.ActionMapping mapping, javax.servlet.http.HttpServletRequest request) {
		//this.orderStatus=new OrderStatus();
	    //this.orderStatusLine = new OrderStatusLine();
	}
	
	/**
     * Gets OrderStatus 
     * @return OrderStatus
     */
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}
	 
	/**
     * Sets OrderStatus 
     * @param OrderStatus
     */
    public void setOrderStatus(OrderStatus orderStatus) {
	   this.orderStatus = orderStatus;
	}
    
    /**
     * Gets OrderStatusLine 
     * @return OrderStatusLine
     */
    public OrderStatusLine getOrderStatusLine() {
    	return this.orderStatusLine;
	}
	 	
    /**
     * Sets OrderStatusLine 
     * @param OrderStatusLine
     */
    public void setOrderStatusLine(OrderStatusLine orderStatusLine) {
	   this.orderStatusLine = orderStatusLine;
	}
    
    /**
     * Gets OrderStatusCollection 
     * @return java.util.Collection
     */
  	public Vector getOrderStatusCollection() {
  		return orderStatusCollection;
	}
  	
	/**
	 * Sets OrderStatusCollection
	 * @param java.util.Vector
	 */
  	public void setOrderStatusCollection(Vector orderStatusCollection) {
	  	this.orderStatusCollection = orderStatusCollection;
	}
  	
  	/**
	 * Gets selectedEntry
	 * @return java.lang.String
	 */
  	public String getSelectedEntry() {
  		return selectedEntry;
	}
  	
  	/**
	 * Sets selectedEntry
	 * @param java.lang.String
	 */
	public void setSelectedEntry(String selectedEntry) {
		this.selectedEntry = selectedEntry;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getReleaseNo() {
		return releaseNo;
	}

	public void setReleaseNo(String releaseNo) {
		this.releaseNo = releaseNo;
	}

	public String getStatusNo() {
		return statusNo;
	}

	public void setStatusNo(String statusNo) {
		this.statusNo = statusNo;
	}
}
