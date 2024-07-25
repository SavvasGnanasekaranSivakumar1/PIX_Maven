<% 
	String path = (String)request.getContextPath();
	String fullPath = null;
	if(request.getParameter("parent") != null)
	{

	fullPath = path+"/login.do?parent="+request.getParameter("parent");
	}
	else
	fullPath = path+"/login.do";

	System.out.println(fullPath);	
	response.sendRedirect(fullPath);
%>
<%-- 
String fullPath = path+"/home/home.do";
	response.sendRedirect(fullPath);
		out.print(fullPath);
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants" %>
<script language="javascript" src="<%=request.getContextPath()%>/js/helloform.js">
</script> 


<script language="javascript" src="<%=request.getContextPath()%>/js/BrowserChecker.js">
</script> 

<script language="javascript">
 
 
 function browserCheck(){
   var brwName= BrowserDetect.browser
   var brwVersion = BrowserDetect.version
   var brwOSName = BrowserDetect.OS
   if((brwName!='Explorer'&&brwName!='Safari')){
   var msg="" ;
   
   msg+=""+ brwName+" internet browser is not supported to run this application. The application may not function properly with this browser.\n\n" ;
   msg+="Supported internet browsers for this application are: (Windows: Internet Explorer version 6+. Mac OS X: Safari Version 2+)" ;
    
    alert(msg);
   }
  
  }
 
  function browserDetect(){
   var brwName=navigator.appName
   var brwVersion = navigator.appVersion
   var brwCodeName = navigator.appCodeName
   // alert('browser......'+brwName+'..Version.....'+brwVersion+'...CodeName...'+brwCodeName);
  }  
   browserCheck();
</script> 
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="PATH" value='<%=request.getContextPath()%>'/>
<html>
<head>
<title>Login Page</title>
<link href="<%=request.getContextPath()%>/css/pixcss.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/pixjs.js"></script>
</head>

<body topmargin="0" leftmargin="0" rightmargin="0" onload="document.LoginForm.loginId.focus();">
<%
if(session.getAttribute("USER_INFO")!=null)
{
	session.removeAttribute("USER_INFO");
}
%>
<table width="100%" height="100%" >
  <tr>
    <td height="482" align="center" valign="middle"><table width="300" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>
         <table width="500" align="center" cellpadding="0"  cellspacing="0" class="loginTableBrdr">
           <tr>
             <td>
             
               <html-el:form action="/login">
               <%@ include file="/common/formbegin.jsp"%>
                <table width="500" cellpadding="1" cellspacing="0">
               
                  <tr>
                    <td colspan="4"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="6"></td>
                   </tr>
                  <tr>
                    <td colspan="4" align="center"><img src="<%=request.getContextPath()%>/images/login_logo.gif" width="178" height="61"></td>
                  </tr>
                  <tr>
                    <td colspan="4"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="6"></td>
                  </tr>
                  <tr>
                    <td colspan="4" class="loginBand">&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="4" class="loginBand">&nbsp;</td>
                  </tr>
                  <tr>
                    <td align="LefT" class="loginBand">&nbsp;</td>
                    <td align="LefT" class="loginBand">Login Name :</td>
                    <td width="59%" colspan="2" class="loginBand">
                    <html-el:text property="loginId" styleClass="textfield" size="30"/></td>
                 </tr>
                  <tr>
                    <td colspan="4" class="loginBand">&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="19%" align="LEFT" class="loginBand">&nbsp;</td>
                    <td width="22%" align="LEFT" class="loginBand">Password :</td>
                    <td colspan="2" class="loginBand">
                   <html-el:password property="password" styleClass="textfield" size="30"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" class="loginBand">&nbsp;</td>
                    <td height="27" colspan="2" class="loginBand"></td>
                  </tr>
                  <tr>
                    <td height="10" colspan="4" class="loginBand"></td>
                    </tr>
                  <tr>
                    <td colspan="4"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="6"></td>
                  </tr>
                  <tr>
                    <td height="22" colspan="4"><table width="100%"  cellspacing="0" cellpadding="0" align="center">
                        <tr>
                           <td width="52%">
                            <div align="center">
                              <html-el:submit property ="validate" value="login" styleClass="buttonMain"/>
                            
                            </td>
                       </tr>
                <tr>
                <td align="center" width="52%">
                           <br>
                           <a href="javascript:forgotpassword('${PATH}/login/forgotpassword.do')" class="LinkGreen2">Forgot Password? Click here.</a></td>
                          
                         </tr>
          <%String message = (String)request.getAttribute(PIXConstants.PIX_ERROR);%>                  
                  
               </table>
             </td>
           </tr>
                  <tr>
                    <td colspan="4"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="6"></td>
                  </tr>
         </table></td>
            </tr>
          </table>
            <table width="504" cellspacing="1" align="center">
              <tr>
                <td height="15" colspan="2" class="searchTilte"></td>
              </tr>
              <tr>
                <td height="4" class="bottomtext"><img src="<%=request.getContextPath()%>/images/trans.gif"></td>
                <td align="right"></td>
              </tr>
              <tr>
                <td width="266" height="14" class="bottomtext"> <font color="#FF0000" size="1">
                    <html-el:errors />
                    <span class="bottomtext">
                    <font color="#FF0000" size="1" style="font-weight=bold">
                    <%= message == null ? "" : message%> </span>
                    </font></span></td>
                <td align="right"><img src="<%=request.getContextPath()%>/images/pcs_logo_big.gif" alt="PCS">                  </td>
              </tr>
            </table>
            <table width="100%" align="center" cellspacing="0" class="greyColumnHeading">
              <tr>
                <td height="4px"></td>
              </tr>
            </table>
            <table width="100%" align="center" >
              <tr>
                <td><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="4"></td>
              </tr>
              <tr>
                <td align="center" class="headingMainArrow" >PEARSON EDUCATION </td>
              </tr>
          </table></td>
           
      </tr>
      
    </table></td>
  </tr>
 
</table>
</html-el:form>
</body>
</html>


 --%>