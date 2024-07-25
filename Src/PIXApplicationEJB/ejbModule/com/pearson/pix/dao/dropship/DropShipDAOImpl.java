package com.pearson.pix.dao.dropship;

import org.apache.struts.upload.FormFile;
import com.pearson.pix.business.dropship.DropShipConstants;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.dropship.DropInstDTO;
import com.pearson.pix.dto.dropship.ShipConfDTO;
import com.pearson.pix.exception.AppException;

public class DropShipDAOImpl extends BaseDAO implements DropShipDAO {

	public Object executeRequest(Object object, Integer integer)
	throws AppException {
		Object obj = null;
  try{
		if(DropShipConstants.GET_SHIPMENT_DETAIL == integer){
			ShipConfDTO shipConfDTO = (ShipConfDTO)object;
			int userId = shipConfDTO.getUserId();
			FormFile file = shipConfDTO.getFile();
				obj = (new ShipmentConfDetailDAO()).getShipmentConfDetails(userId, file);
		} else if(DropShipConstants.DELETE_SHIPMENT_DETAIL == integer){
			ShipConfDTO shipConfDTO = (ShipConfDTO)object;
			int userId = shipConfDTO.getUserId();
				obj = (new ShipmentConfDetailDAO()).deleteShipmentConfDetails(userId);
		} else if(DropShipConstants.CONFIRM_SHIPMENT_DETAIL == integer){
			ShipConfDTO shipConfDTO = (ShipConfDTO)object;
			int userId = shipConfDTO.getUserId();
				obj = (new ShipmentConfDetailDAO()).confirmShipmentConfDetails(userId);

		} else if(DropShipConstants.DISPLAY_SHIPMENT_DETAIL == integer){
			ShipConfDTO shipConfDTO = (ShipConfDTO)object;
			int pageNum = shipConfDTO.getPageNo();
			int userId = shipConfDTO.getUserId() == null ? 0: shipConfDTO.getUserId();
				obj = (new ShipmentConfDetailDAO()).getShipConfDetail(userId, pageNum);
		} else if(DropShipConstants.VALIDATE_SHIPINFO == integer){
			ShipConfDTO shipConfDTO = (ShipConfDTO)object;
			FormFile file = shipConfDTO.getFile();
				obj = (new ShipmentConfDetailDAO()).validateExcelSheet(file);
		} else if(DropShipConstants.TOTAL_SHIPMENT_DETAIL == integer){
			ShipConfDTO shipConfDTO = (ShipConfDTO)object;
			int userId = shipConfDTO.getUserId();
				obj = (new ShipmentConfDetailDAO()).totalShipConfDetail(userId);
		} else if(DropShipConstants.DROPSHIP_INSTRUTCIONS_GET == integer){
			DropInstDTO dropInstDTO = (DropInstDTO)object;
			obj=(new DropInstDAO()).getDropInstructionsData(dropInstDTO);
		} else if(DropShipConstants.DROPSHIP_INSTRUTCIONS_PDFDOWNLOAD == integer){
			DropInstDTO dropInstDTO = (DropInstDTO)object;
				obj=(new DropInstDAO()).getPackingSlipDetail(dropInstDTO);
		} else if(DropShipConstants.INSERT_SHIPMENT_DETAIL == integer){
			ShipConfDTO shipConfDTO = (ShipConfDTO)object;
			Integer userId= shipConfDTO.getUserId();
			FormFile fileName = shipConfDTO.getFile();
				obj = (new ShipmentConfDetailDAO()).readExcel(userId, fileName);
		} else if(DropShipConstants.VALIDATE_SHIPMENT_DETAIL == integer){
			ShipConfDTO shipConfDTO = (ShipConfDTO)object;
			Integer userId= shipConfDTO.getUserId();
				(new ShipmentConfDetailDAO()).validateShipConf(userId);
		} else if(DropShipConstants.DROPSHIP_INSTRUTCIONS_GET_STATUS == integer){
				obj=(new DropInstDAO()).getStatusDropDown();
		} 
	}
	 catch (Exception e) {
		e.printStackTrace();
		throw new AppException(e);
	}
	return obj;
}}