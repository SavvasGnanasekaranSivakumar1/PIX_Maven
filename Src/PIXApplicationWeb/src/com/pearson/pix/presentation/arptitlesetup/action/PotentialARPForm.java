package com.pearson.pix.presentation.arptitlesetup.action;

import java.util.Collection;
import java.util.List;

import com.pearson.pix.dto.arptitlesetup.PotentialARP;
import com.pearson.pix.presentation.base.action.BaseForm;
import com.pearson.pix.dto.arptitlesetup.ArpSetUpResult;
import com.pearson.pix.dto.common.Status;
/**
 * @author arvind.yadav
 * 
 */
public class PotentialARPForm extends BaseForm {

	public static final long serialVersionUID = 001011L;
	
	private Collection potentialARPCollection;
	
	private Status statusARP;
	
	private PotentialARP detailArp;
	
	private String status;
    private String edition;
    private String copyRightYear;  
    private String vendorPageCount;
    private String isReviewRequested;
    private String isReviewProvide;
    private String comments;
    private String arpFromDueDate;
	private String arpToDueDate;
	private String fromReqDate;
	private String statusMessage;
	private String toReqDate;
	private String mode;
	private boolean resultsPopUp;
	private String finalStr;
	private Integer statusId; 
	
	private List<ArpSetUpResult> resultListSize; 

    private String selectedEntry;


	public final void reset(org.apache.struts.action.ActionMapping mapping,
			javax.servlet.http.HttpServletRequest request) {
		this.detailArp = new PotentialARP();  
		this.statusARP = new Status();  
	}


	/**
	 * Gets selectedEntry
	 * 
	 * @return selectedEntry
	 */

	public String getSelectedEntry() {
		return selectedEntry;
	}

	/**
	 * Sets selectedEntry
	 * 
	 * @param selectedEntry
	 */
	public void setSelectedEntry(String selectedEntry) {
		this.selectedEntry = selectedEntry;
	}

	public Collection getPotentialARPCollection() {
		return potentialARPCollection;
	}

	public void setPotentialARPCollection(Collection potentialARPCollection) {
		this.potentialARPCollection = potentialARPCollection;
	}


	/**
	 * @return the detailArp
	 */
	public PotentialARP getDetailArp() {
		return detailArp;
	}

	/**
	 * @param detailArp the detailArp to set
	 */
	public void setDetailArp(PotentialARP detailArp) {
		this.detailArp = detailArp;
	}


	/**
	 * @return the arpFromDueDate
	 */
	public String getArpFromDueDate() {
		return arpFromDueDate;
	}


	/**
	 * @param arpFromDueDate the arpFromDueDate to set
	 */
	public void setArpFromDueDate(String arpFromDueDate) {
		this.arpFromDueDate = arpFromDueDate;
	}


	/**
	 * @return the arpToDueDate
	 */
	public String getArpToDueDate() {
		return arpToDueDate;
	}


	/**
	 * @param arpToDueDate the arpToDueDate to set
	 */
	public void setArpToDueDate(String arpToDueDate) {
		this.arpToDueDate = arpToDueDate;
	}

	/**
	 * @return the fromReqDate
	 */
	public String getFromReqDate() {
		return fromReqDate;
	}


	/**
	 * @param fromReqDate the fromReqDate to set
	 */
	public void setFromReqDate(String fromReqDate) {
		this.fromReqDate = fromReqDate;
	}


	/**
	 * @return the toReqDate
	 */
	public String getToReqDate() {
		return toReqDate;
	}


	/**
	 * @param toReqDate the toReqDate to set
	 */
	public void setToReqDate(String toReqDate) {
		this.toReqDate = toReqDate;
	}


	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}


	/**
	 * @param edition the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}


	/**
	 * @return the copyRightYear
	 */
	public String getCopyRightYear() {
		return copyRightYear;
	}


	/**
	 * @param copyRightYear the copyRightYear to set
	 */
	public void setCopyRightYear(String copyRightYear) {
		this.copyRightYear = copyRightYear;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the resultsPopUp
	 */
	public boolean isResultsPopUp() {
		return resultsPopUp;
	}


	/**
	 * @param resultsPopUp the resultsPopUp to set
	 */
	public void setResultsPopUp(boolean resultsPopUp) {
		this.resultsPopUp = resultsPopUp;
	}


	/**
	 * @return the resultListSize
	 */
	public List<ArpSetUpResult> getResultListSize() {
		return resultListSize;
	}


	/**
	 * @param resultListSize the resultListSize to set
	 */
	public void setResultListSize(List<ArpSetUpResult> resultListSize) {
		this.resultListSize = resultListSize;
	}


	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}


	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}


	/**
	 * @return the vendorPageCount
	 */
	public String getVendorPageCount() {
		return vendorPageCount;
	}


	/**
	 * @param vendorPageCount the vendorPageCount to set
	 */
	public void setVendorPageCount(String vendorPageCount) {
		this.vendorPageCount = vendorPageCount;
	}


	/**
	 * @return the isReviewProvide
	 */
	public String getIsReviewProvide() {
		return isReviewProvide;
	}


	/**
	 * @param isReviewProvide the isReviewProvide to set
	 */
	public void setIsReviewProvide(String isReviewProvide) {
		this.isReviewProvide = isReviewProvide;
	}


	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}


	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * @return the isReviewRequested
	 */
	public String getIsReviewRequested() {
		return isReviewRequested;
	}


	/**
	 * @param isReviewRequested the isReviewRequested to set
	 */
	public void setIsReviewRequested(String isReviewRequested) {
		this.isReviewRequested = isReviewRequested;
	}


	/**
	 * @return the finalStr
	 */
	public String getFinalStr() {
		return finalStr;
	}


	/**
	 * @param finalStr the finalStr to set
	 */
	public void setFinalStr(String finalStr) {
		this.finalStr = finalStr;
	}


	/**
	 * @return the statusARP
	 */
	public Status getStatusARP() {
		return statusARP;
	}


	/**
	 * @param statusARP the statusARP to set
	 */
	public void setStatusARP(Status statusARP) {
		this.statusARP = statusARP;
	}


	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}


	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}


	public Integer getStatusId() {
		return statusId;
	}


	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}


}