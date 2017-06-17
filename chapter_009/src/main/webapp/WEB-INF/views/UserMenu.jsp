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

    <style type="text/css">
        .button {
            position: relative;
            display: inline-block;
            font-family: Arial,Helvetica,FreeSans,"Liberation Sans","Nimbus Sans L",sans-serif;
            font-size: 1.5em;
            font-weight: 700;
            color: rgb(245,245,245);
            text-shadow: 0 -1px rgba(0,0,0,.1);
            text-decoration: none;
            user-select: none;
            padding: .3em 1em;
            outline: none;
            border: none;
            border-radius: 3px;
            background: #0c9c0d linear-gradient(#82d18d, #0c9c0d);
            box-shadow: inset #72de26 0 -1px 1px, inset 0 1px 1px #98ff98, #3caa3c 0 0 0 1px, rgba(0,0,0,.3) 0 2px 5px;
            -webkit-animation: pulsate 1.2s linear infinite;
            animation: pulsate 1.2s linear infinite;
        }
        input.button4:hover {
            -webkit-animation-play-state: paused;
            animation-play-state: paused;
            cursor: pointer;
        }
        input.button4:active {
            top: 1px;
            color: #fff;
            text-shadow: 0 -1px rgba(0,0,0,.3), 0 0 5px #ffd, 0 0 8px #fff;
            box-shadow: 0 -1px 3px rgba(0,0,0,.3), 0 1px 1px #fff, inset 0 1px 2px rgba(0,0,0,.8), inset 0 -1px 0 rgba(0,0,0,.05);
        }
        @-webkit-keyframes pulsate {
            50% {color: #fff; text-shadow: 0 -1px rgba(0,0,0,.3), 0 0 5px #ffd, 0 0 8px #fff;}
        }
        @keyframes pulsate {
            50% {color: #fff; text-shadow: 0 -1px rgba(0,0,0,.3), 0 0 5px #ffd, 0 0 8px #fff;}
        }
    </style>

</head>
<body>

<h2>Меню</h2><br />

<form method="POST" action="menu">

    <label for="find">Найти пользователя по id</label>
    <input type="radio" name="act" value="find" id="find"><br>

    <label for="edit">Редактировать профиль</label>
    <input type="radio" name="act" value="edition" id="edit"><br>

    <label for="all">Список всех пользователей</label>
    <input type="radio" name="act" value="all" id="all"><br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>
<br />


<form method="post" action="logout">
    <input class="button" type="submit" value="logout" name="logout"><br>
</form>

</body>
</html>
