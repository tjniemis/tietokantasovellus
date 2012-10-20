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
	<h1 style="left: 50%; position: absolute; margin-left: -150px;">Ty�lleTekij� - Yll�pito</h1>
        <br><br>
        <div id="tabs" class="admin">
            <ul>
                <li><a href="#tabs-1">Ty�t</a></li>
                <li><a href="#tabs-2">K�ytt�j�t</a></li>
            </ul>
            <div id="tabs-1" align="left" width="1000">
                <table cellpadding="3" align="center"> 
                        <tr>
                            <th>Ty�n otsikko</th>
                            <th>Ty�n kuvaus</th>
                            <th>Luotu</th>
                            <th>P��ttyy</th>
                            <th>Ty�n luoja</th>
                        </tr>
                <c:forEach items="${jobs}" var="job" varStatus="status">
                        <tr class="${status.index % 2 == 0 ? 'row0' : 'row1'}">
                            <td>${job.title}</td>
                            <td>${job.description}</td>
                            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${job.created}" /></td>
                            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${job.expires}" /></td>
                            <td>${job.user.name}</td>
                            <td><a href="deleteJobAdmin/${job.id}"><< Poista ty�</a></td>
                        </tr>
                </c:forEach>
                </table>
            </div>
            <div id="tabs-2" align="left" width="1000">   
                <table cellpadding="3" align="center"> 
                        <tr>
                            <th>K�ytt�j�n nimi</th>
                            <th>K�ytt�j�n s�hk�posti</th>
                            <th>K�ytt�j� luotu</th>
                        </tr>
                <c:forEach items="${persons}" var="person" varStatus="status2">
                        <tr class="${status2.index % 2 == 0 ? 'row0' : 'row1'}">
                            <td>${person.name}</td>
                            <td>${person.email}</td>
                            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${person.created}" /></td>
                            <td><a href="deleteUser/${person.id}"><< Poista k�ytt�j�</a></td>
                        </tr>
                </c:forEach>
                </table>
            </div>
	</div>
	</body>
</html>


