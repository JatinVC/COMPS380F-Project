<%-- Document : editItemForm Created on : May 6, 2021, 11:08:42 PM Author : Jatin --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>

        <body>
            <h2>Edit an Item</h2>
            <form:form method="POST" enctype="multipart/form-data" modelAttribute="itemForm">
                <form:label path="name">Food Name</form:label><br />
                <form:input type="text" path="name" value="${item.getName()}"/><br />
                <form:label path="price">Price</form:label><br />
                <form:input type="text" path="price" value="${item.getPrice()}"/><br />
                <input type="file" name="attachments" multiple="multiple" /><br /><br />
                <input type="submit" value="Submit">
            </form:form>
        </body>
        </html>