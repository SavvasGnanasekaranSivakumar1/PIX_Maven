function listfilter4(path)
{
document.goodsReceiptForm.action=path;
document.goodsReceiptForm.submit();
}


function validate(totalLineItems)
{
   // alert('in the method');
	var formObj = eval('document.goodsReceiptForm');
	var errorString = "";
	var errorStringHead="";
	var errorString1="";
	var objError=document.getElementById("error_div");
	var flg = "";
	var onlyNumber= true;
	
	/*if(formObj.elements["goodsreceipt.headerAcceptanceDescription"].value=="")
	{
	    errorStringHead='<LI>Please select Goods Condition at Header Level<br>';
	    errorString = errorStringHead;
	}
	*/
	 onlyNumber =  onlyNumericQuantity(totalLineItems);
	 if(onlyNumber== false)
	 {
	 	errorString=errorString+'<LI>Recieved Quantity can have only positive numeric values.</LI>';
	 }
	
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	/*if((formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value != "") && 
			(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].actualArrivalDate"].value != "")){*/
	if(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value != ""){
	/*		receivedQuantity = formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value;
			if(isNaN(receivedQuantity) || receivedQuantity < 0 || receivedQuantity.indexOf('.') != -1 )
			{
				errorString1='<LI>Please enter Valid Numeric Quantity for Line Item '+(currIndex+1)+'</LI>';
			}
			//errorString=errorStringHead+errorString;
	      	
	      	
	      	if(errorStringHead != "" || errorString1 != "")
	     		errorString=errorStringHead+errorString1+"";
	     	else
	     		errorString=errorStringHead+"";
	      	break;  */

	      	flg = "ok";
	      	continue;
	    }
	else if((formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value !="") || 
	    (formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].actualArrivalDate"].value !=""))
	    {
	   
	    	errorString ='<LI> Please enter valid quantity and ActualArrivalDate for atleast one Line Item </LI>';
			
			if(errorStringHead != "")
				errorString=errorStringHead+errorString;
	    }
	    
	    
	    }
		
		if (flg != "ok")
		{

			errorString ='<LI> Please enter valid quantity and ActualArrivalDate for atleast one Line Item </LI>';
			
			if(errorStringHead != "")
				errorString=errorStringHead+errorString;
		} 
		
		/*if(receivedQuantity< 0 || receivedQuantity.indexOf('.') != -1 || isNaN(receivedQuantity))
		{
			errorString=errorString+'<LI>Please enter Valid Numeric Received Quantity for Line Item '+(currIndex+1)+'</LI>';
		}
		
		if(isNaN(intransitDamagedQty) || intransitDamagedQty < 0 || intransitDamagedQty.indexOf('.') != -1 )
		{
			errorString=errorString+'<LI>Please enter Valid Numeric Transport Damaged Quantity for Line Item '+(currIndex+1)+'</LI>';
		}
		
		if(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].actualArrivalDate"].value == "")
		{
			errorString=errorString+'<LI>Please select Actual Arrival Date for Line Item '+(currIndex+1)+'</LI>';
		}
		
	    if(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].acceptanceDescription"].value=="")
	    {
		     errorString=errorString+'<LI>Please select Goods Condition for Line '+(currIndex+1)+'</LI>';
			                      
	    }*/
	    
	    
	    
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

function validateMillGR(totalLineItems)
{
   // alert('in the method');
	var formObj = eval('document.goodsReceiptForm');	
	var errorString = "";
	var errorStringHead="";
	var errorString1="";
	var objError=document.getElementById("error_div");
	var flg = "";
	var totalCumulative = 0;
	var onlyNumber= true;
	objError.innerHTML= "";

	if(formObj.elements["goodsreceipt.headerAcceptanceDescription"].value=="")
	{
	    errorStringHead='<LI>Please select Goods Condition at Header Level<br>';
	    errorString = errorStringHead;
	}
	 onlyNumber =  onlyNumericQuantity(totalLineItems);
	 if(onlyNumber== false)
	 {
	 	errorString=errorString+'<LI>Recieved Quantity can have only positive numeric values.</LI>';
	 }
	
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
		totalCumulative = 0;
		totalCumulative = totalCumulative + parseInt(document.getElementById("cumulativeQuantityRec"+currIndex).value);
	if((formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value != "") && 
			(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].actualArrivalDate"].value != "")){
	
			var maxAmt = document.getElementById("maxAmtAllow"+currIndex).value;
	  		totalCumulative = totalCumulative + parseInt(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value);
	  		if(parseInt(totalCumulative) <= parseInt(maxAmt))
	     	{
	     		flg = "ok";
	     		continue;
	        }
	        else
	        {
	        	errorString='<LI> Received Quantity is not within the tolerance limit .</LI>';
	        } 
	    }
	else if((formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value !="") || 
	    (formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].actualArrivalDate"].value !=""))
	    {
	   		if(errorString=='')
			{
	    		errorString ='<LI> Please enter valid quantity and ActualArrivalDate for atleast one Line Item </LI>';
				if(errorStringHead != "")
					errorString=errorStringHead+errorString;
			}
	    }
	    
	    
	    }
		
		if (flg != "ok")
		{
			if(errorString=='')
			{
				errorString ='<LI> Please enter valid quantity and ActualArrivalDate for atleast one Line Item </LI>';
				
				if(errorStringHead != "")
					errorString=errorStringHead+errorString;
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
    var formObj = eval('document.goodsReceiptForm');
	var vendorStatus = obj.options[obj.selectedIndex].text;
	setLineStatus(totalLineItems,formObj,vendorStatus);
}

function setLineStatus(totalLineItems,formObj,newText)
{
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	    var objName = "goodsreceipt.goodsReceiptLineCollection["+currIndex+"].acceptanceDescription";
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




function acceptPOs(path)
{
	formname = document.goodsReceiptForm;
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
					alert("Please select alteast one Goods Receipt");
					return;
				}
	    	}
		}
    	if(selectedCounter == 0)
		{
			alert("Please select alteast one Goods Receipt");
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
				alert("please select atleast one Goods Receipt.");
				return;
			}
    	}
    	else
    	{
    		alert("please select atleast one Goods Receipt.");
    		return;
    	}
	}
	
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
}

 function acceptPOsNew(path)
{
	formname = document.goodsReceiptForm;
	var HiddenVal = formname.idHidden.value;
	
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	
	   alert("please select atleast one Goods Receipt.");
	
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
function submitCancelReceipt(path)
{
	document.goodsReceiptForm.action = path;
	document.goodsReceiptForm.submit();
}
function submitCancelReceipt1(path)
{
	if(isHistFormChanged())
		{
			if(confirm("All unsaved data will be lost. Do you want to proceed?"))
	    	{
	    	document.goodsReceiptForm.action = path;
			document.goodsReceiptForm.submit();
			}
	    }
	else
	    {
	    	document.goodsReceiptForm.action = path;
			document.goodsReceiptForm.submit();
	    }
	
}

function submitAction(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
	hideButtons.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
}
function submitGoodsAction2(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	

}

//function to validate the entered text is numeric
function onlyNumericQuantity(totalLineItems)
{
	var formObject = eval('document.goodsReceiptForm');	
	
	var digits = "0123456789";
	var temp="";	
	for(i=0;i<totalLineItems;i++)
	{
		var enteredQuantity = parseInt(formObject.elements["goodsreceipt.goodsReceiptLineCollection["+i+"].receivedQuantity"].value);
								 
		for(j=0;j<enteredQuantity.length;j++)
		{
			temp = enteredQuantity.substring(j,j+1)
			if (digits.indexOf(temp) == -1 && enteredQuantity != "")
   			 {
      			return false;
    		}
  		}
  	} 
 }
// RFS change 
/*function getPODetail1(path)
	{
		var url="";
	 	if(document.goodsReceiptForm.poNumber.value!=""){
			url = path+"pono="+document.goodsReceiptForm.poNumber.value;
		}
		//Do the Ajax call
      	if (window.ActiveXObject){ 
            req = new ActiveXObject("Microsoft.XMLHTTP"); 
        }else if (window.XMLHttpRequest){ 
            req = new XMLHttpRequest(); 
        } 
        req.open("GET", url, false); 
        req.onreadystatechange = function (){getPOInfo();};
        req.send(null);
    }*/
 function getPODetail1(ctx)
	{
		var url="";
	 	if(document.goodsReceiptForm.poNumber.value!=""){
			url = ctx+"/home/dmlist.do?"+"pono="+document.goodsReceiptForm.poNumber.value;
			//alert("Grv"+url);
		}
		//Do the Ajax call
      	if (window.ActiveXObject){ 
            req = new ActiveXObject("Microsoft.XMLHTTP"); 
        }else if (window.XMLHttpRequest){ 
            req = new XMLHttpRequest(); 
        } 
        req.open("GET", url, false); 
      //    alert("req.readyState " + req.readyState);
        //req.onreadystatechange = function (){getPOInfo();};
        req.send(null);
        return req.responseText;
    }
    
	//to generate full urlDM from the details fetched from backend by calling function
	/*function getPOInfo()
	{
	     if (req.readyState == 4) 
    { // Complete
      if(req.status == 200)
      { //OK
           xml = req.responseText;
              // alert("xml " + xml);
           msg1 = xml.split("</message>");
          //alert("msg1 " + msg1);
          countval = msg1[0].split("<message>");
          //alert("msg1 " + countval[1]);
          arr = countval[1].split(",");
          document.goodsReceiptForm.poid.value = arr[0];
          document.goodsReceiptForm.pover.value = arr[1];
          document.goodsReceiptForm.porel.value = arr[2];
          //alert("Grv"+ arr[0]+arr[1]+arr[2]);
           if(((countval[1].toString()).indexOf(","))!= -1)
          {
          arr = countval[1].split(",");
          document.goodsReceiptForm.poid.value = arr[0];
          document.goodsReceiptForm.pover.value = arr[1];
          document.goodsReceiptForm.porel.value = arr[2];
          document.goodsReceiptForm.poNumber.value = arr[3];
          document.goodsReceiptForm.poorderType.value=arr[4];
           }
           else
           {
           document.goodsReceiptForm.poid.value = "";
           document.goodsReceiptForm.pover.value = "";
           document.goodsReceiptForm.porel.value = "";	
           document.goodsReceiptForm.poorderType.value="";
           }
	
	}
	}
	}*/
	/*function submitSearch(path)
{
    var url="";
    if(document.goodsReceiptForm.poNumber.value=="")
	  {
	   alert('Purchase Order No. cannot be blank');
	   return;
	  }
	  else
	  {
	  	if(document.goodsReceiptForm.poid.value =="")
	  	 {
	  		alert('PO does not exist');
	  		return;
	  	 }
	  	else
	  	 {
   			url = path+"&pono="+document.goodsReceiptForm.poNumber.value+"&poid="+document.goodsReceiptForm.poid.value+"&poversion="+document.goodsReceiptForm.pover.value+"&rno="+document.goodsReceiptForm.porel.value+"&orderPaper="+document.goodsReceiptForm.poorderType.value+"&fo=paperfo";
    		document.goodsReceiptForm.action = url;
    		document.goodsReceiptForm.submit();
		 }
	  }	
}*/
function submitSearch(ctx,urlGRNew,urlGROld)
{
    var url="";
    if(document.goodsReceiptForm.poNumber.value=="")
	  {
	   alert('Purchase Order No. cannot be blank');
	   return;
	  }
	  else
	  {	  	
	  	var xml = getPODetail1(ctx);
	  	//alert(xml);
	  	var msg1 = xml.split("</message>");
        var countval = msg1[0].split("<message>");
	  	if(((countval[1].toString()).indexOf(","))!= -1)
	  	{
	  	  arr = countval[1].split(",");
          var poid = arr[0];
          var pover = arr[1];
          var porel = arr[2];
          var pono = arr[3];
          var poorderType=arr[4];
          var poOrder=arr[5];
		  if(poorderType == 'O')
			  	url = urlGRNew+"&pono="+pono+"&poid="+poid+"&poversion="+pover+"&rno="+porel+"&orderPaper="+poorderType+"&fo=paperfo"+"&order="+poOrder;
		  else
		  		url = urlGROld+"&pono="+pono+"&poid="+poid+"&poversion="+pover+"&rno="+porel+"&order="+poorderType;
	    
	     if(isFormChanged())
	      	{
			if(confirm("All unsaved data will be lost. Do you want to proceed?"))
	    	window.location.href =url;
	    	}
	    	else
	    	{
	    	window.location.href =url;
	    	}
	  	}  		
	  	else
	  	 {
	  		alert('PO does not exist');
	  		return;
	  	 }
	  }	
}

function validateNewPaperGR(totalLineItems)
{
   // alert('in the method');
	var formObj = eval('document.goodsReceiptForm');
	var errorString = "";
	var errorStringHead="";
	var errorString1="";
	var objError=document.getElementById("error_div");
	var flg = "";
	//var totalCumulative = 0;
	var onlyNumber= true;
	var colorFlag=false;
	objError.innerHTML= "";

		/* onlyNumber =  onlyNumericQuantity(totalLineItems);
	 if(onlyNumber== false)
	 {
	 	errorString=errorString+'<LI>Recieved Quantity can have only positive numeric values.</LI>';
	 }*/
	 for(j=0;j<totalLineItems;j++)
 		{
    		formObj.elements["goodsreceipt.goodsReceiptLineCollection["+j+"].receivedQuantity"].style.backgroundColor = "white";
    	}
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	if(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value != "" &&
	checkNumb(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value)
	)
	{
			var alertIndex = currIndex+1;
			prevIndex = currIndex - 1;
			var lineNo = document.getElementById("poLineNo"+currIndex).value;
			if(!(prevIndex >=0 && lineNo==document.getElementById("poLineNo"+prevIndex).value))
                  colorFlag = false;
                
			var maxAmt = document.getElementById("maxAmtAllow"+currIndex).value;
			var recAmt = parseInt(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value);
	  		
	  		if(parseInt(recAmt)== 0)
	     	{	     		
	     		errorString=errorString+'<LI> Received Quantity can not be zero for PO line No. '+document.getElementById("poLineNo"+currIndex).value+'</LI>';
	     		formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].style.backgroundColor = "#C0C0C0";
	        }
	        else if(parseInt(recAmt) <= parseInt(maxAmt))
	        {	        	
	        	flg = "ok";
	     		continue;
	        }
	        else
	        {
				if(!(colorFlag)){
	        	colorFlag = true;
	        	errorString=errorString+'<LI> Received Quantity should be less than Delivered Quantity for PO line No. '+document.getElementById("poLineNo"+currIndex).value+'</LI>';
	        	}
	        	formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].style.backgroundColor = "#C0C0C0";
	        } 
	    }
	else if(!checkNumb(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value))
	    {
	   			
	   			errorString = "<LI> Please enter valid quantity for PO Line No. "+document.getElementById("poLineNo"+currIndex).value+"</LI>";
	   			formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].style.backgroundColor = "#C0C0C0";
	   					
	    }    
	    
	}//end of for
		if (flg != "ok")
		{
			if(errorString=='')
			{
				errorString ='<LI> Please enter valid quantity for at least one line item </LI>';
				
				if(errorStringHead != "")
					errorString=errorStringHead+errorString;
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


function validatePaperGRHistory(totalLineItems1)
{
	var formObj = eval('document.goodsReceiptForm');
	var errorString = "";
	var errorStringHead="";
	var errorString1="";
	var objError=document.getElementById("error_div");
	var flg = "";
	var x=false;
	var totalCumulative = 0;
	objError.innerHTML= "";
	var zeroGRString="";
	var recAmt;	
    var count=0;
    var str2="";
    var colorFlag=false;
   for(j=0;j<totalLineItems1;j++)
 		{
    		formObj.elements["goodsreceiptCollection["+j+"].goodsReceiptLineCollection[0].receivedQuantity"].style.backgroundColor = "white";
    	}
	for(currIndex=0;currIndex<totalLineItems1;currIndex++)
    {    	
    	if(formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value != "" && formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value != 0
    	&&  checkNumb(formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value))
		{
    	var dmNo=document.getElementById("DMNo"+currIndex).value;
    	var maxAmt = document.getElementById("maxAmtAllow"+currIndex).value;
    	var prevIndex = currIndex-1;
		if(prevIndex >=0 && dmNo==document.getElementById("DMNo"+prevIndex).value)
	       	{
		       totalCumulative = totalCumulative+parseInt(formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value);
		    }
	       	else
	       	{
	       	colorFlag=false;
	       	totalCumulative = parseInt(formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value);
	       	}	
	       	if(parseInt(totalCumulative) <= parseInt(maxAmt))
	        {    	
	        	flg = "ok";
	     		continue;
	        }
	        else
	        {
	          if(!(colorFlag)){
	          colorFlag = true;
	        	errorString=errorString+'<LI>Total Received Quantity should not exceed Delivered Quantity for PO line No. '+document.getElementById("poLineNo"+currIndex).value+'</LI>';
	        	formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].style.backgroundColor = "#C0C0C0";
	        	}
	        }
	     	
    	}
    	else
    	{
    	if(!checkNumb(formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value))
		{
				formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].style.backgroundColor ="#C0C0C0";
				var errorMsg= new String();
				errorMsg = "<LI> Please enter valid quantity for PO Line No. "+document.getElementById("poLineNo"+currIndex).value+"</LI>";
				if(errorString == ''){
				errorString=errorString+errorMsg;
				}
				else if((errorString.toString()).indexOf(errorMsg)== -1){
				errorString=errorString+errorMsg;
				}
		}
    	if(formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value == "" )
		{
				formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].style.backgroundColor ="#C0C0C0";
				var errorMsg= new String();
				errorMsg = "<LI> Received Qty cannot be blank for PO Line No. "+document.getElementById("poLineNo"+currIndex).value+"</LI>";
				if(errorString == ''){
				errorString=errorString+errorMsg;
				}
				else if((errorString.toString()).indexOf(errorMsg)== -1){
				errorString=errorString+errorMsg;
				}
		}
    	if(formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value != "" && document.getElementById("textBoxQty"+currIndex).value != 0)
    	{
		 	 zeroGRString= zeroGRString +'\n'+document.getElementById("zeroGR"+currIndex).value;
    	}
    	}
    }
  	
	if(errorString=='')
	{
		if(zeroGRString != '')
		{
		//var zeroGRAlert=zeroGRString.substring(0,zeroGRString.length-1);
		x=window.confirm("You have entered zero quantity for GR No. " +zeroGRString+"\nThis will cancel the GR. Click \"OK\" to continue");
		return x;
		}
		else
		return true;
		
	}
	else
	{
		objError.innerHTML= 'Please rectify the following errors:<br>'+errorString;
		window.location="#topofpage";
		return false;
	}
	
}

function submitAjaxAction(path)
{
    document.goodsReceiptForm.action=path;
     document.goodsReceiptForm.submit();
      document.body.style.cursor = "wait";
    buttons3.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
}
function submitEnter(ctx,urlGRNew,urlGROld,event)
{
	var keycode;
	if (window.event) keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	else return true;
	
	if (keycode == 13)
	   {
		submitSearch(ctx,urlGRNew,urlGROld)
		return false;
		}
	else return true;
}
function checkNumb(t){
		
		var numb = "0123456789"
		var w = "";
    	for (i=0; i < t.length; i++) {
        x = t.charAt(i);
        if (numb.indexOf(x,0) == -1)
        return false;
        }
        return true;
    }
    
function isFormChanged()
{
var formObj = eval('document.goodsReceiptForm');
 var totalLineItems=document.getElementById("totalLineItems2").value;
 var count=0;
 for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	if((formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"])!=null)
	  {
	 	if(formObj.elements["goodsreceipt.goodsReceiptLineCollection["+currIndex+"].receivedQuantity"].value != "")
	     count=count+1;
	  }   
    }
 if(count==0)
 return false;
 else
 return true;
}

function isHistFormChanged()
{
 var formObj = eval('document.goodsReceiptForm');
 var totalLineItems=document.getElementById("totalLineItems2").value;
 var count=0;
 
 for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	  if((formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"])!=null)
	  {
	  if(formObj.elements["goodsreceiptCollection["+currIndex+"].goodsReceiptLineCollection[0].receivedQuantity"].value != document.getElementById("textBoxQty"+currIndex).value)
	  count=count+1;
	  }
    }
 
 if(count==0)
 return false;
 else
 return true;
}

function validatePage(path)
{
	if(isFormChanged())
			{
			if(confirm("All unsaved data will be lost. Do you want to proceed?"))
	    	window.location.href =path;
	    	}
	else
	    	{
	    	window.location.href =path;
	    	}
}


function dmDetailspopUp(contx,path,productCode,poid,poversion,msgNo,grRoleTracking)
   {
   	//	alert(msgNo);
//         alert(productCode);
      //    alert(poid);
      //    alert('hi');
     //   path = contx+'/goodsreceipt/goodsreceiptpapernew.do?paperlist=dmdetailist&poid=7000000212&productcode=0000003941&poversion=3';
     	path = contx+'/goodsreceipt/goodsreceiptpapernew.do?paperlist=dmdetailist&poid='+poid+'&productcode='+productCode+'&poversion='+poversion+'&msgNo='+msgNo+'&grRoleTracking='+grRoleTracking;  
        window.open(path,'dmdetails','height=450,width=1000,left=0,top=0');
   }
  