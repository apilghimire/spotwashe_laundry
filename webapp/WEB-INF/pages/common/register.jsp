<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>

<div class="form-container">
    <form id="signup-form" action="${pageContext.request.contextPath}/register" method="post">
        <h2>Sign Up</h2>
        <p class="subheading">Create your spotwashe account to get started.</p>

        <label for="userName">Full Name</label>
        <input type="text" name="userName" placeholder="Enter your name" required />

        <label for="number">Phone Number</label>
        <input type="number" name="number" placeholder="Enter your phone" required />

        <label for="email">Email</label>
        <input type="email" name="email" placeholder="Enter your email" required />

        <label for="dateOfBirth">Date of Birth</label>
        <input type="date" name="dateOfBirth" placeholder="MM/DD/YYYY" required />

        <label for="userAddress">Address</label>
        <input type="text" name="userAddress" placeholder="Enter your address" required />

        <input type="hidden" name="role" value="user" />

        <label for="password">Password</label>
        <input type="password" name="password" placeholder="Create a password" required />

        <div class="button-group">
            <a href="${pageContext.request.contextPath}/login" class="back-button">Back</a>
            <button type="submit" id="submit-btn" class="submit-button">Create Account</button>
        </div>

        <div id="loader" style="display:none;"><i class="fas fa-spinner fa-spin"></i> Processing...</div>
    </form>
</div>

<!-- Alert handlers -->
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
