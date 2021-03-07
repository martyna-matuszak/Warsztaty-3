<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>
<body>

<h1>Grading solution</h1>

<c:out value="${solution.toString()}"/> <br> <br>

<form action="/adminPanel/solution/grade" method="POST">

    <label> Grade:
    <select name="grade">
        <c:forEach items="${grades}" var="grade">
            <option value="${grade}">${grade}</option>
        </c:forEach>
    </select>
    </label> <br>

    <input type="hidden" name="id" value="${solution.id}">
    <input type="submit">
</form>

</body>
</html>
