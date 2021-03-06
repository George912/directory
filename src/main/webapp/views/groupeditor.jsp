<%@ page import="ru.bellintegrator.app.EditorAction" %>
<%@ page import="ru.bellintegrator.app.model.Group" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Редактор группы</title>
</head>
<body>
<%
    Group group = (Group) request.getAttribute("group");
    EditorAction action = EditorAction.getActionFromString(request.getParameter("action"));

    if (action == EditorAction.UPDATE) {%>
<form action="/directory/editor/group?action=<%=EditorAction.UPDATED%>" method="POST">
    <%}%>

    <% if (action == EditorAction.CREATE) {%>
    <form action="/directory/editor/group?action=<%=EditorAction.CREATED%>" method="POST">
        <%}%>

        Имя <input type="text" name="name" value=<%=group.getName()%>/><br/>
        Заметки <textarea name="notes"><%=group.getNotes()%></textarea><br/>
        <input type="hidden" name="group_id" value=<%=group.getId()%>/>
        <input type="submit" value="Сохранить"/>
    </form>
</form>
</body>
</html>
