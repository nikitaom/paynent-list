<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Payments</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        td {
            text-align: center;
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover{background-color:#f5f5f5}
    </style>
</head>
<body>
<h1>Payments</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>paymentDate</th>
        <th>paymentAmount</th>
        <th>channel</th>
    </tr>
    <c:forEach var="onePayment" items="${paymentsList}">
        <tr>
            <td><a href="/payments/${onePayment.id}">${onePayment.id}</a></td>
            <td>${onePayment.user.name}</td>
            <td>
                <fmt:formatDate pattern = "dd-MM-yyyy"
                                value = "${onePayment.paymentDate}" />
            </td>
            <td>${onePayment.paymentAmount}</td>
            <td>${onePayment.channel}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="makepayment">New payment</a>
</body>
</html>