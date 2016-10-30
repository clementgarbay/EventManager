<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="app" uri="application" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-12">
    <div class="card">
        <div class="content">
            <h3 style="margin-top: 0;">Mon profil</h3>
            <p>Vous êtes connecté en tant que <b>${SECURITY_LOGGED_USER.name} <small>(${SECURITY_LOGGED_USER.email})</small></b>.</p>
        </div>
    </div>
</div>