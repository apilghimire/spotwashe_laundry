<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css" />
        <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <!-- SweetAlert -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <jsp:include page="header.jsp" />
    

<main>
${contextPath}
    <section class="welcome-section">
        <h1>Welcome to spotwashe</h1>
        <p>Your trusted partner for fast, reliable laundry services.</p>
    </section>

    <section class="services">
        <h2>Our Services</h2>
        <div class="cards">
            <div class="card">
                <h3>Wash and Fold</h3>
                <p>Convenient wash and fold service for your daily laundry needs.</p>
                <button>Book Now</button>
            </div>
            <div class="card">
                <h3>Ironing</h3>
                <p>Professional ironing for crisp, wrinkle-free clothes.</p>
                <button>Book Now</button>
            </div>
            <div class="card">
                <h3>Dry Cleaning</h3>
                <p>Expert dry cleaning for delicate and special garments.</p>
                <button>Book Now</button>
            </div>
            <div class="card">
                <h3>Pickup and Delivery</h3>
                <p>Doorstep pickup and delivery for ultimate convenience.</p>
                <button>Book Now</button>
            </div>
        </div>
    </section>

    <section class="why-choose-us">
        <h2>Why Choose Us?</h2>
        <div class="cards">
            <div class="card">
                <h3>Quality Care</h3>
                <p>We treat every garment with the utmost care and attention.</p>
            </div>
            <div class="card">
                <h3>On-Time</h3>
                <p>Punctual service with flexible scheduling options.</p>
            </div>
            <div class="card">
                <h3>Support</h3>
                <p>Friendly customer support ready to assist you.</p>
            </div>
        </div>
    </section>
</main>
    

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


    <jsp:include page="footer.jsp" />
</body>
</html>