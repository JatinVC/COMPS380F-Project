<!DOCTYPE html>
<html>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <br />
        <a href="<c:url value="/items/list" />">返回</a><br /><br />
        <br />
        <h2>用戶</h2>
        <a href="<c:url value="/user/createUser" />">建立用戶</a><br /><br />
        <c:choose>
            <c:when test="${fn:length(systemUsers) == 0}">
                <i>There are no users in the system.</i>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>用戶名稱</th><th>密碼</th><th>權限</th><th>行動</th>
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