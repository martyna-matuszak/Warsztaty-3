<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:include page="header.jsp"/>

<body>
<h1>Users in group <c:out value="${group.name}"/>: </h1>

<table>
    <tr><th>ID</th> <th>Email</th> <th>Username</th> <th>Details</th></tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.username}</td>
            <td> <a href="/user?id=${user.id}">[see more]</a> </td>
        </tr>
    </c:forEach>
</table>

</body>

<jsp:include page="footer.jsp"/>

</html>
