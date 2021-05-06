

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Itempage</title>
    </head>
    <style>
        p{
            font-size: 25px;
        }
    </style>
    <body style="text-align: center;background-color:white;">
        <div style="background-color: aqua;height: 100vh;width: 70%;padding: 0%;margin-left: auto;margin-right: auto;">
        <c:forEach items="${Items}" var="items">

            <h1>${items.foodName}</h1>
        <p>Description:</p>
        <P> ${items.description}</p>
        <p>Photos:</p> 
                <c:forEach items="${pictures}" var="pic">
        <img src="${pic.contentsString}" width="100px" height="100px" alt="alt"/>
            </c:forEach>
        <p>Price:${items.price}HKD</p>
        <p>availability of the item:${items.quantity}</p>
        <p>Comments:</p>
        <ul>
            <li>Jason:It is a good stuff but i use it to play game<li>
            <li>Kelly:It is bad, i use it two days and it boom~ boom boom<li>
        </ul>
    </c:forEach>
    </div>

</body>
</html>
