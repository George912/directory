<%@ page import="ru.bellintegrator.app.model.AnalyticalInfo" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: neste_000
  Date: 12.09.2017
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>eugc</title>
</head>
<body>
<%AnalyticalInfo info = (AnalyticalInfo) request.getAttribute("info");%>
<h2>Количество групп каждого пользователя</h2>
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
        for (Map.Entry<Integer, Long> entry1 : info.getEachUserGroupCount().entrySet()) {%>
    <tr>
        <td><%=entry1.getKey()%>
        </td>
        <td><%=entry1.getValue()%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
