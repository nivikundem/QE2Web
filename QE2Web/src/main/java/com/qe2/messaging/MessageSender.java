package com.qe2.messaging;




import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.qe2.db.utilities.DBDetails;

public class MessageSender {

	private static final Logger LOG = Logger.getLogger(MessageSender.class.getName());	
    
	//Sending activeMQ xml message to the QUEUE - CROSSING.HO.PERFORMANCE
	public  String sendMessage() throws ClassNotFoundException, SQLException, ParserConfigurationException, DOMException, DatatypeConfigurationException, TransformerException, IOException{
		Connection con = null;
		DBDetails dbDetails = new DBDetails();
		con = dbDetails.getDBConnectionForQE2();
		ResultSet rs = getResultSet(dbDetails.ACTIVITY_NAME, dbDetails, con);
		String xmlStr = getXMLForQ(rs,dbDetails.ACTIVITY_NAME);
		con.close();
		ActiveMqMessageProducer activeMQMessagePoster = new ActiveMqMessageProducer();
		activeMQMessagePoster.sendMessage("CROSSING.HO.PERFORMANCE",xmlStr);
		return xmlStr;
	}

	

    /**
     * Get xml for ActiveMQ
     * @param rs
     * @param queryName
     * @return
     * @throws SQLException 
     * @throws ParserConfigurationException 
     * @throws DatatypeConfigurationException 
     * @throws DOMException 
     * @throws IOException 
     * @throws TransformerException 
     */
	private static String getXMLForQ(ResultSet rs, String queryName) throws SQLException, ParserConfigurationException, DOMException, DatatypeConfigurationException, TransformerException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document xmlOutputDocument = db.newDocument();
				
		// root element
		Element root = xmlOutputDocument.createElement("QE2-CROSSING-DATA");
		xmlOutputDocument.appendChild(root);

		Element customersElement = xmlOutputDocument
				.createElement("report");
		root.appendChild(customersElement);					
		
		while(rs.next()){			
			Element activityElement = xmlOutputDocument
					.createElement("activity");			
			activityElement.setAttribute("uid", rs.getString("uid"));
			activityElement.setAttribute("gate_number", rs.getString("gate_number"));
			activityElement.setAttribute("vehicle_type", rs.getString("vehicle_type"));
			activityElement.setAttribute("direction",rs.getString("direction"));
			activityElement.setAttribute("vrn", rs.getString("vrn"));
			activityElement.setAttribute("crossing_datetime", gregDate(rs.getTimestamp("crossing_datetime")).toString());
			customersElement.appendChild(activityElement);
		}
		
		return generateOutputXml(xmlOutputDocument);
	}

	
	
	/**
     * get resultset for the the query
     */
	private static ResultSet getResultSet(String queryName, DBDetails dbDetails, Connection con) throws SQLException{
		LOG.info(queryName+"   query start");
		String sqlQuery = dbDetails.getSqlQueryForQE2(queryName);
		ResultSet rs = null;
		try {
			Statement stmt;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info(queryName+"   query end");
		return rs;
	}
	
    /**
     * To get xml format date [gregorian calendar date]
     * @param timestamp
     * @return
     * @throws DatatypeConfigurationException
     */
	private static XMLGregorianCalendar gregDate(Date timestamp) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(timestamp);
		XMLGregorianCalendar gregDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		return gregDate;
	}
	
	
	/**
	 * generateOutputXml	 * 
	 * @param xmlOutputDocument
	 * @return String
	 * @throws TransformerException
	 * @throws IOException
	 */
	private static String generateOutputXml(Document xmlOutputDocument)
			throws TransformerException, IOException {
		// TransformerFactory instance is used to create Transformer objects.
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		// create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(xmlOutputDocument);
		transformer.transform(source, result);
		String xmlString = sw.toString();
		return xmlString;
	}
}
