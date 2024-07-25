<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>

<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="sanFilter" value='<%=request.getParameter("sanFilter")%>' /> 
<c:set var="nameFilter" value='<%=request.getParameter("nameFilter")%>' /> 
<c:set var="statusFilter" value='<%=request.getParameter("statusFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' /> 
  
  <c:set var="san_no" value='<%=request.getParameter("SAN_VALUE")%>' /> 
  <tr>
    <td height="25" align="left" valign="top">
    <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    <c:choose>
    	<c:when test="${san_no == null}">
    		<span class="headingText">Add Pub Unit </span>
    	</c:when>
    	<c:otherwise>
    		<span class="headingText">Edit Pub Unit - ${san_no}</span>
    	</c:otherwise>
    </c:choose>
    </td>
  </tr>
  
  <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
  
  <tr>
    <td align="left" valign="top" class="padding23">
    
      <c:set var="san_no" value='<%=request.getParameter("SAN_VALUE")%>' />
      
        
      <html-el:form action="/admin/updatepubunit">
      <%@ include file="/common/formbegin.jsp"%>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <c:choose>
        	<c:when test="${san_no == null}">
        		<tr>
          			<td height="15" colspan="2" class="text">To <span class="textBold">Add a  Pub Unit Info </span> details, fill in the following fields.  Fields marked with <span class="mandatory">*</span> are mandatory. </td>
        		</tr>
      		</c:when>
      		<c:otherwise>
        		<tr>
          			<td height="15" colspan="2" class="text">To <span class="textBold">Edit the Pub Unit Info </span> details, fill in the following fields.  Fields marked with <span class="mandatory">*</span> are mandatory. </td>
        		</tr>
      		</c:otherwise>
      	</c:choose>
      
        <tr>
          <td height="15" colspan="2"></td>
        </tr>
        
        <tr><td>
    		<table cellpadding="0" cellspacing="0">
    			<tr>
    				<td id="error_div" class="errorMessageText"></td>
    			</tr>
    		</table>
    	</td></tr>
    	
    	 <tr><td>
    	 	<c:if test="${errorMsg != null}">
          		<tr> 
          			<td class="errorMessageText"><c:out value="${errorMsg}"/></td> 
          		</tr>
      		</c:if>
    	 </td></tr>
        
        <tr>
             <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
              
              <tr>
              	<td width="11" height="22" align="right" valign="bottom">
                <img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
                <td width="90" align="center" class="tabSubBg">Info</td>
                <td width="12" height="22" align="left" valign="bottom">
                <img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
                
              </tr>
              
          </table></td>
          <td align="right" valign="bottom">&nbsp;</td>
        </tr>
        <tr>
          <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
    
      
      <table width="98%" border="0" cellpadding="0" cellspacing="1">
             
       	<tr>
        	<c:choose>
        		<c:when test="${san_no == null}">
       				<td width="25%" class="blueColumn">PUBLISHER SAN NUMBER:<span class="mandatory">*</span> </td>
          			<td width="25%" align="left" class="lightblueColumn"><html-el:text property="party.san" size="27" maxlength="60" styleClass="textfield"/></td>
          		</c:when>
          		<c:otherwise>
          			<td width="25%" class="blueColumn">PUBLISHER SAN NUMBER:<span class="mandatory">*</span> </td>
          			<td width="25%" align="left" class="lightblueColumn"><html-el:text property="party.san" size="27" maxlength="60" value="${san_no}" readonly="true" styleClass="textfield"/></td>
          		</c:otherwise>
          	</c:choose>
          <td width="25%" align="left" class="blueColumn">ADDRESS LINE 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.address1" size="40" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">PUBLISHER NAME:<span class="mandatory">*</span> </td>
          <td align="left" class="lightblueColumn">
          	<html-el:text property="party.name1" size="38" maxlength="60" styleClass="textfield"/>
          
          </td>

          <td align="left" class="blueColumn">ADDRESS LINE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.address2" size="40" maxlength="60" styleClass="textfield"/></td>

        </tr>
        <tr>
          <td class="blueColumn">PUBLISHER STATUS:<span class="mandatory">*</span> </td>
          <td align="left" class="lightblueColumn"><html-el:select property="party.activeFlag" styleClass= "textfield">
           <html-el:option value="">Select</html-el:option>
             <html-el:option value="Y">Active</html-el:option>
             <html-el:option value="N">Disabled</html-el:option>
          </html-el:select></td>

          <td align="left" class="blueColumn">ADDRESS LINE 3: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.address3" size="27" maxlength="60" styleClass="textfield"/></td>

        </tr>
        <tr>
          <td class="blueColumn">CONTACT FIRST NAME: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.contactFirstName" size="27" maxlength="60" styleClass="textfield"/></td>

          <td align="left" class="blueColumn">ADDRESS LINE 4: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.address4" size="27" maxlength="60" styleClass="textfield"/></td>

        </tr>
        <tr>
          <td class="blueColumn">CONTACT LAST NAME:</td>
          <td align="left" class="lightblueColumn"> <html-el:text property="party.contactLastName" size="27" maxlength="60" styleClass="textfield"/></td>

          <td align="left" class="blueColumn">STATE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.state" size="27" maxlength="20" styleClass="textfield"/></td>

        </tr>
        <tr>
          <td class="blueColumn">BUSINESS PHONE 1: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.phone1" size="27" maxlength="25" styleClass="textfield"/></td>

		  <td align="left" class="blueColumn">ZIP:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.postalCode" size="27" maxlength="20" styleClass="textfield"/></td>          

        </tr>
        <tr>
          <td class="blueColumn">BUSINESS PHONE 2:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.phone2" size="27" maxlength="25" styleClass="textfield"/></td>

          <td align="left" class="blueColumn">CITY:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.city" size="27" maxlength="45" styleClass="textfield"/></td>	
        </tr>
        <tr>
          <td class="blueColumn">FAX 1: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.fax1" size="27" maxlength="25" styleClass="textfield"/></td>

          <td align="left" class="blueColumn">COUNTRY:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          
          <html-el:select property="party.countryDetail.countryCode" styleClass= "textfield" >
			<html-el:option value="">Select</html-el:option>
			<c:forEach var="item" items="${pubList}" varStatus="indexId">
				<html-el:option value="${item.countryCode}"><c:out value="${item.countryName}"/></html-el:option>
			</c:forEach>
	  	  </html-el:select> 
          
           
           
          </td>
        </tr>
        <tr>
          <td class="blueColumn">FAX 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.fax2" size="27" maxlength="25" styleClass="textfield"/></td>

          <td align="left" class="blueColumn">EMAIL:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.email" size="45" maxlength="50" styleClass="textfield"/></td>

        </tr>
        <tr>
          <td class="blueColumn">MOBILE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.mobile" size="27" maxlength="25" styleClass="textfield"/></td>

          <td align="left" class="blueColumn">WEBSITE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.website" size="27" maxlength="50" styleClass="textfield"/></td>

        </tr>
        	
        <tr>
          <td height="1" colspan="7" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
      </table>
      
      
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
     
        <tr>
          <td height="15" align="right" class="Linkmore">&nbsp;</td>
        </tr>
        <tr>
          <td>
          
          <label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          	<input name="sanFilter" type="hidden" value="${sanFilter}">
			<input name="nameFilter" type="hidden" value="${nameFilter}">
			<input name="statusFilter" type="hidden" value="${statusFilter}">
			<input name="startDateFilter" type="hidden" value="${startDateFilter}">
			<input name="endDateFilter" type="hidden" value="${endDateFilter}">
            <c:choose>
            	<c:when test="${san_no == null}">
   					<input name="Button1" type="button" class="buttonMain" onClick="if(validatePublisher()){submitActionPublisher('<%=request.getContextPath()%>/admin/insertpubunit.do?PAGE_VALUE=${PAGE_VALUE}')}else{return false;}" value="Save">
   					<input name="PubEditCancel" type="hidden" value="PubEditCancel"/>
   					<input name="Button2" type="button" class="buttonMain" onClick="submitPublisherCancelAction('<%=request.getContextPath()%>/admin/listpub.do?PAGE_VALUE=${PAGE_VALUE}')" value="Close">
   					
   				</c:when>
            	<c:otherwise>
   					<input name="Button3" type="button" class="buttonMain" onClick="if(validatePublisher()){submitActionPublisher('<%=request.getContextPath()%>/admin/updatepubunit.do?SAN_VALUE=${san_no}&PAGE_VALUE=${PAGE_VALUE}')}else{return false;}" value="Update">
   					<input name="PubEditCancel" type="hidden" value="PubEditCancel"/>
   					<input name="Button4" type="button" class="buttonMain" onClick="submitPublisherCancelAction('<%=request.getContextPath()%>/admin/listpub.do?PAGE_VALUE=${PAGE_VALUE}')" value="Close">
            	</c:otherwise>
            </c:choose>
            </td>
            </tr>
            </table>
           
          </label>
          </td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
       <input name="ADMIN_MODULE" type="hidden" value="PUB">
    </html-el:form>
  
    </table></td>
    
  </tr>
  
  
 