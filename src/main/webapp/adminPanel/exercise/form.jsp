<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>
<body>

<h1>Exercise form</h1>

<form action="${action}" method="POST">
    <label>
        Title:
        <input type="text" name="title" value="${exercise.title}">
    </label><br>
    <label>
        Description:
        <input type="text" name="description" value="${exercise.description}">
    </label><br>
    <input type="hidden" name="id" value="${exercise.id}">
    <input type="submit">
</form>

</body>
</html>
