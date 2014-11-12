<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

        <a href='<spring:url value="/inputForm" />'><spring:message code="user.create" /></a>
        <a href='<spring:url value="/usersList" />'><spring:message code="user.list" /></a>

        <form action="${pageContext.request.contextPath}/getUserById" method="get">
            <label path="Id">UserId:</label><input type="text" name="id"/><br>
            <input type="submit" name="Submit" value="Search User by ID">
        </form>
        <br>
        <form action="${pageContext.request.contextPath}/getUserByLogin" method="get">
            <label path="Login">Login:</label><input type="text" name="login"/><br>
            <input type="submit" name="Submit" value="Search User by Login">
        </form>

        <form:form method="get" modelAttribute="users">
            <h1><spring:message code="user.list" /></h1>
            <u1>
                <table>
                    <th>UserId</th>
                    <th>Login</th>
                    <th>Name</th>
                    <th>Edit User</th>
                    <th>Remove User</th>

                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.userId}</td>
                            <td>${user.login}</td>
                            <td>${user.name}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/getUpdateForm" method="put">
                                    <input type="text" id="id" name="id" value="${user.userId}" hidden="true"/>
                                    <input type="text" id="login" name="login" value="${user.login}" hidden="true"/>
                                    <input type="text" id="name" name="name" value="${user.name}" hidden="true"/>
                                    <input type="submit" value="edit"/>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/removeUser" method="delete">
                                    <input type="text" id="id" name="id" value="${user.userId}" hidden="true"/>
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