<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglibs.jsp"%>

<c:set var="statusMessage" value="${potentialARPForm.statusMessage}" />
             <TR>
               <td width="10px" align="center" class="tableRow"><c:out value="${potentialARPForm.detailArp.potentialarpDetail.titleIsbn}"/><label>
             	<td class="tableRow"><c:out value="${statusMessage}"/> <br></td>
             	</label>
			 </TD>
			 </TR> 