

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Itempage</title>
    </head>
    <body>
        <c:forEach items="${Items}" var="items">

            <h1>${items.foodName}</h1>
        <lable>Description:</lable>
        <P> ${items.description}</p>
        <lable>Photos:</lable> 
                <c:forEach items="${pictures}" var="pic">
        <img src="${pic.contentsString}" width="100px" height="100px" alt="alt"/>
            </c:forEach>
        <p>Price:${items.price}HKD</p>
        <p>availability of the item:${items.quantity}</p>
        <lable>Comments:</lable>
        <ul>
            <li>Jason:It is a good stuff but i use it to play game<li>
            <li>Kelly:It is bad, i use it two days and it boom~ boom boom<li>
        </ul>
    </c:forEach>

</body>
</html>
