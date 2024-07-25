<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/inventory/inventorysearching">
<%@ include file="/common/formbegin.jsp"%>


  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Inventory </span></td>
  </tr>
  
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr><td class="errorMessageText">
    		<div id="error_div" class="errorMessageText"></div>
    	</td></tr>
      
      <tr>
        <td height="15" class="text">To view the Inventory status for a supplied component, please search on the following parameters. </td>
      </tr>
    </table>
      <table width="60%" border="0" cellspacing="0" cellpadding="0">
      <tr><td class="errorMessageText">
    		<div id="error_div" class="errorMessageText"></div>
    	</td></tr>
        <tr>
          <td><fieldset class="legendBorder">
            <legend class="legendeTitle">SEARCH INVENTORY STATUS </legend><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td height="5"></td>
			  </tr>
			<tr>
              <td align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1">
                <tr>
                  <td height="5"></td>
                  <td></td>
                </tr>
                <tr><td>
                <tr>
                  <td width="40%" class="blueColumn">ISBN10/ISBN13:</td>
                  <td align="left" class="lightblueColumn">
                  <html-el:text property="isbn10" size="27"  maxlength="13" styleClass="textfield"/>
                </tr>
               <tr>
                  <td class="blueColumn">RAW MATERIAL NUMBER:</td>
                  <td align="left" class="lightblueColumn">
                  <html-el:text property="rawMaterialNo" size="27"  maxlength="13" styleClass="textfield"/>
                </tr>
                <tr>
                  <td class="blueColumn">PRINT NO:</td>
                  <td align="left" class="lightblueColumn">
                  <html-el:text property="printNum" size="27" onkeypress="return filtercheck()" maxlength="3" styleClass="textfield"/>
                </tr>
                <tr>
                  <td class="blueColumn">PARTY NAME:</td>
                  <td align="left" class="lightblueColumn">
                   <html-el:select property="party.san"  styleClass= "textfield" >
						<html-el:option value="">Select</html-el:option>
						<c:forEach var="item" items="${PartyName}" varStatus="indexId">
								<html-el:option value="${item.san}"><c:out value="${item.name1}"/></html-el:option>
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
              <label>
              <input name="Button2" type="button" class="buttonMain" onClick="submitInventoryDetail('<%=request.getContextPath()%>/inventory/inventorysearching.do')" value="Search">
              </label>
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