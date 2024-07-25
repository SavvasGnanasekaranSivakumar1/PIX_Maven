<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.presentation.orderstatus.action.OrderStatusForm,com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<html-el:form action="/orderStatus/orderStatusNew">
       <%@ include file="/common/formbegin.jsp"%>
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No. ${pono} 
    <c:if test="${rno!=null && rno!='0'}">
    - ${rno}
    </c:if></span></td>
  </tr>
  <tr>
  
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
          
     <tr>
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Status No. <c:out value='<%=request.getAttribute("StatusNo")%>' /></span> </td>
      </tr>
      
           <tr>
        <td height="15" colspan="2"></td>
        </tr>
      
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
    </table>
    <table width="98%" border="0" cellspacing="1" cellpadding="0">
      <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
       <c:if test="${errorMsg == null}"> 
        <tr>
          <td width="2%" class="tableHeading">&nbsp;</td>
          <td width="10%" class="tableHeading">COMPONENT  </td>
          <td width="12%" class="tableHeading">REQUESTED <br>
          DELIVERY DATE </td>
          <td width="12%" class="tableHeading">ESTIMATED <br>
          DELIVERY DATE </td>
          <td width="15%" class="tableHeading">PRESSTIMELINE</td>
          <td width="25%" class="tableHeading">ORDER<br>
          STATUS</td>
          <td class="tableHeading">COMMENTS</td>
        </tr>
        <c:set var="orderStatusDetailPrev" value="" />
        <c:set var="class1" value="tableRow"/>
        <c:forEach var="orderStatusDetail" items="${orderStatusForm.orderStatusCollection}" varStatus="indexId">  
          <c:set var="poLine" value="${orderStatusDetail.poLineNo}"/>
           <c:if test="${poLine%2 != 0}" >
           <c:set var="class1" value="tableRow"/>
           </c:if>
           <c:if test="${poLine%2 == 0}" >
           <c:set var="class1" value="tableAlternateRow"/>
           </c:if>
          
          <c:choose>
          <c:when test="${indexId.index==0 || orderStatusDetailPrev.poLineNo != orderStatusDetail.poLineNo}">
          <tr>
          	<td class="${class1}"><c:out value="${orderStatusDetail.poLineNo}."/></td>
          	<td class="${class1}"><c:out value="${orderStatusDetail.productDescription}"/></td>
          	<td class="${class1}"><c:out value="${orderStatusDetail.requestedDeliveryDate}"/></td>
         	<td class="${class1}"><c:out value="${orderStatusDetail.estimatedDeliveryDate}"/></td>
          	<td width="15%" class="${class1}"><c:out value="${orderStatusDetail.timelineDate}" /></td> 
          	<td width="25%" class="${class1}"><c:out value="${orderStatusDetail.statusDescription}"/></td>
          	<td valign="top" class="${class1}">
          	<textarea class="${class1}" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:250px; text-align:left">
          	${orderStatusDetail.comments}
       <%--		<textarea class="${class1}" readonly="readonly" rows="2" style="background-color:transparent;border:none; overflow:hidden; width:250px;text-align:left">${orderStatusDetail.comments}  --%>
       		</textarea></td>
          </tr>
           </c:when>
           <c:otherwise>
           <tr>
           <td valign="top" class="${class1}"></td>
           <td valign="top" class="${class1}"></td>
           <td valign="top" class="${class1}"></td>
           <td valign="top" class="${class1}"></td>    
             
          <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="15%" class="${class1}"><c:out value="${orderStatusDetail.timelineDate}" /></td> 
              <td width="25%" class="${class1}"><c:out value="${orderStatusDetail.statusDescription}"/></td>
              <td valign="top" class="${class1}">
       		<textarea class="${class1}" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:250px; text-align:left">
       			${orderStatusDetail.comments}
       		</textarea></td>
            </tr>
          </table></td>
          </tr>
          </c:otherwise>
          </c:choose>
          <c:set var="orderStatusDetailPrev" value="${orderStatusDetail}" />
         </c:forEach> 
         </c:if> 
        <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if>
        <tr>
          <td height="1" colspan="7" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
       <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td><label>
           <table cellpadding="0" cellspacing="0">
          <tr>
				<td>
				<input name="Button32" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
				</td>
                </tr>
            </table>
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
       </table></td>
      </html-el:form>
  