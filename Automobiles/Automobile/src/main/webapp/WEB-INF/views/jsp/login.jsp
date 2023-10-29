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
        <h1>Login</h1>
    </div>
    <div class="row">
        <c:url value="/login" var="loginUrlString"/>
        <form id="appointment-form" action="${loginUrlString}" method="POST">
            <div class="form-group">
                <label for="userName">Username</label>
                <input name="userName" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="remember">Remember Me?</label>
                <input type="checkbox" id="remember" name="remember-me"/>
            </div>

            <sec:csrfInput/>

            <c:if test="${param.logout != null }">
                <p>You have successfully been logged out.</p>
            </c:if>

            <c:if test="${param.error != null }">
                <p>Invalid Credentials!</p>
            </c:if>

            <button type="submit" id="btn-save" class="btn btn-primary">Login</button>
        </form>
    </div>
</div>
</body>
</html>
