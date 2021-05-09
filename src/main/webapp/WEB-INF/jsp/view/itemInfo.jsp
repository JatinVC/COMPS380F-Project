<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Item List</title>
    </head>

    <body style="text-align: center;background-color:white;">
        <div style="background-color: aqua;height: 100vh;width: 70%;padding: 0%;margin-left: auto;margin-right: auto;">
            <security:authorize access="hasAnyRole('USER','ADMIN')"> 
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            </security:authorize>
            <a href="/380fproject/items/list" >English</a>
            <a href="/380fproject/items/listZh" >繁體中文</a><br /><br />
            <a href="<c:url value="/cart" />">ViewCart</a><br /><br />
            <security:authorize access="hasAnyRole('USER','ADMIN')"> 
                <a href="<c:url value="/user/userUpdate" />">Update Your Personal Information</a>
            </security:authorize>
            <br/><br/>
            <security:authorize access="hasRole('ADMIN')"> 
                [<a href="<c:url value="/user/list" />">User Management</a>]
            </security:authorize>   
            <h1>Item List</h1><br /><br />
            <c:forEach items="${Items}" var="items">
                <security:authorize access="hasRole('ADMIN')"> 
                    [<a href="<c:url value="/items/${items.id}/delete/" />">Delete</a>]
                </security:authorize>
                <a style="font-size: 30px;" href="<c:url value="/items/${items.id}" />">${items.id}.${items.foodName}</a>
                <a href="<c:url value="/cart/addToCart">

                   <c:param name="item" value="${items.foodName}" />
                    </c:url>">Add to cart</a>
                <br />

            </c:forEach>
        </div>
    </body>
</html>