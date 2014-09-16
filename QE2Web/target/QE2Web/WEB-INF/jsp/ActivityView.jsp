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
	<title>Bridge Crossing Activity</title> 
    <script src="/scripts/utility.js"></script> 
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script>
    $(document).ready(function(){    	
    	quickSearchFormSubmit();
    	crossingActivityFormSubmit();
   	});
    </script>
 </head>
<body>
<div id="container">		
   
	<%@ include file="header.jsp" %>	
    <%@ include file="menu.jsp" %>
 
 	<div id="content">	
	
   <div id="leftdiv">
       <form  method="GET" action='ActivityView' name="quickSearchForm" id="quickSearchForm">
			<div>
				&nbsp;Vrn &nbsp; <input type="text" id="quickSearchTextbox" name="quickSearchTextbox" size="15"/>
				&nbsp;
				<span id="errormessage1"></span>
				<input type="submit" name="quickSearchButton" value="Search" class="quicksearchbutton" />
				
			</div>
		</form>
		<div id="division"><img src="finalCountPieChartByVehicletype" id="chart"/>  </div> 
   </div>
   <div id="rightdiv"> 
	 <form method="GET" action="ActivityView" name="entryForm" id="entryForm">
		    Vrn : <input type="text" id="vrn" name="vrn">
		    Direction : 
		    <select id="direction" name="direction">
			  <option value="North">North</option>
			  <option value="South">South</option>			 
			</select>	
		    Gate Numbers :		    
			 <select id="gateNumber" name="gateNumber">
			  <option value="Gate 1">Gate 1</option>
			  <option value="Gate 2">Gate 2</option>			 
			</select>
		    <input type="submit" value="Save" class="quicksearchbutton"/>
		</form>
	 
	    <c:choose>
		    <c:when test="${fn:length(crossingDetailsObjList) > 0}">
			<br/><br/>
			<form  method="POST" action='PdfGenView' name="crossingActivityForm" id="crossingActivityForm">  
				<div>  
				<table id="activitytable">			
				   	<tr>
						<th>UID</th>
						<th><a href="javascript:void(0);" onClick="sortFormSubmit('vrn');return false;">Vehicle Registration Number</a></th>
						<th>Vehicle Type</th>
						<th>Gate No</th>
						<th>Direction</th>
						<th>Date And Time</th>	   
						<!--  <th>PDF? <div id="errormessage2"></div></th>	         -->        
					</tr>
	
					<c:forEach items="${crossingDetailsObjList}" var="crossingDetailsObj">
						<tr>	
							<td><c:out value="${crossingDetailsObj.uid}" /></td>								
							<td><c:out value="${crossingDetailsObj.vrn}" /></td>
							<td><c:out value="${crossingDetailsObj.vehicleType}" /></td>	
							<td><c:out value="${crossingDetailsObj.gateNumber}" /></td>							
							<td><c:out value="${crossingDetailsObj.direction}" /></td>		
							<td><c:out value="${crossingDetailsObj.crossingDateTime}" /></td>
						   <%--  <td><span title="${crossingDetailsObj.vrn}"><input type="checkbox" class="pdf" name="pdf" value="${crossingDetailsObj.uid}"/></span></td> 	  --%>	
						</tr>
					</c:forEach>	
					<tr>
					<td colspan="6" align="center">
					     <a id="folink" href="javascript:void(0);" onClick="window.open('DisplayHtmlView', 'DisplayHtmlView', 'width=400, height=200');">Full Report HTML View</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					     <a id="folink"  href="javascript:void(0);" onClick="window.open('DisplayPdfView', 'DisplayPdfView', 'width=400, height=200');">Full Report PDF View</a></td>		
					 <!-- <td><input type="submit" id="pdfgen" name="pdfgen" value="PDF" align="right"/>&nbsp;&nbsp;</td> -->		 
					</tr>
				</table>
				<br/><br/>
				</div>
			</form>    
			<!--  forms with with hidden values -->    
			<form  method="GET" action='ActivityView' name="sortForm">		   
			<input type="hidden" name="sortField" value="date">
			</form> 
			</c:when>
			<c:otherwise>
			   <div id="textPara"> No records match this criteria.</div>
			</c:otherwise>
			</c:choose>
     </div>
	</div> 	
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>