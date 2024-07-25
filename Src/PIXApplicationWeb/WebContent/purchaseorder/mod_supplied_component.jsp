<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/purchaseorder/suppliedcomponent">
  <tr>
    <td height="25" align="left" valign="top"><img src="/PIXApplicationWeb/images/heading_icon.gif" width="23" height="9"><span class="headingText">Supplied Components - Order</span></td>
  </tr>      
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" class="text">Following is the Supplied Component Info. </td>
        </tr>
      <tr>

        <td width="50%"></td>
        <td align="right" valign="bottom" height="16"></td>
      </tr>
    </table>
<c:set var="counter" value='0' />
<c:forEach var="lineItem" items="${orderDetailForm.lineItems}" varStatus="lineInfo">    
	<c:if test="${lineItem.suppliedCompCollection != null && not empty lineItem.suppliedCompCollection}">    
      <c:set var="counter" value='${counter+1}' />
      <table width="98%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="50%"  border="0" cellspacing="0" cellpadding="0">
              <tr>        
                <td class="titleBlue"><c:if test="${lineItem.lineNo!=null}">${lineItem.lineNo}.</c:if> ${lineItem.productDescription}</td>
              </tr>
          </table></td>
        </tr>
        
		<c:forEach var="suppliedComp" items="${lineItem.suppliedCompCollection}">        
        <tr>
          <td align="left" valign="top"><table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
              <td class="titleHeading">PRODUCT ID</td>
                <td class="titleHeading">SUPPLIED COMPONENT NAME </td>
                 <td class="titleHeading">QUANTITY</td>
                <td class="titleHeading">UOM</td>
                <td class="titleHeading">COMPONENT DELIVERY DATE</td>
               

                <td width="30px" class="titleHeading">VENDOR DETAILS</td>
              </tr>
              <tr>
                <td  valign="top" class="lightblueColumn">${suppliedComp.productCode}</td>
                <td  valign="top" class="lightblueColumn">${suppliedComp.productDescription}</td>
                <td  valign="top" class="lightblueColumn">${suppliedComp.quantity}</td>
                <td  valign="top" class="lightblueColumn">${suppliedComp.UOMDetail.uom}</td>
                <td  valign="top" class="lightblueColumn">${suppliedComp.shipDate}</td>
                
                <td width="30%" valign="top">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">

                    <tr>
                      <td height="5" class="lightblueColumn"></td>
                    </tr>
                    <tr>
                      <td bgcolor="#F8FBFE" class="headingBlack">${suppliedComp.partyDetail.name1} ${suppliedComp.partyDetail.name2} ${suppliedComp.partyDetail.name3}</td>
                    </tr>
                    <tr>
                      <td class="lightblueColumn"> <br>
	                      ${suppliedComp.partyDetail.address1} ${suppliedComp.partyDetail.address2}<br>
	                      <c:if test="${suppliedComp.partyDetail.address3!=null && suppliedComp.partyDetail.address4!=null}">${suppliedComp.partyDetail.address3} ${suppliedComp.partyDetail.address4}<br></c:if>
	                      ${suppliedComp.partyDetail.city} ${suppliedComp.partyDetail.postalCode} ${suppliedComp.partyDetail.state} ${suppliedComp.partyDetail.countryDetail.countryName}</td>
                    </tr>
                    <tr>
                      <td height="5" class="lightblueColumn"></td>
                    </tr>
			<c:forEach var="contact" items="${suppliedComp.partyDetail.contactCollection}">
				<c:set var="contactVendor" value="${contact}"/>
                    <tr>
                      	<td class="lightblueColumn">Attention - <br>
							&nbsp;${contactVendor.contactFirstName} ${contactVendor.contactLastName}<br>
							<c:if test="${contactVendor.phone!=null}">&nbsp;${contactVendor.phone} (Business)<br></c:if>
							<c:if test="${contactVendor.mobile!=null}">&nbsp;${contactVendor.mobile} (Mob)<br></c:if>
							<c:if test="${contactVendor.fax!=null}">&nbsp;${contactVendor.fax} (Fax)<br></c:if>
							<c:if test="${contactVendor.email!=null}">&nbsp;${contactVendor.email} <br></c:if>
						</td>
                    </tr>
			
                    <tr>
                      <td height="4" class="lightblueColumn"></td>
                    </tr>
			</c:forEach>
                  </table>
               </td>
              </tr>
              <tr>
                <td colspan="6" valign="top" height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif"></td>
              </tr>
              <tr>
                <td colspan="6" align="left" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                    <tr>

                      <td width="18%" class="greyColumn">SUPPLIED COMPONENT NOTES: </td>
                      <td class="greyColumn">
                      <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px;text-align:left">  
${suppliedComp.pubUnitComments}
 </textarea>
                       </td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td colspan="6" valign="top"  height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>

          </table></td>
        </tr>
        <tr>
          <td height="16"></td>
        </tr>
		</c:forEach>        
      </table>
    </c:if>  
</c:forEach>
<c:if test="${counter==0}">
	<c:set var="errorMsg" value='The Supplied Components are not available for this Purchase Order.' />
	<table width="98%"  border="0" cellspacing="1" cellpadding="0">
	<tr>
    	<td height="1" colspan="5" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
  	</tr>
	<tr> 
		<td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
	</tr>
	<tr>
    	<td height="1" colspan="5" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
  	</tr>
	</table>
</c:if>      
      <table>
        <tr>
          <td><label>
            <input name="Button3" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
          </label></td>

        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
      </table>
      </td>
  </tr>
</html-el:form>