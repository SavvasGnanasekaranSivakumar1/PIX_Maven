//function for enabling only numbers for input 

function filtercheck()
{
if(!(event.keyCode >= 48 && event.keyCode <= 57))
{
return false;
}
}

//function to open a pop up
function MM_openBrowserWindow(theURL,winName,features) { //v2.0
    window.open(theURL,winName,features);
 }

//function for handling of forgot password
function forgotpassword(path)
{
if(document.LoginForm.loginId.value=="")
{
alert('Please enter your Login Name');
}
else
{
document.LoginForm.action=path;
document.LoginForm.submit();
}

}

//function for handling the drop down on homepage for search
function submithome(urlPO,urlPlanning,urlBookSpec,urlUsage,urlDMNew,urlDMOld,urlGRNew,urlGROld,urlCA)
{
	var url="";
	var description="";
	var poDetail = "";
	if(document.homePageForm.moduleCode.value=="")
	{
	alert('Please select a Transaction Type for search');
	return;
	} 
	if(document.homePageForm.moduleCode.value=="PLA")
	{
	description="Planning";
	}
	if(document.homePageForm.moduleCode.value=="BSP")
	{
	description="Book Specification";
	}
	if(document.homePageForm.moduleCode.value=="USG")
	{
	description="Usage";
	}
	if(!(document.homePageForm.moduleCode.value=="POR")&& (document.homePageForm.jobno.value!=""))
	{
	alert('Job No. is not associated with'+' '+ description);
	return;
	}
	if(document.homePageForm.moduleCode.value=="POR")
	{
	document.homePageForm.action =urlPO; 
	}
	if(document.homePageForm.moduleCode.value=="PLA")
	{
	document.homePageForm.action = urlPlanning;
	}
	if(document.homePageForm.moduleCode.value=="BSP")
	{
	document.homePageForm.action = urlBookSpec;
	}
	if(document.homePageForm.moduleCode.value=="USG")
	{
	document.homePageForm.action = urlUsage;
	}
	//Gaurav
	if(document.homePageForm.moduleCode.value=="DM"||document.homePageForm.moduleCode.value=="GR")
	{ 
	  if(document.homePageForm.pono.value=="")
	  {
	   alert('Purchase Order No. cannot be blank');
	   return;
	  }
	  else
	  {  
	 	ctx = "/pix";
	  	var xml = getPODetail(ctx);
	  	//alert(xml);
	  	var msg1 = xml.split("</message>");
        var countval = msg1[0].split("<message>");
	  	if(((countval[1].toString()).indexOf(","))!= -1){
	  	arr = countval[1].split(",");
          var poid = arr[0];
          var pover = arr[1];
          var porel = arr[2];
          var pono = arr[3];
          var poorderType=arr[4];
          var poOrder=arr[5];
          if(document.homePageForm.moduleCode.value=="DM")
        		{ 	  
	  				if(poorderType == 'O')
	  				url = urlDMNew+"&pono="+pono+"&poid="+poid+"&poversion="+pover+"&rno="+porel+"&orderPaper="+poorderType+"&fo=paperfo"+"&order="+poOrder+"&orderType="+poorderType;
	  				else
	  				url = urlDMOld+"&pono="+pono+"&poid="+poid+"&poversion="+pover+"&rno="+porel+"&order="+poOrder+"&orderType="+poorderType;
	  	    	} 
	  		else
	  			{   
	  				if(poorderType == 'O')
	  				url = urlGRNew+"&pono="+pono+"&poid="+poid+"&poversion="+pover+"&rno="+porel+"&orderPaper="+poorderType+"&fo=paperfo"+"&order="+poOrder+"&orderType="+poorderType;
	  				else
	  				url = urlGROld+"&pono="+pono+"&poid="+poid+"&poversion="+pover+"&rno="+porel+"&PAGE_VALUE=1"+"&order="+poOrder+"&orderType="+poorderType;
	  			}
	       document.homePageForm.action = url;
          }
          else
	  	 {
	  		alert('PO does not exist');
	  		return;
	  	 }
	    }
	    }
	if(document.homePageForm.moduleCode.value=="CA")
	{
	if(document.homePageForm.pono.value=="")
	  {
	   alert('Purchase order cannot be blank');
	   return;
	  }
	document.homePageForm.action = urlCA+"&poNoFilter="+document.homePageForm.pono.value;
	}
	document.homePageForm.submit();
}

//function for fetching the drop down list of values for status of modules
function submitstatus(home,urlPO,urlPlanning,urlBookSpec,urlUsage)
{
   	if(document.homePageForm.moduleCode.value !="DM"&& document.homePageForm.moduleCode.value !="GR" && document.homePageForm.moduleCode.value !="CA"  )
	{
		if(document.homePageForm.moduleCode.value=="")
		{
		document.homePageForm.action =home;
		} 
    	if(document.homePageForm.moduleCode.value=="POR")
		{
		document.homePageForm.action =urlPO;
		}
		if(document.homePageForm.moduleCode.value=="PLA")
		{
		document.homePageForm.action =urlPlanning;
		}
		if(document.homePageForm.moduleCode.value=="BSP")
		{
		document.homePageForm.action =urlBookSpec;
		}
		if(document.homePageForm.moduleCode.value=="USG")
		{
		document.homePageForm.action =urlUsage;
		}
		document.homePageForm.submit();
	}	
}
	//to get po details from backend
	function getPODetail(ctx)
	{
		
		var url="";
	 	if(document.homePageForm.pono.value!=""){
			url = ctx+"/home/dmlist.do?"+"pono="+document.homePageForm.pono.value;
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
	 formname = eval('document.homePageForm');
	 
    if (req.readyState == 4) 
    { // Complete
      if(req.status == 200)
      { //OK
      
               xml = req.responseText;
              // alert("xml " + xml);
          
           msg1 = xml.split("</message>");
          
          countval = msg1[0].split("<message>");
          var returnString = "";
          returnString = countval[1].toString();
          //alert("msg1 " + countval[1]);
          if(((countval[1].toString()).indexOf(","))!= -1)
          {
          arr = countval[1].split(",");
          document.homePageForm.poid.value = arr[0];
          document.homePageForm.pover.value = arr[1];
          document.homePageForm.porel.value = arr[2];
          document.homePageForm.pono.value = arr[3];
          document.homePageForm.poorderType.value=arr[4];
          document.homePageForm.poOrder.value=arr[5];
          alert("inside podetail()"+countval[1]);
          return returnString; 
           }
           else
           {
           document.homePageForm.poid.value = "";
           document.homePageForm.pover.value = "";
           document.homePageForm.porel.value = "";
           document.homePageForm.poorderType.value="";
           document.homePageForm.poOrder.value="";
           alert("inside podetail()"+countval[1]);
          return ""; 
           }
           
           }
          
	}
	}*/
