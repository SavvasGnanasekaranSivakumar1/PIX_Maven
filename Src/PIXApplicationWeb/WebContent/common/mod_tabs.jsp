<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="purchaseorderid"><%=request.getParameter("poid")%></c:set>
<c:set var="purchaseorderversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="releaseno"><%=request.getParameter("rno")%></c:set>
<c:set var="purchaseordernumber"><%=request.getParameter("pono")%></c:set>
<c:set var="ordertype"><%=request.getParameter("order")%></c:set>
<c:choose>
<c:when test="${USER_INFO.roleTypeDetail.roleType=='B'}">
<c:set var="orderType"><%=request.getParameter("orderType")%></c:set>
</c:when>
<c:otherwise><c:set var="orderType"/></c:otherwise>
</c:choose>
<c:set var="page_order_list"><%=request.getParameter("page_order_list")%></c:set>
<c:set var="pageFilter" value='<%=request.getParameter("pageFilter")%>' /> 
<c:set var="ponoFilter" value='<%=request.getParameter("ponoFilter")%>' /> 
<c:set var="isbnFilter" value='<%=request.getParameter("isbnFilter")%>' /> 
<c:set var="printNoFilter" value='<%=request.getParameter("printNoFilter")%>' /> 
<c:set var="statusFilter" value='<%=request.getParameter("statusFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' />
<c:set var="sbBDateFilter" value='<%=request.getParameter("sbBDateFilter")%>' /> 
<c:set var="ebBDateFilter" value='<%=request.getParameter("ebBDateFilter")%>' />  
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="lineItem" value='<%=request.getAttribute("lineItem")%>' /> 
<c:set var="orderPaper" value='<%=request.getAttribute("orderPaper")%>' /> 
<c:set var="detailURL"/>
<c:set var="deliveryURL"/>
<c:set var="orderStatusURL"/>

<c:choose>
	<c:when test="${roleType == 'C'}">
		<c:set var="detailURL" value="/purchaseorder/millorderdetail.do?partyM=M&lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
		<%--<c:set var="deliveryURL" value="/deliverymessage/deliverymessagelist.do?partyM=M&lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
		<c:set var="orderStatusURL" value="/orderStatus/orderStatusList.do?lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
		<c:set var="goodReceiptURL" value="/goodsreceipt/goodsreceiptlist.do?lineItem=${lineItem}&orderPaper=${orderPaper}&"/>--%>
		<%
		if(request.getAttribute("pageModule").toString().equals("purchaseorder")){
		%>
		<tr>
			<td width="11" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
			<td width="90" align="center" class="tabSubBg" >Details</td>
			<td width="11" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
			<td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
			
		</tr>
		<%}
		%>
		  
	</c:when>
	<c:otherwise>
	
		<c:choose>
		   <c:when test="${roleType == 'M'}">
		   		
			   	<c:set var="detailURL" value="/purchaseorder/millorderdetail.do?partyM=M&lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
			   	<%-- 
			   	<c:set var="deliveryURL" value="/deliverymessage/deliverymessagelist.do?partyM=M&lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
			   	--%>
			   	<c:set var="deliveryURL" value="/deliverymessage/deliverymessagemilllist.do?orderPaper=${orderPaper}&fo=paperfo&"/>
			   	<c:set var="orderStatusURL" value="/orderStatus/orderStatusList.do?lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
			   	<c:set var="goodReceiptURL" value="/goodsreceipt/goodsreceiptpapernew.do?paperlist=paperlist&lineItem=${lineItem}&orderPaper=${orderPaper}&roleType=${roleType}&rno=${releaseno}&"/>
		   </c:when>
		   
		   <c:otherwise> 
		   		<c:choose>
		   			<c:when test="${orderPaper=='O'}">
		   				<c:choose>
		   					<c:when test="${roleType == 'B'&& order == 'P'}">
		   						<c:set var="detailURL" value="/purchaseorder/orderdetail.do?lineItem=${lineItem}&orderPaper=${orderPaper}&fo=paperfo&"/>
		   					</c:when>
		   					<c:otherwise>	
		   						<c:set var="detailURL" value="/purchaseorder/millorderdetail.do?partyM=M&lineItem=${lineItem}&orderPaper=${orderPaper}&fo=paperfo&"/>
		   					</c:otherwise>
		   				</c:choose>
		   				<%-- 
		   				<c:set var="deliveryURL" value="/deliverymessage/deliverymessagelist.do?lineItem=${lineItem}&orderPaper=${orderPaper}&fo=paperfo&"/>
		   				--%>
		   				<c:set var="deliveryURL" value="/deliverymessage/deliverymessagemilllist.do?orderPaper=${orderPaper}&fo=paperfo&"/>
		   				<c:set var="goodReceiptURL" value="/goodsreceipt/goodsreceiptpapernew.do?paperlist=paperlist&lineItem=${lineItem}&orderPaper=${orderPaper}&fo=paperfo&"/>
		   			</c:when>
		   			<c:otherwise>
			   			<c:set var="detailURL" value="/purchaseorder/orderdetail.do?lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
			   			<c:set var="deliveryURL" value="/deliverymessage/deliverymessagelist.do?lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
			   			<c:set var="goodReceiptURL" value="/goodsreceipt/goodsreceiptlist.do?lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
			   		</c:otherwise>
			    </c:choose>
			   
			   <c:set var="orderStatusURL" value="/orderStatus/orderStatusList.do?lineItem=${lineItem}&orderPaper=${orderPaper}&"/>
			   
		   </c:otherwise>
		</c:choose>	
		
		
		
		<%
		if(request.getAttribute("pageModule").toString().equals("purchaseorder")){
		%>
		<tr>
			<td width="11" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
			<td width="90" align="center" class="tabSubBg" >Details</td>
			<td width="11" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
			<td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
			<c:if test="${roleType!='M' && orderPaper!='O'}">
				<td width="13" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
				<td width="90" align="center" class="tabSubBg2"><a href="<%=request.getContextPath()%>${orderStatusURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" >Job Status</a></td>
				<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
			</c:if>
			<td width="13" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
			<td width="150" align="center" class="tabSubBg2"><a href="<%=request.getContextPath()%>${deliveryURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" > <c:if test="${roleType=='M'}">New </c:if> Delivery Message</a> </td>
			<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
			<td width="13" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
			<td width="150" align="center" class="tabSubBg2"><a href="<%=request.getContextPath()%>${goodReceiptURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" ><c:if test="${roleType == 'V' && order!='P'}">New </c:if> Goods Receipt</a> </td>
			<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
			
		</tr>
		<%}
		else if(request.getAttribute("pageModule").toString().equals("orderstatus"))
		{
		%>
		<tr>
			<td width="13" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
			<td width="90" align="center" class="tabSubBg2" ><a href="<%=request.getContextPath()%>${detailURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink">Details</a></td>
			<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
			<td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
			<c:if test="${roleType!='M' && orderPaper!='O'}">
				<td width="11" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
				<td width="90" align="center" class="tabSubBg">Job Status</td>
				<td width="11" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
			</c:if>
			<td width="13" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
			<td width="120" align="center" class="tabSubBg2"><a href="<%=request.getContextPath()%>${deliveryURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" >Delivery Message</a> </td>
			<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
			<td width="13" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
			<td width="120" align="center" class="tabSubBg2"><a href="<%=request.getContextPath()%>${goodReceiptURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" >Goods Receipt</a> </td>
			<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		</tr>
		<%}
		else if(request.getAttribute("pageModule").toString().equals("deliverymessage"))
		{
		%>
		<tr>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>		    
		    <td width="90" align="center" class="tabSubBg2" ><a href="<%=request.getContextPath()%>${detailURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${order}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink">Details</a></td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		    <td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
		    <c:if test="${roleType!='M' && orderPaper!='O'}">
			    <td width="13" height="22" align="right" valign="bottom">
			    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
			    
		    	<td width="90" align="center" class="tabSubBg2">
		    	<a href="<%=request.getContextPath()%>${orderStatusURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${order}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" >Job Status</a></td>
		    	<td width="12" height="22" align="left" valign="bottom">
		    		<img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		    </c:if>
		    <td width="12" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
		    <td width="150" align="center" class="tabSubBg"><c:if test="${roleType=='M'}">New </c:if> Delivery Message</td>
		    <td width="11" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
		    <td width="150" align="center" class="tabSubBg2">
		    <a href="<%=request.getContextPath()%>${goodReceiptURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" ><c:if test="${roleType == 'V'}">New </c:if> Goods Receipt</a> </td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		</tr>
		<%}
		else if(request.getAttribute("pageModule").toString().equals("goodsreceipt"))
		{
		%>
		<tr>
			<td width="13" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
			<td width="90" align="center" class="tabSubBg2" ><a href="<%=request.getContextPath()%>${detailURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink">Details</a></td>
			<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
			<td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
			<c:if test="${roleType!='M' && orderPaper!='O'}">
				<td width="13" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
				<td width="90" align="center" class="tabSubBg2"><a href="<%=request.getContextPath()%>${orderStatusURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" >Job Status</a></td>
				<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
			</c:if>
			<td width="13" height="22" align="right" valign="bottom">
			<img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
			<td width="150" align="center" class="tabSubBg2"><a href="<%=request.getContextPath()%>${deliveryURL}poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${ordertype}&PAGE_VALUE=1&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${orderType}" class="tabSubBgLink" ><c:if test="${roleType=='M'}">New </c:if> Delivery Message</a> </td>
			<td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
			<td width="12" height="22" align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>			
			<td width="150" align="center" class="tabSubBg"><c:if test="${roleType == 'V'} ">New </c:if> Goods Receipt</td>
			<td width="11" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
		</tr>
		<%}%>
	</c:otherwise>
</c:choose>