<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <body>
        <form action="${pageContext.request.contextPath}/mvc/person/addPerson" method="post">
            <label path="fname">First Name:</label><input type="text" name="firstName"/><br>
            <label path="lname">Last Name:</label><input type="text" name="lastName"/><br>
            <input type="submit" name="Submit" value="Add">
        </form>

        <a href='<spring:url value="/mvc/person/getPersons" />'> <spring:message code="person.list" /></a>
    </body>
</html>