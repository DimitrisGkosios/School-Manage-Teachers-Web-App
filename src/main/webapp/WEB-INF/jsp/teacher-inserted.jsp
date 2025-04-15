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
    <title>Επιτυχής Εισαγωγή</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: {
                            600: '#2563eb',
                            700: '#1d4ed8',
                        },
                        success: {
                            500: '#10b981',
                            600: '#059669',
                        }
                    }
                }
            }
        }
    </script>
</head>
<body class="bg-gray-50 min-h-screen flex flex-col">
<%@ include file="header.jsp" %>

<main class="container mx-auto px-4 py-8">
    <div class="max-w-4xl mx-auto bg-white shadow-lg rounded-lg p-6 text-center">
        <!-- Success Message -->
        <h1 class="text-3xl font-bold text-success-600 mb-4">Επιτυχής Εισαγωγή</h1>
        <p class="text-lg text-gray-700 mb-2"><strong>Επώνυμο:</strong> ${sessionScope.teacherInfo.lastname}</p>
        <p class="text-lg text-gray-700 mb-2"><strong>Όνομα:</strong> ${sessionScope.teacherInfo.firstname}</p>

        <!-- Back Button -->
        <div class="mt-6">
            <a href="${pageContext.request.contextPath}/school-app/dashboard"
               class="inline-flex items-center px-6 py-3 border border-transparent text-lg font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-600 transition-all duration-200 transform hover:scale-105">
                Επιστροφή στον Πίνακα Ελέγχου
            </a>
        </div>
    </div>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>