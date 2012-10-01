<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>TyölleTekijä!</title>
		<link type="text/css" href="resources/css/main.css" rel="stylesheet" />
		<link type="text/css" href="resources/jquery-start/css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="resources/jquery-start/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="resources/jquery-start/js/jquery-ui-1.8.23.custom.min.js"></script>
		
	</head>
	<body style="text-align:center;">
            <jsp:include page="header.jsp"/>
	<br>
	<h1 class="header">TyölleTekijä!</h1>
	<div id="radio2" class="mainbuttons">
		<input type="radio" id="radio11" name="radio2" onclick="location.href='createJob'"/><label for="radio11">Ilmoita työ</label>
		<input type="radio" id="radio22" checked="checked" name="radio2" onclick="location.href='jobs'"/><label for="radio22">Työilmoitukset</label>
		<input type="radio" id="radio33" name="radio2" onclick="location.href='personalData'"/><label for="radio33">Omat tiedot</label>
	</div>
	<br><br>
	<div id="accordion" class="acc">
            <c:forEach items="${jobs}" var="job" varStatus="status">
                    <h3><a href="#">${job.title}</a></h3>
                    <div>
			<p align="left"><b>Työn kuvaus:</b> ${job.description}</p>
			<p align="left"><b>Tarjousten deadline:</b> ${job.expires}</p>			
                        <table cellpadding="0" cellspacing="0">
                        <tr><td class="jobtext"><b>Tarjouksen hinta:</b></td><td align="left"><input type="text" size="30"/></td></tr>
                        <tr><td valign="top" class="jobtext"><b>Tarjouksen teksti:</b></td><td align="left"><textarea rows="7" cols="46"></textarea></td></tr>				
                            <c:choose>
                                <c:when test="${user!=null}">
                                    <tr><td colspan="2" align="center">
                                    <div class="omatbuttons">
                                    <button onclick="location.href='makeOffer/${job.id}'">Tarjoa</button>
                                    <button onclick="opendialog2();">Kysy</button>
                                    </div>
                                    </td></tr>
                                </c:when>  
                            </c:choose>					
                        </table>
			<br><br>
			<div id="tabs-${status.index}">
                            <ul>
                                    <li><a href="#tabs-${status.index}-1">Kysymykset ja vastaukset</a></li>
                                    <c:if test="${job.user.id==user.id}"><li><a href="#tabs-${status.index}-2">Tarjoukset</a></li></c:if>
                            </ul>
                            <div id="tabs-${status.index}-1" align="left" width="800">
                            <c:forEach items="${job.questions}" var="question">
                                <table cellpadding="0" cellspacing="0">
                                <tr><td width="500">
                                <p class="jobtext"><b>Kysymys:</b>
                                        ${question.question}
                                </p>
                                <p class="jobtext"><b>Vastaus:</b>
                                    <c:choose>
                                        <c:when test="${question.answer!=null}">
                                            ${question.answer}
                                        </c:when>  
                                        <c:otherwise>
                                        <i>Työn tilaaja ei ole vielä vastannut kysymykseen.</i>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                </td>
                                <td width="300" align="right">
                                        <div class="smallbuttons">	
                                            <c:if test="${job.user.id==user.id}">
                                                <button class="smallbutton" onclick="opendialog();">Vastaa</button>
                                                <button class="smallbutton" onclick="location.href='removeQuestion/${question.id}'">Poista</button>
                                            </c:if>
                                        </div>
                                </td></tr>
                                </table>
                                <hr>
                            </c:forEach>
                            </div>
                            <c:if test="${job.user.id==user.id}">
                                <div id="tabs-${status.index}-2">
                                    <b>HUOM! Vain tilaaja näkee tämän osion!</b>
                                    <c:forEach items="${job.offers}" var="offer">					
                                        <p align="left" class="jobtext"><b>Tarjoaja: </b><a href="#">${offer.user.name}</a></br>
                                        <b>Hinta: </b>${offer.price}<br>
                                        <b>Teksti: </b>${offer.description}</p>
                                        <div class="smallbuttons" align="left">	
                                                <button class="smallbutton" onclick="location.href='acceptOffer/${offer.id}'">Hyväksy</button>
                                        </div>
                                        <hr>
                                    </c:forEach>
                                </div>
                            </c:if>
			</div>
                    </div>
                    
                </c:forEach>
	</div>
	<div id="dialog" title="Vastaus">
		<textarea rows="5" cols="44"></textarea>
		<div class="smallbuttons">	
			<button class="smallbutton">Vastaa</button>
		</div>
	</div>
	<div id="dialog2" title="Kysymys">
		<textarea rows="5" cols="44"></textarea>
		<div class="smallbuttons">	
			<button class="smallbutton">Kysy</button>
		</div>
	</div>
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
                            height: 179,
                            width: 310,
                            modal: true, 
                            autoOpen: false
                    });
                    $( "#dialog2" ).dialog({
                            height: 179,
                            width: 310,
                            modal: true, 
                            autoOpen: false
                    });
            });

            function opendialog() {
                    $( "#dialog" ).dialog('open');
            }
            function opendialog2() {
                    $( "#dialog2" ).dialog('open');
            }
    </script>
	</body>
</html>

