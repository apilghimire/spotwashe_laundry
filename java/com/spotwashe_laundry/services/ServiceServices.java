package com.spotwashe_laundry.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.spotwashe_laundry.config.DbConnection;
import com.spotwashe_laundry.model.Service;

public class ServiceServices {
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public ServiceServices() {
		try {
			this.dbConn = DbConnection.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Service> serviceList() {
		ArrayList<Service> service = new ArrayList<Service>(); // Create an ArrayList object
		try {
			String query = "SELECT * FROM Service ";

			PreparedStatement pst = dbConn.prepareStatement(query);
			ResultSet set = pst.executeQuery();

			while (set.next()) {
	            Service serv = new Service();
	            serv.setServiceId(set.getInt("serviceid"));
	            serv.setServiceName(set.getString("servicename"));
	            serv.setServicePrice(set.getInt("base_price_per_unit"));
	            service.add(serv);
	        }
			return service;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public int getRatePrice(int serviceType) {
		try {
			String query = "SELECT * FROM `Service` WHERE `serviceid` = ?";
			PreparedStatement pst = dbConn.prepareStatement(query);
			pst.setInt(1, serviceType);
			ResultSet set = pst.executeQuery();
			if (set.next()) {
				return  set.getInt("base_price_per_unit");
				
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
