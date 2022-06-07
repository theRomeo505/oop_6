<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Hello Page</title>
        <link rel="stylesheet" type="text/css" href="styles/style.css">
    </head>
    <body>
        <p style="color: red">  <c:out value = "${warning}"/></p>
        <p style="color: green">  <c:out value = "${topup}"/></p>
    <div class="cards">
        <h2>Hello, ${fn:escapeXml(sessionScope.User.name)}!</h2>
        <!-- ------------------------------------------------------------------------ -->
        <c:forEach items="${cardAccountMap}" var="item">
            Card Number: ${item.key.cardNumber} ----- ${item.value.moneyAmount}$<br>
        </c:forEach>
        <!-- ------------------------------------------------------------------------ -->
    </div>
    <hr>
       <div class="form">
        <form method="post" action="client" id="payForm">
           <h3>Make payment</h3>
            Card:
            <select name="card">
                <c:forEach items="${cardAccountMap}" var="card">
                    <option value="${card.key.cardNumber}">${card.key.cardNumber}</option>
                </c:forEach>
            </select> <br>
           Money:
           <input type="number" min="0" id="money" name="money" /><br>
           Info:
            <input type="text" id="info" name="info" /><br>
            <input type="hidden" name="command" id="commandPay"/>

            <input type="submit" id="submitPay" class="button" name="submit" value="Add payment" onclick="setCommand('Pay', 'payForm')">
        </form>
       </div>
        <!-- ------------------------------------------------------------------------ -->
        <div class="form">
    <form method="post" action="client" id="topUpForm">
            <h3>Top up an account</h3>

            Card:
            <select name="card">
                <c:forEach items="${cardAccountMap}" var="card">
                    <option value="${card.key.cardNumber}">${card.key.cardNumber}</option>
                </c:forEach>
            </select><br>
            Money:
            <input type="number" min="0" id="topUpMoney" name="money" /><br>
            <input type="hidden" name="command" id="commandTopUp"/>
            <input type="submit" id="submitTopUp" class="button" name="submit" value="Top up account" onclick="setCommand('TopUp', 'topUpForm')" >
        </form>
        </div>
        <!-- ------------------------------------------------------------------------ -->
        <div class="form">
    <form method="post" action="client" id="blockForm">
            <h3>Block cards</h3>
            Card:
            <select name="card">
                <c:forEach items="${cardAccountMap}" var="card">
                    <option value="${card.key.cardNumber}">${card.key.cardNumber}</option>
                </c:forEach>
            </select><br>
            <input type="hidden" name="command" id="commandBlock"/>

            <input type="submit" id="submitBlock" class="button" name="submit" value="Block" onclick="setCommand('Block', 'blockForm')" >
        </form>
        </div>
        <!-- ------------------------------------------------------------------------ -->
        <script>
            function setCommand(command, formName)
            {
                document.getElementById("command" + command).value = command;
                var form = document.getElementById(formName);
                form.submit();
            }
        </script>
        <br>
        <h3>Payments</h3>
        <table>
            <c:forEach items="${sessionScope.Payments}" var="item">
                <tr>
                    <td><c:out value="${item}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

