<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 07.06.17
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>все пользователи</title>
</head>
<body>

    <h1>Список всех пользователей</h1>


    <c:forEach var="user" items="${requestScope.allUsers}">
        <ul>

            <li>Id: <c:out value="${user.id}"/></li>

            <li>Name: <c:out value="${user.name}"/></li>

            <li>Login: <c:out value="${user.login}"/></li>

            <li>Email: <c:out value="${user.email}"/></li>

            <li>Create date: <c:out value="${user.createAccount}"/></li>

            <li>Role: <c:out value="${user.successLevel}"/></li>

        </ul><hr align="center" width="90%" size="5" color="#dddddd" />

    </c:forEach>


    <form action="answer" method="post">
        <input type="submit" value="Меню">
    </form>

</body>
</html>
