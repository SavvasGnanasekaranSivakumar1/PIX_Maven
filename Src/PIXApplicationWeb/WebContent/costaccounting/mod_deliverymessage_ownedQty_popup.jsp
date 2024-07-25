<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>

<head>
	<script src="<%=request.getContextPath()%>/js/prototype.js" type="text/javascript"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/flexigrid.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/flexigrid/flexigrid.css" />

	<script type="text/javascript">
		function submitCancelReceiptPop(path)
		{
			document.goodsReceiptForm.action = path;
			document.goodsReceiptForm.submit();
		}
	</script>
</head>

<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="poId"><%=request.getAttribute("poId")%></c:set>  <%-- from GR Command --%>
<c:set var="ownershipMode"><%=request.getAttribute("ownershipMode")%></c:set>  <%-- from GR Command --%>
<c:set var="productCode"><%=request.getAttribute("productCode")%></c:set>
<c:set var="msgId"><%=request.getAttribute("msgId")%></c:set>
<c:set var="pono"><%=request.getAttribute("pono")%></c:set>
<c:set var="msgNo"><%=request.getAttribute("msgNo")%></c:set>
<c:set var="culistSize" value='${fn:length(costAccountingForm.cuPopVector)}'/>
 


<link href="../css/pixcss.css" rel="stylesheet" type="text/css">

		                 <html-el:form action="/goodsreceipt/insertnewgoodsreceipt">
		                 <script>
       var count =0 ;
    </script>  
  <c:choose>
  
  	<c:when test="${culistSize > 0}">
 
                                
                  <table width="100%">
                   <tr>
	                  <table width="100%" border="0">
	                     <tr>
	    				   <td  align="right" valign="middle" class="topLinkPopup"><a href="#" onClick="javascript:window.close();" style="cursor:hand"><font class="textWhite">close </font><img src="../images/close.gif" width="16" height="9" border="0"></a></td>
	  					</tr>
	  				  </table>
                   </tr>

					<tr>
					
						<c:if test="${culistSize > 10}" >
						<table width="98.5%" border="0" cellspacing="1" cellpadding="0" >
					</c:if>
					
					<c:if test="${culistSize <= 10}" >
					<table width="100%" border="0" cellspacing="1" cellpadding="0" >
					</c:if>
					
					
						<tr>
		                   <td width="8%" class="tableHeading">PO #</td>
		                   <td width="5%" class="tableHeading">PO LINE#</td>
		                   <td width="10%" class="tableHeading">L4 MATERIAL#</td>
		                   <td width="22%" class="tableHeading">MATERIAL DESCRIPTION</td>
		                   <td width="12%" class="tableHeading">MILL/MERCHANT</td>
		                   <td width="10%" class="tableHeading">PRINTER</td>
		                   <td width="9%" class="tableHeading">ROLL #</td>
		                   <td width="9%" class="tableHeading">DELIVERED QTY</td>
		                   <td width="9%" class="tableHeading">RECEIVED QTY</td>
		                 </tr>
						</table>
					<tr>

                   <tr>
                   
                   
                   <c:if test="${culistSize > 10}">
					                   <div style="overflow:auto; height:290px; width:101.5%">
					         </c:if>
					<c:if test="${culistSize <= 10}">
					                   <div style="overflow:auto; width:101.5%">
					         </c:if>
                   
                   
               <%--     <div style="overflow:auto; height:100; width:100%">  --%>
	                 <table width="98.5%" border="0" cellspacing="1" cellpadding="0" >
		              
				         <c:forEach var="cuList" items="${costAccountingForm.cuPopVector}" varStatus="indexId">
				             <c:if test="${indexId.count%2 != 0}" >
						         <c:set var="class1" value="tableRow"/>
						         <c:set var="class2" value="tableRowlink"/>
					         </c:if>
					         <c:if test="${indexId.count%2 == 0}" >
						         <c:set var="class1" value="tableAlternateRow"/>
						         <c:set var="class2" value="tableAlternateRowlink"/>
					         </c:if>
					        
					         <tr>
					         <td width="8%" class="${class1}" align="right"> <c:out value="${cuList.cuPono}"></c:out> </td>
				               <td width="5%" class="${class1}" align="right"><c:out value="${cuList.cuPoLineNo}"></c:out> </td>
				              <td width="10%" class="${class1}" align="right"><c:out value="${cuList.cuProductCode}"></c:out>  </td>
				              <td width="22%" class="${class1}"> <c:out value="${cuList.cuProductDescription}"></c:out> </td>
				              <td width="12%" class="${class1}"> <c:out value="${cuList.cuSupplierName}"></c:out> </td>
				              <td width="10%" class="${class1}" align="left"> <c:out value="${cuList.cuPrinter}"></c:out>  </td>
				              <td width="9%" class="${class1}" align="left"> <c:out value="${cuList.cuRollNo}"></c:out> </td>
				              <td width="9%" class="${class1}" align="right"> <fmt:formatNumber value="${cuList.cuDeliveredQty}" pattern="###,###"/></td>
				              <td width="9%" class="${class1}" align="right"> <fmt:formatNumber value="${cuList.cuReceivedQty}" pattern="###,###"/> </td>
					            
				          <%--     <td width="50%" class="${class1}" align="right">  <c:out value="${cuList.cuRollNumber}"></c:out>  </td>
				               <td width="50%" class="${class1}" align="right">- <c:out value="${cuList.cuQuantity}"></c:out>  </td>
				               --%>
				           </tr>
				                 
    		<script>
    			count= eval('<c:out value="${dmDtlList.quantity}"></c:out>')+count;
         </script>
				         </c:forEach>
				      <%--    <tr>
		                   <td width="10%" class="tableHeading">TOTAL</td>
		                   <td width="6%" class="tableHeading"></td>
		                   <td width="10%" class="tableHeading"> </td>
		                   <td width="22%" class="tableHeading">&nbsp;</td>
		                   <td width="12%" class="tableHeading">&nbsp;</td>
		                   <td width="10%" class="tableHeading">&nbsp;</td>
		                   <td width="9%" class="tableHeading">&nbsp;</td>
		                   <td width="9%" class="tableHeading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${goodsReceiptForm.totalDelRollWeight}" pattern="###,###"/></td>
		                   <td width="9%" class="tableHeading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${goodsReceiptForm.totalRecRollWeight}" pattern="###,###"/></td>
		                 </tr>
				           --%>
	       	          </table>
	       	          
	       	          
	       	          
                  </div>

 					                        <table ><tr height="40"></tr>
  		<tr>  
    		
                                          <c:if test="${culistSize > 10}" >
                                                <table width="98.5%" border="0" cellspacing="1" cellpadding="0" >
                                          </c:if>
                                          
                                          <c:if test="${culistSize <= 10}" >
                                                <table width="100%" border="0" cellspacing="1" cellpadding="0" >
                                          </c:if>

						<tr>
		                   <td width="8%" class="tableHeading" align="left">TOTAL</td>
		                   <td width="5%" class="tableHeading"></td>
		                   <td width="10%" class="tableHeading"></td>
		                   <td width="22%" class="tableHeading"></td>
		                   <td width="12%" class="tableHeading"></td>
		                   <td width="10%" class="tableHeading"></td>
		                   <td width="9%" class="tableHeading"></td>
		                   <td width="9%" class="tableHeading" align="right"><fmt:formatNumber value="${costAccountingForm.totalDelRollWeight}" pattern="###,###"/></td>
		                   <td width="9%" class="tableHeading" align="right"><fmt:formatNumber value="${costAccountingForm.totalRecRollWeight}" pattern="###,###"/></td>
		                 </tr> 
						</table>    
    		
    		
          <%--     <td class="tableHeading"> ROLL COUNT:   <fmt:formatNumber value="${listSize}" pattern="###,###"/> 
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>  
          **    <td class="tableHeading"> TOTAL   
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 
            <td class="tableHeading">
                        DELIVERED QUANTITY: <fmt:formatNumber value="${goodsReceiptForm.totalDelRollWeight}" pattern="###,###"/>
              </td>
              <td class="tableHeading">
                        RECEIVED QUANTITY: <fmt:formatNumber value="${goodsReceiptForm.totalRecRollWeight}" pattern="###,###"/> --%>
                      <%-- &nbsp;  <script>document.write(count);</script> --%>  
              
              
      <%--      </tr>  --%>

       	</table>  
                   </tr>
                  </table>
                  
                  <table>
                  <tr>
                <td height="20px"></td></tr>

                  
                   	<tr>
                 <td>
           <input name="excel" type="button" class="buttonMain" onClick="submitCancelReceiptPop('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=cuOwnedPop&pono=${pono}&msgNo=${msgNo}&ownershipMode=${ownershipMode}')" value="Export to Excel">       
                  </td>  
                  <td>
                  	<input name="Close" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
                  </td>
                  </tr>
                  
                  </table>
                  
  	</c:when>
  	
  	<c:when test="${culistSize == 0}">
  	<table width="100%">
  	<tr> <%--noRecordsMessage--%>
  		        		<td class="tableHeading" align="center" valign="middle" height="30px"> Currently there are no files to display </td>
  	</tr>
  	</table>	        		
  	</c:when>
  	
  </c:choose>
  
     </html-el:form>

