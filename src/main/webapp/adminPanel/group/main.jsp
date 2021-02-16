<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<jsp:include page="/adminPanel/adminHeader.jsp"/>

<body>
<h1>Users groups: </h1>


<a href="/adminPanel/group/add">[add new group]</a>

<table>
    <tr><th>ID</th> <th>Name</th> </tr>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td>${group.id}</td>
            <td>${group.name}</td>
            <td> <a href="/adminPanel/group/edit?id=${group.id}"> [edit] </a> </td>
            <td> <a href="/adminPanel/group/delete?id=${group.id}"> [delete] </a> </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
