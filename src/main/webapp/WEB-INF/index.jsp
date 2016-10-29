<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" uri="application" %>

<!DOCTYPE html>

<html>
<head>
    <title>Event Manager</title>
    <meta charset="UTF-8">

    <!-- External CSS files -->
    <%--<link rel="stylesheet"  type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"/>--%>

    <!-- CSS files -->
    <link href="<app:getResource path="/css/main.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
    <c:if test="${not empty alertMessage}">
        <div class="alert alert-<c:out value='${alertType}'/>">
            <div class="container-fluid">
                <div class="alert-icon">
                    <i class="material-icons"><c:out value='${(alertType == "danger") ? "error_outline" : "check"}'/></i>
                </div>
                <button type="button" class="close" data-dismiss="alert" aria-label="Fermer">
                    <span aria-hidden="true"><i class="material-icons">clear</i></span>
                </button>
                <c:out value="${alertMessage}" />
            </div>
        </div>
    </c:if>

    <%@ include file="/WEB-INF/partials/nav.jsp" %>

    <div class="wrapper">
        <div class="container">
            <div class="row">
                <c:if test="${partialPage != null}">
                    <jsp:include page="/WEB-INF/partials/${partialPage}" flush="true" />
                </c:if>
            </div>
        </div>
    </div>

    <%-- JavaScript files --%>
    <script src="<app:getResource path="/js/jquery.min.js" />"           type="text/javascript"></script>
    <script src="<app:getResource path="/js/bootstrap.min.js" />"        type="text/javascript"></script>
    <script src="<app:getResource path="/js/material.min.js" />"         type="text/javascript"></script>
    <script src="<app:getResource path="/js/nouislider.min.js" />"       type="text/javascript"></script>
    <script src="<app:getResource path="/js/bootstrap-datepicker.js" />" type="text/javascript"></script>
    <script src="<app:getResource path="/js/material-kit.js" />"         type="text/javascript"></script>

    <script type="text/javascript">
    // TODO : place it in an external js file
    $(function() {
        $('#datepicker').datepicker({
            altField: "#datepicker",
            closeText: 'Fermer',
            prevText: 'Précédent',
            nextText: 'Suivant',
            currentText: 'Aujourd\'hui',
            monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
            monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
            dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
            dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
            dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
            weekHeader: 'Sem.',
            dateFormat: 'dd/mm/yyyy'
        });
    });
    </script>
</body>
</html>
