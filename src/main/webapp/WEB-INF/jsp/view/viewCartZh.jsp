<!DOCTYPE html>
<html>
    <head>
        <title>View Cart</title>
    </head>
    <body>
        <a href="/cart" >English</a>
        <a href="/cart/Zh" >繁體中文</a><br /><br />
        <a href="<c:url value="/items/list/viewCart/emptyCart" />">清空購物車</a>
        <h1>購物車</h1>
        <a href="<c:url value="/items/list"/>">食物清單</a><br /><br />
        <c:choose>
            <c:when test="${empty cart}">
                您的購物車是空的!
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


