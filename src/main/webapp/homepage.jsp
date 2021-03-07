<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:include page="header.jsp"/>

<body>
<h1>Lately added solutions: </h1>

<table>
<tr><th>ID</th> <th>Created</th> <th>Graded</th> <th>Description</th> <th>Exercise</th><th>User</th><th>Grade</th></tr>
<c:forEach items="${solutions}" var="solution">
    <tr>
        <td>${solution.id}</td>
        <td>${solution.created}</td>
        <td>${solution.updated}</td>
        <td>${solution.description}</td>
        <td><a href="/exercise?id=${solution.exerciseId}"> [ex. ${solution.exerciseId}] </a> </td>
        <td><a href="/user?id=${solution.userId}"> [user ${solution.userId}]</a> </td>
        <th>
            <c:choose>
                <c:when test="${solution.grade == 0.0}">
                    none
                </c:when>
                <c:otherwise>
                    ${solution.grade}
                </c:otherwise>
            </c:choose>
        </th>
    </tr>
</c:forEach>
</table>

<h1>Groups</h1>
<a href="/groups">[go to groups]</a>

</body>

<jsp:include page="footer.jsp"/>

</html>