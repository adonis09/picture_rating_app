<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add tag</title>
</head>
<body>

<form:form method="post" modelAttribute="tag">
    <table>
        <tr>
            <td>Tag title:</td>
            <td><form:input type="text" path="title"/></td>
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
