<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>
	<h1>User Profile</h1>
	
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
	
	<table width=100%>
		<tr>
			<td align="left">
				<form action="/APSV/UpdateProfileServlet" method="GET">
					<table align="center">
					<tr>
						<td colspan="2" align="center">Update Researcher Information</td>
					</tr>
					<tr>
						<td>Id</td>
						<td><c:out value="${researcher.id}"></c:out></td>
					</tr>
					<tr>
						<td>Name</td>
						<td><input type="text" name="researcher_name" value="${researcher.name}"></td>
					</tr>
					<tr>
						<td>Affiliation</td>
						<td><input type="text" name="researcher_aff" value="${researcher.affiliation}"></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="researcher_email" value="${researcher.email}"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="researcher_pwd" value="${researcher.password}"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="Update"></td>
					</tr>
					</table>		
				</form>
			</td>
			<td align="right">
				<form action="/APSV/CreatePublicationServlet" method="GET">
					<table align="center">
					<tr>
						<td colspan="2" align="center">New Publication</td>
					</tr>
					<tr>
						<td>Id</td>
						<td><input type="text" name="pub_id"></td>
					</tr>
					<tr>
						<td>Title</td>
						<td><input type="text" name="pub_title"></td>
					</tr>
					<tr>
						<td>Citations</td>
						<td><input type="text" name="pub_cit"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="Create"></td>
					</tr>
					</table>		
				</form>
			</td>
		</tr>
	</table>
	
	

</body>
</html>