<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add user</title>
</head>
<body>

<form:form method="post" modelAttribute="user">
    <table>
        <tr>
            <td>Username: </td>
            <td><form:input type="text" path="name"/></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><form:input type="email" path="email"/></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><form:input type="password" path="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="SUBMIT">
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
