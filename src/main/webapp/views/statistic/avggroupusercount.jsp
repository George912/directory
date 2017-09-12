<%@ page import="ru.bellintegrator.app.model.AnalyticalInfo" %><%--
  Created by IntelliJ IDEA.
  User: neste_000
  Date: 12.09.2017
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta charset="UTF-8">
    <title>aguc</title>
</head>
<body>
<%AnalyticalInfo info = (AnalyticalInfo) request.getAttribute("info");%>
<h2>Среднее количество пользователей в группах</h2>
<br/>
<input type="text" name="avg_user_count_in_groups" value=<%=info.getAvgUserCountInGroups()%>>
</body>
</html>
