<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>Ty�lleTekij�!</title>
		<link type="text/css" href="resources/css/main.css" rel="stylesheet" />
		<link type="text/css" href="resources/jquery-start/css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="resources/jquery-start/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="resources/jquery-start/js/jquery-ui-1.8.23.custom.min.js"></script>
		<script>
			$(function() {
				$( "button", ".buttons" ).button();
			});
		</script>
	</head>
	<body>
            <jsp:include page="header.jsp"/>
	<br>
	<h1 style="left: 50%; position: absolute; margin-left: -150px;">Ty�lleTekij�!</h1>
	<div class="buttons">
		<table width="900" cellpadding="10">
		<tr>
		<td width="300">
                <c:if test="${user!=null}">
                    <button class="mainbutton" onclick="location.href='createJob'">Ilmoita ty�</button>
                </c:if>
		</td><td width="300">
		<button class="mainbutton" onclick="location.href='jobs'">Ty�ilmoitukset</button>
		</td><td width="300">
                <c:if test="${user!=null}">
                    <button class="mainbutton" onclick="location.href='personalData'">Omat tiedot</button>
                </c:if>
		</td>
		</tr>
		<tr>
		<td width="250" valign="top">
                <c:if test="${user!=null}">
                    Puuttuuko uusien keitti�koneiden asentaja? Harjakaton uusija? Yhdistykselt�si verkkosivut? Ilmoita ty�st� t��ll� ja l�yd� ty�lle tekij�!
                </c:if>
		</td><td valign="top">
		Ty�kalenterissa tilaa? Selaa ty�ilmoituksia t��ll� ja tarjoa!
		</td><td valign="top">
                <c:if test="${user!=null}">
		Selaa ilmoitus- ja ty�historiaasi. Seuraa aktiivisia ilmoituksia ja tarjoustesi edistymist� t��ll�. 
                 </c:if>
		</td>
		</tr>

		</table>
	</div>
	</body>
</html>


