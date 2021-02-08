<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:include page="header.jsp"/>

<body>
<h1> User details:</h1>
ID: <c:out value="${user.id}"/> <br>
Email: <c:out value="${user.email}"/><br>
Username: <c:out value="${user.username}"/><br>
Group: <a href="/group?id=${user.userGroupId}">[id <c:out value="${user.userGroupId}"/>]</a> <br>
 <br>

<h1>User's solutions: </h1>

<table>
    <tr><th>ID</th> <th>Created</th> <th>Updated</th> <th>Description</th> <th>Exercise</th></tr>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td>${solution.description}</td>
            <td><a href="/exercise?id=${solution.exerciseId}"> [ex. ${solution.exerciseId}] </a> </td>
        </tr>
    </c:forEach>
</table>

</body>

<jsp:include page="footer.jsp"/>

</html>
