
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
   <tr>
		<td height="25" align="left" valign="top">
			<img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
			<span class="headingText">Usage - Filter</span>
		</td>
	</tr>
	<html-el:form action="/usage/usageList">
		<tr>
			<td align="left" valign="top" class="padding23">
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="text">
							You can filter the usage for purchase orders based on the following attributes.
						</td>
					</tr>
					<tr>
						<td align="right" valign="bottom">
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="98%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td height="1" colspan="3" class="tableLine"></td>
					</tr>
                     <tr>
						<td colspan="2" class="titleBlue2">
							ORDER NUMBER: 
						</td>
						<td width="70%" class="inboxdrakBlue2">
							<html-el:text property="usage.poNo" styleClass="textfield" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="titleBlue2">
							ISBN:
						</td>
						<td width="70%" class="inboxdrakBlue2">
							<html-el:text property="usage.titleDetail.isbn10" styleClass="textfield" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="titleBlue2">
							PRINT NUMBER:
						</td>

						<td width="70%" class="inboxdrakBlue2">
							<html-el:text property="usage.titleDetail.printNo" styleClass="textfield" />
						</td>
					</tr>
					<%-- <tr>
						<td colspan="2" class="titleBlue2">
							STATUS:
						</td>
						<td class="inboxdrakBlue2">
 							<html-el:select property="usage.statusDetail.statusId" styleClass="textfield">
								<html-el:option value="">Select</html-el:option>
								<c:forEach var="usageStatus" items="${UsageStatus}" varStatus="indexId">
									<html-el:option value="${usageStatus.statusId}">
										<c:out value="${usageStatus.statusDescription}" />
									</html-el:option>
								</c:forEach>
							</html-el:select>
                       </td>
					</tr> --%>
				 <tr>
                   <td width="17%" rowspan="2" class="titleBlue2">POSTED  DATE - </td>
              	    <td class="titleBlue2">START DATE:</td>
						<td class="inboxdrakBlue2">
						<html-el:text property="startDate" styleClass="textfield" size="12" styleId="demo1" />
							<a href="javascript:NewCal('demo1','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"> </a>
						</td>
					</tr>
					<tr>
					<td class="titleBlue2">END DATE:</td>
						<td class="inboxdrakBlue2">
						<html-el:text property="endDate" styleClass="textfield" size="12" styleId="demo2" />
						<a href="javascript:NewCal('demo2','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"> </a>
						</td>
					</tr>
			  </table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td height="1" colspan="3" class="tableLine">
							<input type="hidden" name="PAGE_VALUE" value="1"/>
							<input type="hidden" name="party" value="V"/>
							<input type="hidden" name="page" value="C"/>
						</td>
					</tr>
				</table>
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
     				<tr>
						<td width="45%" height="15" align="left" class="subLinksMain"></td>
						<td height="10" align="right"></td>
					</tr>
					<tr>
						<td colspan="2">
							<label>
								<html-el:submit property="submit" value="Filter" styleClass="buttonMain" onclick="javascript: return dateCheck(document.usageFormList.endDate.value,document.usageFormList.startDate.value,'>=','Start Date should be ','End Date');"/>
							</label>
						</td>
					</tr>
					<tr>
						<td height="35" colspan="2">
							&nbsp;
						</td>
					</tr>
				</table>
			</tr>
	</html-el:form>


