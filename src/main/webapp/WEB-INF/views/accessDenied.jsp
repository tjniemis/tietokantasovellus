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
	<br>
	<h1 style="left: 50%; position: absolute; margin-left: -150px;">TyölleTekijä!</h1>
	<div class="buttons">
            <br><br>
            <h2 align="center">Not so fast junior! Now go <a href="start">back</a> where you came from!</h2>
	</div>
	</body>
</html>


