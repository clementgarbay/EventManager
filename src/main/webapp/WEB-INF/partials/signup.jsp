<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="app" uri="application" %>

<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
    <div class="card card-signup">
        <form class="form" method="post" action="<app:getUrl pathId="SIGNUP"/>">
            <div class="content">
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-user"></i>
                    </span>
                    <div class="form-group is-empty">
                        <input type="text" name="name" class="form-control" placeholder="Votre nom visible sur le site (Prénom Nom)" value="${user.name}">
                        <span class="material-input"></span>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-at"></i>
                    </span>
                    <div class="form-group is-empty">
                        <input type="text" name="email" class="form-control" placeholder="Votre adresse email" value="${user.email}">
                        <span class="material-input"></span>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-key"></i>
                    </span>
                    <div class="form-group is-empty">
                        <input type="password" name="password" class="form-control" placeholder="Votre mot de passe">
                        <span class="material-input"></span>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-key"></i>
                    </span>
                    <div class="form-group is-empty">
                        <input type="password" name="password2" class="form-control" placeholder="Répéter votre mot de passe">
                        <span class="material-input"></span>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-briefcase"></i>
                    </span>
                    <div class="form-group is-empty">
                        <input type="password" name="company" class="form-control" placeholder="Votre entreprise (facultatif)" value="${user.company}">
                        <span class="material-input"></span>
                    </div>
                </div>
            </div>
            <div class="footer text-center">
                <button type="submit" class="btn btn-primary" style="margin: 20px 0 0 0;">S'inscrire</button>
                <div style="font-size: .8em; color: #555; margin: 10px 0;">
                    <a href="<app:getUrl pathId="LOGIN"/>" style="color: #555;">Se connecter</a> | <a href="" style="color: #555;">Mot de passe oublié ?</a>
                </div>
            </div>
        </form>
    </div>
</div>