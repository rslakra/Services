<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a href="<spring:url value="/"/>" class="navbar-brand">RSLakra's Auto Service Center</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href='<spring:url value="/services/"/>'>Services</a></li>
            <li><a href="<spring:url value='/appointments/'/>">Appointments</a></li>
            <li><a href="<spring:url value='/schedules/'/>">Schedules</a></li>

            <sec:authorize access="authenticated" var="authenticated"/>
            <c:choose>
                <c:when test="${authenticated}">
                    <li>
                        <p class="navbar-text">
                            Welcome
                                <sec:authentication property="principal.firstName"/>
                            <a id="logout" href="#">Logout</a>
                        <ul class="nav navbar-nav">
                            <li><a href="<spring:url value="/logout"/>">Logout</a></li>
                        </ul>
                        </p>
                        <form id="logout-form" action="<c:url value="/logout"/>" method="POST">
                            <sec:csrfInput/>
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<spring:url value="/login/"/>">Sign In</a></li>
                    <li><a href="<spring:url value="/register/"/>">Register</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<!-- lazy-loading -->
<script src="<spring:url value="/resources/js/logout-action.js"/>"></script>