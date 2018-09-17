<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 26.04.2018
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="loginForm">
        <form:form action="/signup" method="post" modelAttribute = "user">
            <form:hidden path="userId"/>
            <div class="form-group">
                <label >Name</label>
                <form:input type="text" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" path="name" class="form-control"  placeholder="Enter name"/>
            </div>
            <div class="form-group">
                <label >Email</label>
                <form:input type="email" pattern="[^ @]*@[^ @]*" path="email" class="form-control"  placeholder="Enter your email"/>
            </div>
            <div class="form-group">
                <label >Password</label>
                <form:input type="password" pattern=".{4,}" title="Four or more characters" path="password" class="form-control"  placeholder="Enter password"/>
            </div>
            <button type="submit" class="btn btn-success">Sign up</button>
        </form:form>
    </div>
</div>
</body>
</html>
