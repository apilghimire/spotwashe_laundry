package com.spotwashe_laundry.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.spotwashe_laundry.model.Service;
import com.spotwashe_laundry.model.User;
import com.spotwashe_laundry.services.ServiceServices;
import com.spotwashe_laundry.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(asyncSupported = true, urlPatterns = {"/order"})
public class order extends HttpServlet {
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
		    	req.getRequestDispatcher("/WEB-INF/pages/admin/order.jsp").forward(req, resp);
		    } else if ("User".equals(userRole)) {
		    	ArrayList<String> lis = new ArrayList<String>();
		    	ServiceServices servicess = new ServiceServices();
		    	ArrayList<Service> list = servicess.serviceList();
		    	for(int i =0;i< list.size();i++) {
		    		lis.add(list.get(i).getServiceName());
		    	}
		    	req.setAttribute("services", list);

		    	req.getRequestDispatcher("/WEB-INF/pages/user/order.jsp").forward(req, resp);				    }


	    } else {
	        // No active session, show login page
	        req.getRequestDispatcher("WEB-INF/pages/common/login.jsp").forward(req, resp);
	    }
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/pages/user/order.jsp").forward(req, resp);

	}
}
