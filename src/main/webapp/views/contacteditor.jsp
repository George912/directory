<%@ page import="ru.bellintegrator.app.EditorAction" %>
<%@ page import="ru.bellintegrator.app.model.Contact" %>
<%@ page import="ru.bellintegrator.app.model.Group" %>
<%@ page import="ru.bellintegrator.app.model.PhoneNumberType" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактор контакта</title>
</head>
<body>
<%
    Contact contact = (Contact) request.getAttribute("contact");
    EditorAction action = EditorAction.getActionFromString(request.getParameter("action"));
    List<Group> groupList = null;

    if (action == EditorAction.UPDATE) {
%>
<form action="/directory/editor/contact?action=<%=EditorAction.UPDATED%>" method="POST">
    <%}%>

    <% if (action == EditorAction.CREATE) {
        groupList = (List<Group>) request.getAttribute("groups");
    %>
    <form action="/directory/editor/contact?action=<%=EditorAction.CREATED%>" method="POST">
        <%}%>

        Фамилия <input type="text" name="last_name" value=<%=contact.getLastName()%>><br/>
        Имя <input type="text" name="name" value=<%=contact.getFirstName()%>><br/>
        Отчество <input type="text" name="middle_name" value=<%=contact.getMiddleName()%>><br/>
        Телефон 1 <input type="text" name="phone1" value=<%=contact.getFirstPhoneNumber()%>><br/>
        Тип телефона 1 <select size="1" name="phone1_type">
        <%
            for (PhoneNumberType type : PhoneNumberType.values()) {%>
        <option value=<%=type%>><%=type.getName()%>
        </option>
        <%}%>
    </select><br/>
        Телефон 2 <input type="text" name="phone2" value=<%=contact.getSecondPhoneNumber()%>><br/>
        Тип телефона 2 <select size="1" name="phone2_type">
        <%
            for (PhoneNumberType type : PhoneNumberType.values()) {%>
        <option value=<%=type%>><%=type.getName()%>
        </option>
        <%}%>
    </select><br/>
        Эл. почта <input type="text" name="email" value=<%=contact.getEmail()%>><br/>
        Заметки <textarea name="notes"><%=contact.getNotes()%></textarea><br/>
        Группы
        <br/>
        <% if (action == EditorAction.CREATE) {
            for (Group group : groupList) {%>
        <input type="checkbox" name="group" value=<%=group.getId()%>> <%=group.getName()%><br/>
        <%}%>
        <%}%>
        <% if (action == EditorAction.UPDATE) {
            for (Group group : contact.getGroupList()) {%>
        <input type="checkbox" name="group" value=<%=group.getId()%> checked> <%=group.getName()%><br/>
        <%}%>
        <%}%>
        <br/>
        <input type="hidden" name="contact_id" value=<%=contact.getId()%>/>
        <input type="submit" value="Сохранить"/>
        <input type="button" value="Отмена"/>
    </form>
</form>
</body>
</html>
