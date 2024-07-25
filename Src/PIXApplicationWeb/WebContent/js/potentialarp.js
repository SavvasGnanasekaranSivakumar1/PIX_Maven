//function for filtering with previous next
function listfilter(path)
{
   var statusFilter = document.potentialARPForm.statusFilter.value;
   if(statusFilter == 3)
   {
	   var checkBox = document.potentialARPForm.selectedEntry;
	   var select = false;
	   if(checkBox.length ==undefined)
	   {
	      if(checkBox.checked)
	         select = true;
	   }
	   else
	   {
	     for(var n=0 ;n<checkBox.length;n++)
		 {
		   if(checkBox[n].checked)
		       select = true;
	     }  
	   }
	   if(select == true)
	   {
	  	   if(confirm("Please Flip Title\Reject the selected ISBN otherwise the entry get lost."))
	       {
	          document.potentialARPForm.action=path;
	          document.potentialARPForm.submit(); 
	       }
	   }
	   else
	   {
	      document.potentialARPForm.action=path;
	      document.potentialARPForm.submit(); 
	   }
   }
   else
   {
      document.potentialARPForm.action=path;
      document.potentialARPForm.submit(); 
   }
}

function sfilter(path)
{
    
	if(!dateCheck(document.potentialARPForm.arpToDueDate.value,document.potentialARPForm.arpFromDueDate.value,'>=','Start Date should be ','End Date'))
	{
	return false;
	/*alert("enter valid date ,'>=','Start Date should be ','End Date'"); 	formname = eval('document.orderListForm');		document.orderListForm.action=path;*/
	
	}
	
	if(!dateCheck(document.potentialARPForm.toReqDate.value,document.potentialARPForm.fromReqDate.value,'>=','Start Date should be ','End Date'))
	{
		return false;		
	}
	document.potentialARPForm.action=path;
	document.potentialARPForm.submit();
     	
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

 function acceptARPList(path)
{
	formname = document.potentialARPForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var selectedArr ="";
	var status; 

	if(modifiedHiddenVal.length==0){
		alert("Please select atleast one plan.");
		return;
	}else{
		/*************************For adding three more variables**************************	*/
		var checkBox = document.potentialARPForm.selectedEntry;
		var str; 
		var bufferStr ="";
		var mode ="accept";
		if(checkBox.length ==undefined)
		{
		   str =   document.potentialARPForm.selectedEntry.value+'-'+document.potentialARPForm.vendorPageCount.value+'-'+document.getElementById("isReviewProv1").checked+'-'+document.getElementById("reviewReq1").checked+'-'+document.potentialARPForm.comments.value;
		   bufferStr = str;
		}
		else
		{
			for(var n=0 ;n<checkBox.length;n++)
			{
			   if(checkBox[n].checked)
			   {
			      var x = n+1;
			      str =   document.potentialARPForm.selectedEntry[n].value+'-'+document.potentialARPForm.vendorPageCount[n].value+'-'+document.getElementById("isReviewProv"+x).checked+'-'+document.getElementById("reviewReq"+x).checked+'-'+document.potentialARPForm.comments[n].value;
			      if(bufferStr !=""){
			         bufferStr = str+'|'+bufferStr;
			      }
			      else{
			         bufferStr = str;
			      }
			   }
			}
		}
		formname.checksArr.value=bufferStr;  
		status = document.potentialARPForm.statusFilter.value;
        
		var urlPopup='/pix/potentialarp/potentialarpaccept.do?action=openPopup&mode='+mode+'&status='+status+'&finalStr='+bufferStr;
		window.open(urlPopup,'resultpopup','width=600,height=320');
		document.body.style.cursor = "wait";
	    buttons2.innerHTML="PLEASE WAIT WHILE SEARCH IS GOING ON..";
    }
}


 function rejectARPList(path)
{
	formname = document.potentialARPForm;
	var HiddenVal = formname.idHidden.value;
	var modifiedHiddenVal=trim(HiddenVal);
	var mode ="reject";
	var selectedArr ="";
	var status; 

	if(modifiedHiddenVal.length==0){
		alert("Please select atleast one plan.");
		return;
	}else{
		/*************************For adding three more variables**************************	*/
		var checkBox = document.potentialARPForm.selectedEntry;
		var str; 
		var bufferStr ="";
		if(checkBox.length ==undefined)
		{
		   if(document.potentialARPForm.comments.value=='')
		   {  alert('Please enter the comments for selected ISBN.');
		      return false;
		   }
		   str =   document.potentialARPForm.selectedEntry.value+'-'+document.potentialARPForm.vendorPageCount.value+'-'+document.getElementById("isReviewProv1").checked+'-'+document.getElementById("reviewReq1").checked+'-'+document.potentialARPForm.comments.value;
		   bufferStr = str;
		}
		else
		{
			for(var n=0 ;n<checkBox.length;n++)
			{
			   if(checkBox[n].checked)
			   {
			      var x = n+1; 
			      if(document.potentialARPForm.comments[n].value=='')
				  {  alert('Please enter the comments for selected ISBN.');   
				     return false; }
			      str =   document.potentialARPForm.selectedEntry[n].value+'-'+document.potentialARPForm.vendorPageCount[n].value+'-'+document.getElementById("isReviewProv"+x).checked+'-'+document.getElementById("reviewReq"+x).checked+'-'+document.potentialARPForm.comments[n].value;
			      if(bufferStr !=""){
			         bufferStr = str+'|'+bufferStr;
			      }
			      else{
			         bufferStr = str;
			      }
			   }
			}
		}
		formname.checksArr.value=bufferStr;  
		status = document.potentialARPForm.statusFilter.value;

		var urlPopup='/pix/potentialarp/potentialarpreject.do?action=openPopup&mode='+mode+'&status='+status+'&finalStr='+bufferStr;
		window.open(urlPopup,'resultpopup','width=600,height=320');
		document.body.style.cursor = "wait";
	    buttons2.innerHTML="PLEASE WAIT WHILE SEARCH IS GOING ON.."; 
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

 /* function to create StringBuffer Object*/
     
     function StringBuffer() { 
      this.buffer = new Array(); 
      } ;
     
     
      /* function to append String into StringBuffer Object*/
      StringBuffer.prototype.append = function append(string) { 
       this.buffer.push(string); 
       return this; 
      }; 


       /* function for toString from StringBuffer Object*/
      StringBuffer.prototype.toString = function toString() { 
       return this.buffer.join(","); 
      }; 
 
     


 /* function for adding and deleting id from jsp id Hidden*/
     function addDeleteIdsFromSetNewARP( hiddenObj,checkBoxId,checkBoxObj ){
       
       var temp=hiddenObj.value;
       var check=checkBoxObj.checked ;
       var buf = new StringBuffer();
     
       if(temp.length!=0){
       var tempNew=temp.split(",");
       var idSet=new LinkedHashSet();
       idSet.add('a');
       idSet.add('b');
       idSet.add('c');
       
       for(var i=0;i<tempNew.length;i++){
     
         if(tempNew[i]!=''){
         idSet.add(trim(tempNew[i]));
          }
     
         }
        if(check===true){
        if(tempNew.length==1){
      
        idSet.add(trim(checkBoxId));
        }else{
        idSet.add(trim(checkBoxId));
        }
        
         for(itr = idSet.iterator(); itr.hasNext();){
           var id=itr.next();
          if(id!=''&&id!=','&&id!='a'&&id!='b'&&id!='c'){
           buf.append(trim(id));
           }
        }
        
        }else{ 
       
            for(itr1 = idSet.iterator(); itr1.hasNext();){
	       
	        var id=itr1.next();
	       
           if(id!=''&&id!=','&&id!='a'&&id!='b'&&id!='c'){
           
            if(id==checkBoxId){
             continue;
             }else{
               buf.append(trim(id));
            }
           }
        }
          //idSet.remove(trim(checkBoxId));
       
      }
        }else{
         buf.append(trim(checkBoxId));
        
        }
       bufStr=trim(buf.toString())
      document.getElementById('hiddenVal').value =bufStr ;
 
  }


/*For string tokenizer*/
String.prototype.tokenize = tokenize;

function tokenize()
  {
     var input             = "";
     var separator         = " ";
     var trim              = "";
     var ignoreEmptyTokens = true;

     try {
       String(this.toLowerCase());
     }
     catch(e) {
       window.alert("Tokenizer Usage: string myTokens[] = myString.tokenize(string separator, string trim, boolean ignoreEmptyTokens);");
       return;
     }

     if(typeof(this) != "undefined")
       {
          input = String(this);
       }

     if(typeof(tokenize.arguments[0]) != "undefined")
       {
          separator = String(tokenize.arguments[0]);
       }

     if(typeof(tokenize.arguments[1]) != "undefined")
       {
          trim = String(tokenize.arguments[1]);
       }

     if(typeof(tokenize.arguments[2]) != "undefined")
       {
          if(!tokenize.arguments[2])
            ignoreEmptyTokens = false;
       }

     var array = input.split(separator);

     if(trim)
       for(var i=0; i<array.length; i++)
         {
           while(array[i].slice(0, trim.length) == trim)
             array[i] = array[i].slice(trim.length);
           while(array[i].slice(array[i].length-trim.length) == trim)
             array[i] = array[i].slice(0, array[i].length-trim.length);
         }

     var token = new Array();
     if(ignoreEmptyTokens)
       {
          for(var i=0; i<array.length; i++)
            if(array[i] != "")
              token.push(array[i]);
       }
     else
       {
          token = array;
       }

     return token;
  }
  
/*For Number keys only*/  
  function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
  
  
 /*function to add or remove all check box ids for each single page*/
  function addDeleteAllIdsFromSet(topChechBoxObj ,hiddenObj,childChechBoxObj)
  {
	        var IdsArray=new Array();
	         var IdsCheck=new Array();
			var temp=hiddenObj.value;
	        var idSet=new LinkedHashSet();
	        idSet.add('a');
            idSet.add('b');
            idSet.add('c');
          
	        var check=topChechBoxObj.checked ;
	        var buf = new StringBuffer();
	        var tempNew=temp.split(",");
	       if(temp.length!=0)
	        {
	          
	           for(var i=0;i<tempNew.length;i++)
	            {
	             if(tempNew[i]!='')
		         {
		         idSet.add(trim(tempNew[i]));
		         }
	          }
	        }
              var theForm = topChechBoxObj.form, z = 0;
		      var x=0
		      for(z=0; z<theForm.length;z++)
		      {
		       if(theForm[z].type == 'checkbox' && theForm[z].name != 'checkall' && theForm[z].name=='selectedEntry')
		       {
				    if(theForm[z].value!='')
			        IdsArray[x]=theForm[z].value ;
			        IdsCheck[x]=theForm[z].checked;
			        x++;
		        }
		      }
            if(check==true)
              {  
		        if(tempNew.length!=null&&tempNew.length==1)
		        {
		        
		           for(var g=0;g<IdsArray.length;g++)
		           {
		            idSet.add(trim(IdsArray[g]));
		           }
		        }else
		        {  
		          for(var g=0;g<IdsArray.length;g++)
		           {
		            idSet.add(trim(IdsArray[g]));
		           }
		        }
		        }
		        
		        /*
		        else
		        { 
		         for(var g=0;g<IdsArray.length;g++)
		         {
		            if(IdsCheck[g]==true)
		           idSet.remove(trim(IdsArray[g]));
		           alert(idSet)
		         }
		       }*/
          if(check==true){
	      for(itr = idSet.iterator(); itr.hasNext();)
	        {
	           var id=itr.next();
	          if(id!=''&&id!=','&&id!='a'&&id!='b'&&id!='c'){
	           buf.append(trim(id));
	           }
	        }
	        }else{
	        
	        
	        
	        for(itr1 = idSet.iterator(); itr1.hasNext();){
	         var flag=0;
	        var id=itr1.next();
	       
           if(id!=''&&id!=','&&id!='a'&&id!='b'&&id!='c'){
           
            for(var g=0;g<IdsArray.length;g++){
            
            if(id==IdsArray[g]){
             flag=1;
            break;
           }
         }
            
            if(flag==0)
            buf.append(trim(id));
            
            }
  
	      }
	    }
        bufStr=trim(buf.toString())
        document.getElementById('hiddenVal').value =bufStr ;
        checkUncheckAllARP(topChechBoxObj);
   }
     
  function checkUncheckAllARP(theElement) {
	var theForm = theElement.form, z = 0;
    for(z=0; z<theForm.length;z++){
		if(theForm[z].type == 'checkbox' && theForm[z].name != 'checkall' && theForm[z].name=='selectedEntry'){
	        theForm[z].checked = theElement.checked;
        }
    }
  }
  
  /*Function to check vendor count should not be greater then PageCount */
  function checkPageCount(pageCount,count)
  {
     var checkBox = document.potentialARPForm.selectedEntry;
     if(checkBox.length==undefined)
     {
       //alert(pageCount+"  ::: "+document.potentialARPForm.vendorPageCount.value+" ::: "+count);
       if(pageCount < document.potentialARPForm.vendorPageCount.value)
       {
	       alert("Vendor Page Count cannot be greater than Page Count.");
	       document.potentialARPForm.vendorPageCount.focus();
	       return false;
       }
     }
     else
     {
       //alert(pageCount+"  ::: "+document.potentialARPForm.vendorPageCount[count-1].value+" ::: "+count);
       if(pageCount < document.potentialARPForm.vendorPageCount[count-1].value)
       {
	       alert("Vendor Page Count cannot be greater than Page Count.");
	       document.potentialARPForm.vendorPageCount[count-1].focus();
	       return false;
       }
     }
       
  }
  
   
 
//function for export to excel
function acceptPotentialExcel(path)
{
	formname = document.potentialARPForm;
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
					var str = formname.selectedEntry[j].value;
					var final = str.tokenize("-","",false);
					selectedArr = "'"+final[1]+"'";
					flag=1;
				}
				else if(flag!=0)
				{
					var str = formname.selectedEntry[j].value;
					var final = str.tokenize("-","",false);
					selectedArr = selectedArr + ",'"+final[1]+"'";
				}
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
				var str = formname.selectedEntry[j].value;
				var final = str.tokenize("-","",false);
				selectedArr = "'"+final[1]+"'";
				flag=1;
			}
			else if(flag!=0)
			{
			    var str = formname.selectedEntry[j].value;
				var final = str.tokenize("-","",false);
				selectedArr = selectedArr + "," +final[1];
			}
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
  

//function for Accept All functionality
function acceptPotentialExcelAll(path)
{
    document.potentialARPForm.action = path;
	document.potentialARPForm.submit();
}

  