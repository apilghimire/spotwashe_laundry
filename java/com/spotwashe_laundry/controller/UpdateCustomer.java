package com.spotwashe_laundry.controller;

import com.spotwashe_laundry.model.Order;
import com.spotwashe_laundry.model.User;
import com.spotwashe_laundry.services.OrderServices;
import com.spotwashe_laundry.services.ReportServices;
import com.spotwashe_laundry.services.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/updateUser")
public class UpdateCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // 1. Get user ID from request
        String userIdParam = req.getParameter("userId");
        if (userIdParam == null || userIdParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/customers");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdParam);

            // 2. Get user object from database
            UserServices userService = new UserServices();
            User user = userService.getUserById(userId);

            if (user != null) {
                // 3. Set user in request scope
                req.setAttribute("userToUpdate", user);
                
                HttpSession session = req.getSession(false); // don't create new session
        	    if (session != null && session.getAttribute("currentUser") != null) {
        	        // User is already logged in, redirect to home
        	    	// Forward to homepage or dashboard
        		    String userRole = null;
        		    Cookie[] cookies = req.getCookies();
        		    
        		    if (cookies != null) {
        		        for (Cookie cookie : cookies) {
        		            if ("userRole".equals(cookie.getName())) {
        		                userRole = cookie.getValue();
        		                break;
        		            }
        		        }
        		    }
        		    // Now you can use userRole
        		    if ("admin".equals(userRole)) {
        		    	
        		    	req.getRequestDispatcher("/WEB-INF/pages/admin/UpdateUser.jsp").forward(req, resp);
        		    } else if ("User".equals(userRole)) {
        		    	
        		    	req.getRequestDispatcher("/WEB-INF/pages/user/Dashboard.jsp").forward(req, resp);				    }


        	    } else {
        	        // No active session, show login page
        	        req.getRequestDispatcher("/WEB-INF/pages/common/login.jsp").forward(req, resp);
        	    }
                
            } else {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("/WEB-INF/pages/common/login.jsp").forward(req, resp);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/customers");
        }
    }
        
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String userIdStr = req.getParameter("userId");
            String numberStr = req.getParameter("number");

            if (userIdStr == null || userIdStr.isEmpty() || numberStr == null || numberStr.isEmpty()) {
                req.setAttribute("error", "User ID and phone number are required.");
                req.getRequestDispatcher("/WEB-INF/pages/admin/UpdateUser.jsp").forward(req, resp);
                return;
            }

            int userId = Integer.parseInt(userIdStr);
            long number = Long.parseLong(numberStr);

            // Get other fields
            String name = req.getParameter("userName");
            String email = req.getParameter("email");
            String dob = req.getParameter("dateOfBirth");
            String address = req.getParameter("address");
            String newPassword = req.getParameter("password");

            // Load existing user
            UserServices userServices = new UserServices();
            User user = userServices.getUserById(userId);

            if (user == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("/WEB-INF/pages/admin/UpdateUser.jsp").forward(req, resp);
                return;
            }


            // Update fields
            user.setUserName(name);
            user.setNumber(number);
            user.setEmail(email);
            user.setDateOfBirth(dob);
            user.setUserAddress(address);

            if (newPassword != null && !newPassword.trim().isEmpty()) {
                user.setPassword(newPassword); // optionally hash it here
            }

            boolean updated = userServices.update(user);

            if (updated) {
                req.setAttribute("success", "User updated successfully.");
                resp.sendRedirect(req.getContextPath() + "/customers");
            } else {
                req.setAttribute("error", "Failed to update user.");
                req.setAttribute("userToUpdate", user);
                req.getRequestDispatcher("/WEB-INF/pages/admin/UpdateUser.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An unexpected error occurred.");
            req.getRequestDispatcher("/WEB-INF/pages/admin/UpdateUser.jsp").forward(req, resp);
        }
    }
    
    }

