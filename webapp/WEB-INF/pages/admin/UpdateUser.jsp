<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Customer | Spotwashe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UpdateUser.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>

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
    <h2>Customer Info</h2>
    <form action="${pageContext.request.contextPath}/updateUser" method="post" style="max-width: 500px;">
        <input type="hidden" name="userId" value="${userToUpdate.userId}" />

        <label>Full Name</label>
        <div class="input-icon">
            <input type="text" name="userName" placeholder="Enter full name" value="${userToUpdate.userName}" required />
            <i class="fas fa-user"></i>
        </div>

        <label>Phone Number</label>
        <div class="input-icon">
            <input type="text" name="number" placeholder="Enter phone number" value="${userToUpdate.number}" required />
            <i class="fas fa-phone"></i>
        </div>
        
        <label>Address</label>
        <div class="input-icon">
            <input type="text" name="address" placeholder="Enter Address" value="${userToUpdate.userAddress}" required />
            <i class="fas fa-phone"></i>
        </div>

        <label>Email Address</label>
        <div class="input-icon">
            <input type="email" name="email" placeholder="Enter email address" value="${userToUpdate.email}" required />
            <i class="fas fa-envelope"></i>
        </div>

        <label>Date of Birth</label>
        <div class="input-icon">
            <input type="date" name="dateOfBirth" value="${userToUpdate.dateOfBirth}" required />
            <i class="fas fa-calendar-alt"></i>
        </div>

        <label>New Password</label>
        <div class="input-icon">
            <input type="password" name="password" placeholder="Enter new password" />
            <i class="fas fa-lock"></i>
        </div>

        <div style="margin-top: 20px;">
            <button type="submit" class="logout-btn" style="background-color: #6b47f3;">Update Information</button>
<button type="button" onclick="deleteUser(${userToUpdate.userId})" class="logout-btn" style="background-color: #f44336;">Delete User</button>
<a href="${pageContext.request.contextPath}/customers" class="logout-btn" style="background-color: #eee; color: #000;">Back</a>
        </div>
    </form>
</main>
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
<script>
    function deleteUser(userId) {
        if (confirm("Are you sure you want to delete this user?")) {
            fetch("${pageContext.request.contextPath}/updateUser?userId=" + userId, {
                method: "get"
            })
            .then(response => response.text())
            .then(message => {
                // Pass userId to the /deleteUser servlet
                window.location.href = "${pageContext.request.contextPath}/deleteUser?userId=" + userId;
            });
        }
    }
</script>


</body>
</html>
