<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				$( "button", ".mainbuttons, .omatbuttons, .smallbuttons" ).button();				
				$( "#radio" ).buttonset();
				$( "#radio2" ).buttonset();
                                $( "#radio3" ).buttonset();
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
		<input type="radio" id="radio13" name="radio" onclick="location.href='jobHistory'"/><label for="radio13">Työhistoria</label>
		<input type="radio" id="radio14" name="radio" checked="checked" onclick="location.href='personalHistory'"/><label for="radio14">Ilmoitushistoria</label>
		<input type="radio" id="radio15" name="radio" onclick="location.href='offerHistory'"/><label for="radio15">Tarjoushistoria</label>
		
	</div>
	<br><br>
        <c:if test="${empty jobs}">
                <i>Sinulta ei löydy vanhoja ilmoituksia</i>
        </c:if>
	<div id="accordion" class="acc">            
            <c:forEach items="${jobs}" var="job" varStatus="status">
                <h3><a href="#">${job.title}</a></h3>
                <div align="left">
                <ul>
                    <li class="jobtext" id="${job.id}">
                        <b>Työ luotu</b><br><fmt:formatDate pattern="dd.MM.yyyy" value="${job.created}" /><br><br>
                        <b>Työn kuvaus:</b><br>${job.description}<br><br>
                        <b>Voittanut tarjoaja:</b><br>${job.winningOffer.user.name}<br><br>
                        <b>Voittanut tarjoushinta</b><br>${job.winningOffer.price}<br><br>
                        <b>Voittanut tarjoussisältö</b><br>${job.winningOffer.description}<br><br>
                    </li>
                    <c:if test="${job.review==null}">
                    <div class="omatbuttons" align="left" style="margin-top: 10px;">
                        <button onclick="opendialog('${job.winningOffer.user.id}', '${job.winningOffer.user.name}', '${job.id}');">Arvioi työn suorittaja</button>&nbsp;
                    </div>
                    </c:if>
                </ul>
                </div>
            </c:forEach>
	</div>
        <div id="dialog" title="Kirjoita arvio">
            <input type="hidden" id="user_id" value="" />
            <input type="hidden" id="job_id" value="" />
            <div id="user_name"></div><br>
            <b>Tähdet:</b><br>
            <div id="radio3" class="mainbuttons" style="margin-top: 5px; margin-bottom: 10px;">
                
                <input type="radio" id="radio_r_1" name="r_rating" value="1"/><label for="radio_r_1">&#9734;</label>
                <input type="radio" id="radio_r_2" name="r_rating" value="2"/><label for="radio_r_2">&#9734;&#9734;</label>
                <input type="radio" id="radio_r_3" name="r_rating" value="3"/><label for="radio_r_3">&#9734;&#9734;&#9734;</label>
                <input type="radio" id="radio_r_4" name="r_rating" value="4"/><label for="radio_r_4">&#9734;&#9734;&#9734;&#9734;</label>
                <input type="radio" id="radio_r_5" name="r_rating" value="5"/><label for="radio_r_5">&#9734;&#9734;&#9734;&#9734;&#9734;</label>
            </div>
            <b>Arvio:</b><br>
            <textarea rows="5" cols="44" id="r_text"></textarea>
            <div class="smallbuttons">
                    <button class="smallbutton" onclick="reviewJob();">Tallenna</button>
                    <button class="smallbutton" onclick="javascript:$( '#dialog' ).dialog('close');">Sulje</button>
            </div>
        </div>        
        <script>
            $(function() {
                    $( "#dialog" ).dialog({
                            height: 300,
                            width: 400,
                            modal: true, 
                            autoOpen: false
                    });
            });
            function opendialog(userId, userName, jobId) {
                document.getElementById('user_id').value = userId;
                document.getElementById('job_id').value = jobId;
                document.getElementById('user_name').innerHTML = 'Arvioi työn tekijä <b>'+userName+'</b>';
                document.getElementById('r_text').value = '';
                $( "#dialog" ).dialog('open');
            }
        </script>
	</body>
</html>

