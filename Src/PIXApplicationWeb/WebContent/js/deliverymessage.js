

function listfilter3(path)
{

document.deliveryMessageForm.action=path;
document.deliveryMessageForm.submit();
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

function validate(totalLineItems)
{
	var formObj = eval('document.deliveryMessageForm');
	var errorString = "";
	var errorStringHead = "";
	var errorString1 = "";
	var flag="";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	var onlyNumber= true;
	if(formObj.elements["deliveryMessage.msgTypeDetail"].value=="")
	{
		errorStringHead='<LI>Please select Delivery Message Type</LI>';
		errorString = errorStringHead;
	}
  	 onlyNumber =  numericQuantity(totalLineItems);
	 if(onlyNumber== false)
	 {
	 	errorString=errorString+'<LI>Delivered Quantity can have only positive numeric values.</LI>';
	 }
  	
  	
  	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
/*	var chell="";
	chell=formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].text;
	
	if (chell == null)
	alert("null value");*/
	  	   
	  if((formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value != "") &&
	  (formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value != null)&&
	   (formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].estimatedDeliveryDate"].value != "")){
	  	  
	  /*deliveredQuantity = formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value;
			if(isNaN(deliveredQuantity) || deliveredQuantity < 0 || deliveredQuantity.indexOf('.') != -1)
			{
				errorString1='<LI>Please enter valid Quantity for Line Item '+(currIndex+1)+'</LI>';
			}
			
			if(errorStringHead != "" || errorString1 != "")
	     		errorString=errorStringHead+errorString1+"";
	     	else
	     		errorString=errorStringHead+"";
	     	break; */
	     	flag = "ok";
	     	continue;
	        }  
	else{ 	    
		    if((formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value != "") || 
		     (formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].estimatedDeliveryDate"].value != ""))
			{ 
			
			     //if(flag !=0 )
				//errorString = errorStringHead+'<LI> Please enter Valid Quantity and select ShipDate for atleast one Line Item </LI>';
				//alert("errorstring"+errorString);
				errorString='<LI> Please enter valid quantity and select ShipDate for atleast one Line Item </LI>';
				if(errorStringHead != "")
				errorString=errorStringHead+errorString;
			} 
		}
	
		/*deliveredQuantity = formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value;
		if(deliveredQuantity=="" || deliveredQuantity<= 0 || deliveredQuantity.indexOf('.') != -1)
		{
			errorString=errorString+'<LI>Please enter Valid Delivered Quantity for Line Item '+(currIndex+1)+'</LI>';
		}
		if(isNaN(deliveredQuantity))
		{
			errorString=errorString+'<LI>Please enter Numeric Estimated Quantity for Line Item '+(currIndex+1)+'</LI>';
		}
		if(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].estimatedDeliveryDate"].value == "")
		{
			errorString=errorString+'<LI>Please select Ship Date for Line Item '+(currIndex+1)+'</LI>';
		} +errorStringLine
		*/
	}
	
	if (flag != "ok")
	{
			errorString='<LI> Please enter valid quantity and select ShipDate for atleast one Line Item </LI>';
				if(errorStringHead != "")
				errorString=errorStringHead+errorString;
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

  
//var httpRequestNew;
function validateBOLUploading(totalLineItems1,context,path){
         var randomnumber=Math.floor(Math.random()*11);
		var url = context+'/filelistsize/ajaxaction.do?totalLineItems='+randomnumber; 
		//alert(httpRequestNew);
		if (window.ActiveXObject){ 
		httpRequestNew = new ActiveXObject("Microsoft.XMLHTTP"); 
		
		}else if (window.XMLHttpRequest){ 
		httpRequestNew = new XMLHttpRequest(); 
		
	    } 
		httpRequestNew.open("GET", url, true); 
		httpRequestNew.onreadystatechange = function() {
		 flag=getBOLUploadingValue(path);
		} ; 
		httpRequestNew.send(null); 
    }
function getBOLUploadingValue(path){
    	if (httpRequestNew.readyState == 4) { 
			if(httpRequestNew.status == 200) {
			var flag="" ; 
			var totalLineItems=0;
			xml = httpRequestNew.responseXML;
			//alert(httpRequestNew.responseText);
			var fileUploadStr='' ;
			var file_upload = xml.getElementsByTagName("file-upload")[0];
				if(file_upload!=null&&file_upload.firstChild!=null){
				fileUploadStr=file_upload.firstChild.data;
                  }
            var fileArr= fileUploadStr.split('~');
            if(fileArr.length!=0){
            totalLineItems=parseInt(fileArr[0]);
            }
			for(var k=1;k<=totalLineItems;k++){
			 var fileUpload=0 ;
			 if(fileArr[k].length!=0){
			   fileUpload=parseInt(fileArr[k])
			   }
             if(fileUpload==0){
              document.getElementById('hid'+(k-1)).value="" ;
              }else{
              document.getElementById('hid'+(k-1)).value="uploaded" ;
              }
            //alert("hid.."+document.getElementById('hid'+(k-1)).value +"fileuplaod "+fileUpload);
			}
			flag=validateMillNew(totalLineItems);
			
			if(flag.indexOf("true")!= -1){
			for(var k=0;k<totalLineItems;k++){			
			 document.getElementById('hid'+k).value="" ;
			 }
		     submitAjaxAction(path);
			}else{
			return false ;
			}
			//httpRequestNew=null ;
		}
		else {
			alert("Error loading page\n"+ httpRequestNew.status +":"+ httpRequestNew.statusText) ; 
			} 
		}
	 }
  
function validateMillNew(totalLineItems)
{ 
	
    var formObj = eval('document.deliveryMessageForm');
	var errorString = "";
	var errorStringHead = "Please rectify the following errors";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";	
	var alertIndex;
	var flag1;
	var flag;
	var flag2="";
  	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	  alertIndex=currIndex + 1;
	  lineNo = document.getElementById("lineNo"+currIndex).value;
    	if((formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value != "") &&
	     (formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value != null) &&
	     checkNumb(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value) &&
	     (document.getElementById("hid"+currIndex).value!="") && (document.getElementById("hid"+currIndex).value!=null))
	     {
	  	  	var maxAmt = document.getElementById("maxAmtAllow"+currIndex).value;
	  	  	//alert("maxAmt                 "+maxAmt);
	  	  	//alert("dm quantity            "+formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value);
	  		if(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value==0)
	        {
	        	errorString=errorString + '<LI> Quantity cannot be zero at PO Line No. '+lineNo+'</LI>';	
	        	formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].style.backgroundColor ="#C0C0C0";
	        }
	     	else if(parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value) > parseInt(maxAmt))
	     	{
	     		
	     		errorString=errorString +'<LI> Delivered Quantity is not within the tolerance limit for PO Line No. '+ lineNo +'</LI>';
	     		formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].style.backgroundColor ="#C0C0C0";
	        }
	        else
	        {
	        flag="ok";
	        }
	     }
	    else 
	    {
		    if(!checkNumb(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value))
		    {
		            formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].style.backgroundColor ="#C0C0C0";
					errorString=errorString+'<LI> Please enter valid quantity for PO Line No.'+lineNo+'</LI>';
		    }
		    if((formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value != "") || 
	               (document.getElementById("hid"+currIndex).value!=""))
			 { 
			  	if(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value == "")
					{
						errorString=errorString + '<LI> Please enter valid quantity for PO Line No.'+lineNo+'</LI>';
						formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].style.backgroundColor ="#C0C0C0";
					}
					else if((document.getElementById("hid"+currIndex).value=="") || (document.getElementById("hid"+currIndex).value==null))
					{
						errorString=errorString + '<LI> Bill of Lading is missing for PO Line No. '+ lineNo + '</LI>';
					    flag1="notOk";
					}
	    	}
    	}
	}//end of for
	if (flag != "ok")
	{
			if(errorString=='')
			{
				if(flag1=="notok")
				{
					errorString='<LI> Uploading Bill of Lading is mandatory. </LI>';
				}
				else
				{
					errorString='<LI> Please enter valid quantity for at least one line item.</LI>';
				}
				
			}
	}
	if(errorString=='')
	{
		flag2="true";
	}
	else
	{
		//alert("inside else ");
		errorString = errorStringHead+errorString;
    	objError.innerHTML=errorString;
		window.location="#topofpage";
		flag2= "false";
	}
	//alert("flag inside validate new mill"+flag2);
	return flag2;
  }


//validate called for MILL when posting new Delivery message
function validateMill(totalLineItems)
 {    
	var formObj = eval('document.deliveryMessageForm');
	var errorString = "";
	var errorStringHead = "";
	var errorString1 = "";
	var flag="";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";
	
	if(formObj.elements["deliveryMessage.msgTypeDetail"].value=="")
	{
		errorStringHead='<LI>Please select Delivery Message Type</LI>';
		errorString = errorStringHead;
	}
	
	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
		if((formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value == "") || 
		     (formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].estimatedDeliveryDate"].value == ""))
			{
				flag = "ok";
				break;
			}
	}
	
	if (flag == "ok")
	{
			errorString='<LI> Please enter valid quantity and select ShipDate for all Line Items </LI>';
				if(errorStringHead != "")
				errorString=errorStringHead+errorString;
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
	return false;
}

function acceptPOs(path)
{
	formname = document.deliveryMessageForm;
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
	
}

 function acceptPOsNew(path)
{
	formname = document.deliveryMessageForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	
	   alert("Please select atleast one Delivery Message.");
	
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
//-----------------------------------------------------------	
function acceptPOsNew1(path)
{
	formname = document.deliveryMessageForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr = "";
	
	
	if(modifiedHiddenVal.length==0){
	
	   alert("Please select atleast one Delivery Message.");
	
	return;
	}else{
	
	var idsArray = modifiedHiddenVal.split(',');
	var idsArray2 = idsArray[0].split('-');
	selectedArr=selectedArr+idsArray2[0];
	for(var j=1;j<idsArray.length;j++)
	{
	var idsArray1= idsArray[j].split('-');
	selectedArr = selectedArr  + "," + trim(idsArray1[0])
	}
	formname.checksArr.value=selectedArr;
	formname.action = path;
	formname.submit();
	 }
	
	}	
//-----------------------------------------------------------------	
	
function submitDelmsgAction(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons3.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";

}
function submitDelMsgActionMill(path,obj)
{
    document.deliveryMessageForm.action=path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons3.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";

}

function submitAjaxAction(path)
{
    document.deliveryMessageForm.action=path;
     document.deliveryMessageForm.submit();
	document.body.style.cursor = "wait";
    buttons3.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
}

//--------------------------------------

function submitDeliveryMsgAction(path,obj,stat)
{
    formname = eval('document.'+obj.form.name);
    //alert('here1');
  	//stat = document.getElementById("stat");
  	//alert(stat);
  	if(stat != 'CANCELLED'){
  	
  	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons3.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON...";
	}
	else{
	alert("Cannot post Delivery Message for cancelled PO.");
	return false;
	}

}
//--------------------------------------


function submitCancelDelivery(path)
{
    document.deliveryMessageForm.action = path;
	document.deliveryMessageForm.submit();
}

function submitCancelDelivery1(path)
{
    if(isHistFormChanged())
			{
			if(confirm("All unsaved data will be lost. Do you want to proceed?"))
	    	window.location.href =path;
	    	}
	else
	    	{
	    	window.location.href =path;
	    	}
}

function submitDelmsgAction2(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	

}


function newPaperGoodReceipt(path)
{
	formname = document.deliveryMessageForm;
	
	if(formname.selectedEntry != undefined)
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
				alert("Please select only one Delivery Message to post New Goods Receipt.");
				return;
			}
			if(noOfSelected==0)
			{
				alert("Please select Delivery Message to post New Goods Receipt.");
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
				
	    	}
	    	else
	    	{
	    		alert("Please select Delivery Message to post New Goods Receipt.");
	    		return;
	    	}
		}
		
		var temp = selectedArr;
		var obj = document.getElementsByName('querystr_'+temp);
		var newObj = document.getElementsByName('delLine_'+temp);
		var newpath=path+'&MSG_ID='+obj[0].value+"&MSG_LINE_NO="+newObj[0].value;
		
		formname.action = newpath;
		formname.submit();	
	}
	else
	{
		alert("No Delivery Message exists for posting a Goods Receipt");
	}
	
}

//function to validate the entered text is numeric
function numericQuantity(totalLineItems)
{
	var formObject = eval('document.deliveryMessageForm');
	var digits = "0123456789";
	var temp="";	
	for(i=0;i<totalLineItems;i++)
	{
		var enteredQuantity = formObject.elements["deliveryMessage.deliveryMsgLineCollection["+i+"].deliveredQuantity"].value;
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
//gaurav search
function getPODetail1(ctx)
	{
		var url="";
	 	if(document.deliveryMessageForm.poNumber.value!=""){
			url = ctx+"/home/dmlist.do?"+"pono="+document.deliveryMessageForm.poNumber.value;
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
/*function getPODetail1(path)
	{
		
		var url="";
	 	if(document.deliveryMessageForm.poNumber.value!=""){
			url = path+"pono="+document.deliveryMessageForm.poNumber.value;
			//alert("GRV"+url);
		}
		//Do the Ajax call
      	if (window.ActiveXObject){ 
            req = new ActiveXObject("Microsoft.XMLHTTP"); 
        }else if (window.XMLHttpRequest){ 
            req = new XMLHttpRequest(); 
        } 
        req.open("GET", url, false); 
      //alert("req.readyState " + req.readyState);
        req.onreadystatechange = function(){getPOInfo();};
        req.send(null);
    }*/
    
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
          if(((countval[1].toString()).indexOf(","))!= -1)
          {
          arr = countval[1].split(",");
          document.deliveryMessageForm.poid.value = arr[0];
          document.deliveryMessageForm.pover.value = arr[1];
          document.deliveryMessageForm.porel.value = arr[2];
          document.deliveryMessageForm.poNumber.value = arr[3];
          document.deliveryMessageForm.poorderType.value=arr[4];
           }
           else
           {
           document.deliveryMessageForm.poid.value = "";
           document.deliveryMessageForm.pover.value = "";
           document.deliveryMessageForm.porel.value = "";
           document.deliveryMessageForm.poorderType.value="";
           }
           }
	}
	}*/
	
	function submitSearch(ctx,urlDMNew,urlDMOld)
{

	
    var url="";
    if(document.deliveryMessageForm.poNumber.value=="")
	  {
	   alert('Purchase Order No. cannot be blank');
	   return;
	  }
	  else
	  {	  	
	  	var xml = getPODetail1(ctx);
	  
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
			  	url = urlDMNew+"&pono="+pono+"&poid="+poid+"&poversion="+pover+"&rno="+porel+"&orderPaper="+poorderType+"&fo=paperfo"+"&order="+poOrder;
		  else
		  		url = urlDMOld+"&pono="+pono+"&poid="+poid+"&poversion="+pover+"&rno="+porel+"&order="+poorderType;
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
//validate called for MILL when posting new Delivery message
function validateMillHistory(totalLineItems,path)
 {    
	var formObj = eval('document.deliveryMessageForm');
	var errorString = "";
	var errorStringHead = "<div>Please rectify the following errors</div>";
	var objError=document.getElementById("error_div");
	objError.innerHTML= "";	
	var totalCumulative = 0;
	var zeroQty = false;
	var flag = "";
	var lineNo ;
	var str2 = "";
	var colorIndex=0;
	var colorFlag = false;
	var zeroDMString = "";
	for(j=0;j<totalLineItems;j++)
 			{
    			formObj.elements["deliveryMessage.deliveryMsgLineCollection["+j+"].postedQuantity"].style.backgroundColor ="white";
    		}
  	for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	//alert("testing checkNumb: input= "+(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value)+"output= "+checkNumb(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value));
	 var alertIndex = currIndex +1;
	 var maxAmt = document.getElementById("maxAmtAllow"+currIndex).value;
	 var minAmt = document.getElementById("minAmtAllow"+currIndex).value;
	 lineNo = document.getElementById("lineNumber"+currIndex).value;
	 if((formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value != "") &&
	  (formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value != 0)&&
	  checkNumb(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value))
	    {	       	
	       formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value = parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value);
	       	var prevIndex = currIndex-1;
	       	//calculating total cumulative
	       	if(prevIndex >=0 && lineNo==document.getElementById("lineNumber"+prevIndex).value)
	       	{
	       	totalCumulative = totalCumulative+parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value);
	       	//alert(totalCumulative);
	       	}
	       	else
	       	{
	       	colorFlag = false;
	       	totalCumulative = parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value);
	       	}
			//checking values within limit or not 
			if((totalCumulative<=parseInt(maxAmt))&&(parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value)>=parseInt(minAmt)))
			{
						flag = "ok";
						continue;
			}
			else
			{    			   
    			   if((parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value)<parseInt(minAmt)))	
    			   {
    				formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].style.backgroundColor ="#C0C0C0";
					errorString=errorString+'<LI> Delivered Qty cannot be less than the Recieved Qty for PO Line No. '+lineNo+'</LI>';
				   }
				   else if(totalCumulative>parseInt(maxAmt)) 
				   {
				     colorIndex = currIndex;
				   	 if(!(colorFlag))
				   	 {
				   	    colorFlag=true;
						errorString=errorString+'<LI> Delivered Qty is not within tolerance limit for PO Line No. '+lineNo+'</LI>';
						formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].style.backgroundColor ="#C0C0C0";
				     }
				   }
			}
			
  	  }	 
  	  else
    	{  
    	if(!(checkNumb(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value)))
    	{
    			formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].style.backgroundColor ="#C0C0C0";
				var errorMsg= new String();
				errorMsg = "<LI> Please enter valid quantity for PO Line No. "+lineNo+"</LI>";
				if(errorString == ''){
				errorString=errorString+errorMsg;
				}
				else if((errorString.toString()).indexOf(errorMsg)== -1){
				errorString=errorString+errorMsg;
				}
    	} 
    	if(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value == "")
    	{
    			formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].style.backgroundColor ="#C0C0C0";
				var errorMsg= new String();
				errorMsg = "<LI> Delivered Qty cannot be blank for PO Line No. "+lineNo+"</LI>";
				if(errorString == ''){
				errorString=errorString+errorMsg;
				}
				else if((errorString.toString()).indexOf(errorMsg)== -1){
				errorString=errorString+errorMsg;
				}
    	}	
    	if((parseInt(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value)<parseInt(minAmt)))	
    	{
    			formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].style.backgroundColor ="#C0C0C0";
				var errorMsg= new String();
				errorMsg = "<LI> Delivered Qty cannot be less than the Recieved Qty for PO Line No. "+lineNo+"</LI>";
				if(errorString == ''){
				errorString=errorString+errorMsg;
				}
				else if((errorString.toString()).indexOf(errorMsg)== -1){
				errorString=errorString+errorMsg;
				}
		 }		 
    	if(formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value != "" && document.getElementById("postedQuantity"+currIndex).value != 0)
    	{
		 	 zeroDMString= zeroDMString +'\n' +document.getElementById("zeroDM"+currIndex).value;
    	}
    	}       
	 }
	if(errorString=="")
	{
		if(zeroDMString != '')
		{
		//var zeroDMAlert=zeroDMString.substring(0,zeroDMString.length-1);
		x=window.confirm("You have entered zero quantity for DM No. " +zeroDMString+"\nThis will cancel the DM. Click \"OK\" to continue");
		if(x)
		submitAjaxAction(path);
		else 
		return false;
		}
		else
		submitAjaxAction(path);
		
	}
	else
	{
    	errorString = errorStringHead+errorString;
    	objError.innerHTML=errorString;
		window.location="#topofpage";
		return false;
	}
}
//called when user hits enter when the focus is on search text box

function submitEnter(ctx,urlDMNew,urlDMOld,event)
{
	var keycode;
	if (window.event) keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	else return true;
	
	if (keycode == 13)
	   {
		submitSearch(ctx,urlDMNew,urlDMOld)
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
 var formObj = eval('document.deliveryMessageForm');
 var totalLineItems=document.getElementById("totalLineItems1").value;
 var count=0;
 for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	if(document.getElementById("bolb"+currIndex)!= null){
	  if((formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].deliveredQuantity"].value != "") ||
	     (document.getElementById("bolb"+currIndex).style.display==""))
	     
	     count=count+1;
	     }
    }
 if(count==0)
 return false;
 else return true;
}

function isHistFormChanged()
{
 var formObj = eval('document.deliveryMessageForm');
 var totalLineItems=document.getElementById("totalLineItems1").value;
 var count=0;
 for(currIndex=0;currIndex<totalLineItems;currIndex++)
	{
	if(document.getElementById("bolb"+currIndex)!= null){
	  if((formObj.elements["deliveryMessage.deliveryMsgLineCollection["+currIndex+"].postedQuantity"].value != document.getElementById("postedQuantity"+currIndex).value)||
	     (document.getElementById("bolb"+currIndex).style.display==""))
	     count=count+1;
	     }
    }
    
 if(count==0)
 return false;
 else return true;
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

function validatePageRtPrinter(path)
{
	    	window.location.href =path;
}