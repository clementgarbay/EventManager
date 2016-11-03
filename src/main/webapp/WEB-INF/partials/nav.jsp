<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="navbar navbar-absolute navbar-transparent">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<app:getUrl pathId="EVENTS" />">Event Manager</a>
        </div>
        <div class="collapse navbar-collapse" id="navigation">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="<app:getUrl pathId="EVENTS"/>">
                        Prochains événements
                    </a>
                </li>

                <c:if test="${SECURITY_IS_LOGGED}">
                    <li>
                        <a href="<app:getUrl pathId="EVENTS_NEW"/>">
                            <i class="fa fa-plus" style="margin: 3px 5px 0 0; font-size: 10px; float: left;"></i> Créer un événement
                        </a>
                    </li>
                </c:if>

                <c:choose>
                    <c:when test="${SECURITY_IS_LOGGED}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-cog"></i>
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="<app:getUrl pathId="PROFIL"/>">Profil</a></li>
                                <li><a href="<app:getUrl pathId="EVENTS_MY"/>">Mes événements</a></li>
                                <li class="divider"></li>
                                <li><a href="<app:getUrl pathId="LOGOUT"/>">Se déconnecter</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="<app:getUrl pathId="LOGIN"/>">
                                <i class="fa fa-sign-in"></i>Se connecter
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>