<%-- 
    Document   : addItem
    Created on : Apr 29, 2021, 4:11:47 PM
    Author     : Jatin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Food Item</title>
    </head>
    <body>
        <h2>Add a new Item</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="itemForm">
            <form:label path="foodName">Food Name</form:label><br />
            <form:input type="text" path="foodName" /><br />
            <form:label path="price">Price</form:label><br />
            <form:input type="text" path="price" /><br />
            <input type="file" name="attachments" multiple="multiple" /><br /><br />
            <input type="submit" value="Submit">
        </form:form>
    </body>
</html>
