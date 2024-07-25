<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.dto.admin.User"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>

<%
User objUser = (User)session.getAttribute("USER_INFO");
String login = objUser.getFirstName()+" "+objUser.getLastName();
Integer userId = objUser.getUserId();
String password = objUser.getPassword();
%>	
<c:set var="oldPassword" value='<%=session.getAttribute("Password")%>'/>
<html-el:hidden styleId="PassWord" property="CurrentPassWord" value="${oldPassword}"/>
<c:set var="existingpassword" value='<%=request.getParameter("existingpassword")%>'/>
<c:set var="userId"><%=userId%></c:set>

 <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    	<span class="headingText">Edit Profile - <%=login%></span>    	
    </td>
 </tr>
  
  <tr>
    <td align="left" valign="top" class="padding23">
    
    <html-el:form action="/admin/adduserinfo">
      <%@ include file="/common/formbegin.jsp"%>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      	<tr>
  			
  			<c:if test="${PasswordExist!=null}">
  				<font color="#CC0000" size="2" face="Verdana">
    			<span>${PasswordExist}</span>
  			</c:if>	
  		</tr>
  		<tr><td>
    		<table cellpadding="0" cellspacing="0">
    		<tr>
    		<td id="error_div" class="errorMessageText"></td>
    		</tr>
    		</table>
    	</td></tr>	
    	
    	<tr>
        	<td height="15" colspan="2" class="text"><div id="txtTitle">To <span class="textBold">Edit the User Info </span> details, fill in the following fields.  Fields marked with <span class="mandatory">*</span> are mandatory. </div></td>
        </tr>
      	
        
        <tr>
          <td height="15" colspan="2"></td>
        </tr>
        
        
        <tr>
          <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
              <tr>
			  <td align="left">
			  
			  <table width="100%" border="0" cellpadding="0" cellspacing="0">
			 <tr >
			 
			 <td height="5"></td>
			 </tr>	  	
			 <tr>
          <td width="100%" height="22">
		  <div id="tabLinks">
				<div id="tab1" class="selected1"><a href="javascript:showDiv(1)" >Info</a></div>
				<div id="tab2"><a href="javascript:showDiv(2)" >Associate Role</a></div>
				<div id="tab3"><a href="javascript:showDiv(3)">Associate Supplier</a></div>		
				<div id="tab4"><a href="javascript:showDiv(4)">Associate PUB Unit</a></div>								
		  </div>
		  </td>
          
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
        <td width="25%" class="blueColumn">SSOID:<span class="mandatory">*</span> </td>
          <td width="25%" align="left" class="lightblueColumn">${userForm.v42}</td>
<%--	<td width="25%" align="left" class="lightblueColumn">${userForm.user.ssoid}</td>      
     <td width="25%" class="blueColumn">USER ID:<span class="mandatory">*</span> </td>
          <td width="25%" align="left" class="lightblueColumn">${userForm.user.login}</td>
     --%>     
          <td align="left" class="blueColumn">ADDRESS LINE 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address1" size="40" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
        <td width="25%" class="blueColumn">USER ID:<span class="mandatory">*</span> </td>
		<td width="25%" align="left" class="lightblueColumn">${userForm.v42}</td>
<%--           <td width="25%" align="left" class="lightblueColumn">${userForm.user.login}</td>
<td class="blueColumn">FIRST NAME:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">${userForm.user.firstName}</td>
--%>
          <td align="left" class="blueColumn">ADDRESS LINE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address2" size="40" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">FIRST NAME:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">${userForm.user.firstName}</td>
<%--           <td class="blueColumn">LAST NAME:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">${userForm.user.lastName}</td>
     --%>     
          <td align="left" class="blueColumn">ADDRESS LINE 3:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address3" size="27" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
<td class="blueColumn">LAST NAME:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">${userForm.user.lastName}</td>
<%--           <td width="25%" class="blueColumn">ACCOUNT TYPE:<span class="mandatory">*</span></td>
          <td width="25%" align="left" class="lightblueColumn">
          	<c:choose>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'A'}">
          			Admin
          		</c:when>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'B'}">
          			Buyer
          		</c:when>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'V'}">
          			Vendor
          		</c:when>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'M'}">
          			Mill
          		</c:when>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'C'}">
          			Cost Accounting
          		</c:when>
          	</c:choose>
          </td>
--%>
          <td width="25%" align="left" class="blueColumn">ADDRESS LINE 4: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address4" size="27" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
<td width="25%" class="blueColumn">ACCOUNT TYPE:<span class="mandatory">*</span></td>
          <td width="25%" align="left" class="lightblueColumn">
          	<c:choose>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'A'}">
          			Admin
          		</c:when>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'B'}">
          			Buyer
          		</c:when>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'V'}">
          			Vendor
          		</c:when>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'M'}">
          			Mill
          		</c:when>
          		<c:when test="${userForm.user.roleTypeDetail.roleType == 'C'}">
          			Cost Accounting
          		</c:when>
          	</c:choose>
          </td>
<%--          <td class="blueColumn">ACCOUNT STATUS:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	<c:choose>
          		<c:when test="${userForm.user.activeFlag == 'Y'}">
          			Active
          		</c:when>
          		<c:otherwise>
          			Disabled
          		</c:otherwise>
          	</c:choose>
          </td>
--%>          
          <td align="left" class="blueColumn">STATE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.state" size="27" maxlength="20" styleClass="textfield"/></td>
        </tr>
        <tr>
        <td class="blueColumn">ACCOUNT STATUS:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	<c:choose>
          		<c:when test="${userForm.user.activeFlag == 'Y'}">
          			Active
          		</c:when>
          		<c:otherwise>
          			Disabled
          		</c:otherwise>
          	</c:choose>
          </td>
<%--
          <td class="blueColumn">PASSWORD:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	
          	<html-el:password styleId="pass" property="user.password" onkeyup="passwordClick()" maxlength="20" styleClass="textfield" size="27"/>
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
          <td align="left" class="blueColumn">ZIP:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.postalCode" size="27" maxlength="20" styleClass="textfield"/></td>
          
        </tr>
        <tr>
<%-- 
          <td class="blueColumn">PASSWORD EXPIRES:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
           	<c:choose>
          		<c:when test="${userForm.user.passwordExpiry == 'Y'}">
          			Yes
          		</c:when>
          		<c:otherwise>
          			No
          		</c:otherwise>
          	</c:choose>
          </td>
--%>        
  <td class="blueColumn">BUSINESS PHONE 1: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone1" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">CITY:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.city" size="27" maxlength="45" styleClass="textfield"/></td>
        </tr>
        <tr>

<%--           <td class="blueColumn">BUSINESS PHONE 1: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone1" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
<td class="blueColumn">BUSINESS PHONE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone2" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">COUNTRY:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	<html-el:select property="user.countryDetail.countryCode" styleClass= "textfield" >
		  		<html-el:option value="">Select</html-el:option>
					<c:forEach var="item" items="${userListCountry}" varStatus="indexId2">
						<html-el:option value="${item.countryCode}"><c:out value="${item.countryName}"/></html-el:option>
					</c:forEach>
	  		</html-el:select>
	  	  </td>
        </tr>
        <tr>

<%--           <td class="blueColumn">BUSINESS PHONE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone2" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
<td class="blueColumn">FAX 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax1" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">EMAIL:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">${userForm.user.email}</td>
        </tr>
        <tr>

<%--           <td class="blueColumn">FAX 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax1" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
<td class="blueColumn">FAX 2:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax2" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">MOBILE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.mobile" size="27" maxlength="25" styleClass="textfield"/></td>
        </tr>
        <tr>

<%--           <td class="blueColumn">FAX 2:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax2" size="27" maxlength="25" styleClass="textfield"/></td>
--%>
          <td class="blueColumn">EMAIL USER ON ACTIVITY: </td>
          <td align="left" class="lightblueColumn">
           	<c:choose>
          		<c:when test="${userForm.user.emailActivityFlag == 'Y'}">
          			Yes
          		</c:when>
          		<c:when test="${userForm.user.emailActivityFlag == 'N'}">
          			No
          		</c:when>
          		<c:otherwise></c:otherwise>
          	</c:choose>	
          </td>
          <td align="left" class="blueColumn">WEBSITE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.website" size="27" maxlength="50" styleClass="textfield"/></td>
        </tr>
        <tr>
 <%--          <td class="blueColumn">EMAIL USER ON ACTIVITY: </td>
          <td colspan="3" align="left" class="lightblueColumn">
           	<c:choose>
          		<c:when test="${userForm.user.emailActivityFlag == 'Y'}">
          			Yes
          		</c:when>
          		<c:when test="${userForm.user.emailActivityFlag == 'N'}">
          			No
          		</c:when>
          		<c:otherwise></c:otherwise>
          	</c:choose>	
          </td>
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
<!--      		<input name="Button3" type="button" class="buttonMain" onClick="if(validateUserProfile()){submitActionUser('<%=request.getContextPath()%>/admin/updateprofile.do?ADMIN_MODULE=USER&LOGIN=EDITPROFILE')}else{return false;}" value="Update">-->
			<input name="Button3" type="button" class="buttonMain" onClick="if(validateUserProf('changePass')){submitActionUser('<%=request.getContextPath()%>/admin/updateprofile.do?ADMIN_MODULE=USER&LOGIN=EDITPROFILE')}else{return false;}" value="Update">
            <input name="Button4" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/home/home.do')" value="Close">
   			</td>
            </tr>
            </table>
   		  </label></td>
        </tr>
      
    </table>  
    <table>  
       <tr><%--<td class="errorMessageText">Password Security Rules:<br><LI>Password must be at least 7 characters long.</LI><br><LI>Password should not match with last seven passwords.</LI><br><LI>The first 5 characters of the Password should not be same as the previous password.</LI><br><LI>Password should contain atleast one uppercase, one lowercase, one numeric, and one special character.</LI></td>--%></tr>
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
       
       <c:forEach var="privilegeList" items="${userForm.user.privilegeCollection}" varStatus="indexId3">  
       
       <c:choose>
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
               
        
        <tr>
          <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
     
    <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td align="right" height="15"></td>
        </tr>
        <tr>
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          	<input name="Button4" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/home/home.do')" value="Close">
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
	
        <!-- <tr>
          <td class="tableHeading">SUPPLIER NAME </td>
          <td class="tableHeading">SAN</td>
          <td class="tableHeading">DATE ADDED </td>
          <td class="tableHeading">USER ADDED</td>
          
          
        </tr>-->
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
        
       <c:forEach var="partyList" items="${userForm.user.partyCollection}" varStatus="indexId3">  
       	<c:if test="${partyList.partyType == 'V' || partyList.partyType == 'M' || partyList.partyType == 'D'}">
        	<tr>
        		<td class="tableRow"><c:out value="${partyList.name1}"/></td>
          		<td class="tableRow"><c:out value="${partyList.san}"/></td>
          		<td class="tableRow">
          			<c:choose>
						<c:when test="${partyList.partyType=='M'}">
							Mill
						</c:when>
						<c:when test="${partyList.partyType=='D'}">
							Merchant
						</c:when>
						<c:when test="${partyList.partyType=='V'}">
							Suppliers
						</c:when>
					</c:choose>
          		</td>
          		<td class="tableRow"><fmt:formatDate value="${partyList.creationDateTime}" type="both" pattern="MM/dd/yy" /></td>
          		<td class="tableRow"><%=login%></td>
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
           	<input name="Button2" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/home/home.do')" value="Close">
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
       <!--  <tr>
          <td class="tableHeading">PUBLISHING UNIT </td>
          <td class="tableHeading">SAN</td>
          <td class="tableHeading">DATE ADDED </td>
          <td class="tableHeading">USER ADDED</td>
        </tr>-->
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
        <c:forEach var="partyList" items="${userForm.user.partyCollection}" varStatus="indexId4">  
               
        <c:if test="${partyList.partyType == 'B'}">
       		<tr>
        		<td class="tableRow"><c:out value="${partyList.name1}"/></td>
          		<td class="tableRow"><c:out value="${partyList.san}"/></td>
          		<td class="tableRow"><fmt:formatDate value="${partyList.creationDateTime}" type="both" pattern="MM/dd/yy" /></td>
          		<td class="tableRow"><%=login%></td>
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
          	<input name="Button2" type="button" class="buttonMain" onClick="submitUserCancelAction('<%=request.getContextPath()%>/home/home.do')" value="Close">
          	</td>
            </tr>
            </table>
          </label></td>
        </tr>
        <tr>
          <td height="35"></td>
        </tr>      
        
    </table></div>    
    </html-el:form></td>
  </tr>






