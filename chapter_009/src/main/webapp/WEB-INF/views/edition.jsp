<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование данных пользователя</title>
</head>
<body>

    <form method="post" action="edit">

        <input type="number" required placeholder="id пользователя для редактирования" name="id"><br><br>
        <input type="text" required placeholder="name" name="name"><br>
        <input type="text" required placeholder="login" name="login"><br>
        <input type="text" required placeholder="password" name="password"><br>
        <input type="text" required placeholder="email" name="email"><br>
        <label for="admin">admin </label><input type="radio" required value="admin" name="role" id="admin" /><br>
        <label for="user">user </label><input type="radio" required value="user" name="role" id="user" />
        <br><br><br>
        <input type="submit" value="Редактировать">

    </form>


    <p style="color: red"><c:out value="${requestScope.warning}"/></p>
</body>
</html>
