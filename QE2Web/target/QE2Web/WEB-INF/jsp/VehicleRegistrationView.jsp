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
	<title>Vehicle Owner Details Entry</title>  
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>  
    <script>
    $(document).ready(function(){    	
    	entryViewFormSubmit();    	
   	});
     
    function entryViewFormSubmit(){
		$("#entryViewForm").submit(function(event){    	
    	var vrnValue = $("#vrn").val(); 
    	var nameValue = $("#name").val(); 
    	var postcodeValue = $("#postcode").val(); 
    	if(vrnValue == ""){
    	$("#vrnerrormessage").html("*");
    	event.preventDefault();
    	}
    	if(nameValue == ""){
        	$("#nameerrormessage").html("*");
        	event.preventDefault();
       	}
    	
    	if(postcodeValue == ""){
        	$("#postcodeerrormessage").html("*");
        	event.preventDefault();
       	}    	
    	});
	}  
     
    </script> 
    
 </head>
<body>
   	<div id="container">	
	<%@ include file="header.jsp" %>
	<%@ include file="menu.jsp" %>	
 	<div id="content"> 		
	       <div id="leftdiv">
	        <p>
	        <form  method="POST" action='add.html' name="entryViewForm" id="entryViewForm">
			<div id="entrydiv">
			    <table>
			       <tr><td>VRN</td><td> <input type="text" id="vrn" name="vrn"/><div id="vrnerrormessage"></div><br/></td></tr>
			       <tr><td>Vehicle Type</td><td>
			       <select id="vehicleType" name="vehicleType">
					  <option value="CAR">CAR</option>
					  <option value="VAN">VAN</option>
					   <option value="TRUCK">TRUCK</option>
					   <option value="CAMPERVAN">CAMPERVAN</option>
					  <option value="MOTORCYCLE">MOTORCYCLE</option>					  
					</select>
			       <div id="nameerrormessage"></div><br/></td></tr>
				   <tr><td></td><td align="center"><br/><input type="submit" class="quicksearchbutton" value="ADD" /></td></tr>			    
			    </table>
			</div>
		   </form>
	        </p>
	        </div>		
			<div id="rightdiv">  
			<p> 
			<table id="activitytable">
			   	<tr>
					<th>UID</th>
					<th>Vehicle Registration Number</th>
					<th>Vehicle Type</th>
				  	<th>Edit</th>		
				  	<th>Delete</th>	                 
				</tr>
				
				<c:forEach items="${vehicleRegistrationDetailsList}" var="vehicleRegistrationDetails">
				<form  method="GET" action='edit' name="editForm${vehicleRegistrationDetails.uid}" id="editForm${vehicleRegistrationDetails.uid}">
				    <input type="hidden" name="uid" value="${vehicleRegistrationDetails.uid}">
				    <tr>
				        <td>${vehicleRegistrationDetails.uid} </td>
				        <td>${vehicleRegistrationDetails.vrn} </td>																				
				  <%--       <td> <input type="text" id="vehicleTypeText" name="vehicleTypeText" value="${vehicleRegistrationDetails.vehicleType}"/> <a href="edit/${vehicleRegistrationDetails.uid}/${vehicleRegistrationDetails.vehicleType}">Update</a> </td> --%>
				       
				       <td>
						<select id="ddlVehicleType" name="ddlVehicleType">
						    <c:choose>						    
								<c:when test = "${vehicleRegistrationDetails.vehicleType=='CAR'}">
								<option value="CAR" selected>CAR</option>
								</c:when>
								<c:otherwise>
								<option value="CAR">CAR</option>
								</c:otherwise>
							</c:choose>	
							<c:choose>			
								<c:when test = "${vehicleRegistrationDetails.vehicleType=='VAN'}">
								<option value="VAN" selected>VAN</option>
								</c:when>
								<c:otherwise>
								<option value="VAN">VAN</option>
								</c:otherwise>
							</c:choose>
							<c:choose>	
								<c:when test = "${vehicleRegistrationDetails.vehicleType=='TRUCK'}">
								<option value="TRUCK" selected>TRUCK</option>
								</c:when>
								<c:otherwise>
								<option value="TRUCK">TRUCK</option>
								</c:otherwise>
							</c:choose> 
							<c:choose>
								<c:when test = "${vehicleRegistrationDetails.vehicleType=='CAMPERVAN'}">
								<option value="CAMPERVAN" selected>CAMPERVAN</option>
								</c:when>
								<c:otherwise>
								<option value="CAMPERVAN">CAMPERVAN</option>
								</c:otherwise>							
							</c:choose>
							<c:choose>
								<c:when test = "${vehicleRegistrationDetails.vehicleType=='MOTOR CYCLE'}">
								<option value="MOTOR CYCLE" selected>MOTOR CYCLE</option>	
								</c:when>
								<c:otherwise>
								<option value="MOTOR CYCLE">MOTOR CYCLE</option>	
								</c:otherwise>
							</c:choose>		  
						</select>
				       </td>
				       <td><input type="Submit" value="Edit"> </td>
				       <td><a href="delete/${vehicleRegistrationDetails.uid}">X</a></td>
				    </tr>
				    </form>
				</c:forEach>
			   
			</table> 
			</p>
			</div>
		    <div class"clear"></div>		
	</div> 
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>