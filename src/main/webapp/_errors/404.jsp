<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/error-header.jsp" />

	<h1>404</h1>
	<p>Page not found</p>
	<section>
		
		<ul class="action-tabs on-form with-children-tip children-tip-left">
			<li><a href="javascript:history.back()" title="Go back"><img src='<c:url value="/images/icons/fugue/navigation-180.png" />' width="16" height="16"></a></li>
		</ul>
		
		<ul class="action-tabs right on-form with-children-tip children-tip-right">
			<li><a href="index.html" title="Go to homepage"><img src='<c:url value="/images/icons/fugue/home.png" />' width="16" height="16"></a></li>
		</ul>
		
		<form class="block-content no-title dark-bg form" method="post" action="">
			<input type="text" name="s" id="s" value=""> &nbsp; 
			<button type="submit">Search</button>
		</form>
		
	</section>

<jsp:include page="/WEB-INF/view-elements/error-footer.jsp" />
