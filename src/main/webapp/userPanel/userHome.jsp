<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:include page="userHeader.jsp"/>

<body>
<h1>User panel </h1>

Here you can see all the data about the user, add solutions to exercises and see grades.

<h2><c:out value="${loginMessage}"/> </h2>
<form action="/userPanel" method="POST">
    <label> Username:
        <input type="text" name="username">
    </label><br>
    <label> Password:
        <input type="password" name="password">
    </label> <br>
    <input type="hidden" name="redirect" value="/userPanel/details">
    <input type="submit">
</form>

</body>

</html>
