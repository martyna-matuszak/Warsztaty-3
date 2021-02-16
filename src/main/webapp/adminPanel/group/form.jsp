<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>
<body>

<h1>Group form</h1>

<form action="${action}" method="POST">
    <label>
        Name:
        <input type="text" name="name" value="${group.name}">
    </label><br>

    <input type="hidden" name="id" value="${group.id}">
    <input type="submit">
</form>

</body>
</html>
