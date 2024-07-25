<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page language="java"%>
<%@ page import="com.pearson.pix.presentation.inventory.action.InventoryForm,com.pearson.pix.business.common.PIXConstants,com.pearson.pix.exception.AppException"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>

<c:set var="readOnlyForm" value="F"/>
<c:if test="${InventoryAccessRight=='READ'}">

				<c:set var="readOnlyForm" value="T"/>
			</c:if>
			
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Inventory </span></td>
  </tr>
  <html-el:form action="/inventory/inventorysearching">
<%@ include file="/common/formbegin.jsp"%>
<% String errorMsg=(String)request.getAttribute(PIXConstants.PIX_ERROR); %>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" class="text">To view the Inventory status for a supplied component, please search on the following parameters. </td>
      </tr>
    </table>
      <table width="60%" border="0" cellspacing="0" cellpadding="0">
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
             <td> <input name="Button2" type="button" class="buttonMain" onClick="submitInventoryDetail('<%=request.getContextPath()%>/inventory/inventorysearching.do')" value="Search">
              </td>
            </tr>
            
          </table></fieldset></td>
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="15"></td>
        </tr>
        
         <c:forEach var="lineItem" items="${inventoryForm.inventoryCollection}" varStatus="invLine">
		<c:set var="totalLineItems" value="${invLine.count}"/>
 		</c:forEach>
        <tr>
          <td height="1" class="tableBorderDash"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td height="15"></td>
        </tr>
        <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
      	 <td height="15" class="text">  Following is the Inventory Status for <span class="textBold">ISBN ${inventoryForm.isbn10}</span>. </td>
        
        
      
        <tr><td class="errorMessageText">
    		<div id="error_div" class="errorMessageText"></div>
    	</td></tr>
        <tr>
          <td height="15"></td>
        </tr>
      </table>
      <c:if test="${errorMsg == null}"> 
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td class="tableHeading">PRODUCT ID </td>
          <td class="tableHeading">SUPPLIED COMPONENT </td>
          <td class="tableHeading">QUANTITY</td>
        </tr>
        <tr>
           <c:forEach var="inventoryItem" items="${inventoryForm.inventoryCollection}" varStatus="indexId"> 
          <tr>
          <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         </c:if> 
	      <td class="${class1}"><c:out value="${inventoryItem.productCode}"/>
          <td class="${class1}"><c:out value="${inventoryItem.productDescription}"/>
          <td class="${class1}">
          
          <c:choose>
	    		<c:when test="${readOnlyForm !='T'}">
	    		<html-el:text property="inventoryCollectionIdx[${indexId.index}].stockInHand" value="${inventoryItem.stockInHand}" size="10" styleClass="textfield" maxlength="9" />  
	    	   	</c:when>
				<c:otherwise>
				${inventoryItem.stockInHand}
				</c:otherwise>
			  	</c:choose>	
			  	
          </tr>
          </c:forEach>
        </tr>
        
        <tr>
          <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      </c:if>
      
      <c:if test="${errorMsg != null}">
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td height="1" colspan="8" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>  
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
           
           <tr>
            <td height="1" colspan="8" class="tableLine">
            <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
          	</table>
         </c:if> 	 
              
          
          	
      <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        
    <c:if test="${errorMsg == null }"> 
        <tr>
          <td><label>
          <c:if test="${readOnlyForm !='T'}">
            <input name="Button" type="button" class="buttonMain" onClick="if(validateInv(${totalLineItems})) { submitAction('<%=request.getContextPath()%>/inventory/inventorysave.do',this) } else {return false;}" value="Save">
            </c:if>
			
			<input name="Button2" type="button" class="buttonMain" onClick="submitPlanningCancelAction('<%=request.getContextPath()%>/inventory/inventorysearch.do')" value="Close">
          </label></td>
        </tr>
        </c:if>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
       
        
    
      
   </table>  	
       </td>
  </tr> 
   
  </html-el:form> 
   <html-el:form action="/planning/planninglist.do">
   </html-el:form>
      
   
       
   
  
   
   
  