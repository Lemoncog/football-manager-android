package uk.co.lemoncog.footballmanager.android.account

import uk.co.lemoncog.footballmanager.androidcosofretrofit.LoginStepsProvider
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.LoginFailure

class LoginWizard {
    fun login(username: String, password: String, success: (AuthenticatedUser) -> Unit, failure: (LoginFailure) -> Unit) {
        var loginStepsProvider = LoginStepsProvider(username, password);

        loginStepsProvider.get({ authenticatedUser : AuthenticatedUser ->
            success(authenticatedUser)
        }, { serverLoginFailure : LoginFailure ->
            failure(serverLoginFailure);
        });
    }
}