<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>

<body>
<h1>Users: </h1>
<a href="/adminPanel/user/add">[add new user]</a>

<table>
    <tr><th>ID</th> <th>Email</th> <th>Username</th> <th>Group</th> </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.username}</td>
            <td>${user.userGroupId}</td>
            <td> <a href="user/edit?id=${user.id}">[edit]</a> </td>
            <td> <a href="user/delete?id=${user.id}">[delete]</a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
