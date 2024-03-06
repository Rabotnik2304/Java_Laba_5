<%--
  Created by IntelliJ IDEA.
  User: Informant
  Date: 05.03.2024
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>L2.1</title>
</head>
<body>
<p>Регистрация нового пользователя системы</p>

<form action="registration" method="POST">
    Email: <input type="text" name="email"/>
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="pass"/>
    <input type="submit" value="Зарегистрироваться">
</form>
<a href="log">Войти, если уже зарегистрирован.</a>
</body>
</html>
