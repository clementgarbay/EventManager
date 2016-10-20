<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-sm-12">
    <div class="card">
        <div class="header" data-background-color="purple" style="margin-top: -20px; background: #9c27b0;">
            <div class="nav-tabs-navigation">
                <div class="nav-tabs-wrapper">
                    <ul class="nav nav-tabs" data-tabs="tabs">
                        <li class="active">
                            <a href="#profile" data-toggle="tab" aria-expanded="true">
                                <i class="fa fa-child"></i> Profil
                                <div class="ripple-container"></div>
                            </a>
                        </li>
                        <li>
                            <a href="#events_owner" data-toggle="tab" aria-expanded="false">
                                <i class="fa fa-calendar-o"></i> Mes événements
                                <div class="ripple-container"></div>
                            </a>
                        </li>
                        <li>
                            <a href="#events_participant" data-toggle="tab" aria-expanded="false">
                                <i class="fa fa-calendar-o"></i> Événements auquels je participe
                                <div class="ripple-container"></div>
                            </a>
                        </li>
                        <li style="float: right;">
                            <a href="" data-toggle="modal" data-target="#newEvent">
                                <i class="fa fa-plus"></i> Créer un événement
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
                    <h3>Profil</h3>
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

<div class="modal fade" id="newEvent" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Créer un événement</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <input type="text" class="form-control" placeholder="Titre">
                        <textarea class="form-control" placeholder="Description" rows="8"></textarea>
                        <input type="text" class="form-control" placeholder="Lieu">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label">Nombre de places</label>
                            <input type="number" class="form-control">
                            <span class="material-input"></span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label">Date</label>
                            <input type="date" class="form-control">
                            <span class="material-input"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-simple">Enregistrer</button>
                <button type="button" class="btn btn-danger btn-simple" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>