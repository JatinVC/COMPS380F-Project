<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Item List</title>
    </head>
    <body>
        
         <a href="<c:url value="/items/viewCart" />">ViewCart</a><br /><br />
        <h1>Item List</h1>
        
        <a href="<c:url value="/items/itemOne" />">Computer</a>       
        <a href="<c:url value="">
               <c:param name="action" value="addToCart" />
               <c:param name="item" value="Computer" />
           </c:url>">Add to cart</a><br /><br /> 
        
        <a href="<c:url value="/items/itemTwo" />">Mobile</a>
        <a href="<c:url value="">
               <c:param name="action" value="addToCart" />
               <c:param name="item" value="Mobile" />
           </c:url>">Add to cart</a><br /><br />
        
        <a href="<c:url value="/items/itemThree" />">Headset</a>
        <a href="<c:url value="">
               <c:param name="action" value="addToCart" />
               <c:param name="item" value="Headset" />
           </c:url>">Add to cart</a>
        
        <c:forEach items="${Items}" var="items">
            <a href="<c:url value="/items/${items.id}" />">${items.foodName}</a><br /><br />
        </c:forEach>



    </body>
</html>
