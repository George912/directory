<html>
<head>
    <meta charset="UTF-8">
    <title>Редактор контакта</title>
</head>
<body>
<form action="main.jsp" method="POST">
    Фамилия <input type="text" name="last_name"><br/>
    Имя <input type="text" name="name"><br/>
    Отчество <input type="text" name="middle_name"><br/>
    Телефон 1 <input type="text" name="phone1"><br/>
    Тип телефона 1 <select size="1" name="phone1_type">
        <%
            //todo
        %>
    </select><br/>
    Телефон 2 <input type="text" name="phone2"><br/>
    Тип телефона 2 <select size="1" name="phone2_type">
    <%
        //todo
    %>
    </select><br/>
    Эл. почта <input type="text" name="email"><br/>
    Заметки <textarea name="notes"></textarea><br/>
    Группы <textarea name="groups" ></textarea><br/>
    <input type="submit" value="Сохранить"/>
    <input type="button" value="Отмена"/>
</form>
</form>
</body>
</html>
