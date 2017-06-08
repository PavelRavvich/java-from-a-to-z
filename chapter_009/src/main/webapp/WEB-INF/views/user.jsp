<%@ page import="ru.pravvich.user.User" %><%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 27.05.17
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>user</title>

</head>
<body>
    <% User user = (User) request.getAttribute("user"); %>

    <h1>User view</h1><br />

        <ul>

            <li>Id: <%=user.getId()%></li>

            <li>Name: <%=user.getName()%></li>

            <li>Login: <%=user.getLogin()%></li>

            <li>Email: <%=user.getEmail()%></li>

            <li>Create date: <%=user.getCreateAccount()%></li>

        </ul><br />

    <form action="answer" method="get">
        <input type="submit" value="Меню">
    </form>

</body>
</html>
