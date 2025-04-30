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

<header>
    <div class="navbar">
        <div class="logo">
            <a href="${contextPath}">
                <img src="${contextPath}/resources/logo.png" alt="Logo" style="height: 40px; vertical-align: middle;">
            </a>
        </div>
        <nav>
            <ul>
                <li><a href="${contextPath}">Home</a></li>
                <li><a href="${contextPath}/pages/aboutus.jsp">About Us</a></li>
                <li><a href="${contextPath}/pages/profilio.jsp">Portfolio</a></li>
                <li><a href="${contextPath}/pages/contactus.jsp">Contact</a></li>
                <c:choose>
                    <c:when test="${not empty currentUser}">
                        <li><a href="${contextPath}/logout" class="login-button">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${contextPath}/login" class="login-button">Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</header>
