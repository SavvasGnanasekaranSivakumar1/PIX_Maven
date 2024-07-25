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
<c:set var="msgNo"><%=request.getParameter("msgNo")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("productcode")%></c:set>   
<c:set var="poId"><%=request.getAttribute("poId")%></c:set>  <%-- from GR Command --%>
<c:set var="productCode"><%=request.getAttribute("productCode")%></c:set>
<c:set var="msgId"><%=request.getAttribute("msgId")%></c:set>

<c:set var="listSize" value='${fn:length(goodsReceiptForm.dmDtlList)}'/>
<%-- 
 <script>
      alert('${msgId}');
</script> 
--%>
<link href="../css/pixcss.css" rel="stylesheet" type="text/css">

                             <html-el:form action="/goodsreceipt/insertnewgoodsreceipt">
                             <script>
       var count =0 ;
    </script>  
  <c:choose>
  
      <c:when test="${listSize > 0}">
 
                                
                  <table width="100%">
                   <tr>
                        <table width="100%" border="0">
                           <tr>
                                 <td  align="right" valign="middle" class="topLinkPopup"><a href="#" onClick="javascript:window.close();" style="cursor:hand"><font class="textWhite">close </font><img src="../images/close.gif" width="16" height="9" border="0"></a></td>
                                    </tr>
                                </table>
                   </tr>

                              <tr>
                              <c:if test="${listSize > 10}" >
                                    <table width="98.5%" border="0" cellspacing="1" cellpadding="0" >
                              </c:if>
                              
                              <c:if test="${listSize <= 10}" >
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
                              
                              <c:if test="${listSize > 10}" >
                                                 <div style="overflow:auto; height:290px; width:100%">
                                       </c:if>
                              <c:if test="${listSize <= 10}" >
                                                 <div style="overflow:auto; width:100%">
                                       </c:if>
                              
                   

               <%-- --%>        <table width="100%" border="0" cellspacing="1" cellpadding="0" >   
                          
                                 <c:forEach var="dmDtlList" items="${goodsReceiptForm.dmDtlList}" varStatus="indexId">
                                     <c:if test="${indexId.count%2 != 0}" >
                                             <c:set var="class1" value="tableRow"/>
                                             <c:set var="class2" value="tableRowlink"/>
                                       </c:if>
                                       <c:if test="${indexId.count%2 == 0}" >
                                             <c:set var="class1" value="tableAlternateRow"/>
                                             <c:set var="class2" value="tableAlternateRowlink"/>
                                       </c:if>   
                                  <%-- <script>count= eval('<c:out value="${dmDtlList.rollWeight}"></c:out>')+count;</script> --%>   
                                   <tr> 
                                   <%-- <td class="${class1}">    <input name="checkall" type="checkbox" value="checkbox" ></td> --%>  <%--onclick="addDeleteAllIdsFromSetNew(this,document.planningForm.idHidden,document.planningForm.selectedEntry);"--%>
                                      <td width="8%" class="${class1}" align="right">  <c:out value="${dmDtlList.poNo}"></c:out>  </td>
                                       <td width="5%" class="${class1}" align="right"> <c:out value="${dmDtlList.poLineNo}"></c:out>  </td>
                                      <td width="10%" class="${class1}" align="right">  <c:out value="${dmDtlList.productCode}"></c:out>  </td>
                                      <td width="22%" class="${class1}">  <c:out value="${dmDtlList.productDescription}"></c:out>  </td>
                                      <td width="12%" class="${class1}">  <c:out value="${dmDtlList.supplierName}"></c:out>  </td>
                                      <td width="10%" class="${class1}" align="left">  <c:out value="${dmDtlList.orgUnitName}"></c:out>  </td>
                              <%-- <td class="${class1}">  <c:out value="${dmDtlList.orgUnitName}"></c:out>  </td> --%>        
                                      <td width="9%" class="${class1}" align="left">  <c:out value="${dmDtlList.rollNumber}"></c:out>  </td>
                                      <td width="9%" class="${class1}" align="right"> <fmt:formatNumber value="${dmDtlList.delQuantity}" pattern="###,###"/>   </td>
                                      <td width="9%" class="${class1}" align="right">  <fmt:formatNumber value="${dmDtlList.recQuantity}" pattern="###,###"/></td>
                              <%-- <td class="${class1}">  <c:out value="${dmDtlList.status}"></c:out>  </td> --%>        
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
                                          <c:if test="${listSize > 10}" >
                                                <table width="98.5%" border="0" cellspacing="1" cellpadding="0" >
                                          </c:if>
                                          
                                          <c:if test="${listSize <= 10}" >
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
                               <td width="9%" class="tableHeading" align="right"><fmt:formatNumber value="${goodsReceiptForm.totalDelRollWeight}" pattern="###,###"/></td>
                               <td width="9%" class="tableHeading" align="right"><fmt:formatNumber value="${goodsReceiptForm.totalRecRollWeight}" pattern="###,###"/></td>
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
              
              
           </tr>

            </table>  

                  
                  
                  <tr height="20">&nbsp;</tr>
                 
                   </tr>
                  </table>
                  
                  <table>
                  
                        <tr>
                  <td>
           <input name="excel" type="button" class="buttonMain" onClick="submitCancelReceiptPop('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=goodReceiptPaperPop&poid=${poid}&poversion=${poversion}&productCode=${productCode}&msgId=${msgId}&msgNo=${msgNo}&pono=${pono}')" value="Export to Excel">       
                  </td>
                  <td>
                        <input name="Close" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
                  </td>
                  </tr>
                  
                  </table>
                  
      </c:when>
      
      <c:when test="${listSize == 0}">
      <table width="100%">
      <tr> <%--noRecordsMessage--%>
                              <td class="tableHeading" align="center" valign="middle" height="30px"> Currently there are no files to display </td>
      </tr>
      </table>                      
      </c:when>
      
  </c:choose>
  
     </html-el:form>


