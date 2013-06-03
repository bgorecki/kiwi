<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/view-elements/error-header.jsp" />

	<h1>500</h1>
	<p>Internal server error</p>
	<section>
		
		<ul class="action-tabs with-children-tip children-tip-left">
			<li><a href="javascript:history.back()" title="Go back"><img src='<c:url value="/images/icons/fugue/navigation-180.png" />' width="16" height="16"></a></li>
		</ul>
		
		<ul class="action-tabs right with-children-tip children-tip-right">
			<li><a href="index.jsp" title="Go to homepage"><img src='<c:url value="/images/icons/fugue/home.png"/>' width="16" height="16"></a></li>
		</ul>
		
		<div class="block-content no-title dark-bg">
			Please contact your system administrator
		</div>
		
	</section>

<jsp:include page="/WEB-INF/view-elements/error-footer.jsp" />
