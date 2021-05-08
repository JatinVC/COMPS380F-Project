<!DOCTYPE html>
<html>
    <head>
        <title>User registration</title>
    </head>
    <body>
        <h2>註冊</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="SystemUser">
            <form:label path="username">用戶名稱</form:label><br/>
            <form:input type="text" path="username" /><br/><br/>
            <form:label path="password">密碼</form:label><br/>
            <form:input type="text" path="password" /><br/><br/>
            <form:label path="fullName">姓名</form:label><br/>
            <form:input type="text" path="fullName" /><br/><br/>
            <form:label path="phoneNumber">電話號碼</form:label><br/>
            <form:input type="text" path="phoneNumber" /><br/><br/>
            <form:label path="address">地址</form:label><br/>
            <form:input type="text" path="address" /><br/><br/>
            <br /><br />
            <input type="submit" value="註冊"/>
        </form:form>
    </body>
</html>