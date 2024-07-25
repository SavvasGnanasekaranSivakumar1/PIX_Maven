
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>

<c:set var="san_no" value='<%=request.getParameter("SAN_VALUE_SUPPLIER")%>' /> 
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="sanFilter" value='<%=request.getParameter("sanFilter")%>' /> 
<c:set var="nameFilter" value='<%=request.getParameter("nameFilter")%>' /> 
<c:set var="statusFilter" value='<%=request.getParameter("statusFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' /> 
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    <c:choose>
    	<c:when test="${san_no == null}">
    		<span class="headingText">Add Supplier </span>
    	</c:when>
    	<c:otherwise>
    		<span class="headingText">Edit Supplier - ${san_no}</span>
    	</c:otherwise>
    </c:choose>
    </td>
</tr>
  
  <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
  
  <tr>
    <td align="left" valign="top" class="padding23">
    <html-el:form action="/admin/insertsupplier">
      <%@ include file="/common/formbegin.jsp"%>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      	<c:choose>
        	<c:when test="${san_no == null}">
        		<tr>
          			<td height="15" colspan="2" class="text"><div id="txtTitle">To <span class="textBold">Add a  Supplier Info </span> details, fill in the following fields.  Fields marked with <span class="mandatory">*</span> are mandatory. </div></td>
        		</tr>
      		</c:when>
      		<c:otherwise>
        		<tr>
          			<td height="15" colspan="2" class="text"><div id="txtTitle">To <span class="textBold">Edit the Supplier Info </span> details, fill in the following fields.  Fields marked with <span class="mandatory">*</span> are mandatory. </div></td>
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
    	</td>
    	</tr>
       	
       	<tr>
          <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
              <tr>
			  <td>
			  
			  <table border="0" cellpadding="0" cellspacing="0">
			  	  <tr>
          <td width="100%" height="22">
		  <div id="tabLinks">
				<div id="tab1" class="selected1"><a href="javascript:showDiv(1)" >Info</a></div>
				<div id="tab2"><a href="javascript:showDiv(2)" >Transport Details</a></div>
									
		  </div>
		  </td>
          <td align="right" valign="bottom">&nbsp;</td>
        </tr>
              </table>
			 			  </td>               
			  </tr>
          </table></td>
          <td align="right" valign="bottom">&nbsp;</td>
        </tr>
        <tr>
          <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      
      	
      	
	  <div id="table1" style="display:inline">
	  
      <table width="98%" border="0" cellpadding="0" cellspacing="1">
      	      	
        <tr>
        	<c:choose>
        		<c:when test="${san_no == null}">
       				<td width="25%" class="blueColumn">SUPPLIER SAN NUMBER:<span class="mandatory">*</span> </td>
          			<td width="25%" align="left" class="lightblueColumn"><html-el:text property="party.san" size="27" maxlength="60" styleClass="textfield"/></td>
          		</c:when>
          		<c:otherwise>
          			<td width="25%" class="blueColumn">SUPPLIER SAN NUMBER:<span class="mandatory">*</span> </td>
          			<td width="25%" align="left" class="lightblueColumn"><html-el:text property="party.san" size="27" maxlength="60" value="${san_no}" readonly="true" styleClass="textfield"/></td>
          		</c:otherwise>
          	</c:choose>
          <td width="25%" align="left" class="blueColumn">ADDRESS LINE 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.address1" size="40" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">SUPPLIER NAME:<span class="mandatory">*</span> </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.name1" size="38" maxlength="60" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">ADDRESS LINE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="party.address2" size="40" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">SUPPLIER STATUS:<span class="mandatory">*</span> </td>
          <td align="left" class="lightblueColumn"><html-el:select property="party.activeFlag" styleClass="textfield">
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
          <td align="left" class="lightblueColumn"><html-el:text property="party.contactLastName" size="27" maxlength="60" styleClass="textfield"/></td>
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
          <c:choose>
        		<c:when test="${san_no == null}">
          			<td align="left" class="blueColumn">COUNTRY:<span class="mandatory">*</span></td>
          			<td align="left" class="lightblueColumn">
          			<html-el:select property="party.countryDetail.countryCode" styleClass= "textfield" >
						<html-el:option value="">Select</html-el:option>
						<c:forEach var="item" items="${supplierListCountry}" varStatus="indexId">
							<html-el:option value="${item.countryCode}"><c:out value="${item.countryName}"/></html-el:option>
						</c:forEach>
	  				</html-el:select>
	  	  			</td>
	  	  		</c:when>
	  	  		<c:otherwise>
	  	  			<td align="left" class="blueColumn">COUNTRY:<span class="mandatory">*</span></td>
          			<td align="left" class="lightblueColumn">
          			<html-el:select property="party.countryDetail.countryCode" styleClass= "textfield">
						<html-el:option value="">Select</html-el:option>
						
						<c:forEach var="item" items="${supplierListCountry}" varStatus="indexId">
							
							<html-el:option value="${item.countryCode}"><c:out value="${item.countryName}"/></html-el:option>
						</c:forEach>
					</html-el:select> 
				</c:otherwise>
			</c:choose>
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
          <td class="blueColumn">SUPPLIER TYPE:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	<html-el:select property="party.partyType" styleClass= "textfield">
				<html-el:option value="">Select</html-el:option>
				<c:forEach var="item" items="${supplierTypes}" varStatus="indexId">
					<html-el:option value="${item.refCode}"><c:out value="${item.description}"/></html-el:option>
				</c:forEach>
			</html-el:select> 
          </td>
          <td align="left" class="blueColumn">&nbsp;</td>
          <td align="left" class="lightblueColumn">&nbsp;</td>
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
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          	<input name="sanFilter" type="hidden" value="<c:out value='${sanFilter}'/>">
			<input name="nameFilter" type="hidden" value="<c:out value='${nameFilter}'/>">
			<input name="statusFilter" type="hidden" value="<c:out value='${statusFilter}'/>">
			<input name="startDateFilter" type="hidden" value="<c:out value='${startDateFilter}'/>">
			<input name="endDateFilter" type="hidden" value="<c:out value='${endDateFilter}'/>">
            <c:choose>
          	<c:when test="${san_no == null}">
          		<input name="Button1" type="button" class="buttonMain" onClick="if(validateSupplier()){submitActionForSupplier('<%=request.getContextPath()%>/admin/insertsupplier.do?PAGE_VALUE=${PAGE_VALUE}')}else{return false;}" value="Save">
          		<input name="SupplierEditCancel" type="hidden" value="SupplierEditCancel"/>
            	<input name="Button2" type="button" class="buttonMain" onClick="submitSupplierCancelAction('<%=request.getContextPath()%>/admin/listsup.do?PAGE_VALUE=${PAGE_VALUE}')" value="Close">
            </c:when>
            <c:otherwise>
            	<c:choose>
    				<c:when test="${supplierForm.party.activeFlag == 'Y'}">
            			<input name="Button3" type="button" class="buttonMain" onClick="if(validateSupplier()){submitActionForSupplier('<%=request.getContextPath()%>/admin/updatesupplier.do?SAN_VALUE=<c:out value='${san_no}'/>&PAGE_VALUE=<c:out value='${PAGE_VALUE}'/>')}else{return false;}" value="Update">
            		</c:when>
            		<c:otherwise>
            				<input name="Button3" type="button" class="buttonMain" onClick="if(validateSupplier()){submitActionSupplier('<%=request.getContextPath()%>/admin/updatesupplier.do?SAN_VALUE=<c:out value='${san_no}'/>&PAGE_VALUE=<c:out value='${PAGE_VALUE}'/>}else{return false;}" value="Update">
            		</c:otherwise>
            	</c:choose>
            	<%--<input name="FlagSupplierEdit" type="hidden" value="FlagSupplierEdit"/>--%>
            	<input name="SupplierEditCancel" type="hidden" value="SupplierEditCancel"/>
            	<input name="Button4" type="button" class="buttonMain" onClick="submitSupplierCancelAction('<%=request.getContextPath()%>/admin/listsup.do?PAGE_VALUE=<c:out value='${PAGE_VALUE}'/>')" value="Close">
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
    </div>
    
    <div id="table2" style="display:none">
    <table width="98%" border="0" cellpadding="0" cellspacing="1">
    <c:choose>
    	<c:when test="${supplierForm.party.activeFlag == 'Y' || san_no == null}">
        <tr>
          
          		<td width="25%" class="blueColumn">ACCESS METHOD: <span class="mandatory">*</span></td>
          		<td align="left" class="lightblueColumn">
          			<html-el:select styleId="access" property="party.transportDetail.accessMethodDetail.refId"  styleClass= "textfield">
          				<html-el:option value="">Select</html-el:option>
							<c:forEach var="item" items="${supplierListAccess}" varStatus="indexId">
								<html-el:option value="${item.refId}">
									<c:out value="${item.description}"/>
								</html-el:option>
							</c:forEach>
					</html-el:select>
		  		</td>
		  	
	  		</tr>
        
        <tr>
          <td class="blueColumn">FTP SERVER NAME/IP: </td>
          <td align="left" class="lightblueColumn"><html-el:text styleId="serverName" property="party.transportDetail.serverName" size="27" maxlength="50" styleClass="textfield"/></td>
        </tr>
        
        <tr>
          <td class="blueColumn">FTP USER: </td>
          <td align="left" class="lightblueColumn"><html-el:text styleId="login" property="party.transportDetail.login" size="27" maxlength="20" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">FTP PASSWORD: </td>
          <td align="left" class="lightblueColumn"><html-el:text styleId="password" property="party.transportDetail.password" size="27" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">FOLDER (FTP GET): </td>
          <td align="left" class="lightblueColumn"><html-el:text styleId="folder" property="party.transportDetail.folder" size="27" maxlength="50" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">FOLDER (FTP PUT): </td>
          <td align="left" class="lightblueColumn"><html-el:text styleId="putFolder" property="party.transportDetail.putFolder" size="27" maxlength="50" styleClass="textfield"/></td>
        </tr>
        </c:when>
        
        <c:otherwise>
        	<tr>
          
          		<td width="25%" class="blueColumn">ACCESS METHOD: <span class="mandatory">*</span></td>
          		<td align="left" class="lightblueColumn">
          			<%--<html-el:select styleId="access" property="party.transportDetail.accessMethodDetail.refId"  styleClass= "textfield">
          				<html-el:option value="">Select</html-el:option>
							<c:forEach var="item" items="${supplierListAccess}" varStatus="indexId">
								<html-el:option value="${item.refId}">
									<c:out value="${item.description}"/>
								</html-el:option>
							</c:forEach>
					</html-el:select>--%>
					${supplierForm.party.transportDetail.accessMethodDetail.description}
					<html-el:hidden property="party.transportDetail.accessMethodDetail.refId" value="${supplierForm.party.transportDetail.accessMethodDetail.refId}"/>
					
		  		</td>
		  	
		  	</tr>
		  	<tr>
	          <td class="blueColumn">FTP SERVER NAME/IP: </td>
	          <td align="left" class="lightblueColumn">${supplierForm.party.transportDetail.serverName} </td>
	        </tr>
        
	        <tr>
	          <td class="blueColumn">FTP USER: </td>
	          <td align="left" class="lightblueColumn">${supplierForm.party.transportDetail.login}</td>
	        </tr>
	        <tr>
	          <td class="blueColumn">FTP PASSWORD: </td>
	          <td align="left" class="lightblueColumn">${supplierForm.party.transportDetail.password}</td>
	        </tr>
	        <tr>
	          <td class="blueColumn">FOLDER (FTP GET): </td>
	          <td align="left" class="lightblueColumn">${supplierForm.party.transportDetail.folder}</td>
	        </tr>
	        <tr>
	          <td class="blueColumn">FOLDER (FTP PUT): </td>
	          <td align="left" class="lightblueColumn">${supplierForm.party.transportDetail.putFolder}</td>
	        </tr>
        </c:otherwise>
        </c:choose>
        <tr>
          <td height="1" colspan="5" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
     </table>
     
	  <table width="98%" border="0" cellspacing="0" cellpadding="0">
	  
	  
        <tr>
          <td height="15" align="right" class="Linkmore">&nbsp;</td>
        </tr>
        <tr>
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          	<input name="sanFilter" type="hidden" value="<c:out value='${sanFilter}'/>">
		  	<input name="nameFilter" type="hidden" value="<c:out value='${nameFilter}'/>">
			<input name="statusFilter" type="hidden" value="<c:out value='${statusFilter}'/>">
			<input name="startDateFilter" type="hidden" value="<c:out value='${startDateFilter}'/>">
			<input name="endDateFilter" type="hidden" value="<c:out value='${endDateFilter}'/>">
          <c:choose>
          	<c:when test="${san_no == null}">
          		<input name="Button1" type="button" class="buttonMain" onClick="if(validateSupplier()){submitActionForSupplier('<%=request.getContextPath()%>/admin/insertsupplier.do?PAGE_VALUE=<c:out value='${PAGE_VALUE}'/>')}else{return false;}" value="Save">
          		<input name="SupplierEditCancel" type="hidden" value="SupplierEditCancel"/>
            	<input name="Button2" type="button" class="buttonMain" onClick="submitSupplierCancelAction('<%=request.getContextPath()%>/admin/listsup.do?PAGE_VALUE=<c:out value='${PAGE_VALUE}'/>')" value="Close">
            </c:when>
            <c:otherwise>
            	<c:if test="${supplierForm.party.activeFlag == 'Y' || san_no == null}">
            		<input name="Button3" type="button" class="buttonMain" onClick="if(validateSupplier()){submitActionForSupplier('<%=request.getContextPath()%>/admin/updatesupplier.do?SAN_VALUE=${san_no}&PAGE_VALUE=<c:out value='${PAGE_VALUE}'/>')}else{return false;}" value="Update">
            	</c:if>
            	<input name="SupplierEditCancel" type="hidden" value="SupplierEditCancel"/>
   				<input name="Button4" type="button" class="buttonMain" onClick="submitSupplierCancelAction('<%=request.getContextPath()%>/admin/listsup.do?PAGE_VALUE=<c:out value='${PAGE_VALUE}'/>')" value="Close">
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
    </div>
    <input name="ADMIN_MODULE" type="hidden" value="SUP">
    </html-el:form>
    
	</td>
  </tr>

