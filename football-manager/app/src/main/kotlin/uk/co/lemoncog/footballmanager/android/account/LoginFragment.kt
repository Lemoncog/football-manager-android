package uk.co.lemoncog.footballmanager.android.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.android.list.GameListFragment
import uk.co.lemoncog.footballmanager.android.list.showToastFor
import uk.co.lemoncog.footballmanager.androidcosofretrofit.LoginStepsProvider
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.GameLoginNavigation
import uk.co.lemoncog.footballmanager.core.LoginFailure
import uk.co.lemoncog.footballmanager.core.LoginModel
import uk.co.lemoncog.footballmanager.core.account.LoginPresenter

fun getAccountSharedPrefs(context: Context) : SharedPreferences {
    return context.getSharedPreferences("USER_STORAGE", Context.MODE_PRIVATE);
}

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

class LoginViewController(val context: Context, val loginPresenter: LoginPresenter, val authenticatedUserWriter: AuthenticatedUserWriter, val loginWizard: LoginWizard, val gameLoginNavigation: GameLoginNavigation) {
    fun onResume() {
        loginPresenter.onReady();

        loginPresenter.loginActionListener = { loginModel : LoginModel ->
            loginWizard.login(loginModel.username, loginModel.password, {
                authenticatedUser: AuthenticatedUser ->

                authenticatedUserWriter.write(authenticatedUser, {
                    gameLoginNavigation.navigateToGameScreen();
                });

            }, {
                loginFailure : LoginFailure ->
                showToastFor(context, loginFailure);
            });
        }
    }

    fun onPause() {
    }
}

class LoginFragment : Fragment(), GameLoginNavigation {
    override fun navigateToGameScreen() {
        activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GameListFragment(), "game_frag").commit();
    }

    override fun navigateToLoginScreen() {
        throw UnsupportedOperationException()
    }

    lateinit var loginViewController: LoginViewController;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.login_fragment, container, false) as View;

        val sharedPrefs = getAccountSharedPrefs(activity);

        loginViewController = LoginViewController(context, LoginPresenter(LoginViewImpl(view.findViewById(R.id.login_view))), AuthenticatedUserWriter(sharedPrefs), LoginWizard(), this);

        return view;
    }

    override fun onResume() {
        super.onResume()

        loginViewController.onResume();
    }

    override fun onPause() {
        super.onPause()

        loginViewController.onPause();
    }

}
