package com.pearson.pix.business.releasetopix.interfaces;

import java.util.Map;
import java.util.Set;

import javax.ejb.EJBLocalObject;

import com.pearson.pix.dto.reports.ReportPixHistory;
import com.pearson.pix.dto.reports.Report;
import com.pearson.pix.dto.ces.CustomOrderHistoryCESDTO;
import com.pearson.pix.dto.ces.PixEtlBookSpecCes;
import com.pearson.pix.dto.ces.PixEtlPoHeaderCes;
import com.pearson.pix.exception.AppException;

public interface ReleaseToPIXLocal extends javax.ejb.EJBObject{
	
	
	public Boolean insertPOInfoToPIX(Map poData)throws AppException ;
	
	public PixEtlPoHeaderCes[] validatePOInfoToPIXFromStaging(Map poData)throws AppException;
	
    public Boolean insertBookSpecInfoToPIX(Map bookSpecData)throws AppException;
	
	public PixEtlBookSpecCes[] validateBookSpecInfoToPIXFromStaging(Map bookSpecData)throws AppException;
	
	public Report[] getOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput)throws AppException;
	
	public ReportPixHistory[] getPixOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput)throws AppException;
	

}
