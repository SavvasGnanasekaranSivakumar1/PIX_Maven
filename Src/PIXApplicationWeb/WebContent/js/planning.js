//function for filtering with previous next
function listfilter(path)
{
document.planningForm.action=path;
document.planningForm.submit();
}

function sfilter(path)
{
    
	if(!dateCheck(document.planningForm.endDate.value,document.planningForm.startDate.value,'>=','Start Date should be ','End Date'))
	{
	return false;
	/*alert("enter valid date ,'>=','Start Date should be ','End Date'"); 	formname = eval('document.orderListForm');		document.orderListForm.action=path;*/
	
	}
	
	if(!dateCheck(document.planningForm.ebBDate.value,document.planningForm.sbBDate.value,'>=','Start Date should be ','End Date'))
	{
		return false;		
	}
	
		document.planningForm.action=path;
		document.planningForm.submit();
     	
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
/*function limit(totalLineItems)
{
var formObj = eval('document.orderDetailForm');
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

//function for validating input
function validate(vendorIndex,totalLineItems)
{
	var formObj = eval('document.orderDetailForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	
	
	/*if(formObj.elements["poHeader.partyCollectionIdx["+vendorIndex+"].comments"].value=="")
	{
		errorString=errorString+'<LI>Please enter Comments for Header</LI>';
	}*/
	
	
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
						
		/*if(formObj.elements["poHeader.lineItemCollectionIdx["+currIndex+"].supplierComments"].value=="")
		{
			errorString=errorString+'Please enter Comments for Line Item '+(currIndex+1)+'</LI>';
		}*/
		
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





//function for enabling only numbers for input 
function filtercheck()
{
if(!(event.keyCode >= 48 && event.keyCode <= 57))
{
return false;
}
}
//function for accepting multiple planning for purchase orders

function acceptPlanningPOs(path)
{
	formname = document.planningForm;
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
					alert("Please select atleast one plan.");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			alert("Please select atleast one plan.");
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
				alert("Please select atleast one plan.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select atleast one plan.");
    		return;
    	}
	}
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE SEARCH IS GOING ON..";
}
 
 
  function acceptPlanningPOsNew(path)
{
	formname = document.planningForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	alert("Please select atleast one plan.");
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



 
 

//function for export to excel
function acceptPlanningPOExcel(path)
{
	formname = document.planningForm;
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
					alert("Please select atleast one plan.");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			alert("Please select atleast one plan.");
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
				alert("Please select atleast one plan.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select atleast one plan.");
    		return;
    	}
	}
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	
}
  
  function acceptPlanningPOExcelNew(path)
{
	formname = document.planningForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	alert("Please select atleast one plan.");
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




//function for submit action
function submitPlanningAction(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE SEARCH IS GOING ON..";
}

function submitPlanningActionExcel(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	
}


//function for cancel functionality
function submitPlanningCancelAction(path)
{
	if(confirm("This action will exit from the current page. Are you sure want to proceed?"))
	{
	document.planningForm.action = path;
	document.planningForm.submit();
    }
    
}
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
