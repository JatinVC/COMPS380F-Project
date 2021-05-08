<%-- 
    Document   : commentForm
    Created on : May 5, 2021, 2:34:53 PM
    Author     : Jatin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Comment</title>
    </head>
    <body>
        <h1>Create a comment</h1>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="commentForm">
            <form:label path="commentContent">Comment</form:label>
            <form:input type="text" path="commentContent" /><br />
            <input type="submit" value="Submit">
        </form:form>
    </body>
</html>