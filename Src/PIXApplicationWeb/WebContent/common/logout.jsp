
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />

<script type="text/javascript">

//window.close();
  var pmsPath= '${CONTEXT_PATH}';
//  alert(pmsPath);
  pmsPath=pmsPath+"/close.jsp";
  var c_Winodw=window.open(pmsPath,"_self")
  c_Winodw.opener=window;

</script>

