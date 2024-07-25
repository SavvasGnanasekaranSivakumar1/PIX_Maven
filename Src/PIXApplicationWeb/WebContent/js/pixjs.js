


// JavaScript Document
//function to open a pop up
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

function linksHover(x){

	document.getElementById("link_" + x).className="topLinkshover";
}

function linksNormal(x){
	document.getElementById("link_" + x).className="topLinks";
	selectedLink();
}



function selectedLink()
{
	
	var currurl = window.location;
	currurl = currurl + "";
	if(currurl.indexOf("/planning") != -1)
	{
		if(document.getElementById("link_3") != undefined)
			document.getElementById("link_3").className="topLinkshover";
	}
	else if(currurl.indexOf("/bookspecification") != -1)
	{
		if(document.getElementById("link_1") != undefined)
			document.getElementById("link_1").className="topLinkshover";
	}
	else if(currurl.indexOf("/costaccounting") != -1)
	{
		if(document.getElementById("link_8") != undefined)
			document.getElementById("link_8").className="topLinkshover";
	}
	else if(currurl.indexOf("/purchaseorder") != -1 || currurl.indexOf("/deliverymessage") != -1 || currurl.indexOf("/orderStatus") != -1 || currurl.indexOf("/goodsreceipt") != -1)
	{
		if(document.getElementById("link_2") != undefined)
			document.getElementById("link_2").className="topLinkshover";
	}
	else if(currurl.indexOf("/usage") != -1)
	{
		if(document.getElementById("link_4") != undefined)
			document.getElementById("link_4").className="topLinkshover";
	}
	else if(currurl.indexOf("/inventory") != -1)
	{
		if(document.getElementById("link_5") != undefined)
			document.getElementById("link_5").className="topLinkshover";
	}
	else if(currurl.indexOf("/reports") != -1)
	{
		if(document.getElementById("link_6") != undefined)
			document.getElementById("link_6").className="topLinkshover";
	}
	else if(currurl.indexOf("/admin") != -1)
	{
		if(document.getElementById("link_7") != undefined)
			document.getElementById("link_7").className="topLinkshover";
	}
	else if(currurl.indexOf("/potentialarplist") != -1)
	{
		if(document.getElementById("link_9") != undefined)
			document.getElementById("link_9").className="topLinkshover";
	}	
}  









function showrow()
{
document.getElementById("row1").style.display="inline";
}
//function for sucess page ok submit
function submitsuccessAction(path,obj)
{
    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	document.body.style.cursor = "wait";
    buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON..."; 
}

function submitAction(path,obj)
{

    formname = eval('document.'+obj.form.name);
  	formname.action = path;
	formname.submit();
	//document.body.style.cursor = "wait";
    //buttons2.innerHTML="PLEASE WAIT WHILE PROCESS IS GOING ON..."; 
}

//---------------------------------
function submitActionNDM(path,obj)
{
    formname = eval('document.'+obj.form.name);
    stat = document.getElementById("stat").value;
    
  	if(stat != 'CANCELLED'){
  	
  	formname.action = path;
	formname.submit();
	}
	else{
	alert("Cannot post Delivery Message for cancelled PO.");
	return false;
	}
	   
}
//---------------------------------
function checkFutureDate(textDateString)
{
	currDate = new Date();
	var currMilliSec = currDate.getTime();
	var partArray = textDateString.split('/',3);
	var textDate = new Date(partArray[2],partArray[0]-1,partArray[1]);
	var textMilliSec = textDate.getTime();
	//Set 1 day in milliseconds
	var oneDay=1000*60*60*24
	
	var diff = Math.ceil((textMilliSec-currMilliSec)/(oneDay))
	if(diff >= 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}
function submitPlanningCancelAction(path)
{
	if(confirm("This action will exit from the current page. Are you sure want to proceed?"))
	{
		if(document.getElementById("module").value =='titlesetup'){
		   var formObj = eval('document.potentialARPForm');  
		   formObj.action = path;
		   formObj.submit(); 
		}else{
		   document.planningForm.action = path;
		   document.planningForm.submit();
		}
    }
    
}

 function submitInvCancelAction(path)
{
	
	document.inventoryForm.action = path;
	document.inventoryForm.submit();
  }

function checkUncheckAll(theElement) {
	var theForm = theElement.form, z = 0;
    for(z=0; z<theForm.length;z++){
		if(theForm[z].type == 'checkbox' && theForm[z].name != 'checkall'){
	        theForm[z].checked = theElement.checked;
        }
    }
}

var slTab;

slTab=1;

function showDiv(number)
{

	if (slTab!=number)
	{
		document.getElementById('table'+number).style.display="block";
		document.getElementById('tab'+number).className="selected1";
		document.getElementById('table'+slTab).style.display="none";
		document.getElementById('tab'+slTab).className="";
		if(number>=3)
		{
			document.getElementById('txtTitle').style.display="none";
			
		}
		else
		{
			document.getElementById('txtTitle').style.display="Block";
		}
		
		slTab=number;
	}
}


//function to open a modal pop up
function MM_openModalBrWindow(theURL,winName,features) { 
	var newWindow = window.open(theURL,winName,features);
  	
	if (!document.all) {

		document.captureEvents (Event.CLICK);
	}
	document.onclick = function() {
		if (newWindow != null && !newWindow.closed) 
		{ 
			newWindow.focus();
		}
	}
}

    /*
        var request; 

		function addDeleteIdsFromSet(hiddenObj,contextPath,id,chechBoxObj){ 
		
		var url='';
		var idHidden='' ;
		
		
		if(hiddenObj.value!='')
		idHidden=hiddenObj.value;
		if(chechBoxObj.checked==true)
		url = contextPath+'/idlist/idListAjax.do?operationFlag=ADD&commandClassIndex=1&checkBoxId='+id+'&idHidden='+idHidden  ; 
		else
	    url = contextPath+'/idlist/idListAjax.do?operationFlag=REMOVE&commandClassIndex=1&checkBoxId='+id+'&idHidden='+idHidden  ;
	   
		if (window.ActiveXObject){ 
		request = new ActiveXObject("Microsoft.XMLHTTP"); 
		
		}else if (window.XMLHttpRequest){ 
		request = new XMLHttpRequest(); 
	    } 
	
		request.open("GET", url, true); 
		request.onreadystatechange = function() {confirmation(hiddenObj,''); } ; 
		request.send(null); 
		
	} 
	
	
	
	function addDeleteAllIdsFromSet(topChechBoxObj ,hiddenObj,contextPath,childChechBoxObj){ 
		
		var url='';
		var idHidden='' ;
		var IdsArray=new Array();
		
		var theForm = topChechBoxObj.form, z = 0;
		var x=0
      for(z=0; z<theForm.length;z++){
      
		if(theForm[z].type == 'checkbox' && theForm[z].name != 'checkall'){
		    if(theForm[z].value!='')
	        IdsArray[x]=theForm[z].value ;
	        x++;
        }
      }
    
		
		 
		if(hiddenObj.value!='')
		idHidden=hiddenObj.value;
		if(topChechBoxObj.checked==true)
		url = contextPath+'/idlist/idListAjax.do?operationFlag=ADDALL&commandClassIndex=1&checkBoxId='+IdsArray+'&idHidden='+idHidden  ; 
		else
	    url = contextPath+'/idlist/idListAjax.do?operationFlag=REMOVEALL&commandClassIndex=1&checkBoxId='+IdsArray+'&idHidden='+idHidden  ;
	   
		if (window.ActiveXObject){ 
		request = new ActiveXObject("Microsoft.XMLHTTP"); 
		
		}else if (window.XMLHttpRequest){ 
		request = new XMLHttpRequest(); 
	    } 
	
		request.open("GET", url, true); 
		request.onreadystatechange = function() {confirmation(hiddenObj,topChechBoxObj); } ; 
		request.send(null); 
		
	} 
	
	
	
	
	
	function confirmation(hiddenObj,topChechBoxObj){
	var list='';
	if (request.readyState == 4){ 
			
			if(request.status == 200) { 
			
		    xml = request.responseXML;
		 
		  // if (window.ActiveXObject){ 
		   
		   listset = xml.getElementsByTagName("list-set")[0];
		   
           if (listset != null) {
           
            listset= listset.firstChild.data;
            
            list=listset;
            } 

     //}
     
    
	   
		} else { 
			alert("Error loading page\n"+ request.status +":"+ request.statusText); 
			 } 
		}
	
	
	if(list!=''){
	 hiddenObj.value=list;
	}
	
	if(topChechBoxObj!='')
    checkUncheckAll(topChechBoxObj);
	}
	
	*/
	
	
	
	
 /*function to showing the checked checkBox*/
 function comapareCheckedValuesModified(selectedValue,currentPageValues,checkBoxLength,topChechBoxObj)
  { 
	if(selectedValue!='')
	{    
	    var checkedLength=0;
	    var checkBoxLengthInt=parseInt(checkBoxLength);
	    var paperIdStr=trim(selectedValue);
		var selDistinctValue = paperIdStr.split(',');
		
		for(i=0;i<selDistinctValue.length;i++)
		{
		    if(currentPageValues.length>1){
			for(j=0;j<currentPageValues.length;j++)
			{
				if(trim(selDistinctValue[i])==trim(currentPageValues[j].value))
				{
				 
					currentPageValues[j].checked = true;
					checkedLength++;	
				}	
			}
		 }else{
	        if(trim(selDistinctValue[i])==trim(currentPageValues.value))
				{
					currentPageValues.checked = true;	
					checkedLength++;
				}	
		  }
	  }	
	  if(checkBoxLengthInt!=0&&(checkBoxLengthInt==checkedLength)){
	       topChechBoxObj.checked =true;
	  }
	}
 }



  /* function to trim the string  */
  function trim(str) {  
     if (str != null) { 
            var i;   
      for (i=0; i<str.length; i++) {
           if (str.charAt(i)!=" ") {
              str=str.substring(i,str.length); 
              break;          
               }        
                }       
           for (i=str.length-1; i>=0; i--) { 
                  if (str.charAt(i)!=" ") { 
                        str=str.substring(0,i+1);
                          break;  
                      } 
          }
         if (str.charAt(0)==" ") {
               return "";   
             } else { 
                                                  
             return str;  
          } 
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
     function addDeleteIdsFromSetNew( hiddenObj,checkBoxId,checkBoxObj ){
      
       
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
  
  
  /*function to add or remove all check box ids for each single page*/
  function addDeleteAllIdsFromSetNew(topChechBoxObj ,hiddenObj,childChechBoxObj)
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
		       if(theForm[z].type == 'checkbox' && theForm[z].name != 'checkall')
		       {
				    if(theForm[z].value!='')
			        IdsArray[x]=theForm[z].value ;
			        IdsCheck[x]=theForm[z].checked
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
	        checkUncheckAll(topChechBoxObj);
   }
