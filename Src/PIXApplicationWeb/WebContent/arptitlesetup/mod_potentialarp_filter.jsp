<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ page language="java"%>


<tr>
	<td height="25" align="left" valign="top">
		<img src="<%=request.getContextPath()%>/images/heading_icon.gif"
			width="23" height="9">
		<span class="headingText">Potenial ARP - Filter</span>
	</td>
</tr>
<html-el:form action="/potentialarp/potentialarplist?PAGE_VALUE=1">

	<tr>
		<td align="left" valign="top" class="padding23">
			<table width="98%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="text">
						You can apply filters based on the following attributes.
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
					<td height="1" colspan="3" class="tableLine">
						<%-- <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"> --%>

					</td>
				</tr>

				<tr>
					<td colspan="2" class="titleBlue2">
						ISBN:
					</td>
					<td width="70%" class="inboxdrakBlue2">
						<html-el:text property="detailArp.potentialarpDetail.titleIsbn"
							styleClass="textfield" />
					</td>
				</tr>
				<tr>
					<td colspan="2" class="titleBlue2">
						AUTHOR:
					</td>

					<td width="70%" class="inboxdrakBlue2">
						<html-el:text property="detailArp.potentialarpDetail.author"
							styleClass="textfield" />
					</td>

				</tr>
				<tr>
					<td colspan="2" class="titleBlue2">
						EDITION:
					</td>

					<td width="70%" class="inboxdrakBlue2">
						<html-el:text property="edition"
							styleClass="textfield" />
					</td>

				</tr>
				<tr>
					<td colspan="2" class="titleBlue2">
						BUYER:
					</td>

					<td width="70%" class="inboxdrakBlue2">
						<html-el:text property="detailArp.potentialarpDetail.buyerName"
							styleClass="textfield" />
					</td>

				</tr>
				<tr>
					<td colspan="2" class="titleBlue2">
						COPYRIGHT YEAR:
					</td>

					<td width="70%" class="inboxdrakBlue2">
						<html-el:text
							property="copyRightYear"
							styleClass="textfield" />
					</td>

				</tr>
<!--  
				<tr>
					<td colspan="2" class="titleBlue2">
						STATUS:
					</td>
					<td class="inboxdrakBlue2">

						<html-el:select
							property="detailArp.potentialarpDetail.titleStatus"
							styleClass="textfield">
							<html-el:option value="">Select</html-el:option>
							<c:forEach var="item" items="${potentialARPAllStatus}"
								varStatus="indexId">
								<html-el:option value="${item.id}">
									<c:out value="${item.description}" />  
								</html-el:option>
							</c:forEach>
						</html-el:select>
					</td>
				</tr> -->
				<tr>
					<td colspan="2" class="titleBlue2">
						FILP WITH CORRECTION:
					</td>
					<td class="inboxdrakBlue2">
						<html-el:select property="statusId"	styleClass="textfield">
							  <html-el:option value="0">Select</html-el:option>
							  <html-el:option value="72">Yes</html-el:option>
							  <html-el:option value="67">No</html-el:option>    
						</html-el:select>
					</td>
				</tr>
				<tr>
					<td width="18%" rowspan="2" class="titleBlue2">
						ARP DUE DATE -
					</td>
					<td class="titleBlue2">
						FROM DATE:
					</td>
					<td class="inboxdrakBlue2">
						<html-el:text size="12" property="arpFromDueDate"
							styleClass="textfield" styleId="demo2" />
						<a href="javascript:NewCal('demo2','MMDDYYYY')"><img
								src="<%=request.getContextPath()%>/images/cal.gif" width="16"
								height="16" border="0" alt="Pick a date"> </a>
					</td>
				</tr>
				<tr>
					<td class="titleBlue2">
						TO DATE:
					</td>
					<td class="inboxdrakBlue2">
						<html-el:text size="12" property="arpToDueDate"
							styleClass="textfield" styleId="demo3" />
						<a href="javascript:NewCal('demo3','MMDDYYYY')"><img
								src="<%=request.getContextPath()%>/images/cal.gif" width="16"
								height="16" border="0" alt="Pick a date"> </a>
					</td>
				</tr>

				<tr>
					<td width="18%" rowspan="2" class="titleBlue2">
						INVENTORY REQUEST DATE -
					</td>
					<td class="titleBlue2">
						FROM DATE:
					</td>
					<td class="inboxdrakBlue2">
						<html-el:text size="12" property="fromReqDate"
							styleClass="textfield" styleId="demo4" />
						<a href="javascript:NewCal('demo4','MMDDYYYY')"><img
								src="<%=request.getContextPath()%>/images/cal.gif" width="16"
								height="16" border="0" alt="Pick a date"> </a>
					</td>
				</tr>
				<tr>
					<td class="titleBlue2">
						TO DATE:
					</td>
					<td class="inboxdrakBlue2">
						<html-el:text size="12" property="toReqDate"
							styleClass="textfield" styleId="demo5" />
						<a href="javascript:NewCal('demo5','MMDDYYYY')"><img
								src="<%=request.getContextPath()%>/images/cal.gif" width="16"
								height="16" border="0" alt="Pick a date"> </a>
					</td>
				</tr>
			</table>
			<table width="98%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td height="1" colspan="3" class="tableLine"></td>
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
							<c:set var="contextPath" value='<%=request.getContextPath()%>' />
							<!-- 	<html-el:submit property="submit" value="Filter" styleClass="buttonMain"  onclick="javascript: return sfilter('${contextPath}/planning/planninglist.do?PAGE_VALUE=1');filtercheck();"/>  -->
							<input name="Button3" type="button" class="buttonMain"
								onClick="javascript: return sfilter('${contextPath}/potentialarp/potentialarplist.do?PAGE_VALUE=1&status=${homeStatus}');filtercheck();"
								value="Filter">

						</label>
					</td>
				</tr>
				<tr>
					<td height="35" colspan="2">
						&nbsp;
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
</html-el:form>

