<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <a href="/380fproject/login" >English</a>
        <a href="/380fproject/loginZh" >繁體中文</a>
        <c:if test="${param.error != null}">
            <p>Login failed.</p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p>You have logged out.</p>
        </c:if>
        <h2>Login</h2>
        <form action="login" method="POST">
            <label for="username">Username:</label><br/>
            <input type="text" id="username" name="username" /><br/><br/>
            <label for="password">Password:</label><br/>
            <input type="password" id="password" name="password" /><br/><br/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Log In"/>
        </form>
            <br/><br/>
            <a href="<c:url value="/user/userRegister" />">Register as a User</a><br /><br />
    </body>
</html>
