<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ page import="com.pearson.pix.dto.admin.User"%>

<%@ page language="java"%>
<%@ page import="com.pearson.pix.presentation.home.action.HomePageForm"%>
<script language="javascript" src="<%=request.getContextPath()%>/js/helloform.js"></script>

<c:set var="userid">${userid}</c:set>
<c:set var="otindex" value="0"/>
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />

<c:set var="isDisplay" value="<%=session.getAttribute(\"isDisplay\")%>"/>

  <script language="javascript">

function callSelectedURL2()
{
		var element = document.getElementById("populatedrop");

		var url=element.options[element.selectedIndex].value; 
		if(url != ''){
//			alert("On selection of this application the User will switch to '" + url + "' application URL");
			window.open(url);
			}
		return false;
}

</script>


<%-- 
<html-el:form action="/home/home.do">
                          <tr>
                            <td class="text10size">Switch Application:   
                           <html-el:select property="applicationSwitch" onchange="javascript:submitstatus('${PATH}/home/home.do','${PATH}/purchaseorderstatus.do','${PATH}/planningstatus.do','${PATH}/bookspecificationstatus.do','${PATH}/usagestatus.do')" styleClass="listBoxText" >
							 <html-el:option value="">Select</html-el:option>
							 <html-el:option value="POR" ><c:out value="PEPMS" /></html-el:option>
							 <html-el:option value="PLA"><c:out value="GSR" /></html-el:option>
						   </html-el:select>                             
						   </td>
                          </tr>


<c:if test="${ssopix != null && ssopix != ''}">
Switch To:&nbsp;
<select name="selectedApp" id="populatedrop" onchange="return callSelectedURL2()">
                    <option value="">Select</option>
                  <c:forEach var="item" items="${ssopix}" varStatus="indexId">
         		<option value="${item.applicationURL}"><c:out value="${item.applicationName}"/></option>
                  </c:forEach>
</select>
</c:if>
--%>
    <tr>
         <td><span class="indexTitle"> Welcome to Savvas Implementation of XBITS </span></td>
    </tr>
    
   <html-el:form action="/home/home.do">  
   <td> 
   <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
       <%--<td width="2" class="DivLeftBroder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>--%>
         <td align="left" valign="top">
          <td width="69%" align="left" valign="top">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                 <tr>
                <td class="normalText">XBITS (XML Book Industry Transaction Standards) is a new standard for XML (Extensible Markup Language) transactions to facilitate bi-directional electronic data exchanges between publishers, printers, and component and other media vendors. Savvas Implementation of XBITS ( PIX ) involves B2M and B2B flavors. While B2M is meant for interaction with Vendors that are not yet &quot;B2B enabled&quot;, Savvas's larger goal is to help our Vendors communicate &quot;electronically&quot; in a bi-directional mode with Savvas. This website is Savvas's B2M solution to implement XBITS standard transactions to enable 'paperless' communication from Savvas business units and help Savvas business communication with all our suppliers to be streamlined and in a consistent and standard methodology.<br>
                    </td>
              </tr>
              <tr>
                <td height="22">&nbsp;</td>
              </tr>
              <tr>
                <td class="inbox_table"><table width="100%" border="0" cellspacing="1" cellpadding="0">
                    <tr>
                      <td height="23" class="bottom_border">
                        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="35"><img src="<%=request.getContextPath()%>/images/inbox_icon.gif" width="35" height="24"></td>
                            <td width="178"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="4"></td>
                              </tr>
                              <tr>
                                <td height="19" class="inboxTitle">Your Inbox </td>
                              </tr>
                              <tr>
                                <td height="1"></td>
                              </tr>
                            </table></td>
                            <td>&nbsp;</td>
                          </tr>
                      </table></td>
                    </tr>
                   <c:if test="${norecordsmsg!=null}">
                    <tr>
                    <td class="noRecordsMessage" valign="middle" height="30px"><c:out value="${norecordsmsg}"/></td>
                    </tr>
                  </c:if>
                  
                    <tr>
                      <td class="bottom_border">
                      <c:if test="${roleType!='C'}">
	                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                      <c:set var="myIndex" value="0"/> 
	                       <c:forEach var="display" items="${inboxdetails}" varStatus="indexId">
	                        
	                       <c:choose>  
	                       <c:when test="${display.itemTypeDetail.description=='ARP Title SetUp'}"> 
	                          
	                           <c:set var="myIndex" value="1"/> 
	                            <td width="35%" valign="middle" class="inboxdrakBlue">
	                            	<c:choose>
	                            		<c:when test="${roleType!='M'}">
	                            			<a href="${PATH}/home/potentialarplist.do?party=V&status=${display.statusDetail.statusId}&page=I&PAGE_VALUE=1" class="inboxdrakBlue3"><c:out value="${display.itemTypeDetail.description} [${display.statusDetail.statusDescription}] - " /> 
			                            	</a>
			                            </c:when>
			                            <c:otherwise>
			                            	<a href="${PATH}/home/potentialarplist.do?party=M&status=${display.statusDetail.statusId}&page=I&PAGE_VALUE=1" class="inboxdrakBlue3"><c:out value="${display.itemTypeDetail.description} [${display.statusDetail.statusDescription}] - " /> 
			                            	</a>
			                            </c:otherwise>
			                         </c:choose>
	                            </td>
	                            <td width="20%" valign="middle" class="inboxdrakBlue"><c:out value="${display.count}"/></td>
	                          <c:if test="${(indexId.index%2==1 && indexId.index!=0 && myIndex==1)||(myIndex==0)}">
	                            </tr>            
	                            </c:if>
	                      </c:when>
	                       
	                                               
	                          <c:when test="${display.itemTypeDetail.description=='Purchase Orders'}"> 
	                          
	                           <%--<c:if test="${display.statusDetail.statusId=='19'||display.statusDetail.statusId=='38'|| display.statusDetail.statusId=='39'||display.statusDetail.statusId=='0'||display.statusDetail.statusId=='20'||display.statusDetail.statusId=='21'||display.statusDetail.statusId=='22'}"> --%>                          
	                             <c:set var="myIndex" value="1"/> 
	                            <td width="35%" valign="middle" class="inboxdrakBlue">
	                            	<c:choose>
	                            		<c:when test="${roleType!='M'}">
			                            	<a href="${PATH}/home/purchaseorderlist.do?party=V&status=${display.statusDetail.statusId}&page=I&PAGE_VALUE=1" class="inboxdrakBlue3"><c:out value="${display.itemTypeDetail.description} [${display.statusDetail.statusDescription}] - " /> 
			                            	</a>
			                            </c:when>
			                            <c:otherwise>
			                            	<a href="${PATH}/home/millpurchaseorderlist.do?party=M&status=${display.statusDetail.statusId}&page=I&PAGE_VALUE=1" class="inboxdrakBlue3"><c:out value="${display.itemTypeDetail.description} [${display.statusDetail.statusDescription}] - " /> 
			                            	</a>
			                            </c:otherwise>
			                         </c:choose>
	                            </td>
	                            <td width="20%" valign="middle" class="inboxdrakBlue"><c:out value="${display.count}"/></td>
	                          <c:if test="${(indexId.index%2==1 && indexId.index!=0 && myIndex==1)||(myIndex==0)}">
	                            </tr>            
	                            </c:if>
	                           
	                             <%--</c:if>--%>                                         
	                      </c:when>

	                        <c:when test="${display.itemTypeDetail.description=='Planning'}">
        
	                          <%--<c:if test="${display.statusDetail.statusId=='1'||display.statusDetail.statusId=='2'||display.statusDetail.statusId=='3'||display.statusDetail.statusId=='4'}">--%>
	                           <c:set var="myIndex" value="1"/> 
	                          <td valign="middle" class="inboxdrakBlue">
	                          	<c:choose>
	                            	<c:when test="${roleType!='M'}">
	                            	    <a href="${PATH}/home/planninglist.do?status=${display.statusDetail.statusId}&page=I&PAGE_VALUE=1" class="inboxdrakBlue3"><c:out value="${display.itemTypeDetail.description} [${display.statusDetail.statusDescription}] - " /> 
	                          			</a>
	                          		</c:when>
	                          		<c:otherwise>
	                          			<a href="${PATH}/home/millplanninglist.do?status=${display.statusDetail.statusId}&page=I&PAGE_VALUE=1" class="inboxdrakBlue3"><c:out value="${display.itemTypeDetail.description} [${display.statusDetail.statusDescription}] - " /> 
	                          			</a>
	                          		</c:otherwise>
	                          	</c:choose>
	                          </td>
	                          <td valign="middle" class="inboxdrakBlue"><c:out value="${display.count}"/></td>
	                         <c:if test="${(indexId.index%2==1 && indexId.index!=0 && myIndex==1)||(myIndex==0)}">
	                            </tr>            
	                            </c:if>
	                          
	                        <%--</c:if>--%>  
	                         
	                       </c:when>
	                     
	                        <c:when test="${display.itemTypeDetail.description=='Book Specification'}">
	                          
	                         <%--<c:if test="${display.statusDetail.statusId=='5' || display.statusDetail.statusId=='6'|| display.statusDetail.statusId=='7'|| display.statusDetail.statusId=='8'}">--%>
	                          <c:set var="myIndex" value="1"/>
	                          <td valign="middle" class="inboxdrakBlue"><a href="${PATH}/home/bookspeclist.do?status=${display.statusDetail.statusId}&page=I&PAGE_VALUE=1" class="inboxdrakBlue3"> <c:out value="${display.itemTypeDetail.description} [${display.statusDetail.statusDescription}] - " /> </a></td>
	                          <td valign="middle" class="inboxdrakBlue"><c:out value="${display.count}"/></td>
	                          <c:if test="${(indexId.index%2==1 && indexId.index!=0 && myIndex==1)||(myIndex==0)}">
	                            </tr>            
	                            </c:if>
	                            
	                          <%--</c:if>--%>  
	                     </c:when>
	                       <c:when test="${display.itemTypeDetail.description=='Delivery Message'}">
	                         <c:set var="myIndex" value="1"/>
	                         	<c:choose>
	                            	<c:when test="${roleType!='M'}">
	                          			<td valign="middle" class="inboxdrakBlue"><a href="${PATH}/deliverymessage/inboxdeliverylist.do?PAGE_VALUE=1&home=H&days=5" class="inboxdrakBlue3"> <c:out value="${display.itemTypeDetail.description} [in last 5 days] - " /> </a></td>
	                          		</c:when>
	                          		<c:otherwise>
	                          			<td valign="middle" class="inboxdrakBlue"><a href="${PATH}/deliverymessage/inboxdeliverymsgmilldetail.do?PAGE_VALUE=1&home=H&days=5&partyType1=M&order=P" class="inboxdrakBlue3"> <c:out value="${display.itemTypeDetail.description} [in last 5 days] - " /> </a></td>
	                          		</c:otherwise>
	                          	</c:choose>
	                          <td valign="middle" class="inboxdrakBlue"><c:out value="${display.count}"/></td>
	                          <c:if test="${(indexId.index%2==1 && indexId.index!=0 && myIndex==1)||(myIndex==0)}">
	                            </tr>            
	                            </c:if>
	                            </c:when> 
	                           
	                     	<c:when test="${display.itemTypeDetail.description=='Dropship'}"> 
	                         <c:set var="myIndex" value="1"/>
	                         	<td valign="middle" class="inboxdrakBlue"><a href="${PATH}/dropshipinstructionStatus/display.do?statusw=${display.statusDetail.statusId}&page=I&PAGE_VALUE=1" class="inboxdrakBlue3"> <c:out value="${display.itemTypeDetail.description} [${display.statusDetail.statusDescription}] -" /> </a></td>
	                          	<td valign="middle" class="inboxdrakBlue"><c:out value="${display.count}"/></td>
	                          	<c:if test="${(indexId.index%2==1 && indexId.index!=0 && myIndex==1)||(myIndex==0)}">
	                            	</tr>            
	                            </c:if>
	                     	</c:when> 
	                            
	                          <c:otherwise>
	                          <c:set var="otindex" value="${otindex+1}"/>
	                          </c:otherwise>
	                      </c:choose>                     
	                       <c:set var="finalindex" value="${indexId.index-otindex}"/>
	                  </c:forEach>
	                      <c:if test="${myIndex==1 && finalindex%2==0}">
	                      <td width="35%" valign="middle" class="inboxdrakBlue">&nbsp;</td>
	                      <td valign="middle" class="inboxdrakBlue">&nbsp;</td>
	                     
	                      </c:if>
                      	</table>
                      </c:if>
                      </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
          <td width="31%" align="left" valign="top" class="table_left_margin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>
                 
                <input type="hidden" name="poid" id="poid"/>
                <input type="hidden" name="pover" id="pover"/>
                <input type="hidden" name="porel" id="porel"/>
                <input type="hidden" name="poorderType" id="poorderType"/>
                <input type="hidden" name="poOrder" id="poOrder"/>
                
                <table width="95%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="50%" colspan="2" height="15"></td>
                    </tr>
                    
                    <tr>
                      <td colspan="2"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                        	 
                        	 
                          <td width="28"><img src="<%=request.getContextPath()%>/images/search_icon.gif" width="28" height="24"></td>
                          <td width="100"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="4"></td>
                              </tr>
                              <tr>
                                <td height="19" class="searchTilte">Quick Search </td>
                              </tr>
                               <tr>
                                <td height="1"></td>
                              </tr>
                          </table></td>
                          <td>&nbsp;</td>
                        </tr>
                      </table></td>
                    </tr>
                    <tr>
                      <td colspan="2" class="searchBorder"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="45%" class="text10size" height="12"></td>
                            <td class="text10size"></td>
                          </tr>
                          <tr>
                            <td class="text10size">Transaction Type:</td>
                            <td>
                                                                  
                           <html-el:select property="moduleCode" onchange="javascript:submitstatus('${PATH}/home/home.do','${PATH}/purchaseorderstatus.do','${PATH}/planningstatus.do','${PATH}/bookspecificationstatus.do','${PATH}/dropshipinstructionStatus/display.do','${PATH}/usagestatus.do')" styleClass="listBoxText" >
							 <html-el:option value="">Select</html-el:option>
							 <html-el:option value="POR" ><c:out value="Purchase Order" /></html-el:option>
							 <%--<html-el:option value="DS" ><c:out value="Dropship" /></html-el:option> --%>
							 <c:if test="${roleType!='C'}">
							 	<html-el:option value="PLA"><c:out value="Planning" /></html-el:option>
							 </c:if>
							 <!-- Gaurav -->
							 <c:choose>
							 <c:when test="${roleType!='C'}">
							  	<html-el:option value="DM"><c:out value="Delivery Message"/></html-el:option>
							 	<html-el:option value="GR"><c:out value="Goods Receipt"/></html-el:option>
							 </c:when>
							 <c:otherwise>
							 <html-el:option value="CA"><c:out value="Delivery Message"/></html-el:option>
							 </c:otherwise>
							 </c:choose>
							 <!-- Gaurav -->
							 <c:if test="${roleType!='M' && roleType!='C'}">
								 <html-el:option value="BSP"><c:out value="Book Specification" /></html-el:option>
								 <%--<html-el:option value="USG"><c:out value="Usage"/></html-el:option> --%>
							 </c:if>
						  
						   </html-el:select>                             </td>
						   
                          </tr>
                          <tr>
                            <td height="10"></td>
                            <td></td>
                          </tr>
                          
        
                          <tr>
                          	<c:choose>
                            		<c:when test="${roleType!='M' && roleType!='C'}">
                            			<td class="text10size">ISBN:</td>
                            		</c:when>
                            		<c:otherwise>
                            			<td class="text10size">Material Number:</td>
                            		</c:otherwise>
                            </c:choose>
                            <td>
                            <html-el:text property="isbn" styleClass="textBoxtext"></html-el:text></td>
                            </tr>
                          <tr>
                            <td height="10"></td>
                            <td></td>
                          </tr>
                          <tr>
                          	<c:choose>
                            		<c:when test="${roleType!='M' && roleType!='C'}">
			                            <td class="text10size">Printing No.: </td>
			                            <td><html-el:text property="printno" onkeypress="return filtercheck()" styleClass="textBoxtext"></html-el:text></td>
			                        </c:when>
			                        <c:otherwise>
			                        	<td class="text10size">Paper Grade: </td>
			                            <td><html-el:text property="printno" styleClass="textBoxtext"></html-el:text></td>
			                        
			                        </c:otherwise>
			               </c:choose>
                          </tr>
                          <tr>
                            <td height="10"></td>
                            <td></td>
                          </tr>
                          <tr>
                            <td height="20"><span class="text10size">Purchase Order No.:</span></td>
                             <c:choose>
                             <c:when test="${roleType != 'C'}">
                            <td> <html-el:text property="pono" maxlength="10" styleId="ponobox" styleClass="textBoxtext"></html-el:text></td>
                            </c:when>
                            <c:otherwise>
                            <td> <html-el:text property="pono" maxlength="10" styleId="ponobox" styleClass="textBoxtext" ></html-el:text></td>
                            </c:otherwise>
							</c:choose>
                          </tr>
                          <tr>
                            <td height="10"></td>
                            <td></td>
                          </tr>
                          <tr>
                            <td height="20"><span class="text10size">Job No.:</span></td>
                            <td> <html-el:text property="jobno" styleClass="textBoxtext"></html-el:text></td>
                          </tr>
                          <tr>
                            <td height="10"></td>
                            <td></td>
                          </tr>
                            <tr>
                           <td class="text10size">Status:</td>
                           <c:if test="${planningAllStatus==null && poAllStatus==null && BookSpecAllStatus==null && DropshipAllStatus==null && UsageStatus==null}">
                           <td><select name="select2" class="listBoxText">
                                <option>Select</option>
                            </select></td>
                          </c:if>
                          <%--<td><select name="select2" class="listBoxText">
                                <option>Select</option>
                            </select></td>
                          </tr>--%>
                          <%--<html-el:hidden property="moduleDescription"/>--%>
                          
                          <%--<input type="hidden" name="moduleCode" value="${moduleCode}"/>--%>
                          <%--<html-el:hidden property="statusId"/>--%>
                                      
                         
                          
                          <c:if test="${planningAllStatus!=null}">
                              <td> <html-el:select property="statusDescription" styleClass="textfield">
								<html-el:option value="">Select</html-el:option>
								<c:forEach var="item" items="${planningAllStatus}" varStatus="indexId">
									<html-el:option value="${item.statusId}">
										<c:out value="${item.statusDescription}" />
									</html-el:option>
								</c:forEach>
							</html-el:select> 
                          </td>
                          </c:if>
                          <c:if test="${poAllStatus!=null}">
                          
                              <td> <html-el:select property="statusDescription" styleClass="textfield">
								<html-el:option value="">Select</html-el:option>
								<c:forEach var="item" items="${poAllStatus}" varStatus="indexId">
									<html-el:option value="${item.statusId}">
										<c:out value="${item.statusDescription}" />
									</html-el:option>
								</c:forEach>
							</html-el:select> 
                          </td>
                          </c:if>
                             <c:if test="${BookSpecAllStatus!=null}">
                          
                              <td>  
                         <html-el:select property="statusDescription"  styleClass= "textfield" >
						<html-el:option value="">Select</html-el:option>
						<c:forEach var="item" items="${BookSpecAllStatus}" varStatus="indexId">
						<html-el:option value="${item.statusId}"><c:out value="${item.statusDescription}"/></html-el:option>
						</c:forEach>
						</html-el:select>
                          </td>
                          </c:if>
                          	
                          	<c:if test="${DropshipAllStatus!=null}">
                          
                              <td>  
                         <html-el:select property="statusDescription"  styleClass= "textfield" >
						<html-el:option value="">Select</html-el:option>
						<c:forEach var="item" items="${DropshipAllStatus}" varStatus="indexId">
						<html-el:option value="${item.statusId}"><c:out value="${item.statusDescription}"/></html-el:option>
						</c:forEach>
						</html-el:select>
                          </td>
                          </c:if>
                          
                          <c:if test="${UsageStatus!=null}">
                          
                              <td>  
                         <html-el:select property="statusId"  styleClass= "textfield" >
						<html-el:option value="">Select</html-el:option>
						<c:forEach var="item" items="${UsageStatus}" varStatus="indexId">
						<html-el:option value="${item.statusId}"><c:out value="${item.statusDescription}"/></html-el:option>
						</c:forEach>
						</html-el:select>
                          </td>
                          </c:if>
                          
                         </tr>
                          <tr>
                            <td height="10"></td>
                            <td></td>
                          </tr>
                          <tr>
                            <td height="10"></td>
                            <td >
                            	<c:choose>
                            		<c:when test="${roleType!='M' && roleType!='C'}">
                            			<a href="javascript:submithome('${PATH}/home/purchaseorderlist.do?party=V&PAGE_VALUE=1','${PATH}/home/planninglist.do?PAGE_VALUE=1','${PATH}/home/bookspeclist.do?PAGE_VALUE=1','${PATH}/dropshipinstructionStatus/display.do?PAGE_VALUE=1','${PATH}/home/usagelist.do?PAGE_VALUE=1','${PATH}/deliverymessage/deliverymessagemilllist.do?PAGE_VALUE=1&page_order_list=1','${PATH}/deliverymessage/deliverymessagelist.do?PAGE_VALUE=1&page_order_list=1','${PATH}/goodsreceipt/goodsreceiptpapernew.do?paperlist=paperlist&PAGE_VALUE=1&page_order_list=1','${PATH}/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&page_order_list=1','${PATH}/costaccounting/approvallist.do?PAGE_VALUE=1')"/>
                          				<img src="<%=request.getContextPath()%>/images/search_button.gif" alt="Search" width="74" height="20" border="0" class="button_align"></a>
                          			</c:when>
                          			<c:otherwise>
                          				<a href="javascript:submithome('${PATH}/home/millpurchaseorderlist.do?party=M&PAGE_VALUE=1','${PATH}/home/millplanninglist.do?PAGE_VALUE=1','${PATH}/home/bookspeclist.do?PAGE_VALUE=1','${PATH}/home/usagelist.do?PAGE_VALUE=1','${PATH}/deliverymessage/deliverymessagemilllist.do?PAGE_VALUE=1&page_order_list=1','${PATH}/deliverymessage/deliverymessagelist.do?PAGE_VALUE=1&page_order_list=1','${PATH}/goodsreceipt/goodsreceiptpapernew.do?paperlist=paperlist&PAGE_VALUE=1&page_order_list=1','${PATH}/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&&page_order_list=1','${PATH}/costaccounting/approvallist.do?PAGE_VALUE=1')"/>
                          				<img src="<%=request.getContextPath()%>/images/search_button.gif" alt="Search" width="74" height="20" border="0" class="button_align"></a>
                          				
                          			</c:otherwise>
                          			
                          		</c:choose>
                          </td> 
                           
                         <%-- <html-el:submit  property="submit" value="Search" styleClass="buttonMain"   onclick="submithome();"/>--%>
                          
                          </tr>
                          <tr>
                            <td height="14"></td>
                            <td></td>
                          </tr>
                      </table></td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><table width="95%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
                    <tr>
                      <td valign="middle"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="38"><img src="<%=request.getContextPath()%>/images/new_icon.gif" width="38" height="24"></td>
                              <td width="100"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td height="4"></td>
                                </tr>
                                <tr>
                                  <td height="19" class="searchTilte">News</td>
                                </tr>
                                <tr>
                                  <td height="1"></td>
                                </tr>
                              </table></td>
                              <td>&nbsp;</td>
                            </tr>
                          </table></td>
                          </tr>
                      </table></td>
                    </tr>
                    <tr>
                      <td height="19" valign="top" class="newsBorder"><span class="newtextContainer">
                      <a href="javascript:MM_openBrowserWindow('<%=request.getContextPath()%>/PIXManual/PIXManual.pdf','','scrollbars=yes,width=800,height=500')" class="newslink">PIX Manual</a>
                      </span><br>
                      <span><%--<a href="javascript:MM_openBrowserWindow('/PIXManual/faq.pdf','','scrollbars=yes,width=800,height=500')" class="newslink">Frequently Asked Questions</a>--%>
                      </span><br>
                      <span><%--<a href="javascript:MM_openBrowserWindow('/PIXManual/releasenotes.pdf','','scrollbars=yes,width=800,height=500')" class="newslink">Release Notes</a>--%>
                      </span><br> 
                      
                      </td>
                    </tr>
                </table></td>
              </tr>
               </table></td>
        </tr>
      </table>
      
      </td>
    
</html-el:form>