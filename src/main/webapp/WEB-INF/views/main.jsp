<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 26.04.2018
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/pager.js"></script>
    <script src="js/jquery-3.2.1.min.js"></script>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="loginForm">

        <form id="loginForm" action="/log_in">
            <button class="btn btn-success">Log in</button>
        </form>

        <form id="signupForm" action="/signup">
            <button class="btn btn-primary">Sign up</button>
        </form>

    </div>
</div>
</body>
</html>
