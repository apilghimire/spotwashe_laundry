<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>

<div class="login-container">
    <form id="signup-form" action="${pageContext.request.contextPath}/login" method="post">
        <h2>Sign In</h2>

        <a href="${pageContext.request.contextPath}/" class="back-button">Back</a>

        <label for="email">Email</label>
        <input type="email" name="email" placeholder="Enter your email" required />

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required />

        <button type="submit" id="submit-btn" class="login-button">Login</button>

        <div class="row-button-group">
            <a href="${pageContext.request.contextPath}/register" class="link-button">Create Account</a>
        </div>

        <div id="loader" style="display:none;"><i class="fas fa-spinner fa-spin"></i> Processing...</div>
    </form>
</div>

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
