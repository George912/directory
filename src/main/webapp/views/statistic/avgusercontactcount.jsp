<%@ page import="ru.bellintegrator.app.model.AnalyticalInfo" %><%--
  Created by IntelliJ IDEA.
  User: neste_000
  Date: 12.09.2017
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>aucc</title>
</head>
<body>
<%AnalyticalInfo info = (AnalyticalInfo) request.getAttribute("info");%>
<h2>Среднее количество контактов у пользователей</h2>
<br/>
<input type="text" name="avg_user_contact_count" value=<%=info.getAvgUsersContactCount()%>>
</body>
</html>
