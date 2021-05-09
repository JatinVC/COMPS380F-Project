<!DOCTYPE html>
<html>
    <body>
        <br />
        <a href="<c:url value="/items/list" />">Go Back to the Main Page</a><br /><br />
        <br />
        <h2>Order History</h2>
        <c:choose>
            <c:when test="${fn:length(userOrder) == 0}">
                <i>You have not ordered anything yet</i>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Order Details</th><th>Date</th>
                    </tr>
                    <c:forEach items="${userOrder}" var="history">
                        <tr>
                            <td>${history.item}</td>
                            <td>${history.orderDate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>