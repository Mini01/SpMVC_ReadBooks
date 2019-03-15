<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-head.jspf" %>
</head>


<link rel="stylesheet" 
	href=<c:url value="/css/book.css" /> >
<body>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<section>
	<c:if test="${BODY == 'LIST' }">
		<%@ include file="/WEB-INF/views/bodys/bookslist.jspf" %>
	</c:if>
	<c:if test="${BODY == 'VIEW' }" >
		<%@ include file="/WEB-INF/views/bodys/bookview.jspf" %>
	</c:if>
	<c:if test="${BODY == 'WRITE'}" >
		<%@ include file="/WEB-INF/views/bodys/bookwrite.jspf" %>
	</c:if>


</section>
</body>
</html>