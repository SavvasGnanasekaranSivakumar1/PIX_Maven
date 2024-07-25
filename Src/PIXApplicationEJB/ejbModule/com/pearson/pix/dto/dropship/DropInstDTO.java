package com.pearson.pix.dto.dropship;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author vishal sinha
 */
	public class DropInstDTO implements java.io.Serializable {
		private static final long serialVersionUID = -4894114233534010995L;
		
		public DropInstDTO() {
			super();
		}
		
		private String invoice;
		private String freightTerms;
		private String isbn13;
		private String title;
		private BigDecimal unitCost;
		private String addShippingInstruction ;
		private String shipToName1;
		private String shipToAddress1;
		private String shipToAddress2;
		private String shipTOAddress3;
		private String shipTOAddress4;
		private String shipTOCity;
		private String shipTOState;
		private String shipTOPostalCode;
		private String shipToCountry;
		private String billToName1;
		private String billToAddress1;
		private String billToAddress2;
		private String billToAddress3;
		private String billToAddress4;
		private String billToCity;
		private String billToState;
		private String billToPostalCode;
		private String billToCountry;
		
		private String bkNumber= null;
		private String docCtrlNo= null;
		private String vendor=null;
		private String vendorContact=null;
		private String isbnNo= null;
		private String custAccountName= null;
		private String businessOwnerShip=null;
		private String poNumber= null;
		private String carrierLevel= null;
		private Date instructionDate= null; 
		private BigDecimal totalQty= null;
		private String status= null;
		private String pkgSlipFlag= null;
		private Integer userId= null;
		private String printNo= null;
		private Integer statusId= null;
		private String startDate= null;
		private String endDate= null;
		private int pagination=1;
		private BigDecimal rn;
		private BigDecimal ds_Id;
		private String vendorReferenceNumber;
		public Object dtoObject; 
		public String isSchool;
		private String statusDescription=null;
		private String statusDetail=null;
		private String shipTo= null;
		private String billTo=null;
		private String billToAccNumber;
		private String unitPrice;
		private String page;
		
		
		private List<DropInstDTO> objDropInstDTOList;
		private Integer dropInstListSize=0;
		/**
		 * @return the bkNumber
		 */
		public String getBkNumber() {
			return bkNumber;
		}


		/**
		 * @param bkNumber the bkNumber to set
		 */
		public void setBkNumber(String bkNumber) {
			this.bkNumber = bkNumber;
		}


		/**
		 * @return the docCtrlNo
		 */
		public String getDocCtrlNo() {
			return docCtrlNo;
		}


		/**
		 * @param docCtrlNo the docCtrlNo to set
		 */
		public void setDocCtrlNo(String docCtrlNo) {
			this.docCtrlNo = docCtrlNo;
		}


		/**
		 * @return the isbnNo
		 */
		public String getIsbnNo() {
			return isbnNo;
		}


		/**
		 * @param isbnNo the isbnNo to set
		 */
		public void setIsbnNo(String isbnNo) {
			this.isbnNo = isbnNo;
		}


		/**
		 * @return the custAccountName
		 */
		public String getCustAccountName() {
			return custAccountName;
		}


		/**
		 * @param custAccountName the custAccountName to set
		 */
		public void setCustAccountName(String custAccountName) {
			this.custAccountName = custAccountName;
		}


		/**
		 * @return the poNumber
		 */
		

		/**
		 * @return the carrierLevel
		 */
		public String getCarrierLevel() {
			return carrierLevel;
		}


		/**
		 * @param carrierLevel the carrierLevel to set
		 */
		public void setCarrierLevel(String carrierLevel) {
			this.carrierLevel = carrierLevel;
		}


		/**
		 * @return the instructionDate
		 */
		

		/**
		 * @return the totalQty
		 */
		

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
		 * @return the pkgSlipFlag
		 */
		public String getPkgSlipFlag() {
			return pkgSlipFlag;
		}


		/**
		 * @param pkgSlipFlag the pkgSlipFlag to set
		 */
		public void setPkgSlipFlag(String pkgSlipFlag) {
			this.pkgSlipFlag = pkgSlipFlag;
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
		 * @return the startDate
		 */
		public String getStartDate() {
			return startDate;
		}


		/**
		 * @param startDate the startDate to set
		 */
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}


		/**
		 * @return the endDate
		 */
		public String getEndDate() {
			return endDate;
		}


		/**
		 * @param endDate the endDate to set
		 */
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}


		/**
		 * @return the pagination
		 */
		public int getPagination() {
			return pagination;
		}


		/**
		 * @param pagination the pagination to set
		 */
		public void setPagination(int pagination) {
			this.pagination = pagination;
		}


		
		/**
		 * @return the serialVersionUID
		 */
		public static long getSerialVersionUID() {
			return serialVersionUID;
		}


		public void setPoNumber(String poNumber) {
			this.poNumber = poNumber;
		}


		public String getPoNumber() {
			return poNumber;
		}


		

		
		public void setDs_Id(BigDecimal ds_Id) {
			this.ds_Id = ds_Id;
		}


		


		public BigDecimal getRn() {
			return rn;
		}


		public BigDecimal getDs_Id() {
			return ds_Id;
		}


		public void setInstructionDate(Date instructionDate) {
			this.instructionDate = instructionDate;
		}


		public BigDecimal getTotalQty() {
			return totalQty;
		}


		public void setTotalQty(BigDecimal totalQty) {
			this.totalQty = totalQty;
		}


		public Date getInstructionDate() {
			return instructionDate;
		}


		public void setRn(BigDecimal rn) {
			this.rn = rn;
		}


		public Object getDtoObject() {
			return dtoObject;
		}


		public void setDtoObject(Object dtoObject) {
			this.dtoObject = dtoObject;
		}


		public String getIsSchool() {
			return isSchool;
		}


		public void setIsSchool(String isSchool) {
			this.isSchool = isSchool;
		}


		public String getInvoice() {
			return invoice;
		}


		public void setInvoice(String invoice) {
			this.invoice = invoice;
		}


		public String getFreightTerms() {
			return freightTerms;
		}


		public void setFreightTerms(String freightTerms) {
			this.freightTerms = freightTerms;
		}


		public String getIsbn13() {
			return isbn13;
		}


		public void setIsbn13(String isbn13) {
			this.isbn13 = isbn13;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		

		public String getAddShippingInstruction() {
			return addShippingInstruction;
		}


		public void setAddShippingInstruction(String addShippingInstruction) {
			this.addShippingInstruction = addShippingInstruction;
		}


		public String getShipToName1() {
			return shipToName1;
		}


		public void setShipToName1(String shipToName1) {
			this.shipToName1 = shipToName1;
		}


		public String getShipToAddress1() {
			return shipToAddress1;
		}


		public void setShipToAddress1(String shipToAddress1) {
			this.shipToAddress1 = shipToAddress1;
		}


		public String getShipToAddress2() {
			return shipToAddress2;
		}


		public void setShipToAddress2(String shipToAddress2) {
			this.shipToAddress2 = shipToAddress2;
		}


		public String getShipTOAddress3() {
			return shipTOAddress3;
		}


		public void setShipTOAddress3(String shipTOAddress3) {
			this.shipTOAddress3 = shipTOAddress3;
		}


		public String getShipTOAddress4() {
			return shipTOAddress4;
		}


		public void setShipTOAddress4(String shipTOAddress4) {
			this.shipTOAddress4 = shipTOAddress4;
		}


		public String getShipTOCity() {
			return shipTOCity;
		}


		public void setShipTOCity(String shipTOCity) {
			this.shipTOCity = shipTOCity;
		}


		public String getShipTOState() {
			return shipTOState;
		}


		public void setShipTOState(String shipTOState) {
			this.shipTOState = shipTOState;
		}


		public String getShipTOPostalCode() {
			return shipTOPostalCode;
		}


		public void setShipTOPostalCode(String shipTOPostalCode) {
			this.shipTOPostalCode = shipTOPostalCode;
		}


		public String getShipToCountry() {
			return shipToCountry;
		}


		public void setShipToCountry(String shipToCountry) {
			this.shipToCountry = shipToCountry;
		}


		public String getBillToName1() {
			return billToName1;
		}


		public void setBillToName1(String billToName1) {
			this.billToName1 = billToName1;
		}


		public String getBillToAddress1() {
			return billToAddress1;
		}


		public void setBillToAddress1(String billToAddress1) {
			this.billToAddress1 = billToAddress1;
		}


		public String getBillToAddress2() {
			return billToAddress2;
		}


		public void setBillToAddress2(String billToAddress2) {
			this.billToAddress2 = billToAddress2;
		}


		public String getBillToAddress3() {
			return billToAddress3;
		}


		public void setBillToAddress3(String billToAddress3) {
			this.billToAddress3 = billToAddress3;
		}


		public String getBillToAddress4() {
			return billToAddress4;
		}


		public void setBillToAddress4(String billToAddress4) {
			this.billToAddress4 = billToAddress4;
		}


		public String getBillToCity() {
			return billToCity;
		}


		public void setBillToCity(String billToCity) {
			this.billToCity = billToCity;
		}


		public String getBillToState() {
			return billToState;
		}


		public void setBillToState(String billToState) {
			this.billToState = billToState;
		}


		public String getBillToPostalCode() {
			return billToPostalCode;
		}


		public void setBillToPostalCode(String billToPostalCode) {
			this.billToPostalCode = billToPostalCode;
		}


		

		public String getBillToCountry() {
			return billToCountry;
		}


		public void setBillToCountry(String billToCountry) {
			this.billToCountry = billToCountry;
		}


		/*public BigDecimal getUnitCost() {
			return unitCost;
		}


		public void setUnitCost(BigDecimal unitCost) {
			this.unitCost = unitCost;
		}*/


		public List<DropInstDTO> getObjDropInstDTOList() {
			return objDropInstDTOList;
		}


		public void setObjDropInstDTOList(List<DropInstDTO> objDropInstDTOList) {
			this.objDropInstDTOList = objDropInstDTOList;
		}


		public Integer getDropInstListSize() {
			return dropInstListSize;
		}


		public void setDropInstListSize(Integer dropInstListSize) {
			this.dropInstListSize = dropInstListSize;
		}


		public String getVendorReferenceNumber() {
			return vendorReferenceNumber;
		}


		public void setVendorReferenceNumber(String vendorReferenceNumber) {
			this.vendorReferenceNumber = vendorReferenceNumber;
		}


		


		/**
		 * @return the vendorContact
		 */
		public String getVendorContact() {
			return vendorContact;
		}


		/**
		 * @param vendorContact the vendorContact to set
		 */
		public void setVendorContact(String vendorContact) {
			this.vendorContact = vendorContact;
		}


		/**
		 * @return the vendor
		 */
		public String getVendor() {
			return vendor;
		}


		/**
		 * @param vendor the vendor to set
		 */
		public void setVendor(String vendor) {
			this.vendor = vendor;
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
		 * @return the businessOwnerShip
		 */
		public String getBusinessOwnerShip() {
			return businessOwnerShip;
		}


		/**
		 * @param businessOwnership the businessOwnership to set
		 */
		public void setBusinessOwnerShip(String businessOwnerShip) {
			this.businessOwnerShip = businessOwnerShip;
		}


		/**
		 * @return the shipTo
		 */
		public String getShipTo() {
			return shipTo;
		}


		/**
		 * @param shipTo the shipTo to set
		 */
		public void setShipTo(String shipTo) {
			this.shipTo = shipTo;
		}


		/**
		 * @return the billTo
		 */
		public String getBillTo() {
			return billTo;
		}


		/**
		 * @param billTo the billTo to set
		 */
		public void setBillTo(String billTo) {
			this.billTo = billTo;
		}


		/**
		 * @return the billToAccNumber
		 */
		public String getBillToAccNumber() {
			return billToAccNumber;
		}


		/**
		 * @param billToAccNumber the billToAccNumber to set
		 */
		public void setBillToAccNumber(String billToAccNumber) {
			this.billToAccNumber = billToAccNumber;
		}


		/**
		 * @return the unitPrice
		 */
		public String getUnitPrice() {
			return unitPrice;
		}


		/**
		 * @param unitPrice the unitPrice to set
		 */
		public void setUnitPrice(String unitPrice) {
			this.unitPrice = unitPrice;
		}


		public String getPage() {
			return page;
		}


		public void setPage(String page) {
			this.page = page;
		}

		
			
	}


