<%@ taglib prefix="ajaxUploading" uri="/WEB-INF/fileUploading.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pearson.pix.presentation.fileuploading.command.FileUploadListener.FileUploadStats"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>
 <% session.removeAttribute("FILE_UPLOAD_STATS"); %>
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="USER_INFO" value="<%=session.getAttribute(\"USER_INFO\")%>" />
 <c:if test="${USER_INFO == null}">
 <c:redirect url="/"/>
 </c:if>
 <c:set var="isHistoryPop" value="<%=request.getAttribute(\"isHistoryPop\")%>" /> <%--  from BOLUploadingCommand--%>
 <c:set var="historyFlag" value="<%=request.getParameter(\"historyFlag\")%>" />

<c:set var="isDMPop" value="<%=request.getAttribute(\"isDMPop\")%>" />
<c:set var="MESSAGE" value="<%=request.getAttribute(\"MESSAGE\")%>" />
<c:set var="lineNo" value='<%=request.getAttribute(\"lineNo\")%>' />
<c:set var="pono" value='<%=request.getAttribute(\"pono\")%>' />
<c:set var="productCode" value='<%=request.getAttribute(\"productCode\")%>' />  
<c:set var="upload" value='<%=request.getAttribute(\"upload\")%>' />
<c:set var="index" value='<%=request.getAttribute(\"index\")%>' />
<c:set var="del" value='<%=request.getParameter(\"del\")%>' />
<c:set var="delwin" value='<%=request.getParameter(\"delwin\")%>' />
<c:set var="approvalFlag" value="<%=request.getParameter(\"approvalFlag\")%>" />
<c:set var="singlefile" value='<%=request.getParameter(\"singlefile\")%>' />
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="fileID"><%=request.getParameter("fileId")%></c:set>
<c:set var="msgID"><%=request.getParameter("msgId")%></c:set>
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<%-- <c:set var="roleType"><%=request.getParameter("roleType")%></c:set> --%> 
<script>
</script>
<c:set var="poNO"><%=request.getParameter("poNO")%></c:set>
<c:set var="subfolderName" value="<%=request.getAttribute(\"subfolderName\")%>" /> 
<c:set var="fileNameOnly"><%=request.getParameter("fileNameOnly")%></c:set>
<c:set var="delreplace"><%=request.getParameter("delreplace")%></c:set>
<c:set var="uploadListSize" value='${fn:length(bolUploadingForm.uploadFile)}' />
<c:if test="${poversion==null||poversion==''}">
<c:set var="poversion"><%=request.getAttribute("poversion")%></c:set>
<c:set var="fileNameList" value="" />
</c:if>
<c:if test="${singlefile==null||singlefile==''}">
<c:set var="singlefile"><%=request.getAttribute("singlefilenew")%></c:set>
<c:set var="fileID"><%=request.getAttribute("fileid")%></c:set>
<c:set var="fileNameOnly"><%=request.getAttribute("filename")%></c:set>
</c:if>
 
<c:set var="roleTracking" value ='<%=session.getAttribute(\"roleTrackingFlag\")%>'/>
<c:set var="showRLDToCU"><%=request.getAttribute("showRLDToCU")%></c:set>
<c:set var="DMGRMode"><%=request.getAttribute("DMGRMode")%></c:set>
<c:set var="roleTrackingCU"><%=request.getAttribute("roleTrackingg")%></c:set>
<%--  <script>
	alert('roleTrackingCU');
	alert('${roleTrackingCU}');
	
</script>

<script>
	alert('roleTracking');
	alert('${roleTracking}');
</script>
--%>


<html>
<head>
<script src="<%=request.getContextPath()%>/js/prototype.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/table_patch.js" type="text/javascript"></script>

<script type="text/javascript">
function submitCancelDelivery(path)
{
    document.bolUploadingForm.action = path;
	document.bolUploadingForm.submit();

}

	</script>



<script >
	function setImageIcon(uploadListSize,index){
	if(uploadListSize!= 0)
	{
	//window.opener.document.getElementById('bola'+index).style.display="none";
	window.opener.document.getElementById('bolb'+index).style.display="";
	}
	else
	{
	window.opener.document.getElementById('bola'+index).style.display="";
	window.opener.document.getElementById('bolb'+index).style.display="none";
	}
	}
   var currentFile;
   var delWin ;
  function deleteFileFromSys(indx,url){
  var uri=url+'&fileIndex='+indx;
  MM_openBrWindowNew(uri,'DownLoadFile','scrollbars=yes,width=800,height=410,top=10');
  }
  
  
   function makeReadOnly(){
    var kwrd=event.keyCode
   if(kwrd.length!=0){
     return false;
     }else{
    return false;
    }
   }
   
   
   function uploadFile(contx,path,singlefile)
   {//alert('singlefile = '+singlefile);
      if((document.getElementById('billNo').value).replace(/ /g,'')!='' && (document.getElementById('freightNo').value).replace(/ /g,'')!='' && (document.getElementById('carrierNo').value).replace(/ /g,'')!=''&& document.getElementById('selectFile').value!='')
       {
        var specialChar=validateSpecialChar();
        var duplicateFile=duplicateFileChecker(specialChar);
         if(specialChar==true&&duplicateFile==true){
         if(singlefile=='true'){
             
             var fileID='${fileID}';
            // var fileNameOnly='${fileNameOnly}' ; 
          var fileNameOnly= document.getElementById('fileNameOnly').innerHTML; 
          path=contx+'/bolfilelist/downloadfile.do?operation=rollDetailsHistory&historyFlag=DMHistory&lineNo=${lineNo}&msgId=${msgID}&flag=download&pono=${pono}&poversion=${poversion}&index=${index}&subfolderName=${subfolderName}&delreplace=true&delwin=true&fileID='+fileID+'&filename='+fileNameOnly+'&currentFile='+currentFile;
		//	alert('123');      
         }
         toggleButton('submit_button');
         toggleButton('close_button');
		document.bolUploadingForm.action=path;
		document.bolUploadingForm.target='_self';
	//	alert(path);
		document.bolUploadingForm.submit();
	//	alert('345');
		}else{
		if(specialChar==true&&duplicateFile==false){
          alert("This file is already uploaded, please upload file with different file name.");
          }else{
          alert("Please remove the special characters (e.g. #,!,@,$,%,^,:) from the file name and try to upload the file again.");
              } 
           return false;
		   }
     }else{
        alert("Please input all the values before attaching a file.");
        return false;
     }
   }
  
  
 
  
   function downloadWIN( windowURL ) {
   return window.open( windowURL,'download','width=10,scrollbars=0,height=10,toolbar=0,location=0,directories=0,status=0,menuBar=0,screenX=50,sc reenY=50,left=50,top=50') 
   }

	function submit(path,index)
	{ 
	    var fileurl=document.getElementById('fileNameUrl'+index).innerHTML ;
	  
	    var  filename=document.getElementById('fileNameid'+index).innerHTML ;
	  
	    var val_url='/PIXManual/bill_of_lading/'+fileurl ; 
	     document.bolUploadingForm.fileName.value=filename;
	     document.bolUploadingForm.fileURL.value=val_url ;
		document.bolUploadingForm.action=path;
		document.bolUploadingForm.target='_self';
		document.bolUploadingForm.submit();
	}
	
	

    function MM_openBrWindowNew(theURL,winName,features) { //v2.0
          var billNo=document.getElementById('billNo').value;
           var freightNo=document.getElementById('freightNo').value;
           var carrierNo=document.getElementById('carrierNo').value;
           theURL=theURL+'&billNo='+billNo+'&freightNo='+freightNo+'&carrierNo='+carrierNo;
           setTimeout("window.open('"+theURL+"','"+winName+"','"+features+"')",1000);
             // window.open(theURL,winName,features);
        }
        
        function MM_openBrWindowNew2(theURL,winName,features) { //v2.0
           childW=setTimeout("window.open('"+theURL+"','"+winName+"','"+features+"')",1000);
           childW.moveTo(0,0);
        }
        
        function MM_openBrWindowNew3(theURL,winName,features) { //v2.0
          childW=window.open(theURL,winName,features); 
          childW.moveTo(0,0);
        }
        
        function fileDelCheck(theURL1,theURL2,winName,features,filesize,approvalFlag,index){
        //alert ("filesize"+filesize+"approvalFlag"+approvalFlag+"index"+index);
        if(approvalFlag!='Yes'){
        if(filesize!=0&&filesize==1){
        var msg="   \n" ;
            msg+="You need to upload a new file before deleting this file as Bill of Lading is mandatory for each line Item.\n\n" ;
           msg+="                                             Do you want to continue?" ;
       if(confirm(msg)){
      
	    var fileurl=document.getElementById('fileNameUrl'+index).innerHTML ;
	    var  filename=document.getElementById('fileNameid'+index).innerHTML ;
	    theURL2=theURL2+'&fileNameOnly='+filename+'&file='+fileurl ;
	 //   alert (theURL2);
         delWin= MM_openBrWindowNew3(theURL2,winName,features);
        }else{
        window.close();
        }
        }else{
        if(confirm('Do you really want to delete this file?')){
        var fileurl=document.getElementById('fileNameUrl'+index).innerHTML ;
      //  theURL1=theURL1+'&flag=download&file='+fileurl ;
        MM_openBrWindowNew3(theURL1,winName,features);
        }
           }
        }else{
           alert('You cannot delete this Bill of Lading file as this has been already processed.');
          }
        }
        
			function toggleButton(buttonID) {
			 if ( document.getElementById(buttonID).disabled == true ) {
					document.getElementById(buttonID).disabled = false;
				} else {
				document.getElementById(buttonID).disabled = true;
				} 
			} 
			
			var fileName='' ;
			 function  validateSpecialChar(){
			 var check=true;
              var selectFileURL=document.getElementById('selectFile').value ;
              var arr=selectFileURL.split('\\');  
              var selectFile=arr[arr.length-1];  
              for(var i=0;i<selectFile.length;i++){
              var spch=selectFile.charAt(i);
              if(spch=='!'||spch=='@'||spch=='$'||spch=='%'||spch=='&'||spch=='='||spch=='^'||spch==':'||spch==';'||spch=='\\'||spch=='/'||spch=='?'||spch=='#'){
                 check=false ;
                 break;
               }
              }
              if(check==true){
              fileName=selectFile ;
              }
            return check ;
    }
     
   
       function duplicateFileChecker(check) {
         var flag=true ;
			 if (fileName != ''&&check==true) {
					var fileNameList=document.getElementById('fileNameList').value ;
					var fileNameLower=fileName.toLowerCase();
					if(fileNameList.lenght!=0){
					var fileNameListArr=fileNameList.split('#');
					for(var i=0;i<fileNameListArr.length;i++){
					 if(fileNameListArr[i].length!=0&&fileNameListArr[i]==fileNameLower){
					    flag=false;
					    break;
					 }
					 }
					}
				} 
				currentFile=fileName ;
				fileName='' ;
				return flag ;
			} 
        
     
     
            function process(context,lineNo,pono,id) {
             
                
                document.getElementById('uplaodingStatus').innerHTML="" ;
            	if(document.getElementById('billNo').value!='' && document.getElementById('freightNo').value!='' && document.getElementById('carrierNo').value!=''&& document.getElementById('selectFile').value!='')
            	{
            	     var specialChar=validateSpecialChar();
            	     var duplicateFile=duplicateFileChecker(specialChar);
            	    if(specialChar==true&&duplicateFile==true){
            	    var sig = '${singlefile}' ;
            	    var appender='' ;
            	    if(sig=='true'){
            	    var fileID='${fileID}';
                    var msgID='${msgID}';
                    var poNO='${poNO}'; 
                    var fileNameOnly='' ;
            	    appender='&deleteAlso=true&fileID='+fileID+'&msgID='+msgID+'&poNO='+poNO+'&filename='+fileNameOnly;
            	    }
            	    
                	toggleButton('submit_button');
                	toggleButton('close_button');
                	startpage_checkUploadStatus();
                	var ob=document.fileUploadForm ; 
                	ob.action='<%=request.getContextPath()%>/FileUpload?pono=${pono}&lineNo=${lineNo}&subfolderName=${subfolderName}&index=${index}'+appender ;
                	ob.submit();
                	return true;
                	}else{
                	if(specialChar==true&&duplicateFile==false){
                	alert("This file is already uploaded, please upload file with different file name.");
                	}else{
                	 alert("Please remove the special characters (e.g. #,!,@,$,%,^,:) from the file name and try to upload the file again.");
                	} 
                	return false;
                	}
                }
                else
                {
                	alert("Please input all the values before attaching a file.");
                	return false;
                }
            }
            
             var updater = null;
            function startStatusCheck(context) {
                document.getElementById("messageDiv").innerHTML = "";
            	document.getElementById('submit_button').disabled = true;
            	document.getElementById('close_button').disabled = true;
                updater = new Ajax.PeriodicalUpdater(
                                        'messageDiv',
                                        context+'/UploadStatus',
                                        {asynchronous:true, frequency:1, method: 'get', onFailure: reportError});
                return true;
            }

            function reportError(request) {
                killUpdate("Error communicating with server. Please try again.");
            }

            function killUpdate() {
                $('submitButton').disabled = false;
                updater.stop();
                new Ajax.Updater('messageDiv',
			                     'UploadStatus',
			                     {asynchronous:true, method: 'get', parameters: 'status=end', onFailure: reportError});
            }
            
             function setProggressBar(pComplete,bytesPerMillisecondStr,bytesProcessedStr,currentSizeStr,ceilingSize,timeLeft) { 
               divBody="" ;
               document.getElementById('uplaodingStatus').style.display="block" ;
               document.getElementById('uplaodingStatus').innerHTML="File is Uploading...";
               divBody +="<div class='prog-border'><div class='prog-bar' style='width: " + pComplete + "%;'></div></div>";
               divBody +="<table><tr><td class='st' width='250px;'>"+timeLeft+ " (at " + bytesPerMillisecondStr + "kb/sec)</td>" ;
               divBody += " <td class='st'>"+bytesProcessedStr + " MB / "+currentSizeStr+" MB ( "+ceilingSize+"% )</td></tr></table>" 
               document.getElementById('contentDiv').innerHTML=divBody ;
                
            }
            
            function uploadStatus(flag){ 
             document.getElementById('uplaodingStatus').style.display="block" ;
             document.getElementById('uplaodingStatus').innerHTML="" ;
            
            if(flag=='done'){
               document.getElementById('fileStatus').value='done' ;
               document.getElementById('uplaodingStatus').innerHTML="File Uploading is Completed.";
                var sig = '${singlefile}' ; 
                if(sig=='true'){
                window.location.href= '<%=request.getContextPath()%>/delMsgDetail/downloadfile.do?lineNo=${lineNo}&msgId=${msgID}&flag=download&pono=${pono}&poversion=${poversion}&index=${index}&subfolderName=${subfolderName}&delreplace=true';
               }else{
               MM_openBrWindowNew2('<%=request.getContextPath()%>/purchaseorder/downloadfile.do?lineNo=${lineNo}&pono=${pono}&upload=success&index=${index}&poversion=${poversion}&singlefile=${singlefile}&del=${del}&subfolderName=${subfolderName}','DownLoadFile','scrollbars=yes,width=800,height=410,top=10');
               }
               }
              else{
              
               var msg="" ;
                if(flag=="fileoversize")
                 msg+="File size can not be more than 4 MB." ;
                 else
                msg+="This file does not exist." ;
                document.getElementById('fileStatus').value=flag  ;
                var ide = '${index}' ;
                if(msg.length!=0){
               var uploadListSize= '${uploadListSize}' ;
                
               if(uploadListSize=='0'){
                funcPassValue(null,null,ide);
                }
                }
               document.getElementById('uplaodingStatus').innerHTML="<label class='errorMessageText'>"+msg+"</label>"; 
               document.getElementById('contentDiv').style.display ='none' ;
              }
            }
            
            function funcPassValue(lineNo,pono,id)
            {
                if(window.opener.document.getElementById('hid'+id)!=null){
            	if(lineNo==null&&pono==null){
            	window.opener.document.getElementById('hid'+id).value="" ;
            	}else{
            	window.opener.document.getElementById('hid'+id).value=""+pono+"-"+lineNo;
            	}
            	}
            }
             
           function funcPassValueNew(flag){  
            var singlefile='${singlefile}' ;
              var uploadListSize= '${uploadListSize}' ; 
           if(singlefile!='true'&&uploadListSize=='0'){
            var id='${index}' ;
            if(window.opener.document.getElementById('hid'+id)!=null){
            if(flag!='done'){
            	window.opener.document.getElementById('hid'+id).value="" ;
            	}else{ 
            	var lineNo='${lineNo}';
            	var pono='${pono}';
            	window.opener.document.getElementById('hid'+id).value=""+pono+"-"+lineNo;
            	  }
            	}
              }
           
            }
    function winCloseNew(flg,flg1){
    if(flg=='true'){
    window.opener.close()  ;
    }
    }
    
    function showMsg(msg){
    if(msg!='null' && msg.length!=0 && msg!=null && msg != ''){
      document.getElementById('uplaodingStatus').innerHTML="<label class='errorMessageText'>"+msg+"</label>"; 
       document.getElementById('uplaodingStatus').style.display ='' ;
    }
    }
    
    
    function deleteConfirmation(url,index){
    if(confirm('Do you really want to delete this file?'))
    {
        var fileurl=document.getElementById('fileUploadId'+index).innerHTML ; 
        url=url+'&file='+fileurl ;
        delWin=window.open(url,'DownLoadFile','scrollbars=yes,width=800,height=410,top=100');
      }
    }
 </script>
			
			
<title>Upload BOL File</title>
<link href="../css/pixcss.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=request.getContextPath()%>/js/AjaxPartsTaglib.js.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/menu.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/pix.js"></script>
<script type="text/JavaScript">
<!--
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script>
</head>

<body class="BodyStyle" id="bodyId" onLoad="javasript:winCloseNew('${del}','${delwin}')" onunload="javascript:setImageIcon('${uploadListSize}','${index}')" >
<%--<iframe id='target_upload' name='target_upload' src='' style='display: none'></iframe> 

<div id="messageDiv"  style='Display:none'></div>  enctype="multipart/form-data"--%>

<html-el:form action="/purchaseorder/uploadbolloadfile" enctype="multipart/form-data" target="DownLoadFile">
<%--
<form enctype="multipart/form-data" name="bolUploadingForm" action="<%=request.getContextPath()%>/FileUpload?pono=${pono}&lineNo=${lineNo}" method="post" target="target_upload" >
--%>
<div id="fileNameOnly" style="display:none" >${fn:escapeXml(fileNameOnly)}</div>
<c:set var="fileListSize" value='${fn:length(bolUploadingForm.fileList)}' />

<c:set var="dmDtListSize" value='${fn:length(bolUploadingForm.dmDtlList)}' />
<script>

</script>
<input type="hidden" name="fileName"/>
<input type="hidden" name="fileURL"/>

  
<%-- <c:if test="${roleType == 'M'}">  --%> 



<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" align="right" valign="middle" class="topLinkPopup"><a href="#" onClick="javascript:window.close();" style="cursor:hand"><font class="textWhite">close </font><img src="../images/close.gif" width="16" height="9" border="0"></a></td>
  </tr>
  <tr>
    <td width="4" rowspan="4" align="left" valign="top" class="LeftBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="2" height="1"></td>
    <td width="100%" height="4" align="right" valign="top" class="LinkGreen"><img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
    <td width="4" rowspan="8" align="right" valign="top" class="RightBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
  </tr>
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Delivery Message - Bill of lading </span></td>
  </tr>
  <tr>

<script>  
	
</script>
  <c:if test="${(roleType == 'M' || roleType == null) && roleTracking == 0}">    <%-- attach disable for roletracking --%>
  	<c:choose>
  		<c:when test="${flag!='download'}">
    		<td align="left" valign="top" class="padding23">
      		<table width="98%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
        		<td height="16"></td>
        	</tr>
      		<tr>
        		<td height="16"><table width="100%" border="0" cellspacing="1" cellpadding="0">
          	<tr>
            	<td class="blueColumn">Bill No:</td>
            	<td class="lightblueColumn">
             	 	<html-el:text property="billNo" styleId="billNo" size="15" styleClass= "textfield"  maxlength="25" />
            	</td>
            	<td class="blueColumn">Freight No: </td>
            	<td class="lightblueColumn">
             		<html-el:text property="freightNo" styleId="freightNo" size="15" styleClass= "textfield"  maxlength="25" />
            	</td>
            	<td class="blueColumn">Carrier No: </td>
            	<td class="lightblueColumn">
             		<html-el:text property="carrierNo" styleId="carrierNo" size="15" styleClass= "textfield"  maxlength="60" />
	            </td>
         	</tr>
            <tr>
            	<td class="blueColumn">Select File: </td>
            	<td colspan="5" class="lightblueColumn">
            		<html-el:file property="dataFile" styleId="selectFile" onkeypress="return makeReadOnly();" styleClass= "textfield"  size="100" maxlength="210" />
            	</td>
            </tr>
          	<tr>
            	<td class="blueColumn">&nbsp; </td>
            	<td colspan="5" class="lightblueColumn">*Special Characters are not allowed in the filename (e.g. #,!,@,$,%,^,:).</td>
            </tr>
          	<tr>
           		<td colspan="4" height="5px"></td>
          	</tr>
          	<tr>
           		<td colspan="6" height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
          	<tr>
            	<td colspan="4" height="5px"></td>
            </tr>
            <tr>
            	<td colspan="2">
            		<div id='uplaodingStatus' class='blueColumn' style='display:none'></div>
         			<%--   <div id="contentDiv" style="display:block"> </div> --%>
	            </td>
            </tr>	
            <script>
            	showMsg('${MESSAGE}');
            </script>
            <input type="hidden" name="doAjaxStatus" value="true"/>
	        <tr>


           <c:if test="${isHistoryPop == 'rollDetailsHistory'  }"> 
   				
                  <td>
	  	       <input name="Button32" type="button" id="submit_button" class="buttonMain" onClick="javascript:uploadFile('<%=request.getContextPath()%>','<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?popType=rollDetailsHistory&operation=attachFile&lineNo=${lineNo}&pono=${pono}&poversion=${poversion}&index=${index}&ACTION_TYPE=UPLOADING&subfolderName=${subfolderName}&upload=success&msgId=${msgID}&productCode=${productCode}','${singlefile}');" value="Attach File">
                  
                  </td>
              
 
			</c:if> 
           
            <c:if test="${isDMPop == 'rollDetailsDM' }"> 
   				
                  <td>
	  	       <input name="Button32" type="button" id="submit_button" class="buttonMain" onClick="javascript:uploadFile('<%=request.getContextPath()%>','<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?popType=rollDetailsDM&operation=attachFile&operation=attachFile&lineNo=${lineNo}&pono=${pono}&poversion=${poversion}&index=${index}&ACTION_TYPE=UPLOADING&subfolderName=${subfolderName}&upload=success&msgId=${msgID}&productCode=${productCode}','${singlefile}');" value="Attach File">
                  
                  </td>
			</c:if>

            	</td>
          		<td><input name="Button3" type="button" id="close_button" class="buttonMain" onClick="javascript:window.close();" value="Close"></td>
          	</tr>
          	
        </table>
        </td>
      </c:when>
      <c:when test="${flag!=null && flag!='' && fileListSize=='0'}">
        		<td class="noRecordsMessage" align="center" valign="middle" height="30px"> Currently there are no files to display </td>
       </c:when>
      </c:choose>
    </c:if> <%-- roleType attach disable for roletracking --%>
  <%-- ************************************************************************************* --%>   
    
<%--     <script>
    	alert('${roleType}');
    	alert('${roleTracking}');
    	alert('${historyFlag}');
    </script>
    --%> 
      <c:if test="${(roleType == 'M' || roleType == null) && roleTracking > 0 && historyFlag == 'DMHistory'}">    <%-- historyFlag attach enable for DM History roletracking --%>
    <script>
    </script>    
  	
  	<c:choose>
  		<c:when test="${flag!='download'}">
    		<td align="left" valign="top" class="padding23">
      		<table width="98%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
        		<td height="16"></td>
        	</tr>
      		<tr>
        		<td height="16"><table width="100%" border="0" cellspacing="1" cellpadding="0">
          	<tr>
            	<td class="blueColumn">Bill No:</td>
            	<td class="lightblueColumn">
             	 	<html-el:text property="billNo" styleId="billNo" size="15" styleClass= "textfield"  maxlength="25" />
            	</td>
            	<td class="blueColumn">Freight No: </td>
            	<td class="lightblueColumn">
             		<html-el:text property="freightNo" styleId="freightNo" size="15" styleClass= "textfield"  maxlength="25" />
            	</td>
            	<td class="blueColumn">Carrier No: </td>
            	<td class="lightblueColumn">
             		<html-el:text property="carrierNo" styleId="carrierNo" size="15" styleClass= "textfield"  maxlength="60" />
	            </td>
         	</tr>
            <tr>
            	<td class="blueColumn">Select File: </td>
            	<td colspan="5" class="lightblueColumn">
            		<html-el:file property="dataFile" styleId="selectFile" onkeypress="return makeReadOnly();" styleClass= "textfield"  size="100" maxlength="210" />
            	</td>
            </tr>
          	<tr>
            	<td class="blueColumn">&nbsp; </td>
            	<td colspan="5" class="lightblueColumn">*Special Characters are not allowed in the filename (e.g. #,!,@,$,%,^,:).</td>
            </tr>
          	<tr>
           		<td colspan="4" height="5px"></td>
          	</tr>
          	<tr>
           		<td colspan="6" height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
          	<tr>
            	<td colspan="4" height="5px"></td>
            </tr>
            <tr>
            	<td colspan="2">
            		<div id='uplaodingStatus' class='blueColumn' style='display:none'></div>
         			<%--   <div id="contentDiv" style="display:block"> </div> --%>
	            </td>
            </tr>	
            <script>
            	showMsg('${MESSAGE}');
            </script>
            <input type="hidden" name="doAjaxStatus" value="true"/>
	        <tr>


           <c:if test="${isHistoryPop == 'rollDetailsHistory'  }"> 
            <script>

            </script>
   				
                  <td>
	  	       <input name="Button32" type="button" id="submit_button" class="buttonMain" onClick="javascript:uploadFile('<%=request.getContextPath()%>','<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?popType=rollDetailsHistory&operation=attachFile&historyFlag=DMHistory&lineNo=${lineNo}&pono=${pono}&poversion=${poversion}&index=${index}&ACTION_TYPE=UPLOADING&subfolderName=${subfolderName}&upload=success&msgId=${msgID}&productCode=${productCode}','${singlefile}');" value="Attach File">
                  
                  </td>
              
 
			</c:if> 
           
            <c:if test="${isDMPop == 'rollDetailsDM' }"> 
   				
                  <td>
	  	       <input name="Button32" type="button" id="submit_button" class="buttonMain" onClick="javascript:uploadFile('<%=request.getContextPath()%>','<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?popType=rollDetailsDM&operation=attachFile&lineNo=${lineNo}&pono=${pono}&poversion=${poversion}&index=${index}&ACTION_TYPE=UPLOADING&subfolderName=${subfolderName}&upload=success&msgId=${msgID}&productCode=${productCode}','${singlefile}');" value="Attach File">
                  
                  </td>
			</c:if>

            	</td>
          		<td><input name="Button3" type="button" id="close_button" class="buttonMain" onClick="javascript:window.close();" value="Close"></td>
          	</tr>
          	
        </table>
        </td>
      </c:when>
      <c:when test="${flag!=null && flag!='' && fileListSize=='0'}">
        		<td class="noRecordsMessage" align="center" valign="middle" height="30px"> Currently there are no files to display </td>
       </c:when>
      </c:choose>
    </c:if> <%-- HistoryFlag roleType attach enable for roletracking --%>
    
    
 <%-- End ************************************************************************************* --%>   
    
      </tr>
      <tr>
        <td height="16"><br>
      </tr>
</table>
<c:if test="${dmDtListSize == 0 && (roleTracking > 0 || roleTrackingCU > 0 ) }"> <%-- || showRLDToCU == 'yes' This flag was applies to show roll level info to all users who view "download files" link, whether they are Roll tracking or not--%>   
<table width="100%">		
<tr>          
<td align="center" class="tableHeading">  
	No Roll Details Available For The Transaction.
</td>
<tr>
</table>
</c:if>
    <div style="position=absolute;height:396; width:100%" />
    <c:if test="${roleType == 'M' || roleType == null}">
   <c:if test="${singlefile!='true'}"> 	
   <c:if test="${uploadListSize>0}">
		<table border="0" cellspacing="0" cellpadding="0" >
		<tr>
	    <td width="11" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
		<td width="145" align="center" class="tabSubBg">Attached Files</td>
		<td width="11" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
		</tr>
		</table>
		
		          	<c:if test="${uploadListSize < 5}">
<%-- 97.8% --%><table width="98%" border="0" cellspacing="1" cellpadding="0" valign="top" >   
		</c:if>
		<c:if test="${uploadListSize > 4}">
<%-- 96%--%><table width="95.9%" border="0" cellspacing="1" cellpadding="0" valign="top" >
		</c:if>
		
		
	<%-- 	<c:if test="${uploadListSize < 5}">
		<table border="0" cellspacing="1" cellpadding="0" width="97.95%">
		</c:if>
		<c:if test="${uploadListSize > 4}">
		<table border="0" cellspacing="1" cellpadding="0" width="95.75%">
		</c:if> --%>
			<tr>
        		  <td class="tableHeading" width="80.55%">ATTACHED FILE </td>
          		 <c:if test="${USER_INFO.roleTypeDetail.roleType=='M'}">
          		  <td width="20%" align="center" class="tableHeading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DELETE FILE</td>
          	 	 </c:if>
        	   </tr>
        </table>
		
		<div style="overflow:auto; postion:relative;height:80; width:98%">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" align="left" >
		       
           
	         <c:forEach var="list" items="${bolUploadingForm.uploadFile}" varStatus="index1">
	    	  <tr>
	          	<td width="80%" class="tableRow">${list.fileUrl}</td>
	          	<c:if test="${USER_INFO.roleTypeDetail.roleType=='M'}">
	          	 <c:set var="fileNameList" value="${fileNameList}${list.fileName}#" />  
	          	<div id="fileUploadId${index1.index}" style="display:none" >${fn:escapeXml(list.fileUrl)}</div>


           <c:if test="${isHistoryPop == 'rollDetailsHistory'  }"> 
   				
                  <td>
	  	       
	  	     <td width="20%" align="center" class="tableRow" ><a href="#" onClick="javascript:deleteConfirmation('<%=request.getContextPath()%>/boltempfiledelete/delete.do?popType=rollDetailsHistory&operation=attachFile&historyFlag=DMHistory&pono=${pono}&poversion=${poversion}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&ACTION_TYPE=DELETE&fileIndex=${index1.index}&subfolderName=${subfolderName}&msgId=${msgID}&productCode=${productCode}','${index1.index}');" ><img src="<%=request.getContextPath()%>/images/icon_delete.gif" width="12" height="12" border="0"></a></td>  
                  
                  </td>
              
 
			</c:if> 
           
            <c:if test="${isDMPop == 'rollDetailsDM' }"> 
   				
                  <td>

	<td width="20%" align="center" class="tableRow" ><a href="#" onClick="javascript:deleteConfirmation('<%=request.getContextPath()%>/boltempfiledelete/delete.do?popType=rollDetailsDM&operation=attachFile&pono=${pono}&poversion=${poversion}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&ACTION_TYPE=DELETE&fileIndex=${index1.index}&subfolderName=${subfolderName}&msgId=${msgID}&productCode=${productCode}','${index1.index}');" ><img src="<%=request.getContextPath()%>/images/icon_delete.gif" width="12" height="12" border="0"></a></td>
                  
                  </td>
			</c:if>
	          </c:if>
	   			 </tr>
		 	</c:forEach>
        	<tr>
          		<td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
       		</tr>
		</table>
		</div>
	</c:if>
   	</c:if>	
   	</c:if>
 <%-- 2nd roleType Check--%>
    
    <script>
       var count =0 ;
    </script>
	<table>
	<tr>
     		<td>&nbsp; </td>
  	</tr>
	
	</table>
	<script>
	</script>
    <c:if test="${roleType == 'M' || roleType == 'C' || roleType == null}">
    <c:if test="${fileListSize > 0}">
		<table border="0" cellspacing="0" cellpadding="0">
		<tr>
	    <td width="11" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
			<td width="145" align="center" class="tabSubBg">Existing Files</td>
			<td width="11" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
		</tr>
		</table>
		<div style="overflow:auto;positon:relative;height:150;width:98%">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td class="tableHeading">UPLOADED FILE</td>
          <td width="1%" align="center" class="tableHeading">DELIVERY MESSAGE NO.</td>
          <td align="center" class="tableHeading">FILE UPLOADED DATE</td>
          <td align="center" class="tableHeading">FILE UPLOADED BY</td>
          <td align="center" class="tableHeading">DOWNLOAD</td>
          <c:if test="${flag!='download'&& USER_INFO.roleTypeDetail.roleType=='M'&& msgID!= 'null' && uploadListSize==0}">
          <td align="center" class="tableHeading">DELETE FILE</td>
          </c:if>
          
          </tr>
	        <c:forEach var="list" items="${bolUploadingForm.fileList}" varStatus="index1">
             <tr>
	          <div id="fileNameid${index1.index}" style="display:none" >${fn:escapeXml(list.fileName)}</div>  <%-- style="display:none" --%>
	          
	          <div id="fileNameUrl${index1.index}" style="display:none">${fn:escapeXml(list.fileUrl)}</div>
	          <td align="left" class="tableRow"><a href="javascript:submit('<%=request.getContextPath()%>/bolfiledownload/download.do?fileId=${list.fileSeq}','${index1.index}')" class="tableRowlink">${list.fileName}</a></td>
	          <td align="center" class="tableRow">DM_${pono}_${poversion}_${list.msgId}</td>
	          <td align="left" class="tableRow"><fmt:formatDate value="${list.creationDateTime}" type="both" pattern="MM/dd/yyyy" /></td>
	          <td align="left" class="tableRow">${list.createdBy}</td> 
	          <td align="center" class="tableRow"><a href="javascript:submit('<%=request.getContextPath()%>/bolfiledownload/download.do?fileId=${list.fileSeq}','${index1.index}')"><img src="<%=request.getContextPath()%>/images/icon_download-1.gif" width="10" height="15" border="0"></a></td>
	           <c:if test="${flag!='download'&& USER_INFO.roleTypeDetail.roleType=='M' && msgID != 'null' && uploadListSize==0}">
	             <ajaxUploading:DMFileSubFolder id="subfolderName2" msgId="${msgId}" lineNo="${lineNo}"/>

<%--	    <td align="center" class="tableRow"><a href="#" onClick="javascript:fileDelCheck('<%=request.getContextPath()%>/boltempfiledelete/delete.do?popType=rollDetailsHistory&pono=${pono}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&poversion=${poversion}&del=true&msgId=${list.msgId}&subfolderName=${subfolderName2}&ACTION_DELETE=DB_DELETE&ACTION_TYPE=DELETE','<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?pono=${pono}&poNO=${pono}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&poversion=${poversion}&del=true&msgId=${list.msgId}&singlefile=true&subfolderName=${subfolderName2}&SINGAL_DELETE=true','Delete','scrollbars=yes,width=800,height=410,top=10','${fileListSize}','${approvalFlag}','${index1.index}');"><img src="<%=request.getContextPath()%>/images/icon_delete.gif" width="12" height="12" border="0"></a></td>
 --%>  
<script>
</script>

 <c:if test="${isHistoryPop == 'rollDetailsHistory'  }"> 
	  	    <script>
	  	    </script>
	  	    <td align="center" class="tableRow"><a href="#" onClick="javascript:fileDelCheck('<%=request.getContextPath()%>/boltempfiledelete/delete.do?popType=rollDetailsHistory&operation=attachFile&historyFlag=DMHistory&pono=${pono}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&poversion=${poversion}&del=true&msgId=${list.msgId}&subfolderName=${subfolderName2}&ACTION_DELETE=DB_DELETE&ACTION_TYPE=DELETE','<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?popType=rollDetailsHistory&operation=attachFile&historyFlag=DMHistory&pono=${pono}&poNO=${pono}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&poversion=${poversion}&del=true&msgId=${list.msgId}&singlefile=true&subfolderName=${subfolderName2}&SINGAL_DELETE=true','Delete','scrollbars=yes,width=800,height=600,top=10','${fileListSize}','${approvalFlag}','${index1.index}');"><img src="<%=request.getContextPath()%>/images/icon_delete.gif" width="12" height="12" border="0"></a></td>
 </c:if> 
           
            <c:if test="${isDMPop == 'rollDetailsDM' }"> 
   				<script>
                  </script>
                 
<td align="center" class="tableRow"><a href="#" onClick="javascript:fileDelCheck('<%=request.getContextPath()%>/boltempfiledelete/delete.do?popType=rollDetailsDM&operation=attachFile&pono=${pono}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&poversion=${poversion}&del=true&msgId=${list.msgId}&subfolderName=${subfolderName2}&ACTION_DELETE=DB_DELETE&ACTION_TYPE=DELETE','<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?popType=rollDetailsDM&pono=${pono}&poNO=${pono}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&poversion=${poversion}&del=true&msgId=${list.msgId}&singlefile=true&subfolderName=${subfolderName2}&SINGAL_DELETE=true','Delete','scrollbars=yes,width=800,height=410,top=10','${fileListSize}','${approvalFlag}','${index1.index}');"><img src="<%=request.getContextPath()%>/images/icon_delete.gif" width="12" height="12" border="0"></a></td>
                  
                  
			</c:if>   


	          </c:if>
	        </tr>
	       </c:forEach>
        <tr>
          <td height="1" colspan="6" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      
	</div>
  </c:if>
</c:if> <%-- 3rd roleType --%>



 <c:if test="${dmDtListSize > 0 && ( roleTracking > 0 || roleTrackingCU > 0 )}"> <%-- || showRLDToCU == 'yes' This flag was applies to show roll level info to all users who view "download files" link, whether they are Roll tracking or not--%>
<%-- <c:if test="${fileListSize > 0}"> --%>     
     <table border="0" cellspacing="0" cellpadding="0" >
	<tr>
	<td height="20px"></td>
	</tr>
		<tr>
	    <td width="11" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
		<td width="145" align="center" class="tabSubBg">Roll Level Details</td>
		<td width="11" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
		</tr>
		
		</table> 
		
<table width="97.9%" border="0" cellspacing="0" cellpadding="0">
  <%-- <tr>
           		<td colspan="6" height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr> 		
          	<c:if test="${uploadListSize < 5}">
		<table border="0" cellspacing="1" cellpadding="0" width="97.95%">
		</c:if>
		<c:if test="${uploadListSize > 4}">
		<table border="0" cellspacing="1" cellpadding="0" width="95.75%">
		</c:if>    dmDtListSize
          	--%>  	
</table>

          	<c:if test="${dmDtListSize < 5}">
<%-- 97.8% --%><table width="97.8%" border="0" cellspacing="1" cellpadding="0" valign="top" >   
		</c:if>
		<c:if test="${dmDtListSize > 4}">
<%-- 96%--%><table width="95.8%" border="0" cellspacing="1" cellpadding="0" valign="top" >
		</c:if>


		      <div style="overflow:auto; height:100; width:90" class="hide">
		        <tr>
                   <td width="20%" align="center" class="tableHeading">ROLL #</td>
                   <td width="20%" align="center" class="tableHeading">ROLL WEIGHT</td>
                   <td width="20%" align="center" class="tableHeading">DM POSTED BY</td>
                   <td width="20%" align="center" class="tableHeading">DM POSTED DATE</td>
                </tr></div>
<%-- --%></table>    	

          	<c:if test="${dmDtListSize == 1}">
    <div style="overflow:auto;positon:relative;height:25;width:98%"> <%-- height:100; --%>   
		</c:if>
		<c:if test="${dmDtListSize == 2}">
   <div style="overflow:auto;positon:relative;height:50;width:98%"> <%-- height:100; --%>
		</c:if>
<c:if test="${dmDtListSize == 3}">
   <div style="overflow:auto;positon:relative;height:75;width:98%"> <%-- height:100; --%>
		</c:if>
		<c:if test="${dmDtListSize >= 4}">
   <div style="overflow:auto;positon:relative;height:100;width:98%"> <%-- height:100; --%>
		</c:if>

	<table width="100%" border="0" cellspacing="1" cellpadding="0" align="left" > 	
	 
        <c:forEach var="dmDtlList" items="${bolUploadingForm.dmDtlList}" varStatus="indexId">
                
                <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         <c:set var="class2" value="tableRowlink"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         <c:set var="class2" value="tableAlternateRowlink"/>
	      </c:if>   
        <script>count= eval('<c:out value="${dmDtlList.rollWeight}"></c:out>')+count;
         </script>
        <tr>
        <c:if test="${dmDtListSize < 5}">
		<td width="20%" align="left" class="${class1}">    <c:out value="${dmDtlList.rollNumber}"></c:out></td>
		</c:if>
		<c:if test="${dmDtListSize > 4}">
		<td width="20.9%" align="left" class="${class1}">    <c:out value="${dmDtlList.rollNumber}"></c:out></td>
		</c:if>
<%-- width="20.9%" --%> 
              <td width="20%" align="right" class="${class1}" align="right"><fmt:formatNumber value="${dmDtlList.rollWeight}" pattern="###,###"/></td>
               <td width="20%" align="left" class="${class1}"> <c:out value="${dmDtlList.dmPostedBy}"></c:out>  </td>
              <td width="20%" align="left" class="${class1}" align="right">  <c:out value="${dmDtlList.dmPostedDate}"></c:out>  </td>
           </tr>

           </c:forEach>
         <%--    <tr>
                   <td align="center" class="tableHeading">TOTAL</td>
                   <td align="right" class="tableHeading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${bolUploadingForm.totalRollWeight}" pattern="###,###"/></td>
                   <td align="center" class="tableHeading">&nbsp;</td>
                   <td align="center" class="tableHeading">&nbsp;</td>
                </tr>   --%>
           
       	</table>
    </div>

<table width="97.9%" border="0" cellspacing="0" cellpadding="0">
    	<tr>
           		<td colspan="6" height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
</table>

<c:if test="${dmDtListSize < 5}">
<%-- 97.8% --%><table width="97.8%" border="0" cellspacing="1" cellpadding="0" valign="top" >
		</c:if>
		<c:if test="${dmDtListSize > 4}">
<%-- 96%--%><table width="96%" border="0" cellspacing="1" cellpadding="0" valign="top" >
		</c:if>


		      <div style="overflow:auto; width:90" class="hide">
		        <tr>
                   <td width="20%" align="LEFT" class="tableHeading">TOTAL</td>
                   <c:if test="${bolUploadingForm.totalRollWeight != 0}">
                   <td width="20%" align="right" class="tableHeading"><fmt:formatNumber value="${bolUploadingForm.totalRollWeight}" pattern="###,###"/></td>
                   </c:if>
                   <c:if test="${bolUploadingForm.totalRollWeight == 0}">
                   <td width="20%" align="right" class="tableHeading"><fmt:formatNumber value="${totalRollWt}" pattern="###,###"/></td>
                   </c:if>
                   <td width="20%" align="center" class=""></td>
                   <td width="20%" align="center" class=""></td>
                </tr></div>
 

<%-- </table>    	<table >
  
    		<tr>
     <%--          <td class="tableHeading"> ROLL COUNT:   <c:out value="${dmDtListSize}"></c:out>  -%>
          <td class="tableHeading">TOTAL
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 
            <td class="tableHeading">
                        ROLL WEIGHT: &nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${bolUploadingForm.totalRollWeight}" pattern="###,###"/> 
                        <%-- <script> document.write(count);</script> -%>
              </td>
           </tr>
           
            --%>
           <c:if test="${isHistoryPop == 'rollDetailsHistory' && roleType != 'C' }">  <%-- only for DM History, not for Cost User --%>
           <script>
           </script>
   				<tr>
                  <td>&nbsp;</td></tr>
				<table>    
       	       <tr>
                  <td>
	  	       <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=DMHistoryPaperPop&msgID=${msgID}')" value="Export to Excel">
                  
                  </td>
                  <td>
                  	<input name="Close" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
                  </td>
                  </tr>
         </table>
 
			</c:if> 
           
            <c:if test="${isDMPop == 'rollDetailsDM' }"> 
   				<script>
           </script>
   				<tr>
                  <td>&nbsp;</td></tr>
   				
				<table>
       	       <tr>
                  <td>
	  	       <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=DMPaperPop&pono=${pono}&productCode=${productCode}&lineNo=${lineNo}')" value="Export to Excel">
                  
                  </td>
                  <td>
                  	<input name="Close" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
                  </td>
                  </tr>
         </table>
 
			</c:if>
			
			<c:if test="${roleType == 'C' && isHistoryPop == 'rollDetailsHistory'}">    <%-- also for costuser --%>
   				<script>
           </script>
   				<tr>
                  <td>&nbsp;</td></tr>
				<table>    
       	       <tr>
                  <td>
	  	       <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=DMHistoryPaperPop&msgID=${msgID}&DMGRMode=${DMGRMode}')" value="Export to Excel">
                  
                  </td>
                  <td>
                  	<input name="Close" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
                  </td>
                  </tr>
         </table>
 
			</c:if>
			
			
            
            
                      	

       	</table>
    </c:if>
    



      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
         <tr>
           <td height="20">&nbsp;</td>
         </tr>
         
         
         
         <table width="98%" border="0" cellspacing="0" cellpadding="0">
       


         
          <tr>
          <td>&nbsp;</td>
        </tr>
         <tr>
           <td height="20">&nbsp;</td>
         </tr>
         
     </table></td>
  </tr>
    </div>
  <tr>

<%-- 
    <td height="100%" valign="bottom">
    <div style="position:absolute;bottom:0; ">
    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" valign="bottom" class="bottomrepeatInside"><table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
            
            <tr>
              <td width="20%"><img src="../images/trans.gif" width="1" height="1" /></td>
              <td align="center" valign="bottom" class="bottomtext">copyright savvas 2020</td>
              <td width="20%" align="right"><img src="<%=request.getContextPath()%>/images/bottom_logo.gif" alt="PCS Logo" width="85" height="37"></td>
              <td width="10" align="right">&nbsp;</td>
            </tr>
            
        </table>
        </div></td>
        </tr>
    </table></td>
    --%>
    
    
  </tr>
</table>
<input type="hidden" id="fileStatus" />
<input type="hidden" id="fileNameList" value="${fileNameList}">

</html-el:form>


</body>
</html>