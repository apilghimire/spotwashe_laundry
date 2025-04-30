<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <!-- SweetAlert -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>

<div class="container">
    <form id="signup-form" action="${pageContext.request.contextPath}/register" method="post">	
        <h2>User Registration</h2>
        
        <input type="text" name="userName" placeholder="Username" required />
        <input type="number" name="number" placeholder="Phone Number" required />
        <input type="email" name="email" placeholder="Email" required />
        <input type="date" name="dateOfBirth" required />
        <textarea name="userAddress" placeholder="Address" required></textarea>

        <select name="role" required>
            <option value="" disabled selected>Select Role</option>
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select>

        <div class="password-wrapper">
            <input type="password" id="password" name="password" placeholder="Password" required />
        </div>

        <button type="submit" id="submit-btn">Register</button>
        <div id="loader" style="display:none;"><i class="fas fa-spinner fa-spin"></i> Processing...</div>
    </form>
</div>

<!-- Display Success or Error Alert from Servlet -->
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
