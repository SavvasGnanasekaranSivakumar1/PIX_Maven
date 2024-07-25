 <%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/bookspecification/bookspecdetail">
<%@ include file="/common/formbegin.jsp"%>
<c:if test="${bookspecform.bookSpec.partyCollection != null && not empty bookspecform.bookSpec.partyCollection}">
<c:set var="hmBookSpecPrev" value="${bookspecform.bookSpecPrev}" scope="page"/>
<c:set var="readOnlyForm" value="F"/>
<c:forEach var="party" items="${bookspecform.bookSpec.partyCollection}" varStatus="partyCount">
	<c:choose>
		<c:when test="${party.partyType=='B'}">
			<c:set var="buyer" value="${party}"/>
			<c:if test="${BookspecAccessRight=='READ'}">
				<c:set var="readOnlyForm" value="T"/>
			</c:if>
			<c:forEach var="contact" items="${buyer.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactBuyer" value="${contact}"/>
				</c:if>
			</c:forEach>
		</c:when>
		<c:when test="${party.partyType=='V'}">
			<c:set var="vendor" value="${party}"/>
			<c:set var="partyCounter" value="${partyCount.index}"/>
			<c:forEach var="contact" items="${vendor.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactVendor" value="${contact}"/>
				</c:if>
			</c:forEach>
		</c:when>
		</c:choose>
</c:forEach>

</c:if>
 <tr>
    <td height="25" align="left" valign="top">
    <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    <c:if test="${bookspecform.bookSpec.specNo!=null}">
    <span class="headingText">Book Specification No. <c:out value="${bookspecform.bookSpec.specNo}"/> - 
    </c:if>
    <c:if test="${bookspecform.bookSpec.releaseNo != null && bookspecform.bookSpec.releaseNo != '0'}">
    <c:out value="${bookspecform.bookSpec.releaseNo}"/> </span></td>
    </c:if>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
    <a name="topofpage"></a>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
            <tr><td class="errorMessageText">
    		<div id="error_div" class="errorMessageText"></div>
    	</td></tr>
      

      <tr>
        <td height="15" align="left" valign="top">
        <table width="100%"  border="0" cellspacing="1" cellpadding="0">
          
          <tr>
            <td width="10%" class="blueColumn">ISBN 10:	</td>
            <td class="lightblueColumn"><c:out value="${bookspecform.bookSpec.titleDetail.isbn10}"/></td>
            <td width="10%" class="blueColumn">ISSUE DATE: </td>
            <td class="lightblueColumn"><fmt:formatDate value="${bookspecform.bookSpec.specIssueDate}" type="both" pattern="MM/dd/yy" /></td> 
         </tr>
          <tr>
            <td width="10%" class="blueColumn">ISBN 13: </td>
            <td class="lightblueColumn"><c:out value="${bookspecform.bookSpec.titleDetail.isbn13}"/></td>
            <td width="10%" class="blueColumn" align="left">PRINT NO: </td>
            <td class="lightblueColumn"><c:out value="${bookspecform.bookSpec.titleDetail.printNo}"/></td>
          </tr>
        </table></td>
      </tr>
    </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">

        
        <tr>
          <td align="center" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="45%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">BUYER </legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
              <%--   <c:forEach var="party" items="${bookspecform.bookSpec.partyCollection}" varStatus="indexId"> --%>
                    <td class="headingText12">${buyer.name1} ${buyer.name2} ${buyer.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend">${buyer.address1} ${buyer.address2}<br>
                      ${buyer.address3} ${buyer.address4}<br>
                      ${buyer.city} ${buyer.postalCode} ${buyer.state} ${buyer.countryDetail.countryName}
                    </td>
                      
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactBuyer.contactFirstName} ${contactBuyer.contactLastName}<br>
<c:if test="${contactBuyer.phone!=null}">&nbsp;${contactBuyer.phone} (Business)</c:if><br>
<c:if test="${contactBuyer.mobile!=null}">&nbsp;${contactBuyer.mobile} (Mob)</c:if><br>
<c:if test="${contactBuyer.fax!=null}">&nbsp;${contactBuyer.fax} (Fax)</c:if><br>
<c:if test="${contactBuyer.email!=null}">&nbsp;${contactBuyer.email} </c:if><br>
                    
                  </tr>
                </table>
              </fieldset></td>
              <td width="5%" height="170">&nbsp;</td>
              <td valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">VENDOR (V063) </legend>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${vendor.name1} ${vendor.name2} ${vendor.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend">
                      ${vendor.address1} ${vendor.address2}<br>
                      ${vendor.address3} ${vendor.address4}<br>
                      ${vendor.city} ${vendor.postalCode} ${vendor.state} ${vendor.countryDetail.countryName}</td>
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactVendor.contactFirstName} ${contactVendor.contactLastName}<br>
<c:if test="${contactVendor.phone!=null}">&nbsp;${contactVendor.phone} (Business)</c:if><br>
<c:if test="${contactVendor.mobile!=null}">&nbsp;${contactVendor.mobile} (Mob)</c:if><br>
<c:if test="${contactVendor.fax!=null}">&nbsp;${contactVendor.fax} (Fax)</c:if><br>
<c:if test="${contactVendor.email!=null}">&nbsp;${contactVendor.email} </c:if><br>
                  </tr>
                </table>
              </fieldset><br></td>
              </tr>
          </table>           
<c:if test="${bookspecform.bookSpec.lineItemCollection != null && not empty bookspecform.bookSpec.lineItemCollection}"> 
<c:forEach var="lineItem" items="${bookspecform.bookSpec.lineItemCollection}" varStatus="lineItemCount">
   <c:set var="hmproduct" value="${bookspecform.bookSpecPrev[lineItem.productCode]}" scope="page"/>            
              	
              
               	 
                
              <table width="100%"  border="0" cellspacing="0" cellpadding="0">
              
 			<tr>             
             
              <c:if test="${lineItem.pressDetail!=null}"> 
               
               <c:choose>
                <c:when test="${lineItem.finishedGoodFlag=='Y'}">
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td width="40%" align="left" class="titleFinishedGood">
				<c:out value="${lineItem.specLineNo}"/>&nbsp;<c:out value="${lineItem.productDescription}"/></td>
 			    <td align="left" valign="middle">
                <table width="86" height="15" border="0" cellpadding="0" cellspacing="0">
                <tr>
                <td align="center" valign="middle" class="textFinishedGood">Finished Good </td>
                </tr>
                </table>
                </td>                                    
                </tr>
                </table>
                </c:when>
                <c:otherwise>
                <td><table width="40%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td class="titleBlue"><c:out value="${lineItem.specLineNo}"/>&nbsp;<c:out value="${lineItem.productDescription}"/></td>
                </tr>
                </table> </td>
                </c:otherwise>
             </c:choose>
                
              
                
    
              
             <tr>
                <td align="center" valign="top">
                   
                   
                    <table width="100%"  border="0" cellspacing="1" cellpadding="0">
             <c:forEach var="miscItem" items="${lineItem.miscCollection}" varStatus="miscItemCount">
              <c:set var="Value" value="${hmproduct[miscItem.label]}" scope="page"/>
                       <c:if test="${miscItemCount.index%2==0}">
                       <tr>
                       </c:if> 
                       <c:choose>
	                   <c:when test="${miscItem.value != Value}">
                       <td width="20%" height="0" class="blueColumn"><c:out value="${fn:toUpperCase(miscItem.label)}"/> </td>
                       <td width= "30%" class="lightblueColumnBold"><c:out value="${miscItem.value}"/></td>
                       </c:when>
                       <c:otherwise>
                       <td width="20%" class="blueColumn"><c:out value="${fn:toUpperCase(miscItem.label)}"/> </td>
                       <td width= "30%" class="lightblueColumn"><c:out value="${miscItem.value}" /></td>
                      </c:otherwise>
                       </c:choose>
                         <c:if test="${miscItemCount.index%2==1}">
                       </tr>
                       </c:if>                                    
                      </c:forEach>
 			
                    </table>
                     <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="15"></td>
                    </tr>
                    <tr>
                      <td height="1" class="tableBorderDash"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
                    </tr>
                    <tr>
                      <td height="15"></td>
                    </tr>
                  </table>
                    
                    
              </tr>
            
              </c:if>
             
               
                      
        
            
            
             
  
                    
  <%--condition for nonPressDetail --%>
  	
    <c:if test="${lineItem.nonPressDetail!=null}">
    
   
    		<c:choose>
                <c:when test="${lineItem.finishedGoodFlag=='Y'}">
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td width="40%" align="left" class="titleFinishedGood">
				<c:out value="${lineItem.specLineNo}"/>&nbsp;<c:out value="${lineItem.productDescription}"/></td>
 			    <td align="left" valign="middle">
                <table width="86" height="15" border="0" cellpadding="0" cellspacing="0">
                <tr>
                <td align="center" valign="middle" class="textFinishedGood">Finished Good </td>
                </tr>
                </table>
                </td>                                    
                </tr>
                </table>
                </c:when>
                <c:otherwise>
                <td><table width="40%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td class="titleBlue"><c:out value="${lineItem.specLineNo}"/>&nbsp;<c:out value="${lineItem.productDescription}"/></td>
                </tr>
                </table> </td>
                </c:otherwise>
             </c:choose>
                      
    
   
  
    
                <tr>
                <td align="center" valign="top">
                  <table width="100%"  border="0" cellspacing="1" cellpadding="0">
             <c:forEach var="miscItem" items="${lineItem.miscCollection}" varStatus="miscItemCount">
              <c:set var="Value" value="${hmproduct[miscItem.label]}" scope="page"/>
                       <c:if test="${miscItemCount.index%2==0}">
                       <tr>
                       </c:if> 
                       <c:choose>
	                   <c:when test="${miscItem.value != Value}">
                       <td width="20%" height="0" class="blueColumn"><c:out value="${fn:toUpperCase(miscItem.label)}"/> </td>
                       <td width= "30%" class="lightblueColumnBold"><c:out value="${miscItem.value}"/></td>
                       </c:when>
                       <c:otherwise>
                       <td width="20%" height="0" class="blueColumn"><c:out value="${fn:toUpperCase(miscItem.label)}"/> </td>
                        <td width= "30%" class="lightblueColumn"><c:out value="${miscItem.value}"/></td>
                       </c:otherwise>
                       </c:choose>
                         <c:if test="${miscItemCount.index%2==1}">
                       </tr>
                       </c:if>                                    
                      </c:forEach>
 			
                    </table>
                    
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="15"></td>
                    </tr>
                    <tr>
                      <td height="1" class="tableBorderDash">
                      <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
                    </tr>
                    <tr>
                      <td height="15"></td>
                    </tr>
                  </table>
                  </td>
              </tr>
              </c:if>
            
              
              
            
            
           <%-- condition for bindingDetail --%>
             
  	
	
    			<c:if test="${lineItem.bindingDetail!=null}">
    			
              <c:choose>
                <c:when test="${lineItem.finishedGoodFlag=='Y'}">
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td width="40%" align="left" class="titleFinishedGood">
				<c:out value="${lineItem.specLineNo}"/>&nbsp;<c:out value="${lineItem.productDescription}"/></td>
 			    <td align="left" valign="middle">
                <table width="86" height="15" border="0" cellpadding="0" cellspacing="0">
                <tr>
                <td align="center" valign="middle" class="textFinishedGood">Finished Good </td>
                </tr>
                </table>
                </td>                                    
                </tr>
                </table>
                </c:when>
                <c:otherwise>
                <td><table width="40%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td class="titleBlue"><c:out value="${lineItem.specLineNo}"/>&nbsp;<c:out value="${lineItem.productDescription}"/></td>
                </tr>
                </table> </td>
                </c:otherwise>
             </c:choose>
   
    		
   
              <tr>
                <td align="center" valign="top">
                 
                  <table width="100%"  border="0" cellspacing="1" cellpadding="0">
             <c:forEach var="miscItem" items="${lineItem.miscCollection}" varStatus="miscItemCount">
              <c:set var="Value" value="${hmproduct[miscItem.label]}" scope="page"/>
                       <c:if test="${miscItemCount.index%2==0}">
                       <tr>
                       </c:if> 
                       <c:choose>
	                   <c:when test="${miscItem.value != Value}">
                       <td width="20%" height="0" class="blueColumn"><c:out value="${fn:toUpperCase(miscItem.label)}"/> </td>
                        <td width= "30%" class="lightblueColumnBold"><c:out value="${miscItem.value}"/></td>
                       </c:when>
                       <c:otherwise>
                       <td width="20%" height="0" class="blueColumn"><c:out value="${fn:toUpperCase(miscItem.label)}"/> </td>
                        <td width= "30%" class="lightblueColumn"><c:out value="${miscItem.value}"/></td>
                       </c:otherwise>
                       </c:choose>
                         <c:if test="${miscItemCount.index%2==1}">
                       </tr>
                       </c:if>                                    
                      </c:forEach>
 			
                    </table>
                    
                    
                  
                      
                     
                  
                  
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="15"></td>
                    </tr>
                    <tr>
                      <td height="1" class="tableBorderDash">
                      <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
                    </tr>
                    <tr>
                      <td height="15"></td>
                    </tr>
                  </table>
                  
               </td>
              </tr>
              </c:if>
              
              <c:if test="${lineItem.bindingDetail == null && lineItem.pressDetail == null && lineItem.nonPressDetail == null }">
    			
              <c:choose>
                <c:when test="${lineItem.finishedGoodFlag=='Y'}">
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td width="40%" align="left" class="titleFinishedGood">
				<c:out value="${lineItem.specLineNo}"/>&nbsp;<c:out value="${lineItem.productDescription}"/></td>
 			    <td align="left" valign="middle">
                <table width="86" height="15" border="0" cellpadding="0" cellspacing="0">
                <tr>
                <td align="center" valign="middle" class="textFinishedGood">Finished Good </td>
                </tr>
                </table>
                </td>                                    
                </tr>
                </table>
                </c:when>
                <c:otherwise>
                <td><table width="40%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td class="titleBlue"><c:out value="${lineItem.specLineNo}"/>&nbsp;<c:out value="${lineItem.productDescription}"/></td>
                </tr>
                </table> </td>
                </c:otherwise>
             </c:choose>
             
              <tr>
                <td align="center" valign="top">
             <table width="100%"  border="0" cellspacing="1" cellpadding="0">
             <c:forEach var="miscItem" items="${lineItem.miscCollection}" varStatus="miscItemCount">
              <c:set var="Value" value="${hmproduct[miscItem.label]}" scope="page"/>
                       <c:if test="${miscItemCount.index%2==0}">
                       <tr>
                       </c:if> 
                       <c:choose>
	                   <c:when test="${miscItem.value != Value}">
                       <td width="20%" height="0" class="blueColumn"><c:out value="${fn:toUpperCase(miscItem.label)}"/> </td>
                        <td width= "31%" class="lightblueColumnBold"><c:out value="${miscItem.value}"/></td>
                       </c:when>
                       <c:otherwise>
                       <td width="20%" height="0" class="blueColumn"><c:out value="${fn:toUpperCase(miscItem.label)}"/> </td>
                        <td width= "31%" class="lightblueColumn"><c:out value="${miscItem.value}"/></td>
                       </c:otherwise>
                       </c:choose>
                         <c:if test="${miscItemCount.index%2==1}">
                       </tr>
                       </c:if>                                    
                      </c:forEach>
 			
                    
                    
                   </table>
                   
                   
                   
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="5"></td>
                    </tr>
                    <tr>
                      <td height="1" class="tableLine">
                      <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
                    </tr>
                    <tr>
                      <td height="5"></td>
                    </tr>
                  </table>
                  
                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="15"></td>
                    </tr>
                    <tr>
                      <td height="1" class="tableBorderDash">
                      <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
                    </tr>
                    <tr>
                      <td height="15"></td> 
                    </tr>
                  </table>  
                 </td>
              </tr>  
           </c:if>
       </table>            
                   
                  
                  
              
                  
                                
                    
                
            </c:forEach>
            </c:if>  
             
            </table>
            
            
          
           
                  <table width="98%" border="0" cellspacing="0" cellpadding="0">
                   
                   <tr>
          <td height="15" align="left">
          <input name="Button2" type="button" class="buttonMain" onClick="MM_goToURL('parent','javascript:window.close();');return document.MM_returnValue" value="Close"></td>
        </tr>
                   
                    </table>
                  
                  </html-el:form>
                 
            
          
              
           
   
            
           

            
			
      
   
   
 
 

 
