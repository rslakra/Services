<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <jsp:include page="title.jsp"/>

    <link rel="stylesheet" href="<spring:url value="/resources/css/styles.css"/>"/>
    <link rel="stylesheet" href="<spring:url value="/resources/css/datepicker.css"/>"/>
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-multiselect.css"/>"/>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Latest Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" type="text/javascript"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap/bootstrap-datepicker.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap/bootstrap-multiselect.js"/>"></script>
    <script src="<spring:url value="/resources/js/logout-action.js"/>"></script>
    <script src="<spring:url value="/resources/js/appointment/appointments.js"/>"></script>
    <script>
        var root = "${pageContext.request.contextPath}";
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <h1>Appointment</h1>
    </div>
    <div class="row">
        <h1>Appointment</h1>
        <ul class="list-group">
            <li class="list-group-item">
                <label>Customer:</label>
                <c:if test="${appointment.user != null }">
                    <span>${appointment.user.firstName } ${appointment.user.lastName}</span>
                </c:if>
            </li>
            <li class="list-group-item">
                <label>Appointment Date:</label><span>${appointment.appointmentOn}</span>
            </li>
            <li class="list-group-item">
                <label>Make:</label><span>${appointment.vehicle.make}</span>
            </li>
            <li class="list-group-item">
                <label>Model:</label><span>${appointment.vehicle.model }</span>
            </li>
            <li class="list-group-item">
                <label>Year:</label><span>${appointment.vehicle.year }</span>
            </li>
            <li class="list-group-item">
                <label>Services:</label>
                <ul>
                    <c:forEach items="${appointment.services}" var="service">
                        <li>${service}</li>
                    </c:forEach>
                </ul>
            </li>
            <li class="list-group-item">
                <label>Status:</label><span>${appointment.status}</span>
            </li>
            <li class="list-group-item">
                <!-- visible to all users -->
                <a class="btn btn-default" href="<spring:url value="/appointments/"/>" role="button">Back</a>

                <!-- visible to loggedInUser's only -->
                <sec:authorize access="${isUser}">
                    <a class="btn btn-default" href="<spring:url value="/appointments/cancel"/>"
                       role="button">Cancel</a>
                </sec:authorize>

                <!-- visible to Admin's only -->
                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <a class="btn btn-default" href="<spring:url value="/appointments/confirm/${appointment.id}"/>"
                       role="button">Confirm</a>
                </sec:authorize>

                <!-- visible to authenticated loggedInUser only -->
                <sec:authorize url="/schedules/*">
                    <a class="btn btn-default"
                       href="<spring:url value="/appointments/complete"/>"
                       role="button">Mark Complete</a>
                </sec:authorize>
            </li>
        </ul>
    </div>
</div>

</body>
</html>
