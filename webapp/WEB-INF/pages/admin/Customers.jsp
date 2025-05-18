<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer List | Spotwashe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admindashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

<!-- Top Navigation -->
<header class="navbar">
    <div class="navbar-left">
        <div class="brand">Spotwashe</div>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/login"><i class="fas fa-th-large"></i> Dashboard</a>
            <a href="${pageContext.request.contextPath}/customers"><i class="fas fa-users"></i> Customers</a>
            <a href="${pageContext.request.contextPath}/profile"><i class="fas fa-user"></i> Profile</a>
        </nav>
    </div>
    <a href="${pageContext.request.contextPath}/logout" class="logout-btn">
        <i class="fas fa-sign-out-alt"></i> Logout
    </a>
</header>

<main class="dashboard-container">
    <h2>Customer List</h2>
    <table class="order-table">
        <thead>
        <tr>
            <th><i class="fas fa-user"></i> ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}" varStatus="status">
            <tr>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.number}</td>
                <td>${user.userAddress}</td>
                <td>
					<form method="get" action="${pageContext.request.contextPath}/updateUser" style="display:inline;">
					    <input type="hidden" name="userId" value="${user.userId}" />
					    <button type="submit" style="background:#6b47f3; color:white; border:none; padding:6px 12px; border-radius:5px; cursor:pointer;">
					        <i class="fas fa-eye"></i> Update
					    </button>
					</form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

</body>
</html>
