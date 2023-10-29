<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
        <sec:authorize access="isAuthenticated()" var="authenticated"/>
        <c:choose>
            <c:when test="${authenticated}">
                <h1>Appointments</h1>
                <div class="row">
                    <h3 class="col col-md-8">Review your scheduled maintenance appointments.</h3>
                    <div class="col col-md-4">
                        <button id="add-appointment-btn" type="button"
                                class="btn btn-default" data-toggle="modal"
                                data-target="#add-appointment-form">
                            <span class="glyphicon glyphicon-plus"></span> Create Appointment
                        </button>
                    </div>
                </div>
                <table id="appointment-table" class="table">
                    <thead>
                    <tr>
                        <th>Customer</th>
                        <th>Appointment Date</th>
                        <th>Make</th>
                        <th>Model</th>
                        <th>Year</th>
                        <th>Services</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>There are currently no appointments scheduled.</td>
                    </tr>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <li><a href="<spring:url value="/login/"/>">Sign In</a></li>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<!-- add appointment form -->
<div id="add-appointment-form" class="modal fade" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <form:form id="appointment-form" commandName="appointment">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">Schedule an Appointment</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="appointmentOn">Appointment Date</label>
                        <div class="input-append date clearfix" id="appointment-on"
                             data-date="" data-date-format="mm-dd-yyyy">
                            <form:input cssClass="pull-left" path="appointmentOn"/>
                            <span class="add-on pull-left">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="make">Make</label>
                        <form:input path="vehicle.make" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="model">Model</label>
                        <form:input path="vehicle.model" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="year">Year</label>
                        <form:input path="vehicle.year" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="make">Services</label>
                        <form:select id="services" path="services" multiple="multiple">
                            <form:option value="Battery Replacement"></form:option>
                            <form:option value="Oil Change">Oil Change</form:option>
                            <form:option value="Tire Change">Tire Change</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="btn-close" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" id="btn-save" class="btn btn-primary">Save</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
