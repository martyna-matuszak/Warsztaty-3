<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>

<body>

<h2><c:out value="${message}"/> </h2>


<table>
    <tr><th>ID</th> <th>Created</th> <th>Graded</th> <th>Description</th> <th>Exercise</th> <th>User</th> <th>Grade</th></tr>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td>${solution.description}</td>
            <td>${solution.exerciseId}</td>
            <td>${solution.userId}</td>

            <c:choose>
                <c:when test="${solution.grade == 0.0}">
                    <td>none</td>
                    <td><a href="/adminPanel/solution/grade?id=${solution.id}">[Add grade]</a></td>
                </c:when>
                <c:otherwise>
                    <td>${solution.grade}</td>
                    <td><a href="/adminPanel/solution/grade?id=${solution.id}">[Change grade]</a></td>
                </c:otherwise>
            </c:choose>

        </tr>
    </c:forEach>
</table>
</body>
</html>
