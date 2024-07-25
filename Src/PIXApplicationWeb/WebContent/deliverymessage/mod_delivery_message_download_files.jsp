<%@ taglib prefix="ajaxUploading" uri="/WEB-INF/fileUploading.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pearson.pix.presentation.fileuploading.command.FileUploadListener.FileUploadStats"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>
 <% session.removeAttribute("FILE_UPLOAD_STATS"); %>
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="USER_INFO" value="<%=session.getAttribute("USER_INFO")%>" />
<c:set var="lineNo" value='<%=request.getAttribute("lineNo")%>' />
<c:set var="pono" value='<%=request.getAttribute("pono")%>' />
<c:set var="upload" value='<%=request.getAttribute("upload")%>' />
<c:set var="index" value='<%=request.getAttribute("index")%>' />
<c:set var="del" value='<%=request.getParameter("del")%>' />
<c:set var="approvalFlag" value="<%=request.getParameter("approvalFlag")%>" />
<c:set var="singlefile" value='<%=request.getParameter("singlefile")%>' />
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="fileID"><%=request.getParameter("fileId")%></c:set>
<c:set var="msgID"><%=request.getParameter("msgId")%></c:set>
<c:set var="poNO"><%=request.getParameter("poNO")%></c:set>
<c:set var="subfolderName" value="<%=request.getAttribute("subfolderName")%>" /> 
<c:set var="fileNameOnly"><%=request.getParameter("fileNameOnly")%></c:set>
<c:set var="delreplace"><%=request.getParameter("delreplace")%></c:set>
<c:set var="uploadListSize" value='${fn:length(fileUploadingForm.uploadFile)}' />
<c:if test="${poversion==null||poversion==''}">
<c:set var="poversion"><%=request.getAttribute("poversion")%></c:set>
  <c:set var="fileNameList" value="" />
</c:if>

<html>
<head>
<script src="<%=request.getContextPath()%>/js/prototype.js" type="text/javascript"></script>
<script >
  
  function deleteFileFromSys(indx,url){
  var uri=url+'&fileIndex='+indx;
  MM_openBrWindowNew(uri,'DownLoadFile','scrollbars=yes,width=800,height=410,top=10');
  }
  
  
  function makeReadOnly(){
 
  var kwrd=event.keyCode
  //document.getElementById('selectFile').focus();
  if(kwrd.length!=0){
  return false;
  }else{
  return false;
  }
  
  }
  
   function downloadWIN( windowURL ) {
  
return window.open( windowURL,'download','width=10,scrollbars=0,height=10,toolbar=0,locatio n=0,directories=0,status=0,menuBar=0,screenX=50,sc reenY=50,left=50,top=50') 
}

	function submit(path,index)
	{ 
	    var fileurl=document.getElementById('fileNameUrl'+index).innerHTML ;
	    var  filename=document.getElementById('fileNameid'+index).innerHTML ;
	    var val_url='/PIXManual/bill_of_lading/'+fileurl ; 
	     document.fileUploadForm.fileName.value=filename;
	     document.fileUploadForm.fileURL.value=val_url ;
		document.fileUploadForm.action=path;
		document.fileUploadForm.submit();
	}
	
	function submitdel(path)
	{
		document.fileUploadForm.action=path;
		document.fileUploadForm.submit();
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
           setTimeout("window.open('"+theURL+"','"+winName+"','"+features+"')",1000);
        }
        
        function fileDelCheck(theURL1,theURL2,winName,features,filesize,approvalFlag,index){
        
        if(approvalFlag!='Y'){
        if(filesize!=0&&filesize==1){
        var msg="   \n" ;
            msg+="You need to upload a new file before deleting this file as Bill of Lading is mandatory for each line Item.\n\n" ;
           msg+="                                             Do you want to continue?" ;
       if(confirm(msg)){
      
	   var fileurl=document.getElementById('fileNameUrl'+index).innerHTML ;
	    var  filename=document.getElementById('fileNameid'+index).innerHTML ;
	    theURL2=theURL2+'&fileNameOnly='+filename+'&file='+fileurl ;
        MM_openBrWindowNew2(theURL2,winName,features);
        }else{
        window.close();
        }
        }else{
        if(confirm('Do you really want to delete this file?')){
        var fileurl=document.getElementById('fileNameUrl'+index).innerHTML ;
        theURL1=theURL1+'&flag=download&file='+fileurl ;
        MM_openBrWindowNew2(theURL1,winName,features);
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
              //var str = /^[a-zA-Z0-9#,._ ]*$/;
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
                    var fileNameOnly='${fileNameOnly}' ;
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
                	// alert("File upload aborted, Please remove special characters (e.g. #,!,@,$,%,^,:) from the filename.");
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
               // funcPassValue('${lineNo}','${pono}','${index}');
                
               document.getElementById('fileStatus').value='done' ;
               document.getElementById('uplaodingStatus').innerHTML="File Uploading is Completed.";
                var sig = '${singlefile}' ; 
                if(sig=='true'){
                window.location.href= '<%=request.getContextPath()%>/delMsgDetail/downloadfile.do?lineNo=${lineNo}&msgId=${msgID}&flag=download&pono=${pono}&poversion=${poversion}&index=${index}&subfolderName=${subfolderName}&delreplace=true';
               // window.open('<%=request.getContextPath()%>/delMsgDetail/downloadfile.do?lineNo=${lineNo}&msgId=${msgID}&flag=download&pono=${pono}&poversion=${poversion}&index=${index}&subfolderName=${subfolderName}','DownLoadFile','scrollbars=yes,width=800,height=410,top=10,location=1,status=1');
               }else{
                // alert('done');
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
            
            
    
            
    function AjaxCall(index,uploadListSize){
    var singlefile='${singlefile}' ; 
    // if(uploadListSize==0){
    window.opener.document.getElementById('fileSize').value=uploadListSize ;
    
    if(singlefile!='true'){
    if(document.getElementById('fileStatus')!=null){ 
    var fileStatus=document.getElementById('fileStatus').value;
    document.getElementById('fileStatus').value='' ; 
    if(fileStatus.length==0){
    if(document.getElementById('selectFile')!=null){
       var file=document.getElementById('selectFile').value
       window.opener.document.getElementById('ajaxCall').value=file  ;
       window.opener.document.getElementById('upload'+index).focus();
        }
       }
     }
     }
     // }
    }
    
    function winCloseNew(flg){
    if(flg=='true'){
    window.opener.close()  ;
    }
    }
    
    function deleteConfirmation(url,index){
    if(confirm('Do you really want to delete this file?'))
    {
        var fileurl=document.getElementById('fileUploadId'+index).innerHTML ; 
        url=url+'&file='+fileurl ;
       var delWin=window.open(url,'DownLoadFile','scrollbars=yes,width=800,height=410,top=100');
      }
    }
        
     
     
      function parentReadOnly(){
         window.opener.document.getElementById('page').style.disable=true ;
       }
         
         // parentReadOnly();   
			</script>
			
			
<title>Pix</title>
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

<body class="BodyStyle" id="bodyId" onLoad="javasript:winCloseNew('${del}')" onUnload="javasript:AjaxCall('${index}','${uploadListSize}');" >
<iframe id='target_upload' name='target_upload' src='' style='display: none'></iframe>
<div id="messageDiv"  style='Display:none'></div>

<form enctype="multipart/form-data" name="fileUploadForm" action="<%=request.getContextPath()%>/FileUpload?pono=${pono}&lineNo=${lineNo}" method="post" target="target_upload" >


<c:set var="fileListSize" value='${fn:length(fileUploadingForm.fileList)}' />

<input type="hidden" name="fileName"/>
<input type="hidden" name="fileURL"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" align="right" valign="middle" class="topLinkPopup"><a href="#" onClick="javascript:window.close();" style="cursor:hand"><img src="../images/close.gif" width="16" height="9" border="0"></a></td>
  </tr>
  <tr>
    <td width="2" rowspan="4" align="left" valign="top" class="LeftBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="2" height="1"></td>
    <td width="100%" height="4" align="right" valign="top" class="LinkGreen"><img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
    <td width="4" rowspan="4" align="right" valign="top" class="RightBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
  </tr>
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Delivery Message - Bill of lading </span></td>
  </tr>

  <tr>
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
            <td class="lightblueColumn"><input name="billNo" type="text" id="billNo" class="textfield" maxlength="25" size="15" /></td>
            <td class="blueColumn">Freight No: </td>
            <td class="lightblueColumn"><input name="freightNo" type="text" id="freightNo" class="textfield" maxlength="25" size="15"/></td>
            <td class="blueColumn">Carrier No: </td>
            <td class="lightblueColumn"><input name="carrierNo" type="text" id="carrierNo" class="textfield" maxlength="60" size="15"/></td>
          </tr>
          
         
          <ajaxUploading:timer ajaxRef="page/checkUploadStatus" startOnLoad="false" frequency="1700" />
        
            <%--<div id="contentDiv" style="display:block"> --%>
          
          <tr>
            <td class="blueColumn">Select File: </td>
            <td colspan="5" class="lightblueColumn"><input name="datafile" type="file" id="selectFile" onKeyPress="return makeReadOnly();" class="textfield" size="110"  ></td>
            </tr>
          <tr>
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
            <div id='uplaodingStatus'class='blueColumn'style='Display:none'></div>
            <div id="contentDiv" style="display:block">
            </div>
            </td>
            </tr>
            
            <input type="hidden" name="doAjaxStatus" value="true"/>
            
          <tr>
            <td colspan="4"><input name="Button32" type="button" id="submit_button" class="buttonMain" onClick="process('<%=request.getContextPath()%>','${lineNo}','${pono}','${index}');" value="Attach File" ></td>
          </tr>
          
          </div>
          
          
        </table>
        
        
        
        </td>
      </tr>
     
      </c:when>
      <c:when test="${flag!=null && flag!='' && fileListSize=='0'}">
        		<td class="noRecordsMessage" align="center" valign="middle" height="30px"> Currently there are no files to display </td>
       </c:when>
      </c:choose>
      <tr>
        <td height="16"></td>
      </tr>
    </table>
    
   <c:if test="${singlefile!='true'}"> 	
    <c:if test="${uploadListSize>0}">
    	
    	<div style="overflow:scroll;overflow-x: hidden; height:126px; width:98%">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
        
        <tr>
          <td class="tableHeading">Attached File's </td>
          <c:if test="${USER_INFO.roleTypeDetail.roleType=='M'}">
          <td align="center" class="tableHeading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Delete File</td>
          </c:if>
        </tr>
        
         
	        <c:forEach var="list" items="${fileUploadingForm.uploadFile}" varStatus="index1">
	        <%System.out.println("jsp...........");%>
	        	
	      <%-- poLineNo ${list.poLineNo} ----- lineNo ${lineNo}  <%=request.getContextPath()%>/deletefilefromsystem/delete.do?pono=${pono}&lineNo=${lineNo}&file=${list.fileUrl}&fileId=${list.fileSeq}&index=${index}&ACTION_TYPE=delete
	       <c:if test="${list.poLineNo==lineNo}">  window.close();window.open('<%=request.getContextPath()%>/FileUpload/deleteFile.do?pono=${pono}&lineNo=${lineNo}&file=${list.fileUrl}&fileId=${list.fileSeq}&index=${index}','download','scrollbars=yes,width=800,height=410,top=10');--%>
	        <tr>
	          <%--<td width="80%" class="tableRow">
	          deleteFileFromSys('${index1.index}','<%=request.getContextPath()%>/deletefilefromsystem/delete.do?pono=${pono}&poversion=${poversion}&lineNo=${lineNo}&file=${list.fileUrl}&fileId=${list.fileSeq}&index=${index}&ACTION_TYPE=delete');  
	          <a href="javascript:submit('<%=request.getContextPath()%>/download/download.do?PATH=\\PIXManual\\bill_of_uploading\\${list.fileUrl}&FILENAME=${list.fileName}')" class="tableRowlink">${list.fileName}</a></td>--%>
	          <td width="80%" class="tableRow"><a href="#" class="tableRowlink">${list.fileUrl}</a></td>
	          <c:if test="${USER_INFO.roleTypeDetail.roleType=='M'}">
	           <c:set var="fileNameList" value="${fileNameList}${list.fileName}#" />  
	          <div id="fileUploadId${index1.index}" style="display:none" >${fn:escapeXml(list.fileUrl)}</div>
	          <td width="20%" align="center" class="tableRow" ><a href="#" onClick="javascript:deleteConfirmation('<%=request.getContextPath()%>/deletefilefromsystem/delete.do?pono=${pono}&poversion=${poversion}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&ACTION_TYPE=delete&fileIndex=${index1.index}&subfolderName=${subfolderName}','${index1.index}');" ><img src="<%=request.getContextPath()%>/images/icon_delete.gif" width="12" height="12" border="0"></a>
	          </td>
	          </c:if>
	        </tr>
	      <%-- </c:if>--%>
	       
	       </c:forEach>
      <%-- </c:if>--%>
      
      
       

        <tr>
          <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
	</div>
    </c:if>
     <c:if test="${uploadListSize==0&&delreplace!='true'}">
     <script>
    funcPassValue(null,null,'${index}');
    </script>
   	</c:if>	
   	</c:if>	
        	
    <c:if test="${fileListSize>0}">
      <div style="overflow:scroll;overflow-x: hidden; height:126px; width:98%">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
        
        <tr>
          <c:if test="${flag!='download'}">
          	<td width="1%" align="center" class="tableHeading">DELIVERY MESSAGE NO.</td>
          </c:if>
          <td class="tableHeading">UPLOADED FILE</td>
          <td width="1%" align="center" class="tableHeading">DOWNLOAD</td>
          <c:if test="${flag=='download'&& USER_INFO.roleTypeDetail.roleType=='M'}">
          <td width="1%" align="center" class="tableHeading">&nbsp;&nbsp;&nbsp;&nbsp;DELETE FILE</td>
          </c:if>
        </tr>
       
       
        	
	        <c:forEach var="list" items="${fileUploadingForm.fileList}" varStatus="index1">
	        
	        <tr>
	          
	          <c:if test="${flag!='download'}">
	          	<td align="center" class="tableRow">DM_${pono}_${poversion}_${list.msgId}</td>
	          </c:if>
	          <div id="fileNameid${index1.index}" style="display:none">${fn:escapeXml(list.fileName)}</div>
	          <div id="fileNameUrl${index1.index}" style="display:none">${fn:escapeXml(list.fileUrl)}</div>
	          <td width="80%" class="tableRow"><a href="javascript:submit('<%=request.getContextPath()%>/download/download.do','${index1.index}')" class="tableRowlink">${list.fileName}</a></td>
	          <td align="center" class="tableRow"><a href="javascript:submit('<%=request.getContextPath()%>/download/download.do','${index1.index}')"><img src="<%=request.getContextPath()%>/images/icon_download-1.gif" width="10" height="15" border="0"></a></td>
	            <c:if test="${flag=='download'&& USER_INFO.roleTypeDetail.roleType=='M'}">
	             <ajaxUploading:DMFileSubFolder id="subfolderName2" msgId="${msgId}" lineNo="${lineNo}"/>
	          <td width="20%" align="center" class="tableRow"><a href="#" onClick="javascript:fileDelCheck('<%=request.getContextPath()%>/deletefromdownload/deleteFile.do?pono=${pono}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&poversion=${poversion}&del=true&msgId=${list.msgId}&subfolderName=${subfolderName2}','<%=request.getContextPath()%>/purchaseorder/downloadfile.do?pono=${pono}&poNO=${pono}&lineNo=${lineNo}&fileId=${list.fileSeq}&index=${index}&poversion=${poversion}&del=true&msgId=${list.msgId}&singlefile=true&subfolderName=${subfolderName2}','Delete','scrollbars=yes,width=800,height=410,top=10','${fileListSize}','${approvalFlag}','${index1.index}');"><img src="<%=request.getContextPath()%>/images/icon_delete.gif" width="12" height="12" border="0"></a></td>
	          </c:if>
	        </tr>
	       
	       </c:forEach>
	       
     
       

        <tr>
          <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
	</div>
  </c:if>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
		
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;<input name="Button3" type="button" id="close_button" class="buttonMain" onClick="javascript:window.close();" value="Close"></td>
        </tr>
        
         <tr>
           <td height="20"  >&nbsp;</td>
            </tr>
            
         
     </table></td>
  </tr>
  <tr>
    <td height="100%" valign="bottom"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" valign="bottom" class="bottomrepeatInside"><table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
            
            <tr>
              <td width="20%"><img src="../images/trans.gif" width="1" height="1" /></td>
              <td align="center" valign="bottom" class="bottomtext">copyright savvas 2020</td>
              <td width="20%" align="right"><img src="<%=request.getContextPath()%>/images/bottom_logo.gif" alt="PCS Logo" width="85" height="37"></td>
              <td width="10" align="right">&nbsp;</td>
            </tr>
            
        </table></td>
        </tr>
    </table></td>
  </tr>
</table>
<input type="hidden" id="fileStatus" />
<input type="hidden" id="fileNameList" value="${fileNameList}">
</form>
<ajaxUploading:enable/>

 
    

</body>
</html>