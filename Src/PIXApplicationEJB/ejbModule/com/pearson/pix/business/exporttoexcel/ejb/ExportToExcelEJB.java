package com.pearson.pix.business.exporttoexcel.ejb;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.pearson.pix.dao.exporttoexcel.ExportToExcelDAO;
import com.pearson.pix.dto.exporttoexcel.ExportToExcelDTO;
import com.pearson.pix.exception.AppException;

/**
 * @author Ajay Jain
 * XDoclet-based session bean.  The class must be declared
 * public according to the EJB specification.
 *
 * To generate the EJB related files to this EJB:
 *		- Add Standard EJB module to XDoclet project properties
 *		- Customize XDoclet configuration for your appserver
 *		- Run XDoclet
 *
 * Below are the xdoclet-related tags needed for this EJB.
 * 
 * @ejb.bean name="ExportToExcel"
 *           display-name="Name for ExportToExcel"
 *           description="Description for ExportToExcel"
 *           jndi-name="ExportToExcel"
 *           type="Stateless"
 *           view-type="local"
 */
public class ExportToExcelEJB implements SessionBean {

	/**
	 * default serial id.
	 */
	private static final long serialVersionUID = 1L;
	public ExportToExcelEJB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Set the associated session context. The container calls this method 
	 * after the instance creation.
	 * 
	 * The enterprise bean instance should store the reference to the context 
	 * object in an instance variable.
	 * 
	 * This method is called with no transaction context. 
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void setSessionContext(SessionContext newContext)
		throws EJBException {
	
	}

	public void ejbRemove() throws EJBException{
		// TODO Auto-generated method stub

	}

	public void ejbActivate() throws EJBException{
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException{
		// TODO Auto-generated method stub

	}
	
	  public void ejbCreate() throws javax.ejb.CreateException
	   {
	   }

	  /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getReportDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getReportDetail(objDTO);
	    }
	    
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getDetail(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getMillDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getMillDetail(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getPurchaseDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getPurchaseDetail(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getPurchaseMillDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getPurchaseMillDetail(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getGoodReceiptDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getGoodReceiptDetail(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the GR History screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred
	     * @author anshu.bhardwaj 
	     */
	    public ExportToExcelDTO getGoodReceiptPaperHistory(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getGoodReceiptPaperHistory(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the GR New screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred
	     * @author anshu.bhardwaj 
	     */
	    public ExportToExcelDTO getGoodReceiptPaperNew(ExportToExcelDTO objDTO) throws AppException{ // getGoodReceiptPaperPop, getGoodReceiptPaperNew
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getGoodReceiptPaperNew(objDTO);
	    }
	    
	    // GR popup
	    public ExportToExcelDTO getGoodReceiptPaperPop(ExportToExcelDTO objDTO) throws AppException{ // getGoodReceiptPaperPop, getGoodReceiptPaperNew
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getGoodReceiptPaperPop(objDTO);
	    }

	    // CU GR popup
	    public ExportToExcelDTO cuOwnedQtyPopup(ExportToExcelDTO objDTO) throws AppException{ // getGoodReceiptPaperPop, getGoodReceiptPaperNew
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.cuOwnedQtyPopup(objDTO);
	    }

	    
	    
// DM popup
	    
	    public ExportToExcelDTO getDMPaperPop(ExportToExcelDTO objDTO) throws AppException{ // getGoodReceiptPaperPop, getGoodReceiptPaperNew
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getDMPaperPop(objDTO);
	    }

// DM History popup
	    
	    public ExportToExcelDTO getDMHistoryPaperPop(ExportToExcelDTO objDTO) throws AppException{ // getGoodReceiptPaperPop, getGoodReceiptPaperNew
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getDMHistoryPaperPop(objDTO);
	    }
	    
	    
	    
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getDeliveryDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getDeliveryDetail(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method New DM screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getDeliveryMessageNewMill(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getDeliveryMessageNewMill(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the DM History screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getDeliveryMessageHistory(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getDeliveryMessageHistory(objDTO);
	    }
	    
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the DM History screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getInboxDeliveryMessageHistory(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getInboxDeliveryMessageHistory(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getBookSpecDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getBookSpecDetail(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getOrderStatusDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getOrderStatusDetail(objDTO);
	    }
	    
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred 
	     */
	    public ExportToExcelDTO getDeliveryMessageApprovalDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getDeliveryMessageApprovalDetail(objDTO);
	    }
	    /**
	     * <!-- begin-xdoclet-definition --> 
	     * @ejb.interface-method view-type="remote"
	     * <!-- end-xdoclet-definition --> 
	     * @generated
	     *
	     * //TODO: Must provide implementation for bean method stub
	     * This method save the budget overview screen information.
	     * 
	     * @param objDTO : ExportToExcelDTO object
	     * @return objDTO :ExportToExcelDTO object
	     * @throws Exception If an exception occurred
	     * @author anshu bhardwaj 
	     */
	    public ExportToExcelDTO getOutstandingDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getOutstandingDetail(objDTO);
	    }
	    
	    public ExportToExcelDTO getPotentialARPDetail(ExportToExcelDTO objDTO) throws AppException{
	    	ExportToExcelDAO objDAO = new ExportToExcelDAO();
	        return objDAO.getPotentialARPDetail(objDTO);
	    }
}
