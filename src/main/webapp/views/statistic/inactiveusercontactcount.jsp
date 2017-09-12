<%@ page import="ru.bellintegrator.app.model.AnalyticalInfo" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: neste_000
  Date: 12.09.2017
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta charset="UTF-8">
    <title>iucc</title>
</head>
<body>
<%AnalyticalInfo info = (AnalyticalInfo) request.getAttribute("info");%>

<h2>Список неактивных пользователей приложения</h2>
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
        for (Map.Entry<Integer, Long> entry2 : info.getInactiveUserCount().entrySet()) {%>
    <tr>
        <td><%=entry2.getKey()%>
        </td>
        <td><%=entry2.getValue()%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
