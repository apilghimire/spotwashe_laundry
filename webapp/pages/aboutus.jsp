<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>

<%
    HttpSession userSession = request.getSession(false);
    String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
    pageContext.setAttribute("currentUser", currentUser);
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="header.jsp" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css" />

<main>
    <section class="about-section" style="padding: 40px 20px; max-width: 1000px; margin: auto;">
        <h1>About spotwashe</h1>
        <div style="display: flex; flex-wrap: wrap; gap: 20px; align-items: center;">
            <img src="${contextPath}/resources/about.jpg" alt="Teamwork" style="max-width: 300px; border-radius: 12px;">
            <p style="flex: 1; min-width: 280px;">
                spotwashe is dedicated to delivering fast, reliable, and eco-friendly laundry solutions.
                Our mission is to simplify your laundry experience with modern technology and exceptional
                customer care. We believe in sustainability, convenience, and building trust with every customer.
            </p>
        </div>
    </section>

    <section class="branches" style="padding: 20px;">
        <h2 style="text-align: center;">Our Branches</h2>
        <div class="cards" style="display: flex; flex-wrap: wrap; justify-content: center; gap: 20px;">
            <div class="card">
                <h4>Main Branch</h4>
                <h3>Downtown Center</h3>
                <p>123 Main St, City Center. Open daily 8am–10pm.</p>
                <button>View Map</button>
            </div>
            <div class="card">
                <h4>North Branch</h4>
                <h3>Uptown Plaza</h3>
                <p>456 Uptown Ave, Northside. Open Mon–Sat 9am–9pm.</p>
                <button>View Map</button>
            </div>
            <div class="card">
                <h4>East Branch</h4>
                <h3>East Market</h3>
                <p>789 East Rd, East End. Open daily 7am–8pm.</p>
                <button>View Map</button>
            </div>
        </div>
    </section>
</main>

<jsp:include page="footer.jsp" />
