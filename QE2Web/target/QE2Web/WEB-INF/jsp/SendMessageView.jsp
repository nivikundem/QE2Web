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
	<style type="text/css"><%@include file="css/qe2_stylesheet.css" %></style><!--  stylesheet -->
	<title>About View</title> 	
</head>
<body>
	<div id="container">   
   	<%@ include file="header.jsp" %>    
    <%@ include file="menu.jsp" %>
    <div id="textPara">		   
	  <%-- <c:out value="${message}" />   --%>
	  Sent XML message to the ActiveMQ :: http://localhost:8161/admin/browse.jsp?JMSDestination=CROSSING.HO.PERFORMANCE
    </div>		
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>