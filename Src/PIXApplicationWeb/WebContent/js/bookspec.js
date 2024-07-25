//function for filtering with previous next
function listfilter(path)
{
document.bookspecform.action=path;
document.bookspecform.submit();
}

function acceptBookSpec(path)
{
	formname = document.bookspecform;
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
					alert("Please select atleast one Book Specification.");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			alert("Please select atleast one Book Specification.");
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
				alert("Please select atleast one Book Specification.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select atleast one Book Specification.");
    		return;
    	}
	}
	
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
}


 function acceptBookSpecNew(path)
{
	formname = document.bookspecform;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	alert("Please select atleast one Book Specification.");
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



function acceptBookSpecExcel(path)
{
	formname = document.bookspecform;
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
					selectedArr = selectedArr + "," + formname.selectedEntry[j].value;
				else{
					alert("Please select atleast one Book Specification.");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			alert("Please select atleast one Book Specification.");
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
				selectedArr = selectedArr + "," + formname.selectedEntry[j].value;
			else{
				alert("Please select atleast one Book Specification.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select atleast one Book Specification.");
    		return;
    	}
	}
	
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	
}

  function acceptBookSpecExcelNew(path)
{
	formname = document.bookspecform;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	alert("Please select atleast one Book Specification.");
	return;
	}else{
	var idsArray = modifiedHiddenVal.split(',');
	selectedArr=selectedArr+idsArray[0];
	for(var r=1;r<idsArray.length;r++){
	
	selectedArr = selectedArr  + "," + trim(idsArray[r])
	
	}
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	}
	
}


function submitBspCancelAction(path)
{
if(confirm("This action will exit from the current page. Are you sure want to proceed?"))
	{
	document.bookspecform.action = path;
	document.bookspecform.submit();
	
}
}
function submitInventoryDetail(path)
{

	formname = eval('document.inventoryForm');
	if(formname.elements["party.san"].value=="")
	{
	alert("PARTY NAME IS REQUIRED");
	
	}
	
	else if(formname.elements["isbn10"].value=="" && formname.elements["rawMaterialNo"].value=="")
	{
	alert("Either ISBN or RAW MATERIAL NUMBER is required for searching");
	
	}
	
	else
		{
			formname.action = path;
     		formname.submit();
     	}
	
}
function submitReportDetail(path)
{

	formname = eval('document.reportForm');
	if(formname.elements["reference.refCode"].value=="" )
	{
	alert("ITEM IS REQUIRED");
	
	}
	else if(formname.elements["reference.refCode"].value=="REP" )
	{
	alert("REPORT IS NOT A VALID MODULE FOR SEARCH");
	
	}
	
	else if(formname.elements["isbn10"].value=="" && formname.elements["porderNo"].value=="" && formname.elements["printNo"].value=="")
	{
	alert("Please specify ISBN,PURCHASE ORDER NUMBER OR PRINT NUMBER");
	
	}
	else if(formname.elements["reference.refCode"].value=="ALL" && formname.elements["isbn10"].value=="" && formname.elements["porderNo"].value=="" && formname.elements["printNo"].value=="" )
	{
	alert("EITHER ISBN,PURCHASE ORDER NUMBER OR PRINT NUMBER  IS REQUIRED FOR SEARCHING");
	
	}
	else if(!dateCheck(document.reportForm.endDate.value,document.reportForm.startDate.value,'>=','Start Date should be ','End Date'))
	{
	/*alert("enter valid date ,'>=','Start Date should be ','End Date'");*/
	
	}
	else if(formname.elements["reference.refCode"].value=="ALL" && formname.elements["isbn10"].value!="" || formname.elements["porderNo"].value!="" || formname.elements["printNo"].value!="" )
	{
			formname.action = path;
     		formname.submit();
	
	}
	
	else
		{
			formname.action = path;
     		formname.submit();
     	}
	
}
  
  function submitInvCancelAction(path)
{
	
	document.inventoryForm.action = path;
	document.inventoryForm.submit();
}

function filtercheck()
{
if(!(event.keyCode >= 48 && event.keyCode <= 57))
{
 return false;
}
}
function submitBspAction(path,obj)
{
	formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
 
}

function validate( vendorIndex )
{
	var formObj = eval('document.bookspecform');
	var errorString = "";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	if(formObj.elements["bookSpec.partyCollectionIdx["+vendorIndex+"].comments"].value!="")
	{
		var vendorComments = formObj.elements["bookSpec.partyCollectionIdx["+vendorIndex+"].comments"].value;
		if(vendorComments.length>255)
		{
			errorString=errorString+'<LI>Number of characters in vendor Comments cannot be more than 255</LI>';
		}
		/*if(vendorComments.indexOf("<")!= -1 || vendorComments.indexOf(">")!= -1)
		{
			errorString=errorString+'<LI>vendor Comments cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
		}*/
		
	}
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= '<LI>Please rectify the following errors :<br>'+errorString;
		window.location="#topofpage";
		return false;
	}
}
function validateInv( totalLineItems )
{
	var formObj = eval('document.inventoryForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	    inventoryQuantity = formObj.elements["inventoryCollectionIdx["+currIndex+"].stockInHand"].value;
		
		if(isNaN(inventoryQuantity)||inventoryQuantity< 0 || inventoryQuantity.indexOf('.') != -1)
		{
			errorString=errorString+'<LI>Please enter Valid Quantity For Line Item '+(currIndex+1)+'<br>';
		}
	}	
	
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
		return false;
	}
}

/*function limitheader(vendorIndex)
{
var formObj = eval('document.bookspecform');
var headerComments = formObj.elements["bookSpec.partyCollectionIdx["+vendorIndex+"].comments"].value;
if(headerComments.length>254)
{
alert('Sorry! Number of characters in Comments cannot be more than 255');
return false;
}

}*/
	



