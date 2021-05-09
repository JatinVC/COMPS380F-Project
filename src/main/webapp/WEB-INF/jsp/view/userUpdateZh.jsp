<!DOCTYPE html>
<html>
    <head>
        <title>用戶資料</title>
    </head>
    <body>
        <h2>更新用戶資料</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="SystemUser">
            <form:label path="password">密碼</form:label><br/>
            <form:input type="text" path="password" /><br/><br/>
            <form:label path="fullName">姓名</form:label><br/>
            <form:input type="text" path="fullName" /><br/><br/>
            <form:label path="phoneNumber">電話號碼</form:label><br/>
            <form:input type="text" path="phoneNumber" /><br/><br/>
            <form:label path="address">地址</form:label><br/>
            <form:input type="text" path="address" /><br/><br/>
            <br /><br />
            <input type="submit" value="更新"/>
        </form:form>
    </body>
</html>