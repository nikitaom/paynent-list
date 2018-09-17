<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 26.04.2018
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <%--<link href="css/style.css" rel="stylesheet" type="text/css">--%>
</head>
<body>
<div class="container">
<div class="loginForm">
    <form:form role="form" action="/log_in" method="post">
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" class="form-control"  placeholder="Enter email"
                   required pattern="[^ @]*@[^ @]*"/>
        </div>
        <div class="form-group">
            <label >Password</label>
            <input type="password" name="password"class="form-control"  placeholder="Enter password"
                   required pattern="^[a-zA-Z0-9-_\.]{1,20}$">
        </div>
        <button type="submit" class="btn btn-success">Log in</button>
    </form:form>
</div>
</div>
</body>
</html>
