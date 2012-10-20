<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<jsp:include page="includes.jsp"/>
		<script>
			$(function() {
                            $( "#tabs" ).tabs();
                        });                        
		</script>
	</head>
	<body>
            <jsp:include page="header.jsp"/>
	<br>
	<h1 style="left: 50%; position: absolute; margin-left: -150px;">TyölleTekijä - Ylläpito</h1>
        <br><br>
        <div id="tabs" class="admin">
            <ul>
                <li><a href="#tabs-1">Työt</a></li>
                <li><a href="#tabs-2">Käyttäjät</a></li>
            </ul>
            <div id="tabs-1" align="left" width="1000">
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
            <div id="tabs-2" align="left" width="1000">   
                <table cellpadding="3" align="center"> 
                        <tr>
                            <th>Käyttäjän nimi</th>
                            <th>Käyttäjän sähköposti</th>
                            <th>Käyttäjä luotu</th>
                        </tr>
                <c:forEach items="${persons}" var="person" varStatus="status2">
                        <tr class="${status2.index % 2 == 0 ? 'row0' : 'row1'}">
                            <td>${person.name}</td>
                            <td>${person.email}</td>
                            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${person.created}" /></td>
                            <td><a href="deleteUser/${person.id}"><< Poista käyttäjä</a></td>
                        </tr>
                </c:forEach>
                </table>
            </div>
	</div>
	</body>
</html>


