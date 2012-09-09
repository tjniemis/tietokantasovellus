<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	User ${user.id} - ${user.name} 
</h1>
<form:form commandName="user" style="padding:8px">
    ID - ${user.id}<br/>
    <p>
        Name<br/>
        <form:input path="name"/>
    </p>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
