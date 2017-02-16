<%@ tag description="Pagination" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="url" required="true"  %>
<%@ attribute name="total" required="true" type="java.lang.Integer" %>
<%@ attribute name="pageParam" required="true" %>
<%@ attribute name="current" required="true" type="java.lang.Integer" %>

<ul class="pagination pagination-lg">

    <c:if test="${current > 3}">
        <c:url var="first" value="${url}">
            <c:param name="${pageParam}" value="1" />
        </c:url>
        <li> <a href="<c:out value="${first}"/>">1</a> </li>
    </c:if>

    <c:if test="${current > 4}">
        <li class="disabled"><span>...</span></li>
    </c:if>

    <c:if test="${current > 2}">
        <c:url var="prev" value="${url}">
            <c:param name="${pageParam}" value="${current - 2}"/>
        </c:url>
        <li> <a href="<c:out value="${prev}"/>"><c:out value="${current - 2}"/></a> </li>
    </c:if>

    <c:if test="${current > 1}">
        <c:url var="prev" value="${url}">
            <c:param name="${pageParam}" value="${current - 1}"/>
        </c:url>
        <li> <a href="<c:out value="${prev}"/>"><c:out value="${current - 1}"/></a> </li>
    </c:if>

    <c:url value="${url}" var="currentUrl">
        <c:param name="${pageParam}" value="${current}"/>
    </c:url>

    <li class="active"> <a href="<c:out value="${currentUrl}"/>"><c:out value="${current}"/></a> </li>

    <c:if test="${current < total}">
        <c:url var="next" value="${url}">
            <c:param name="${pageParam}" value="${current + 1}"/>
        </c:url>
        <li> <a href="<c:out value="${next}"/>"><c:out value="${current + 1}"/></a> </li>
    </c:if>

    <c:if test="${current + 1 < total}">
        <c:url var="next" value="${url}">
            <c:param name="${pageParam}" value="${current + 2}"/>
        </c:url>
        <li> <a href="<c:out value="${next}"/>"><c:out value="${current + 2}"/></a> </li>
    </c:if>

    <c:if test="${current + 3 < total}">
        <li class="disabled"><span>...</span></li>
    </c:if>

    <c:if test="${current + 2 < total}">
        <c:url var="last" value="${url}">
            <c:param name="${pageParam}" value="${total}" />
        </c:url>
        <li><a href="<c:out value="${last}"/>"><c:out value="${total}"/></a></li>
    </c:if>

</ul>
