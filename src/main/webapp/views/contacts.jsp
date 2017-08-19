<html>
<head>
    <meta charset="UTF-8">
    <title>Контакты</title>
</head>
<body>
<h2>Контакты пользователя</h2>

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
                    <td>Отчество</td>
                    <td>Фамилия</td>
                    <td>Телефон 1</td>
                    <td>Тип телефона 1</td>
                    <td>Телефон 2</td>
                    <td>Тип телефона 2</td>
                    <td>Эл. почта</td>
                    <td>Заметки</td>
                    <td>Группы</td>
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
