<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<jsp:include page="includes.jsp"/>
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
		<input type="radio" id="radio11" checked="checked" name="radio2" onclick="location.href='createJob'"/><label for="radio11">Ilmoita työ</label>
                <input type="radio" id="radio22" name="radio2" onclick="location.href='jobs'"/><label for="radio22">Työilmoitukset(${count.available})</label>
                <input type="radio" id="radio44" name="radio2" onclick="location.href='personalJobs'"/><label for="radio44">Omat ilmoitukset(${count.jobs})</label>
                <input type="radio" id="radio33" name="radio2" onclick="location.href='personalData'"/><label for="radio33">Omat tarjoukset(${count.offers})</label>                    
                <input type="radio" id="radio55" name="radio2" onclick="location.href='jobHistory'"/><label for="radio55">Historiatiedot</label>
	</div>
	<br><br>
	<h2>Ilmoita työ</h2>
	<div id="lomake" align="center" class="lomake">
		<table>
                <form:form name="f" action="addJob" modelAttribute="job" method="POST">
                    <form:hidden path="status" value="0"/>
		<tr>
                    <td><b>Työtehtävän otsikko:</b></td>
                    <td><form:input path="title" size="60"/>&nbsp;<form:errors path="title" cssClass="error"/></td>
                </tr>
                <tr>
                    <td valign="top"><b>Työtehtävän kuvaus:</b></td>
                    <td><form:textarea rows="7" cols="40" path="description" size="60"/>&nbsp;<form:errors path="description" cssClass="error"/></td>
                </tr>
		<tr>
                    <td valign="top"><b>Tarjousten deadline:</b></td>
                    <td><form:input path="expires" id="datepicker"/><form:errors path="expires" cssClass="error"/></td>
                </tr>
                 </form:form>
		<tr>
                    <td colspan="2" align="center">                   
                        <div class="omatbuttons">
                                <button class="ilmoitabutton" onclick="document.f.submit();">Ilmoita</button>
                        </div>
                    </td>
                </tr>
		</table>
	</div>
	
	</body>
</html>

