<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ page language="java"%>
<script>
function filtercheckNew()
{
if(!(event.keyCode >= 48 && event.keyCode <= 57))
{
 return false;
}
}
</script>


	<tr>
		<td height="25" align="left" valign="top">
			<img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
			<span class="headingText">Furnished Orders - Filter</span>
			
		</td>
	</tr>
	 
	<html-el:form action="/purchaseorder/furnishedorderlist">
		<tr>
			<td align="left" valign="top" class="padding23">
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="text">
							You can filter the purchase orders based on the following attributes. 
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
							<html-el:text property="poHeader.poNo" styleClass="textfield" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="titleBlue2">
							ISBN/MATERIAL NUMBER:
						</td>
						<td width="70%" class="inboxdrakBlue2">
							<html-el:text property="poHeader.titleDetail.isbn10" styleClass="textfield" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="titleBlue2">
							PRINT NUMBER:
						</td>

						<td width="70%" class="inboxdrakBlue2">
							<html-el:text property="poHeader.titleDetail.printNo" onkeypress="return filtercheckNew()" styleClass="textfield" />
						</td>

					</tr>
					
					<input type="hidden" name="PAGE_VALUE" value="1"/>
					<input type="hidden" name="party" value="S"/>
					<input type="hidden" name="page" value="C"/>
					
				 <tr>
                   <td width="17%" rowspan="2" class="titleBlue2">ORDER POSTED DATE - </td>
              	    <td class="titleBlue2">START DATE:</td>
						<td class="inboxdrakBlue2">
						<html-el:text property="startDate" styleClass="textfield" styleId="demo1" />
							<a href="javascript:NewCal('demo1','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"> </a>
						</td>
					</tr>
					
					<tr>
					<td class="titleBlue2">END DATE:</td>
						<td class="inboxdrakBlue2">
						<html-el:text property="endDate" styleClass="textfield" styleId="demo2" />
						<a href="javascript:NewCal('demo2','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"> </a>
						</td>
					</tr>
			        
			     
	 	
           	</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td height="1" colspan="3" class="tableLine">
							<%-- <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"> --%>
							
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
								<html-el:submit property="submit" value="Filter" styleClass="buttonMain" onclick="javascript: return dateCheck(document.orderListForm.endDate.value,document.orderListForm.startDate.value,'>=','Start Date should be ','End Date');"/>
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
