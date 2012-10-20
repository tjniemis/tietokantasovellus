<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="headertext">                
<c:choose>
    <c:when test="${user==null}">
        <a href="login">[Kirjaudu]</a>&nbsp;<a href="register">[Rekisteröidy]</a>
    </c:when>
    <c:otherwise>
        Tervetuloa <c:out value="${user.name}" />&nbsp;
        <a href="start">[Etusivu]</a>&nbsp;
        <c:if test='${user.role=="ROLE_USER"}'><a href="userdata">[Omat tiedot]</a>&nbsp;</c:if>
        <a href="<c:url value='j_spring_security_logout' />">[Kirjaudu Ulos]</a>
    </c:otherwise>
</c:choose>
</header>