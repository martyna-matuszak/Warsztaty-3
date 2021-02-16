<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>
<body>

<h1>User form</h1>

<form action="${action}" method="POST">
    <label>
        Email:
        <input type="text" name="email" value="${user.email}">
    </label><br>
    <label>
        Username:
        <input type="text" name="username" value="${user.username}">
    </label><br>
    <label>
        Password:
        <input type="password" name="password" minlength="8" required>
    </label><br>
    <label for="groupId">Choose a group:</label>
    <select id="groupId" name="groupId">
        <c:forEach items="${groups}" var="group">
            <option value="${group.id}">${group.name}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="id" value="${user.id}"> <br>
    <input type="submit">
</form>

</body>
</html>
