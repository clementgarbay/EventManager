<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="navbar navbar-absolute navbar-transparent">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%= request.getContextPath() %>">Event Manager</a>
        </div>
        <div class="collapse navbar-collapse" id="navigation-doc">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="<app:getUrl route="EVENTS" />">
                        Prochains événements
                    </a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/app/login">
                        <i class="fa fa-sign-in"></i>Se connecter
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>