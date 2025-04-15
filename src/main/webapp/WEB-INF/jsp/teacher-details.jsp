<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
    <title>Προβολή Καθηγητή</title>
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
<%@ include file="header.jsp"%>

<main class="container mx-auto px-4 py-8">
    <div class="max-w-4xl mx-auto bg-white shadow-lg rounded-lg p-6">
        <!-- Back Button -->
        <button onclick="history.back()" class="mb-4 inline-flex items-center px-4 py-2 bg-gray-200 hover:bg-gray-300 text-gray-800 font-medium rounded-md">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm-1.707-7.707a1 1 0 010-1.414L10.586 7H6a1 1 0 110-2h7a1 1 0 011 1v7a1 1 0 11-2 0V9.414l-2.293 2.293a1 1 0 01-1.414 0z" clip-rule="evenodd"/>
            </svg>
            Πίσω
        </button>

        <h1 class="text-2xl font-bold text-gray-800 mb-4">Πληροφορίες Καθηγητή</h1>

        <!-- Error Message -->
        <c:if test="${not empty error}">
            <div class="mb-6 p-4 bg-red-50 border-l-4 border-red-500 text-red-700 rounded">
                <p>${error}</p>
            </div>
        </c:if>

        <!-- Teacher Details -->
        <c:if test="${not empty teacher}">
            <dl class="divide-y divide-gray-200">
                <div class="py-4 flex justify-between">
                    <dt class="font-medium text-gray-500">ID:</dt>
                    <dd class="text-gray-900">${teacher.id}</dd>
                </div>
                <div class="py-4 flex justify-between">
                    <dt class="font-medium text-gray-500">Όνομα:</dt>
                    <dd class="text-gray-900">${teacher.firstname}</dd>
                </div>
                <div class="py-4 flex justify-between">
                    <dt class="font-medium text-gray-500">Επώνυμο:</dt>
                    <dd class="text-gray-900">${teacher.lastname}</dd>
                </div>
                <div class="py-4 flex justify-between">
                    <dt class="font-medium text-gray-500">Email:</dt>
                    <dd class="text-gray-900">${teacher.email}</dd>
                </div>
                <div class="py-4 flex justify-between">
                    <dt class="font-medium text-gray-500">Τηλέφωνο:</dt>
                    <dd class="text-gray-900">${teacher.phoneNum}</dd>
                </div>
                <div class="py-4 flex justify-between">
                    <dt class="font-medium text-gray-500">Διεύθυνση:</dt>
                    <dd class="text-gray-900">${teacher.street} ${teacher.streetNum}, ${teacher.zipCode}</dd>
                </div>
            </dl>
        </c:if>
    </div>
</main>

<%@ include file="footer.jsp"%>
</body>
</html>