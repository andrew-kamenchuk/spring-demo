<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message var="title" code="products.title" />

<t:layout title="${title}">

    <jsp:attribute name="header" >
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="<c:out value="${urlPath}"/>" class="navbar-brand text-capitalize"> <spring:message code="products.watches"/> </a>
                </div>

                <div class="collapse navbar-collapse">
                    <form action="<c:out value="${urlPath}"/>" class="navbar-form navbar-left" autocomplete="off">
                        <div class="form-group">
                            <input type="text" class="form-control" name="search"
                                    value="<c:out value="${search}" />" placeholder="<spring:message code="products.search" />..." />
                        </div>
                        <button class="btn btn-default" type="submit"> <i class="fa fa-search"></i> </button>
                    </form>

                    <p class="navbar-text"><strong><u> ${products.totalElements} </u></strong> <sup>(found)</sup> </p>
                </div>
            </div>
        </nav>
    </jsp:attribute>

    <jsp:body>

            <%-- PAGINATION --%>
            <c:url value="${urlPath}" var="paginationBase">
                <c:param name="search" value="${search}"/>
                <c:param name="s" value="${orderString}"/>
                <c:forEach items="${filters}" var="filter">
                    <c:param name="f" value="${filter}"/>
                </c:forEach>
            </c:url>
            <c:if test="${products.totalPages > 1 && page <= products.totalPages}">
                <div class="text-center">
                    <t:pagination url="${paginationBase}" total="${products.totalPages}" pageParam="p" current="${page}" />
                </div>
                <%-- SORTING --%>
                <c:url value="${urlPath}" var="sortingsBase">
                    <c:param name="search" value="${search}"/>
                    <c:param name="p" value="${page}"/>
                    <c:forEach items="${filters}" var="filter">
                        <c:param name="f" value="${filter}"/>
                    </c:forEach>
                </c:url>
                <div class="text-center" id="sortings">
                    <t:sorting sortingsUrl="${sortingsBase}" orderString="${orderString}" orders="${orders}" sortParam="s"/>
                </div>
                <%-- SORTING --%>
            </c:if>
            <%-- PAGINATION --%>

            <div class="container-fluid">
            <%-- ITEMS --%>
            <div class="col-md-9 col-xs-12">
            <ul class="list-group">
                <c:forEach items="${products.content}" var="p">
                    <li class="list-group-item">
                        <div>
                            <h4 class="row text-center">
                                <c:out value="${p.name}" /> <hr />
                                <span><i class="fa fa-gbp"></i> <c:out value="${p.price}" /> </span>
                            </h4>
                        </div>
                        <div>
                            <ul>
                                <c:forEach items="${p.values}" var="v">
                                    <li><c:out value="${v.property.name}" />: <c:out value="${v.name}" /> </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>

            </ul>
            </div>
            <%-- ITEMS --%>

            <%-- FILTERS --%>
            <div class="col-md-3 col-xs-12">
            <form method="get" action="<c:out value="${urlPath}"/>" name="filters">
            <input type="hidden" name="s" value="<c:out value="${orderString}"/>" />
            <input type="hidden" name="search" value="<c:out value="${search}"/>" />
            <c:forEach items="${facets}" var="facet">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title text-center">
                            <a href="javascript:void(0);" data-toggle="collapse" data-target="#property<c:out value="${facet.property.id}"/>"
                                    <c:if test="${facet.selected}"> aria-expanded="true" </c:if>>
                                        <c:out value="${facet.property.name}" />
                            </a>
                        </h4>
                    </div>

                    <div id="property<c:out value="${facet.property.id}"/>" class="panel-collapse collapse <c:if test="${facet.selected}">in</c:if>">
                        <ul class="panel-body list-group">
                        <c:forEach items="${facet.propertyValueFacetResults}" var="filter">
                            <li class="checkbox list-group-item <c:if test="${filter.selected}">list-group-item-success</c:if>">
                                <label>
                                    <input type="checkbox" name="f" <c:if test="${filter.selected}"> checked </c:if>
                                        <c:if test="${filter.count == 0}"> disabled </c:if>
                                        value="<c:out value="${filter.propertyValue.id}" />" />

                                    <c:choose>
                                        <c:when test="${filter.count > 0}">
                                            <span><c:out value="${filter.propertyValue.name}"/></span>
                                            <c:if test="${not filter.selected}">
                                                <span class="badge"><c:if test="${facet.selected}"> + </c:if> <c:out value="${filter.count}" /></span>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                        <span class="text-muted"><s><c:out value="${filter.propertyValue.name}"/></s></span>
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:forEach>
            </form>
            </div>
            <%-- FILTERS --%>

            </div>

            <%-- PAGINATION --%>
            <c:if test="${products.totalPages > 1 && page <= products.totalPages}">
                <div class="text-center">
                    <t:pagination url="${paginationBase}" total="${products.totalPages}" pageParam="p" current="${page}" />
                </div>
            </c:if>
            <%-- PAGINATION --%>

    </jsp:body>

</t:layout>
