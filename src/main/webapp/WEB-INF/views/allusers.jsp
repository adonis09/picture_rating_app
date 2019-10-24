<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>all users</title>
</head>
<body>

<a href="/">home page</a>

<h4>all users</h4>

<table>

<c:forEach var="oneUser" items="${users}">
<tr>
    <td>${oneUser.id}</td>
    <td>${oneUser.name}</td>
    <td>${oneUser.email}</td>
</tr>
</c:forEach>
</table>

<a href="/user/register">add new user</a>

</body>
</html>
