<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="event" type="fr.eventmanager.entity.Event" required="true" rtexprvalue="true" %>

<div class="row">
    <div class="col-sm-12">
        <div class="form-group">
            <label class="control-label">Nom de l'événement</label>
            <input type="text" name="title" class="form-control" placeholder="Donnez-lui un titre court et distinctif" value="${event.title}">
        </div>

        <div class="form-group">
            <label class="control-label">Description</label>
            <textarea name="description" class="form-control" placeholder="Décrivez cet événement pour présenter son contenu, les intervenants, son organisation, etc" rows="8">${event.description}</textarea>
        </div>

    </div>
</div>
<div class="row">
    <div class="col-sm-6">
        <div class="form-group">
            <label class="control-label">Adresse</label>
            <input type="text" name="address_name" class="form-control" placeholder="Précisez où il est organisé" value="${event.address.name}">
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
            <input type="number" name="address_zipCode" min="0" class="form-control" placeholder="44000" value="${event.address.zipCode}">
            <span class="material-input"></span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-2">
        <div class="form-group">
            <label class="control-label">Nombre de places (maximum)</label>
            <input type="number" name="maxTickets" min="0" class="form-control" placeholder="40" value="${event.maxTickets}">
            <span class="material-input"></span>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="form-group">
            <label class="control-label">Prix du ticket</label>
            <input type="number" name="price" min="0" class="form-control" placeholder="0.0" value="${event.price}">
            <span class="material-input"></span>
        </div>
    </div>
    <div class="col-sm-2 col-sm-offset-2">
        <div class="form-group">
            <label class="control-label">Date & heure</label>
            <input type="text" name="date_day" id="datepicker" class="form-control" placeholder="dd/mm/yyyy" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${event.date}"/>">
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
