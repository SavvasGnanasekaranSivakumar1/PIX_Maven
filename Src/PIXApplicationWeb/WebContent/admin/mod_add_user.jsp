<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>

<%@ page import="com.pearson.pix.dto.admin.User"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>


<c:set var="login" value='<%=request.getParameter("LOGIN")%>' /> 
<c:set var="userName" value='<%=request.getParameter("USERNAME")%>'/>
<c:set var="flagforsupplier" value='<%=request.getParameter("FLAGSUPP")%>'/>
<c:set var="flagforpublisher" value='<%=request.getParameter("FLAGPUB")%>'/>
<c:set var="createdBy" value='<%=request.getParameter("CREATEDBY")%>'/>

<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="userFilter" value='<%=request.getParameter("userFilter")%>' /> 
<c:set var="accountStatusFilter" value='<%=request.getParameter("accountStatusFilter")%>' /> 
<c:set var="accountTypeFilter" value='<%=request.getParameter("accountTypeFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' /> 
<c:set var="oldPassword" value='<%=session.getAttribute("Password")%>'/>
	
<%
User objUser = (User)session.getAttribute("USER_INFO");
String loginUser = objUser.getFirstName();
String loginUserLastName = objUser.getLastName();
%>

<html-el:hidden styleId="PassWord" property="CurrentPassWord" value="${oldPassword}" />
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    <c:choose>
    	<c:when test="${login == null || login == ''}">
    	 	<span class="headingText">Add User</span>
    	</c:when>
    	<c:otherwise>
    	 	<span class="headingText">Edit User - ${userName}</span>
    	</c:otherwise>
    </c:choose>
    </td>
 </tr>
  
  <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
  
  <tr>
    <td align="left" valign="top" class="padding23">
    
    <html-el:form action="/admin/adduserinfo">
      <%@ include file="/common/formbegin.jsp"%>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
  			
  			<c:if test="${PasswordExist!=null}">
  				<font color="#CC0000" size="2" face="Verdana">
    			<span >${PasswordExist}</span>
  			</c:if>	
  		</tr>
  		<tr><td>
    		<table cellpadding="0" cellspacing="0">
    		<tr>
    		<td id="error_div" class="errorMessageText"></td>
    		</tr>
    		</table>
    	</td></tr>
      	<c:choose>
        	<c:when test="${login == null || login == ''}">
        		<tr>
          			<td height="15" colspan="2" class="text"><div id="txtTitle">To <span class="textBold">Add the User Info </span> details, fill in the following fields.  Fields marked with <span class="mandatory">*</span> are mandatory. </div></td>
        		</tr>
      		</c:when>
      		<c:otherwise>
        		<tr>
          			<td height="15" colspan="2" class="text"><div id="txtTitle">To <span class="textBold">Edit the User Info </span> details, fill in the following fields.  Fields marked with <span class="mandatory">*</span> are mandatory. </div></td>
        		</tr>
      		</c:otherwise>
      	</c:choose>
        
      
        
    	
    	<tr><td>
    		<c:if test="${errorMsg != null}">
          		<tr> 
          			<td class="errorMessageText"><c:out value="${errorMsg}"/></td> 
          		</tr>
      		</c:if>
    	</td></tr>
        
        <tr>
          <td height="22"><table width="536" height="22" border="0" cellpadding="0" cellspacing="0">
              <tr>
			  <td align="left">
			  <%--<div id="table11" style="display:inline; width:113px">--%>
			  <table width="100%" border="0" cellpadding="0" cellspacing="0">
			 <tr >
			 
			 <td height="5"></td>
			 </tr>	  	
			 <tr>
			 
			 
          <td width="100%" height="22">
		  <div id="tabLinks">
		  		<div id="tab1"><a href="javascript:showDiv(1)" >Info</a></div>
				<div id="tab2"><a href="javascript:showDiv(2)" >Associate Role</a></div>
				<div id="tab3"><a href="javascript:showDiv(3)">Associate Supplier</a></div>                     
				<div id="tab4"><a href="javascript:showDiv(4)">Associate PUB Unit</a></div>                                           
			
		  	</div>
		  </td>
          
        </tr>
             
			<c:set var="publisherTab" value='<%=request.getParameter("publisherIds")%>'/>
		<c:set var="supplierTab" value='<%=request.getParameter("supplierIds")%>'/>		             
			  
          </table></td>
          <td align="right" valign="bottom">&nbsp;</td>
        </tr>
        </table>
		
        <tr>
          <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
       
      </table>      
      <div id="table1" style="display:inline">
	  <table width="98%" border="0" cellpadding="0" cellspacing="1">
	      <tr>
           
            <c:choose>
        		<c:when test="${login == null || login == ''}">
					<td width="25%" class="blueColumn">SSOID:<span class="mandatory">*</span></td>
					<td align="left" class="lightblueColumn"><html-el:text property="user.ssoid" size="27" maxlength="100" styleClass="textfield"/></td>
          		</c:when>
          		<c:otherwise>
          			<td width="25%" class="blueColumn">SSOID:<span class="mandatory">*</span></td>
          			<td align="left" class="lightblueColumn">
          				<html-el:text property="user.ssoid" size="27" maxlength="100" styleClass="textfield"/>
          			</td>
          		</c:otherwise>
          	</c:choose>

          <c:choose>
        		<c:when test="${login == null || login == ''}">
       				<td width="25%" class="blueColumn">USER ID:<span class="mandatory">*</span> </td>
          			<td width="25%" align="left" class="lightblueColumn"><html-el:text property="user.login" size="27" maxlength="20" styleClass="textfield"/></td>
          		</c:when>
          		<c:otherwise>
          			<td width="25%" class="blueColumn">USER ID:<span class="mandatory">*</span> </td>
          			<td width="25%" align="left" class="lightblueColumn">
          				<html-el:text property="user.login" size="27" maxlength="20" readonly="true" styleClass="textfield"/>
          			</td>
          		</c:otherwise>
          	</c:choose>


        </tr>
        <tr>
          <td class="blueColumn">FIRST NAME: <span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.firstName" size="27" maxlength="60" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">ADDRESS LINE 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address1" size="40" maxlength="60" styleClass="textfield"/></td>
<%-- 
          <td align="left" class="blueColumn">ADDRESS LINE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address2" size="40" maxlength="60" styleClass="textfield"/></td>
--%>
        </tr>
        <tr>
          <td class="blueColumn">LAST NAME: <span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.lastName" size="27" maxlength="60" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">ADDRESS LINE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address2" size="40" maxlength="60" styleClass="textfield"/></td>
<%--           <td align="left" class="blueColumn">ADDRESS LINE 3:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address3" size="27" maxlength="60" styleClass="textfield"/></td>
--%>
        </tr>
        <tr>
          <td width="25%" class="blueColumn">ACCOUNT TYPE:<span class="mandatory">*</span></td>
          <td width="25%" align="left" class="lightblueColumn">
          <html-el:select property="user.roleTypeDetail.roleType" styleId="accounttype1" styleClass= "textfield" onchange="changeUserPrivilege(document.userForm.elements['user.roleTypeDetail.roleType'])">
		  	<html-el:option value="">Choose Account Type</html-el:option>
				<c:forEach var="item" items="${userList}" varStatus="indexId1">
					<c:if test="${item.description != 'SHIPTO'}">
						<html-el:option value="${item.roleType}"><c:out value="${item.description}"/></html-el:option>
				    </c:if>
				</c:forEach>
		  </html-el:select> </td>
           <td align="left" class="blueColumn">ADDRESS LINE 3:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address3" size="27" maxlength="60" styleClass="textfield"/></td>
<%-- 		  
          <td width="25%" align="left" class="blueColumn">ADDRESS LINE 4: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address4" size="27" maxlength="60" styleClass="textfield"/></td>
--%>
        </tr>
        <tr>
          <td class="blueColumn">ACCOUNT STATUS:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	<html-el:select property="user.activeFlag" styleClass="textfield">
            	<html-el:option value="">Choose Status</html-el:option>
                <html-el:option value="Y">Active</html-el:option>
                <html-el:option value="N">Disabled</html-el:option>
            </html-el:select>
          </td>
          <td width="25%" align="left" class="blueColumn">ADDRESS LINE 4: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address4" size="27" maxlength="60" styleClass="textfield"/></td>            
<%--             
          <td align="left" class="blueColumn">STATE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.state" size="27" maxlength="20" styleClass="textfield"/></td>
--%>        
        </tr>
        <tr>

<%--          <td class="blueColumn">PASSWORD:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          <c:choose>
        	<c:when test="${login == null || login == ''}">
        		<html-el:password styleId="pass" property="user.password" maxlength="20" styleClass="textfield" size="27"/>
          	</c:when>
          	<c:otherwise>
          		<html-el:password styleId="pass" property="user.password" onkeyup="passwordClickAdmin()" maxlength="20" styleClass="textfield" size="27"/>
          	</c:otherwise>
          </c:choose>	
          		<html-el:hidden styleId="passStore" property="existingPassword"/>
          		<html-el:hidden styleId="changePass" property="changePassword"/>
          	<script >
         function storePassValue(){
         var pass=document.getElementById('pass').value
         document.getElementById('passStore').value=pass;
          }
          storePassValue();
        </script>
          </td>
--%>
<td class="blueColumn">BUSINESS PHONE 1: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone1" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">STATE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.state" size="27" maxlength="20" styleClass="textfield"/></td>
<%-- 
          <td align="left" class="blueColumn">ZIP:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.postalCode" size="27" maxlength="20" styleClass="textfield"/></td>
--%>
        </tr>
        <tr>

<%--          <td class="blueColumn">PASSWORD EXPIRES:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn"><html-el:select property="user.passwordExpiry" styleClass="textfield">
          		<html-el:option value="">Select</html-el:option>
                <html-el:option value="Y">Yes</html-el:option>
                <html-el:option value="N">No</html-el:option>
          </html-el:select></td>
--%>          
<td class="blueColumn">BUSINESS PHONE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone2" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">ZIP:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.postalCode" size="27" maxlength="20" styleClass="textfield"/></td>
<%-- 
          <td align="left" class="blueColumn">CITY:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.city" size="27" maxlength="45" styleClass="textfield"/></td>
--%>          
        </tr>
        <tr>
<%--          <td class="blueColumn">BUSINESS PHONE 1: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone1" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
<td class="blueColumn">FAX 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax1" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">CITY:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.city" size="27" maxlength="45" styleClass="textfield"/></td>
<%--           
          <td align="left" class="blueColumn">COUNTRY:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	<html-el:select property="user.countryDetail.countryCode" styleClass= "textfield" >
		  		<html-el:option value="">Select</html-el:option>
					<c:forEach var="item" items="${userListCountry}" varStatus="indexId2">
						<html-el:option value="${item.countryCode}"><c:out value="${item.countryName}"/></html-el:option>
					</c:forEach>
	  		</html-el:select>
	  	  </td>
--%>	  	  
	  	  
        </tr>
        <tr>

<%--           <td class="blueColumn">BUSINESS PHONE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone2" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
<td class="blueColumn">FAX 2:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax2" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">COUNTRY:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	<html-el:select property="user.countryDetail.countryCode" styleClass= "textfield" >
		  		<html-el:option value="">Select</html-el:option>
					<c:forEach var="item" items="${userListCountry}" varStatus="indexId2">
						<html-el:option value="${item.countryCode}"><c:out value="${item.countryName}"/></html-el:option>
					</c:forEach>
	  		</html-el:select>
	  	  </td>
<%--           <td align="left" class="blueColumn">EMAIL:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.email" size="45" maxlength="50" styleClass="textfield"/></td>
--%>
        </tr>
        <tr>
<%--          <td class="blueColumn">FAX 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax1" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
<td class="blueColumn">EMAIL USER ON ACTIVITY: </td>
          <td align="left" class="lightblueColumn">
          <html-el:select property="user.emailActivityFlag" styleClass="textfield">
          		<html-el:option value="">Select</html-el:option>
                <html-el:option value="Y">Yes</html-el:option>
                <html-el:option value="N">No</html-el:option>
          </html-el:select></td>
		<td align="left" class="blueColumn">EMAIL:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.email" size="45" maxlength="100" styleClass="textfield"/></td>

<%-- 
          <td align="left" class="blueColumn">MOBILE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.mobile" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
        </tr>
        <tr>

<%--          <td class="blueColumn">FAX 2:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax2" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
<td class="blueColumn">WEBSITE: </td>
<td align="left" class="lightblueColumn"><html-el:text property="user.website" size="27" maxlength="50" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">MOBILE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.mobile" size="27" maxlength="25" styleClass="textfield"/></td>
<%--           <td align="left" class="blueColumn">WEBSITE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.website" size="27" maxlength="50" styleClass="textfield"/></td>
--%>
        </tr>
        <tr>
        
<%--                   <td class="blueColumn">WEBSITE: </td>
<td align="left" class="lightblueColumn"><html-el:text property="user.website" size="27" maxlength="50" styleClass="textfield"/></td>
--%>
<%-- 
<td class="blueColumn">EMAIL USER ON ACTIVITY: </td>
          <td colspan="3" align="left" class="lightblueColumn">
          <html-el:select property="user.emailActivityFlag" styleClass="textfield">
          		<html-el:option value="">Select</html-el:option>
                <html-el:option value="Y">Yes</html-el:option>
                <html-el:option value="N">No</html-el:option>
          </html-el:select></td>
   --%>     
<%--           <td class="blueColumn">EMAIL USER ON ACTIVITY: </td>
          <td colspan="3" align="left" class="lightblueColumn">
          <html-el:select property="user.emailActivityFlag" styleClass="textfield">
          		<html-el:option value="">Select</html-el:option>
                <html-el:option value="Y">Yes</html-el:option>
                <html-el:option value="N">No</html-el:option>
          </html-el:select></td>


<td align="left" class="blueColumn">WEBSITE:</td>
<td align="left" class="lightblueColumn"><html-el:text property="user.website" size="27" maxlength="50" styleClass="textfield"/></td>
--%>

<%--           
            <c:choose>
        		<c:when test="${login == null || login == ''}">
					<td width="25%" class="blueColumn">SSOID:<span class="mandatory">*</span></td>
					<td align="left" class="lightblueColumn"><html-el:text property="user.ssoid" size="27" maxlength="50" styleClass="textfield"/></td>
          		</c:when>
          		<c:otherwise>
          			<td width="25%" class="blueColumn">SSOID:<span class="mandatory">*</span></td>
          			<td align="left" class="lightblueColumn">
          				<html-el:text property="user.ssoid" size="27" maxlength="20" readonly="true" styleClass="textfield"/>
          			</td>
          		</c:otherwise>
          	</c:choose>
--%>
          
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
          		<input name="userFilter" type="hidden" value="${userFilter}">
				<input name="accountStatusFilter" type="hidden" value="${accountStatusFilter}">
				<input name="accountTypeFilter" type="hidden" value="${accountTypeFilter}">
				<input name="startDateFilter" type="hidden" value="${startDateFilter}">
				<input name="endDateFilter" type="hidden" value="${endDateFilter}">
          <c:choose>
          	<c:when test="${login == null || login == ''}">
          		<input name="Button" type="button" class="buttonMain" onClick="if(validateUser('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/insertuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')}else{return false;}" value="Save">
          		<input name="UserEditCancel" type="hidden" value="UserEditCancel"/>
            	<input name="Button2" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/admin/listuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')" value="Close">
             
            </c:when>
            <c:otherwise>
            	<input name="Button3" type="button" class="buttonMain" onClick="if(validateUser('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/updateuser.do?LOGIN=${login}&ADMIN_MODULE=USER&PAGE_VALUE=${PAGE_VALUE}')}else{return false;}" value="Update">
            	<input name="UserEditCancel" type="hidden" value="UserEditCancel"/>
   				<input name="Button4" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/admin/listuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')" value="Close">
   			</c:otherwise>
   		  </c:choose>
   		  	</td>
           	</tr>
            </table>
         </label></td>
        </tr>
        
    </table>
    <table>  

   		<tr><td height="35">&nbsp;</td></tr>      
    </table>
    </div>
    
     <div id="table2" style="display:none">
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td class="tableHeading">USER ROLE </td>
          <td class="tableHeading">READ</td>
          <td class="tableHeading">POST TRANSACTION </td>
        </tr>
       
     <c:choose>
       <c:when test="${userForm.user.activeFlag == 'Y' || login == null  || login == ''}"> 
       <tr>
        	<td class="tableRow"><c:out value="Planning"/></td>
        	<td class="tableRow">
        		<html-el:select property="planningRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="planningPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
        			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        <tr>
        	<td class="tableAlternateRow"><c:out value="Book Specification"/></td>
        	<td class="tableAlternateRow">
        		<html-el:select property="bookSpecRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableAlternateRow">
        		<html-el:select property="bookSpecPost" styleClass="textfield" >
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        <tr>
        	<td class="tableRow"><c:out value="Purchase Orders"/></td>
        	<td class="tableRow">
        		<html-el:select property="purchaseOrderRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="purchaseOrderPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        <tr>
        	<td class="tableAlternateRow"><c:out value="Order Confirmation"/></td>
        	<td class="tableAlternateRow">
        		<html-el:select property="orderConfirmRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableAlternateRow">
        		<html-el:select property="orderConfirmPost" styleClass="textfield" >
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        <tr>
        	<td class="tableRow"><c:out value="Order Status"/></td>
        	<td class="tableRow">
        		<html-el:select property="orderStatusRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="orderStatusPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        
        <tr>
        	<td class="tableAlternateRow"><c:out value="Delivery Message"/></td>
        	<td class="tableAlternateRow">
        		<html-el:select property="deliveryMesgRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableAlternateRow">
        		<html-el:select property="deliveryMesgPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        <tr>
        	<td class="tableRow"><c:out value="Goods Receipt"/></td>
        	<td class="tableRow">
        		<html-el:select property="goodsReceiptRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="goodsReceiptPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
        			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        <tr>
        	<td class="tableAlternateRow"><c:out value="Usage"/></td>
        	<td class="tableAlternateRow">
        		<html-el:select property="usageRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableAlternateRow">
        		<html-el:select property="usagePost" styleClass="textfield" >
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        <tr>
        	<td class="tableRow"><c:out value="Onhand Inventory"/></td>
        	<td class="tableRow">
        		<html-el:select property="inventoryRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="inventoryPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        <tr>
        	<td class="tableRow"><c:out value="ARP Title SetUp"/></td>
        	<td class="tableRow">
        		<html-el:select property="arpTitleRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="arpTitlePost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
         
        <!-- <tr>
        	<td class="tableRow"><c:out value="Dropship Instructions"/></td>
        	<td class="tableRow">
        		<html-el:select property="dropshipinstructionsRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="dropshipinstructionsPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        <tr>
        	<td class="tableRow"><c:out value="Upload ShipInfo"/></td>
        	<td class="tableRow">
        		<html-el:select property="uploadShipInfoRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="uploadShipInfoPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr> -->
        <tr>
        	<td class="tableRow"><c:out value="Dropship"/></td>
        	<td class="tableRow">
        		<html-el:select property="dropshipinstructionsRead" styleClass="textfield">
          			<html-el:option value="Y">Yes</html-el:option>
                	<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
          	<td class="tableRow">
        		<html-el:select property="dropshipinstructionsPost" styleClass="textfield">
        			<html-el:option value="Y">Yes</html-el:option>
          			<html-el:option value="N">No</html-el:option>
          		</html-el:select>
          	</td>
        </tr>
        
        </c:when>
        <c:when test="${userForm.user.activeFlag == 'N'}">
        	<c:forEach var="privilegeList" items="${userForm.user.privilegeCollection}" varStatus="indexId3">  
       
       <c:choose>
       	<%--<c:when test="${privilegeList.moduleDetail.refId == '3'}">--%>
       	<c:when test="${privilegeList.moduleDetail.refCode == 'PLA'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Planning"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Planning"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Planning"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Planning"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
       
        
        <c:when test="${privilegeList.moduleDetail.refCode == 'BSP'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Book Specification"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Book Specification"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Book Specification"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Book Specification"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
       
        
        <c:when test="${privilegeList.moduleDetail.refCode == 'POR'}">
			<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Purchase Orders"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Purchase Orders"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Purchase Orders"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Purchase Orders"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
        
        
        <c:when test="${privilegeList.moduleDetail.refCode == 'OCO'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Order Confirmation"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Order Confirmation"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Order Confirmation"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Order Confirmation"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
        
        <c:when test="${privilegeList.moduleDetail.refCode == 'OST'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Order Status"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Order Status"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Order Status"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Order Status"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
        
        <c:when test="${privilegeList.moduleDetail.refCode == 'DME'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Delivery Message"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Delivery Message"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Delivery Message"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Delivery Message"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
        
        <c:when test="${privilegeList.moduleDetail.refCode == 'GRE'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Goods Receipt"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Goods Receipt"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Goods Receipt"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Goods Receipt"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
        
        
        <c:when test="${privilegeList.moduleDetail.refCode == 'USG'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Usage"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Usage"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Usage"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Usage"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
        
        
        <c:when test="${privilegeList.moduleDetail.refCode == 'INV'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Onhand Inventory"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Onhand Inventory"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Onhand Inventory"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Onhand Inventory"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
          
        <c:when test="${privilegeList.moduleDetail.refCode == 'ARP'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="ARP Title SetUp"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="ARP Title SetUp"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="ARP Title SetUp"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="ARP Title SetUp"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>
         
        <c:when test="${privilegeList.moduleDetail.refCode == 'DS'}">
       		<c:choose>
       		<c:when test="${privilegeList.accessFlag == 'R'}">
       			<tr>
        			<td class="tableRow"><c:out value="Dropship"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'W'}">
       			<tr>
        			<td class="tableRow"><c:out value="Dropship"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'B'}">
       			<tr>
        			<td class="tableRow"><c:out value="Dropship"/></td>
        			<td class="tableRow">
        				Yes
          			</td>
          			<td class="tableRow">
        				Yes
          			</td>
        		</tr>
        	</c:when>
        	<c:when test="${privilegeList.accessFlag == 'N'}">
       			<tr>
        			<td class="tableRow"><c:out value="Dropship"/></td>
        			<td class="tableRow">
        				No
          			</td>
          			<td class="tableRow">
        				No
          			</td>
        		</tr>
        	</c:when>
        	</c:choose>
        </c:when>    
        </c:choose>
       </c:forEach> 
               	
        </c:when>
        
       </c:choose> 	
           
        <tr>
          <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <div id="buttons2" class="tabSelectText"></div> 
    <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td align="right" height="15"></td>
        </tr>
        <tr>
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          	<input name="userFilter" type="hidden" value="${userFilter}">
			<input name="accountStatusFilter" type="hidden" value="${accountStatusFilter}">
			<input name="accountTypeFilter" type="hidden" value="${accountTypeFilter}">
			<input name="startDateFilter" type="hidden" value="${startDateFilter}">
			<input name="endDateFilter" type="hidden" value="${endDateFilter}">
          <c:choose>
          	<c:when test="${login == null || login == ''}">
          		<input name="Button" type="button" class="buttonMain" onClick="if(validateUser('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/insertuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')}else{return false;}" value="Save">
          		<input name="UserEditCancel" type="hidden" value="UserEditCancel"/>
            	<input name="Button2" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/admin/listuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')" value="Close">
            </c:when>
            <c:otherwise>
            	<c:if test="${userForm.user.activeFlag == 'Y'}">
            		<input name="Button3" type="button" class="buttonMain" onClick="if(validateUser('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/updateuser.do?LOGIN=${login}&ADMIN_MODULE=USER&PAGE_VALUE=${PAGE_VALUE}')}else{return false;}" value="Update">
            	</c:if>
            	<input name="UserEditCancel" type="hidden" value="UserEditCancel"/>
   				<input name="Button4" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/admin/listuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')" value="Close">
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
    </table></div>
    <div id="table3" style="display:none">
	<table width="98%" border="0" cellspacing="1" cellpadding="0">		
	
		<c:forEach var="CheckSupplier" items="${userForm.user.partyCollection}" varStatus="indexId">  
			<c:choose>
				<c:when test="${CheckSupplier.partyType == 'V' || CheckSupplier.partyType == 'M' || CheckSupplier.partyType == 'D'}">
					<c:set var="SupplierFlag" value="0"/>
				</c:when>
			</c:choose>
	   </c:forEach>
		<c:choose>
			<c:when test="${SupplierFlag == 0}">
				<tr>
					<c:if test="${userForm.user.activeFlag == 'Y' || login == null  || login == ''}">
						<td width="3%" class="tableHeading">
						<input name="checkall" id="checkall" type="checkbox" value="checkbox" onclick="checkUncheckAllSup(this);">
						</td>					
					</c:if>
					
          			<td class="tableHeading">SUPPLIER NAME </td>
          			<td class="tableHeading">SAN</td>
          			<td class="tableHeading">ACCOUNT TYPE</td>
          			<td class="tableHeading">DATE ADDED </td>
                	<td class="tableHeading">USER ADDED</td>
                </tr>
             </c:when>
             <c:otherwise>
             	<tr><td align="center" valign="middle" height="30px" class="noRecordsMessage">
					Currently there are no suppliers associated to the user.
				</td></tr>
			 </c:otherwise>
		</c:choose>
              
        
       <c:forEach var="supplierList" items="${userForm.user.partyCollection}" varStatus="indexId3">  
       	<%--<c:if test="${supplierList.partyType == 'V'}">--%>
       	<c:if test="${supplierList.partyType == 'V' || supplierList.partyType == 'M' || supplierList.partyType == 'D'}">
       	
       		<tr>
       			<c:if test="${userForm.user.activeFlag == 'Y' || login == null  || login == ''}">
        			<td class="tableRow">
					<!-- <html-el:radio property="radioSupplier" value="${indexId3.index}"></html-el:radio>-->
        			<html-el:checkbox property="radioSupplier" styleId="radioSupplier" value="${indexId3.index}"></html-el:checkbox>
        			</td>
        		</c:if>
          		<td class="tableRow"><c:out value="${supplierList.name1}"/></td>
          		<td class="tableRow"><c:out value="${supplierList.san}"/></td>
          		<td class="tableRow">
          			<c:choose>
						<c:when test="${supplierList.partyType=='M'}">
							Mill
						</c:when>
						<c:when test="${supplierList.partyType=='D'}">
							Merchant
						</c:when>
						<c:when test="${supplierList.partyType=='V'}">
							Suppliers
						</c:when>
					</c:choose>
          		</td>
          		<td class="tableRow"><fmt:formatDate value="${supplierList.creationDateTime}" type="both" pattern="MM/dd/yy" /></td>
          		<%--<td class="tableRow"><%=loginUser%> <%=loginUserLastName%></td>--%>
          		<c:choose>
          			<c:when test="${login == null || login == ''}">
          				<td class="tableRow"><%=loginUser%> <%=loginUserLastName%></td>
          			</c:when>
          			<c:otherwise>
          				<c:choose>
          					<c:when test="${supplierTab != null}">
          						<td class="tableRow"><%=loginUser%> <%=loginUserLastName%></td>
          					</c:when>
          					<c:otherwise>
          						<td class="tableRow">${createdBy}</td>	
          					</c:otherwise>
          				</c:choose>
          			</c:otherwise>
          		</c:choose>
          		</tr> 
        </c:if> 	
       </c:forEach> 
        
        
        
        <tr>
          <td height="1" colspan="6" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
        
      </table>
      
    <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr><td height="15" align="right"></td></tr>
        
        <tr>
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
            <input name="userFilter" type="hidden" value="${userFilter}">
			<input name="accountStatusFilter" type="hidden" value="${accountStatusFilter}">
			<input name="accountTypeFilter" type="hidden" value="${accountTypeFilter}">
			<input name="startDateFilter" type="hidden" value="${startDateFilter}">
			<input name="endDateFilter" type="hidden" value="${endDateFilter}">
            <c:choose>
        		<c:when test="${login == null || login == ''}">
        			
           			<input type="hidden" id="suppIds" name="supplierIds">					
            		<input name="Button" type="button" class="buttonMain" onClick="javascript:return MM_openAdminBrWindow('supplierpopup.do?PAGE_VALUE=1&ADMIN_MODULE=SUP&METHOD=USER_ADD&LOGIN=${login}&FLAGSUP=${flagforsupplier}&partyList=partyList','AddSupplier','scrollbars=yes,width=600,height=650,left=400,top=10')" value="Add Supplier">
            		<input name="Button1" type="button" class="buttonMain" value="Delete Supplier" onClick="if(validateDeleteSupp()){submitActionUser('<%=request.getContextPath()%>/admin/deletesupplierfromuser.do?PAGE_VALUE=1&ADMIN_MODULE=SUP&METHOD=USER_ADD&LOGIN=${login}')}else{return false;}">
            		<input name="Button2" type="button" class="buttonMain" onClick="if(validateUser('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/insertuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')}else{return false;}" value="Save">
            		<input name="UserEditCancel" type="hidden" value="UserEditCancel"/>
            		<input name="Button3" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/admin/listuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')" value="Close">
            	</c:when>
            	<c:otherwise>
         			<input type="hidden" id="suppIds" name="supplierIds">						
            		<input name="USERNAME" type="hidden" value="${userName}"/>
            		<c:if test="${userForm.user.activeFlag == 'Y'}">
            			<input name="Button" type="button" class="buttonMain" onClick="javascript:return MM_openAdminBrWindow('supplierpopupedit.do?PAGE_VALUE=1&ADMIN_MODULE=SUP&METHOD=USER_EDIT&LOGIN=${login}&FLAGSUP=${flagforsupplier}&partyList=partyList','AddSupplier','scrollbars=yes,width=600,height=650,left=400,top=10')" value="Add Supplier">
            			<input name="Button1" type="button" class="buttonMain" value="Delete Supplier" onClick="if(validateDeleteSupp()){submitActionUser('<%=request.getContextPath()%>/admin/deletesupplierfromuseredit.do?PAGE_VALUE=1&ADMIN_MODULE=SUP&METHOD=USER_EDIT&LOGIN=${login}')}else{return false;}">
            			<input name="Button2" type="button" class="buttonMain" onClick="if(validateUser('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/updateuser.do?LOGIN=${login}&ADMIN_MODULE=USER&PAGE_VALUE=${PAGE_VALUE}')}else{return false;}" value="Update">
            		</c:if>
            		<input name="UserEditCancel" type="hidden" value="UserEditCancel"/>
            		<input name="Button3" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/admin/listuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')" value="Close">
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
    </table></div>
   <div id="table4" style="display:none">
   <table width="98%" border="0" cellspacing="1" cellpadding="0">
   
        <c:forEach var="CheckPublisher" items="${userForm.user.partyCollection}" varStatus="indexId">  
			<c:choose>
				<c:when test="${CheckPublisher.partyType == 'B'}">
					<c:set var="PublisherFlag" value="0"/>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${PublisherFlag == 0}">
				<tr>
					<c:if test="${userForm.user.activeFlag == 'Y' || login == null  || login == ''}">
						<td width="3%" class="tableHeading">
						<input name="checkallpub" id="checkallpub" type="checkbox" value="checkboxpub" onclick="checkUncheckAllPub(this);">
					</c:if>
          			<td class="tableHeading">PUBLISHING UNIT </td>
          			<td class="tableHeading">SAN</td>
          			<td class="tableHeading">DATE ADDED </td>
                	<td class="tableHeading">USER ADDED</td>
                </tr>
             </c:when>
             <c:otherwise>
             	<tr><td align="center" valign="middle" height="30px" class="noRecordsMessage">
					Currently there are no publisher associated to the user.
				</td></tr>
			 </c:otherwise>
		</c:choose>
        <c:forEach var="supplierList" items="${userForm.user.partyCollection}" varStatus="indexId4">  
               
        <c:if test="${supplierList.partyType == 'B'}">
       		<tr>
       			<c:if test="${userForm.user.activeFlag == 'Y' || login == null  || login == ''}">
        			<td class="tableRow">
        			<%--<input name="radiobutton" type="radio" value="radiobutton">--%>
        			<%--<html-el:radio property="radioPublisher" value="${indexId4.index}"></html-el:radio>--%>
        				<html-el:checkbox property="radioPublisher" styleId="radioPublisher" value="${indexId4.index}"></html-el:checkbox>
        			</td>
        		</c:if>
          		<td class="tableRow"><c:out value="${supplierList.name1}"/></td>
          		<td class="tableRow"><c:out value="${supplierList.san}"/></td>
          		<td class="tableRow"><fmt:formatDate value="${supplierList.creationDateTime}" type="both" pattern="MM/dd/yy" /></td>
          		<%--<td class="tableRow"><%=loginUser%> <%=loginUserLastName%></td>--%>
          		<c:choose>
          			<c:when test="${login == null || login == ''}">
          				<td class="tableRow"><%=loginUser%> <%=loginUserLastName%></td>
          			</c:when>
          			<c:otherwise>
          				<c:choose>
          					<c:when test="${publisherTab != null}">
          						<td class="tableRow"><%=loginUser%> <%=loginUserLastName%></td>
          					</c:when>
          					<c:otherwise>
          						<td class="tableRow">${createdBy}</td>	
          					</c:otherwise>
          				</c:choose>
          			</c:otherwise>
          		</c:choose>
          		
          	</tr> 
          </c:if>
        </c:forEach> 
         
        <tr>
          <td height="1" colspan="5" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      
   <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr><td height="15" align="right"></td></tr>
        
        <tr>
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          	<input name="userFilter" type="hidden" value="${userFilter}">
			<input name="accountStatusFilter" type="hidden" value="${accountStatusFilter}">
			<input name="accountTypeFilter" type="hidden" value="${accountTypeFilter}">
			<input name="startDateFilter" type="hidden" value="${startDateFilter}">
			<input name="endDateFilter" type="hidden" value="${endDateFilter}">
          <c:choose>
        		<c:when test="${login == null || login == ''}">
          			<input type="hidden" id="pubIds" name="publisherIds">
            		<input name="Button" type="button" class="buttonMain" onClick="javascript:return MM_openAdminBrWindow('pubunitpopup.do?PAGE_VALUE=1&ADMIN_MODULE=PUB&METHOD=USER_ADD&LOGIN=${login}&FLAGPUB=${flagforpublisher}&partyListPub=partyListPub','AddSupplier','scrollbars=yes,width=600,height=650,left=400,top=10')" value="Add Pub Unit">
            		<input name="Button1" type="button" class="buttonMain" value="Delete Pub Unit" onClick="if(validateDeletePub()){submitActionUser('<%=request.getContextPath()%>/admin/deletepublisherfromuser.do?PAGE_VALUE=1&ADMIN_MODULE=PUB&METHOD=USER_ADD&LOGIN=${login}')}else{return false;}">
            		<input name="Button2" type="button" class="buttonMain" onClick="if(validateUser('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/insertuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')}else{return false;}" value="Save">
            		<input name="UserEditCancel" type="hidden" value="UserEditCancel"/>
            		<input name="Button3" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/admin/listuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')" value="Close">
            	</c:when>
            	<c:otherwise>
            		<input type="hidden" id="pubIds" name="publisherIds">
            		<input name="USERNAME" type="hidden" value="${userName}"/>
            		<c:if test="${userForm.user.activeFlag == 'Y'}">
            			<input name="Button" type="button" class="buttonMain" onClick="javascript:return MM_openAdminBrWindow('pubunitpopupedit.do?PAGE_VALUE=1&ADMIN_MODULE=PUB&METHOD=USER_EDIT&LOGIN=${login}&FLAGPUB=${flagforpublisher}&partyListPub=partyListPub','AddSupplier','scrollbars=yes,width=600,height=650,left=400,top=10')" value="Add Pub Unit">
            			<input name="Button1" type="button" class="buttonMain" value="Delete Pub Unit" onClick="if(validateDeletePub()){submitActionUser('<%=request.getContextPath()%>/admin/deletepublisherfromuseredit.do?PAGE_VALUE=1&ADMIN_MODULE=PUB&METHOD=USER_EDIT&LOGIN=${login}')}else{return false;}">
            			<input name="Button2" type="button" class="buttonMain" onClick="if(validateUser('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/updateuser.do?LOGIN=${login}&ADMIN_MODULE=USER&PAGE_VALUE=${PAGE_VALUE}')}else{return false;}" value="Update">
            		</c:if>
            		<input name="UserEditCancel" type="hidden" value="UserEditCancel"/>
            		<input name="Button3" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/admin/listuser.do?PAGE_VALUE=${PAGE_VALUE}&ADMIN_MODULE=USER')" value="Close">
            	</c:otherwise>
            </c:choose>
            </td>
            </tr>
            </table>
          </label></td>
        </tr>
        <tr>
          <td height="35"></td>
        </tr>
    </table></div>
    
	<c:choose>
		<c:when test="${(supplierTab == '' && publisherTab == '') || (supplierTab == null && publisherTab == null)}">
			<script language="javascript">showDiv(1)</script>
		</c:when>
		<c:when test="${supplierTab != ''}">
			<script language="javascript">showDiv(3)</script>
		</c:when>
		<c:when test="${publisherTab != ''}">
			<script language="javascript">showDiv(4)</script>
		</c:when>
		<c:otherwise>
			<script language="javascript">showDiv(2)</script>
		</c:otherwise>
	</c:choose> 
    <html-el:form action="/admin/listuser.do">
    </html-el:form>
    </html-el:form></td>
  </tr>


