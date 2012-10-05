<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
				$( "button", ".smallbuttons" ).button();	
				$( "#radio2" ).buttonset();		
				$( "#datepicker" ).datepicker({ dateFormat: "dd.mm.yy" });	
			});
		</script>
                <style>
                    .error {
                            color: #ff0000;
                    }
                </style>
	</head>
	<body style="text-align:center;margin:0 auto;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10pt;">
	<h1 class="header">Ty�lleTekij�!</h1>
	<br><br><br>
	<h2>Rekister�idy</h2>
	<div id="lomake" align="center" class="lomake">
            
		<table>
                    <form:form name="f" action="addUser" modelAttribute="user" method="POST">
                <tr><td><b>Nimi:</b></td><td><form:input path="name" size="40"/>&nbsp;<form:errors path="name" cssClass="error"/></td></tr>
		<tr><td><b>S�hk�posti:</b></td><td><form:input path="email" size="40"/>&nbsp;<form:errors path="email" cssClass="error" /></td></tr>
		<tr><td><b>Salasana:</b></td><td><form:password path="password" size="40"/>&nbsp;<form:errors path="password" cssClass="error"/></td></tr>
		<tr><td><b>Salasana uudestaan:</b></td><td><form:password path="password_confirm" size="40"/>&nbsp;<form:errors path="password_confirm" cssClass="error"/></td></tr>
                </form:form>
		<tr><td colspan="2" align="center">
		<div class="smallbuttons">
			<button class="ilmoitabutton" onclick="document.f.submit();">Tallenna</button>
                        <button class="ilmoitabutton" onclick="location.href='start'">Peruuta</button>
		</div>
		</td></tr>
		</table>
            
	</div>
	
	</body>
</html>
