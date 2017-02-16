<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:layout title="Products">

    <jsp:attribute name="header" >
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="<c:out value="${urlPath}"/>" class="navbar-brand">Watches</a>
                </div>

                <div class="collapse navbar-collapse">
                    <form action="<c:out value="${urlPath}"/>" class="navbar-form navbar-left" autocomplete="off">
                        <div class="form-group">
                            <input type="text" class="form-control" name="search"
                                    value="<c:out value="${search}" />" placeholder="Search..." />
                        </div>
                        <button class="btn btn-default" type="submit"> <i class="fa fa-search"></i> </button>
                    </form>

                    <p class="navbar-text"><strong><u> ${products.totalElements} </u></strong> <sup>(found)</sup> </p>
                </div>
            </div>
        </nav>
    </jsp:attribute>

    <jsp:body>

        <div class="container">

            <%-- PAGINATION --%>
            <c:url value="${urlPath}" var="paginationBase">
                <c:param name="search" value="${search}"/>
                <c:param name="s" value="${orderString}"/>
            </c:url>
            <c:if test="${products.totalPages > 1 && page <= products.totalPages}">
                <div class="text-center">
                    <t:pagination url="${paginationBase}" total="${products.totalPages}" pageParam="p" current="${page}" />
                </div>
                <%-- SORTING --%>
                <c:url value="${urlPath}" var="sortingsBase">
                    <c:param name="search" value="${search}"/>
                    <c:param name="p" value="${page}"/>
                </c:url>
                <div class="text-center">
                    <t:sorting sortingsUrl="${sortingsBase}" orderString="${orderString}" orders="${orders}" sortParam="s"/>
                </div>
                <%-- SORTING --%>
            </c:if>
            <%-- PAGINATION --%>

            <%-- ITEMS --%>
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
            <%-- ITEMS --%>

            <%-- PAGINATION --%>
            <c:if test="${products.totalPages > 1 && page <= products.totalPages}">
                <div class="text-center">
                    <t:pagination url="${paginationBase}" total="${products.totalPages}" pageParam="p" current="${page}" />
                </div>
            </c:if>
            <%-- PAGINATION --%>

        </div>
    </jsp:body>

</t:layout>
