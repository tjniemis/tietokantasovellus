<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
<h1>Listing Users</h1>
<c:forEach items="${users}" var="user">
	<a href="get/${user.id}">${user.id} -
	${user.name}</a>
	<br />
</c:forEach>
</body>
</html>
