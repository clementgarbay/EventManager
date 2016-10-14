<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
    <div class="card card-signup">
        <div class="form" method="post" action="/profil  ">
            <div class="content">
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">event</i>
                    </span>
                        <span class="label label-primary">Mes événements</span>
                    </div>
                </div>
            </br>
            </br>
            <span class="label label-primary">Organisateur</span>
                <table class="table">
                    <thead>
                    <tr>
                        <th class="text-center">#</th>
                        <th>Nom</th>
                        <th>Lieu</th>
                        <th>Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="text-center">1</td>
                        <td>convention</td>
                        <td>Nantes</td>
                        <td>15/10</td>
                        <td class="td-actions text-right">
                            <button type="button" rel="tooltip" title="View" class="btn btn-danger btn-simple btn-xs">
                                <i class="fa fa-search"></i>
                            </button>
                        <button type="button" rel="tooltip" title="Edit" class="btn btn-success btn-simple btn-xs">
                            <i class="fa fa-edit"></i>
                        </button>
                        <button type="button" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
                            <i class="fa fa-times"></i>
                        </button>
                    </td>
                    </tr>
                    <tr>
                        <td class="text-center">2</td>
                        <td>Festival</td>
                        <td>Vannes</td>
                        <td>18/11</td>
                        <td class="td-actions text-right">
                            <button type="button" rel="tooltip" title="View" class="btn btn-danger btn-simple btn-xs">
                                <i class="fa fa-search"></i>
                            </button>
                        <button type="button" rel="tooltip" title="Edit" class="btn btn-success btn-simple btn-xs">
                        <i class="fa fa-edit"></i>
                    </button>
                        <button type="button" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
                            <i class="fa fa-times"></i>
                        </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </br>
            </br>
            <span class="label label-primary">Participant</span>
            <table class="table">
                <thead>
                <tr>
                    <th class="text-center">#</th>
                    <th>Nom</th>
                    <th>Lieu</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="text-center">1</td>
                    <td>Soirée</td>
                    <td>Nantes</td>
                    <td>23/10</td>
                    <td class="td-actions text-right">
                        <button type="button" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
                            <i class="fa fa-times"></i>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td class="text-center">2</td>
                    <td>Basket</td>
                    <td>Cholet</td>
                    <td>12/11</td>
                    <td class="td-actions text-right">
                        <button type="button" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
                            <i class="fa fa-times"></i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
            <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                Nouvel événement
            </button>
            </div>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Créer événement</h4>
                        </div>
                        <div class="modal-body">
                            <textarea class="form-control" placeholder="Nom" rows="1"></textarea>
                            <textarea class="form-control" placeholder="Description" rows="8"></textarea>

                            <table>
                                <tr>
                                    <td><textarea class="form-control" placeholder="Places" rows="1"></textarea></td>
                            <td><textarea class="form-control" placeholder="Date" rows="1"></textarea></td>
                                </tr>
                            </table>
                           </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-simple" data-dismiss="modal">Fermer</button>
                            <button type="button" class="btn btn-info btn-simple">Enregistrer</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>