<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

<!-- Top Navigation -->
<header class="navbar">
    <div class="navbar-left">
        <div class="brand">Spotwashe</div>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/dashboard"><i class="fas fa-th-large"></i> Dashboard</a>
            <a href="${pageContext.request.contextPath}/order"><i class="fas fa-file-alt"></i> Orders</a>
            <a href="${pageContext.request.contextPath}/profile"><i class="fas fa-user"></i> Profile</a>
        </nav>
    </div>
    <a href="${pageContext.request.contextPath}/logout" class="logout-btn">
    <i class="fas fa-sign-out-alt"></i> Logout
</a>
</header>

<!-- Main Content -->
<main class="dashboard-container">
    <h1>Account Overview</h1>

		<div class="info-cards">
		    <div class="info-card">
		        <i class="fas fa-user-circle card-icon"></i>
		        <div class="card-title">Name</div>
		        <div class="card-main">${userInfo.name}</div>
		    </div>
		    <div class="info-card">
		        <i class="fas fa-phone card-icon"></i>
		        <div class="card-title">Phone</div>
		        <div class="card-main">${userInfo.phone}</div>
		    </div>
		    <div class="info-card">
		        <i class="fas fa-map-marker-alt card-icon"></i>
		        <div class="card-title">Address</div>
		        <div class="card-main">${userInfo.address}</div>
		    </div>
		</div>

    <h2>My Orders</h2>
    <table class="order-table">
        <thead>
            <tr>
                <th>Order</th>
                <th>Status</th>
                <th>Date</th>
                <th>Amount</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
		    <c:forEach var="order" items="${userOrderList}">
		        <tr>
		            <td><i class="fas fa-truck"></i> Order #${order.orderId}</td>
		            <td>inProgress</td>
		            <td>${order.pickUpDate}</td>
		            <td>$${order.finalPrice}</td>
		            <td><i class="fas fa-box-open"></i></td>
		        </tr>
		    </c:forEach>
		</tbody>

    </table>
</main>

</body>
</html>
