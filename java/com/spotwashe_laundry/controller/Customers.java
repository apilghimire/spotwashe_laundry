package com.spotwashe_laundry.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.spotwashe_laundry.model.Order;
import com.spotwashe_laundry.model.User;
import com.spotwashe_laundry.services.OrderServices;
import com.spotwashe_laundry.services.ServiceServices;
import com.spotwashe_laundry.services.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(asyncSupported = true, urlPatterns = {"/customers"})
public class Customers extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession(false); // don't create new session
	    if (session != null && session.getAttribute("currentUser") != null) {
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
		    	UserServices servicess = new UserServices();
	            ArrayList<User> userList= servicess.getAllUsers();
		        req.setAttribute("userList", userList);
		    	req.getRequestDispatcher("/WEB-INF/pages/admin/Customers.jsp").forward(req, resp);
		    } else if ("User".equals(userRole)) {
		    	req.getRequestDispatcher("/WEB-INF/pages/user/Dashboard.jsp").forward(req, resp);				    }


	    } else {
	        // No active session, show login page
	        req.getRequestDispatcher("WEB-INF/pages/common/login.jsp").forward(req, resp);
	    }
	}
}
