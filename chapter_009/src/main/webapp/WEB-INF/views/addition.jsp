<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 29.05.17
  Time: 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление</title>
</head>
<body>

    <form method="post" action="addition">

        <input type="text" required placeholder="name" name="name"><br>
        <input type="text" required placeholder="login" name="login"><br>
        <input type="text" required placeholder="password" name="password"><br>
        <input type="text" required placeholder="email" name="email"><br>
        <label for="admin">admin </label><input type="radio" required value="admin" name="role" id="admin" />
        <label for="user">user </label><input type="radio" required value="user" name="role" id="user" />
        <input type="submit" value="Добавить">

    </form>

</body>
</html>
