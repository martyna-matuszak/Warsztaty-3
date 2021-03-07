<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>

<body>
<h1>Solutions</h1>

Choose what do you want to see:
<ul>
    <li>
        <form action="/adminPanel/solution?range=all" method="post">
            <label>All solutions:
                <input type="submit">
            </label>
        </form>
    </li>

    <li>
        <form action="/adminPanel/solution?range=user" method="POST">
            <label> All solutions for user:
            <select id="userId" name="userId">
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
            </label>
            <input type="submit">
        </form>
    </li>

    <li>
        <form action="/adminPanel/solution?range=exercise" method="POST">
            <label> All solutions to the exercise:
                <select id="exerciseId" name="exerciseId">
                    <c:forEach items="${exercises}" var="exercise">
                        <option value="${exercise.id}">${exercise.title}</option>
                    </c:forEach>
                </select>
            </label>
            <input type="submit">
        </form>
    </li>
</ul>

<h2>Ungraded solutions: </h2>

<table>
    <tr><th>ID</th> <th>Created</th> <th>Description</th> <th>Exercise</th> <th>User</th> </tr>
    <c:forEach items="${solutions}" var="solution">
        <tr>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.description}</td>
            <td>${solution.exerciseId}</td>
            <td>${solution.userId}</td>

            <td><a href="/adminPanel/solution/grade?id=${solution.id}">[Add grade]</a></td>


        </tr>
    </c:forEach>
</table>

</body>
</html>
