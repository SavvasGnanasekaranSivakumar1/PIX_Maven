package com.pearson.pix.presentation.goodsreceipt.action;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;

import org.apache.struts.action.ActionForm;


import com.pearson.pix.dto.goodsreceipt.GoodsReceipt;
import com.pearson.pix.dto.goodsreceipt.GoodsReceiptLine;
import com.pearson.pix.presentation.base.action.BaseForm;



public class GoodsReceiptForm extends BaseForm{
	private static final long serialVersionUID = 1L;
	
	private GoodsReceipt goodsreceipt;
	private GoodsReceiptLine goodsreceiptLine;
    private Collection goodsreceiptCollection;
    private String selectedEntry;
	 private BigDecimal poId;
	 private BigDecimal poVersion;
	private Vector dmDtlList; 
	private int totalRollWeight;
	private int totalDelRollWeight;
	private int totalRecRollWeight;
	
	
    public int getTotalRollWeight() {
		return totalRollWeight;
	}
	public void setTotalRollWeight(int totalRollWeight) {
		this.totalRollWeight = totalRollWeight;
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
	public final void reset
    (org.apache.struts.action.ActionMapping mapping,
    javax.servlet.http.HttpServletRequest request)
    {
    	//this.goodsreceipt = new GoodsReceipt();
       this.goodsreceiptLine = new GoodsReceiptLine();
    }
  	public Collection getGoodsreceiptCollection() {
		return goodsreceiptCollection;
	}
	public void setGoodsreceiptCollection(Collection goodsreceiptCollection) {
		this.goodsreceiptCollection = goodsreceiptCollection;
	}
	public GoodsReceiptLine getGoodsreceiptLine() {
		return goodsreceiptLine;
	}
	public void setGoodsreceiptLine(GoodsReceiptLine goodsreceiptLine) {
		this.goodsreceiptLine = goodsreceiptLine;
	}
	public GoodsReceipt getGoodsreceipt() {
		return goodsreceipt;
	}
	public void setGoodsreceipt(GoodsReceipt goodsreceipt) {
		this.goodsreceipt = goodsreceipt;
	}
	public String getSelectedEntry() {
		return selectedEntry;
	}
	public void setSelectedEntry(String selectedEntry) {
		this.selectedEntry = selectedEntry;
	}
	public Vector getDmDtlList() {
		return dmDtlList;
	}
	public void setDmDtlList(Vector dmDtlList) {
		this.dmDtlList = dmDtlList;
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
