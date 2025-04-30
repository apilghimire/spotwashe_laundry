<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

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

<main style="padding: 40px 20px; max-width: 900px; margin: auto;">
    <section>
        <h1>About Our Company</h1>
        <p style="max-width: 700px;">
            Spotwashe delivers fast, reliable, and eco-friendly laundry solutions for modern living.
            Our mission is to simplify your laundry experience with seamless technology and exceptional service.
        </p>
    </section>

    <section style="margin-top: 40px;">
        <h2>Contact Information</h2>
        <div class="cards">
            <div class="card">
                <h4>support@spotwashe.com</h4>
                <p>Email us anytime</p>
            </div>
            <div class="card">
                <h4>+1 555 123 4567</h4>
                <p>Mon–Fri, 9am–6pm</p>
            </div>
            <div class="card">
                <h4>123 Clean Ave, NY</h4>
                <p>Visit our office</p>
            </div>
        </div>
    </section>

    <section style="margin-top: 40px;">
        <h2>Contact Form</h2>
        <form action="#" method="post" style="margin-top: 20px;">
            <label for="name">Your Name</label><br>
            <input type="text" id="name" name="name" placeholder="Enter your name" required
                   style="width: 100%; padding: 10px; margin-bottom: 15px; background-color: #f8f8f8; border: none;"><br>

            <label for="email">Email Address</label><br>
            <input type="email" id="email" name="email" placeholder="Enter your email" required
                   style="width: 100%; padding: 10px; margin-bottom: 15px; background-color: #f8f8f8; border: none;"><br>

            <label for="message">Message</label><br>
            <textarea id="message" name="message" rows="6" placeholder="How can we help you?" required
                      style="width: 100%; padding: 10px; margin-bottom: 20px; background-color: #f8f8f8; border: none;"></textarea><br>

            <button type="submit" class="login-button">Send Message</button>
        </form>
    </section>
</main>

<jsp:include page="footer.jsp" />
