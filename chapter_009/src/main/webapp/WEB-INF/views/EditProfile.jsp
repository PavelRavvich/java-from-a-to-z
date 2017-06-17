<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 17.06.17
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>


    <form method="post" action="edit_profile">

        <input type="text" required placeholder="new name" name="name"><br>
        <input type="text" required placeholder="new login" name="login"><br>
        <input type="text" required placeholder="new password" name="password"><br>
        <input type="text" required placeholder="new email" name="email"><br>

    </form>

    <c:out value = "${requestScope.error}"/>

</body>
</html>
