<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="false" %>
<html>
<head>
    <title>View Employees</title>
    <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
</head>
<body>

<h3 style="color: red;">Show All Employees</h3>
<ul>
    <c:forEach var="employee" items="${employees}">
        <li>${employee}</li>
    </c:forEach>
</ul>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.empId}</td>
            <td>${employee.empName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
