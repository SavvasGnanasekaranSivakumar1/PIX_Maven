package com.pearson.pix.dto.orderstatus;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

/**
* Contains Order Status Information Detail 
* @author Dandu Thirupathi
*/
public class OrderStatus implements Serializable {
	  
    /**
	* Collection of line items in OrderStatus
	*/
	private Collection lineItemCollection;
    
	private String statusNo;
	
  /**
  * Constructor of the OrderStatus
  */
	public OrderStatus() {
		super();
		this.lineItemCollection = new Vector();
	}
	 
	/**
	 * Gets lineItemCollection
	 * @return java.util.Collection
	*/
	public Collection getLineItemCollection() {
		return this.lineItemCollection;
	}
	
	/**
	* Gets OrderStatusLine for OderStatus
	* @return com.pearson.pix.dto.orderstatus.OrderStatus
	*/
	public OrderStatusLine getLineItemCollectionIdx(int index) {
		return (OrderStatusLine)((Vector)this.lineItemCollection).get(index);
	}
	
	
    /**
    * Sets lineItemCollection
    * @param java.util.Collection
    */
	public void setLineItemCollection(Collection lineItemCollection) {
		this.lineItemCollection = lineItemCollection;
	}
	
	/**
    * Sets OrderStatusLine with index
    * @param int
    * @param com.pearson.pix.dto.orderstatus.OrderStatusLine
    */
	public void setLineItemCollectionIdx(int index, OrderStatusLine orderStatusline) {
		((Vector)this.lineItemCollection).set(index, orderStatusline);			
	}
	
	/**
	* Gets statusNo
	* @return java.lang.String
	*/
	public String getStatusNo() {
		return this.statusNo;
	}
		
	/**
	* Sets statusNo
	* @param java.lang.String
	*/
	public void setStatusNo(String statusNo) {
		this.statusNo = statusNo;
	}
}
