<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <c:if test="${user!=null}">
            <div id="radio2" class="mainbuttons">
                <input type="radio" id="radio11" name="radio2" onclick="location.href='createJob'"/><label for="radio11">Ilmoita työ</label>
                <input type="radio" id="radio22" checked="checked" name="radio2" onclick="location.href='jobs'"/><label for="radio22">Työilmoitukset(${count.available})</label>
                <input type="radio" id="radio44" name="radio2" onclick="location.href='personalJobs'"/><label for="radio44">Omat ilmoitukset(${count.jobs})</label>
                <input type="radio" id="radio33" name="radio2" onclick="location.href='personalData'"/><label for="radio33">Omat tarjoukset(${count.offers})</label>                    
                <input type="radio" id="radio55" name="radio2" onclick="location.href='jobHistory'"/><label for="radio55">Historiatiedot</label>
            </div>
        </c:if>
        <br>
        <h3>Alla listattuna työt joihin voit tehdä tarjouksen. Aktiiviset tarjouksesi löydät Omat tarjoukset-osiosta.</h3>
	<div id="accordion" class="acc">
            <c:forEach items="${jobs}" var="job" varStatus="status">
                <c:choose>
                    <c:when test="${job.show}">
                    <h3><a href="#">${job.title}</a></h3>
                    <div align="left">
                        <ul style="padding-left: 10px;">
                        <li class="jobtext"><b>Työ luotu:</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${job.created}" /></li>
			<li class="jobtext"><b>Työn kuvaus:</b><br>${job.description}</li>
			<li class="jobtext"><b>Tarjousten deadline:</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${job.expires}" /></li>		
                        				
                        <c:if test="${user!=null&&user.id!=job.user.id}">
                            <div id="offer_${job.id}">
                                <form id="${job.id}">
                                <li class="jobtext"><b>Tarjouksen hinta:</b><br><input type="text" size="18" name="price"/></li>
                                <li valign="top" class="jobtext"><b>Tarjouksen teksti:&nbsp;</b><br><textarea rows="5" cols="70" name="pricetext"></textarea></li>
                                </form>
                                <div class="omatbuttons" align="left">
                                    <button onclick="saveOffer('${job.id}');">Tee tarjous</button>&nbsp;
                                </div>         
                            </div>
                        </c:if>
                        
                        </ul>
			<br><br>
			<div id="tabs-${status.index}">
                            <ul>
                                    <li><a href="#tabs-${status.index}-1">Kysymykset ja vastaukset</a></li>
                            </ul>
                            <div id="tabs-${status.index}-1" align="left" width="800">
                            <c:if test="${user.id!=null && job.user.id!=user.id}">
                                <div class="omatbuttons" align="left" style="padding: 0px; margin-top: 0px;">
                                    <button onclick="opendialog2('${job.id}');">Lähetä kysymys</button>
                                </div>
                                <br>
                            </c:if>
                                <ul id="q_${job.id}">                                
                            <c:forEach items="${job.questions}" var="question">
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
                    </c:when>  
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
	</div>
        
        <!-- Dialog used -->
	<div id="dialog" title="Lisää vastaus">
                <input type="hidden" id="q_dialog_id" value="" />
                <div id="a_help"></div><br>
		<textarea rows="5" cols="44" id="a_text"></textarea>
		<div class="smallbuttons">
			<button class="smallbutton" onclick="javascript:saveAnswer();">Vastaa</button>
                        <button class="smallbutton" onclick="javascript:$( '#dialog' ).dialog('close');">Sulje</button>
		</div>
	</div>
	<div id="dialog2" title="Lisää Kysymys">
                <input type="hidden" id="q_job" value="" />
		<textarea rows="5" cols="44" id="q_text"></textarea>
		<div class="smallbuttons">	
			<button class="smallbutton" onclick="javascript:saveQuestion();">Kysy</button>
                        <button class="smallbutton" onclick="javascript:$( '#dialog2' ).dialog('close');">Sulje</button>
		</div>
	</div>
        <div id="offerdialog">
                <p>Tarjous talletettu onnistuneesti!</p>
                <div class="smallbuttons">	
                    <button class="smallbutton" onclick="closeDialog('#offerdialog');">Ok</button>
		</div>
        </div>
        <!-- End of dialogs -->
        
        <script>
            $(function() {
                    $( "button", ".mainbuttons, .omatbuttons, .smallbuttons" ).button();				
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
                            height: 200,
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
            function opendialog2(jobId) {
                    document.getElementById('q_job').value = jobId;
                    $( "#dialog2" ).dialog('open');
            }
            function confirmation(questionId) {
                var answer = confirm("Olet poistamassa kysymyksen ja sen vastauksen. Oletko varma?")
                if (answer){
                        deleteQuestion(questionId);
                }
            }
    </script>
	</body>
</html>

