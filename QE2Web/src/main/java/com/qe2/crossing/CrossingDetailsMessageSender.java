package com.qe2.crossing;

import java.rmi.RemoteException;
import com.crossing.www.BridgeCrossingWS.BridgeCrossingWSProxy;

public class CrossingDetailsMessageSender {

//   http://localhost:8080/BridgeCrossingWS/services/BridgeCrossingPort?wsdl
	private static final String SERVICE_URL = "http://localhost:8080/BridgeCrossingWS/services/BridgeCrossingPort"; 
	
	/**
	 * To send the crossing details using webservice url
	 * @param vrn
	 * @param gateNumber
	 * @param direction
	 */
	public static void CrossingDetails(String vrn, String gateNumber,
			String direction) {
		BridgeCrossingWSProxy port = new BridgeCrossingWSProxy(SERVICE_URL);

		String crossingDetails;
		try {
			crossingDetails = port.sendCrossingDetails(vrn, gateNumber, direction);
			System.out.println("send crossing details  "+crossingDetails);
		} catch (RemoteException e) {			
			e.printStackTrace();
		}
	}

}
