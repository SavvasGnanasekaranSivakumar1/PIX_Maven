<%@ include file="/common/taglibs.jsp"%>
<c:set var="CONTEXT_PATH" value="<%=request.getContextPath()%>" />
<c:set var="CONTEXT_PATH" value="${CONTEXT_PATH}" />
<c:set var="resultListSize" value="${potentialARPForm.resultListSize}"></c:set>
<c:set var="status" value="${potentialARPForm.status}"></c:set>
<c:set var="resultsPopUp" value="${potentialARPForm.resultsPopUp}" />
<link href="<c:out value="${CONTEXT_PATH}"/>/css/pixcss.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:out value="${CONTEXT_PATH}"/>/js/jquery.js"></script>
<script type="text/javascript" src="<c:out value="${CONTEXT_PATH}"/>/js/potentialarp.js"></script>

<HEAD>
	<TITLE>ARP Title Setup Result</TITLE>

<script type="text/javascript">	
//Below is the code related to ajax implementation for potential arp flip/reject

function getTitleStatusMsg(){

   var finalStr = '<c:out value="${potentialARPForm.finalStr}"/>';
   var checkBoxVal;  
   var status = '<c:out value="${potentialARPForm.status}"/>';
   var mode = '<c:out value="${potentialARPForm.mode}"/>';
   var tokens = finalStr.tokenize("|","", true);
   var url;
   for(var i=0; i<tokens.length; i++)
    {
        var str = tokens[i];
        var final = str.tokenize("-","",false);
        /*Fetching individual from the buffer str created on parent window side in format ---|---|---*/
        var eventId = final[0];
        var titleIsbn = final[1];
        if(final[2] == undefined || final[2] ==""){  
             var vendorPageCount = "";
        }else{  
           var vendorPageCount = final[2];
        }
        var isProvided = final[3];
        var isRequested = final[4];
        if(final[5] == undefined){
          var comments = "";
        }else{
          var comments = final[5];
        }  
        
        if(mode=='accept')
        {  url = "/pix/potentialarp/potentialarpaccept.do?action=accept";
        } 
        else  
        {  url = "/pix/potentialarp/potentialarpreject.do?action=reject";
        }          
        $.ajax({
				type: "GET",
				url: url,
				async: false,  
				data: "&eventId=" + eventId+"&titleIsbn="+titleIsbn+"&vendorPageCount="+vendorPageCount+"&isProvided="+isProvided+"&isReviewReq="+isRequested+"&comments="+comments+"&status="+status,
				success: function(data){
				$('#processingRow').remove();
				//New changes as part of Furtify XSS:DOM fix
				//var validatedData = validateInputData(data);
				var validatedData = cleanData(data);
				$('#dynaContentTable tr:last').after(validatedData);
				//$('#dynaContentTable tr:last').after(data);
			}
	 });
	} 
	window.opener.location.href = '/pix/potentialarp/potentialarplist.do?PAGE_VALUE=1&status='+status;// refreshing parent.
}

//Added below as part of Fortify XSS: DOM fix
/*function validateInputData(data){	
	const blacklist = /(<[^>]+>|<[^>]>|<\/[^>]>)|(document\.cookie)|eval|http-equiv/ig;
	var inputData = data.replace(blacklist, '');
	return inputData;
}*/
function cleanData(data){
	console.log(data);
	return DOMPurify.sanitize(data);
}

function closeParent(){	
	window.opener.parent.close();
}	

//	Jquery code for fixed header.


</script>
</HEAD>

<html-el:form action="/potentialarp/potentialarplist.do">
	<body>
		<TABLE width="100%" height="0" border="0" cellpadding="0" cellspacing="0">
			<TR>
				<td><img src="<%=request.getContextPath()%>/images/banner_01.gif" width="100%" height="15" /></td>
				<TD >
					<a href="javascript:window.close();"><img
							src="<c:out value="${CONTEXT_PATH}"/>/images/close.gif"
							alt="Close Window" width="20" height="15" border="0"> </a>
				</TD>
			</TR>
		</TABLE>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
			
			<TR>
                <TD height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Potential ARP Result</span></TD>
            </TR>
            <table id="dynaContentTable" width="98%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="tableHeading">TITLE ISBN</td>
              <td class="tableHeading">DESCRIPTION</td>
              <tr id="processingRow"><td class="tableRow">Processing<img src="<%=request.getContextPath()%>/images/processing.gif" height="3" width="3%" /><td></tr>
            </tr>
           </table>
            <TR>
				<TD align="left">
					<input name="Submit111" type="button" class="buttonMain"
						value="CLOSE" onclick="window.close();">
				</TD>
				<TD height="10" align="left"></TD>
			 </TR>
		</TABLE>
	</body>
	
	<!-- Added below as part of Fortify XSS: DOM vulnerability fix -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/dompurify/2.3.4/purify.min.js"></script>
	
	<script>
	$(document).ready(function() {
		
		var arpResultsPopUp = '<c:out value="${resultsPopUp}"/>';
		if(arpResultsPopUp == 'true'){
		
			self.setTimeout('getTitleStatusMsg()',2000);
		}
	});
	</script>
</html-el:form>