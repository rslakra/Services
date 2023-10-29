<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <jsp:include page="title.jsp"/>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- Latest Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" type="text/javascript"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <h1>Register</h1>
    </div>
    <div class="row">
        <c:url value="/register" var="registerUrlString"/>
        <form id="appointment-form" action="${registerUrlString}" method="POST">
            <div class="form-group">
                <label for="email">Email</label>
                <input class="form-control" name="email"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input class="form-control" type="password" name="password"/>
            </div>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input class="form-control" name="firstName"/>
            </div>
            <div class="form-group">
                <label for="middleName">Middle Name</label>
                <input class="form-control" name="middleName"/>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input class="form-control" name="lastName"/>
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <input class="form-control" name="status"/>
            </div>
            <div class="form-group">
                <label for="role">Role</label>
                <input class="form-control" name="role"/>
            </div>
            <sec:csrfInput/>

            <!-- show error message -->
            <c:if test="${param.error != null }">
                <p><c:out value="${param.message}"/></p>
            </c:if>

            <button type="submit" id="btn-save" class="btn btn-primary">Register</button>
        </form>
    </div>
</div>
</body>
</html>