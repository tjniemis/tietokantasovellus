<%-- 
    Document   : header
    Created on : 24.9.2012, 16:19:30
    Author     : tesuomin
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="headertext">                
<c:choose>
    <c:when test="${user==null}">
        <a href="login">[Kirjaudu]</a>&nbsp;<a href="register">[Rekisteröidy]</a>
    </c:when>  
    <c:otherwise>
        Tervetuloa <c:out value="${user.name}" /> <a href="logout">[Kirjaudu Ulos]</a>
    </c:otherwise>
</c:choose>
</header>