<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				$( "input:submit, button", ".smallbuttons" ).button();
				$( "#radio2" ).buttonset();		
				$( "#datepicker" ).datepicker({ dateFormat: "dd.mm.yy" });	
			});
		</script>
	</head>
	<body style="text-align:center;margin:0 auto;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10pt;">
	<h1 class="header">TyölleTekijä!</h1>
	<br><br><br>
	<h2>Kirjaudu</h2>
	<div id="lomake" align="center" class="lomake">            
		<table>
                <form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
                    <tr><td><b>Sähköposti:</b></td><td><input type="text" name="j_username" size="40"/></td></tr>
                    <tr><td><b>Salasana:</b></td><td><input type="password" name="j_password"  size="40"/></td></tr>
                
		<tr><td colspan="2" align="center">
		<div class="smallbuttons">
			<input type="submit" class="ilmoitabutton" onclick="document.f.submit();" value="Kirjaudu" />
			<button class="ilmoitabutton" onclick="location.href='register'">Rekisteröidy</button>
		</div>                        
		</td></tr>
                </form>
		</table>
            
	</div>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	</body>
</html>

