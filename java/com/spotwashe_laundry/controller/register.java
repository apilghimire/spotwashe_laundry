package com.spotwashe_laundry.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.spotwashe_laundry.model.User;
import com.spotwashe_laundry.services.UserServices;
import com.spotwashe_laundry.util.PasswordEncryption;

@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public register() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/common/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. Receive Form Data
		String userName = req.getParameter("userName");
		String numberStr = req.getParameter("number");
		String email = req.getParameter("email");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String userAddress = req.getParameter("userAddress");
		String role = req.getParameter("role");
		String password = PasswordEncryption.encrypt(email, req.getParameter("password"));

		// 2. Validate (Optional: can be expanded later)
		if (userName == null || numberStr == null || email == null || dateOfBirth == null ||
		    userAddress == null || role == null || password == null ||
		    userName.isEmpty() || numberStr.isEmpty() || email.isEmpty() ||
		    dateOfBirth.isEmpty() || userAddress.isEmpty() || role.isEmpty() || password.isEmpty()) {
		    
			// Redirect back if any field missing
			req.setAttribute("error", "Please fill all fields.");
			req.getRequestDispatcher("/WEB-INF/pages/common/register.jsp").forward(req, resp);
			return;
		}

		// 3. Convert Phone Number
		Long number = null;
		try {
			number = Long.parseLong(numberStr);
		} catch (NumberFormatException e) {
			req.setAttribute("error", "Invalid phone number.");
			req.getRequestDispatcher("/WEB-INF/pages/common/register.jsp").forward(req, resp);
			return;
		}

		// 4. Create User Object
		User user = new User(userName, number, email, dateOfBirth, userAddress, role, password);

		// 5. Call UserServices to handle business logic
		UserServices userServices = new UserServices();
		boolean isRegistered = userServices.registerUser(user);

		// 6. Redirect Based on Result
		if (isRegistered) {
			req.setAttribute("success", "Registration successful! Please login.");
			req.getRequestDispatcher("/WEB-INF/pages/common/register.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Registration failed. Please try again.");
			req.getRequestDispatcher("/WEB-INF/pages/common/register.jsp").forward(req, resp);
		}
	}
}
