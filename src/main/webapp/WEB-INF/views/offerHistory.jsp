<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<jsp:include page="includes.jsp"/>
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
		<input type="radio" id="radio11" name="radio2" onclick="location.href='createJob'"/><label for="radio11">Ilmoita työ</label>
                <input type="radio" id="radio22" name="radio2" onclick="location.href='jobs'"/><label for="radio22">Työilmoitukset</label>
                <input type="radio" id="radio44" name="radio2" onclick="location.href='personalJobs'"/><label for="radio44">Omat ilmoitukset</label>
                <input type="radio" id="radio33" name="radio2" onclick="location.href='personalData'"/><label for="radio33">Omat tarjoukset</label>                    
                <input type="radio" id="radio55" checked="checked" name="radio2" onclick="location.href='jobHistory'"/><label for="radio55">Historiatiedot</label>
	</div>
	<div id="radio" class="omatbuttons">
		<input type="radio" id="radio13" name="radio" onclick="location.href='jobHistory'"/><label for="radio13">Työhistoria</label>
		<input type="radio" id="radio14" name="radio" onclick="location.href='personalHistory'"/><label for="radio14">Ilmoitushistoria</label>
		<input type="radio" id="radio15" name="radio" checked="checked" onclick="location.href='offerHistory'"/><label for="radio15">Tarjoushistoria</label>
		
	</div>
	<br><br>
        <c:if test="${empty offers}">
                <i>Sinulla ei ole vanhentuneita tarjouksia.</i>
        </c:if>
	<div id="accordion" class="acc">
            <c:forEach items="${offers}" var="offer" varStatus="status">
                <h3><a href="#">${offer.job.title}</a></h3>
                <div align="left">
                <ul>
                    <li class="jobtext" id="${offer.id}">
                        <b>Työ luotu</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${offer.job.created}" /><br><br>
                        <b>Tarjottu työ:</b><br>${offer.job.description}<br><br>
                        <b>Tarjousten viim.jättöpvm:</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${offer.job.expires}" /><br><br>
                        <b>Tarjottu hinta:</b><br>${offer.price}<br><br>
                        <b>Tarjouksen sisältö:</b><br>${offer.description}<br><br>
                    </li>
                    
                </ul>                    
                </div>
            </c:forEach>
	</div>
        
        
	</body>
</html>

