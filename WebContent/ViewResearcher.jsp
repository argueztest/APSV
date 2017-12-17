<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Researcher Profile</h1>
<p>Name: <c:out value="${researcher.name}" ></c:out></p>
<p>Affiliation: <c:out value="${researcher.affiliation}" ></c:out></p>
<p>Email: <c:out value="${researcher.email}" ></c:out></p>


<table border=1>
		<tr>
			<td>Title</td>
			<td>CiteCount</td>
		</tr>
		<c:forEach items="${researcher.publications }" var="pub">
		<tr>
			<td><c:out value="${pub.title }"></c:out></td>
			<td><c:out value="${pub.citeCount }"></c:out></td>
		</tr>
		
		</c:forEach>
	</table>
</body>
</html>