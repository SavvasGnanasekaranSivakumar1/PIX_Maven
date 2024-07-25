<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/fileUploading.tld" prefix="fileUploading"%>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="stat"><%=request.getParameter("stat")%></c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="pageFilter" value='<%=request.getParameter("pageFilter")%>' /> 
<c:set var="ponoFilter" value='<%=request.getParameter("ponoFilter")%>' /> 
<c:set var="isbnFilter" value='<%=request.getParameter("isbnFilter")%>' /> 
<c:set var="printNoFilter" value='<%=request.getParameter("printNoFilter")%>' /> 
<c:set var="statusFilter" value='<%=request.getParameter("statusFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' />  
<c:set var="sbBDateFilter" value='<%=request.getParameter("sbBDateFilter")%>' /> 
<c:set var="ebBDateFilter" value='<%=request.getParameter("ebBDateFilter")%>' /> 
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>' /> 
<c:set var="msgId" value='<%=request.getParameter("MSG_ID")%>' />

<script>
var httpRequest;
function getFileDownload(lineno,pono,id){
       var filename=document.getElementById('ajaxCall').value;
        if(filename.length!=0){
        var path= '<%=request.getContextPath()%>' ;
		var url = path+'/AjaxAction.do?lineno='+lineno+'&pono='+pono+'&filename='+filename; 
		document.getElementById('ajaxCall').value='' ;
		if (window.ActiveXObject){ 
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
		
		}else if (window.XMLHttpRequest){ 
		httpRequest = new XMLHttpRequest(); 
	    } 
	
		httpRequest.open("GET", url, true); 
		httpRequest.onreadystatechange = function() {
		 getFileUpload(id,lineno,pono);
		} ; 
		httpRequest.send(null); 
       }
    
    }
    
     function getFileUpload(id,lineno,pono){
    	if (httpRequest.readyState == 4) { 
			if(httpRequest.status == 200) { 
			xml = httpRequest.responseXML;
			 file_upload = xml.getElementsByTagName("file-upload")[0];
			 var fileUpload=false ;
		if(file_upload!=null&&file_upload.firstChild!=null){
			fileUpload=file_upload.firstChild.data;
         }
         
         funcPassValueNew2(fileUpload,id,lineno,pono);
		}else { 
			alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
		}
	 }
    
    
    function funcPassValueNew2(flag,id,lineno,pono){
             
            if(flag!=true){ 
            	alert('File is not uploaded correctly.');
            	}
            }
 
 
</script>
<!--<input type="hidden" name="stat" id="stat" value="${deliveryMessageForm.stat}"/>-->

<c:set var="headerOrLineCollection" />
   <c:choose>
   <c:when test="${roleType == 'M' || orderPaper == 'O'}">
   <c:set var="poLineCheck"/>
   <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">
     <c:if test="${indexId.index==0}">
    <c:set var="headerOrLineCollection" value="${deliveryMessageLine.linePartyCollection}"/>
    </c:if>
   </c:forEach>
	  </c:when>
     <c:otherwise> 
	 <c:set var="headerOrLineCollection" value="${deliveryMessageForm.deliveryMessage.partyCollection}"/>
	 </c:otherwise>
  </c:choose>	

<html-el:form action="/deliverymessage/deliverymessagenew"> 
<%@ include file="/common/formbegin.jsp"%>
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  ${deliveryMessageForm.deliveryMessage.poNo}
    <c:if test="${deliveryMessageForm.deliveryMessage.releaseNo!=null && deliveryMessageForm.deliveryMessage.releaseNo!='0'}">
    - ${deliveryMessageForm.deliveryMessage.releaseNo}
    </c:if> </span></td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.DELMSG_CODE)&& "P".equals(request.getParameter("order")))
{
%>
<tr>
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Delivery Message  No. <c:out value="${deliveryMessageForm.deliveryMessage.msgNo}"/></span>. To go back to the delivery message listing, click on the <span class="textBlue">close</span> button. To create a new delivery message click on the <span class="textBlue">new delivery message </span> button. </td>
      </tr>
<%		}else{%>
       <tr>
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Delivery Message  No. <c:out value="${deliveryMessageForm.deliveryMessage.msgNo}"/></span>. To go back to the delivery message listing, click on the <span class="textBlue">close</span> button.</td>
      </tr>
<%		}%>    
      
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
          <c:set var="pageModule" value="deliverymessage" scope="request"></c:set>
          <c:set var="orderPaper" value="${orderPaper}" scope="request"></c:set>
          <%@ include file="/common/mod_tabs.jsp"%>
        </table></td>
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine">
        <img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
    </table>
      <table width="60%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="5" valign="bottom" class="headingMain">&nbsp;</td>
        </tr>
        <tr>
          <td height="20" valign="bottom" class="headingMain">
          <table width="100%" border="0" cellspacing="1" cellpadding="0">
            <tr>
              <td width="35%" class="blueColumn">MESSAGE TYPE: </td>
              <td class="lightblueColumn"><c:out value="${deliveryMessageForm.deliveryMessage.msgTypeDetail}"/></td>
            </tr>
          </table></td>
        </tr>
        <tr>
        <c:forEach var="party" items="${headerOrLineCollection}" varStatus="indexId">     
          <td><fieldset class="legendBorder">
          <legend class="legendeTitle">SHIP TO (${party.san})
          <c:if test="${party.orgUnitCode!=null}">(${party.orgUnitCode})</c:if> </legend>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td height="5"></td>
			  </tr>
			<tr>
              <td class="headingText12"><c:out value="${party.name1}"/></td>
            </tr>
            <tr>
              <td class="textLegend"><c:out value="${party.address1}"/><br>                                         
                <c:out value="${party.city}"/> <c:out value="${party.postalCode}"/> <c:out value="${party.state}"/> <c:out value="${party.countryDetail.countryName}"/></td>
            </tr>
             
            <tr>
              <td height="5" class="textLegend"></td>
            </tr>
            <tr>
             <c:set var="contactCollectionValue"/>
             <c:choose>
            <c:when test="${roleType == 'M' || orderPaper == 'O'}">
         <c:set var="contactCollectionValue" value="${party.linePartyContactCollection}"/>
	     </c:when>
     <c:otherwise> 
	  <c:set var="contactCollectionValue" value="${party.contactCollection}"/>
	 </c:otherwise>
  </c:choose>	
        
			<c:forEach var="contact" items="${contactCollectionValue}" varStatus="indexId">
							
              <td class="textLegend"><span class="textLegendBold"> -</span> <c:out value="${contact.contactFirstName}"/><br>
				<c:if test="${contact.phone!=null}">&nbsp;${contact.phone} (Business)</c:if><br>
				<c:if test="${contact.mobile!=null}">&nbsp;${contact.mobile} (Mobile)</c:if><br>
				<c:if test="${contact.fax!=null}">&nbsp;${contact.fax} (Fax)</c:if><br>
				<c:if test="${contact.email!=null}">&nbsp;${contact.email} </c:if><br>
			</td>
		  </c:forEach>
		
            </tr>
            </c:forEach>
          </table></fieldset></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
       
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
       <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
        <c:if test="${errorMsg == null}"> 
        <tr>
          <td width="2%" class="tableHeading">PO LINE NO. </td>
           <c:choose>
   				<c:when test="${roleType == 'M' || orderPaper == 'O'}">
   					<td class="tableHeading">MATERIAL NUMBER </td>
          			<td class="tableHeading">MATERIAL DESCRIPTION </td>
          		</c:when>
          		<c:otherwise>
          			<td class="tableHeading">COMPONENT </td>
          		</c:otherwise>
          	</c:choose>
          <td class="tableHeading" width="10%">ORIGINAL QUANTITY</td>
          <c:if test="${roleType == 'M' || orderPaper == 'O'}">
          	<td class="tableHeading" width="10%">TOTAL DELIVERED</td>
          </c:if>
          <td class="tableHeading">QUANTITY</td>
          <td class="tableHeading">REQUESTED DELIVERY DATE </td>
          <td class="tableHeading">SHIP DATE </td>
          <c:if test="${roleType == 'M' || orderPaper == 'O'}">
          	<td class="tableHeading">BILL OF LADING </td>
          </c:if>
        </tr>
        
        <c:set var="deliveryMessageDetailPrev" value="" />
        <c:set var="class1" value="tableRow"/>
        <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">
        <c:set var="lineNo" value="${deliveryMessageLine.lineNo}"/>
         <c:if test="${lineNo%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         </c:if>
         <c:if test="${lineNo%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
         </c:if>
         <tr>
         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineNo}"/></td>
         <c:choose>
   				<c:when test="${roleType == 'M' || orderPaper == 'O'}">
   					<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productCode}"/></td>
         			<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineDecription}"/></td>
         		</c:when>
         		<c:otherwise>
         			<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productDescription}"/></td>	
         		</c:otherwise>
         </c:choose>
         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.balanceQuantity}"></fmt:formatNumber> </td>
         
         <c:if test="${roleType == 'M' || orderPaper == 'O'}">
         	<td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.postedQuantity}"></fmt:formatNumber> </td>	
         </c:if>
         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.deliveredQuantity}"></fmt:formatNumber> </td>
         
         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.requestedDeliveryDate}"/></td>
         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.estimatedDeliveryDate}"  /></td>
         <c:if test="${roleType == 'M' || orderPaper == 'O'}">
         	
         	<c:choose>
         		<c:when test="${deliveryMessageLine.fileExists!='0'}">
         		 <fileUploading:DMFileSubFolder id="subfolderName" msgId="${msgId}" lineNo="${deliveryMessageLine.lineNo}"/>
         			<td valign="top" class="${class1}"><a href="#" id="upload${indexId.index}"  onClick="MM_openBrWindow('<%=request.getContextPath()%>/bolfilelist/downloadfile.do?lineNo=${deliveryMessageLine.lineNo}&msgId=${msgId}&flag=download&pono=${pono}&poversion=${poversion}&index=${indexId.index}&subfolderName=${subfolderName}&approvalFlag=${deliveryMessageLine.approvalFlag}','DownLoadFile','scrollbars=yes,width=800,height=410,top=10')" class="subLinksMain"><c:out value="download files"/></a></td>
         		</c:when>
         		<c:otherwise>
         			<td valign="top" class="${class1}">&nbsp;</td>
         		</c:otherwise>
         	</c:choose>
         	
         </c:if>
         </tr>
         </c:forEach>
          </c:if> 
         <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" height="30px" valign="middle" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if>
         
          <tr>
          <c:choose>
          <c:when test="${roleType == 'M' || orderPaper == 'O'}">
          	<td height="1" colspan="9" class="tableLine"/>
          </c:when>
          <c:otherwise>
          	<td height="1" colspan="6" class="tableLine"/>
          </c:otherwise>
          </c:choose>
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <div id="buttons2" class="tabSelectText"> </div>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
         <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons3" class="tabSelectTextleft">
          <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
          <input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
          <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
          <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
          <input type="hidden" name="statusFilter" value="${statusFilter}"/>
          <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
          <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
          <input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
         <input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
<%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.DELMSG_CODE)&& "P".equals(request.getParameter("order"))){
%> 
           <c:choose>
               <c:when test="${roleType == 'M'}">
	             <input name="Button1" type="button" class="buttonMain" onClick="submitDeliveryMsgAction('<%=request.getContextPath()%>/deliverymessage/milldeliverymessagenew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}',this,'${deliveryMessageForm.stat}')" value="New Delivery Message">
	           </c:when>
               <c:otherwise> 
	          <input name="Button1" type="button" class="buttonMain" onClick="submitDeliveryMsgAction('<%=request.getContextPath()%>/deliverymessage/deliverymessagenew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}',this,'${deliveryMessageForm.stat}')" value="New Delivery Message">
	         </c:otherwise>
		   </c:choose>	
     
           
<%}%>             
   <%	 if("H".equals(request.getParameter("home"))){%>
 
           <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/deliverymessage/inboxdeliverylist.do?PAGE_VALUE=1&days=5')" value="Close">
  
    <%		}else{%>
        <input name="page_order_list" type="hidden" value="${page_order_list}">
        <c:choose>
        	<c:when test="${orderPaper=='O'}">
        		<input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/deliverymessage/deliverymessagelist.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${PAGE_VALUE}&orderPaper=${orderPaper}&fo=paperfo')" value="Close">
        	</c:when>
        	<c:otherwise>
        		<input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/deliverymessage/deliverymessagelist.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${PAGE_VALUE}')" value="Close">
        	</c:otherwise>
        </c:choose>
    
    <%		}%>      
           <input name="Button2" type="button" class="buttonMain" onClick="submitDelmsgAction2('<%=request.getContextPath()%>/pdf/deliverymessagepdf.do',this)" value="Export PDF">
          </td>
          </tr>
            </table>
           
           </label>
            
          </td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
        
     </table></td>
      <input type="hidden"  id="ajaxCall"/>
     </html-el:form>
  </tr>
