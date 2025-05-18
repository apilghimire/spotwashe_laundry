package com.spotwashe_laundry.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.spotwashe_laundry.model.User;
import com.spotwashe_laundry.services.UserServices;
import com.spotwashe_laundry.util.PasswordEncryption;


/**
 * Servlet implementation class profile
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/profile"})
public class profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession(false); // don't create new session
	    if (session != null && session.getAttribute("currentUser") != null) {
	        User currentUser = (User) session.getAttribute("currentUser");
	        req.setAttribute("currentuser", currentUser);

	        // Determine view based on role
	        String userRole = currentUser.getRole();

	        if ("admin".equalsIgnoreCase(userRole)) {
	            req.getRequestDispatcher("/WEB-INF/pages/admin/profile.jsp").forward(req, resp);
	        } else {
	            req.getRequestDispatcher("/WEB-INF/pages/user/profile.jsp").forward(req, resp);
	        }
	    } else {
	        // No session = redirect to login
	        req.getRequestDispatcher("/WEB-INF/pages/common/login.jsp").forward(req, resp);
	    }
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession(false);

	    if (session == null || session.getAttribute("currentUser") == null) {
	        resp.sendRedirect("login");
	        return;
	    }

	    User currentUser = (User) session.getAttribute("currentUser");

	    // 1. Read form fields
	    String fullName = req.getParameter("fullName");
	    String phoneStr = req.getParameter("phone");
	    String dob = req.getParameter("dob");
	    String address = req.getParameter("address");
	    String currentPassword = req.getParameter("currentPassword");
	    String newPassword = req.getParameter("newPassword");

	    // 2. Check: currentPassword must be provided and must match
	    if (currentPassword == null || currentPassword.trim().isEmpty()) {
	        req.setAttribute("error", "Current password is required to update profile.");
	        req.getRequestDispatcher("/WEB-INF/pages/user/profile.jsp").forward(req, resp);
	        return;
	    }

	    if (!currentUser.getPassword().equals(currentPassword)) {
	        req.setAttribute("error", "Current password is incorrect.");
	        req.getRequestDispatcher("/WEB-INF/pages/user/profile.jsp").forward(req, resp);
	        return;
	    }

	    // 3. Compare and update only changed fields
	    boolean updated = false;
	    boolean passwordChange = false;

	    if (!currentUser.getUserName().equals(fullName)) {
	        currentUser.setUserName(fullName);
	        updated = true;
	    }

	    if (!String.valueOf(currentUser.getNumber()).equals(phoneStr)) {
	        try {
	            currentUser.setNumber(Long.parseLong(phoneStr));
	            updated = true;
	        } catch (NumberFormatException e) {
	            req.setAttribute("error", "Invalid phone number format.");
	            req.getRequestDispatcher("/WEB-INF/pages/user/profile.jsp").forward(req, resp);
	            return;
	        }
	    }

	    if (!currentUser.getDateOfBirth().equals(dob)) {
	        currentUser.setDateOfBirth(dob);
	        updated = true;
	    }

	    if (!currentUser.getUserAddress().equals(address)) {
	        currentUser.setUserAddress(address);
	        updated = true;
	    }

	    if (newPassword != null && !newPassword.trim().isEmpty() && !newPassword.equals(currentUser.getPassword())) {
	        currentUser.setPassword(newPassword);
	        passwordChange=true;
	        updated = true;
	    }

	    // 4. Update if anything changed
	    if (updated) {
	        UserServices userServices = new UserServices();
	        boolean success = userServices.update(currentUser); // Implement this method in UserServices

	        if (success) {
	            session.setAttribute("currentUser", currentUser); // Refresh session object
	            req.setAttribute("success", "Profile updated successfully.");
	        } else {
	            req.setAttribute("error", "Failed to update profile. Please try again.");
	        }
	    } else {
	        req.setAttribute("info", "No changes detected.");
	    }

	    req.setAttribute("currentUser", currentUser);
	    if(passwordChange) {
	    session.invalidate();
	    }
	    resp.sendRedirect("dashboard");
	}

}

