<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Event Manager</title>
    <meta charset="UTF-8">

    <!-- External CSS files -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"/>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/>

    <!-- CSS files -->
    <link href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%= request.getContextPath() %>/assets/css/material-kit.css" rel="stylesheet"/>
    <link href="<%= request.getContextPath() %>/assets/css/material-dashboard.css" rel="stylesheet"/>
    <link href="<%= request.getContextPath() %>/assets/css/style.css" rel="stylesheet"/>
</head>
<body>
    <%@ include file="/WEB-INF/partials/nav.jsp" %>

    <div class="wrapper">
        <div class="container">
            <div class="row">
                <c:if test="${requestScope.jspPage != null}">
                    <jsp:include page="/WEB-INF/partials/${requestScope.jspPage}" flush="true" />
                </c:if>
            </div>
        </div>
    </div>

    <%-- Javascript files --%>
    <script src="<%= request.getContextPath() %>/assets/js/jquery.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/assets/js/material.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/assets/js/nouislider.min.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap-datepicker.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/assets/js/material-kit.js" type="text/javascript"></script>
</body>
</html>
