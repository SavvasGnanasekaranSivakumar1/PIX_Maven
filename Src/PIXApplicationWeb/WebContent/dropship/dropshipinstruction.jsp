<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants,com.pearson.pix.business.common.PIXUtil"%>
<%@ page import="com.pearson.pix.dto.admin.User"%>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' />

<c:set var="PAGE" value='<%=request.getAttribute("page")%>' />
<c:set var="STATUS_ID" value='<%=request.getAttribute("statusw")%>' />
<c:set var="haveWriteAccess" value="false"></c:set>
<%
	User objUser = (User)session.getAttribute("USER_INFO");
	if(PIXUtil.getPrivilege(request,PIXConstants.DROPSHIP_CODE).equals("WRITE") || PIXUtil.getPrivilege(request,PIXConstants.DROPSHIP_CODE).equals("BOTH"))
	{
%>
<c:set var="haveWriteAccess" value="true" />	
<% } %>
<c:set var="userForm" value='${objUser}' />
<c:set var="userCollection" value='<%=objUser.getPrivilegeCollection()%>' />
<head>
<title>PIX</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8819-1" />
<meta http-equiv="cache-control" content="max-age=0, must-revalidate, no-cache, no-store, private">
<meta http-equiv="expires" content="-1">
<meta http-equiv="pragma" content="no-cache">
<link href="<%=request.getContextPath()%>/css/pixcss.css" rel="stylesheet" type="text/css">

<script language="javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/jquery-functions.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/pixjs.js"></script>
<script type="text/JavaScript">
<!--
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
function selectAllCheckBoxes(buttonGroup) { 
         if (buttonGroup != undefined) { 
         if (document.getElementById('checkbox1').checked) {
               for (var i = 0; i < buttonGroup.length; i++) {         
               if (buttonGroup[i].disabled) {
                  buttonGroup[i].checked = false;
               } else {           
                  buttonGroup[i].checked = true;
               }            
            }
            buttonGroup.checked = true;         
         } else {
            for (var i=0; i<buttonGroup.length; i++) {           
               buttonGroup[i].checked = false;            
            }
            buttonGroup.checked = false;
         }
         highlightTRAll(document.getElementById('checkbox1').checked);
      }   
   }
	
	function nextPage(val,stat,pag)
	{
		type='<%=request.getAttribute("type")%>';
		if(<%=request.getAttribute("type")!=null%>)
		{
		 var selectboxdrop=type;
		 var searchboxval='<%=request.getAttribute("search")%>';
		 document.dropInstForm.action="/pix/dropshipinstructionStatus/display.do?type="+selectboxdrop+"&search="+searchboxval+"&PAGE_VALUE="+val+"&statusw="+stat+"&page="+pag;
		 }
		if(<%=request.getAttribute("filter1")!=null%>)
		{
		filter=<%=request.getAttribute("filter1")%>;	
	    document.dropInstForm.action="/pix/dropshipinstructionStatus/display.do?PAGE_VALUE="+val+"&filter="+filter+"&statusw="+stat+"&page="+pag;
		}
		if(<%=request.getAttribute("type")==null && request.getAttribute("filter1")==null %>)
		{
		 document.dropInstForm.action="/pix/dropshipinstructionStatus/display.do?PAGE_VALUE="+val+"&statusw="+stat+"&page="+pag;
		}
		document.dropInstForm.submit();
	}
	

function searchValue(param){
     
     var selectboxdrop=escape(document.getElementById("quickfindSelectbox").value);
     var searchboxval=document.getElementById("quickfindTxtbox").value;
     	if(searchboxval.length>0)
    		 {
			document.dropInstForm.action="/pix/dropshipinstructionStatus/display.do?PAGE_VALUE=1&type="+selectboxdrop+"&search="+searchboxval;
			document.dropInstForm.submit();
			}else{
			alert("Please Enter Search Criteria");
			return false;
			}
	}
function resetSearch(param){
     		document.dropInstForm.action="/pix/dropshipinstructionStatus/display.do?PAGE_VALUE=1";
			document.dropInstForm.submit();
			}
function downloadPackingSlip()
 { 
 var counter=-1;
 var validateCount=-1;
 var urlCustom='/pix/dropship/action/ajax.do?checkboxvalues='; 
 var selectedRowId = '';
	 $('input[name=checkIndex]:checked').each(function(i) {
             selectedRowId+=$(this).val()+",";
             counter++;
            });
		var newUrl=urlCustom+selectedRowId;
		if(counter < 0)
		{
		alert('Please select a Record(s) to Download');
		return false;
		}
      window.location.href = newUrl;
 }

</script>
</head>

<body class="BodyStyle">
<html-el:form action="/dropshipinstructionStatus/display.do" styleId="dropInstForm">
<tr>
    <td height="20" align="left" valign="top" class="paddingSmallT paddingSmallR">
    	<span class="floatLeft headingText padding1px"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"> Dropship Instruction
      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	&nbsp;&nbsp;&nbsp;&nbsp;
      	<span style="display:inline;text-align:right">
     
      	<span class="LinkGreen font10" style="padding:0px"><strong>QUICK FIND</strong>:</span>
        <% String selected ="selected" ;%>
        <html-el:select property="quickfindSelectbox" styleId="quickfindSelectbox" styleClass="textfield" style="width:125px;">
        <html-el:option value="po">ORDER</html-el:option>
        <html-el:option value="isbn">ISBN-10/ISBN-13</html-el:option>
        <html-el:option value="bk">BK #</html-el:option>
        
        </html-el:select>
        
        <html-el:text property="quickfindTxtbox" styleId="quickfindTxtbox" styleClass="textfield" maxlength="13"></html-el:text>
        <c:set var="btnlbl" value="Reset"></c:set>
        <input type="button" class="buttonMain" value="go" style="width:30px; margin:0 5px 0 -3px;height:18px"  onclick="javascript: return searchValue('GO');"/>
        <input type="button" class="buttonMain" name="reset" value=" Reset" 
        style="margin-left:0px;height:18px;" onclick="javascript: return resetSearch('Reset');" />
      </span>
    </span>
    </td>
  </tr>

  
 
  <tr>
    <td align="left" valign="top" class="padding23">
    	
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="1" colspan="2" class="tableLine"></td>
      </tr>
      <tr>
        <td align="right" valign="bottom">
          <img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8">
          <a href="<%=request.getContextPath()%>/dropshipinst/dropInstFilter.do?pageFilter=Y" class="subLinks">filter</a>
          
        </td>
       </tr>
      </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
      <c:choose>
        <c:when test="${dropInstForm.dropshipdto.dropInstListSize gt 0}">
        
        <tr>
          <td height="1" colspan="13" class="tableLine"></td>
        </tr>
        <tr>
	      <td width="14" align="center" class="tableHeading"></td>
	      <td width="14" align="center" class="tableHeading"></td>
          <td width="2%" align="center" class="tableHeading">
          <input type="checkbox" name="groupCheckbox" id="checkbox1" onclick="selectAllCheckBoxes(document.dropInstForm.checkIndex);">
          </td>
          <td class="tableHeading">BK#</td>
          <!-- <td class="tableHeading">DOC CTRL#</td> -->
          <td class="tableHeading">ISBN</td>
          <td class="tableHeading">CUST ACCOUNT NAME</td>
          <td class="tableHeading">ORDER NUMBER</td>
          <td class="tableHeading">VENDOR</td>
          <td class="tableHeading">VENDOR CONTACT</td>
          <td class="tableHeading">CARRIER &amp; LEVEL</td>
          <td class="tableHeading">INSTRUCTION DATE</td>
          <td class="tableHeading">QUANTITY</td>
          <td class="tableHeading">STATUS</td>
        </tr>
        <c:set var="count" value="0"></c:set>
        
       
        
         <c:forEach var="dropInstDtoList"  items="${dropInstForm.dropshipdtolist}" varStatus="indexId" >
        <tr>
        <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         <c:set var="class2" value="tableRowlink"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         <c:set var="class2" value="tableAlternateRowlink"/>
	         </c:if>
	         
        <td align="center" class="${class1}">
          <c:if test="${dropInstDtoList.status eq 'Amended'}" >	<span style="color:orange;font-weight:bold;font-size:18px; !important;">*</span> </c:if>
          </td>
		  <td align="center" class="${class1}">          	
          <c:if test="${dropInstDtoList.pkgSlipFlag eq 'Y'}" > <img src="<%=request.getContextPath()%>/images/right_rfs.gif" width="14" height="14" border="0"></img></c:if>
          </td>
          <td align="center" class="${class1}"><label>
          <html-el:checkbox property="checkIndex" styleId="${dropInstDtoList.ds_Id}" value="${indexId.index}"></html-el:checkbox>
          
          </label></td>
          <td class="${class1}">${dropInstDtoList.bkNumber}</td>
          <!-- <td class="${class1}">${dropInstDtoList.docCtrlNo}</td> -->
          <td class="${class1}">${dropInstDtoList.isbnNo}</td>
          <td class="${class1}">${dropInstDtoList.custAccountName}</td>
          <td class="${class1}">${dropInstDtoList.poNumber}</td>
          <td class="${class1}">${dropInstDtoList.vendor}</td>
          <td class="${class1}">${dropInstDtoList.vendorContact}</td>
          <td class="${class1}">${dropInstDtoList.carrierLevel}</td>
          <td class="${class1}"><fmt:formatDate value="${dropInstDtoList.instructionDate}" type="both" pattern="MM/dd/yyyy" /></td>
          <td class="${class1}" align="right">${dropInstDtoList.totalQty}</td>
          <td class="${class1}">${dropInstDtoList.status}</td>
          </tr>
          <c:set var="lastrowid" value="${dropInstDtoList.rn}"></c:set> 
          </c:forEach>
        
        <tr>
          <td height="1" colspan="13" class="tableLine"></td>
        </tr>
      </table>
       <table width="98%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td align="right">
            	<c:if test="${prevValue!=null && prevValue!=''}">
            		<a href="javascript:void(0);" class="backNext_append" 
            		onclick="javascript: return nextPage(${prevValue},'<c:out value="${STATUS_ID}"/>','<c:out value="${PAGE}"/>');"><img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0" /></a>
	          </c:if>
	           <c:if test="${nextValue!=null && nextValue!=''}">
	           	   <a href="javascript:void(0);" class="backNext_append" 
	           	   onclick="javascript: return nextPage(${nextValue},'<c:out value="${STATUS_ID}"/>','<c:out value="${PAGE}"/>');"><img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0" /></a>
	            </c:if>
            </td>
            </tr>
            <tr>
            	<td>
			           <div id="DwnldPakSlip_div">
			           <br/>
			           <br/>
						<c:if test="${haveWriteAccess}">
							 <input name="Button" id="DwnldPakSlip" type="button" class="buttonMain" value="Download Packaging Slip" style="width:170px" onclick="downloadPackingSlip();" />
						</c:if>
						<c:if test="${!haveWriteAccess}">
							 <input name="Button" id="DwnldPakSlip" type="button" class="buttonMain" disabled="disabled" value="Download Packaging Slip" style="width:170px"  />
						</c:if>
						</div>
            	</td>
            </tr>
            <table>
            <tr>
            <td height="5" colspan="1">&nbsp;</td>
          </tr>
       <tr>
       
       <td colspan="2" class="text">Indicators shown in Columns 1 and 2 Respectively.</td>
       </tr>
       <tr>
        <td colspan="1" class="text" style="color:red;"> 
        	<img src="<%=request.getContextPath()%>/images/right_rfs.gif"  alt="Downloaded Packing Slip"> - Already Downloaded Packing Slip. <br>
 			<span style="color:orange;font-weight:bold;font-size:18px; !important;" >*</span> - Resent Packing Slip.<br>
        	<img src="<%=request.getContextPath()%>/images/video1.gif" alt="View Video" width="20" height="18" >
 			- To review a video tutorial of how to use the DropShip screens in PIX.<a href="https://pearsononline.webex.com/pearsononline/lsr.php?AT=pb&SP=MC&rID=26598097&rKey=5630aba4f2ffdc60" target="_blank">Click here.</a> 
        </td>
         </tr> 
         </table>
               </table>
            </c:when>
            
         <c:otherwise>
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage">There is No DropShip Instructions to Display.</td>   
           </tr>
           <tr>
          <td height="1" colspan="8" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>  
       
        </c:otherwise>
       </c:choose>
       
          </table>
        </td>
       </tr>
</html-el:form>
</body>
</html>