<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>Ty�lleTekij�!</title>
		<link type="text/css" href="resources/css/main.css" rel="stylesheet" />
		<link type="text/css" href="resources/jquery-start/css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="resources/jquery-start/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="resources/jquery-start/js/jquery-ui-1.8.23.custom.min.js"></script>
                <script type="text/javascript" src="resources/js/tsoha.js"></script>
                <script>
			$(function() {
				$( "button", ".mainbuttons, .omatbuttons, .smallbuttons" ).button();				
				$( "#radio" ).buttonset();
				$( "#radio2" ).buttonset();
				$( "#accordion" ).accordion({
					collapsible: true,
                                        autoHeight: false
				});
			});
		</script>
	</head>
	<body style="text-align:center;">
	<jsp:include page="header.jsp"/>
	<br>
	<h1 class="header">Ty�lleTekij�!</h1>
	<div id="radio2" class="mainbuttons">
		<input type="radio" id="radio21" onclick="location.href='createJob'"/><label for="radio21">Ilmoita ty�</label>
		<input type="radio" id="radio22" onclick="location.href='jobs'"/><label for="radio22">Ty�ilmoitukset</label>
		<input type="radio" id="radio23" checked="checked" onclick="location.href='personalData'"/><label for="radio23">Omat tiedot</label>
	</div>
	<div id="radio" class="omatbuttons">
                <input type="radio" id="radio11" name="radio" checked="checked" onclick="location.href='personalData'"/><label for="radio11">Aktiiviset tarjoukset</label>
		<input type="radio" id="radio12" name="radio" onclick="location.href='personalJobs'"/><label for="radio12">Aktiiviset ilmoitukset</label>
		<input type="radio" id="radio13" name="radio" onclick="location.href='jobHistory'"/><label for="radio13">Ty�historia</label>
		<input type="radio" id="radio14" name="radio" onclick="location.href='personalHistory'"/><label for="radio14">Ilmoitushistoria</label>
		<input type="radio" id="radio15" name="radio" onclick="location.href='offerHistory'"/><label for="radio15">Tarjoushistoria</label>
		
	</div>
	<br><br>
        <c:if test="${empty offers}">
                <i>Ei aktiivisia tarjouksia</i>
        </c:if>
	<div id="accordion" class="acc">
            <c:forEach items="${offers}" var="offer" varStatus="status">
                <h3><a href="#">${offer.job.title}</a></h3>
                <div align="left">
                    <ul>
                        <li class="jobtext" id="${offer.id}">
                            <b>Ty� luotu</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${offer.job.created}" /><br><br>
                            <b>Tarjottu ty�:</b><br>${offer.job.description}<br><br>
                            <b>Tarjousten viim.j�tt�pvm:</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${offer.job.expires}" /><br><br>
                            <b>Tarjottu hinta:</b><br>${offer.price}<br><br>
                            <b>Tarjouksen sis�lt�:</b><br>${offer.description}<br><br>
                        </li>
                    </ul>                    
                    <div class="omatbuttons" align="left" style="margin-top: 10px;">
                        <button onclick="confirmOfferRemove('${offer.id}');">Poista tarjous</button>&nbsp;
                    </div>
                    <br>
                    <div id="tabs-${status.index}">
                        <ul>
                            <li><a href="#tabs-${status.index}-1">Kysymykset ja vastaukset</a></li>
                        </ul>
                        <div id="tabs-${status.index}-1" align="left">
                            <ul id="q_${offer.job.id}">                                
                        <c:forEach items="${offer.job.questions}" var="question">
                            <span id="question_id_${question.id}">
                            <li class="jobtext"><b class="jobtext">Kysymys:</b>${question.question}</li>
                            <li class="jobtext"><b class="jobtext">Vastaus:</b>
                                <span id="q_a_${question.id}">
                                <c:choose>
                                    <c:when test="${question.answer!=null}">
                                        ${question.answer}
                                    </c:when>  
                                    <c:otherwise>
                                        <i>Ty�n tilaaja ei ole viel� vastannut kysymykseen.</i>
                                    </c:otherwise>
                                </c:choose>
                                </span>
                            </li>
                            <hr>
                            </span>
                        </c:forEach>
                            </ul>  
                        </div>
                    </div>
                </div>
            </c:forEach>
	</div>
        <script>
            $(function() {

                <c:forEach items="${offers}" var="offer" varStatus="status">
                        $( "#tabs-${status.index}" ).tabs();
                </c:forEach>
            });
            
            function confirmOfferRemove(offerId) {
                var answer = confirm("Olet poistamassa tarjouksesti. Oletko varma?")
                if (answer){
                    location.href = 'removeOffer/'+offerId;
                }
            }
            
        </script>
	</body>
</html>
