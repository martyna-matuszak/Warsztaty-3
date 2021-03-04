<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>
<body>

<h1>Adding solution</h1>
Exercise: <c:out value="${exercise.title}"/> <br>
Description: <c:out value="${exercise.description}"/> <br>

<form action="/userPanel/addSolution" method="POST">
    <label>
        Solution's description:
        <input type="text" name="description" value="${solution.description}">
    </label><br>

    <input type="hidden" name="exerciseId" value="${exercise.id}">
    <input type="submit">
</form>

</body>
</html>
