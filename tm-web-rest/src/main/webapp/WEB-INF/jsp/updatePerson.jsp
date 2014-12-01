<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <body>
        <form action="${pageContext.request.contextPath}/client/person/updatePerson" method="post">
            <label path="id">PersonId:</label><input type="text" name="id" value="${person.personId}" readonly="readonly"/><br>
            <label path="firstName">First Name:</label><input type="text" name="firstName" value="${person.personFirstName}"/><br>
            <label path="lastName">Last Name:</label><input type="text" name="lastName" value="${person.personLastName}"/><br>
            <input type="submit" value="Edit">
        </form>

        <a href='<spring:url value="/client/person/getPersons" />'> <spring:message code="person.list" /></a>
    </body>
</html>