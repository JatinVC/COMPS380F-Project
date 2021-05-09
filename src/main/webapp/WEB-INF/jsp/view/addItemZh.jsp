<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Food Item</title>
    </head>
    <body>
        <h2>建立新貨品</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="itemForm">
            <form:label path="name">食物名稱</form:label><br />
            <form:input type="text" path="name" /><br />
            <form:label path="price">價格</form:label><br />
            <form:input type="text" path="price" /><br />
            <form:label path="description">描述</form:label><br />
            <form:input type="text" path="description" /><br />
            <input type="file" name="attachments" multiple="multiple" /><br /><br />
            <input type="submit" value="建立">
        </form:form>
    </body>
</html>