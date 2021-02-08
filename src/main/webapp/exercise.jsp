<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="header.jsp"/>

<body>
<h1> Exercise details:</h1>
ID: <c:out value="${exercise.id}"/> <br>
Title: <c:out value="${exercise.title}"/><br>
Description: <c:out value="${exercise.description}"/><br>

</body>
<jsp:include page="footer.jsp"/>

</html>
