<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Item List</title>
    </head>
    <body>
        <h1>Item List</h1>
     <a href="<c:url value="/items/itemOne" />">Computer</a><br /><br />
      <a href="<c:url value="/items/itemTwo" />">Mobile</a><br /><br />
       <a href="<c:url value="/items/itemThree" />">Headset</a><br /><br />
       <c:forEach items="${Items}" var="items">
              <a href="<c:url value="/items/itemThree" />">${items.Items_id}</a><br /><br />
       </c:forEach>
      
     

    </body>
</html>
