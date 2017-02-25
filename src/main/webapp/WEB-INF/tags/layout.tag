<%@ tag description="Main Layout" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="title" required="true" %>

<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><c:out value="${title}" /></title>

    <link rel="stylesheet" href="<c:url value="/static/tp/bootstrap/css/bootstrap.min.css" />" type="text/css" media="all" >
    <link rel="stylesheet" href="<c:url value="/static/tp/font-awesome/css/font-awesome.min.css" />" type="text/css" media="all" >
    <link rel="stylesheet" href="<c:url value="/static/css/styles.css" />" type="text/css" media="all" >
</head>
<body>

<header>
    <jsp:invoke fragment="header" />
</header>

<div class="container-fluid" id="main-content">
    <jsp:doBody />
</div>

<footer>
    <jsp:invoke fragment="footer" />
</footer>

<script src="<c:url value="/static/tp/jquery.min.js" />" type="text/javascript" charset="UTF-8" ></script>
<script src="<c:url value="/static/tp/polyfill.js" />" type="text/javascript" charset="UTF-8" ></script>
<script src="<c:url value="/static/js/app.min.js" />" type="text/javascript" charset="UTF-8" ></script>
</body>
</html>