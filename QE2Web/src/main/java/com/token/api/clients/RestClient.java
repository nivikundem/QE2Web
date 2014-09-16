package com.token.api.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
 
/**
 * 
 * @author NK
 *
 */
public class RestClient { 

	/**
	 * invokeRestClient
	 * @param urlStr
	 * @return
	 */
	public static String invokeRestClient(String urlStr){
	 String output = null;
	 String response = null;
	 try {		 
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");				
			conn.setRequestProperty("Accept", "text/*");		 
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}		 
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				response = output;
			}				
			conn.disconnect();	
			return response;
		  } catch (MalformedURLException e) {		 
			e.printStackTrace();		 
		  } catch (IOException e) {	 
			e.printStackTrace();		 
		  }
	    return response;		
	}
	
	/**
	 * invokeSearchClient
	 * @param urlStr
	 * @return HashMap<String, String>
	 */
	public static  HashMap<String, String> invokeSearchClient(String urlStr){		 
		 HashMap<String, String> tokenDataMap = new HashMap<String, String>();
		 try {			 
				URL url = new URL(urlStr);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");				
				conn.setRequestProperty("Accept", "application/xml");		 
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}		 
				BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	            String apiOutput = br.readLine();		         
	            conn.disconnect();		 
	            tokenDataMap =  getApiMap(apiOutput);		        
				return tokenDataMap;
			  } catch (MalformedURLException e) {		 
				e.printStackTrace();		 
			  } catch (IOException e) {	 
				e.printStackTrace();		 
			  }
		  
		return tokenDataMap;			
		} 
		
	/**
	 * Get API in Hashmap format
	 * @param xml
	 * @return
	 */
	public static  HashMap<String, String> getApiMap(String xml) {			
			HashMap<String, String> tokenDataMap = new HashMap<String, String>();
			try {			
				InputSource inputSource = new InputSource(new StringReader(xml));
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document xmlDocument = db.parse(inputSource);		
				Element rootElement = xmlDocument.getDocumentElement();
				NodeList proxamaDataList = rootElement
						.getElementsByTagName("proxamaData");				
				for (int i = 0; i < proxamaDataList.getLength(); i++) {// for each proxama in the  list				
					Element proxamaData = (Element) proxamaDataList.item(i);				
					tokenDataMap.put(((Element) (proxamaData.getElementsByTagName("key")).item(0)).getChildNodes().item(0).getNodeValue(), ((Element) (proxamaData.getElementsByTagName("value")).item(0)).getChildNodes().item(0).getNodeValue());
				}		
				return tokenDataMap;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tokenDataMap;
		}
}