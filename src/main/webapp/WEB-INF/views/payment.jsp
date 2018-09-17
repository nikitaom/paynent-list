<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Payment</title>
</head>
<body>
<h1>Payment ID: ${onePayment.id}</h1>
<table>
    <tr>
        <td><i>Customer</i></td>
        <td>${onePayment.customer.customerName}</td>
    </tr>
    <tr>
        <td><i>PaymentDate</i></td>
        <td>
            <fmt:formatDate pattern = "dd-MM-yyyy"
                            value = "${onePayment.paymentDate}" />
        </td>
    </tr>
    <tr>
        <td><i>paymentAmount</i></td>
        <td>${onePayment.paymentAmount}</td>
    </tr>
    <tr>
        <td><i>channel</i></td>
        <td>${onePayment.channel}</td>
    </tr>
</table>
<a href="/payments">Back</a>
</body>
</html>