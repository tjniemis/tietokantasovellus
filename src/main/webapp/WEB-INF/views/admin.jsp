<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<jsp:include page="includes.jsp"/>
		<script>
			$(function() {
				$( "button", ".buttons" ).button();
			});
		</script>
	</head>
	<body>
            <jsp:include page="header.jsp"/>
	<br>
	<h1 style="left: 50%; position: absolute; margin-left: -150px;">TyölleTekijä!</h1>
	<div class="buttons">
            <table cellpadding="3" align="center"> 
                    <tr>
                        <th>Työn otsikko</th>
                        <th>Työn kuvaus</th>
                        <th>Luotu</th>
                        <th>Päättyy</th>
                        <th>Työn luoja</th>
                    </tr>
            <c:forEach items="${jobs}" var="job" varStatus="status">
                    <tr class="${status.index % 2 == 0 ? 'row0' : 'row1'}">
                        <td>${job.title}</td>
                        <td>${job.description}</td>
                        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${job.created}" /></td>
                        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${job.expires}" /></td>
                        <td>${job.user.name}</td>
                        <td><a href="deleteJobAdmin/${job.id}"><< Poista työ</a></td>
                    </tr>
            </c:forEach>
                    </table>
	</div>
	</body>
</html>


