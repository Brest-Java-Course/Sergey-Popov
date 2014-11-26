<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@page isELIgnored="false" %>
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

        <a href='<spring:url value="/mvc/person/addPerson" />'><spring:message code="person.create" /></a>
        <a href='<spring:url value="/mvc/person/personList" />'><spring:message code="person.list" /></a>
        <a href='<spring:url value="/mvc/task/getTasks" />'><spring:message code="task.list" /></a>


        <form action="${pageContext.request.contextPath}/mvc/person/getPersonById" method="get">
            <label path="Id">PersonId:</label><input type="text" name="id"/><br>
            <input type="submit" value="Search Person by ID">
        </form>

        <br>

        <form:form method="get" modelAttribute="persons">
            <h1><spring:message code="person.list" /></h1>
            <u1>
                <table>
                    <th>PersonId</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Edit Person</th>
                    <th>Remove Person</th>
                    <th>Add Task</th>

                    <c:forEach items="${persons}" var="person">
                        <tr>
                            <td>${person.personId}</td>
                            <td>${person.personFirstName}</td>
                            <td>${person.personLastName}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/mvc/person/updatePerson" method="get">
                                    <input type="text" id="id" name="id" value="${person.personId}" hidden="true"/>
                                    <input type="text" id="fname" name="firstName" value="${person.personFirstName}" hidden="true"/>
                                    <input type="text" id="lname" name="lastName" value="${person.personLastName}" hidden="true"/>
                                    <input type="submit" value="edit"/>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/mvc/person/deletePerson" method="post">
                                    <input type="text" id="id" name="id" value="${person.personId}" hidden="true"/>
                                    <input type="submit" value="remove"/>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/mvc/task/addTask" method="get">
                                    <input type="text" id="personId" name="personId" value="${person.personId}" hidden="true"/>
                                    <input type="submit" value="add"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </u1>
        </form:form>
    </body>
</html>