<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>TyölleTekijä!</title>
		<link type="text/css" href="css/main.css" rel="stylesheet" />
		<link type="text/css" href="jquery-start/css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="jquery-start/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="jquery-start/js/jquery-ui-1.8.23.custom.min.js"></script>
		<script>
			$(function() {
				$( "button", ".smallbuttons" ).button();	
				$( "#radio2" ).buttonset();		
				$( "#datepicker" ).datepicker({ dateFormat: "dd.mm.yy" });	
			});
		</script>
	</head>
	<body style="text-align:center;margin:0 auto;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10pt;">
	<h1 class="header">TyölleTekijä!</h1>
	<br><br><br>
	<h2>Rekisteröidy</h2>
	<div id="lomake" align="center" class="lomake">
		<table>
		<tr><td><b>Sähköposti:</b></td><td><input type="text" size="40"/></td></tr>
		<tr><td><b>Salasana:</b></td><td><input type="text" size="40"/></td></tr>
		<tr><td colspan="2" align="center">
		<div class="smallbuttons">
			<button class="ilmoitabutton" onclick="location.href='start.html'">Kirjaudu</button>
			<button class="ilmoitabutton" onclick="location.href='register.html'">Rekisteröidy</button>
		</div>
		</td></tr>
		</table>
	</div>
	
	</body>
</html>

