<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message var="title" code="error.title" />

<t:layout title="${title}">
    <jsp:body>
        <div class="text-center">
        <h1><spring:message code="error.heading" /> </h1>
        <h4 class="text-capitalize"><c:out value="${errorMsg}"/></h4>
        </div>
    </jsp:body>
</t:layout>
