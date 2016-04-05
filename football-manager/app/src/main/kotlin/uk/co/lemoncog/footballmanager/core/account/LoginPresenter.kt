package uk.co.lemoncog.footballmanager.core.account

import uk.co.lemoncog.footballmanager.core.LoginModel

class LoginPresenter(val loginView: LoginView) {
    var loginActionListener: ((LoginModel) -> Unit)? = null;
    init {
        loginView.setLoginClickedListener {
            loginPressed();
        }
    }

    fun onReady() {
    }

    fun loginPressed() {
        loginActionListener?.invoke(LoginModel(loginView.getUsername(), loginView.getPassword()));
    }
}

