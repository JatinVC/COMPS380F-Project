<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Item List</title>
    </head>
    <body>

        <a href="<c:url value="/items/viewCart" />">ViewCart</a><br /><br />
        <h1>Item List</h1>
        <c:forEach items="${Items}" var="items">
            <a href="<c:url value="/items/${items.id}" />">${items.foodName}</a>
            <a href="<c:url value="">
                   <c:param name="action" value="addToCart" />
                   <c:param name="item" value="${items.foodName}" />
               </c:url>">Add to cart</a><br /><br /> 
        </c:forEach>





    </body>
</html>
