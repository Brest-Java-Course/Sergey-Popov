<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<% pageContext.setAttribute("now", new org.joda.time.DateTime()); %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <body>
        <form action="${pageContext.request.contextPath}/client/task/addTask" method="post">
            <label path="taskName">Task Name:</label><input type="text" name="taskName"/><br>
            <input type="text" name="startDate" value="${now}" hidden="true"/>
            <label path="personId">PersonId:</label><input type="text" name="personId" value="${personId}" readonly="readonly"/><br>
            <input type="submit" name="Submit" value="Add">
        </form>

        <a href='<spring:url value="/client/task/getTasks" />'> <spring:message code="task.list" /></a>
    </body>
</html>