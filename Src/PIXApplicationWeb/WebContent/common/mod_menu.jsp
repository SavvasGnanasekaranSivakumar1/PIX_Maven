<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.dto.admin.User"%>
<%
User objUser = (User)session.getAttribute("USER_INFO");   
String roleType = "";
if(objUser!=null)
{
	roleType = objUser.getRoleTypeDetail().getRoleType();
}
%>
<tr>
<td height="92" colspan="3" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="175" align="right" valign="top"><a href="<%=request.getContextPath()%>/home/home.do"><img src="<%=request.getContextPath()%>/images/Logo.gif" alt="Savvas Implementation of XBITS" width="177" height="92" border="0" /></a></td>
        <td width="*" align="left" valign="top"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="302" height="69" class="headerbg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="14%"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                          <td width="86%" height="30"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                        </tr>
                        <tr>
                          <td><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                          <td><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                        </tr>
                        <tr>
                          <td><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                          <td class="date">Hi <%=objUser.getFirstName()%> <%=objUser.getLastName()%>; Today is
                            <script language="JavaScript" type="text/javascript">
function makeArray() {
     for (i = 0; i<makeArray.arguments.length; i++)
          this[i + 1] = makeArray.arguments[i];
}

var months = new makeArray('Jan','Feb','Mar',
    'Apr','May','Jun','Jul','Aug','Sep',
    'Oct','Nov','Dec');

var date = new Date();
var day  = date.getDate();
var month = date.getMonth() + 1;
var yy = date.getYear();
var year = (yy < 1000) ? yy + 1900 : yy;

document.write(day + " " + months[month] + "&nbsp;" + year+"");
  </script></td>
            </tr>
            </table></td>
                <td class="bannerBack"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1" /></td>
                    <td width="298"><img src="<%=request.getContextPath()%>/images/banner_02.gif" width="298" height="69" /></td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td valign="top" class="topLinkBack">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="23" valign="top">
				  <div id="topNav" style="width: 1024px">
				  
			  <% if(!((PIXConstants.COST_ACCOUNTING_ROLE).equals(roleType)))
			  		{
			  %>
				  	<div>
				  		<%
							if(PIXUtil.checkUserReadWriteAccess(request,PIXConstants.PLANNING_CODE))
							{
							
							if((PIXConstants.MILL_ROLE).equals(roleType))
							{
						%>            
				  		<html-el:link styleId="link_3" page="/planning/millplanninglist.do?PAGE_VALUE=1">PLANNING</html-el:link>
				  		<% }else{ %>
				  		
				  		<html-el:link styleId="link_3" page="/planning/planninglist.do?PAGE_VALUE=1">PLANNING</html-el:link>
							<%}
							}
							if((PIXUtil.checkUserReadWriteAccess(request,PIXConstants.BOOKSPEC_CODE)) && !((PIXConstants.MILL_ROLE).equals(roleType)) )
							{	
						%>
				  	</div>
					<div>
						<html-el:link styleId="link_1" page="/bookspecification/bookspeclist.do?PAGE_VALUE=1">BOOK SPECIFICATION</html-el:link>			
						<%
							}
							if(PIXUtil.checkUserReadWriteAccess(request,PIXConstants.PO_CODE))
							{
						%>
					</div>
					<div>
					      <%
							if((PIXConstants.MILL_ROLE).equals(roleType))
							{
						%>
						<html-el:link styleId="link_2" page="/purchaseorder/millpurchaseorderlist.do?PAGE_VALUE=1&party=M">ORDER</html-el:link>
						<%}else{%>
						<html-el:link styleId="link_2" page="/purchaseorder/purchaseorderlist.do?PAGE_VALUE=1&party=V">ORDER</html-el:link>
						<%
						  }
							}
							if((PIXUtil.checkUserReadWriteAccess(request,PIXConstants.USAGE_CODE)) && !((PIXConstants.MILL_ROLE).equals(roleType)) )
							{
						%>  
					</div>
					<%-- VPUNUVA<div>
						<html-el:link styleId="link_4" page="/usage/usageList.do?PAGE_VALUE=1">USAGE</html-el:link>
						<%
							}
							if((PIXUtil.checkUserReadWriteAccess(request,PIXConstants.INVENTORY_CODE)) && !((PIXConstants.MILL_ROLE).equals(roleType)))
							{
						%>
					</div>--%>
					<div>
						<html-el:link styleId="link_5" page="/inventory/inventorysearch.do">INVENTORY</html-el:link>
						<%
							}
						%>
					</div>	
				<%-- VPUNUVA<div>
					   <% 
					 	if((PIXConstants.VENDOR_ROLE).equals(roleType) || (PIXConstants.ADMIN_ROLE).equals(roleType) || (PIXConstants.BUYER_ROLE).equals(roleType))
					        {  
						%>
						    <SCRIPT language="javascript" type="text/javascript">
								dropshipSubmenus('<%=request.getContextPath()%>');
							</SCRIPT>
							<html-el:link  styleId="link_11" href ="javascript:linksHover(11);" onmouseover="linksHover(11);dropdownmenuDropship(this, event, menu11, '200px')" onmouseout="delayhidemenuDropship()">DROPSHIP</html-el:link>					
						<%
						}
					     %>
					</div> --%>
					<div>
						<%
							if(!((PIXConstants.MILL_ROLE).equals(roleType)))
							{
								if((PIXConstants.VENDOR_ROLE).equals(roleType) || (PIXConstants.ADMIN_ROLE).equals(roleType))
								{
						%>
						<html-el:link styleId="link_6" page="/reports/reportsearch.do">REPORTS</html-el:link>
						<%
								}
								else if((PIXConstants.BUYER_ROLE).equals(roleType))
								{
						%>
					</div>
					<div>
							<SCRIPT language="javascript" type="text/javascript">
							buyerReportSubmenus('<%=request.getContextPath()%>')
							</SCRIPT>
							<html-el:link styleId="link_6" href ="javascript:linksHover(6);" onmouseover="linksHover(6);dropdownmenubuyer(this, event, menu4, '218px')" onmouseout="delayhidemenubuyer()">REPORTS</html-el:link>					
								
						<%
								}
							}
						%>					
					</div>
			<!--New Menu for outstanding report for mill users, @author Anshu Bhardwaj -->
					<DIV>
						<%
							if((PIXConstants.MILL_ROLE).equals(roleType))
							{
						%>
					</DIV>
					<DIV>
						<SCRIPT language="javascript" type="text/javascript">
							millReportSubmenus('<%=request.getContextPath()%>')
						</SCRIPT>
						<html-el:link styleId="link_7" href ="javascript:linksHover(7);" onmouseover="linksHover(7);dropdownmenu(this, event, menu3, '218px')" onmouseout="delayhidemenu()">REPORTS</html-el:link>					
						<%
							}
						%>			
					</DIV>
			<!--End of changes made for outstanding report-->	
					<DIV>										 
						<%
							if((PIXConstants.ADMIN_ROLE).equals(roleType))
							{
						%>
					</div>
					<div >
						<SCRIPT language="javascript" type="text/javascript">
							adminsubmenus('<%=request.getContextPath()%>')
						</SCRIPT>
						<html-el:link styleId="link_7" onmouseover="linksHover(7);dropdownmenu(this, event, menu2, '120px')" onmouseout="delayhidemenu()" href ="javascript:linksHover(7);" style="display:all">ADMIN</html-el:link>
						<%}
							else
						{%>
						<html-el:link styleId="link_7" onmouseover="linksHover(7);dropdownmenu(this, event, menu2, '120px')" onmouseout="delayhidemenu()" href ="javascript:linksHover(7);" style="display:none">ADMIN</html-el:link>
						<%}%>
					</div>
					
			<%
				}
				else if((PIXConstants.COST_ACCOUNTING_ROLE).equals(roleType))
				{
					if(PIXUtil.checkUserReadWriteAccess(request,PIXConstants.PO_CODE))
					{
			%>
						<div>
							<html-el:link styleId="link_2" page="/purchaseorder/millpurchaseorderlist.do?PAGE_VALUE=1&party=M">ORDER</html-el:link>
						</div>
					<%
						}
					%>
						<div>
							<html-el:link styleId="link_8" page="/costaccounting/approvallist.do?PAGE_VALUE=1">PAPER ACCOUNTING</html-el:link>
						</div>	
						
						<div>
							<html-el:link styleId="link_8" page="/mismatchreport/mismatchreport.do?mr=y">MISMATCH REPORT</html-el:link>
						</div>
			<%
				}
			%>
				  <!-- Added by Arvind for ARP Tile SetUp Link in ARP Phase -->
					<%-- VPUNUVA<div>
					   <% 
					    if(PIXUtil.checkUserReadWriteAccess(request,PIXConstants.ARP_MODULE_CODE))
					        {  
						%>
						    
						    <SCRIPT language="javascript" type="text/javascript">
							arpTitleSubmenus('<%=request.getContextPath()%>')
							</SCRIPT>
							<html-el:link  styleId="link_9" href ="javascript:linksHover(9);" onmouseover="linksHover(9);dropdownmenuarp(this, event, menu5, '218px')" onmouseout="delayhidemenuarp()">ARP TITLE SETUP</html-el:link>					
								
						<%
						}
					     %>
					</div>--%>
				
						
						
									
				
				
				  </div>
				 
                 </td>
                  <td align="right" valign="top"><img src="<%=request.getContextPath()%>/images/linkRight.gif" width="52" height="20"></td>
                </tr>
              </table>
              </td>
            </tr>
        </table></td>
      </tr>
    </table></td>
</tr>    