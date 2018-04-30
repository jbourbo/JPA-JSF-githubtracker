<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 30/04/2018
  Time: 08:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/track" enctype="application/x-www-form-urlencoded" method="post">
        <p>${pageContext.request.contextPath}</p>
        <label>login:</label>
        <input name="login" type="text">
        <br/>
        <button type="submit">Go!</button>
    </form>
</body>
</html>
