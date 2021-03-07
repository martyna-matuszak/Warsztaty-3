<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:include page="/header.jsp"/>

<body>
<h1>Admin panel </h1>

If your account has admin access, then after login:
<ul>
    <li>you can see all groups there, add, edit and delete them if necessary</li>
    <li>you can see all users there, add, edit and delete them if necessary</li>
    <li>you can see all exercises there, add, edit and delete them if necessary</li>
    <li>you can grade the solutions here</li>
</ul>


<c:choose>
    <c:when test="${redirect != null}">
        <c:set var = "url" scope = "session" value = "${redirect}"/>
    </c:when>
    <c:otherwise>
        <c:set var = "salary" scope = "session" value = "/adminPanel"/>
    </c:otherwise>
</c:choose>

<br>
<h2><c:out value="${loginMessage}"/> </h2>
<form action="/adminLogin" method="POST">
    <label> Username:
        <input type="text" name="username">
    </label><br>
    <label> Password:
        <input type="password" name="password">
    </label> <br>
    <input type="hidden" name="redirect" value="${url}">
    <input type="submit">
</form>

</body>
</html>
