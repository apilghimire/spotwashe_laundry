<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

<!-- Top Nav -->
<header class="navbar">
    <div class="navbar-left">
        <div class="brand">Spotwashe</div>
        <nav class="nav-links">
            <a href="#"><i class="fas fa-th-large"></i> Dashboard</a>
            <a href="#"><i class="fas fa-list"></i> Orders</a>
            <a href="#"><i class="fas fa-user"></i> Profile</a>
        </nav>
    </div>
    <button class="logout-button">Logout</button>
</header>

<!-- Main Form -->
<main class="form-container">
    <div class="breadcrumb">Dashboard &gt; Orders</div>
    <h1>Place Order</h1>

    <div class="section">
        <h3>Order Details</h3>
			<label for="serviceType">Service Type</label>
			<select name="serviceType" id="serviceType" required>
			    <option value="" disabled selected>Select service</option>
			    <c:forEach var="s" items="${services}">
			        <option value="${s.getServiceId()}">
			            ${s.getServiceName()} - $${s.getServicePrice()}
			        </option>
			    </c:forEach>
			</select>



            <label for="weight">Weight (kg)</label>
			<input type="number" name="weight" id="weight" placeholder="Enter weight" step="0.1" max="20" min_="1"required />

            <label for="pickup">Pickup Date & Time</label>
            <input type="datetime-local" name="pickup" id="pickup" required />

            <label for="dropoff">Dropoff Date & Time</label>
            <input type="datetime-local" name="dropoff" id="dropoff" required />

            <button type="submit" class="submit-button">Place Order</button>
        </form>
    </div>
</main>
<script>
window.addEventListener("DOMContentLoaded", function () {
    const pickupInput = document.getElementById("pickup");
    const dropoffInput = document.getElementById("dropoff");

    // Set pickup min = now
    const now = new Date();
    pickupInput.min = now.toISOString().slice(0, 16); // yyyy-MM-ddTHH:mm

    // On pickup change, set dropoff min to +3 days
    pickupInput.addEventListener("change", function () {
        const pickupDate = new Date(this.value);
        if (!isNaN(pickupDate.getTime())) {
            const dropMin = new Date(pickupDate.getTime() + 3 * 24 * 60 * 60 * 1000);
            dropoffInput.min = dropMin.toISOString().slice(0, 16);
        }
    });

    // On submit, validate both rules again
    document.querySelector(".order-form").addEventListener("submit", function (e) {
        const pickupDate = new Date(pickupInput.value);
        const dropoffDate = new Date(dropoffInput.value);
        const now = new Date();
        const minGap = 3 * 24 * 60 * 60 * 1000; // 3 days

        if (pickupDate < now) {
            e.preventDefault();
            alert("Pickup date cannot be in the past.");
        } else if (dropoffDate - pickupDate < minGap) {
            e.preventDefault();
            alert("Dropoff must be at least 3 days after pickup.");
        }
    });
});
</script>


</body>
</html>
