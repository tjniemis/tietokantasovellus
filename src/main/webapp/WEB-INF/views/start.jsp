<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<jsp:include page="includes.jsp"/>
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
		
                <c:if test="${user==null}">
                <table width="1000" cellpadding="5">
		<tr>   
                <td width="800" align="center">
                    <button class="mainbutton" onclick="location.href='jobs'">Ty�ilmoitukset</button>
                </td>
                </tr>
                <tr>
                <td width="800" align="center">
                    Selaa ty�ilmoituksia
                </td>
                </tr>
                </table>
                </c:if>
                <c:if test="${user!=null}">
                <table width="1000" cellpadding="5">
		<tr> 
                    <td width="200">                
                        <button class="mainbutton" onclick="location.href='createJob'">1. Ilmoita ty�</button>                
                    </td>
                    <td width="200">
                        <button class="mainbutton" onclick="location.href='jobs'">2. Ty�ilmoitukset (${count.available})</button>
                    </td>
                    <td width="200">
                        <button class="mainbutton" onclick="location.href='personalJobs'">3. Omat ilmoitukset (${count.jobs})</button>
                    </td>
                    <td width="200">
                        <button class="mainbutton" onclick="location.href='personalData'">4. Omat tarjoukset (${count.offers})</button>
                    </td>
                    <td width="200">
                        <button class="mainbutton" onclick="location.href='personalData'">5. Historiatiedot</button>
                    </td>
                    </tr>
                    <tr>
                    <td width="200" valign="top">                
                        Puuttuuko uusien keitti�koneiden asentaja? Yhdistykselt�si verkkosivut? Ilmoita ja l�yd� ty�lle tekij�!
                    </td>
                    <td width="200" valign="top">
                        Ty�kalenterissa tilaa? Selaa ty�ilmoituksia ja tarjoa!
                    </td>
                    <td width="200" valign="top">
                        Seuraa ja hyv�ksy aktiivisia ilmoituksia.
                    </td>
                    <td width="200" valign="top">
                        Seuraa aktiivisia tarjouksiasi.
                    </td>
                    <td width="200" valign="top">
                        Selaa ilmoitus- ja ty�historiaasi. Sek� arvostele t�itten tekij�it�.
                    </td>
                    </tr>
		</table>
                </c:if>
		
		<tr>
                <c:if test="${user==null}">
                
                </c:if>
	</div>
	</body>
</html>


