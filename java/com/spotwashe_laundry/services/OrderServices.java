package com.spotwashe_laundry.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.spotwashe_laundry.config.DbConnection;
import com.spotwashe_laundry.model.Order;

public class OrderServices {
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public OrderServices() {
		try {
			this.dbConn = DbConnection.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	public Order order(int id) {

		try {
			String query = "SELECT * FROM Order WHERE laundryid = ?";

			PreparedStatement pst = dbConn.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet set = pst.executeQuery();

			while (set.next()) {
	            Order serv = new Order(set.getInt("laundryid"),set.getInt("userid"),set.getInt("serviceid"),set.getInt("weight"),set.getString("pickup_datetime"),set.getString("dropoff_datetime"),set.getLong("final_price"));
	            serv.setProgress(set.getString("progress"));
	            return serv;
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean registerOrder(Order order) {
		boolean flag = false;
		String query = "INSERT INTO `Order` ( userid, serviceid, weight, pickup_datetime, dropoff_datetime, final_price, progress) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement st = dbConn.prepareStatement(query)) {
			st.setInt(1,order.getCustomerID() );
			st.setInt(2, order.getServiceType());
			st.setLong(3, order.getWeight());
			st.setString(4, order.getPickUpDate());
			st.setString(5, order.getDropoffDate());
			st.setLong(6, order.getFinalPrice());
			st.setString(7, order.getProgress());
			st.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception on DB");
		}
		return flag;
	}
	
	public ArrayList<Order> customerOrderList(int id) {
		ArrayList<Order> orderList = new ArrayList<Order>(); // Create an ArrayList object
		try {
			String query = "SELECT * FROM `Order` WHERE  userid = ?";
			PreparedStatement pst = dbConn.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet set = pst.executeQuery();

			while (set.next()) {
	            Order serv = new Order(set.getInt(1),set.getInt(2),set.getInt(3),set.getInt(4),set.getString(5),set.getString(6),set.getLong(7));
	            serv.setProgress(set.getString("progress"));
	            orderList.add(serv);
	        }
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Order> allOrderList() {
		ArrayList<Order> orderList = new ArrayList<Order>(); // Create an ArrayList object
		try {
			String query = "SELECT * FROM `Order`";
			PreparedStatement pst = dbConn.prepareStatement(query);
			ResultSet set = pst.executeQuery();

			while (set.next()) {
	            Order serv = new Order(set.getInt(1),set.getInt(2),set.getInt(3),set.getInt(4),set.getString(5),set.getString(6),set.getLong(7));
	            serv.setProgress(set.getString("progress"));
	            orderList.add(serv);
	        }
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean markOrderAsComplete(int orderId) {
        boolean updated = false;
        String query = "UPDATE `Order` SET progress = 'complete' WHERE laundryid = ?";

        try (PreparedStatement pst = dbConn.prepareStatement(query)) {
            pst.setInt(1, orderId);
            int rows = pst.executeUpdate();
            updated = rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }
	
}
