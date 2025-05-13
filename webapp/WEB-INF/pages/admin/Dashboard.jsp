<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard | Spotwashe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admindashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

<!-- Top Navigation -->
<header class="navbar">
    <div class="navbar-left">
        <div class="brand">spotwashe</div>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/login"><i class="fas fa-th-large"></i> Dashboard</a>
            <a href="${pageContext.request.contextPath}/order"><i class="fas fa-shopping-cart"></i> Orders</a>
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
            <div class="value">1,240</div>
            <div class="desc">Number of Orders</div>
        </div>
        <div class="summary-card">
            <i class="fas fa-users"></i>
            <div class="title">Customers</div>
            <div class="value">860</div>
            <div class="desc">Number of Customers</div>
        </div>
        <div class="summary-card">
            <i class="fas fa-check-circle"></i>
            <div class="title">Completed</div>
            <div class="value">1,120</div>
            <div class="desc">Complete Orders</div>
        </div>
        <div class="summary-card">
            <i class="fas fa-dollar-sign"></i>
            <div class="title">Revenue</div>
            <div class="value">$98,500</div>
            <div class="desc">Total Revenue</div>
        </div>
    </div>

    <h2>Orders</h2>
    <table class="order-table">
        <thead>
            <tr>
                <th>Order</th>
                <th>Customer</th>
                <th>Date</th>
                <th>Amount</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><i class="fas fa-receipt"></i> #10234</td>
                <td>Josh Smith</td>
                <td>2024-06-10</td>
                <td>$250.00</td>
                <td>Completed</td>
                <td><i class="fas fa-eye"></i></td>
            </tr>
            <tr>
                <td><i class="fas fa-receipt"></i> #10235</td>
				<td>Josh Smith</td>                <td>2024-06-10</td>
                <td>$180.00</td>
                <td>Pending</td>
                <td><i class="fas fa-eye"></i></td>
            </tr>
            <tr>
                <td><i class="fas fa-receipt"></i> #10236</td>
                <td>Josh Smith</td>
                <td>2024-06-09</td>
                <td>$320.00</td>
                <td>Completed</td>
                <td>
        			<a href="/orders/view?id=123" class="text-blue-600 hover:text-blue-800" title="View Order">
            			<i class="fas fa-eye"></i>
        			</a>
    			</td>
            </tr>
            <tr>
                <td><i class="fas fa-receipt"></i> #10237</td>
                <td>Josh Smith</td>
                <td>2024-06-09</td>
                <td>$150.00</td>
                <td>Cancelled</td>
                <td>
        			<a href="/orders/view?id=123" class="text-blue-600 hover:text-blue-800" title="View Order">
            			<i class="fas fa-eye"></i>
        			</a>
    			</td>
            </tr>
        </tbody>
    </table>
</main>

</body>
</html>
