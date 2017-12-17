<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator Profile</title>
</head>
<body>
	<h1>Administrator Profile</h1>
	
	<table width=100%>
	<tr>
		<td align="right">
			<c:choose>
			<c:when test = "${user != null}">
				User: <c:out value="${user.name}"></c:out> <a href="/APSV/LogoutServlet"><button>Log Out</button></a>
			</c:when>
		</c:choose>
		</td>
	</tr>
	</table>
	
	<form action="/APSV/CreateResearcherServlet" method="GET">
		<table align="center">
		<tr>
			<td colspan="2" align="center">New Researcher Information</td>
		</tr>
		<tr>
			<td>Id</td>
			<td><input type="text" name="researcher_id"></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><input type="text" name="researcher_name"></td>
		</tr>
		<tr>
			<td>Affiliation</td>
			<td><input type="text" name="researcher_aff"></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" name="researcher_email"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="researcher_pwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="Submit"></td>
		</tr>
		</table>		
	</form>
</body>
</html>