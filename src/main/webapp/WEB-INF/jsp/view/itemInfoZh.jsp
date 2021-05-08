<%@ page import="java.util.Map" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Item List</title>
    </head>
    
    <body style="text-align: center;background-color:white;">
        <div style="background-color: aqua;height: 100vh;width: 70%;padding: 0%;margin-left: auto;margin-right: auto;">
        <a href="/380fproject/items/list" >English</a>
        <a href="/380fproject/items/listZh" >繁體中文</a><br /><br />
            <a href="<c:url value="/cart/Zh" />">購物車</a><br /><br />
            <h1>食物清單</h1>
            <c:forEach items="${Items}" var="items">
                <security:authorize access="hasRole('ADMIN')"> 
                [<a href="<c:url value="/380fproject/items/${items.id}/delete/" />">清除</a>]
                </security:authorize>
                <a style="font-size: 30px;" href="<c:url value="
                    /380fproject/items/${items.id}" />">${items.id}.${items.foodName}</a>
                <a href="<c:url value="/cart/addToCart">

                <c:param name="item" value="${items.foodName}" />
                </c:url>">加入購物車</a>
                <br />

            </c:forEach>
        </div>
    </body>
    </html>