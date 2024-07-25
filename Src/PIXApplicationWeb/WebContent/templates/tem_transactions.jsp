<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/struts-tiles-el.tld" prefix="tiles-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html-el:html>
<head>
<title><tiles-el:getAsString name="title" /></title> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META HTTP-EQUIV="Expires" Content="Thu, 01 Jan 1970 00:00:01 GMT">
<link href="<%=request.getContextPath()%>/css/pixcss.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/pixjs.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%><tiles-el:getAsString name="specificjs" />"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/menu.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/base.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/CollectionInterfaces.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/AbstractCollections.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/HashMap.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/HashSet.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/LinkedHashMap.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/LinkedHashSet.js"></script>

<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>




<script language="JavaScript">
	javascript:window.history.forward();
</script>
<script type="text/javascript">
 openWins = [];
 curWin = 0;

function callSelectedURL(url)
{ 
	
	openWins[curWin++] = window.open(url,'_blank');
	 $.ajax({
            type: "POST",
            cache:false,
            url: "<%=request.getContextPath()%>/login/forgotpassword.do",
            async:false,	
            data:"location_id="+openWins.length                    
           
        }); 
	 
}

function closePIXWindows()
{
	
	//variable set to check the condition for logout
		var flagwar = false;
		
	<%
	String pixChild = (String)request.getSession().getAttribute("childPIX");
	if("true".equals(pixChild))
	{
	%>
		flagwar=true;
		
	<%
	}
	%>
	
	//if the page is not refreshed and if there is any child window
		if(openWins.length>0)
	{
		flagwar=true;
	}
	
	
	if(flagwar==true)
	{
		var con = confirm('If you have any other PCS applications open, please click "Cancel" and close them first before logging out here. Otherwise click "OK" to continue the logout.');
	
		if(con == false)
		{
		
		return false;
		}
		else
		return true;
	
	}
	return true;
	
		
}





var timeout         = 500;
var closetimer		= 0;
var ddmenuitem      = 0;

// open hidden layer
function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();
	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose; 

</script>



</head>
<body class="BodyStyle" onLoad="javascript:selectedLink()">
<c:if test="${USER_INFO == null}">
<c:redirect url="/"/>
</c:if>

<c:if test="${ssopix != null && ssopix != ''}">
<table width="100%">
	<tr>  
<td align="left" valign="bottom" style="padding-left: 21%">
     <ul id="sddm">
				<li><a href="#" onMouseOver="mopen('m1')" onMouseOut="mclosetime()"><img src="<%=request.getContextPath()%>/images/launchAppBtn.gif" width="155" height="20" border="0" /></a>
					<div id="m1" onMouseOver="mcancelclosetime()" onMouseOut="mclosetime()">
					<c:forEach var="launchApp" items="${ssopix}" varStatus="indexId">
                    <a href="#" onClick="callSelectedURL('<c:out value='${launchApp.applicationURL}'/>')"><c:out value="${launchApp.applicationName}"/></a>
                    </c:forEach>
             
 
					</div>
				</li>
			</ul>
    </td>
</tr>
</table>
</c:if>


<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
   	<tiles-el:insert attribute="menu"/>  
<tr>
	<td width="2" rowspan="4" align="left" valign="top" class="LeftBorder">
		<img src="<%=request.getContextPath()%>/images/trans.gif" width="2" height="1">
	</td>
	<tiles-el:insert attribute="usersupport"/>
   	<td width="4" rowspan="4" align="right" valign="top" class="RightBorder">
		<img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1">
	</td>
</tr>   
	<tiles-el:insert attribute="mainbody"/>

  	<tiles-el:insert attribute="footer"/>
</table>
</body>
<tiles-el:insert attribute="datachange"/>

</html-el:html>
