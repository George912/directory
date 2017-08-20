<%@ page import="java.util.List" %>
<%@ page import="ru.bellintegrator.app.model.Contact" %>
<%@ page import="ru.bellintegrator.app.model.Group" %>
<%@ page import="ru.bellintegrator.app.viewmodel.EditorAction" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Контакты</title>
</head>
<body>
<h2>Контакты пользователя</h2>

<table border="5" width="600" height="800">
    <tr>
        <td colspan="2">
            <form action="main.jsp" method="POST">
                <input type="text" name="find_by_name">
                <input type="submit" value="Найти"/>
            </form>
        </td>
    </tr>
    <tr>
        <td rowspan="2">
            <table border="5"  width="500" height="600">
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
                    <td>Редактировать</td>
                    <td>Удалить</td>
                </tr>
                </thead>
                <tbody>
                <%
                    //todo
                    List<Contact> contacts = (List<Contact>) request.getAttribute("contactList");
                    for (Contact contact: contacts){%>
                        <tr>
                            <td><%=contact.getFirstName()%></td>
                            <td><%=contact.getMiddleName()%></td>
                            <td><%=contact.getLastName()%></td>
                            <td><%=contact.getFirstPhoneNumber()%></td>
                            <td><%=contact.getFirstPhoneNumberType().name()%></td>
                            <td><%=contact.getSecondPhoneNumber()%></td>
                            <td><%=contact.getSecondPhoneNumberType().name()%></td>
                            <td><%=contact.getEmail()%></td>
                            <td>
                                <textarea>
                                    <%=contact.getNotes()%>
                                </textarea>
                            </td>
                            <td>
                                <textarea>
                                    <%=contact.getGroupList().toString()%>
                                </textarea>
                            </td>
                            <td><a href="editor/contact?contact_id=<%=contact.getId()%>&action=<%=EditorAction.UPDATE%>">Редактировать</a></td>
                            <td><a href="userdata?contact_id=<%=contact.getId()%>">Удалить</a></td>
                            <td></td>
                        </tr>
                <%}%>
                </tbody>
            </table>
        </td>
        <td>
            <input type="button" value="Создать"/>
        </td>
    </tr>
</table>
<br/>
<br/>
<h2>Группы пользователя</h2>
<br/>
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
        <td rowspan="2">
            <table border="5">
                <thead>
                <tr>
                    <td>Имя</td>
                    <td>Заметки</td>
                    <td>Редактировать</td>
                    <td>Удалить</td>
                </tr>
                </thead>
                <tbody>
                <%
                    //todo
                    List<Group> groups = (List<Group>) request.getAttribute("groupList");
                    for (Group group: groups){%>
                <tr>
                    <td><%=group.getName()%></td>
                    <td>
                        <textarea>
                                <%=group.getNotes()%>
                        </textarea>
                    </td>
                    <td><a href="editor/group?group_id=<%=group.getId()%>&action=<%=EditorAction.UPDATE%>">Редактировать</a></td>
                    <td><a href="userdata?group_id=<%=group.getId()%>">Удалить</a></td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </td>
        <td>
            <input type="button" value="Создать"/>
        </td>
    </tr>
</table>
</body>
</html>
