package com.pearson.pix.dao.releasetopix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import oracle.toplink.sessions.UnitOfWork;

import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.ces.PixEtlBookSpecBindingCes;
import com.pearson.pix.dto.ces.PixEtlBookSpecCes;
import com.pearson.pix.dto.ces.PixEtlBookSpecLineCes;
import com.pearson.pix.dto.ces.PixEtlBookSpecMiscCes;
import com.pearson.pix.dto.ces.PixEtlBookSpecNonpressCes;
import com.pearson.pix.dto.ces.PixEtlBookSpecPressCes;
import com.pearson.pix.dto.ces.PixEtlPoHeaderCes;
import com.pearson.pix.dto.releasetopix.CustomBookSpecReleaseDTO;
import com.pearson.pix.dto.releasetopix.CustomPOReleaseDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

public class ReleaseToPIXDAOImpl extends BaseDAO implements ReleaseToPIXDAO{

	public Boolean insertPOInfoToPIX(Map poData)throws AppException
	{
		  Session objSession =  null;
		  
		  UnitOfWork objUnitOfWork=null;	
		 
		  Boolean resultFlag=null;
		 try{
			
	        objSession =getClientSession();
			objUnitOfWork=getUnitOfWork(objSession);
			//objSession.get
			Set keys=poData.keySet();
			Iterator itr=keys.iterator();
			Vector header=new Vector();
			Vector headerNotes=new Vector();
			Vector poLine=new Vector();
			Vector poPriceDtail=new Vector();
			Vector suppComp=new Vector();
			Vector headerExt=new Vector();
			Map notesMap=new LinkedHashMap();
			while(itr.hasNext())
			{
				String id=(String)itr.next();BigDecimal transactionNumber=new BigDecimal(id);//BigDecimal transactionNumber=(BigDecimal)itr.next()
				CustomPOReleaseDTO objCustomPOReleaseDTO=(CustomPOReleaseDTO)poData.get(id);System.out.println("transactionNumber----"+transactionNumber);
				if(objCustomPOReleaseDTO.getPoHeaderCes()!=null){
				header.addAll(objCustomPOReleaseDTO.getPoHeaderCes());
				}
				if(objCustomPOReleaseDTO.getPoHeaderNotesCes()!=null){
				//notes code
				/*	for(int y=0;y<objCustomPOReleaseDTO.getPoHeaderNotesCes().size();y++)
				{
					PixEtlPoHeaderNotesCes notes=(PixEtlPoHeaderNotesCes)objCustomPOReleaseDTO.getPoHeaderNotesCes().get(y);
					System.out.println("byte len------"+notes.getNotesDescr().getBytes().length);
				}	*/
				headerNotes.addAll(objCustomPOReleaseDTO.getPoHeaderNotesCes());
				}
				if(objCustomPOReleaseDTO.getPoLineCes()!=null){
				poLine.addAll(objCustomPOReleaseDTO.getPoLineCes());
				}
				if(objCustomPOReleaseDTO.getPoPriceDetailCes()!=null){
				poPriceDtail.addAll(objCustomPOReleaseDTO.getPoPriceDetailCes());
				}
				if(objCustomPOReleaseDTO.getPoSuppCompCes()!=null){
				suppComp.addAll(objCustomPOReleaseDTO.getPoSuppCompCes());
				}
				//Added by Sujeet 09 Oct 2009
				if(objCustomPOReleaseDTO.getPoHeaderExt()!=null){
					headerExt.addAll(objCustomPOReleaseDTO.getPoHeaderExt());
				}
			}
			Vector headerResult=null;
			Vector headerNotesResult=null;
			Vector poLineResult=null;
			Vector poPriceDtailResult=null;
			Vector suppCompResult=null;
			Vector headerExtResult=null;
			boolean saveFlag=false;
			if(header.size()>0){
		     headerResult=objUnitOfWork.registerAllObjects(header);
			}
			if(headerNotes.size()>0){
				 headerNotesResult=objUnitOfWork.registerAllObjects(headerNotes);
				 if(headerNotesResult.size()==headerNotes.size())
				 {
					 saveFlag=true; 
				 }
			}
			if(poLine.size()>0){
				 poLineResult=objUnitOfWork.registerAllObjects(poLine);
				 if(poLineResult.size()==poLine.size())
				 {
					 saveFlag=true; 
				 }
			}
			if(suppComp.size()>0){
				 suppCompResult=objUnitOfWork.registerAllObjects(suppComp);
				 if(suppCompResult.size()==suppComp.size())
				 {
					 saveFlag=true; 
				 }
			}
			
			//Added by Sujeet 09 Oct 2009
			if(headerExt.size()>0){
				 headerExtResult=objUnitOfWork.registerAllObjects(headerExt);
				 if(headerExtResult.size()==headerExt.size())
				 {
					 saveFlag=true; 
				 }
			}
			
			if(poPriceDtail.size()>0){
				 poPriceDtailResult=objUnitOfWork.registerAllObjects(poPriceDtail);
				 if(poPriceDtailResult.size()==poPriceDtail.size())
				 {
					 saveFlag=true; 
				 }
			}
		
			objUnitOfWork.commit();
			// Connection con = null;
			//notes code
		    /*try{
		    
			if(notesMap.size()>0){
				Set notesKeys=notesMap.keySet();
				Iterator NotesItr=keys.iterator();
				int number = 0;
				
				Class.forName( "oracle.jdbc.driver.OracleDriver" ).newInstance();
				con = DriverManager.getConnection( "jdbc:oracle:thin:@10.112.133.60:1521:TEST", "pix", "pix" );
				//con = getDataSourceConnection();
				while(NotesItr.hasNext()){
					BigDecimal transactionNumber=(BigDecimal)NotesItr.next();
					PreparedStatement queryStatement=null;
					java.sql.Clob c = CLOB.createTemporary(con, true, CLOB.DURATION_SESSION);
					queryStatement = con.prepareStatement("update PIX_ETL_PO_HEADER_NOTES_CES set NOTES_DESCR=? where transaction_number=?");
					c.setString(1, (String)notesMap.get(transactionNumber));
					System.out.println("notes desc-->"+((String)notesMap.get(transactionNumber))+"\nGaurav--->"+transactionNumber);
					queryStatement.setClob(1,c);
					queryStatement.setBigDecimal(2,transactionNumber);
					number=queryStatement.executeUpdate();
					System.out.println("output no-->"+number);
					con.commit();
				}
				
			}
			if(con != null){
			con.close();}
		    }
		    catch(SQLException e)
		    {
		    	
		    	e.printStackTrace();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }*/
		    
			resultFlag=new Boolean(saveFlag);
			
			
		 }
		    catch(TopLinkException e)
		     {
		        AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"InventoryDAOImpl,getInventory",e);
		        throw appException;
		     }
		   finally
		    {
			   if(objSession!=null)
			   {
				   objSession.release();
			   }
			   
		    } 
		
		return resultFlag;
	}
	
	
	public PixEtlPoHeaderCes[] validatePOInfoToPIXFromStaging(Map poData)throws AppException
	{
		  Session objSession =  null;
		  UnitOfWork objUnitOfWork=null;	Set keys=poData.keySet();
		  StoredProcedureCall call = new StoredProcedureCall(); 
		  PixEtlPoHeaderCes[] updatedPoHeaderCes=null;
		  Record objRecord = null;
		 try{
			ArrayList list=new ArrayList();
			list.addAll(keys);
			ListIterator itr=list.listIterator();
			StringBuffer tNumber=new StringBuffer("");
			for(int i=0;i<keys.size();i++)
			{
				BigDecimal transactionNumber=new BigDecimal((String)itr.next());//BigDecimal transactionNumber=(BigDecimal)itr.next();
				tNumber.append(transactionNumber);
				if(i<(keys.size()-1)){
				  tNumber.append(",");
				}
			}
	        objSession =getClientSession();
			objUnitOfWork=getUnitOfWork(objSession);
			call.setProcedureName("pix_etl_run_time_po_load_proc");
			call.addNamedArgumentValue("I_TRANSACTION_NUMBER",tNumber.toString()); 
			call.addNamedArgumentValue("I_COUNT",new Integer(keys.size())); 
			call.addNamedArgumentValue("i_Type","PO"); 
			objUnitOfWork.executeNonSelectingCall(call);
			updatedPoHeaderCes=new PixEtlPoHeaderCes[keys.size()];
			for(int i=0;i<keys.size();i++)
			{
			  PixEtlPoHeaderCes objPoHeaderDTO=new PixEtlPoHeaderCes();
			  BigDecimal transactionNumber=new BigDecimal((String)itr.previous());//BigDecimal transactionNumber=(BigDecimal)itr.previous();
			 Vector objAddress = (Vector)objSession.executeSelectingCall(new SQLCall("SELECT PO_NUMBER, TRANSACTION_NUMBER,ERROR_DESCR,PIX_TRANSACTION_DATE FROM PIX_ETL_PO_HEADER_CES WHERE TRANSACTION_NUMBER ="+transactionNumber));
			
			  if(objAddress != null && objAddress.size()>0)
			     {
					  objRecord = (Record)objAddress.get(0);
					  objPoHeaderDTO.setPoNumber((String)objRecord.get("PO_NUMBER"));
					  objPoHeaderDTO.setTransactionNumber((BigDecimal)objRecord.get("TRANSACTION_NUMBER"));
					  objPoHeaderDTO.setErrorDescr((String)objRecord.get("ERROR_DESCR"));
					  objPoHeaderDTO.setPixTransactionDate((Date)objRecord.get("PIX_TRANSACTION_DATE"));
				  }
			 updatedPoHeaderCes[i]=objPoHeaderDTO;
			}
			
			
			
		   }catch(TopLinkException e)
		     {
		        AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"InventoryDAOImpl,getInventory",e);
		        throw appException;
		     }
		   finally
		    {
			   if(objSession!=null)
			   {
				   objSession.release();
			   }
		    } 
		   
		   return updatedPoHeaderCes;
	}
	
	
	
	public Boolean insertBookSpecInfoToPIX(Map bookSpecData)throws AppException
	{
		  Session objSession =  null;
		  UnitOfWork objUnitOfWork=null;	
		  Boolean resultFlag=null;
		 try{
			
	        objSession =getClientSession();
			objUnitOfWork=getUnitOfWork(objSession);
			Set keys=bookSpecData.keySet();
			Iterator itr=keys.iterator();
			Vector bookSpec=new Vector();
			Vector bookSpecLine=new Vector();
			Vector bookSpecPress=new Vector();
			Vector bookSpecBind=new Vector();
			Vector bookSpecNonpress=new Vector();
			Vector bookSpecMisc=new Vector();
			while(itr.hasNext())
			{
				System.out.println("loop...........size  ---->"+keys.size());
				String id=(String)itr.next();BigDecimal transactionNumber=new BigDecimal(id);//BigDecimal transactionNumber=new BigDecimal((String)itr.next());//Double transactionNumber=(Double)itr.next();
				CustomBookSpecReleaseDTO objCustomBookSpecReleaseDTO=(CustomBookSpecReleaseDTO)bookSpecData.get(id);
				if(objCustomBookSpecReleaseDTO.getPixBookSpecCes()!=null){
					bookSpec.addAll(objCustomBookSpecReleaseDTO.getPixBookSpecCes());
					
				}
				if(objCustomBookSpecReleaseDTO.getPixBookSpecLineCes()!=null){
					bookSpecLine.addAll(objCustomBookSpecReleaseDTO.getPixBookSpecLineCes());
				}
				if(objCustomBookSpecReleaseDTO.getPixBookSpecPressCes()!=null){
					bookSpecPress.addAll(objCustomBookSpecReleaseDTO.getPixBookSpecPressCes());
				}
				if(objCustomBookSpecReleaseDTO.getPixBookSpecBindingCes()!=null){
					bookSpecBind.addAll(objCustomBookSpecReleaseDTO.getPixBookSpecBindingCes());
				}
				if(objCustomBookSpecReleaseDTO.getPixBookSpecNonpressCes()!=null){
					bookSpecNonpress.addAll(objCustomBookSpecReleaseDTO.getPixBookSpecNonpressCes());
				}
				if(objCustomBookSpecReleaseDTO.getPixBookSpecMiscCes()!=null){
					bookSpecMisc.addAll(objCustomBookSpecReleaseDTO.getPixBookSpecMiscCes());
				}
			}
			Vector bookSpecResult=null;
			Vector bookSpecLineResult=null;
			Vector bookSpecPressResult=null;
			Vector bookSpecBindResult=null;
			Vector bookSpecNonpressResult=null;
			Vector bookSpecMiscResult=null;
			
			boolean saveFlag=false;
			if(bookSpec.size()>0){
				bookSpecResult=objUnitOfWork.registerAllObjects(bookSpec);
			}
			
			if(bookSpecLine.size()>0){
				
				for(int j=0;j<bookSpecResult.size();j++)
				{
					PixEtlBookSpecCes objDB=(PixEtlBookSpecCes)bookSpecResult.get(j);
					for(int k=0;k<bookSpecLine.size();k++)
					{
						PixEtlBookSpecLineCes objLine=(PixEtlBookSpecLineCes)bookSpecLine.get(k);
						if(objLine.getTransactionNumber().getTransactionNumber().intValue()==objDB.getTransactionNumber().intValue())
						{
							objLine.setTransactionNumber(objDB);
							bookSpecLine.set(k,objLine);
						}
					}
				}
				
				bookSpecLineResult=objUnitOfWork.registerAllObjects(bookSpecLine);
				 if(bookSpecLineResult.size()==bookSpecLine.size())
				 {
					 saveFlag=true; 
				 }
			}
			if(bookSpecPress.size()>0){
				
				for(int j=0;j<bookSpecLineResult.size();j++)
				{
					PixEtlBookSpecLineCes objDB=(PixEtlBookSpecLineCes)bookSpecLineResult.get(j);
					for(int k=0;k<bookSpecPress.size();k++)
					{
						PixEtlBookSpecPressCes objPress=(PixEtlBookSpecPressCes)bookSpecPress.get(k);
						if((objPress.getPixEtlBookSpecLineCes().getTransactionNumber().getTransactionNumber().intValue()==objDB.getTransactionNumber().getTransactionNumber().intValue())&&(objPress.getPixEtlBookSpecLineCes().getComponentId().intValue()==objDB.getComponentId().intValue())&&(objPress.getPixEtlBookSpecLineCes().getComponentSeqNumber().intValue()==objDB.getComponentSeqNumber().intValue()))
						{
							objPress.setPixEtlBookSpecLineCes(objDB);
							bookSpecPress.set(k,objPress);
						}
					}
				}
				
				
				
				
				
				bookSpecPressResult=objUnitOfWork.registerAllObjects(bookSpecPress);
				 if(bookSpecPressResult.size()==bookSpecPress.size())
				 {
					 saveFlag=true; 
				 }
			}
			if(bookSpecBind.size()>0){
				
				for(int j=0;j<bookSpecLineResult.size();j++)
				{
					PixEtlBookSpecLineCes objDB=(PixEtlBookSpecLineCes)bookSpecLineResult.get(j);
					for(int k=0;k<bookSpecBind.size();k++)
					{
						PixEtlBookSpecBindingCes objBind=(PixEtlBookSpecBindingCes)bookSpecBind.get(k);
						if((objBind.getPixEtlBookSpecLineCes().getTransactionNumber().getTransactionNumber().intValue()==objDB.getTransactionNumber().getTransactionNumber().intValue())&&(objBind.getPixEtlBookSpecLineCes().getComponentId().intValue()==objDB.getComponentId().intValue())&&(objBind.getPixEtlBookSpecLineCes().getComponentSeqNumber().intValue()==objDB.getComponentSeqNumber().intValue()))
						{
							objBind.setPixEtlBookSpecLineCes(objDB);
							bookSpecBind.set(k,objBind);
						}
					}
				}
				
				
				
				
				bookSpecBindResult=objUnitOfWork.registerAllObjects(bookSpecBind);
				 if(bookSpecBindResult.size()==bookSpecBind.size())
				 {
					 saveFlag=true; 
				 }
			}
			
			if(bookSpecNonpress.size()>0){
				
				for(int j=0;j<bookSpecLineResult.size();j++)
				{
					PixEtlBookSpecLineCes objDB=(PixEtlBookSpecLineCes)bookSpecLineResult.get(j);
					for(int k=0;k<bookSpecNonpress.size();k++)
					{
						PixEtlBookSpecNonpressCes objNonpress=(PixEtlBookSpecNonpressCes)bookSpecNonpress.get(k);
						if((objNonpress.getPixEtlBookSpecLineCes().getTransactionNumber().getTransactionNumber().intValue()==objDB.getTransactionNumber().getTransactionNumber().intValue())&&(objNonpress.getPixEtlBookSpecLineCes().getComponentId().intValue()==objDB.getComponentId().intValue())&&(objNonpress.getPixEtlBookSpecLineCes().getComponentSeqNumber().intValue()==objDB.getComponentSeqNumber().intValue()))
						{
							objNonpress.setPixEtlBookSpecLineCes(objDB);
							bookSpecNonpress.set(k,objNonpress);
						}
					}
				}
				
				
				bookSpecNonpressResult=objUnitOfWork.registerAllObjects(bookSpecNonpress);
				 if(bookSpecNonpressResult.size()==bookSpecNonpress.size())
				 {
					 saveFlag=true; 
				 }
			}
			
			if(bookSpecMisc.size()>0){
				
				for(int j=0;j<bookSpecLineResult.size();j++)
				{
					PixEtlBookSpecLineCes objDB=(PixEtlBookSpecLineCes)bookSpecLineResult.get(j);
					for(int k=0;k<bookSpecMisc.size();k++)
					{
						PixEtlBookSpecMiscCes objMisc=(PixEtlBookSpecMiscCes)bookSpecMisc.get(k);
						if((objMisc.getPixEtlBookSpecLineCes().getTransactionNumber().getTransactionNumber().intValue()==objDB.getTransactionNumber().getTransactionNumber().intValue())&&(objMisc.getPixEtlBookSpecLineCes().getComponentId().intValue()==objDB.getComponentId().intValue())&&(objMisc.getPixEtlBookSpecLineCes().getComponentSeqNumber().intValue()==objDB.getComponentSeqNumber().intValue()))
						{
							objMisc.setPixEtlBookSpecLineCes(objDB);
							bookSpecMisc.set(k,objMisc);
						}
					}
				}
				
				bookSpecMiscResult=objUnitOfWork.registerAllObjects(bookSpecMisc);
				 if(bookSpecMiscResult.size()==bookSpecMisc.size())
				 {
					 saveFlag=true; 
				 }
			}
			
			
			objUnitOfWork.commit();
		
			resultFlag=new Boolean(saveFlag);
			
			
		 }
		    catch(TopLinkException e)
		     {
		        AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"InventoryDAOImpl,getInventory",e);
		        throw appException;
		     }
		   finally
		    {
			   if(objSession!=null)
			   {
				   objSession.release();
			   }
			   
		    } 
		
		return resultFlag;
	}
	
	
	
	public PixEtlBookSpecCes[] validateBookSpecInfoToPIXFromStaging(Map bookSpecData)throws AppException
	{
		  Session objSession =  null;
		  UnitOfWork objUnitOfWork=null;	Set keys=bookSpecData.keySet();
		  StoredProcedureCall call = new StoredProcedureCall(); 
		  PixEtlBookSpecCes[] updatedBookSpecCes=null;
		  Record objRecord = null;
		 try{
			    ArrayList list=new ArrayList();
				list.addAll(keys);
				ListIterator itr=list.listIterator();
				StringBuffer tNumber=new StringBuffer("");
				for(int i=0;i<keys.size();i++)
				{
					BigDecimal transactionNumber=new BigDecimal((String)itr.next());
					tNumber.append(transactionNumber);
					if(i<(keys.size()-1)){
					  tNumber.append(",");
					}
				}
	        objSession =getClientSession();
	        System.out.println("in validation...................");
			objUnitOfWork=getUnitOfWork(objSession);
			call.setProcedureName("PIX_ETL_RUN_TIME_PO_LOAD_PROC");
			call.addNamedArgumentValue("I_TRANSACTION_NUMBER",tNumber.toString()); 
			call.addNamedArgumentValue("I_COUNT",new Integer(keys.size())); 
			call.addNamedArgumentValue("i_Type","BS"); 
			objUnitOfWork.executeNonSelectingCall(call);
			updatedBookSpecCes=new PixEtlBookSpecCes[keys.size()];
			
			for(int i=0;i<keys.size();i++)
			{
				PixEtlBookSpecCes objPixEtlBookSpecCes=new PixEtlBookSpecCes();
				BigDecimal transactionNumber=new BigDecimal((String)itr.previous());//Double transactionNumber=(Double)itr.previous();
			 Vector objAddress = (Vector)objSession.executeSelectingCall(new SQLCall("SELECT TITLE_ISBN10,PRINTING_NUMBER ,TRANSACTION_NUMBER,ERROR_DESCR,PIX_TRANSACTION_DATE,EVENT_LOG_DATE FROM PIX_ETL_BOOK_SPEC_CES WHERE TRANSACTION_NUMBER ="+transactionNumber));
			 

			
			  if(objAddress != null && objAddress.size()>0)
			     {
					  objRecord = (Record)objAddress.get(0);
					  objPixEtlBookSpecCes.setTitleIsbn10((String)objRecord.get("TITLE_ISBN10"));
					  objPixEtlBookSpecCes.setPrintingNumber((BigDecimal)objRecord.get("PRINTING_NUMBER"));
					  objPixEtlBookSpecCes.setTransactionNumber((BigDecimal)objRecord.get("TRANSACTION_NUMBER"));
					  objPixEtlBookSpecCes.setErrorDescr((String)objRecord.get("ERROR_DESCR"));
					  objPixEtlBookSpecCes.setPixTransactionDate((Date)objRecord.get("PIX_TRANSACTION_DATE"));
					  objPixEtlBookSpecCes.setEventLogDate((Date)objRecord.get("EVENT_LOG_DATE"));
				  }
			  updatedBookSpecCes[i]=objPixEtlBookSpecCes;
			}
			
			objUnitOfWork.logMessages();
		   }catch(TopLinkException e)
		     {
		        AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"InventoryDAOImpl,getInventory",e);
		        throw appException;
		     }
		   finally
		    {
			   if(objSession!=null)
			   {
				   objSession.release();
			   }
		    } 
		   
		   return updatedBookSpecCes;
	}
	
	
	
	
}
