<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Comment</title>
    </head>
    <body>
        <h1>留言</h1>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="commentForm">
            <form:label path="commentContent">留言</form:label>
            <form:input type="text" path="commentContent" /><br />
            <input type="submit" value="確認">
        </form:form>
    </body>
</html>