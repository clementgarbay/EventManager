<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="application" %>

<div class="col-sm-12">
    <div class="card">
        <div class="content">
            <h4>Créer un événement</h4>

            <form method="post" action="<app:getUrl pathId="NEW_EVENT"/>">
                <div class="row">
                    <div class="col-sm-12">
                        <input type="text" name="title" class="form-control" placeholder="Titre" value="${event.title}">
                        <textarea name="description" class="form-control" placeholder="Description" rows="8">${event.description}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label">Adresse</label>
                            <input type="text" name="address_name" class="form-control" placeholder="2 Rue Alfred Kastler" value="${event.address.name}">
                            <span class="material-input"></span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label">Ville</label>
                            <input type="text" name="address_city" class="form-control" placeholder="Nantes" value="${event.address.city}">
                            <span class="material-input"></span>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label">Code postal</label>
                            <input type="number" name="address_zipCode" class="form-control" placeholder="44300" value="${event.address.zipCode}">
                            <span class="material-input"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label">Nombre de places</label>
                            <input type="number" name="maxTickets" min="0" class="form-control" placeholder="40" value="${event.maxTickets}">
                            <span class="material-input"></span>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label">Prix du ticket</label>
                            <input type="number" name="price" min="0" class="form-control" placeholder="0" value="${event.price}">
                            <span class="material-input"></span>
                        </div>
                    </div>
                    <div class="col-sm-2 col-sm-offset-2">
                        <div class="form-group">
                            <label class="control-label">Date & heure</label>
                            <input type="date" name="date_day" class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${event.date}"/>">
                            <span class="material-input"></span>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label" style="height: 9px;"></label>
                            <input type="text" name="date_hour" class="form-control" placeholder="18:30" value="<fmt:formatDate pattern="H':'m" value="${event.date}"/>">
                            <span class="material-input"></span>
                        </div>
                    </div>
                </div>

                <button type="submit" class="btn btn-default" style="margin-top: 40px">Enregistrer</button>
            </form>
        </div>
    </div>
</div>
