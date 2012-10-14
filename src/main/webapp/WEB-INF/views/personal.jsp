<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<jsp:include page="includes.jsp"/>
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
	<h1 class="header">TyölleTekijä!</h1>
	<div id="radio2" class="mainbuttons">
		<input type="radio" id="radio11" name="radio2" onclick="location.href='createJob'"/><label for="radio11">Ilmoita työ</label>
                <input type="radio" id="radio22" name="radio2" onclick="location.href='jobs'"/><label for="radio22">Työilmoitukset</label>
                <input type="radio" id="radio44" name="radio2" onclick="location.href='personalJobs'"/><label for="radio44">Omat ilmoitukset</label>
                <input type="radio" id="radio33" checked="checked" name="radio2" onclick="location.href='personalData'"/><label for="radio33">Omat tarjoukset</label>                    
                <input type="radio" id="radio55" name="radio2" onclick="location.href='jobHistory'"/><label for="radio55">Historiatiedot</label>
	</div>
	<br><br>
        <c:if test="${empty offers}">
                <i>Sinulla ei ole aktiivisia tarjouksia töistä.</i>
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
                                        <i>Työn tilaaja ei ole vielä vastannut kysymykseen.</i>
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

