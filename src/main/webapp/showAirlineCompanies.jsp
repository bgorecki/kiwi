<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/header.jsp" />

	    <table class="table" cellspacing="0" width="100%">
 
	    <thead>
	        <tr>
	            <!-- This is a special cell for loading statuses - see below for more -->
	            <th class="black-cell"><span class="success"></span></th>
	             
	            <th scope="col">Nazwa</th>
	            <th scope="col">Kraj</th>
	            <th scope="col" class="table-actions">Akcje</th>
	        </tr>
	    </thead>
 
	    <tfoot>
	        <tr>
	            <td colspan="4"><img src="images/icons/fugue/arrow-curve-000-left.png" width="16" height="16" class="picto"> <b>Znalezione rekordy:</b> ${ empty airlineCompanies ? 0 : airlineCompanies.size()} </td>
	        </tr>
	    </tfoot>
     
	    <tbody>
	        
	        <c:forEach var="company" items="${airlineCompanies}" varStatus="status">
	        	<tr>
		            <th scope="row" class="table-check-cell"></th>
		            <td>${company.nazwa}</td>
		            <td>${company.kraj}</td>
		             
		            <!-- The class table-actions is designed for action icons -->
		            <td class="table-actions">
		                <a href="<c:url value="/airlinecompanies?action=editCompany&id=${company.idPrzewoznika}"/>" title="Edytuj" class="with-tip"><img src="<c:url value="/images/icons/fugue/pencil.png"/>" width="16" height="16"></a>
		                <a href="<c:url value="/airlinecompanies?action=deleteCompany&id=${company.idPrzewoznika}"/>" title="Usuń" class="with-tip" onclick="return confirm('Czy jesteś pewny? Operacja nie może być cofnięta!')"><img src="images/icons/fugue/cross-circle.png" width="16" height="16"></a>
		            </td>
	        	</tr>
	        </c:forEach>
	         
	    </tbody>
 
	</table>
    
<jsp:include page="/WEB-INF/view-elements/footer.jsp" />