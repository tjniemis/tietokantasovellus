<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>TyölleTekijä!</title>
		<link type="text/css" href="resources/css/main.css" rel="stylesheet" />
		<link type="text/css" href="resources/jquery-start/css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="resources/jquery-start/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="resources/jquery-start/js/jquery-ui-1.8.23.custom.min.js"></script>
		<script>
			$(function() {
				$( "button", ".omatbuttons" ).button();	
				$( "#radio2" ).buttonset();		
				$( "#datepicker" ).datepicker({ dateFormat: "dd.mm.yy" });	
			});
		</script>
	</head>
	<body style="text-align:center;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10pt;">
	<jsp:include page="header.jsp"/>
	<br>
	<h1 class="header">TyölleTekijä!</h1>
	<div id="radio2" class="mainbuttons">
		<input type="radio" id="radio11" checked="checked" name="radio2"/><label for="radio11">Ilmoita työ</label>
		<input type="radio" id="radio22" name="radio2" onclick="location.href='jobs'"/><label for="radio22">Työilmoitukset</label>
		<input type="radio" id="radio33" name="radio2" onclick="location.href='omat.html'"/><label for="radio33">Omat tiedot</label>
	</div>
	<br><br>
	<h2>Ilmoita työ</h2>
	<div id="lomake" align="center" class="lomake">
		<table>
		<tr><td><b>Työtehtävän otsikko:</b></td><td><input type="text" size="60"/></td></tr>
		<tr><td valign="top"><b>Työtehtävän kuvaus:</b></td><td><textarea rows="7" cols="46"></textarea></td></tr>
		<tr><td valign="top"><b>Tarjousten deadline:</b></td><td><input type="text" id="datepicker"></td></tr>
		<tr><td colspan="2" align="center">
		<div class="omatbuttons">
			<button class="ilmoitabutton" onclick="location.href='omat.html'">Ilmoita</button>
		</div>
		</td></tr>
		</table>
	</div>
	
	</body>
</html>

