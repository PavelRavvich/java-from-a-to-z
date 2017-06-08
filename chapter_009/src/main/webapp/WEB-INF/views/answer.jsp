<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ответ</title>
</head>
<body>

    <c:out value="${requestScope.serverAnswer}"/>

    <form action="answer" method="get">
        <input type="submit" value="Меню">
    </form>

</body>
</html>
