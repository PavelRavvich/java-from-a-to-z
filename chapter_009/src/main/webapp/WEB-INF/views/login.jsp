<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>

    <form method="post" action="">

        <input type="text" required placeholder="name" name="name"><br>
        <input type="password" required placeholder="email" name="email"><br>
        <input type="submit" value="Войти">

    </form>
    <c:out value="${warning}"/>
</body>
</html>
