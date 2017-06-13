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

    <form method="POST" action="menu">

        <label for="find">Найти пользователя по id</label>
        <input type="radio" name="act" value="find" id="find"><br>

        <label for="add">Добавить пользователя</label>
        <input type="radio" name="act" value="addition" id="add"><br>

        <label for="edit">Редактировать пользователя</label>
        <input type="radio" name="act" value="edition" id="edit"><br>

        <label for="del">Удалить пользователя</label>
        <input type="radio" name="act" value="delete" id="del"><br>

        <label for="all">Список всех пользователей</label>
        <input type="radio" name="act" value="all" id="all"><br>

        <input type="submit" value="Ok" name="Ok"><br>
    </form>
    <br />


    <form method="post" action="logout">
        <input type="submit" value="logout" name="logout"><br>
    </form>

</body>
</html>
