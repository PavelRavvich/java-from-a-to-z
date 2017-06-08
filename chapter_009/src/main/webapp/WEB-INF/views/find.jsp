<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Поиск</title>
</head>
<body>

    <form action="find" method="post">

        <input type="number" required placeholder="id" name="id"><br>
        <input type="submit" value="Найти">

    </form>

    <p style="color: red"><c:out value="${requestScope.fail}"/></p>
</body>
</html>
