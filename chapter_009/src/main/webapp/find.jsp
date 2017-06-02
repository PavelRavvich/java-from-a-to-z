<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 29.05.17
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
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

    <p style="color: red">${fail}</p>

</body>
</html>
