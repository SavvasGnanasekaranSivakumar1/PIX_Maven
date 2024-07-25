<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.dto.admin.User"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil,com.pearson.pix.business.common.PIXConstants"%>
<c:set var="haveWriteAccess" value="false"></c:set>
<%
	User objUser = (User)session.getAttribute("USER_INFO");
	if(PIXUtil.getPrivilege(request,PIXConstants.DROPSHIP_CODE).equals("WRITE") || PIXUtil.getPrivilege(request,PIXConstants.DROPSHIP_CODE).equals("BOTH"))
	{
%>
<c:set var="haveWriteAccess" value="true" />	
<% } %>

<html>
	<head>
		<title>PIX Upload Shipment Confirmation</title>
		<script language="javascript" type="text/javascript">
			function loadPopup(flag) {
				if(flag == 'true') {
					var filename = '<%=request.getParameter("filename")%>';
					var url = '/pix/dropship/shippingConfirmation/read.do?actionType=displayShipInfo&PageNo=1';
					popup_window = window.open(url,'popup_window','width=1124, height=700,left=10,top=10 scrollbars=yes');

					//window.opener.location.href = '/pix/dropship/shippingConfirmation/read.do?actionType=uploadShipInfo';
				} 
			}
		
			function checkFile() {
				var filename = document.uploadShipInfoForm.uploadFile.value;
				var dotIndex = filename.lastIndexOf(".");
				var extention = filename.substring(dotIndex);	
				if($.trim(filename) == "") {
					alert("Please select the file for uploading.");
					return false;
				} else if(extention != '.xls') {
					alert("Please upload the Shipment Confirmation detail in Excel(.xls) only.");
				  	return false;
				}
				
				document.uploadShipInfoForm.action="/pix/dropship/shippingConfirmation.do?actionType=uploadShipInfo";
				document.uploadShipInfoForm.submit();
		
				//window.open('/pix/dropship/shippingConfirmation/read.do?actionType=uploadShipInfo&filename='+filename,'','scrollbars=yes, toolbar=no,menubar=no,status=1,width=960, height=600, top=50,left=15');
				
				return true;
			}
			
			
			function openTemplate() {
				window.open("<%=request.getContextPath()%>/dropship/DropShipmentConfermationTemplate.xls");			
			}
			function openPDF() {
				window.open("<%=request.getContextPath()%>/dropship/DFC Development Guide.pdf");			
			}
			
		</script>
	</head>
	<body>
	  <tr>
    	<td height="20" align="left" valign="top" class="paddingSmallT paddingSmallR">
	    <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9" onload="loadPopup('${validateTemplateFlag}')"><span class="headingText">Shipping Confirmation</span></td>
	  </tr>
	 	  
	  <tr>
	    <td align="left" valign="top" class="padding23">	

		
		<table width="500px" border="0" cellspacing="0" cellpadding="0">
			<c:if test="${validateTemplateFlag eq false}">
			  <tr>
			  <td>
			  	<h5 style="color: RED"> Upload failed. Your Excel sheet does not conform to the format PIX requires. Please use the link below to download a sample 
										template in the correct format, modify your file accordingly, and upload it again.</h5>
			  </td>
			  </tr>	  
			</c:if>
	  			  
			<c:if test="${ShipDataConfirm eq true}">
			  <tr>
			  <td>
			  	<h5 style="color: GREEN"> Ship confirmed data is successfully uploaded.</h5>
			  </td>
			  </tr>	  
			</c:if>
	  			  
			<tr>
				<td>
					<fieldset class="legendBorder">
						<legend class="legendeTitle" style="font-size: 11px;">
							Upload the Ship confirmed orders
						</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="5"></td>
							</tr>
							<tr>
								<td align="left" valign="top">
								<html-el:form action="/dropship/shippingConfirmation.do" enctype="multipart/form-data" styleId="uploadShipInfoForm">
								
									<table width="100%" border="0" cellpadding="0" cellspacing="1">
										<tr>
											<td height="5">&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td class="blueColumn" style="font-size: 11px !important;">
												<strong>Browse your File </strong>
											</td>
											<td align="left" class="lightblueColumn">
												<html-el:file size="30" property="uploadFile"></html-el:file>
											</td>
										</tr>
										<tr>
											<td class="blueColumn"> &nbsp; </td>
											<td align="left" class="lightblueColumn">
											<c:if test="${haveWriteAccess}">
												<input type="button" class="buttonMain" name="listButton" value="Upload" onClick="return checkFile()" />
											</c:if>
											<c:if test="${!haveWriteAccess}">
												<input type="button" class="buttonMain" name="listButton" value="Upload" disabled="disabled" onClick="return checkFile()" />
											</c:if>
											</td>
										</tr>
										<tr>
											<td class="blueColumn"> &nbsp; </td>
											<td align="left" class="lightblueColumn"> &nbsp; </td>
										</tr>
										<tr>
											<td class="blueColumn" style="font-size: 11px !important;">
												<strong>Vendor Name</strong>
											</td>
											<td align="left" class="lightblueColumn" style="font-size: 11px !important;">
												<%=objUser.getFirstName()%> <%=objUser.getLastName()%>
											</td>
										</tr>
									</table>
								</html-el:form>
									
								</td>
           				 </tr>
			            <tr>
			              <td>
			              <br /><br />
			              
	
	 To download template <a href="<%=request.getContextPath()%>/dropship/DropshipTemplate.xls" target="_blank">click here</a>. <br>
	
						<%--<br>
						<a href="#" onclick="openTemplate();">Download Template</a> <br>
						--%></td>
			            </tr>
          			</table></fieldset></td>
        	</tr>
        	<tr>
          		<td>&nbsp;</td>
       		</tr>
      </legend>
      <table>
            <tr>
            <td height="5" colspan="1">&nbsp;</td>
        <td colspan="1" class="text" style="color:red;"> 
 			<span style="color:orange;font-weight:bold;font-size:20px; !important;" >*</span> &nbsp;- To see what the Excel spreadsheet should look like, click here to download a mock-up with sample data<a href="<%=request.getContextPath()%>/dropship/Vendor_shipping_confirmation-mock_up.xls" target="_blank"> Click here</a>. <br>
			<img src="<%=request.getContextPath()%>/images/video1.gif" alt="View Video" width="20" height="18" >
 			- To review a video tutorial of how to use the DropShip screens in PIX <a href="https://pearsononline.webex.com/pearsononline/lsr.php?AT=pb&SP=MC&rID=26598097&rKey=5630aba4f2ffdc60" target="_blank">Click here.</a>      
         </td>
         </tr> 
         </table>
               </table>
      </table>
     </td> </tr>
     </table>
      
      	
	</body>
</html>