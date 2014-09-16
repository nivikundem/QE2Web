package com.qe2.db.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.bridge.crossing.model.CrossingActivityObjModel;

public class DBDetails {
	
	private static final Logger LOG = Logger.getLogger(DBDetails.class.getName());
	 //get database connections
	/**
	 * getDBConnection get mysql db connection details
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getDBConnectionForCrossing() throws ClassNotFoundException,
	SQLException {
		// Load the mySql Driver class
		Class.forName("com.mysql.jdbc.Driver");		
		// build the connection string, and get a connection
		String db_connect_string = "jdbc:mysql://localhost/";	
		String dbName = "qe2_bridge";
		String userName = "root"; 
		String password = "root";
		Connection con = DriverManager.getConnection(db_connect_string+dbName,userName,password);	
		return con;
    }
    	
	/**
	 * getQueryString
	 * @param sortString 
	 * @param quickSearchTextboxVar 
	 * @param sqlQueryType
	 * @return
	 */
	public String getSqlQueryForCrossing(String quickSearchTextboxVar, String sortString){		
		String	sqlQuery = "SELECT uid, gate_number , vehicle_type , direction, vrn, crossing_datetime FROM qe2_bridge.bridge_crossing_performance   ";     			  			
		if(!quickSearchTextboxVar.equalsIgnoreCase("ALL")){
			sqlQuery = sqlQuery + " where vrn like('%"+quickSearchTextboxVar+"%')";
		}
		if(sortString.equalsIgnoreCase("vrn")){
			sqlQuery = sqlQuery + "order by  vrn ";
		}
		else{
			sqlQuery = sqlQuery + " order by crossing_datetime desc";
		}
		return sqlQuery;		
	}
	
	/**
	 * getCountByVehicleTypeQuery
	 * @return
	 */
	  public String getCountByVehicleTypeQueryForCrossing(){		   
		   String countByVehicleTypeQuery = "select vehicle_type vehicleType, count(vehicle_type) 	finalCount from qe2_bridge.bridge_crossing_performance group by vehicle_type \n";
			   return countByVehicleTypeQuery;		   
	   }
	  
	 
	  public final String ACTIVITY_NAME = "BRIDGE-HOURLY-ACTIVITY";
		 //get database connections
		/**
		 * getDBConnection get mysql db connection details
		 * @return
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		public Connection getDBConnectionForQE2() throws ClassNotFoundException,
		SQLException {
			// Load the mySql Driver class
			Class.forName("com.mysql.jdbc.Driver");		
			// build the connection string, and get a connection
			String db_connect_string = "jdbc:mysql://localhost/";	
			String dbName = "qe2_bridge";
			String userName = "root"; 
			String password = "root";
			Connection con = DriverManager.getConnection(db_connect_string+dbName,userName,password);	
			return con;
	    }
	    	
		/**
		 * getQueryString
		 * @param sqlQueryType
		 * @return
		 */
		public String getSqlQueryForQE2(String sqlQueryType){		
			String sqlQuery ="";		
			if(sqlQueryType.equalsIgnoreCase("BRIDGE-HOURLY-ACTIVITY")){
				sqlQuery = "SELECT uid, gate_number, vehicle_type, direction, vrn, crossing_datetime FROM qe2_bridge.bridge_crossing_performance";     			  			
			}
			System.out.println(sqlQuery);
			return sqlQuery;		
		}
		
		
		
		 //get database connections
		/**
		 * getDBConnection
		 * @return
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		public Connection getDBConnectionForToken() throws ClassNotFoundException,
		SQLException {
			Class.forName("com.mysql.jdbc.Driver");	// Load the mySql Driver class			
			Connection con = DriverManager.getConnection(DB_CONNECTION_STRING,USERNAME,PASSWORD);	
			return con;
	    }
	    	
		/**
		 * getQueryString
		 * @param sqlQueryType
		 * @param token 
		 * @param value 
		 * @param key 
		 * @return
		 */
		public String getSqlQueryForToken(String sqlQueryType, long token, String key, String value){		
			String sqlQuery ="";		
			if(sqlQueryType.equalsIgnoreCase(SET_PROXAMA_DATA)){
		    	sqlQuery = "INSERT INTO proxama.proxama_api_table (token, keyname, keyvalue) VALUES("+token+",'"+key+"','"+value+"')";   	    	
		    } 
		    else if(sqlQueryType.equalsIgnoreCase(GET_VALUE_USING_TOKEN_AND_KEY)){
		    	sqlQuery = "SELECT keyvalue FROM proxama.proxama_api_table p where token ="+token+" and keyname ='"+key+"'";  	    	
		    }
		    else if(sqlQueryType.equalsIgnoreCase(TOKEN_REGISTRATION)){
				sqlQuery = "INSERT INTO proxama.token_registration_table (token) VALUES ("+token+")";
			}	
		    else if(sqlQueryType.equalsIgnoreCase(GET_TOKEN_LIST)){
		    	sqlQuery = "SELECT token FROM proxama.token_registration_table ";  	    	
		    }
		    else if(sqlQueryType.equalsIgnoreCase(IS_RECORD_EXIST_FOR_THE_KEY)){
		    	sqlQuery = "SELECT COUNT(*) count FROM proxama.proxama_api_table where keyname ='"+key+"'";  	    	
		    }
			System.out.println(sqlQuery);
			return sqlQuery;		
		}
		
		
		public static final String DB_CONNECTION_STRING = "jdbc:mysql://localhost/proxama";
		public static final String USERNAME = "root";
		public static final String PASSWORD = "root";
		
		public static final String  GET_TOKEN_LIST = "GET_TOKEN_LIST";
		public static final String  TOKEN_REGISTRATION = "TOKEN_REGISTRATION";
		public static final String  GET_VALUE_USING_TOKEN_AND_KEY = "GET_VALUE_USING_TOKEN_AND_KEY";
		public static final String  SET_PROXAMA_DATA = "SET_PROXAMA_DATA";
		public static final String  IS_TOKEN_REGISTERED = "IS_TOKEN_REGISTERED";
		public static final String  IS_RECORD_EXIST_FOR_THE_KEY = "IS_RECORD_EXIST_FOR_THE_KEY";
		
	  
}
