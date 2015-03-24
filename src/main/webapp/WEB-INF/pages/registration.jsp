<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<body/>
<h1>Registration Form</h1>
<form:form method="POST" commandName="personForm" action="/registration/registerPersonDAsync">
    <br><form:errors path="name" cssStyle="color: red"/> <br/>
    Name: <br/> <form:input path="name"/><br/>
    <br><form:errors path="email" cssStyle="color: red"/> <br/>
    Email: <br/> <form:input path="email"/><br/>
    <br><form:errors path="birthDate" cssStyle="color: red"/> <br/>
    Birth Date (yyyy-MM-dd): <br/> <form:input path="birthDate"/> <br/>
    <input type="submit" value="Register"/>
</form:form>
</body>
</html>