package com.pearson.pix.presentation.costaccounting.action;


import java.util.Collection;
import java.util.Vector;


import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.presentation.base.action.BaseForm;

public class CostAccountingForm extends BaseForm{
	private static final long serialVersionUID = 1L;
	
	private Collection deliverymessageCollection;
	private String selectedEntry;
	private String poNo;
	private String materialNo;
	private String deliverymessageNo;
	private String millMerchant;
	private String status;
	private String printer;
	private String startDate;
	private String endDate;
	
	private DeliveryMessage objDeliveryMessage;
	private DeliveryMessageLine deliverymessageLine;
	
	private Vector millMerchantVector;
	private Vector statusVector;

	private Vector cuPopVector;
	private int totalDelRollWeight;
	private int totalRecRollWeight;
	
	public final void reset
    (org.apache.struts.action.ActionMapping mapping,
    javax.servlet.http.HttpServletRequest request)
	{
		if(objDeliveryMessage==null)
		{
			objDeliveryMessage = new DeliveryMessage();
			
		}
		
	}
	public Collection getDeliverymessageCollection() {
		return deliverymessageCollection;
	}

	public void setDeliverymessageCollection(Collection deliverymessageCollection) {
		this.deliverymessageCollection = deliverymessageCollection;
	}
	public String getSelectedEntry() {
		return selectedEntry;
	}
	public void setSelectedEntry(String selectedEntry) {
		this.selectedEntry = selectedEntry;
	}
	public String getDeliverymessageNo() {
		return deliverymessageNo;
	}
	public void setDeliverymessageNo(String deliverymessageNo) {
		this.deliverymessageNo = deliverymessageNo;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getMillMerchant() {
		return millMerchant;
	}
	public void setMillMerchant(String millMerchant) {
		this.millMerchant = millMerchant;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getPrinter() {
		return printer;
	}
	public void setPrinter(String printer) {
		this.printer = printer;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Vector getMillMerchantVector() {
		return millMerchantVector;
	}
	public void setMillMerchantVector(Vector millMerchantVector) {
		this.millMerchantVector = millMerchantVector;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Vector getStatusVector() {
		return statusVector;
	}
	public void setStatusVector(Vector statusVector) {
		this.statusVector = statusVector;
	}
	public Vector getCuPopVector() {
		return cuPopVector;
	}
	public void setCuPopVector(Vector cuPopVector) {
		this.cuPopVector = cuPopVector;
	}
	public int getTotalDelRollWeight() {
		return totalDelRollWeight;
	}
	public void setTotalDelRollWeight(int totalDelRollWeight) {
		this.totalDelRollWeight = totalDelRollWeight;
	}
	public int getTotalRecRollWeight() {
		return totalRecRollWeight;
	}
	public void setTotalRecRollWeight(int totalRecRollWeight) {
		this.totalRecRollWeight = totalRecRollWeight;
	}
	
		
}
