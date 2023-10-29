<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <!-- Latest JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" type="text/javascript"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/resources/js/logout-action.js"/>"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <h1>Welcome to RSLakra's Auto Service Center!</h1>
        <sec:authorize access="isAuthenticated()">
            Welcome back <sec:authentication property="principal.firstName"/>
            <sec:authentication property="principal.lastName"/>
        </sec:authorize>

        <h3>Allow our team of completely unqualified mechanics to attempt to fix your automobile.</h3>
        <p>Using the latest tools of the mechanic trade such as online videos, sketchy forum posts and Reddit comments
            our team of poorly skilled mechanics will possibly not make your automobile worse than when it came in the
            door.
        </p>
        <p>Don't hesitate to schedule an appointment today!</p>
    </div>
</div>
</body>
</html>
