<!DOCTYPE html>
<html>
    <head>
        <title>View Cart</title>
    </head>
    <body>
        <a href="/cart" >English</a>
        <a href="/cart/Zh" >繁體中文</a><br /><br />
        <a href="<c:url value="/cart/emptyCart" />">Empty Cart</a>
        <h1>View Cart</h1>
        <a href="<c:url value="/items/list"/>">Item List</a><br /><br />
        <c:choose>
            <c:when test="${empty cart}">
                Your cart is empty
            </c:when>
            <c:otherwise>
                <ul>
                <c:forEach var="cart" items="${cart}">
                    <li>${cart.key} (qty: ${cart.value})</li>
                </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </body>
</html>


