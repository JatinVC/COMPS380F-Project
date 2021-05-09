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
                <p>食物介紹</p>
                <P> ${items.description}</p>
                <p>圖片:</p>
                <c:forEach items="${pictures}" var="pic">
                    ${pic.setStringContents()}
                    <img src="data:${pic.getMimeContentType()};base64,${pic.getStringContents()}" width="100px" height="100px" alt="alt" />
                </c:forEach>
                <p>價錢:${items.price}HKD</p>
                <p>供應:${items.quantity}</p>
                      <security:authorize access="hasRole('ADMIN')"> 
                  [<a href="<c:url value="/items/${items.id}/editZh" />">更改</a>]        
                  [<a href="<c:url value="/items/Zh/setAvaToTrue/${items.id}"/>">可供應</a>]
                  [<a href="<c:url value="/items/Zh/setAvaToFalse/${items.id}"/>">不可供應</a>]
                     </security:authorize>
                <p>留言</p>
                <security:authorize access="hasAnyRole('USER','ADMIN')"> 
                [<a href="<c:url value="/items/${items.id}/commentZh"/>">留言</a>]
                </security:authorize>
                <ul>
                    <c:forEach items="${comments}" var="comment">
                        <h4>${comment.getUsername()}</h4>
                        <li>${comment.getDate()}: ${comment.getContent()}</li>
                           <security:authorize access="hasRole('ADMIN')"> 
                                [<a href="<c:url value="/items/${items.id}/${comment.getId()}/deleteComment" />">刪除</a>]
                            </security:authorize></li>
                        <li></li>
                        <hr>
                    </c:forEach>
                </ul>
            </c:forEach>
        </div>
    </body>
    </html>