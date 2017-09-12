<%@ page import="ru.bellintegrator.app.model.AnalyticalInfo" %><%--
  Created by IntelliJ IDEA.
  User: neste_000
  Date: 12.09.2017
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>uc</title>
</head>
<body>
<%AnalyticalInfo info = (AnalyticalInfo) request.getAttribute("info");%>
<h2>Общее количество пользователей</h2>
<br/>
<input type="text" name="common_user_count" value=<%=info.getUserCount()%>>
</body>
</html>
