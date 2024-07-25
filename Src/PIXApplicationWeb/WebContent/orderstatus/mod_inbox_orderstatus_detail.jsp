
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.presentation.orderstatus.action.OrderStatusForm,com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="purchaseorderid"><%=request.getParameter("poid")%></c:set>
<c:set var="purchaseorderversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="purchaseordernumber"><%=request.getParameter("pono")%></c:set>
<c:set var="releaseno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="days"><%=request.getParameter("days")%></c:set>
<c:set var="home"><%=request.getParameter("home")%></c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 

  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No. ${orderStatusForm.poNo} 
    <c:if test="${orderStatusForm.releaseNo!=null && orderStatusForm.releaseNo!='0'}">
    - ${orderStatusForm.releaseNo}
    </c:if>
    </span></td>
  </tr>
  <tr>
  <html-el:form action="/orderStatus/orderStatusNew">
       <%@ include file="/common/formbegin.jsp"%>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
     <tr>
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Status No. <c:out value="${orderStatusForm.statusNo}"/> </span>. To go back to the job status listing, click on the <span class="textBlue">close</span> button.</td>
      </tr>
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
        <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
       <tr>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
		    <td width="90" align="center" class="tabSubBg2" ><a href="<%=request.getContextPath()%>/purchaseorder/inboxorderdetail.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&days=${days}&home=${home}&rno=${releaseno}&order=P&page=LH" class="tabSubBgLink">Details</a></td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		    <td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="13" height="22"></td>
		    <td width="90" align="center" class="tabSubBg"><span class="tabSubBgLink">Job Status</span></td>
		  <%--  <a href="<%=request.getContextPath()%>/orderStatus/inboxorderStatusList.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=P&days=${days}&home=${home}&PAGE_VALUE=1" class="tabSubBgLink" >Status</a></td> --%>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="12" height="22"></td>
		    <td width="12" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="11" height="22"></td>
		    <td width="120" align="center" class="tabSubBg2">
		    <a href="<%=request.getContextPath()%>/deliverymessage/inboxdeliverylist.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&days=${days}&home=${home}&order=P&PAGE_VALUE=1" class="tabSubBgLink" >Delivery Message</a></td>
		    <td width="11" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="11" height="22"></td>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
		    <td width="120" align="center" class="tabSubBg2">
		    <a href="<%=request.getContextPath()%>/goodsreceipt/inboxgoodsreceiptlist.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&days=${days}&home=${home}&rno=${releaseno}&order=P&PAGE_VALUE=1" class="tabSubBgLink" >Goods Receipt</a> </td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		   </tr>
        </table></td>
        <td align="right" valign="bottom">&nbsp;</td>
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
        <%--  	<td class="${class1}"><c:out value="${orderStatusDetail.comments}"/></td> --%>
        <td valign="top" class="${class1}">
        <textarea class="${class1}" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:250px; text-align:left">
        ${orderStatusDetail.comments}
      <%--  <textarea class="${class1}" readonly="readonly" rows="2" style="background-color:transparent;border:none;overflow:hidden; height:100%;width:250px;text-align:left">  --%>
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
          <td id="buttons2" class="tabSelectTextleft">
          
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/orderStatus/inboxorderStatusList.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${order}&days=${days}&home=${home}&PAGE_VALUE=${PAGE_VALUE}')" value="Close">
          <input name="Button3" type="button" class="buttonMain" onClick="submitStatusAction('<%=request.getContextPath()%>/pdf/orderstatuspdf.do',this)" value="Export PDF">
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
  </tr>