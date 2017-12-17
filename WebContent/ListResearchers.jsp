<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Researchers List</title>
</head>
<body>
	<h1>Researchers List</h1>
	<table border=1 cellpadding="5">
		<thead>
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Affiliation</td>
				<td>Email</td>
			</tr>
		</thead>
		<c:forEach items="${rs}" var="rsi">
			<tr>
				<td><a
					href=<c:out value="ViewResearcherServlet?rsi=${rsi.id}"/>> <c:out
							value="${rsi.id}" /></a></td>
				<td><c:out value="${rsi.name}"></c:out></td>
				<td><c:out value="${rsi.affiliation}"></c:out></td>
				<td><c:out value="${rsi.email}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>