<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-12">
    <div class="card">
        <div class="col-sm-9">
            <h1>${event.title}</h1>
            <p>${event.description}</p>
        </div>
        <div class="col-sm-3 card-infos">
            <h3 style="margin-top: 0;">Date et heure</h3>
            <p><fmt:formatDate pattern="dd/MM/yyyy',' h'h'" value="${event.date}" /></p>

            <h3>Lieu</h3>
            <p>${event.address.getName()}<br><small>${event.address.getCity()}, ${event.address.getZipCode()} (${event.address.getCountry()})</small></p>

            <h3>Participants</h3>
            <c:choose>
                <c:when test="${fn:length(event.participants) > 0}">
                    <p>${fn:length(event.participants)} personnes inscrites <small>(6 places restantes)</small></p>
                </c:when>
                <c:otherwise>
                    <p>Aucun inscrit</p>
                </c:otherwise>
            </c:choose>

            <button class="btn btn-primary" style="width: 100%; margin-top: 50px;" data-toggle="modal" data-target="#subscribeModal">
                <i class="fa fa-plus" style="margin-right: 5px;"></i> S'inscrire
            </button>
        </div>
    </div>
</div>

<div class="modal fade" id="subscribeModal" style="display: none">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
                <h4 class="modal-title">S'inscrire</h4>
            </div>
            <form class="form" method="post" action="">
                <div class="modal-body">
                    S'inscrire à l'événement <b>${event.title}</b> du <b><fmt:formatDate pattern="dd/MM/yyyy 'à' h'h'" value="${event.date}" /></b>.
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group is-empty">
                                <input type="text" name="name" class="form-control" placeholder="Nom">
                            </div>
                            <div class="form-group is-empty">
                                <input type="email" name="email" class="form-control" placeholder="Email">
                            </div>
                            <div class="form-group is-empty">
                                <input type="email" name="confirm_email" class="form-control" placeholder="Confirmer l'email">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default btn-simple">S'inscrire</button>
                    <button type="button" class="btn btn-danger btn-simple" data-dismiss="modal">Fermer</button>
                </div>
            </form>
        </div>
    </div>
</div>