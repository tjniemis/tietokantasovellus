<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>TyölleTekijä!</title>
		<link type="text/css" href="resources/css/main.css" rel="stylesheet" />
		<link type="text/css" href="resources/jquery-start/css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="resources/jquery-start/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="resources/jquery-start/js/jquery-ui-1.8.23.custom.min.js"></script>
                <script type="text/javascript" src="resources/js/tsoha.js"></script>
                <script>
			$(function() {
				$( "button", ".mainbuttons, .omatbuttons" ).button();				
				$( "#radio" ).buttonset();
				$( "#radio2" ).buttonset();
				$( "#accordion" ).accordion({
					collapsible: true
				});
			});
		</script>
	</head>
	<body style="text-align:center;">
	<jsp:include page="header.jsp"/>
	<br>
	<h1 class="header">TyölleTekijä!</h1>
	<div id="radio2" class="mainbuttons">
		<input type="radio" id="radio21" onclick="location.href='createJob'"/><label for="radio21">Ilmoita työ</label>
		<input type="radio" id="radio22" onclick="location.href='jobs'"/><label for="radio22">Työilmoitukset</label>
		<input type="radio" id="radio23" checked="checked" onclick="location.href='personalData'"/><label for="radio23">Omat tiedot</label>
	</div>
	<div id="radio" class="omatbuttons">
                <input type="radio" id="radio11" name="radio" onclick="location.href='personalData'"/><label for="radio11">Aktiiviset tarjoukset</label>
		<input type="radio" id="radio12" name="radio" onclick="location.href='personalJobs'"/><label for="radio12">Aktiiviset ilmoitukset</label>
		<input type="radio" id="radio13" name="radio" checked="checked" onclick="location.href='jobHistory'"/><label for="radio13">Työhistoria</label>
		<input type="radio" id="radio14" name="radio" onclick="location.href='personalHistory'"/><label for="radio14">Ilmoitushistoria</label>
		<input type="radio" id="radio15" name="radio" onclick="location.href='offerHistory'"/><label for="radio15">Tarjoushistoria</label>
		
	</div>
	<br><br>
        <c:if test="${empty jobs}">
                <i>Ei työhistoriaa</i>
        </c:if>
	<div id="accordion" class="acc">            
            <c:forEach items="${jobs}" var="job" varStatus="status">
                <h3><a href="#">${job.title}</a></h3>
                <div align="left">
                <ul>
                    <li class="jobtext" id="${job.id}">
                        <b>Työ luotu</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${job.created}" /><br><br>
                        <b>Työn kuvaus:</b><br>${job.description}<br><br>
                        <b>Tarjouksen hinta:</b><br>${job.winningOffer.price}<br><br>
                        <b>Tarjouksen sisältö:</b><br>${job.winningOffer.description}<br><br>
                    </li>
                </ul>                    
                </div>
            </c:forEach>
	</div>
	</body>
</html>

