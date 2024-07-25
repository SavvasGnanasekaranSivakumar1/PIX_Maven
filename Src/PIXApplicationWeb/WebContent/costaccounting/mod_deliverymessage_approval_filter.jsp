
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ page language="java"%>


	<tr>
		<td height="25" align="left" valign="top">
			<img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
			<span class="headingText">Delivery Message - Filter</span>
		</td>
	</tr>
	<html-el:form action="/costaccounting/approvallist?PAGE_VALUE=1">
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
							ORDER NUMBER:
						</td>
						<td width="70%" class="inboxdrakBlue2">
							<html-el:text property="poNo" styleClass="textfield" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="titleBlue2">
							MATERIAL NUMBER:
						</td>

						<td width="70%" class="inboxdrakBlue2">
							<html-el:text property="materialNo"   styleClass="textfield" />
						</td>

					</tr>
					<tr>
						<td colspan="2" class="titleBlue2">
							DELIVERY MESSAGE NUMBER:
						</td>

						<td width="70%" class="inboxdrakBlue2">
							<html-el:text property="deliverymessageNo"   styleClass="textfield" />
						</td>

					</tr>
					
					
					<tr>
						<td colspan="2" class="titleBlue2">
							MILL/MERCHANT:
						</td>
						<td class="inboxdrakBlue2">

							<html-el:select property="millMerchant" styleClass="textfield">
								<html-el:option value="">Select</html-el:option>
								<c:forEach var="item" items="${costAccountingForm.millMerchantVector}" varStatus="indexId">
									<html-el:option value="${item}">
										<c:out value="${item}" />
									</html-el:option>
								</c:forEach>
							</html-el:select> 
							
							
						</td>
					</tr>
					
					<tr>
						<td colspan="2" class="titleBlue2">
							STATUS:
						</td>
						<td class="inboxdrakBlue2">

							<html-el:select property="status" styleClass="textfield">
								<html-el:option value="">Select</html-el:option>
								<c:forEach var="item" items="${costAccountingForm.statusVector}" varStatus="indexId">
									<html-el:option value="${item.description}">
										<c:out value="${item.description}" />
									</html-el:option>
								</c:forEach>
							</html-el:select> 
							
							
						</td>
					</tr>
					
					
					<tr>
					<td width="17%" rowspan="2" class="titleBlue2">CREATION DATE - </td>
						<td class="titleBlue2">START DATE:</td>
						<td class="inboxdrakBlue2">
						<html-el:text size="12" property="startDate" styleClass="textfield" styleId="demo3" />
						<a href="javascript:NewCal('demo3','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"> </a>
						</td>
					</tr>
					<tr>
					<td class="titleBlue2">END DATE:</td>
						<td class="inboxdrakBlue2">
						<html-el:text size="12" property="endDate" styleClass="textfield" styleId="demo4" />
						<a href="javascript:NewCal('demo4','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"> </a>
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
							<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
							<!-- 	<html-el:submit property="submit" value="Filter" styleClass="buttonMain"  onclick="javascript: return sfilter('${contextPath}/planning/planninglist.do?PAGE_VALUE=1');filtercheck();"/>  -->
								<input name="Button3" type="button" class="buttonMain" onClick="javascript:listfilter('${contextPath}/costaccounting/approvallist.do?PAGE_VALUE=1');" value="Filter">
								
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

