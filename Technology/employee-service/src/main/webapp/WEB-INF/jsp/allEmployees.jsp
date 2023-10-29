<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Employees page</h1>

<ul>
    <c:forEach items="${employees}" var="advice">
        <li>${advice}</li>
    </c:forEach>
</ul>
