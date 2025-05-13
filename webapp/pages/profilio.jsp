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

<main style="padding: 40px 20px; max-width: 1100px; margin: auto;">
    <section style="display: flex; flex-wrap: wrap; gap: 30px; align-items: center;">
        <img src="${contextPath}/resources/developer.jpg" alt="Developer Photo"
             style="width: 280px; border-radius: 12px;" />
        <div style="flex: 1; min-width: 300px;">
            <h1>Developer Profile</h1>
            <p>
                Hi, I’m Alex Kim, a full-stack developer passionate about building seamless SaaS experiences.
                I designed and developed spotwashe to help users manage laundry services with speed, reliability,
                and a focus on sustainability. My expertise includes React, TypeScript, Node.js,
                and modern UI/UX best practices.
            </p>
        </div>
    </section>

    <section style="margin-top: 60px;">
        <h2>Skills and Tools</h2>
        <div class="cards">
            <div class="card">
                <h4>Frontend</h4>
                <p>React, TypeScript, Material UI,<br>Ant Design, Tailwind CSS</p>
            </div>
            <div class="card">
                <h4>Backend</h4>
                <p>Node.js, Express, PostgreSQL,<br>REST APIs</p>
            </div>
            <div class="card">
                <h4>UI/UX</h4>
                <p>Figma, Prototyping,<br>Accessibility, Responsive Design</p>
            </div>
        </div>
    </section>

    <section style="margin-top: 60px;">
        <h2>Featured Projects</h2>
        <div class="cards">
            <div class="card">
                <h4>spotwashe</h4>
                <p>Laundry Management SaaS for fast, eco-friendly service booking and tracking.</p>
                <a href="#" class="login-button" style="margin-right: 10px;">View Live</a>
                <a href="#" class="login-button">GitHub</a>
            </div>
            <div class="card">
                <h4>TaskFlow</h4>
                <p>A productivity dashboard for teams, featuring real-time collaboration.</p>
                <a href="#" class="login-button" style="margin-right: 10px;">View Live</a>
                <a href="#" class="login-button">GitHub</a>
            </div>
        </div>
    </section>
</main>

<jsp:include page="footer.jsp" />
