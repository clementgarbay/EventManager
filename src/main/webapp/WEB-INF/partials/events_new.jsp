<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="application" %>

<div class="col-sm-12">
    <div class="card">
        <div class="content">
            <h4>Créer un événement</h4>

            <form method="post" action="<app:getUrl pathId="EVENTS_NEW"/>">
                <app:eventForm event="${event}" />
                <button type="submit" class="btn btn-default" style="margin-top: 40px">Enregistrer</button>
            </form>
        </div>
    </div>
</div>
