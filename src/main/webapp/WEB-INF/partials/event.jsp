<div class="col-sm-12">
    <div class="card">
        <div class="col-sm-9">
            <h1>Event's title</h1>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci deserunt quia vero voluptatem. Aliquam doloremque eaque error est eveniet, facere illo inventore obcaecati perferendis quis sapiente soluta tenetur velit!
            </p>
            <h2>Inscrits</h2>
            <table class="table table-hover" style="font-size: 14px;">
                <thead>
                    <tr>
                        <th></th>
                        <th>Nom</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Dakota Rice</td>
                        <td>Oud-Turnhout</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Minerva Hooper</td>
                        <td>Sinaai-Waas</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Sage Rodriguez</td>
                        <td>Baileux</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="col-sm-3 card-split">
            <h3 style="margin-top: 0;">Date et heure</h3>
            <p>Mardi 9 juillet, 19h</p>
            <h3>Lieu</h3>
            <p>Ecole des Mines (Nantes, France)</p>
            <button class="btn btn-primary" style="margin-top: 50px;" data-toggle="modal" data-target="#subscribeModal">
                <i class="fa fa-plus"></i> S'inscrire
            </button>
        </div>
    </div>
</div>

<div class="modal fade" id="subscribeModal" style="display: none;">
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