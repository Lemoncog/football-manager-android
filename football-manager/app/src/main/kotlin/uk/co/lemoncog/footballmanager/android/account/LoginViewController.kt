package uk.co.lemoncog.footballmanager.android.account

import android.content.Context
import uk.co.lemoncog.footballmanager.android.list.showToastFor
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.GameLoginNavigation
import uk.co.lemoncog.footballmanager.core.LoginFailure
import uk.co.lemoncog.footballmanager.core.LoginModel
import uk.co.lemoncog.footballmanager.core.account.LoginPresenter

class LoginViewController(val context: Context, val loginPresenter: LoginPresenter, val authenticatedUserWriter: AuthenticatedUserWriter, val loginWizard: LoginWizard, val gameLoginNavigation: GameLoginNavigation) {
    fun onResume() {
        loginPresenter.onReady();

        loginPresenter.loginActionListener = { loginModel : LoginModel ->
            loginWizard.login(loginModel.username, loginModel.password, {
                authenticatedUser: AuthenticatedUser ->
                persistAuthenticatedUser(authenticatedUser);
            }, {
                loginFailure : LoginFailure ->
                showToastFor(context, loginFailure);
            });
        }
    }

    private fun persistAuthenticatedUser(authenticatedUser: AuthenticatedUser) {
        authenticatedUserWriter.write(authenticatedUser, {
            gameLoginNavigation.navigateToGameScreen();
        })
    }

    fun onPause() {
    }
}