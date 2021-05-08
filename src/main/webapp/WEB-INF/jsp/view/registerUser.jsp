<!DOCTYPE html>
<html>
    <head>
        <title>User registration</title>
    </head>
    <body>
        <h2>Register as a User</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="SystemUser">
            <form:label path="username">Username</form:label><br/>
            <form:input type="text" path="username" /><br/><br/>
            <form:label path="password">Password</form:label><br/>
            <form:input type="text" path="password" /><br/><br/>
            <form:label path="fullName">Full Name</form:label><br/>
            <form:input type="text" path="fullName" /><br/><br/>
            <form:label path="phoneNumber">Phone Number</form:label><br/>
            <form:input type="text" path="phoneNumber" /><br/><br/>
            <form:label path="address">Delivery Address</form:label><br/>
            <form:input type="text" path="address" /><br/><br/>
            <br /><br />
            <input type="submit" value="Add User"/>
        </form:form>
    </body>
</html>