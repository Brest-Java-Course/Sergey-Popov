<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="menu">
Menu
    <ul>
        <li>
            <a href='<spring:url value="/mvc/person/getPersons" />'> <spring:message code="person.list" /></a>
        </li>
        <li>
            <a href='<spring:url value="/mvc/task/getTasks" />'> <spring:message code="task.list" /></a>
        </li>
    </ul>
</div>