<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
    <div class="card card-signup">
        <form class="form" method="post" action="/login">
            <div class="content">
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">email</i>
                    </span>
                    <div class="form-group is-empty">
                        <input type="text" name="user_email" class="form-control" placeholder="Email">
                        <span class="material-input"></span>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">lock_outline</i>
                    </span>
                    <div class="form-group is-empty">
                        <input type="password" name="user_password" class="form-control" placeholder="Mot de passe">
                        <span class="material-input"></span>
                    </div>
                </div>
            </div>
            <div class="footer text-center">
                <button type="submit" class="btn btn-simple btn-primary btn-lg">Se connecter</button>
            </div>
        </form>
    </div>
</div>
<br /><br />
l'utilisateur existe : ${USER_EXISTS}