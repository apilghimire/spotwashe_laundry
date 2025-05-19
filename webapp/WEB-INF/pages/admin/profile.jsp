<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page session="true" %> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile | Spotwashe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

<!-- Top Nav -->
<header class="navbar">
    <div class="navbar-left">
        <div class="brand">Spotwashe</div>
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

<!-- Profile Form -->
<main class="form-container">
    <div class="breadcrumb">Dashboard &gt; Profile</div>
    <h1>Credentials</h1>

    <form action="${pageContext.request.contextPath}/profile" method="post" class="profile-form">

        <div class="form-row">
            <div class="form-group">
                <label>Full Name</label>
                <div class="input-icon">
                    <input type="text" name="fullName" value="${currentuser.userName}" required />
                    <i class="fas fa-user"></i>
                </div>
            </div>

            <div class="form-group">
                <label>Phone Number</label>
                <div class="input-icon">
                    <input type="text" name="phone" value="${currentuser.number}" required />
                    <i class="fas fa-phone"></i>
                </div>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label>Date of Birth</label>
                <div class="input-icon">
                    <input type="date" name="dob" value="${currentuser.dateOfBirth}" required />
                    <i class="fas fa-calendar-alt"></i>
                </div>
            </div>

            <div class="form-group">
                <label>Address</label>
                <div class="input-icon">
                    <input type="text" name="address" value="${currentuser.userAddress}" required />
                    <i class="fas fa-home"></i>
                </div>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label>Current Password</label>
                <div class="input-icon">
                    <input type="password" name="currentPassword" placeholder="Enter current password" required />
                    <i class="fas fa-lock"></i>
                </div>
            </div>

            <div class="form-group">
                <label>New Password</label>
                <div class="input-icon">
                    <input type="password" name="newPassword" placeholder="Enter new password" />
                    <i class="fas fa-lock"></i>
                </div>
            </div>
        </div>

        <button type="submit" class="submit-button">Update Information</button>
    </form>
</main>

</body>
</html>
</html>