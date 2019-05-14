<%--
  Created by IntelliJ IDEA.
  User: hszm2
  Date: 05/05/2019
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Você está autenticado!</title>
</head>
<body>

    <%
        String usuario = (String) session.getAttribute("APID");

        if(usuario == null){
            response.sendRedirect("index.jsp");
        }
    %>

    <h1>Usuário autenticado!</h1>

    <form action="login" method="post">
        <input type="submit" name="action" value="logoff">
    </form>

</body>
</html>
