<%@ page import="ru.bellintegrator.app.model.AnalyticalInfo" %>
<%@ page import="ru.bellintegrator.app.servlets.MainServlet" %>
<%@ page import="java.util.Map" %>
<html>
<body>
<html>
<head>
    <meta charset="UTF-8">
    <title>Контакты</title>
</head>
<body>
<%AnalyticalInfo info = (AnalyticalInfo) request.getAttribute("info");%>
<h2>Статистика приложения</h2>

<table border="5">
    <tr>
        <td>
            Общее количество пользователей <input type="text" name="common_user_count" value=<%=info.getUserCount()%>>
        </td>
        <td>
            Среднее количество пользователей в группах <input type="text" name="avg_user_count_in_groups" value=<%=info.getAvgUserCountInGroups()%>>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            Среднее количество контактов у пользователей <input type="text" name="avg_user_contact_count" value=<%=info.getAvgUsersContactCount()%>>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            Количество контактов каждого пользователя
            <br/>
            <table border="5">
                <thead>
                <tr>
                    <td>Ид пользователя</td>
                    <td>Количество контактов</td>
                </tr>
                </thead>
                <tbody>
                    <%
                        for(Map.Entry<Integer, Long> entry: info.getEachUserContactCount().entrySet()){%>
                        <tr>
                            <td><%=entry.getKey()%></td>
                            <td><%=entry.getValue()%></td>
                        </tr>
                    <%{%>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            Количество групп каждого пользователя
            <br/>
            <table border="5">
                <thead>
                <tr>
                    <td>Ид пользователя</td>
                    <td>Количество групп</td>
                </tr>
                </thead>
                <tbody>
                    <%
                        for(Map.Entry<Integer, Long> entry1: info.getEachUserGroupCount().entrySet()){%>
                        <tr>
                            <td><%=entry1.getKey()%></td>
                            <td><%=entry1.getValue()%></td>
                        </tr>
                    <%{%>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            Список неактивных пользователей приложения
            <br/>
            <table border="5">
                <thead>
                <tr>
                    <td>Ид пользователя</td>
                    <td>Количество контактов</td>
                </tr>
                </thead>
                <tbody>
                    <%
                        for(Map.Entry<Integer, Long> entry2: info.getInactiveUserCount().entrySet()){%>
                        <tr>
                            <td><%=entry2.getKey()%></td>
                            <td><%=entry2.getValue()%></td>
                        </tr>
                    <%{%>
                </tbody>
            </table>
        </td>
    </tr>
</table>
</body>
</html>

</body>
</html>
