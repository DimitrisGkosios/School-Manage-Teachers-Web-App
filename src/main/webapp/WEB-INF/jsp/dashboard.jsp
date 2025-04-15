<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>

<%@ include file="header.jsp"%>


        <div class = "content-container">
        <div class= "message-container message-text">
            <a href="<c:url value='${pageContext.request.contextPath}/school-app/teachers/view' />" class="view-teachers-link ">Προβολή Καθηγητών</a>
        </div>
        </div>

<%@include file="footer.jsp"%>


</body>
</html>