<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:include page="header.jsp"/>

<body>
<h1>Users groups: </h1>

<table>
<tr><th>ID</th> <th>Name</th> <th>See users</th> </tr>
<c:forEach items="${groups}" var="group">
    <tr>
        <td>${group.id}</td>
        <td>${group.name}</td>
        <td> <a href="/group?id=${group.id}"> [view] </a> </td>

    </tr>
</c:forEach>
</table>

</body>

<jsp:include page="footer.jsp"/>

</html>
