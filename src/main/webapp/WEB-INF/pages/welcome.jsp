<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h1>Spring MVC i18n example</h1>
 
Language : <a href="?language=en">English</a>|<a href="?language=uk_UA">Ukrainian</a>
 
<h3>
    welcome.course : <spring:message code="welcome.course" text="default text" />
</h3>
 
Current Locale : ${pageContext.response.locale}
 
</body>
</html>