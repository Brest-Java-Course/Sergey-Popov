<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
    <body>
        <style type="text/css">
            Table {
                width: 300px;
                border-collapse;
            }
            TD, TH {
                padding: 3px;
                border: 1px solid black;
            }
            TH {
                background: #b0e0e6;
            }
        </style>

        <a href='<spring:url value="/mvc/person/getPersons" />'><spring:message code="person.list" /></a>
        <a href='<spring:url value="/mvc/task/getTasks" />'><spring:message code="task.list" /></a>

        <form action="${pageContext.request.contextPath}/mvc/task/getTasksById" method="get">
            <label path="Id">PersonId:</label><input type="text" name="id"/><br>
            <input type="submit" value="Search Tasks by PersonID">
        </form>

        <form action="${pageContext.request.contextPath}/mvc/task/getTaskById" method="get">
            <label path="Id">TaskId:</label><input type="text" name="id"/><br>
            <input type="submit" value="Search Task by TaskID">
        </form>

        <br>

        <form:form method="get" modelAttribute="tasks">
            <h1><spring:message code="task.list" /></h1>
            <u1>
                <table>
                    <th>TaskId</th>
                    <th>TaskName</th>
                    <th>TaskState</th>
                    <th>TaskStartDate</th>
                    <th>TaskEndDate</th>
                    <th>TaskElapsedTime</th>
                    <th>PersonId</th>
                    <th>Close Task</th>
                    <th>Remove Task</th>

                    <c:forEach items="${tasks}" var="task">
                        <tr>
                            <td>${task.taskId}</td>
                            <td>${task.taskName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${task.taskState == true}">
                                        <c:out value="Active"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="Completed"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <joda:format value="${task.startDate}" style="SM" />
                            </td>
                            <td>
                                <joda:format value="${task.endDate}" style="SM" />
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${task.elapsedTime == null}">
                                        <c:out value=""/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${task.elapsedTime} minutes"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${task.person.personId}</td>                            
                            <td>
                                <form action="${pageContext.request.contextPath}/mvc/task/updateTask" method="post">
                                    <input type="text" id="id" name="id" value="${task.taskId}" hidden="true"/>
                                    <input type="text" id="taskName" name="taskName" value="${task.taskName}" hidden="true"/>
                                    <input type="text" id="startDate" name="startDate" value="${task.startDate}" hidden="true"/>
                                    <input type="submit" value="edit"/>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/mvc/task/deleteTask" method="post">
                                    <input type="text" id="id" name="id" value="${task.taskId}" hidden="true"/>
                                    <input type="submit" value="remove"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </u1>
        </form:form>
    </body>
</html>