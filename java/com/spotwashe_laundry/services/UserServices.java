package com.spotwashe_laundry.services;

import java.sql.*;
import java.util.ArrayList;

import com.spotwashe_laundry.config.DbConnection;
import com.spotwashe_laundry.model.Service;
import com.spotwashe_laundry.model.User;
import com.spotwashe_laundry.util.PasswordEncryption;

public class UserServices {
	
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public UserServices() {
		try {
			this.dbConn = DbConnection.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public boolean registerUser(User user) {
		boolean flag = false;
		String query = "INSERT INTO User (name, number, email, dateofbirth, address, role, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement st = dbConn.prepareStatement(query)) {
			st.setString(1, user.getUserName());
			st.setLong(2, user.getNumber());
			st.setString(3, user.getEmail());
			st.setString(4, user.getDateOfBirth());
			st.setString(5, user.getUserAddress());
			st.setString(6, user.getRole());
			st.setString(7, user.getPassword());
			st.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception on DB");
		}
		return flag;
	}
	
	public User login(String email, String password) {
		User customer = null;
		try {
			String query = "SELECT * FROM user WHERE email = ?";

			PreparedStatement pst = dbConn.prepareStatement(query);
			pst.setString(1, email);

			ResultSet set = pst.executeQuery();

			if (set.next()) {
				customer = new User();

				customer.setUserId(set.getInt("userid"));

				// data from db
				String name = set.getString("name");
				// set to user object
				customer.setUserName(name);
				customer.setEmail(set.getString("email"));
				customer.setNumber(set.getLong("number"));
				String password1 = set.getString("password");
				String decryptPassword = PasswordEncryption.decrypt(password1, email);
				customer.setPassword(decryptPassword);


				customer.setDateOfBirth(set.getString("dateofbirth"));
				customer.setUserAddress(set.getString("address"));
				customer.setRole(set.getString("role"));
				if (decryptPassword != null && set.getString("email").equals(email)
						&& decryptPassword.equals(password)) {
					return customer;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean update(User user) {
	    boolean flag = false;
	    String query = "UPDATE User SET name = ?, number = ?, dateofbirth = ?, address = ?, password = ? WHERE userId = ?";

	    try (PreparedStatement st = dbConn.prepareStatement(query)) {
	        st.setString(1, user.getUserName());
	        st.setLong(2, user.getNumber());
	        st.setString(3, user.getDateOfBirth());
	        st.setString(4, user.getUserAddress());
	        st.setString(5, PasswordEncryption.encrypt(user.getEmail(),user.getPassword()));
	        st.setInt(6, user.getUserId());
	        int rowsAffected = st.executeUpdate();
	        flag = rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Exception during update");
	    }

	    return flag;
	}
	
	public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM User WHERE role = ?" ;

        try (PreparedStatement pst = dbConn.prepareStatement(query)){
    		pst.setString(1, "User");
        	ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User(
                    rs.getInt("userid"),
                    rs.getString("name"),
                    rs.getLong("number"),
                    rs.getString("email"),
                    rs.getString("dateofbirth"),
                    rs.getString("address"),
                    rs.getString("role"),
                    PasswordEncryption.decrypt(rs.getString("password"),rs.getString("email"))
                );
                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }
	
    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM User WHERE userid = ?";

        try (PreparedStatement pst = dbConn.prepareStatement(query)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("userid"),
                    rs.getString("name"),
                    rs.getLong("number"),
                    rs.getString("email"),
                    rs.getString("dateofbirth"),
                    rs.getString("address"),
                    rs.getString("role"),
                    PasswordEncryption.decrypt(rs.getString("password"),rs.getString("email"))
    				
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public boolean deleteUserById(int userId) {
        boolean result = false;
        String query = "DELETE FROM User WHERE userid = ?";

        try (PreparedStatement pst = dbConn.prepareStatement(query)) {
            pst.setInt(1, userId);
            int rows = pst.executeUpdate();
            result = rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
