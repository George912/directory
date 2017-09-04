<html>
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body xmlns:jsp="http://java.sun.com/JSP/Page"
      xmlns:sec="http://www.springframework.org/security/tags"
      version="2.0">

<sec:authorize access="isAnonymous()">
    <form action="login" method="POST">
            Логин <input type="text" name="username">
            <br/>
            Пароль <input type="password" name="password"/>
            <br/>
            <input type="submit" value="Войти"/>
            <br/>
            <input type="reset" value="Отмена"/>
    </form>
</sec:authorize>
</body>
</html>
