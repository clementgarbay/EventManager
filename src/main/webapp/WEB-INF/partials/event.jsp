<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="app" uri="application" %>

<c:set var="nbParticipants" scope="page" value="${fn:length(event.participants)}"/>
<c:set var="remainingTickets" scope="page" value="${event.maxTickets - nbParticipants}"/>

<div class="col-sm-12">
    <div class="card">
        <div class="col-sm-9">
            <h1>${event.title}</h1>
            <p>${event.description}</p>
        </div>
        <div class="col-sm-3 card-infos">
            <h3 style="margin-top: 0;">Date et heure</h3>
            <p><fmt:formatDate pattern="dd/MM/yyyy',' HH'h'mm" value="${event.date}" /></p>

            <h3>Lieu</h3>
            <p>${event.address.getName()}<br><small>${event.address.getCity()}, ${event.address.getZipCode()} (${event.address.getCountry()})</small></p>

            <h3>Propriétaire</h3>
            <p>${event.owner.getName()}</p>

            <h3>Participants</h3>
            <c:choose>
                <c:when test="${nbParticipants > 0}">
                    <p>${fn:length(event.participants)} personne(s) inscrite(s)<br><small>${remainingTickets} place(s) restante(s)</small></p>
                </c:when>
                <c:otherwise>
                    <p>Aucun inscrit</p>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${SECURITY_IS_LOGGED && !event.isOwner(SECURITY_LOGGED_USER) && !event.isParticipant(SECURITY_LOGGED_USER) && remainingTickets > 0}">
                    <form class="form" method="post" action="<app:getUrl pathId="EVENTS"/>${event.id}/subscribe">
                        <button type="submit" class="btn btn-primary" style="width: 100%; margin-top: 40px;">
                            <i class="fa fa-plus" style="margin-right: 5px;"></i> S'inscrire
                        </button>
                    </form>
                </c:when>
                <c:when test="${SECURITY_IS_LOGGED && !event.isOwner(SECURITY_LOGGED_USER) && !event.isParticipant(SECURITY_LOGGED_USER) && remainingTickets == 0}">
                    <button type="submit" disabled class="btn btn-primary" style="width: 100%; margin-top: 40px;">
                        <i class="fa fa-plus" style="margin-right: 5px;"></i> S'inscrire
                    </button>
                    <p class="text-muted" style="line-height: 1; text-align: center;"><small>Il n'y a plus de ticket disponible.</small></p>
                </c:when>
                <c:when test="${SECURITY_IS_LOGGED && !event.isOwner(SECURITY_LOGGED_USER) && event.isParticipant(SECURITY_LOGGED_USER)}">
                    <p class="text-center text-success" style="width: 100%; margin-top: 40px;">
                        <i class="fa fa-check" style="margin-right: 5px;"></i> Inscrit
                    </p>
                    <form class="form" method="post" action="<app:getUrl pathId="EVENTS"/>${event.id}/unsubscribe">
                        <button type="submit" class="btn btn-sm btn-danger" style="width: 100%; margin: 0;">
                            Se désinscrire
                        </button>
                    </form>
                </c:when>
                <c:when test="${!SECURITY_IS_LOGGED}">
                    <a href="<app:getUrl pathId="LOGIN"/>" class="btn" style="width: 100%; margin-top: 40px;">
                        Se connecter
                    </a>
                    <p class="text-muted" style="line-height: 1; text-align: center;"><small>Veuillez vous connecter pour vous inscrire à l'événement.</small></p>
                </c:when>
            </c:choose>

        </div>
    </div>
</div>

<%--<div class="modal fade" id="subscribeModal" style="display: none">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">--%>
                    <%--<i class="material-icons">clear</i>--%>
                <%--</button>--%>
                <%--<h4 class="modal-title">S'inscrire</h4>--%>
            <%--</div>--%>
            <%--<form class="form" method="post" action="">--%>
                <%--<div class="modal-body">--%>
                    <%--S'inscrire à l'événement <b>${event.title}</b> du <b><fmt:formatDate pattern="dd/MM/yyyy 'à' H'h'm" value="${event.date}" /></b>.--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<div class="form-group is-empty">--%>
                                <%--<input type="text" name="name" class="form-control" placeholder="Nom">--%>
                            <%--</div>--%>
                            <%--<div class="form-group is-empty">--%>
                                <%--<input type="email" name="email" class="form-control" placeholder="Email">--%>
                            <%--</div>--%>
                            <%--<div class="form-group is-empty">--%>
                                <%--<input type="email" name="confirm_email" class="form-control" placeholder="Confirmer l'email">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="modal-footer">--%>
                    <%--<button type="submit" class="btn btn-default btn-simple">S'inscrire</button>--%>
                    <%--<button type="button" class="btn btn-danger btn-simple" data-dismiss="modal">Fermer</button>--%>
                <%--</div>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>