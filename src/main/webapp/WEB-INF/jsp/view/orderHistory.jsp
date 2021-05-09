<!DOCTYPE html>
<html>
    <body>
        <br />
        <a href="<c:url value="/items/list" />">Go Back to the Main Page</a><br /><br />
        <br />
        <h2>Order History</h2>
        <c:choose>
            <c:when test="${fn:length(history) == 0}">
                <i>You have not ordered anything yet</i>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Order</th><th>Order Details</th><th>Date</th><th>Action</th>
                    </tr>
                    <c:forEach items="${systemUsers}" var="user">
                        <tr>
                            <td>${user.username}</td><td>${user.password}</td>
                            <td>
                                <c:forEach items="${user.roles}" var="role" varStatus="status">
                                    <c:if test="${!status.first}">, </c:if>
                                    ${role.role}
                                </c:forEach>
                            </td>
                            <td>
                                [<a href="<c:url value="/user/adminUpdate/${user.username}" />">Update</a>]
                            </td>
                            <td>
                                [<a href="<c:url value="/user/delete/${user.username}" />">Delete</a>]
                            </td> 
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>