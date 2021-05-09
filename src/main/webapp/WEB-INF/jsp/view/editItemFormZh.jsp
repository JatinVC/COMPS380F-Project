<%-- Document : editItemForm Created on : May 6, 2021, 11:08:42 PM Author : Jatin --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>

        <body>
            <h2>更改食物資料</h2>
            <form:form method="POST" enctype="multipart/form-data" modelAttribute="itemForm">
                <form:label path="name">食物名稱</form:label><br />
                <form:input type="text" path="name" value="${item.getFoodName()}"/><br />
                <form:label path="price">價錢</form:label><br />
                <form:input type="text" path="price" value="${item.getPrice()}"/><br />
                <form:label path="description">描述</form:label><br />
                <form:input type="text" path="description" value="${item.getDescription()}"/><br />
                <input type="submit" value="更改">
            </form:form>
        </body>
        </html>