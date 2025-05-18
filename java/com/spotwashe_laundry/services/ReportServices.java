package com.spotwashe_laundry.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.spotwashe_laundry.config.DbConnection;

public class ReportServices {
	private Connection dbConn;
	
	public ReportServices() {
		try {
			this.dbConn = DbConnection.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	public int getTotalOrders() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM `Order`";
            PreparedStatement pst = dbConn.prepareStatement(query);
            ResultSet set = pst.executeQuery();
            if (set.next()) {
                count = set.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalUsers() {
        int count = 0;
        try {
            String query = "SELECT COUNT(DISTINCT userid) AS total FROM `User`";
            PreparedStatement pst = dbConn.prepareStatement(query);
            ResultSet set = pst.executeQuery();
            if (set.next()) {
                count = set.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getCompletedOrders() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM `Order` WHERE progress = 'complete'";
            PreparedStatement pst = dbConn.prepareStatement(query);
            ResultSet set = pst.executeQuery();
            if (set.next()) {
                count = set.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public double getTotalRevenue() {
        double total = 0.0;
        try {
            String query = "SELECT SUM(final_price) AS total FROM `Order` WHERE progress = 'complete'";
            PreparedStatement pst = dbConn.prepareStatement(query);
            ResultSet set = pst.executeQuery();
            if (set.next()) {
                total = set.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
