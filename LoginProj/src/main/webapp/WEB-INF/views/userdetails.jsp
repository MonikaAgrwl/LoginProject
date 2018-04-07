<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
</head>
<body>
<h1>
 Welcome ${username }
	User Details
</h1>


<table>
    <tr>
        <th>Username</th>
        <th>isLoggedIn</th>
        <th>NumberofLogIns</th>
    </tr>
    <c:forEach items="${userlist}" var="entry" >
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value.loggedIn}</td>
            <td>${entry.value.numberOfLogins}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>