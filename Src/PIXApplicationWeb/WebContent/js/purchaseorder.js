//function for filtering with previous next
function listfilter(path)
{

document.orderListForm.action=path;
document.orderListForm.submit();
}

function detailSubmit(path)
{

document.orderDetailForm.action=path;
document.orderDetailForm.submit();
}

function sfilter(path)
{
    
	if(!dateCheck(document.orderListForm.endDate.value,document.orderListForm.startDate.value,'>=','Start Date should be ','End Date'))
	{
	return false;
	/*alert("enter valid date ,'>=','Start Date should be ','End Date'"); 	formname = eval('document.orderListForm');		document.orderListForm.action=path;*/
	
	}
	
	if(!dateCheck(document.orderListForm.ebBDate.value,document.orderListForm.sbBDate.value,'>=','Start Date should be ','End Date'))
	{
		return false;		
	}
	
		document.orderListForm.action=path;
		document.orderListForm.submit();
     	
}

/*
//function to limit character input to header
function limitheader(vendorIndex)
{
var formObj = eval('document.orderDetailForm');
var headerComments = formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].comments"].value;
if(headerComments.length>255)
{
alert('Sorry! Number of characters in Comments cannot be more than 255');
return false;
}

}

//function to limit character input for line items
function limit(totalLineItems)
{

for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
var lineComments = formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierComments"].value;
if(lineComments.length>255)
{
alert('Number of characters in Comments cannot be more than 255');
return false;
}
}
}*/

//function to validate input
function validate(vendorIndex,totalLineItems)
{
   
	var formObj = eval('document.orderDetailForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	
	var jobNo = formObj.elements["poHeader.jobNo"].value;
	
	if(jobNo.indexOf("<")!= -1 || jobNo.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Job No. cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].statusId"].value=="")
	{
		errorString=errorString+'<LI>Please select Status for Header</LI>';
	}
	/*
	if(formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].deliveryDate"].value!="")
	{
		if(checkFutureDate(formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].deliveryDate"].value)==false)
		{
			errorString=errorString+'<LI>Please select future Delivery Date for Header</LI>';
		}
	}
	*/
	var objHeaderStatus = "poHeader.partyCollectionIdx["+vendorIndex+"].statusId";
	var headerStatus = formObj.elements[objHeaderStatus].options[formObj.elements[objHeaderStatus].selectedIndex].text;
	
	if((headerStatus=="Amended" || headerStatus=="Rejected") && formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].comments"].value=="")
	{
		errorString=errorString+'<LI>Please enter Comments for Header</LI>';
	}
	
	if(formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].comments"].value!="")
	{
		var headerComments = formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].comments"].value;
		if(headerComments.length>255)
		{
			errorString=errorString+'<LI>Number of characters in Header Comments cannot be more than 255</LI>';
		}
		if(headerComments.indexOf("<")!= -1 || headerComments.indexOf(">")!= -1)
		{
			errorString=errorString+'<LI>Header Comments cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
		}
		
	}
	
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
		/*
		if(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].estimatedQuantity"].value=="")
		{
			errorString=errorString;
		}
		else if(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].estimatedQuantity"].value==0)
		{
			errorString=errorString+'<LI>Please enter valid Estimated Quantity for Line Item '+(currIndex+1)+'</LI>';
		}*/
		
		if(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierStatusId"].value=="")
		{
			errorString=errorString+'<LI>Please select Status for Line Item '+(currIndex+1)+'</LI>';
		}
		/*
		if(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].estimatedDeliveryDate"].value!="")
		{
			if(checkFutureDate(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].estimatedDeliveryDate"].value)==false)
			{
				errorString=errorString+'<LI>Please select future Delivery Date for Line Item '+(currIndex+1)+'</LI>';
			}
		}*/
		var objLineStatus = "poHeader.lineItemCollectionIdx["+currIndex+"].supplierStatusId";
		var lineStatus = formObj.elements[objLineStatus].options[formObj.elements[objLineStatus].selectedIndex].text;
		/*
		if((lineStatus=="Amended" || lineStatus=="Rejected") && formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierComments"].value=="")
		{
			errorString=errorString+'Please enter Comments for Line Item '+(currIndex+1)+'</LI>';
		}
		*/
		if(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierComments"].value!="")
		{
			var lineComments = formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierComments"].value;
			if(lineComments.length>255)
			{
				errorString=errorString+'<LI>Number of characters in Line Item '+(currIndex+1)+' Comments cannot be more than 255</LI>';
			}
			if(lineComments.indexOf("<")!= -1 || lineComments.indexOf(">")!= -1)
			{
				errorString=errorString+'<LI>Line Item '+(currIndex+1)+' Comments cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
			}
			
		}
	}
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
		window.location="#topofpage";
		return false;
	}
	
}


function validateNew(vendorIndex,totalLineItems)
{
    
    var lineDlDateAraay=new Array();
    var lineEstimatedDlDateArray=new Array();
    var headerDlDate = document.getElementById("headerDlDate").innerHTML;
    var headerEstimatedDlDate=document.getElementById("header_del_date").value ;
	headerEstimatedDlDate=trim(headerEstimatedDlDate);
    headerDlDate=trim(headerDlDate);
    
    for(var count=1;count<=totalLineItems;count++){
     var lineDlDate = document.getElementById("lineDlDate"+count).innerHTML;
     var lineEstimatedDlDate=document.getElementById("line_del_date_"+(count-1)).value
     lineDlDate=trim(lineDlDate);
     lineEstimatedDlDate=trim(lineEstimatedDlDate);
     lineDlDateAraay[count-1]=lineDlDate;
     lineEstimatedDlDateArray[count-1]=lineEstimatedDlDate ;
     }
	var formObj = eval('document.orderDetailForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	var jobNo = formObj.elements["poHeader.jobNo"].value;
	
	
	if(jobNo.indexOf("<")!= -1 || jobNo.indexOf(">")!= -1)
	{
		errorString=errorString+'<LI>Job No. cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
	}
	if(formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].statusId"].value=="")
	{
		errorString=errorString+'<LI>Please select Status for Header</LI>';
	}
	
	var objHeaderStatus = "poHeader.partyCollectionIdx["+vendorIndex+"].statusId";
	var headerStatus = formObj.elements[objHeaderStatus].options[formObj.elements[objHeaderStatus].selectedIndex].text;
	
	if(headerStatus=='Accepted'&&(headerEstimatedDlDate!=''&&(Date.parse(headerDlDate)<Date.parse(headerEstimatedDlDate))))
	{
		errorString=errorString+'<LI>Estimated Delivery Date cannot be greater than Delivery Date for Header </LI>';
	}
	
	
	if((headerStatus=="Amended" || headerStatus=="Rejected") && formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].comments"].value=="")
	{
		errorString=errorString+'<LI>Please enter Comments for Header</LI>';
	}
	
	if(formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].comments"].value!="")
	{
		var headerComments = formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].comments"].value;
		if(headerComments.length>255)
		{
			errorString=errorString+'<LI>Number of characters in Header Comments cannot be more than 255</LI>';
		}
		if(headerComments.indexOf("<")!= -1 || headerComments.indexOf(">")!= -1)
		{
			errorString=errorString+'<LI>Header Comments cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
		}
		
	}
	
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
		
		if(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierStatusId"].value=="")
		{
			errorString=errorString+'<LI>Please select Status for Line Item '+(currIndex+1)+'</LI>';
		}
		
		var objLineStatus = "poHeader.lineItemCollectionIdx["+currIndex+"].supplierStatusId";
		var lineStatus = formObj.elements[objLineStatus].options[formObj.elements[objLineStatus].selectedIndex].text;
		
		
		if(lineStatus=='Accepted'&&(lineEstimatedDlDateArray[currIndex]!=''&& (Date.parse(lineDlDateAraay[currIndex])<Date.parse(lineEstimatedDlDateArray[currIndex]))))
	    {
			errorString=errorString+'<LI>Estimated  Delivery Date cannot be greater than Delivery Date for Line Item '+(currIndex+1)+' </LI>';
	    }
		
		
		
		
		
		if(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierComments"].value!="")
		{
			var lineComments = formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierComments"].value;
			if(lineComments.length>255)
			{
				errorString=errorString+'<LI>Number of characters in Line Item '+(currIndex+1)+' Comments cannot be more than 255</LI>';
			}
			if(lineComments.indexOf("<")!= -1 || lineComments.indexOf(">")!= -1)
			{
				errorString=errorString+'<LI>Line Item '+(currIndex+1)+' Comments cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
			}
			
		}
	}
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
		window.location="#topofpage";
		return false;
	}
	
}

  
  
  
  
  
  
function changeLineStatus(totalLineItems,obj)
{
	var formObj = eval('document.orderDetailForm');
	var vendorStatus = obj.options[obj.selectedIndex].text;
	setLineStatus(totalLineItems,formObj,vendorStatus);
	/*
	if(vendorStatus=="Select")
	{
		setLineStatus(totalLineItems,formObj,"Select");
	}
	else if(vendorStatus=="New")
	{
		setLineStatus(totalLineItems,formObj,"New");
	}
	else if(vendorStatus=="Amended")
	{
		setLineStatus(totalLineItems,formObj,"Amended");
	}
	else if(vendorStatus=="Cancelled")
	{
		setLineStatus(totalLineItems,formObj,"Cancelled");
	}
	else if(vendorStatus=="No Action")
	{
		setLineStatus(totalLineItems,formObj,"No Action");
	}
	else if(vendorStatus=="Order Delivered")
	{
		setLineStatus(totalLineItems,formObj,"Order Delivered");
	}
	*/
}

function setLineStatus(totalLineItems,formObj,newText)
{
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
		var objName = "poHeader.lineItemCollectionIdx["+currIndex+"].supplierStatusId";
		var lengthOfSelect = formObj.elements[objName].options.length;
		var vendorLineStatus = formObj.elements[objName].options[formObj.elements[objName].selectedIndex].text;
		if(vendorLineStatus=="Select")
		{
			for(indexSelect=0;indexSelect<lengthOfSelect;indexSelect++)
			{
				if((formObj.elements[objName]).options[indexSelect].text==newText)
				{
					(formObj.elements[objName]).options[indexSelect].selected = true;
				}
			}
		}
	}
}

function setLineDate(totalLineItems,partyCounter)
{
	var formObj = eval('document.orderDetailForm');
	exDateTime=formObj.elements["poHeader.partyCollectionIdx["+partyCounter+"].deliveryDate"].value;
	
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
		var objName = "poHeader.lineItemCollectionIdx["+currIndex+"].estimatedDeliveryDate";
		if(formObj.elements[objName].value=="")
		{
			formObj.elements[objName].value = exDateTime;
		}
	}
}

function NewCalPOHeader(pCtrl,pFormat,totalLineItems,partyCounter,pShowTime,pTimeMode)
{

	Cal=new Calendar(dtToday);
	if ((pShowTime!=null) && (pShowTime))
	{
		Cal.ShowTime=true;
		if ((pTimeMode!=null) &&((pTimeMode=='12')||(pTimeMode=='24')))
		{
			TimeMode=pTimeMode;
		}		
	}	
	if (pCtrl!=null)
		Cal.Ctrl=pCtrl;
	if (pFormat!=null)
		Cal.Format=pFormat.toUpperCase();
	
	exDateTime=document.getElementById(pCtrl).value;
	if (exDateTime!="")//Parse Date String
	{
		var Sp1;//Index of Date Separator 1
		var Sp2;//Index of Date Separator 2 
		var tSp1;//Index of Time Separator 1
		var tSp1;//Index of Time Separator 2
		var strMonth;
		var strDate;
		var strYear;
		var intMonth;
		var YearPattern;
		var strHour;
		var strMinute;
		var strSecond;
		//parse month
		Sp1=exDateTime.indexOf(DateSeparator,0)
		Sp2=exDateTime.indexOf(DateSeparator,(parseInt(Sp1)+1));
		
		if ((Cal.Format.toUpperCase()=="DDMMYYYY") || (Cal.Format.toUpperCase()=="DDMMMYYYY"))
		{
			strMonth=exDateTime.substring(Sp1+1,Sp2);
			strDate=exDateTime.substring(0,Sp1);
		}
		else if ((Cal.Format.toUpperCase()=="MMDDYYYY") || (Cal.Format.toUpperCase()=="MMMDDYYYY"))
		{
			strMonth=exDateTime.substring(0,Sp1);
			strDate=exDateTime.substring(Sp1+1,Sp2);
		}
		if (isNaN(strMonth))
			intMonth=Cal.GetMonthIndex(strMonth);
		else
			intMonth=parseInt(strMonth,10)-1;	
		if ((parseInt(intMonth,10)>=0) && (parseInt(intMonth,10)<12))
			Cal.Month=intMonth;
		//end parse month
		//parse Date
		if ((parseInt(strDate,10)<=Cal.GetMonDays()) && (parseInt(strDate,10)>=1))
			Cal.Date=strDate;
		//end parse Date
		//parse year
		strYear=exDateTime.substring(Sp2+1,Sp2+5);
		YearPattern=/^\d{4}$/;
		if (YearPattern.test(strYear))
			Cal.Year=parseInt(strYear,10);
		//end parse year
		//parse time
		if (Cal.ShowTime==true)
		{
			tSp1=exDateTime.indexOf(":",0)
			tSp2=exDateTime.indexOf(":",(parseInt(tSp1)+1));
			strHour=exDateTime.substring(tSp1,(tSp1)-2);
			Cal.SetHour(strHour);
			strMinute=exDateTime.substring(tSp1+1,tSp2);
			Cal.SetMinute(strMinute);
			strSecond=exDateTime.substring(tSp2+1,tSp2+3);
			Cal.SetSecond(strSecond);
		}	
	}
	winCal=window.open("","DateTimePicker","toolbar=0,status=0,menubar=0,fullscreen=no,width=195,height=245,resizable=0,top="+cnTop+",left="+cnLeft);
	docCal=winCal.document;
	RenderCalPOHeader(totalLineItems,partyCounter);
	
}

function RenderCalPOHeader(totalLineItems,partyCounter)
{
	var vCalHeader;
	var vCalData;
	var vCalTime;
	var i;
	var j;
	var SelectStr;
	var vDayCount=0;
	var vFirstDay;

	docCal.open();
	docCal.writeln("<html><head><title>"+WindowTitle+"</title>");
	docCal.writeln("<script>var winMain=window.opener;</script>");
	docCal.writeln("</head><body background='"+ThemeBg+"' link="+FontColor+" vlink="+FontColor+"><form name='Calendar'>");

	vCalHeader="<table border=1 cellpadding=1 cellspacing=1 width='100%' align=\"center\" valign=\"top\">\n";
	//Month Selector
	vCalHeader+="<tr>\n<td colspan='7'><table border=0 width='100%' cellpadding=0 cellspacing=0><tr><td align='left'>\n";
	vCalHeader+="<select name=\"MonthSelector\" onChange=\"javascript:winMain.Cal.SwitchMth(this.selectedIndex);winMain.RenderCalPOHeader("+totalLineItems+","+partyCounter+");\">\n";
	for (i=0;i<12;i++)
	{
		if (i==Cal.Month)
			SelectStr="Selected";
		else
			SelectStr="";	
		vCalHeader+="<option "+SelectStr+" value >"+MonthName[i]+"\n";
	}
	vCalHeader+="</select></td>";
	//Year selector
	vCalHeader+="\n<td align='right'><a href=\"javascript:winMain.Cal.DecYear();winMain.RenderCalPOHeader("+totalLineItems+","+partyCounter+")\"><b><font color=\""+YrSelColor+"\"><</font></b></a><font face=\"Verdana\" color=\""+YrSelColor+"\" size=2><b> "+Cal.Year+" </b></font><a href=\"javascript:winMain.Cal.IncYear();winMain.RenderCalPOHeader("+totalLineItems+","+partyCounter+")\"><b><font color=\""+YrSelColor+"\">></font></b></a></td></tr></table></td>\n";	
	vCalHeader+="</tr>";
	//Calendar header shows Month and Year
	if (ShowMonthYear)
		vCalHeader+="<tr><td colspan='7'><font face='Verdana' size='2' align='center' color='"+MonthYearColor+"'><b>"+Cal.GetMonthName(ShowLongMonth)+" "+Cal.Year+"</b></font></td></tr>\n";
	//Week day header
	vCalHeader+="<tr bgcolor="+WeekHeadColor+">";
	for (i=0;i<7;i++)
	{
		vCalHeader+="<td align='center'><font face='Verdana' size='2'>"+WeekDayName[i].substr(0,WeekChar)+"</font></td>";
	}
	vCalHeader+="</tr>";	
	docCal.write(vCalHeader);
	
	//Calendar detail
	CalDate=new Date(Cal.Year,Cal.Month);
	CalDate.setDate(1);
	vFirstDay=CalDate.getDay();
	vCalData="<tr>";
	for (i=0;i<vFirstDay;i++)
	{
		vCalData=vCalData+GenCellPOHeader(totalLineItems,partyCounter);
		vDayCount=vDayCount+1;
	}
	for (j=1;j<=Cal.GetMonDays();j++)
	{
		var strCell;
		vDayCount=vDayCount+1;
		if ((j==dtToday.getDate())&&(Cal.Month==dtToday.getMonth())&&(Cal.Year==dtToday.getFullYear()))
			strCell=GenCellPOHeader(totalLineItems,partyCounter,j,true,TodayColor);//Highlight today's date
		else
		{
			if (j==Cal.Date)
			{
				strCell=GenCellPOHeader(totalLineItems,partyCounter,j,true,SelDateColor);
			}
			else
			{	 
				if (vDayCount%7==0)
					strCell=GenCellPOHeader(totalLineItems,partyCounter,j,false,SaturdayColor);
				else if ((vDayCount+6)%7==0)
					strCell=GenCellPOHeader(totalLineItems,partyCounter,j,false,SundayColor);
				else
					strCell=GenCellPOHeader(totalLineItems,partyCounter,j,null,WeekDayColor);
			}		
		}						
		vCalData=vCalData+strCell;

		if((vDayCount%7==0)&&(j<Cal.GetMonDays()))
		{
			vCalData=vCalData+"</tr>\n<tr>";
		}
	}
	docCal.writeln(vCalData);	
	//Time picker
	if (Cal.ShowTime)
	{
		var showHour;
		showHour=Cal.getShowHour();		
		vCalTime="<tr>\n<td colspan='7' align='center'>";
		vCalTime+="<input type='text' name='hour' maxlength=2 size=1 style=\"WIDTH: 22px\" value="+showHour+" onchange=\"javascript:winMain.Cal.SetHour(this.value)\">";
		vCalTime+=" : ";
		vCalTime+="<input type='text' name='minute' maxlength=2 size=1 style=\"WIDTH: 22px\" value="+Cal.Minutes+" onchange=\"javascript:winMain.Cal.SetMinute(this.value)\">";
		vCalTime+=" : ";
		vCalTime+="<input type='text' name='second' maxlength=2 size=1 style=\"WIDTH: 22px\" value="+Cal.Seconds+" onchange=\"javascript:winMain.Cal.SetSecond(this.value)\">";
		if (TimeMode==12)
		{
			var SelectAm =(parseInt(Cal.Hours,10)<12)? "Selected":"";
			var SelectPm =(parseInt(Cal.Hours,10)>=12)? "Selected":"";

			vCalTime+="<select name=\"ampm\" onchange=\"javascript:winMain.Cal.SetAmPm(this.options[this.selectedIndex].value);\">";
			vCalTime+="<option "+SelectAm+" value=\"AM\">AM</option>";
			vCalTime+="<option "+SelectPm+" value=\"PM\">PM<option>";
			vCalTime+="</select>";
		}	
		vCalTime+="\n</td>\n</tr>";
		docCal.write(vCalTime);
	}	
	//end time picker
	docCal.writeln("\n</table>");
	docCal.writeln("</form></body></html>");
	docCal.close();
}

function GenCellPOHeader(totalLineItems,partyCounter,pValue,pHighLight,pColor)//Generate table cell with value
{
	var PValue;
	var PCellStr;
	var vColor;
	var vHLstr1;//HighLight string
	var vHlstr2;
	var vTimeStr;
	
	if (pValue==null)
		PValue="";
	else
		PValue=pValue;
	
	if (pColor!=null)
		vColor="bgcolor=\""+pColor+"\"";
	else
		vColor="";	
	if ((pHighLight!=null)&&(pHighLight))
		{vHLstr1="color='red'><b>";vHLstr2="</b>";}
	else
		{vHLstr1=">";vHLstr2="";}	
	
	if (Cal.ShowTime)
	{
		vTimeStr="winMain.document.getElementById('"+Cal.Ctrl+"').value+=' '+"+"winMain.Cal.getShowHour()"+"+':'+"+"winMain.Cal.Minutes"+"+':'+"+"winMain.Cal.Seconds";
		if (TimeMode==12)
			vTimeStr+="+' '+winMain.Cal.AMorPM";
	}	
	else
		vTimeStr="";		
	PCellStr="<td "+vColor+" width="+CellWidth+" align='center'><font face='verdana' size='2'"+vHLstr1+"<a href=\"javascript:winMain.document.getElementById('"+Cal.Ctrl+"').value='"+Cal.FormatDate(PValue)+"';"+vTimeStr+";winMain.setLineDate("+totalLineItems+","+partyCounter+");window.close();\">"+PValue+"</a>"+vHLstr2+"</font></td>";
	return PCellStr;
}

function submitCancelAction(path)
{
	if(confirm("This action will exit from the current page. Are you sure you want to proceed?"))
	{
		document.orderListForm.action = path;
		document.orderListForm.submit();
	}
}

function acceptPOs(path)
{
	formname = document.orderListForm;
	var checkBoxTotal = formname.selectedEntry.length;
	var flag=0;
	if(checkBoxTotal==undefined)
	{
		checkBoxTotal = 1;
	}
	var selectedArr = "";
	if(checkBoxTotal>1)
	{
		var selectedCounter = 0;
		for(j=0;j<checkBoxTotal;j=j+1)
		{
			if(formname.selectedEntry[j].checked)
			{
	    		selectedCounter = selectedCounter + 1;
	    		if(flag==0)
				{
					selectedArr = formname.selectedEntry[j].value;
					flag=1;
				}
				else if(flag!=0)
					selectedArr = selectedArr + "|" + formname.selectedEntry[j].value;
				else{
					alert("Please select atleast one Purchase Order.");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			alert("Please select atleast one Purchase Order.");
			return;
		}
	}
	else if(checkBoxTotal==1)
	{
		if(formname.selectedEntry.checked)
		{
    		if(flag==0)
			{
				selectedArr = formname.selectedEntry.value;
				flag=1;
			}
			else if(flag!=0)
				selectedArr = selectedArr + "|" + formname.selectedEntry[j].value;
			else{
				alert("Please select atleast one Purchase Order.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select atleast one Purchase Order.");
    		return;
    	}
	}
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
}


 function acceptPOsNew(path)
{
	formname = document.orderListForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	alert("Please select atleast one Purchase Order.");
	return;
	}else{
	var idsArray = modifiedHiddenVal.split(',');
	selectedArr=selectedArr+idsArray[0];
	for(var r=1;r<idsArray.length;r++){
	
	selectedArr = selectedArr  + "|" + trim(idsArray[r])
	
	}
	
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE SEARCH IS GOING ON..";
    }
}



//function for export to excel functionality
function acceptPOExcel(path,partyType)
{
	formname = document.orderListForm;
	var checkBoxTotal = formname.selectedEntry.length;
	var flag=0;
	if(checkBoxTotal==undefined)
	{
		checkBoxTotal = 1;
	}
	var selectedArr = "";
	if(checkBoxTotal>1)
	{
		var selectedCounter = 0;
		for(j=0;j<checkBoxTotal;j=j+1)
		{
			if(formname.selectedEntry[j].checked)
			{
	    		selectedCounter = selectedCounter + 1;
	    		if(flag==0)
				{
					selectedArr = formname.selectedEntry[j].value;
					flag=1;
				}
				else if(flag!=0)
					selectedArr = selectedArr + "|" + formname.selectedEntry[j].value;
				else{
					if(partyType=='V')
			    		alert("Please select atleast one Purchase Order.");
			    	else if(partyType=='S')
			    		alert("Please select atleast one Furnished Order.");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			if(partyType=='V')
    			alert("Please select atleast one Purchase Order.");
    		else if(partyType=='S')
    			alert("Please select atleast one Furnished Order.");
			return;
		}
	}
	else if(checkBoxTotal==1)
	{
		if(formname.selectedEntry.checked)
		{
    		if(flag==0)
			{
				selectedArr = formname.selectedEntry.value;
				flag=1;
			}
			else if(flag!=0)
				selectedArr = selectedArr + "|" + formname.selectedEntry[j].value;
			else{
				if(partyType=='V')
		    		alert("Please select atleast one Purchase Order.");
		    	else if(partyType=='S')
		    		alert("Please select atleast one Furnished Order.");
				return;
			}
    	}
    	else
    	{
    		if(partyType=='V')
    			alert("Please select atleast one Purchase Order.");
    		else if(partyType=='S')
    			alert("Please select atleast one Furnished Order.");
			return;
    	}
	}
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	
}

 function acceptPOExcelNew(path,partyType)
{
    
	formname = document.orderListForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	if(modifiedHiddenVal.length==0){
	if(partyType=='V'||partyType=='M')
	   alert("Please select atleast one Purchase Order.");
	else if(partyType=='S')
	   alert("Please select atleast one Furnished Order.");
	return;
	}else{
	var idsArray = modifiedHiddenVal.split(',');
	selectedArr=selectedArr+idsArray[0];
	for(var r=1;r<idsArray.length;r++){
	
	selectedArr = selectedArr  + "|" + trim(idsArray[r])
	
	}
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	}
}




  
function newPOStatusDelMsg(path,partyType)
{
	formname = document.orderListForm;
	var checkBoxTotal = formname.selectedEntry.length;
	var flag=0;
	if(checkBoxTotal==undefined)
	{
		checkBoxTotal = 1;
	}
	var selectedArr = "";
	if(checkBoxTotal>1)
	{
		var noOfSelected = 0;
		for(j=0;j<checkBoxTotal;j=j+1)
		{
			if(formname.selectedEntry[j].checked)
			{
				selectedArr = formname.selectedEntry[j].value;
				noOfSelected=noOfSelected+1;
	    	}
		}
		if(noOfSelected>1)
		{
			if(partyType=='V')
				alert("Please select only one Purchase Order.");
			else if(partyType=='S')
				alert("Please select only one Furnished Order.");	
			return;
		}
	}
	else if(checkBoxTotal==1)
	{
		if(formname.selectedEntry.checked)
		{
    		if(flag==0)
			{
				selectedArr = formname.selectedEntry.value;
				flag=1;
			}
			else if(flag!=0)
				selectedArr = selectedArr + "|" + formname.selectedEntry[j].value;
			else{
				if(partyType=='V')
					alert("Please select only one Purchase Order.");
				else if(partyType=='S')
					alert("Please select only one Furnished Order.");
				return;
			}
    	}
    	else
    	{
    		if(partyType=='V')
	    		alert("Please select only one Purchase Order.");
	    	else if(partyType=='S')
	    		alert("Please select only one Furnished Order.");
    		return;
    	}
	}
	if(selectedArr=="")
	{
		if(partyType=='V')
    		alert("Please select atleast one Purchase Order.");
    	else if(partyType=='S')
    		alert("Please select atleast one Furnished Order.");
		return;
	}
	var temp = selectedArr.split('-');
	var obj = document.getElementsByName('querystr_'+temp[0]);
	var newpath=path+'?'+obj[0].value;
	
	formname.checksArr.value=selectedArr;
	formname.action = newpath;
	formname.submit();
}


  function newPOStatusDelMsgNew(path,partyType)
{
	formname = document.orderListForm;
	
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	 if(partyType=='V'||partyType=='M')
    	alert("Please select atleast one Purchase Order.");
     else if(partyType=='S')
    	alert("Please select atleast one Furnished Order.");
    	
	return;
	}else{
	var idsArray = modifiedHiddenVal.split(',');
	if(idsArray.length>1){
	
	if(partyType=='V' ||partyType=='M')
		alert("Please select only one Purchase Order.");
	 else if(partyType=='S')
	   alert("Please select only one Furnished Order.");
	return;
	
	}else{
	selectedArr=selectedArr+idsArray[0];
	
		if(partyType=='S')
		{
			var temp1 = selectedArr.split('-');
			var obj1 = document.getElementsByName('orderType_'+temp1[0]);
			if(obj1[0].value == 'O')
			{
				alert("Cannot post Goods Receipt for Paper Furnished Order from list page");
				return;
			}
			else
			{
				var temp = selectedArr.split('-');
				var obj = document.getElementsByName('querystr_'+temp[0]);
				
				var newpath=path+'?'+obj[0].value;
				
				formname.checksArr.value=selectedArr;
				formname.action = newpath;
				formname.submit();
			}
		}
		else
		{
	
			var temp = selectedArr.split('-');
			var obj = document.getElementsByName('querystr_'+temp[0]);
			if(partyType=='M')
			{
			var newpath=path+'?'+obj[0].value+'&action=POList&PAGE_VALUE=1&fo=paperfo';
			}
			else
			{
			var newpath=path+'?'+obj[0].value;
			}
			var orderStatus=document.getElementById('orderStatus_'+temp[0]).value;
			orderStatus=trim(orderStatus);
			if((partyType=='M'||partyType=='V')&&orderStatus=='Cancelled'){
			    alert("Cannot post Delivery Message for Cancelled PO.");
				return;
			  }	
			else{
			
			if(partyType=='M' && document.getElementById('lineItem_'+temp[0]).value==0)
			{
				alert("No Line items exist for PO.");
				return;
			}
			else
			{
				formname.checksArr.value=selectedArr;
				formname.action = newpath;
				formname.submit();
			}
			}
		}
	}
 }
 
}

//-----------------------------------------------------------
function newPOStatus(path,partyType){

formname = document.orderListForm;
	
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0)
	{
	   	alert("Please select atleast one Purchase Order.");    	
		return;
	}
	else
	{
		var idsArray = modifiedHiddenVal.split(',');
		if(idsArray.length>1)
		{
			alert("Please select only one Purchase Order.");	 
			return;
		}
		else
		{
			selectedArr=selectedArr+idsArray[0];
			var temp = selectedArr.split('-');
			var obj = document.getElementsByName('querystr_'+temp[0]);
			var newpath=path+'?'+obj[0].value;
			var orderStatus=document.getElementById('orderStatus_'+temp[0]).value;
			orderStatus=trim(orderStatus);
			if(partyType=='V'&&orderStatus=='Cancelled')
			{
			    alert("Cannot post New Status for Cancelled PO.");
				return;
			}
			else
			{
				formname.checksArr.value=selectedArr;
				formname.action = newpath;
				formname.submit();
			}	
		}
	 }
}

//-----------------------------------------------------------


function expandCollapse(id) {
	var element = document.getElementById(id);
	element.style.display = (element.style.display == "none") ? "block" : "none";
}

function hideDiv(tableID)
{
	document.getElementById(tableID).style.display="none";
}

/*  After 22 Sep 2006 */
function sDetails(x,path){
	if(document.getElementById('a_title'+x).className=='lTitle'){
		document.getElementById('a_title'+x).className='lTitle_show'
		document.getElementById('a_hContent'+x).className='sContent';
		document.getElementById('a_img'+x).title="Click to Collapse"
		document.getElementById('a_img'+x).src=path+"/images/collapse.gif"
		
	}
	else
	{
		document.getElementById('a_title'+x).className='lTitle'
		document.getElementById('a_hContent'+x).className='hide';
		document.getElementById('a_img'+x).src=path+"/images/expand.gif";
		document.getElementById('a_img'+x).title="Click to Expand";	
	}
}

 
 function filtercheck()
{
if(!(event.keyCode >= 48 && event.keyCode <= 57))
{
 return false;
}
}
