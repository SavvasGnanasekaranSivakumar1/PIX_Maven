package com.pearson.pix.presentation.deliverymessage.action;


import java.math.BigDecimal;
import java.util.Collection;


import org.apache.struts.action.ActionForm;

import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.presentation.base.action.BaseForm;

public class DeliveryMessageForm extends BaseForm{
	private static final long serialVersionUID = 1L;
	 private DeliveryMessage objDeliveryMessage;
	 private DeliveryMessageLine deliverymessageLine;
	 private Collection deliverymessageCollection;
	 private String quantity;
	 private String estimatedDate;
	 private String messageType;
	 private String selectedEntry;
	 private BigDecimal poId;
	 private BigDecimal poVersion;
	 private String san ;
	 private String stat;
	 
	 
	public String getSan() {
		return san;
	}
	
	public void setSan(String san) {
		this.san = san;
	}
	
	public String getSelectedEntry() {
		return selectedEntry;
	}
	public void setSelectedEntry(String selectedEntry) {
		this.selectedEntry = selectedEntry;
	}
	public final void reset
	       (org.apache.struts.action.ActionMapping mapping,
	       javax.servlet.http.HttpServletRequest request)
	   {
	      // this.objDeliveryMessage = new DeliveryMessage();
	        this.deliverymessageLine = new DeliveryMessageLine();
	   }
	   public DeliveryMessage getDeliveryMessage()
	    {
	         return this.objDeliveryMessage;
	    }
	 	  
	    public void setDeliveryMessage(DeliveryMessage objDeliveryMessage)
	    {
	         this.objDeliveryMessage = objDeliveryMessage;
	    }
	    
	    public DeliveryMessageLine getDeliveryMessageLine()
	    {
	         return this.deliverymessageLine;
	    }
	 	  
	    public void setDeliveryMessageLine(DeliveryMessageLine deliverymessageLine)
	    {
	         this.deliverymessageLine = deliverymessageLine;
	    }
	    
	    
	    /**
	     * Gets deliveryMsgLineCollection
	     * @return java.util.Collection
	     */
	  	public Collection getDeliverymessageCollection() {
	  		return deliverymessageCollection;
	  	}
	  	/**
	      * Sets deliveryMsgLineCollection
	      * @param deliveryMsgLineCollection
	    */
	  	public void setDeliverymessageCollection(Collection deliverymessageCollection) {
	  		this.deliverymessageCollection = deliverymessageCollection;
	  	}
		public String getEstimatedDate() {
			return estimatedDate;
		}
		public void setEstimatedDate(String estimatedDate) {
			this.estimatedDate = estimatedDate;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public String getMessageType() {
			return messageType;
		}
		public void setMessageType(String messageType) {
			this.messageType = messageType;
		}
		public BigDecimal getPoId() {
			return poId;
		}
		public void setPoId(BigDecimal poId) {
			
			this.poId = poId;
		}
		public BigDecimal getPoVersion() {
			return poVersion;
		}
		public void setPoVersion(BigDecimal poVersion) {
			this.poVersion = poVersion;
		}
		
       
		public String getStat() {
			return stat;
		}
		public void setStat(String stat) {
			
			this.stat = stat;
		}
		
}
