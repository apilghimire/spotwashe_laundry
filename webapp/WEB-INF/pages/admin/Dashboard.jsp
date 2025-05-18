<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard | Spotwashe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admindashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<header class="navbar">
    <div class="navbar-left">
        <div class="brand">spotwashe</div>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/dashboard"><i class="fas fa-th-large"></i> Dashboard</a>
            <a href="${pageContext.request.contextPath}/customers"><i class="fas fa-users"></i> Customers</a>
            <a href="${pageContext.request.contextPath}/profile"><i class="fas fa-user"></i> Profile</a>
        </nav>
    </div>
    <a href="${pageContext.request.contextPath}/logout" class="logout-btn">
        <i class="fas fa-sign-out-alt"></i> Logout
    </a>
</header>

<main class="dashboard-container">
    <h1>Overview</h1>
    <div class="summary-cards">
        <div class="summary-card">
            <i class="fas fa-shopping-cart"></i>
            <div class="title">Orders</div>
            <div class="value">${overview['totalOrder']}</div>
            <div class="desc">Number of Orders</div>
        </div>
        <div class="summary-card">
            <i class="fas fa-users"></i>
            <div class="title">Customers</div>
            <div class="value">${overview['totalUser']}</div>
            <div class="desc">Number of Customers</div>
        </div>
        <div class="summary-card">
            <i class="fas fa-check-circle"></i>
            <div class="title">Completed</div>
            <div class="value">${overview['totalCompleted']}</div>
            <div class="desc">Complete Orders</div>
        </div>
        <div class="summary-card">
            <i class="fas fa-dollar-sign"></i>
            <div class="title">Revenue</div>
            <div class="value">$${overview['totalProfit']}</div>
            <div class="desc">Total Revenue</div>
        </div>
    </div>

    <h2>Orders</h2>
    <table class="order-table">
        <thead>
            <tr>
                <th>Order</th>
                <th>Customer ID</th>
                <th>Date</th>
                <th>Amount</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${userOrderList}">
            <tr>
                <td><i class="fas fa-receipt"></i> #${order.orderId}</td>
                <td>${order.customerID}</td>
                <td>${order.pickUpDate}</td>
                <td>$${order.finalPrice}</td>
                <td>${order.progress}</td>
                <td>
                    <a href="#modal-${order.orderId}" class="text-blue-600 hover:text-blue-800" title="View Order">
                        <i class="fas fa-eye"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

<!-- JSTL-based modals -->
<c:forEach var="order" items="${userOrderList}">
<div id="modal-${order.orderId}" class="modal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.5);">
    <div style="background:#fff; padding:20px; max-width:400px; margin:10% auto; border-radius:5px; position:relative;">
        <h3>Order Details</h3>
        <div style="margin-bottom: 20px;">
            <p><strong>Order ID:</strong> #${order.orderId}</p>
            <p><strong>Customer ID:</strong> ${order.customerID}</p>
            <p><strong>Pickup Date:</strong> ${order.pickUpDate}</p>
            <p><strong>Dropoff Date:</strong> ${order.dropoffDate}</p>
            <p><strong>Weight:</strong> ${order.weight} kg</p>
            <p><strong>Final Price:</strong> $${order.finalPrice}</p>
            <p><strong>Status:</strong> ${order.progress}</p>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/completeOrder">
            <input type="hidden" name="orderId" value="${order.orderId}" />
            <div style="display:flex; justify-content: space-between;">
                <a href="#" onclick="document.getElementById('modal-${order.orderId}').style.display='none'" style="padding:8px 16px; background-color:#cc1f1a; color:white; border:none;">Close</a>
                <button type="submit" style="padding:8px 16px; background-color:#4CAF50; color:white; border:none;">Complete</button>
            </div>
        </form>
    </div>
</div>
</c:forEach>

<script>
    document.querySelectorAll('a[href^="#modal-"]').forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const modalId = this.getAttribute('href').substring(1);
            document.getElementById(modalId).style.display = 'block';
        });
    });
</script>
<c:if test="${not empty success}">
    <script>
        $(document).ready(function() {
            swal("Success!", "${success}", "success");
        });
    </script>
</c:if>

<c:if test="${not empty error}">
    <script>
        $(document).ready(function() {
            swal("Error!", "${error}", "error");
        });
    </script>
</c:if>
</body>
</html>
