<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>

    <style type="text/css">
        * {
            background-color: #fdf4ff;
        }

        .button {
            color: #ffffff;
            background-color: #0076af;
        }

        .form {
        padding: 50px;
        position: fixed; top: 40%; left: 50%;
        -webkit-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        }
    </style>
</head>
<body>

    <div class="form">

        <h1>Вход в систему</h1><br>
        <form method="post" action="">

            <input type="text" required placeholder="login" name="login"><br>
            <input type="password" required placeholder="password" name="password"><br><br>
            <input class="button" type="submit" value="Войти">

        </form>
        <c:out value="${warning}"/>

    </div>
</body>
</html>
