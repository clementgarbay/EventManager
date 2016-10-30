<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="app" uri="application" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-12">
    <div class="card">
        <div class="header" data-background-color="purple" style="padding: 0; background: #9c27b0;">
            <div class="nav-tabs-navigation">
                <div class="nav-tabs-wrapper">
                    <ul class="nav nav-tabs" data-tabs="tabs" style="padding: 0;">
                        <li class="active" style="margin-bottom: 0;">
                            <a href="#events_owner" data-toggle="tab" aria-expanded="false">
                                <i class="fa fa-calendar-o" style="margin-right: 5px;"></i> Événements que j'ai créé
                            </a>
                        </li>
                        <li style="margin-bottom: 0;">
                            <a href="#events_participant" data-toggle="tab" aria-expanded="false">
                                <i class="fa fa-calendar-times-o" style="margin-right: 5px;"></i> Événements auxquels je suis inscrit
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="content">
            <div class="tab-content">
                <div class="tab-pane active" id="events_owner">
                    <c:choose>
                        <c:when test="${not empty eventsCreated}">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Titre</th>
                                    <th>Lieu</th>
                                    <th>Date</th>
                                    <th style="width: 20%;"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="event" items="${eventsCreated}">
                                    <tr>
                                        <a href="#">
                                            <td>${event.title}</td>
                                            <td>${event.address.city}</td>
                                            <td>${event.date}</td>
                                        </a>

                                        <td class="td-actions">
                                            <form method="GET" action="<app:getUrl pathId="EVENT_EDIT" params="{eventId:${event.id}}"/>">
                                                <button type="submit" rel="tooltip" title="Modifier" class="btn btn-simple btn-xs">
                                                    <i class="fa fa-edit"></i>
                                                </button>
                                            </form>
                                            <form method="POST" action="<app:getUrl pathId="EVENT_REMOVE" params="{eventId:${event.id}}"/>">
                                                <button type="submit" rel="tooltip" title="Supprimer" class="btn btn-danger btn-simple btn-xs">
                                                    <i class="fa fa-times"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:when test="${empty eventsCreated}">
                            <h4>Vous n'avez créé aucun évènement.</h4>
                        </c:when>
                    </c:choose>
                </div>
                <div class="tab-pane" id="events_participant">
                    <c:choose>
                        <c:when test="${not empty eventsSuscribed}">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Titre</th>
                                    <th>Lieu</th>
                                    <th>Date</th>
                                    <th style="width: 20%;"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="event" items="${eventsSuscribed}">
                                    <tr>
                                        <a href="#">
                                            <td>${event.title}</td>
                                            <td>${event.address.city}</td>
                                            <td>${event.date}</td>
                                        </a>
                                        <td class="td-actions">
                                            <form method="POST" action="<app:getUrl pathId="EVENT_UNSUBSCRIBE" params="{eventId:${event.id}}" />">
                                                <button type="submit" rel="tooltip" title="Se désinscrire" class="btn btn-danger btn-simple btn-xs">
                                                    <i class="fa fa-times"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:when test="${empty eventsSuscribed}">
                            <h4>Vous ne participez à aucun évènement.</h4>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>