<%@ page import="java.util.Map" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Item List</title>
    </head>

    <body style="text-align: center;background-color:white;">
        <div style="background-color: aqua;height: 100vh;width: 70%;padding: 0%;margin-left: auto;margin-right: auto;">
            <a href="<c:url value=" /380fproject/items/viewCart" />">ViewCart</a><br /><br />
            <h1>Item List</h1>
            <c:forEach items="${Items}" var="items">
                <a style="font-size: 30px;" href="<c:url value="
                    /380fproject/items/itemInfo/${items.id}" />">${items.id}.${items.foodName}</a>
                <a href="<c:url value="">
                   <c:param name=" action" value="addToCart" />
                <c:param name="item" value="${items.foodName}" />
                </c:url>">Add to cart</a>
                 [<a href="<c:url value="/380fproject/items/delete/${items.id}"/>">Delete</a>]
                 <br/>
               
            </c:forEach>
        </div>
    </body>
    </html>