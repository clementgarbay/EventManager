<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-12">
    <div class="card">
        <div class="col-sm-9">
            <h1>Lorem ipsum dolor sit amet</h1>
            <p>Consectetur adipisicing elit. Ab adipisci deserunt quia vero voluptatem. Aliquam doloremque eaque error est eveniet, facere illo inventore obcaecati perferendis quis sapiente soluta tenetur velit!
            </p>
        </div>
        <div class="col-sm-3 card-infos">
            <h3 style="margin-top: 0;">Date et heure</h3>
            <p>Mardi 9 juillet, 19h</p>

            <h3>Lieu</h3>
            <p>Ecole des Mines (Nantes, France)</p>

            <h3>Participants</h3>
            <p>23 personnes inscrites <small>(6 places restantes)</small></p>

            <button class="btn btn-primary" style="width: 100%; margin-top: 50px;" data-toggle="modal" data-target="#subscribeModal">
                <i class="fa fa-plus" style="margin-right: 5px;"></i> S'inscrire
            </button>
        </div>
    </div>
</div>

<div class="modal fade" id="subscribeModal" style="display: none">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
                <h4 class="modal-title">S'inscrire</h4>
            </div>
            <form class="form" method="post" action="">
                <div class="modal-body">
                    S'inscrire à l'événement <b>Titre de l'événement</b> du <b>9 juillet à 19h</b>.
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group is-empty">
                                <input type="text" name="name" class="form-control" placeholder="Nom">
                            </div>
                            <div class="form-group is-empty">
                                <input type="email" name="email" class="form-control" placeholder="Email">
                            </div>
                            <div class="form-group is-empty">
                                <input type="email" name="confirm_email" class="form-control" placeholder="Confirmer l'email">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default btn-simple">S'inscrire</button>
                    <button type="button" class="btn btn-danger btn-simple" data-dismiss="modal">Fermer</button>
                </div>
            </form>
        </div>
    </div>
</div>