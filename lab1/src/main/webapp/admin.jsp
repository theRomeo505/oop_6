<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Hello Page</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
    <body>

        <h2>Hello, ${sessionScope.User.name}!</h2>
        <h4>${sessionScope.bankName} bank</h4>
        <hr>       <div class="form">
        <form method="post" action="client" id="unblockForm">
            <c:forEach items="${blockedCards}" var="item">
                Card Number: ${item.cardNumber}<br>
                <input type="submit" class="button"  name="submit" value="Unblock" onclick="unblock(${item.cardNumber})">

            </c:forEach>
            <input type="hidden" name="card" id="cardNum"/>
            <input type="hidden" name="command" id="command"/>

        </form></div>
        <script>
            function unblock(cardNumber)
            {
                document.getElementById("cardNum").value = cardNumber;
                document.getElementById("command").value = "Unblock";
                var form = document.getElementById("unblockForm");
                form.submit();
            }
        </script>

    </body>
</html>

