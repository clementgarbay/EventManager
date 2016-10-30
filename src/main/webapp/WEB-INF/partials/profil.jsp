<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="app" uri="application" %>

<div class="col-sm-12">
    <div class="card">
        <div class="header" data-background-color="purple" style="padding: 0; background: #9c27b0;">
            <div class="nav-tabs-navigation">
                <div class="nav-tabs-wrapper">
                    <ul class="nav nav-tabs" data-tabs="tabs" style="padding: 0;">
                        <li class="active" style="margin-bottom: 0;">
                            <a href="#profile" data-toggle="tab" aria-expanded="true">
                                <i class="fa fa-child" style="margin-right: 5px;"></i> Mon profil
                                <div class="ripple-container"></div>
                            </a>
                        </li>
                        <li style="margin-bottom: 0;">
                            <a href="#events_owner" data-toggle="tab" aria-expanded="false">
                                <i class="fa fa-calendar-o" style="margin-right: 5px;"></i> Mes événements
                                <div class="ripple-container"></div>
                            </a>
                        </li>
                        <li style="margin-bottom: 0;">
                            <a href="#events_participant" data-toggle="tab" aria-expanded="false">
                                <i class="fa fa-calendar-times-o" style="margin-right: 5px;"></i> Événements auxquels je suis inscrit
                                <div class="ripple-container"></div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="content">
            <div class="tab-content">
                <div class="tab-pane active" id="profile">
                    <p>Vous êtes connecté en tant que <b>${SECURITY_LOGGED_USER.name} <small>(${SECURITY_LOGGED_USER.email})</small></b>.</p>
                </div>
                <div class="tab-pane" id="events_owner">
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
                            <tr>
                                <a href="#">
                                    <td>convention</td>
                                    <td>Nantes</td>
                                    <td>15/10</td>
                                </a>
                                <td class="td-actions">
                                    <button type="button" rel="tooltip" title="Modifier" class="btn btn-simple btn-xs">
                                        <i class="fa fa-edit"></i>
                                    </button>
                                    <button type="button" rel="tooltip" title="Supprimer" class="btn btn-danger btn-simple btn-xs">
                                        <i class="fa fa-times"></i>
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <a href="#">
                                    <td>Festival</td>
                                    <td>Vannes</td>
                                    <td>18/10</td>
                                </a>
                                <td class="td-actions">
                                    <button type="button" rel="tooltip" title="Modifier" class="btn btn-simple btn-xs">
                                        <i class="fa fa-edit"></i>
                                    </button>
                                    <button type="button" rel="tooltip" title="Supprimer" class="btn btn-danger btn-simple btn-xs">
                                        <i class="fa fa-times"></i>
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="events_participant">
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
                        <tr>
                            <a href="#">
                                <td>convention</td>
                                <td>Nantes</td>
                                <td>15/10</td>
                            </a>
                            <td class="td-actions">
                                <button type="button" rel="tooltip" title="Se désinscrire" class="btn btn-danger btn-simple btn-xs">
                                    <i class="fa fa-times"></i>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <a href="#">
                                <td>Festival</td>
                                <td>Vannes</td>
                                <td>18/10</td>
                            </a>
                            <td class="td-actions">
                                <button type="button" rel="tooltip" title="Se désinscrire" class="btn btn-danger btn-simple btn-xs">
                                    <i class="fa fa-times"></i>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>