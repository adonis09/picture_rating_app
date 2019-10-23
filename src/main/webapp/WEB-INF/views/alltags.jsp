<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>all tags</title>
</head>
<body>

<h4>all tags</h4>

<table>

    <c:forEach var="oneTag" items="${tags}">

        <tr>
            <td>${oneTag.id}</td>
            <td>${oneTag.title}</td>
        </tr>

    </c:forEach>

</table>

<a href="/tag/add">add new tag</a>

</body>
</html>
