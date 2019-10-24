<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>all pictures</title>
</head>
<body>

<a href="/">home page</a>

<h3>all pictures</h3>

<table>
<c:forEach var="onePicture" items="${pictures}">
    <tr>
        <td>${onePicture.id}</td>
        <td>${onePicture.fileName}</td>
        <td>${onePicture.description}</td>
        <td>${onePicture.created}</td>
    </tr>
</c:forEach>
</table>

<a href="/picture/add">add new picture</a>

</body>
</html>
