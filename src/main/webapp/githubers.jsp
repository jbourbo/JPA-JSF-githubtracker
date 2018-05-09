<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 23/04/2018
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<div class="container">

    <h2>Githubers Table</h2>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Login</th>
            <th>Avatar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${githubers}" var="githuber">
            <tr>
                <td><c:out value="${githuber.id}" /></td>
                <td><c:out value="${githuber.name}" /></td>
                <td><c:out value="${githuber.email}" /></td>
                <td><c:out value="${githuber.login}" /></td>
                <td><img src="<c:out value="${githuber.avatarUrl}" />" alt="github picture" style="width: 100px;height: 100px;"></td>
                <td><form action="${pageContext.request.contextPath}/delete" enctype="application/x-www-form-urlencoded" method="post">
                    <input type="hidden" name="GithuberLogin" value="${githuber.login}">
                    <button type="submit">Untrack</button>
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
