<%@ page import="ru.bellintegrator.app.model.Group" %>
<%@ page import="ru.bellintegrator.app.viewmodel.EditorAction" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактор группы</title>
</head>
<body>
<%
    Group group = (Group) request.getAttribute("group");
    request.setAttribute("group", group);
    EditorAction action = EditorAction.getActionFromString(request.getParameter("action"));

    if (action == EditorAction.UPDATE) {
        request.setAttribute("action", EditorAction.UPDATED);

    } else if (action == EditorAction.CREATE) {
        request.setAttribute("action", EditorAction.CREATED);
    }
%>

<form action="/directory/editor/group" method="POST">
    Имя <input type="text" name="name" value=<%=group.getName()%>><br/>
    Заметки <textarea name="notes"><%=group.getNotes()%></textarea><br/>
    <input type="submit" value="Сохранить"/>
    <input type="button" value="Отмена"/>
</form>
</form>
</body>
</html>
