package com.spotwashe_laundry.controller;

import com.spotwashe_laundry.model.Order;
import com.spotwashe_laundry.services.OrderServices;
import com.spotwashe_laundry.services.ReportServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(asyncSupported = true, urlPatterns = {"/completeOrder"})
public class UpdateOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderIdStr = req.getParameter("orderId");

        if (orderIdStr != null) {
            try {
                int orderId = Integer.parseInt(orderIdStr);

                OrderServices orderService = new OrderServices();
                boolean updated = orderService.markOrderAsComplete(orderId);

                if (updated) {
                    req.setAttribute("success", "Order #" + orderId + " marked as complete.");
                } else {
                    req.setAttribute("error", "Failed to update order #" + orderId + ".");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid order ID format.");
                e.printStackTrace();
            }
        } else {
            req.setAttribute("error", "Order ID missing from request.");
        }
     // Fetch report data
    	ReportServices reportServices = new ReportServices();

        Map<String, Object> overview = new HashMap<>();
        overview.put("totalOrder", reportServices.getTotalOrders());
        overview.put("totalUser", reportServices.getTotalUsers());
        overview.put("totalCompleted", reportServices.getCompletedOrders());
        overview.put("totalProfit", reportServices.getTotalRevenue());
        req.setAttribute("overview", overview);
        OrderServices servicess = new OrderServices();
        ArrayList<Order> allOrderList= servicess.allOrderList();
        req.setAttribute("userOrderList", allOrderList);
        req.getRequestDispatcher("/WEB-INF/pages/admin/Dashboard.jsp").forward(req, resp);

    }
}

