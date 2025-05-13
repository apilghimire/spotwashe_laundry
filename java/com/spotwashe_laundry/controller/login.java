package com.spotwashe_laundry.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.spotwashe_laundry.model.User;
import com.spotwashe_laundry.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(asyncSupported = true, urlPatterns = {"/login"})
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession(false); // don't create new session
	    if (session != null && session.getAttribute("currentUser") != null) {
	        // User is already logged in, redirect to home
	    	req.setAttribute("success", "Already Logged IN");
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
		    	req.getRequestDispatcher("/WEB-INF/pages/admin/Dashboard.jsp").forward(req, resp);
		    } else if ("User".equals(userRole)) {
		    	User currentUser = (User) session.getAttribute("currentUser");
		        Map<String, String> userInfo = new HashMap<>();
		        userInfo.put("name", currentUser.getUserName());
		        userInfo.put("phone", String.valueOf(currentUser.getNumber()));
		        userInfo.put("address", currentUser.getUserAddress());
		        req.setAttribute("userInfo", userInfo);
		        
		        
		    	req.getRequestDispatcher("/WEB-INF/pages/user/Dashboard.jsp").forward(req, resp);				    }


	    } else {
	        // No active session, show login page
	        req.getRequestDispatcher("/WEB-INF/pages/common/login.jsp").forward(req, resp);
	    }
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Receive Form Data
				String email = req.getParameter("email");
				String password =req.getParameter("password");

				// 2. Validate (Optional: can be expanded later)
				if (email == null || password.isEmpty()) {
				    
					// Redirect back if any field missing
					req.setAttribute("error", "Please fill all fields.");
					req.getRequestDispatcher("/WEB-INF/pages/common/login.jsp").forward(req, resp);
					return;
				}

				// 5. Call UserServices to handle business logic
				UserServices userServices = new UserServices();
				User user = userServices.login(email,password);

				if (user == null) {
					HttpSession errorSession = req.getSession();
					errorSession.setAttribute("invalidCustomer", "Invalid Email ");
					req.setAttribute("error", "User not found please check credentials");
					req.getRequestDispatcher("/WEB-INF/pages/common/login.jsp").forward(req, resp);

				} else if (user != null) {
				    HttpSession session = req.getSession();
				    session.setAttribute("currentUser", user);
				    req.setAttribute("success", "Login Success");

				    // Create a cookie for the user role
				    Cookie roleCookie = new Cookie("userRole", user.getRole());
				    roleCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
				    roleCookie.setPath("/"); // accessible across the whole app
				    resp.addCookie(roleCookie);

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
				    	req.getRequestDispatcher("/WEB-INF/pages/admin/Dashboard.jsp").forward(req, resp);
				    } else if ("User".equals(userRole)) {
				    	req.getRequestDispatcher("/WEB-INF/pages/user/Dashboard.jsp").forward(req, resp);				    }


				}


	}
	
}
