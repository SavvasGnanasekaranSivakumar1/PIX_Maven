<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>

<%@ page import="com.pearson.pix.dto.admin.User"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>



<script language="javascript">

function logout()
{
		window.close();
}
/*
function chgPass()
{
//	alert('${changePasswordUrl}');
}
*/

/*
function callSelectedURL1()
{
		var element = document.getElementById("populatedrop");

		var url=element.options[element.selectedIndex].value; 
		if(url != ''){

			window.open(url);
			}
		return false;
}
*/
</script>






<script language="JavaScript">
	javascript:window.history.forward();
</script>




<%-- <c:set var="logout_url" value="<%=session.getAttribute("logout_url")%>"/>
<c:set var="isDisplay" value="<%=session.getAttribute("isDisplay")%>"/> --%>

<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}'/>


<td width="100%" height="20" align="right" valign="top" class="LinkGreen">




      
<%-- 
<c:if test="${ssopix != null && ssopix != ''}">
Switch To:&nbsp;
<select name="selectedApp" id="populatedrop" onchange="return callSelectedURL1()">
                    <option value="">Select</option>
                  <c:forEach var="item" items="${ssopix}" varStatus="indexId">
         		<option value="${item.applicationURL}"><c:out value="${item.applicationName}"/></option>
                  </c:forEach>
</select>
</c:if>
--%>


	<a class="subLinks" href="<%=request.getContextPath()%>/home/home.do" >home</a>
	<img src="<%=request.getContextPath()%>/images/linkGreenDiv.gif" width="1" height="20" align="absmiddle"> 
	<%-- 	<a class="subLinks" href="<%=request.getContextPath()%>/logout.do">logout</a> 
	
	<c:if test='${isDisplay == true}'>
		<a href="${logout_url}" class="subLinks">logout</a>
	</c:if>
	<c:if test='${isDisplay == false}'>
		<a class="subLinks" onclick="logout();">logout</a>
	</c:if>   
	
		<a class="subLinks" href="<%=request.getContextPath()%>/logout.do?isDisplay=${isDisplay}">logout</a>
	--%>
	
	<%-- <a class="subLinks" href="<%=request.getContextPath()%>/logout.do" onclick="return closeAllWindows();">logout</a>--%>
	<%-- <a class="subLinks" href="<%=request.getContextPath()%>/logout.do" onclick="return closeAllWindows();">logout</a>--%>
	<a  href="<%=request.getContextPath()%>/logout.do" onclick="return closePIXWindows();">logout</a>
	<img src="<%=request.getContextPath()%>/images/linkGreenDiv.gif" width="1" height="20" align="absmiddle">
	<a class="subLinks" href="<%=request.getContextPath()%>/admin/editprofile.do?ADMIN_MODULE=USER&LOGIN=EDITPROFILE">edit profile</a>
	<img src="<%=request.getContextPath()%>/images/linkGreenDiv.gif" width="1" height="20" border="0" align="absmiddle">
	<a class="subLinks" href="mailto:pcs-support-pub@pearsoned.com">contact admin</a>
	<img src="<%=request.getContextPath()%>/images/linkGreenDiv.gif" width="1" height="20" align="absmiddle">
	<a class="subLinks" href="https://lsk12.service-now.com/" target="_blank" title="MyHelp">MyHelp</a>

	<%
	String picroles = (String)session.getAttribute("isChangePassword");
         System.out.println("PC  "+picroles);
	if(picroles!=null && "show".equals(picroles) )
          {
	%>
	<%--<c:if test="${roleType=='M' || roleType == 'V' || roleType == 'D'}">--%>
        	<img src="<%=request.getContextPath()%>/images/linkGreenDiv.gif" width="1" height="20" border="0" align="absmiddle">
	 <a class="subLinks" href="${changePasswordUrl}" onclick="chgPass();">change password</a>
        	 <% 
         	} %>
	
	<!-- <a class="subLinks" href="${changePasswordUrl}" onclick="chgPass();">change password</a>  -->
   <%--  </c:if>--%>

      
</td>
	
