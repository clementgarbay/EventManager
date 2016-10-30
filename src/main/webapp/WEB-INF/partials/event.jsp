<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="app" uri="application" %>

<c:set var="nbParticipants" scope="page" value="${fn:length(event.participants)}"/>
<c:set var="remainingTickets" scope="page" value="${event.maxTickets - nbParticipants}"/>
<c:set var="newline" value="<%= \"\n\" %>" />

<div class="col-sm-12">
    <div class="card">
        <div class="col-sm-9">
            <h1>${event.title}</h1>
            <p>${fn:replace(event.description, newline, "<br><br>")}</p>
        </div>
        <div class="col-sm-3 card-infos">
            <h3 style="margin-top: 0;">Date et heure</h3>
            <p><fmt:formatDate pattern="dd/MM/yyyy',' HH'h'mm" value="${event.date}" /></p>

            <h3>Lieu</h3>
            <p>${event.address.getName()}</p>
            <small>${event.address.getCity()}, ${event.address.getZipCode()} (${event.address.getCountry()})</small>

            <h3>Propriétaire</h3>
            <p>${event.owner.getName()}</p>

            <h3>Participants</h3>
            <c:choose>
                <c:when test="${nbParticipants > 0}">
                    <p>${fn:length(event.participants)} personne(s) inscrite(s)<br><small class="text-muted"><i>${remainingTickets} place(s) restante(s)</i></small></p>
                </c:when>
                <c:otherwise>
                    <p>Aucun inscrit</p>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${SECURITY_IS_LOGGED && !event.isOwner(SECURITY_LOGGED_USER) && !event.isParticipant(SECURITY_LOGGED_USER) && remainingTickets > 0}">
                    <form class="form" method="post" action="<app:getUrl pathId="EVENT_SUBSCRIBE" params="{eventId:${event.id}}"/>">
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
                    <form class="form" method="post" action="<app:getUrl pathId="EVENT_UNSUBSCRIBE" params="{eventId:${event.id}}"/>">
                        <button type="submit" class="btn btn-sm btn-danger" style="width: 100%; margin: 0;">
                            Se désinscrire
                        </button>
                    </form>
                </c:when>
                <c:when test="${!SECURITY_IS_LOGGED}">
                    <a href="<app:getUrl pathId="LOGIN"/>?url=/events/${event.id}" class="btn" style="width: 100%; margin-top: 40px;">
                        Se connecter
                    </a>
                    <p class="text-muted" style="line-height: 1; text-align: center;"><small>Veuillez vous connecter pour vous inscrire à l'événement.</small></p>
                </c:when>
            </c:choose>

        </div>
    </div>
</div>