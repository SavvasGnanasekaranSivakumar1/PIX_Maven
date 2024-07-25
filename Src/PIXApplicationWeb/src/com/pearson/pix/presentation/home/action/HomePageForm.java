package com.pearson.pix.presentation.home.action;



import java.util.Collection;
import com.pearson.pix.dto.common.Inbox;
import com.pearson.pix.presentation.base.action.BaseForm;


public class HomePageForm extends BaseForm{
		
	private String moduleDescription;
	private String moduleCode;
	private String isbn;
	private String printno;
	private String pono;
	private String jobno;
	private String statusDescription;
	private Integer statusId;
	private Collection modCollection;
	private Inbox inbox;
	
//	private String applicationSwitch;
	
	public final void reset
    (org.apache.struts.action.ActionMapping mapping,
    javax.servlet.http.HttpServletRequest request)
 {
	if(this.inbox==null)
	{
		this.inbox = new Inbox();
	}
    
 }
	/**
	  * Gets inbox
	  * return inbox
	  */
	
	public Inbox getInbox() {
		return inbox;
	}
	
	/**
	  * Sets inbox
	  * @param inbox
	  */
	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}
	
	/**
	  * Gets isbn
	  * return isbn
	  */
	
	public String getIsbn() {
		return isbn;
	}
	
	/**
	  * Sets isbn
	  * @param isbn
	*/
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	/**
	  * Gets modcollection
	  * return modcollection
	  */
	
	public Collection getModCollection() {
		return modCollection;
	}
	
	/**
	  * Sets modcollection
	  * @param modcollection
	*/
	public void setModCollection(Collection modCollection) {
		this.modCollection = modCollection;
	}
	
	/**
	  * Gets modulecode
	  * return modulecode
	  */
	public String getModuleCode() {
		return moduleCode;
	}
	
	/**
	  * Sets modulecode
	  * @param modulecode
	*/
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	

	/**
	  * Gets moduledescription
	  * return moduledescription
	  */
	public String getModuleDescription() {
		return moduleDescription;
	}
	
	/**
	  * Sets moduledescription
	  * @param moduledescription
	*/
	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	/**
	  * Gets pono
	  * return pono
	  */
	public String getPono() {
		return pono;
	}
	
	/**
	  * Sets pono
	  * @param pono
	*/
	public void setPono(String pono) {
		this.pono = pono;
	}
	
	/**
	  * Gets printno
	  * return printno
	  */
	public String getPrintno() {
		return printno;
	}
	
	/**
	  * Sets printno
	  * @param printno
	*/
	public void setPrintno(String printno) {
		this.printno = printno;
	}
	
	/**
	  * Gets statusId
	  * return statusId
	  */
	public Integer getStatusId() {
		return statusId;
	}
	
	/**
	  * Sets statusId
	  * @param statusId
	*/
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
	/**
	  * Gets statusDescription
	  * return statusDescription
	  */
	public String getStatusDescription() {
		return statusDescription;
	}
	
	/**
	  * Sets statusDescription
	  * @param statusDescription
	*/
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public String getJobno() {
		return jobno;
	}
	public void setJobno(String jobno) {
		this.jobno = jobno;
	}
		
}
