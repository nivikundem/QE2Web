<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css"><%@include file="css/qe2_stylesheet.css" %> </style>
	<title>Login View</title>      
 </head>
<body>
   	<div id="container">
	<%@ include file="header.jsp" %>
 	<div id="content"> 		
	       <div id="leftdiv">
	        <p>
	       
	        </p>
	        </div>		
			<div id="rightdiv">  
			<p> 
			    <c:if test="${not empty param.login_error}">			    
			          <c:out value="{SPRING_SECURITY_LAST_EXCEPTION.message}"/>			    
			    </c:if>    
        	<form name="f" action="<c:url value='j_spring_security_check'/>"
                    method="POST">
	            <table>
	                <tr><td align="center" colspan="2">Login</td></tr>
	                <tr>
	                    <td>Username:</td>
	                    <td><input type='text' name='j_username' /></td>
	                </tr>
	                <tr>
	                    <td>Password:</td>
	                    <td><input type='password' name='j_password'></td>
	                </tr>
	                <tr>
	                    <td colspan="2">&nbsp;</td>
	                </tr>
	                <tr>
	                    <td colspan='2' align="center"><input name="submit" type="submit">&nbsp;<input name="reset" type="reset"></td>
	                </tr>
	            </table> 
            </form>
			</p>
			</div>
		    <div class"clear"></div>		
	</div> 
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>