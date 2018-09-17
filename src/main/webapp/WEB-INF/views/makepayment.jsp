<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add a payment</title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>

</head>
<body>
<h1>New payment</h1>
<%-- Difference between modelAttribute and commandName atributes in form tag in spring?

They are both referring to the same field, thus having same effect.
But, as the field name indicates, modelAttribute should be preferred, as others have also pointed out.--%>

<sf:form modelAttribute="newPayment" action="payments" method="post">
    <label for="userId"><s:message code="property.enterCustomerID"/></label>
    <sf:input path="userId"/>
    <sf:errors path="userId" cssClass="error"/>
    <br>
    <label for="paymentDate"><s:message code="property.enterPaymentDate"/></label>
    <sf:input path="paymentDate"/>
    <sf:errors path="paymentDate" cssClass="error"/>
    <br>
    <label for="paymentAmount"><s:message code="property.enterPaymentAmount"/></label>
    <sf:input path="paymentAmount"/>
    <sf:errors path="paymentAmount" cssClass="error"/>
    <br>
    <label for="channel"><s:message code="property.enterChannel"/></label>
    <sf:input path="channel"/>
    <sf:errors path="channel" cssClass="error"/>
    <br>
    <hr>
    <button type="submit" name="Send"><s:message code="property.sendPayment"/></button>

</sf:form>
</body>
</html>