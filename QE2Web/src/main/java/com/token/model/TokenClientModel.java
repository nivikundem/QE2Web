package com.token.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.qe2.db.utilities.DBDetails;
/**
 * 
 * @author NK
 *
 */
public class TokenClientModel {
	private static final Logger LOG = Logger.getLogger(TokenClientModel.class.getName());
	private static DBDetails dBDetails = new DBDetails();
	
	public static List<Long> getRegistrationList() throws SQLException {		
			Statement stmt = null;
			Connection con = null;
			ArrayList<Long> tokenList = new ArrayList<Long>();
			try {			 		
				con = dBDetails.getDBConnectionForToken();
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(dBDetails.getSqlQueryForToken("GET_TOKEN_LIST",0L,null,null));
				while (rs.next()) {				
					try{					
						tokenList.add(rs.getLong("token"));
					}
					catch (NullPointerException npe)
					{
						LOG.info(npe.toString());
					}
				}				
				return tokenList;
			} catch (Exception e) {
				LOG.info(e.getMessage());
			}
			finally{
				stmt.close();
				con.close();			
			}
			return tokenList;
		}
}
