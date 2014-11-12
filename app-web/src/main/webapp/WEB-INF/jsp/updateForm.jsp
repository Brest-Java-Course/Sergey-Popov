<html>
    <body>
        <form action="${pageContext.request.contextPath}/updateUser" method="put">
            <label path="id">UserId:</label><input type="text" name="id" value="${user.userId}" readonly="readonly"/><br>
            <label path="login">Login:</label><input type="text" name="login" value="${user.login}"/><br>
            <label path="name">Name:</label><input type="text" name="name" value="${user.name}"/><br>
            <input type="submit" value="Edit">
        </form>

        <a href='<spring:url value="/users" />'> <spring:message code="user.update" /></a>
    </body>
</html>