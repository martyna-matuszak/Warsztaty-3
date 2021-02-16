<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>

<body>
<h1>Exercises</h1>
<a href="/adminPanel/exercise/add">[add new exercise]</a>

<table>
    <tr><th>ID</th> <th>Title</th> <th>Description</th> </tr>
    <c:forEach items="${exercises}" var="exercise">
        <tr>
            <td>${exercise.id}</td>
            <td>${exercise.title}</td>
            <td>${exercise.description}</td>
            <td> <a href="/adminPanel/exercise/edit?id=${exercise.id}">[edit]</a> </td>
            <td> <a href="/adminPanel/exercise/delete?id=${exercise.id}">[delete]</a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
