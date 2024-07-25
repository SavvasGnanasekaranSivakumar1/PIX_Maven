//function for filtering with previous next
function listfilter(path)
{
document.costAccountingForm.action=path;
document.costAccountingForm.submit();
}



/*function approveDmList(path)
{
	formname = document.costAccountingForm;
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
					alert("Please select atleast one Delivery Message.");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			alert("Please select atleast one Delivery Message.");
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
				alert("Please select atleast one Delivery Message.");
				return;
			}
    	}
    	else
    	{
    		alert("Please select atleast one Delivery Message.");
    		return;
    	}
	}
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE SEARCH IS GOING ON..";
}*/


function setInitialQauntity(id,hiddId)
{
	// var formObj = eval('document.costAccountingForm');
	
	var inQ=document.getElementById(id).value;
	document.getElementById(hiddId).value=inQ ;
}

//Naveen- PGI
function submitUpdateNew(path,lineItemSize,ownrshpMode)
{
	
	var formObj = eval('document.costAccountingForm');
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	var errorString = "";
	var flag="";
	if(document.getElementById("changeQuantity").value ==''  && (ownrshpMode != 'CONSIGNMENT'))
	{
	
		alert("No line items are changed to update.");
	}
	else
	{
		
		var totalCumulative = 0;
		
		for(i=0;i<lineItemSize;i++)
		{
			totalCumulative = 0;
		
			var maxAmt = document.getElementById("maxAmtAllow"+i).value;
			var filledQauntity=parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollectionIdx["+i+"].owningQuantity"].value);
			//alert("maxAmt   "+maxAmt);
			
			
			
			//Naveen - PGI
			if(ownrshpMode=='OWNED ON DELIVERY'){
			
			//alert("In the OWNED ON DELIVERY loop");
			var initialQauntityHidd = parseInt(document.getElementById("initialQauntityHidd"+i).value);
			var postedQuantity=parseInt(document.getElementById("postedQuantity"+i).value);
			//alert('postedQuantity....'+postedQuantity);
			//alert('initialQauntityHidd....'+initialQauntityHidd);
			var remainsQauntity=postedQuantity-initialQauntityHidd;
			var maxAmtIn=parseInt(maxAmt);
			//alert('remainsQauntity....'+remainsQauntity);
			//alert('maxAmtIn....'+maxAmtIn);
			var toleranceQauntity=maxAmtIn-remainsQauntity;
			//alert('toleranceQauntity....'+toleranceQauntity);
			//alert("filledQauntity   "+filledQauntity);
			if(filledQauntity > 0)
			{
				if(filledQauntity <= toleranceQauntity)
	     			{
	     				flag = "ok";
	     				continue;
	        		}
	        	else
	        		{
	        			errorString='Owning Quantity is not within the tolerance limit.';
	        			flag = "";
	        			break;
	        	
	        		}  
	        	}
	      	else{
	      			  errorString='Owning Quantity cannot be zero or negative.';
	       			 flag= "";
	       			 break;
	        		}
	       
	        }
	       else if(ownrshpMode=='CONSIGNMENT')
	       {
	       //alert("In the CONSIGNMENT loop");
	       		if(filledQauntity > 0)
				{
				if(filledQauntity <= maxAmt)
	     			{
	     				flag = "ok";
	     				continue;
	        		}
	        	else
	        		{
	        			errorString='Owning Quantity is greater than Received Quantity.';
	        			flag = "";
	        			break;
	        	
	        		}  
	        	}
	        	 else{
	      			  errorString='Owning Quantity cannot be zero or negative.';
	       			 flag= "";
	       			 break;
	        		}
	       
	       
	       	}
	       
		}
		//Naveen - PGI
		if (flag != "ok")
		{
			objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
			window.location="#topofpage";
			return false;
		}
		else
		{
			document.costAccountingForm.action=path;
			document.costAccountingForm.submit();
			document.body.style.cursor = "wait";
    		buttons3.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON..."; 
		}
	}
	
}

function submitUpdateNew1(path,lineItemSize,ownrshpMode)
{

	var formObj = eval('document.costAccountingForm');
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	var errorString = "";
	var flag="";
	if(document.getElementById("changeQuantity").value ==''  && (ownrshpMode != 'CONSIGNMENT'))
	{
	
		alert("No line items are changed to update.");
	}
	else
	{
		
		var totalCumulative = 0;
		
		for(i=0;i<lineItemSize;i++)
		{
			totalCumulative = 0;
		
			var maxAmt = document.getElementById("maxAmtAllow"+i).value;
			var filledQauntity=parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollectionIdx["+i+"].owningQuantity"].value);
			
			
			
			//Naveen - PGI
			if(ownrshpMode=='OWNED ON DELIVERY'){
			
			//alert("In the OWNED ON DELIVERY loop");
			var initialQauntityHidd = parseInt(document.getElementById("initialQauntityHidd"+i).value);
			var postedQuantity=parseInt(document.getElementById("postedQuantity"+i).value);
			//alert('postedQuantity....'+postedQuantity);
			//alert('initialQauntityHidd....'+initialQauntityHidd);
			var remainsQauntity=postedQuantity-initialQauntityHidd;
			var maxAmtIn=parseInt(maxAmt);
			//alert('remainsQauntity....'+remainsQauntity);
			//alert('maxAmtIn....'+maxAmtIn);
			var toleranceQauntity=maxAmtIn-remainsQauntity;
			//alert('toleranceQauntity....'+toleranceQauntity);
			if(filledQauntity > 0)
			{
				if(filledQauntity <= toleranceQauntity)
	     			{
	     				flag = "ok";
	     				continue;
	        		}
	        	else
	        		{
	        			errorString='Owning Quantity is not within the tolerance limit.';
	        			flag = "";
	        			break;
	        	
	        		}  
	        	}
	      	else{
	      			  errorString='Owning Quantity cannot be zero or negative.';
	       			 flag= "";
	       			 break;
	        		}
	       
	        }
	       else if(ownrshpMode=='CONSIGNMENT')
	       {
	       //alert("In the CONSIGNMENT loop");
	       		if(filledQauntity > 0)
				{
				if(filledQauntity <= maxAmt)
	     			{
	     				flag = "ok";
	     				continue;
	        		}
	        	else
	        		{
	        			errorString='Owning Quantity is greater than Received Quantity.';
	        			flag = "";
	        			break;
	        	
	        		}  
	        	}
	        	 else{
	      			  errorString='Owning Quantity cannot be zero or negative.';
	       			 flag= "";
	       			 break;
	        		}
	       
	       
	       	}
	       
		}
		//Naveen - PGI
		if (flag != "ok")
		{
			objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
			window.location="#topofpage";
			return false;
		}
	
	}
	
}


function submitUpdate(path,lineItemSize)
{
	
	var formObj = eval('document.costAccountingForm');
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	var errorString = "";
	var flag="";
	if(document.getElementById("changeQuantity").value =='')
	{
		alert("No line items are changed to update.");
	}
	else
	{
		
		var totalCumulative = 0;
		for(i=0;i<lineItemSize;i++)
		{
			totalCumulative = 0;
			var maxAmt = document.getElementById("maxAmtAllow"+i).value;
			totalCumulative = totalCumulative + parseInt(document.getElementById("postedQuantity"+i).value) + parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollectionIdx["+i+"].owningQuantity"].value);
			 
			if(parseInt(totalCumulative) <= parseInt(maxAmt))
	     	{
	     		flag = "ok";
	     		continue;
	        }
	        else
	        {
	        	errorString='Owning Quantity is not within the tolerance limit .';
	        	flag = "";
	        }  
		}
		if (flag != "ok")
		{
			objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
			window.location="#topofpage";
			return false;
		}
		else
		{
			document.costAccountingForm.action=path;
			document.costAccountingForm.submit();
		}
	}
	
}
function submitClose(path)
{
	document.costAccountingForm.action=path;
	document.costAccountingForm.submit();
}

function acceptdelMsgExcel(path)
{
	formname = document.costAccountingForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	alert("Please select atleast one Approval Message.");
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


function submitApprove2(){
alert("hi");
}
function submitdelMsgExcel(path)
{
	document.costAccountingForm.action=path;
	document.costAccountingForm.submit();
}

function submitApprove(path,lineItemSize,ownrshpMode)
{
	
	if(ownrshpMode == 'CONSIGNMENT'){
		
	var formObj = eval('document.costAccountingForm');
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	var errorString = "";
	var flag="";
	if(document.getElementById("changeQuantity").value ==''  && (ownrshpMode != 'CONSIGNMENT'))
	{
	
		alert("No line items are changed to update.");
	}
	else
	{
		
		var totalCumulative = 0;
		
		for(i=0;i<lineItemSize;i++)
		{
			totalCumulative = 0;
		
			var maxAmt = document.getElementById("maxAmtAllow"+i).value;
			var filledQauntity=parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollectionIdx["+i+"].owningQuantity"].value);
			
			
			
			//Naveen - PGI
			if(ownrshpMode=='OWNED ON DELIVERY'){
			
			//alert("In the OWNED ON DELIVERY loop");
			var initialQauntityHidd = parseInt(document.getElementById("initialQauntityHidd"+i).value);
			var postedQuantity=parseInt(document.getElementById("postedQuantity"+i).value);
			//alert('postedQuantity....'+postedQuantity);
			//alert('initialQauntityHidd....'+initialQauntityHidd);
			var remainsQauntity=postedQuantity-initialQauntityHidd;
			var maxAmtIn=parseInt(maxAmt);
			//alert('remainsQauntity....'+remainsQauntity);
			//alert('maxAmtIn....'+maxAmtIn);
			var toleranceQauntity=maxAmtIn-remainsQauntity;
			//alert('toleranceQauntity....'+toleranceQauntity);
			if(filledQauntity > 0)
			{
				if(filledQauntity <= toleranceQauntity)
	     			{
	     				flag = "ok";
	     				continue;
	        		}
	        	else
	        		{
	        			errorString='Owning Quantity is not within the tolerance limit.';
	        			flag = "";
	        			break;
	        	
	        		}  
	        	}
	      	else{
	      			  errorString='Owning Quantity cannot be zero or negative.';
	       			 flag= "";
	       			 break;
	        		}
	       
	        }
	       else if(ownrshpMode=='CONSIGNMENT')
	       {
	       //alert("In the CONSIGNMENT loop");
	       		if(filledQauntity > 0)
				{
				if(filledQauntity <= maxAmt)
	     			{
	     				flag = "ok";
	     				continue;
	        		}
	        	else
	        		{
	        			errorString='Owning Quantity is greater than Received Quantity.';
	        			flag = "";
	        			break;
	        	
	        		}  
	        	}
	        	 else{
	      			  errorString='Owning Quantity cannot be zero or negative.';
	       			 flag= "";
	       			 break;
	        		}
	       
	       
	       	}
	       
		}
		//Naveen - PGI
		if (flag != "ok")
		{
			objError.innerHTML= 'Please rectify the following errors :<br>'+errorString;
			window.location="#topofpage";
			return false;
		}
	
	}
		
		
	}
	
	if(document.getElementById("changeQuantity").value =='' || ownrshpMode == 'CONSIGNMENT')
	{
		
		formname = document.costAccountingForm;
		if(formname.selectedEntry != null)
		{
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
							alert("Please select atleast one Line Item to Approve.");
							return;
						}
			    	}
				}
		    	if(selectedCounter == 0)
				{
					alert("Please select atleast one Line Item to Approve.");
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
						alert("Please select atleast one Line Item to Approve.");
						return;
					}
		    	}
		    	else
		    	{
		    		alert("Please select atleast one Line Item to Approve.");
		    		return;
		    	}
			}
			
			formname.checksArr.value=selectedArr;
			//alert('checkarr val:'+formname.checksArr.value);
			document.costAccountingForm.action=path;
			document.costAccountingForm.submit();
			document.body.style.cursor = "wait";
    		buttons3.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON..."; 
		}
		else
		{
			alert("No Line items to approve");
		}
	}
	else
	{
		alert("Please Click update to change the quantity or ship Date.");
	}
}

function addQuanChange(strToAdd)
{
	formname = eval("document.costAccountingForm");
	if(document.getElementById("changeQuantity").value =='')
	{
		document.getElementById("changeQuantity").value = strToAdd;
	}
	else
	{
		document.getElementById("changeQuantity").value = document.getElementById("changeQuantity").value + "," + strToAdd;	
	}
	
}

function submitdelPdf(path)
{
	document.costAccountingForm.action=path;
	document.costAccountingForm.submit();
}


function numbersonlyNew1(id)
{
   var flag=0;
   var text=document.getElementById('del_quantity'+id).value
   if(event.keyCode==45){
   flag=1;
   }
	if(((event.keyCode==8)||(event.keyCode==45) || (event.keyCode==63272) || (event.keyCode==63234) || (event.keyCode==63235))&&flag==0){
	    
		return true;
	}
	else
	{
	if (((event.keyCode<48||event.keyCode>57)&&event.keyCode!=46) || event.keyCode==46)
    { 
        return false;
    }}
}



function dmDetailspopUp(contx,msgNo,ownershipMode,pono)
   {
	//   	alert(pono);
      //   alert(productCode);
      //    alert(poid);
      //    alert('hi');
     //   path = contx+'/goodsreceipt/goodsreceiptpapernew.do?paperlist=dmdetailist&poid=7000000212&productcode=0000003941&poversion=3';
     //	path = contx+'/goodsreceipt/goodsreceiptpapernew.do?paperlist=dmdetailist&poid='+poid+'&productcode='+productCode+'&poversion='+poversion+'&msgNo='+msgNo+'&grRoleTracking='+grRoleTracking;  
        
        path = contx+'/costaccounting/deliverymessagedetail.do?operation=ownedQtyPopup&msgNo='+msgNo+'&ownershipMode='+ownershipMode+'&pono='+pono;
        window.open(path,'dmdetails','height=450,width=1000,left=0,top=0');  
   }


