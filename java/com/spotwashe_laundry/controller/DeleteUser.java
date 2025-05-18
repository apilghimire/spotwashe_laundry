package com.spotwashe_laundry.controller;

import com.spotwashe_laundry.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = {"/deleteUser"})
public class DeleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	String userIdStr = req.getParameter("userId");
    	System.out.println(userIdStr);

        if (userIdStr == null || userIdStr.isEmpty()) {
        	System.out.println("couln't delete");
            resp.sendRedirect(req.getContextPath() + "/customers");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr);

            UserServices userServices = new UserServices();
            boolean deleted = userServices.deleteUserById(userId);

            if (deleted) {
                resp.sendRedirect(req.getContextPath() + "/customers?success=User+deleted");
            } else {
                resp.sendRedirect(req.getContextPath() + "/customers?error=User+could+not+be+deleted");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/customers?error=Invalid+user+ID");
        }
    }
}
