<!DOCTYPE html>
<html lang ="ab">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
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
        <h2>登入</h2>
        <form action="login" method="POST">
            <label for="username">用戶名稱:</label><br/>
            <input type="text" id="username" name="username" /><br/><br/>
            <label for="password">密碼:</label><br/>
            <input type="password" id="password" name="password" /><br/><br/>
            <input type="checkbox" id="remember-me" name="remember-me" />
            <label for="remember-me">記住密碼</label><br/><br/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="登入"/>
        </form>
    </body>

</html>
