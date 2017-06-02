<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 29.05.17
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удаление пользователя</title>
</head>
<body>

    <form method="POST" action="delete">

        <input type="number" required placeholder="id" name="id" value="0"><br>
        <input name="submit" type="submit" value="Удалить">

    </form>

    <p style="color: red">${result}</p>
</body>
</html>
