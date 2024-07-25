package com.pearson.pix.business.releasetopix.ejb;

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import com.pearson.pix.dto.reports.Report;
import com.pearson.pix.dto.reports.ReportPixHistory;
import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.releasetopix.ReleaseToPIXDAO;
import com.pearson.pix.dao.reports.ReportDAO;
import com.pearson.pix.dto.ces.PixEtlBookSpecCes;
import com.pearson.pix.dto.ces.PixEtlPoHeaderCes;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.dto.ces.CustomOrderHistoryCESDTO;



public class ReleaseToPIXEJB implements SessionBean{
	
	
	/**
     * @J2EE_METHOD  --  ejbCreate
     * Called by the container to create a session bean instance. Its parameters typically
     * contain the information the client uses to customize the bean instance for its use.
     * It requires a matching pair in the bean class and its home interface.
     */
    public void ejbCreate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbRemove
     * A container invokes this method before it ends the life of the session object. This
     * happens as a result of a client's invoking a remove operation, or when a container
     * decides to terminate the session object after a timeout. This method is called with
     * no transaction context.
     */
    public void ejbRemove    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbActivate
     * The activate method is called when the instance is activated from its 'passive' state.
     * The instance should acquire any resource that it has released earlier in the ejbPassivate()
     * method. This method is called with no transaction context.
     */
    public void ejbActivate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbPassivate
     * The passivate method is called before the instance enters the 'passive' state. The
     * instance should release any resources that it can re-acquire later in the ejbActivate()
     * method. After the passivate method completes, the instance must be in a state that
     * allows the container to use the Java Serialization protocol to externalize and store
     * away the instance's state. This method is called with no transaction context.
     */
    public void ejbPassivate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  setSessionContext
     * Set the associated session context. The container calls this method after the instance
     * creation. The enterprise Bean instance should store the reference to the context
     * object in an instance variable. This method is called with no transaction context.
     */
    public void setSessionContext(SessionContext sc)  
    { 

    }
    
    public Boolean insertPOInfoToPIX(Map poData)throws AppException 
    {
    	ReleaseToPIXDAO releaseDAO = DAOFactory.newInstance().getReleaseToPIXDAO();
    	return releaseDAO.insertPOInfoToPIX(poData);
    	
    }
    
    public PixEtlPoHeaderCes[] validatePOInfoToPIXFromStaging(Map poData)throws AppException{
    	ReleaseToPIXDAO releaseDAO = DAOFactory.newInstance().getReleaseToPIXDAO();
    	return releaseDAO.validatePOInfoToPIXFromStaging(poData);
    }
    
  public Boolean insertBookSpecInfoToPIX(Map bookSpecData)throws AppException{
	 ReleaseToPIXDAO releaseDAO = DAOFactory.newInstance().getReleaseToPIXDAO();
	 return releaseDAO.insertBookSpecInfoToPIX(bookSpecData);
  }
	

	public PixEtlBookSpecCes[] validateBookSpecInfoToPIXFromStaging(Map bookSpecData)throws AppException{
		ReleaseToPIXDAO releaseDAO = DAOFactory.newInstance().getReleaseToPIXDAO();
    	return releaseDAO.validateBookSpecInfoToPIXFromStaging(bookSpecData);
	}
	
	public Report[] getOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput)throws AppException{
		Report[] result=null;
		if(orderInput!=null&&orderInput.length>0){
		CustomOrderHistoryCESDTO objReport=orderInput[0];
		ReportDAO reportDAO = DAOFactory.newInstance().getReportDAO();
		Collection resultCollection=reportDAO.getReportList(objReport.getIsbn(),objReport.getPOrderNo(),objReport.getPrintNo(),objReport.getUserId(),objReport.getPagination(),objReport.getItemType(),objReport.getOrderBy(),objReport.getSort(),objReport.getSdate(),objReport.getEdate());
		if(resultCollection!=null&&resultCollection.size()>0){
			result=(Report[])resultCollection.toArray(new Report[resultCollection.size()]);
		 }
		}
		return result;
	}
	
	//Added By Sujeet Kumar -- 26 Nov 2009
	public ReportPixHistory[] getPixOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput)throws AppException{
		ReportPixHistory[] result=null;
		if(orderInput!=null&&orderInput.length>0){
		CustomOrderHistoryCESDTO objReport=orderInput[0];
		ReportDAO reportDAO = DAOFactory.newInstance().getReportDAO();
		Collection resultCollection=reportDAO.getPixReportList(objReport.getIsbn(),objReport.getPrintNo(),objReport.getUserId());
		if(resultCollection!=null&&resultCollection.size()>0){
			result=(ReportPixHistory[])resultCollection.toArray(new ReportPixHistory[resultCollection.size()]);
		 }
		}
		return result;
	}

}
