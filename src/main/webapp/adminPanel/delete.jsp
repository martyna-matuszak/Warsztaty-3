<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="/adminPanel/adminHeader.jsp"/>
<body>
<h2>Deleting</h2>
<c:out value="${element.toString()}"/><br>
<br>
Are you sure that you want to delete this element?<br>
<form action="${action}" method="POST">
    <label><input type="radio" name="delete" value="yes">Yes</label><br>
    <label><input type="radio" name="delete" value="no" checked>No</label><br>
    <input type="hidden" name="id" value="${element.id}">
    <input type="submit">
</form>
</body>
</html>
