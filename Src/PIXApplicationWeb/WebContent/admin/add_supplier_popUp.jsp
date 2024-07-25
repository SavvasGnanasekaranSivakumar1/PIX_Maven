


<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="login" value='<%=request.getParameter("LOGIN")%>' /> 
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" >
$(document).ready(function() {
                checkbox1()
});

function checkbox1(){

var acttype = window.opener.document.getElementById('accounttype1').value;


	if ( acttype == 'V' || acttype == 'M'){
	
	document.getElementById('checkboxall').innerHTML="";

	}
	

}

</script>

 
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    <span class="headingText">Add Supplier </span></td>
  </tr>
  <% String errorMsg=(String)request.getAttribute(PIXConstants.PIX_ERROR); %>
  
  <tr>
    <td align="left" valign="top" class="padding23">
    
    <html-el:form action="/admin/insertuser">
      <%@ include file="/common/formbegin.jsp"%>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      	
      
        <tr>
          <td height="15" colspan="2" class="text">To <span class="textBold">Add Suppliers </span> fill in the checkboxes and click on Add Supplier.</td>
        </tr>
        <tr>
          <td height="15" colspan="2"></td>
        </tr>

		 <tr>
          <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
       
      </table>      
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
      	
      	<c:forEach var="supplierList" items="${supplierForm.supplierListCollection}" varStatus="indexId">  
      		<c:if test="${supplierList.activeFlag=='Y'}">
      			<c:set var="countLabel" value="0"/>
      		</c:if>
      	</c:forEach>
      	<c:choose>	
      		<c:when test="${supplierForm.supplierListCollection == '[]' || countLabel != 0}">
      			<tr>
      			<c:out value="${countLabel}"/>
      			<td align="center" valign="middle" height="30px" class="noRecordsMessage">
					All suppliers are associated to the user.
				</td></tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td width="3%" class="tableHeading" id="checkboxall">
          				<input name="checkall" id="check" type="checkbox" value="checkbox" onclick="checkUncheckAll(this);">
          			</td>
          			<td class="tableHeading">SUPPLIER NAME </td>
          			<td class="tableHeading">SAN</td>
          			<td class="tableHeading">ACCOUNT TYPE</td>
          		</tr>
       	   </c:otherwise>
       </c:choose>
      
      		<c:forEach var="supplierList" items="${supplierForm.supplierListCollection}" varStatus="indexId">  
      		<c:if test="${supplierList.activeFlag=='Y'}">
        	<tr>
        			<c:if test="${indexId.count%2 != 0}" >
	         			<c:set var="class1" value="tableRow"/>
	         		</c:if>
	         		<c:if test="${indexId.count%2 == 0}" >
	         			<c:set var="class1" value="tableAlternateRow"/>
	         		</c:if> 
        		
        		
        		<td class="${class1}">
        			<html-el:checkbox  property="supplierId" value="${indexId.index}" />
          		</td>
          		<td class="${class1}"><c:out value="${supplierList.name1}"/></td>
          		<td class="${class1}"><c:out value="${supplierList.san}"/></td>	
          		<td class="${class1}">
          			<c:choose>
          				<c:when test="${supplierList.partyType=='V'}">
          					Supplier
          				</c:when>
          				<c:when test="${supplierList.partyType=='M'}">
          					Mill
          				</c:when>
          				<c:when test="${supplierList.partyType=='D'}">
          					Merchant
          				</c:when>
          			</c:choose>
          		</td>	
          		
          		
          	</tr> 
          	</c:if>
          	</c:forEach>
        	
                        
        <tr>
          <td height="1" colspan="4" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
        
      </table>
      
      <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          	
			<c:choose>
        		<c:when test="${login == null}">
        			<c:if test="${countLabel == 0}">
        				<input name="Button" type="button" class="buttonMain" onClick="javascript:checkvalidate('<%=request.getContextPath()%>/admin/adduserinfo.do?PAGE_VALUE=1&ADMIN_MODULE=SUP&METHOD=USER_ADD&LOGIN=<c:out value='${login}'/>');" value="Add Supplier">
            		</c:if>
            		<input name="Button2" type="button" class="buttonMain" onClick="javascript:self.close();" value="Close">
            	</c:when>
            	<c:otherwise>
            		<c:if test="${countLabel == 0}">
            			<input name="Button" type="button" class="buttonMain" onClick="javascript:checkvalidate('<%=request.getContextPath()%>/admin/adduserinfo.do?PAGE_VALUE=1&ADMIN_MODULE=SUP&METHOD=USER_EDIT&LOGIN=<c:out value='${login}'/>');" value="Add Supplier">
            		</c:if>
            		<input name="Button2" type="button" class="buttonMain" onClick="javascript:self.close();" value="Close">
            	</c:otherwise>
            </c:choose>
            </td>
            </tr>
            </table>
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
    </table>
     </html-el:form>
    
     </td>
  </tr>

  
  