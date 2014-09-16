<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <body>
     
        <h1 id="banner">Unauthorized Access !!</h1>     
        <hr />     
        <c:if test="${not empty error}">
            <div style="color:red">
                Login attempts failed !<br /> 
                Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>     
        <p class="message">Access denied!</p>
        <a href="#" onClick="window.location='login'">Go back to login page</a> 
    </body>
</html>