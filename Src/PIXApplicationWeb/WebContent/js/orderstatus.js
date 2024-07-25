function listfilter5(path)
{

document.orderStatusForm.action=path;
document.orderStatusForm.submit();
}



function validateOrderStatus(totalLineItems)
{
	var formObj = eval('document.orderStatusForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
  	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
		if(formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].estimatedDate"].value=="" ||
		    formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].statusId"].value=="" )
			{
				if(errorString != null){
					errorString='<LI> Please select Estimated Delivery Date and Status for atleast one Line Item </LI>';
				}
			}
		
	    else{	    
		if(formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].estimatedDate"].value!="" && 
	    formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].statusId"].value!=""){
	      	errorString='';
	    	break;
	    }    	
		}
	
	  /*  if(formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].estimatedDate"].value=="")
		{
			errorString=errorString+'<LI> Please select Estimated Delivery Date for Line Item '+(currIndex+1)+'</LI>';
		}
	    if(formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].statusId"].value=="")
		{
			errorString=errorString+'<LI> Please select Status for Line Item '+(currIndex+1)+'</LI>';
		}
		
		if(formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].comments"].value=="" )
		{
			errorString=errorString+'<LI><FONT COLOR="RED"> Please enter Comments for Line Item '+(currIndex+1)+'</FONT></LI>';
		}*/
		if(formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].comments"].value != "")
		{
			var lineComments = formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].comments"].value;
			if(lineComments.length>255)
			{
				errorString=errorString+'<LI> Number of characters in Line Item '+(currIndex+1)+' Comments cannot be more than 255 </LI>';
			}
			if(lineComments.indexOf("<")!= -1 || lineComments.indexOf(">")!= -1)
			{
				errorString=errorString+'<LI> Line Item '+(currIndex+1)+' Comments cannot contain special characters &quot&lt;&quot and &quot&gt;&quot.</LI>';
			}
			
		}
	}    
	if(errorString=='')
	{
		return true;
	}
	else
	{
		objError.innerHTML='Please rectify the following errors :<br>'+ errorString;
		formObj.action="#error_div";
		return false;
	}
}
function acceptOrderStatus(path)
{
	formname = document.orderStatusForm;
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
					alert("Please select alteast one Order Status.");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			alert("Please select alteast one Order Status.");
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
				alert("Please select alteast one Order Status.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select alteast one Order Status.");
    		return;
    	}
	}
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	    
}

 function acceptOrderStatusNew(path)
{
	formname = document.orderStatusForm;
	var HiddenVal = formname.idHidden.value;
	
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	
	   alert("Please select alteast one Order Status.");
	
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
function submitCancelAction(path)
{
	if(confirm("This action will exit from the current page. Are you sure you want to proceed?"))
	{
		document.orderStatusForm.action = path;
		document.orderStatusForm.submit();
	}
}

function submitStatusAction(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	
}

//function to limit character input for line items
function limitChar(totalLineItems)
{
var formObj = eval('document.orderStatusForm');
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	var lineComments = formObj.elements["orderStatus.lineItemCollectionIdx["+currIndex+"].comments"].value;
		if(lineComments.length>=255)
		{
		alert('Number of characters in Comments cannot be more than 255');
		return false;
		}
	}
}