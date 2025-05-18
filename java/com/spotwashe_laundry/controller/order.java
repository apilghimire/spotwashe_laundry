package com.spotwashe_laundry.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.spotwashe_laundry.model.Order;
import com.spotwashe_laundry.model.Service;
import com.spotwashe_laundry.model.User;
import com.spotwashe_laundry.services.OrderServices;
import com.spotwashe_laundry.services.ServiceServices;

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
		    	ArrayList<String> lis = new ArrayList<String>();
		    	ServiceServices servicess = new ServiceServices();
		    	ArrayList<Service> list = servicess.serviceList();
		    	servicess.getRatePrice(1);
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
		// 1. Receive Form Data
	    HttpSession session = req.getSession(false); // don't create new session
				int serviceType = Integer.parseInt(req.getParameter("serviceType"));
				int weight = Integer.parseInt(req.getParameter("weight"));
				String pickUpDate = req.getParameter("pickup");
				String dropOffDate = req.getParameter("dropoff");
				ServiceServices servicess = new ServiceServices();
				int basePrice =servicess.getRatePrice(serviceType);
				User currentUser = (User) session.getAttribute("currentUser");
				Order place = new Order(serviceType,weight,pickUpDate,dropOffDate);
				place.setCustomerID(currentUser.getUserId());
				long finalPrice =(long)basePrice*weight;
				place.setFinalPrice(finalPrice);
				
				// 5. Call UserServices to handle business logic
				OrderServices orderServices = new OrderServices();
				boolean isOrdered = orderServices.registerOrder(place);

				// 6. Redirect Based on Result
				if (isOrdered) {
					req.setAttribute("success", "Order Placed Sucessfully.");
					req.getRequestDispatcher("/WEB-INF/pages/user/order.jsp").forward(req, resp);
				} else {
					req.setAttribute("error", "Order Place Failed. Please try again.");
					req.getRequestDispatcher("/WEB-INF/pages/user/order.jsp").forward(req, resp);
				}
				
				

	}
}
