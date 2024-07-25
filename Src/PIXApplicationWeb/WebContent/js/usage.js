//function for filtering with previous next
function listfilter(path)
{
	document.usageFormList.action=path;
	document.usageFormList.submit();
}

//function to limit character input for line items
/*function limitChar(totalLineItems)
{
var formObj = eval('document.usageForm');
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	var lineComments = formObj.elements["usage.usageLineItemCollectionIdx["+currIndex+"].comments"].value;
		if(lineComments.length>=255)
		{
		alert('Number of characters in Comments cannot be more than 255');
		return false;
		}
	}
}*/

//validation of form fields
function validate(totalLineItems)
{
   	var formObj = eval('document.usageForm');
	var errorString = "";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
  	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	    usageQuantity = formObj.elements["usage.usageLineItemCollectionIdx["+currIndex+"].usageQuantity"].value;
		if(isNaN(usageQuantity) || usageQuantity < 0 || usageQuantity.indexOf('.') != -1)
		{
			errorString=errorString+'<LI>Please Enter valid Usage Quantity Number for Line Item '+(currIndex+1)+'</LI>';
		}
		damagedQuantity = formObj.elements["usage.usageLineItemCollectionIdx["+currIndex+"].damagedQuantity"].value;
		if(isNaN(damagedQuantity) || damagedQuantity < 0 || damagedQuantity.indexOf('.') != -1)
		{
			errorString=errorString+'<LI>Please Enter valid Damaged Quantity Number for Line Item '+(currIndex+1)+'</LI>';
		}
		/*if(formObj.elements["usage.usageLineItemCollectionIdx["+currIndex+"].comments"].value=="" )
		{
			errorString=errorString+'<LI>Please enter Comments for Line Item '+(currIndex+1)+'</LI>';
		}*/
		if(formObj.elements["usage.usageLineItemCollectionIdx["+currIndex+"].comments"].value!="")
		{
			var lineComments = formObj.elements["usage.usageLineItemCollectionIdx["+currIndex+"].comments"].value;
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
		objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
		formObj.action="#error_div";
		return false;
	}
	
}

function newUsage(path)
{
	formname = document.usageFormList;
	var checkBoxTotal = formname.selectedEntry.length;
	var flag=0;
	if(checkBoxTotal==undefined)
	{
		checkBoxTotal = 1;
	}
	var selectedArr = "";
	if(checkBoxTotal>1)
	{
		for(j=0;j<checkBoxTotal;j=j+1)
		{
			if(formname.selectedEntry[j].checked)
			{
				selectedArr = formname.selectedEntry[j].value;
	    	}
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
				alert("Please select purchase order .");
				return;
			}
    	}
    	else
    	{
    		alert("Please select purchase order .");
    		return;
    	}
	}
	if(selectedArr=="")
	{
		alert("Please select purchase order .");
		return;
	}
	var querystring = eval('formname.querystr_'+selectedArr+'.value');
	var newpath=path+'?'+querystring;
	formname.action = newpath;
	formname.submit();
	
}
function submitCancelAction(path)
{
	if(confirm("This action will exit from the current page. Are you sure you want to proceed?"))
	{
		document.usageFormList.action = path;
		document.usageFormList.submit();
	}
}

function submitUsageAction(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
}
