<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Itempage</title>
    </head>
    <style>
        p {
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
                    ${pic.setStringContents()}
                    <img src="data:${pic.getMimeContentType()};base64,${pic.getStringContents()}" width="100px" height="100px" alt="alt" />
                </c:forEach>
                <p>Price:${items.price}HKD</p>
                <p>availability of the item:${items.quantity}</p>
                <security:authorize access="hasRole('ADMIN')"> 
                    [<a href="<c:url value="/items/${items.id}/edit" />">Edit item page</a>]
                    [<a href="<c:url value="/items/setAvaToTrue/${items.id}"/>">Set it to Yes</a>]
                    [<a href="<c:url value="/items/setAvaToFalse/${items.id}"/>">Set it to No</a>]
                </security:authorize>
                <p>Comments:</p>   
                <security:authorize access="hasAnyRole('USER','ADMIN')"> 
                [<a href="<c:url value="/items/${items.id}/comment"/>">Create a comment</a>]
                </security:authorize>
                <ul style="list-style-type: none;">
                    <c:forEach items="${comments}" var="comment">
                        <h4>${comment.getUsername()}</h4>
                        <li>${comment.getDate()}: ${comment.getContent()}  
                            <security:authorize access="hasRole('ADMIN')"> 
                                [<a href="<c:url value="/items/${items.id}/${comment.getId()}/deleteComment" />">Delete</a>]
                            </security:authorize></li>
                        <li></li>
                        <hr>
                    </c:forEach>
                </ul>
            </c:forEach>
        </div>
    </body>
</html>