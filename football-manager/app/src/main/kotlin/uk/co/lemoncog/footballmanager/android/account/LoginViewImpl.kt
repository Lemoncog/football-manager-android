package uk.co.lemoncog.footballmanager.android.account

import android.view.View
import android.widget.EditText
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.account.LoginView

class LoginViewImpl(val root: View) : LoginView {
    val userNameView = root.findViewById(R.id.login_username) as EditText;
    val passwordView = root.findViewById(R.id.login_password) as EditText;
    val loginView = root.findViewById(R.id.login_button);

    var viewClicked: (() -> Unit)? = null;

    init {
        loginView.setOnClickListener { viewClicked?.invoke(); }
    }

    override fun setLoginClickedListener(clicked: () -> Unit) {
        viewClicked = clicked;
    }

    override fun getUsername(): String {
        return userNameView.text.toString();
    }

    override fun getPassword(): String {
        return passwordView.text.toString();
    }
}