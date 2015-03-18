<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<body/>
<h1>Registration Form</h1>
<form:form method="POST" action="registration/registerPerson">
    Name: <input type="text" name="name"/><br/>
    Email: <input type="text" name="email"/><br/>
    <input type="submit" value="Register"/>
    <%--<table>--%>

    <%--<tr>--%>
    <%--<td>name:</td>--%>
    <%--<td><form:input path="name" /></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>email:</td>--%>
    <%--<td><form:input path="email" /></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td colspan="2">--%>
    <%--<input type="submit" value="Register Person"/>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</table>--%>
</form:form>
</body>
</html>