<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/reports/reportsearching" >

  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Reports </span></td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" class="text">To view the Reports, please search on the following parameters. </td>
      </tr>
    </table>
      <table width="65%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><fieldset class="legendBorder">
            <legend class="legendeTitle">REPORTS SEARCH </legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td height="5"></td>
			  </tr>
			<tr>
              <td align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1">
                <tr>
                  <td height="5"></td>
                  <td></td>
                </tr>
                 <tr>
                  <td width="45%" class="blueColumn">ISBN10/ISBN13 NUMBER:</td>
                  <td align="left" class="lightblueColumn">
                  <input name="isbn10" type="text" class="textfield" maxlength="13"></td>
                <%--  <html-el:text property="isbn10"  size="22" maxlength="13" styleClass="textfield"/></td> --%>
                  </tr>
                <tr>
                  <td class="blueColumn">PURCHASE ORDER NUMBER: </td>
                  <td align="left" class="lightblueColumn">
                  <input name="porderNo" type="text" class="textfield" maxlength="30"></td>
                  <%-- <html-el:text property="porderNo"  size="22" maxlength="13" styleClass="textfield"/></td> --%>
                  
                </tr>
                <tr>
                  <td class="blueColumn">PRINT NUMBER:</td>
                  <td align="left" class="lightblueColumn">
                  <input name="printNo" type="text" onkeypress="return filtercheck()" class="textfield" maxlength="3"></td>
                  <%--<html-el:text property="printNo"   size="22" maxlength="13" styleClass="textfield"/></td> --%>
                  
                </tr>
 
  				<tr>
                     <td class="blueColumn">START DATE:</td>
						<td class="lightblueColumn">
						<html-el:text size="12" property="startDate" styleClass="textfield" styleId="demo1" />
							<a href="javascript:NewCal('demo1','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"> </a>
						</td>
					</tr>
					
					<tr>
					<td class="blueColumn">END DATE:</td>
						<td class="lightblueColumn">
						<html-el:text size="12" property="endDate" styleClass="textfield" styleId="demo2" />
						<a href="javascript:NewCal('demo2','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"> </a>
						</td>
					</tr>						
                
						
              <tr>
            
              <td class="blueColumn">ITEM:</td>
                  <td align="left" class="lightblueColumn" >
                  
                  <html-el:select property="reference.refCode"  styleClass= "textfield"  >
                  <html-el:option value="">select</html-el:option>
                   <html-el:option value="ALL">ALL</html-el:option>
                  <c:forEach var="item" items="${ReportItemName}" varStatus="indexId">
                 <html-el:option value="${item.refCode}"><c:out value="${item.description}"/></html-el:option>
                 </c:forEach>
                   </html-el:select>
				</td>
                </tr> 
          
              </table></td>
            </tr>
            <tr>
              <td height="10"></td>
            </tr>
            
            <tr>
              <td>
               <input name="Button2" type="button" class="buttonMain" onClick="submitReportDetail('<%=request.getContextPath()%>/reports/reportsearching.do')" value="Search">
              </td>
              
            </tr>
            
          </table></fieldset></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
     </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
 </html-el:form> 