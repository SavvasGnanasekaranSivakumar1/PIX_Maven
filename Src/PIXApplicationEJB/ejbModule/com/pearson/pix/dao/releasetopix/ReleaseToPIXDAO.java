package com.pearson.pix.dao.releasetopix;

import java.util.Map;
import java.util.Set;

import com.pearson.pix.dto.ces.PixEtlBookSpecCes;
import com.pearson.pix.dto.ces.PixEtlPoHeaderCes;
import com.pearson.pix.exception.AppException;

public interface ReleaseToPIXDAO {
	
	
	public Boolean insertPOInfoToPIX(Map poData)throws AppException ;
	
	public PixEtlPoHeaderCes[] validatePOInfoToPIXFromStaging(Map poData)throws AppException;
	
	public Boolean insertBookSpecInfoToPIX(Map bookSpecData)throws AppException;
	
	public PixEtlBookSpecCes[] validateBookSpecInfoToPIXFromStaging(Map bookSpecData)throws AppException;

}
