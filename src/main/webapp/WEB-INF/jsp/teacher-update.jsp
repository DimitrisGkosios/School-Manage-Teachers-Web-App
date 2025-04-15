<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
    <title>Ενημέρωση Καθηγητή</title>
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
    <div class="max-w-4xl mx-auto bg-white shadow-lg rounded-lg p-6">
        <!-- Back Button -->
        <button onclick="history.back()" class="mb-4 inline-flex items-center px-4 py-2 bg-gray-200 hover:bg-gray-300 text-gray-800 font-medium rounded-md">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm-1.707-7.707a1 1 0 010-1.414L10.586 7H6a1 1 0 110-2h7a1 1 0 011 1v7a1 1 0 11-2 0V9.414l-2.293 2.293a1 1 0 01-1.414 0z" clip-rule="evenodd"/>
            </svg>
            Πίσω
        </button>

        <h1 class="text-2xl font-bold text-gray-800 mb-6">Ενημέρωση Καθηγητή</h1>

        <!-- Error Messages -->
        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="mb-6 p-4 bg-red-50 border-l-4 border-red-500 text-red-700 rounded">
                <p>${sessionScope.errorMessage}</p>
            </div>
        </c:if>

        <!-- Form -->
        <form method="POST" action="${pageContext.request.contextPath}/school-app/teachers/update" class="space-y-6">
            <input type="hidden" name="id" value="${requestScope.updateDTO.id}">

            <!-- Name Row -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                    <label for="firstname" class="block text-sm font-medium text-gray-700 mb-1">Όνομα</label>
                    <input id="firstname" name="firstname" type="text" value="${requestScope.updateDTO.firstname}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.firstnameMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="Όνομα">
                    <c:if test="${not empty sessionScope.firstnameMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.firstnameMessage}</p>
                    </c:if>
                </div>
                <div>
                    <label for="lastname" class="block text-sm font-medium text-gray-700 mb-1">Επώνυμο</label>
                    <input id="lastname" name="lastname" type="text" value="${requestScope.updateDTO.lastname}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.lastnameMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="Επώνυμο">
                    <c:if test="${not empty sessionScope.lastnameMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.lastnameMessage}</p>
                    </c:if>
                </div>
            </div>

            <!-- VAT and Father Name Row -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                    <label for="vat" class="block text-sm font-medium text-gray-700 mb-1">ΑΦΜ</label>
                    <input id="vat" name="vat" type="text" value="${requestScope.updateDTO.vat}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.vatMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="ΑΦΜ">
                    <c:if test="${not empty sessionScope.vatMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.vatMessage}</p>
                    </c:if>
                </div>
                <div>
                    <label for="fathername" class="block text-sm font-medium text-gray-700 mb-1">Επώνυμο Πατρός</label>
                    <input id="fathername" name="fathername" type="text" value="${requestScope.updateDTO.fatherName}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.fathernameMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="Επώνυμο Πατρός">
                    <c:if test="${not empty sessionScope.fathernameMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.fathernameMessage}</p>
                    </c:if>
                </div>
            </div>

            <!-- Contact Information -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                    <label for="phoneNum" class="block text-sm font-medium text-gray-700 mb-1">Τηλέφωνο</label>
                    <input id="phoneNum" name="phoneNum" type="text" value="${requestScope.updateDTO.phoneNum}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.phoneNumMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="Τηλέφωνο">
                    <c:if test="${not empty sessionScope.phoneNumMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.phoneNumMessage}</p>
                    </c:if>
                </div>
                <div>
                    <label for="email" class="block text-sm font-medium text-gray-700 mb-1">Email</label>
                    <input id="email" name="email" type="email" value="${requestScope.updateDTO.email}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.emailMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="Email">
                    <c:if test="${not empty sessionScope.emailMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.emailMessage}</p>
                    </c:if>
                </div>
            </div>

            <!-- Address Information -->
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                <div>
                    <label for="street" class="block text-sm font-medium text-gray-700 mb-1">Οδός</label>
                    <input id="street" name="street" type="text" value="${requestScope.updateDTO.street}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.streetMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="Οδός">
                    <c:if test="${not empty sessionScope.streetMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.streetMessage}</p>
                    </c:if>
                </div>
                <div>
                    <label for="streetNum" class="block text-sm font-medium text-gray-700 mb-1">Αριθμός</label>
                    <input id="streetNum" name="streetNum" type="text" value="${requestScope.updateDTO.streetNum}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.streetNumMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="Αριθμός">
                    <c:if test="${not empty sessionScope.streetNumMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.streetNumMessage}</p>
                    </c:if>
                </div>
                <div>
                    <label for="zipCode" class="block text-sm font-medium text-gray-700 mb-1">Τ.Κ.</label>
                    <input id="zipCode" name="zipCode" type="text" value="${requestScope.updateDTO.zipCode}"
                           class="w-full px-3 py-2 border ${not empty sessionScope.zipcodeMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600"
                           placeholder="Τ.Κ.">
                    <c:if test="${not empty sessionScope.zipcodeMessage}">
                        <p class="mt-1 text-sm text-red-600">${sessionScope.zipcodeMessage}</p>
                    </c:if>
                </div>
            </div>

            <!-- City Dropdown -->
            <div>
                <label for="cityId" class="block text-sm font-medium text-gray-700 mb-1">Πόλη</label>
                <select id="cityId" name="cityId"
                        class="w-full px-3 py-2 border ${not empty sessionScope.cityIdMessage ? 'border-red-300' : 'border-gray-300'} rounded-md shadow-sm focus:outline-none focus:ring-primary-600 focus:border-primary-600">
                    <c:forEach var="city" items="${requestScope.cities}">
                        <option value="${city.id}" ${city.id == requestScope.updateDTO.cityId ? 'selected' : ''}>${city.name}</option>
                    </c:forEach>
                </select>
                <c:if test="${not empty sessionScope.cityIdMessage}">
                    <p class="mt-1 text-sm text-red-600">${sessionScope.cityIdMessage}</p>
                </c:if>
            </div>

            <!-- Submit Button -->
            <div class="text-right">
                <button type="submit" class="px-6 py-2 bg-primary-600 text-white font-medium rounded-md hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-600">
                    Ενημέρωση
                </button>
            </div>
        </form>
    </div>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>