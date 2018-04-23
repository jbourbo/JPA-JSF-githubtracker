<%@ page import="fr.wildcodeschool.githubtracker.model.Githuber" language="java" %>
<%@ page import="java.util.List" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<ul class="list-group">
<%

    String result = "";
    for(Githuber g: (List<Githuber>)request.getAttribute("githubers")){
        String tmp = "<li class=\"list-group-item\">";
        tmp += g.getName();
        tmp +=  "</li><br>";
        out.println(tmp);

    }
%>
</ul>
</div>
</body>
</html>
