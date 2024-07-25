
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.dto.admin.User"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%--<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>--%>
<script language="javascript" src="<%=request.getContextPath()%>/js/admin.js">
</script>

<link href="<%=request.getContextPath()%>/css/pixcss.css" rel="stylesheet" type="text/css"/>
<%
User objUser = (User)session.getAttribute("USER_INFO");
String login = objUser.getFirstName()+" "+objUser.getLastName();
%>	

 <body class="BodyStyle">
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
    <td height="92" colspan="3" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="175" align="right" valign="top"><a href="<%=request.getContextPath()%>/index.html"><img src="<%=request.getContextPath()%>/images/Logo.gif" alt="Savvas Implementation of XBITS" width="177" height="92" border="0" /></a></td>
        <td width="*" align="left" valign="top"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="302" height="69" class="headerbg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="14%"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                          <td width="86%" height="30"><img src="images/trans.gif" width="1" height="1" /></td>
                        </tr>
                        <tr>
                          <td><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                          <td><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                        </tr>
                        <tr>
                          <td><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                          <td class="date">Hi <%=login%>; Today is
                            <script language="JavaScript" type="text/javascript"><!--Date Script Start here
function makeArray() {
     for (i = 0; i<makeArray.arguments.length; i++)
          this[i + 1] = makeArray.arguments[i];
}

var months = new makeArray('Jan','Feb','Mar',
    'Apr','May','Jun','Jul','Aug','Sep',
    'Oct','Nov','Dec');

var date = new Date();
var day  = date.getDate();
var month = date.getMonth() + 1;
var yy = date.getYear();
var year = (yy < 1000) ? yy + 1900 : yy;

document.write(day + " " + months[month] + "&nbsp;" + year+"");
      </script></td>
                        </tr>
                    </table></td>
                    <td class="bannerBack"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                    <td width="298"><img src="<%=request.getContextPath()%>/images/banner_02.gif" width="298" height="69" /></td>
                  </tr>
              </table></td>
            </tr>
           <tr>
              <td height="23" valign="top" class="topLinkBack">&nbsp;</td>
            </tr> 
        </table></td>
      </tr>
    </table>
    </td>
  </tr>
  
  
  <tr>
    <td width="2" rowspan="5" align="left" valign="top" class="LeftBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="2" height="1"></td>
  <td width="100%" height="20" align="right" valign="top" class="LinkGreen">&nbsp;</td>
    <td width="4" rowspan="5" align="right" valign="top" class="RightBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
  </tr>
 
                    


<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    	<span class="headingText">Edit Profile - <%=login%></span>
    	
    </td>
 </tr>
  <c:if test="${PasswordExist==null}">
  <tr>
    <td align="left" valign="top" class="padding23">
    <font color="#CC0000" size="2" face="Verdana">
 <%--    <span ><LI>Your PASSWORD has expired...Please change your password before proceeding.</LI></span>  --%>
    </font>
    </td>
  </tr>
  </c:if> 
  
  <tr>
    <td align="left" valign="top" class="padding23">
   <table> 
  <html-el:form action="/admin/updateprofile" onsubmit="validatepassword(${match})">
      <%@ include file="/common/formbegin.jsp"%>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      	<tr>
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
          <td width="25%" class="blueColumn">USER ID:<span class="mandatory">*</span> </td>
          <td width="25%" align="left" class="lightblueColumn">${userForm.user.login}</td>
          
          <td align="left" class="blueColumn">ADDRESS LINE 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address1" size="40" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">FIRST NAME:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">${userForm.user.firstName}</td>
          <td align="left" class="blueColumn">ADDRESS LINE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address2" size="40" maxlength="60" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">LAST NAME:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">${userForm.user.lastName}</td>
          <td align="left" class="blueColumn">ADDRESS LINE 3:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address3" size="27" maxlength="60" styleClass="textfield"/></td>
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
          		<c:otherwise>
          			Vendor
          		</c:otherwise>
          	</c:choose>
          </td>
          <td width="25%" align="left" class="blueColumn">ADDRESS LINE 4: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.address4" size="27" maxlength="60" styleClass="textfield"/></td>
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
          <td align="left" class="blueColumn">STATE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.state" size="27" maxlength="20" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">PASSWORD:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">
          	<html-el:password property="user.password" maxlength="20" styleClass="textfield" size="27"/>
          </td>
          <td align="left" class="blueColumn">ZIP:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.postalCode" size="27" maxlength="20" styleClass="textfield"/></td>
          
        </tr>
        <tr>
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
          <td align="left" class="blueColumn">CITY:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.city" size="27" maxlength="45" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">BUSINESS PHONE 1: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone1" size="27" maxlength="25" styleClass="textfield"/></td>
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
          <td class="blueColumn">BUSINESS PHONE 2: </td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.phone2" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">EMAIL:<span class="mandatory">*</span></td>
          <td align="left" class="lightblueColumn">${userForm.user.email}</td>
        </tr>
        <tr>
          <td class="blueColumn">FAX 1:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax1" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">MOBILE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.mobile" size="27" maxlength="25" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">FAX 2:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.fax2" size="27" maxlength="25" styleClass="textfield"/></td>
          <td align="left" class="blueColumn">WEBSITE:</td>
          <td align="left" class="lightblueColumn"><html-el:text property="user.website" size="27" maxlength="50" styleClass="textfield"/></td>
        </tr>
        <tr>
          <td class="blueColumn">EMAIL USER ON ACTIVITY: </td>
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
               	<input name="Button3" type="button" class="buttonMain" onClick="if(validateUserProfile()){submitActionUser('<%=request.getContextPath()%>/admin/updateprofile.do?ADMIN_MODULE=USER&LOGIN=EDITPROFILE')}else{return false;}" value="Update">
   			</td>
            </tr>
            </table>
   		  </label></td>
        </tr>
        
    </table>
   <table>  
<%--        <tr><td class="errorMessageText">Password Security Rules:<br><LI>Password must be at least 7 characters long.</LI><br><LI>Password should not match with last seven passwords.</LI><br><LI>The first 5 characters of the Password should not be same as the previous password.</LI><br><LI>Password should contain atleast one uppercase, one lowercase, one numeric, and one special character.</LI></td></tr> --%>
   		<tr><td height="35">&nbsp;</td></tr>      
    </table>
    </div>
    
     
   </html-el:form> </table></td>
  </tr>
  <tr>
    <td height="100%" valign="bottom"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
<td width="2"  align="left" valign="top" class="LeftBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="2" height="1"></td>
        <td colspan="2" valign="bottom" class="bottomrepeatInside">
        <table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td align="center" valign="bottom" class="bottomtext">copyright savvas 2020</td>
              <td width="20%" align="right"><img src="<%=request.getContextPath()%>/images/bottom_logo.gif" alt="PCS Logo" hspace="2" width="85" height="37"></td>
            </tr>
        </table>
        </td>
        <td width="4" align="right" valign="top" class="RightBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
        
        </tr>
    </table></td>
  </tr>
  </table>
  
  </body>




