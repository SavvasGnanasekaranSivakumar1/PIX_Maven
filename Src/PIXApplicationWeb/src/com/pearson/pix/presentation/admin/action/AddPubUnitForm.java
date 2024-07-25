package com.pearson.pix.presentation.admin.action;


import java.util.Vector;


import com.pearson.pix.dto.admin.Party;
import com.pearson.pix.presentation.base.action.BaseForm;


/*
 * Form bean defined
 * @author shweta.g
 */
public class AddPubUnitForm extends BaseForm
{
	private static final long serialVersionUID = 1L;
	private Party party;
	private String startDate;
	private String endDate;
	private Vector pubUnitListCollection;
	
	public final void reset
		(org.apache.struts.action.ActionMapping mapping,
	    javax.servlet.http.HttpServletRequest request)
	{
		this.party = new Party();
	}
	
	/**
     * Gets party 
     * @return Party
     */
	
	public Party getParty()
	{
		return this.party;
	}
	 
	/**
     * Sets party
     * @param Party
     */
	
	public void setParty(Party party)
	{
		this.party = party;
	}
	
	
	/**
     * Gets pubUnitListCollection
     * @return java.util.Collection
     */
	
  	public Vector getPubUnitListCollection() {
  		return pubUnitListCollection;
  	}
  	
  	/**
     * Sets pubUnitListCollection
     * @param pubUnitListCollection
     */
  	public void setPubUnitListCollection(Vector pubUnitListCollection) {
  		this.pubUnitListCollection = pubUnitListCollection;
  	}
	 
  	
  	/**
     * Gets startDate
     * @return java.util.Date
     */
  	public String getStartDate() {
  		return startDate;
  	}
  	
  	/**
     * Sets startDate
     * @param startDate
     */
  	public void setStartDate(String startDate) {
  		this.startDate = startDate;
  	}
  	
  	/**
     * Gets endDate
     * @return java.util.Date
     */
  	public String getEndDate() {
  		return endDate;
  	}
  	
  	/**
     * Sets endDate
     * @param endDate
     */
  	public void setEndDate(String endDate) {
  		this.endDate = endDate;
  	}
  	
}
