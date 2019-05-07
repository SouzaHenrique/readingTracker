<%--
  Created by IntelliJ IDEA.
  User: hszm2
  Date: 04/05/2019
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Reading Tracker!</title>
</head>
<body>
    <h1>Testando sessão de usuário</h1>

    <form action="login" method="post">

        <label>Usuário: &nbsp <input type="text" name="usuario"/><br></label>
        <label>Senha: &nbsp <input type="text" name="senha"/><br></label>
        <br>
        <br>
        <input type="submit" name="action" value="login">

    </form>



</body>
</html>
