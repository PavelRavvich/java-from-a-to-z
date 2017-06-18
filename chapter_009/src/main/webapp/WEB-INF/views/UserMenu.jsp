<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 29.05.17
  Time: 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню</title>

</head>
<body>

<h2>Меню</h2><br />

<form method="POST" action="user_menu">

    <label for="edit">Редактировать профиль</label>
    <input type="radio" name="act" value="edition" id="edit"><br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>
<br />


<form method="post" action="logout">
    <input class="button" type="submit" value="logout" name="logout"><br>
</form>

</body>
</html>
