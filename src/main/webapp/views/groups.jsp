<html>
<head>
    <meta charset="UTF-8">
    <title>Группы</title>
</head>
<body>
<h2>Группы пользователя</h2>

<table border="5">
    <tr>
        <td colspan="2">
            <form action="main.jsp" method="POST">
                <input type="text" name="find_by_name">
                <input type="submit" value="Найти"/>
            </form>
        </td>
    </tr>
    <tr>
        <td rowspan="3">
            <table border="5">
                <thead>
                <tr>
                    <td>Имя</td>
                    <td>Заметки</td>
                </tr>
                </thead>
                <tbody>
                <%
                    //todo
                %>
                </tbody>
            </table>
        </td>
        <td>
            <input type="button" value="Создать"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" value="Редактировать"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" value="Удалить"/>
        </td>
    </tr>
</table>
</body>
</html>
