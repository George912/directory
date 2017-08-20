<%@ page import="ru.bellintegrator.app.model.Group" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактор группы</title>
</head>
<body>
<%Group group = (Group) request.getAttribute("group");%>
<form action="editor/group" method="POST">
    Имя <input type="text" name="name" value=<%=group.getName()%>><br/>
    Заметки <textarea name="notes" content=<%=group.getNotes()%>></textarea><br/>
    <input type="submit" value="Сохранить"/>
    <input type="button" value="Отмена"/>
</form>
</form>
</body>
</html>
