<%@ page import="ru.bellintegrator.app.model.AnalyticalInfo" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: neste_000
  Date: 12.09.2017
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta charset="UTF-8">
    <title>eucc</title>
</head>
<body>
<%AnalyticalInfo info = (AnalyticalInfo) request.getAttribute("info");%>
<h2>Количество контактов каждого пользователя</h2>
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
        for (Map.Entry<Integer, Long> entry : info.getEachUserContactCount().entrySet()) {%>
    <tr>
        <td><%=entry.getKey()%>
        </td>
        <td><%=entry.getValue()%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
