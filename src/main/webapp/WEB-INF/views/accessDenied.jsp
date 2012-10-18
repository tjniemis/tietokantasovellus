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
		<table width="1000" cellpadding="5">
		<tr>
		<td width="200">
                <c:if test="${user!=null}">
                    <button class="mainbutton" onclick="location.href='createJob'">1. Ilmoita ty�</button>
                </c:if>
		</td>
                <td width="200">
                    <button class="mainbutton" onclick="location.href='jobs'">2. Ty�ilmoitukset</button>
		</td>
                <td width="200">
                <c:if test="${user!=null}">
                    <button class="mainbutton" onclick="location.href='personalJobs'">3. Omat ilmoitukset</button>
                </c:if>
		</td>
                <td width="200">
                <c:if test="${user!=null}">
                    <button class="mainbutton" onclick="location.href='personalData'">4. Omat tarjoukset</button>
                </c:if>
		</td>
                <td width="200">
                <c:if test="${user!=null}">
                    <button class="mainbutton" onclick="location.href='personalData'">5. Historiatiedot</button>
                </c:if>
		</td>
		</tr>
		<tr>
		<td width="200" valign="top">
                <c:if test="${user!=null}">
                    Puuttuuko uusien keitti�koneiden asentaja? Yhdistykselt�si verkkosivut? Ilmoita ja l�yd� ty�lle tekij�!
                </c:if>
		</td>
                <td width="200" valign="top">
		Ty�kalenterissa tilaa? Selaa ty�ilmoituksia ja tarjoa!
		</td>
                <td width="200" valign="top">
                <c:if test="${user!=null}">
		Seuraa ja hyv�ksy aktiivisia ilmoituksia.
                 </c:if>
		</td>
                <td width="200" valign="top">
                <c:if test="${user!=null}">
		Seuraa aktiivisia tarjouksiasi.
                 </c:if>
		</td>
                <td width="200" valign="top">
                <c:if test="${user!=null}">
		Selaa ilmoitus- ja ty�historiaasi. Sek� arvostele t�itten tekij�it�.
                 </c:if>
		</td>
		</tr>

		</table>
	</div>
	</body>
</html>


