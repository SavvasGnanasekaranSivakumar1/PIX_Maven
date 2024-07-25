<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page language="java"%>
		<script language="javascript" type="text/javascript" src="jsp/datetimepicker.js">
</script>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/dropshipinstructionStatus/display.do?filter=true" styleId="dropInstForm">
<%@ include file="/common/formbegin.jsp"%>

<tr>
    <td height="25" align="left" valign="top">
    <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    <span class="headingText">Dropship Instruction - Filter</span></td>
    
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
       <tr><td class="errorMessageText">
    		<div id="error_div" class="errorMessageText"></div>
    	</td></tr>
      <tr>
        <td class="text">You can apply filters using the following attributes. </td>
        </tr>
      
      <tr>
        <td height="15" align="right">&nbsp;</td>
      </tr>
                                      

      <tr>
        <td height="15" align="left" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
            <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          </tr>
          <tr>
            <td colspan="2" class="titleBlue2">BK#:</td>
            <td width="70%" class="inboxdrakBlue2">
            <html-el:text  property="bkNumber" maxlength="13" styleClass="textfield"/></td> 
          </tr> 
          <!-- <tr>
            <td colspan="2" class="titleBlue2">DOC CTRL#:</td>
            <td width="70%" class="inboxdrakBlue2">
            <%-- <input name="textfield" type="text" class="listBoxText"></td> --%>
            <html-el:text  property="docCtrlNo" maxlength="30" styleClass="textfield"/></td> 
          </tr> -->
          
         <tr>
            <td colspan="2" class="titleBlue2">ISBN: </td>
            <td width="70%" class="inboxdrakBlue2">
            <%-- <input name="textfield2" type="text" class="listBoxText"></td> --%>
            <html-el:text property="isbnNo"  maxlength="13" styleClass="textfield"/></td>
          </tr>
          <tr>
            <td colspan="2" class="titleBlue2">CUST ACCOUNT NAME: </td>
            <td width="70%" class="inboxdrakBlue2">
            <%-- <input name="textfield2" type="text" class="listBoxText"></td> --%>
            <html-el:text property="custAccountName"  maxlength="255" styleClass="textfield"/></td>
          </tr> 
          <tr>
            <td colspan="2" class="titleBlue2">ORDER NUMBER: </td>
            <td width="70%" class="inboxdrakBlue2">
            <%-- <input name="textfield2" type="text" class="listBoxText"></td> --%>
            <html-el:text property="poNumber"  maxlength="10" styleClass="textfield"/></td>
          </tr>
         	<tr>
            <td colspan="2" class="titleBlue2">STATUS: </td>
            <td width="70%" class="inboxdrakBlue2" >
            <html-el:select property="statusId"  styleClass="textfield" style="width: 208px;">
            <option value="">------Select------</option>
		    <c:forEach var="dropObj" items="${dropInstForm.status}"  >
				<c:if test="${dropInstForm.statusId==dropObj.statusId}" > 
				<option value="<c:out value='${dropObj.statusId}'/>" selected  ><c:out value="${dropObj.status}"/></option>
				</c:if>
				<c:if test="${dropInstForm.statusId!=dropObj.statusId}" > 
				<option value="<c:out value='${dropObj.statusId}'/>" ><c:out value="${dropObj.status}"/></option>
				</c:if>
			</c:forEach>  			                              
		    </html-el:select>
		</td>
          </tr> 
          <tr>
            <td colspan="2" class="titleBlue2">CARRIER & LEVEL: </td>
            <td width="70%" class="inboxdrakBlue2">
            <%-- <input name="textfield2" type="text" class="listBoxText"></td> --%>
            <html-el:text property="carrierLevel"  maxlength="255" styleClass="textfield"/></td>
          </tr>
          <tr>
            <td colspan="2" class="titleBlue2">PRINTING NUMBER: </td>
            <td width="70%" class="inboxdrakBlue2">
            <%-- <input name="textfield2" type="text" class="listBoxText"></td> --%>
            <html-el:text property="printNo"  maxlength="30" styleClass="textfield"/></td>
          </tr>
         <!--   /Total qty tr
         <tr>
           <td colspan="2" class="titleBlue2">Total QTY </td>
            <td width="70%" class="inboxdrakBlue2">
            <%-- <input name="textfield2" type="text" class="listBoxText"></td> --%>
            <html-el:text property="totalQty" onkeypress="return filtercheck()" maxlength="10" styleClass="textfield"/></td>
          </tr> 
          toal qty tr end/
          -->
         				<%-- <tr>
						<td colspan="2" class="titleBlue2">
							STATUS:
						</td>
						<td class="inboxdrakBlue2">

							<%--<html-el:select property="status" styleClass="textfield">
								<html-el:option value="">Select</html-el:option>
								<c:forEach var="item" items="${poAllStatus}" varStatus="indexId">
									<html-el:option value="${item.statusId}">
										<c:out value="${item.statusDescription}" />
									</html-el:option>
								</c:forEach>
							</html-el:select>
                       
						</td>
					</tr>--%>
          <tr>
            <td width="15%" rowspan="2" class="titleBlue2">POSTED DATE - </td>
            <td class="titleBlue2">START DATE: </td>
            <td class="inboxdrakBlue2">
            <html-el:text size="12" maxlength="20" styleId="demo1" styleClass="textfield" property="startDate" readonly="true"/>
          <%--  <input type="Text" id="demo1" name="start" class="textfield"> --%>
		    <html-el:link href="javascript:NewCal('demo1','MMDDYYYY')">
		     <img src="<%=request.getContextPath()%>/images/cal.gif"
			  width="16" height="16" border="0" alt="Pick a date">
			 </html-el:link>
			 </td>
          </tr>
          <tr>
            <td class="titleBlue2">END DATE: </td>
            <td class="inboxdrakBlue2">
          <html-el:text size="12" maxlength="20" styleId="demo2" styleClass="textfield" property="endDate" readonly="true"/>
        <%--    <input type="Text" id="demo2" name="start" class="textfield"> --%>
                <html-el:link href="javascript:NewCal('demo2','MMDDYYYY')">
                <img src="<%=request.getContextPath()%>/images/cal.gif"
			  width="16" height="16" border="0" alt="Pick a date">
                </html-el:link>
                </td>
          </tr> 
          
          
        </table>
            <table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>
            </table>		
		</td>
      </tr>
      
      <tr>
        <td height="15" align="right" valign="bottom">&nbsp;</td>
      </tr>
    </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
        <tr>
          <td><label>
          <html-el:submit styleClass="buttonMain"  value="Filter"  onclick="javascript: return dateCheck(document.dropInstForm.endDate.value,document.dropInstForm.startDate.value,'>=','Start Date should be ','End Date'); filterval()"/> 
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
      </table>
  </tr>
</html-el:form>



