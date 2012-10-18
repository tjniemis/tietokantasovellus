<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<jsp:include page="includes.jsp"/>
                <script type="text/javascript" src="resources/js/tsoha.js"></script>
	</head>
	<body style="text-align:center;">
	<jsp:include page="header.jsp"/>
	<br>
	<h1 class="header">TyölleTekijä!</h1>
	<div id="radio2" class="mainbuttons">
            <input type="radio" id="radio11" name="radio2" onclick="location.href='createJob'"/><label for="radio11">Ilmoita työ</label>
            <input type="radio" id="radio22" name="radio2" onclick="location.href='jobs'"/><label for="radio22">Työilmoitukset(${count.available})</label>
            <input type="radio" id="radio44" checked="checked" name="radio2" onclick="location.href='personalJobs'"/><label for="radio44">Omat ilmoitukset(${count.jobs})</label>
            <input type="radio" id="radio33" name="radio2" onclick="location.href='personalData'"/><label for="radio33">Omat tarjoukset(${count.offers})</label>                    
            <input type="radio" id="radio55" name="radio2" onclick="location.href='jobHistory'"/><label for="radio55">Historiatiedot</label>
	</div>
	<br><br>
        <c:if test="${empty jobs}">
                <i>Sinulla ei ole aktiivisia työilmoituksia.</i>
        </c:if>
	<div id="accordion" class="acc">            
            <c:forEach items="${jobs}" var="job" varStatus="status">
                <h3><a href="#">${job.title}</a></h3>
                <div align="left">
                <ul>
                    <li class="jobtext" id="${job.id}">
                        <b>Työ luotu</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${job.created}" /><br><br>
                        <b>Työn kuvaus:</b><br>${job.description}<br><br>
                        <b>Tarjousten viim.jättöpvm:</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${job.expires}" /><br><br>
                    </li>
                    <div class="omatbuttons" align="left" style="margin-top: 0px; padding-top: 0px;">	
                        <button class="omatbuttons" onclick="confirmationJob(${job.id})">Poista työ</button><br><br>
                    </div>
                </ul>       
                
                <div id="tabs-${status.index}">
                    <ul>
                            <li><a href="#tabs-${status.index}-2">Tarjoukset</a></li>
                            <li><a href="#tabs-${status.index}-1">Kysymykset ja vastaukset</a></li>                            
                    </ul>
                    
                    <div id="tabs-${status.index}-2">
                        <c:forEach items="${job.offers}" var="offer">					
                            <p align="left" class="jobtext">
                                <b>Tarjoaja: </b><a href="#" onclick="opendialog2(${offer.user.id})">${offer.user.name}</a>&nbsp;(${offer.user.stars})</br>
                                <b>Hinta: </b>${offer.price}<br>
                                <b>Teksti: </b>${offer.description}
                            </p>
                            <div class="smallbuttons" align="left">	
                                    <button class="smallbutton" onclick="location.href='acceptOffer/${offer.id}'">Hyväksy</button>
                            </div>
                            <hr>
                        </c:forEach>
                    </div>
                    <div id="tabs-${status.index}-1" align="left" width="800">
                        <ul id="q_${job.id}">                                
                    <c:forEach items="${job.questions}" var="question">
                        <span id="question_id_${question.id}">
                        <li class="jobtext"><b class="jobtext">Kysymys:&nbsp;</b>${question.question}</li>
                        <li class="jobtext"><b class="jobtext">Vastaus:&nbsp;</b>
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
                            <div class="smallbuttons" align="left">	
                                <button class="smallbutton" onclick="opendialog(${question.id}, '${question.question}', '${question.answer}');">Vastaa</button>
                                <button class="smallbutton" onclick="confirmation('${question.id}')">Poista</button>
                            </div>
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
        <div id="dialog" title="Lisää vastaus">
                <input type="hidden" id="q_dialog_id" value="" />
                <div id="a_help"></div><br>
		<textarea rows="5" cols="44" id="a_text"></textarea>
		<div class="smallbuttons">
			<button class="smallbutton" onclick="javascript:saveAnswer();">Vastaa</button>
                        <button class="smallbutton" onclick="javascript:$( '#dialog' ).dialog('close');">Sulje</button>
		</div>
	</div>
        <div id="dialog2" title="Arviot">
                <input type="hidden" id="r_dialog_id" value="" />
                <div id="review_target"></div>
                <div id="reviews" align="left"></div>
		<div class="smallbuttons">
                        <button class="smallbutton" onclick="javascript:$( '#dialog2' ).dialog('close');">Sulje</button>
		</div>
	</div>          
        <script>
            $(function() {
                    $( "button", ".mainbuttons, .omatbuttons, .smallbuttons" ).button();				
                    $( "#radio" ).buttonset();
                    $( "#radio2" ).buttonset();
                    $( "#accordion" ).accordion({
                            collapsible: true,
                            autoHeight: false
                    });
                    <c:forEach items="${jobs}" var="job" varStatus="status">
                            $( "#tabs-${status.index}" ).tabs();
                    </c:forEach>
                        
                    $( "#dialog" ).dialog({
                            height: 250,
                            width: 400,
                            modal: true, 
                            autoOpen: false
                    });
                    $( "#dialog2" ).dialog({
                            height: 400,
                            width: 400,
                            modal: true, 
                            autoOpen: false
                    });
                    $( "#offerdialog" ).dialog({
                            height: 170,
                            width: 250,
                            modal: true, 
                            autoOpen: false
                    });
            });

            function opendialog(questionId, question, answer) {
                    document.getElementById('q_dialog_id').value = questionId;
                    document.getElementById('a_help').innerHTML = question;
                    document.getElementById('a_text').value = answer;
                    $( "#dialog" ).dialog('open');
            }
            function opendialog2(userId) {
                    document.getElementById('r_dialog_id').value = userId;
                    getUserReviews(userId);
                    $( "#dialog2" ).dialog('open');
            }
            function confirmation(questionId) {
                var answer = confirm("Olet poistamassa kysymyksen ja sen vastauksen. Oletko varma?")
                if (answer){
                    deleteQuestion(questionId);
                }
            }
            function confirmationJob(jobId) {
                var answer = confirm("Olet poistamassa ilmoittamasi työn. Oletko varma?")
                if (answer){
                    location.href='deleteJob/'+jobId;
                }
            }
        </script>
	</body>
</html>

