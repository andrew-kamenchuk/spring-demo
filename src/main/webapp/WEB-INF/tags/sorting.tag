<%@ tag description="Sorting" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="sortingsUrl" required="true" %>
<%@ attribute name="orderString" required="true" %>
<%@ attribute name="orders" required="true" type="org.j.products.search.products.SortOrder[]" %>
<%@ attribute name="sortParam" required="true" %>

<div>
    <span class="text-capitalize"><spring:message code="products.sort_by" />: </span>

    <div class="btn-group">
    <c:forEach items="${orders}" var="order">
        <c:url value="${sortingsUrl}" var="sortUrl">
            <c:param name="${sortParam}" value="${order.toString()}" />
        </c:url>
        <a href="<c:out value="${sortUrl}"/>"
           class="btn btn-default <c:if test="${orderString == order.toString()}">btn-primary</c:if>">
            <c:out value="${order.name}"/>
        </a>
    </c:forEach>
    </div>
</div>