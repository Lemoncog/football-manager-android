package uk.co.lemoncog.footballmanager.android.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.android.list.GameListFragment
import uk.co.lemoncog.footballmanager.android.list.showToastFor
import uk.co.lemoncog.footballmanager.android.services.GameService
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.account.LoginPresenter

fun getAccountSharedPrefs(context: Context) : SharedPreferences {
    return context.getSharedPreferences("USER_STORAGE", Context.MODE_PRIVATE);
}

class LoginStepsProvider(val username: String, val password: String) : DataProvider<AuthenticatedUser, LoginFailure> {
    override fun get(success: (AuthenticatedUser) -> Unit, failure: (LoginFailure) -> Unit) {

        val retrofit = Retrofit.Builder().baseUrl("https://footballmanagerapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        val call = retrofit.create<GameService>(GameService::class.java).login(username, password);

        call.enqueue(object : Callback<ServerLoginReply> {
            override fun onResponse(call: Call<ServerLoginReply>, response: Response<ServerLoginReply>) {

                if(response.body().status.equals(ServerLoginSuccess)) {
                    success(AuthenticatedUser(response.body().token, username));
                } else
                {
                    failure(LoginFailure(response.body().status));
                }
            }

            override fun onFailure(call: Call<ServerLoginReply>, t: Throwable) {
                failure(LoginFailure(t.message as String));
            }
        });

    }
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

interface DataWriter<T> {
    fun write(value : T, success: (T) -> Unit);
}

class AuthenticatedUserWriter(val sharedPrefs: SharedPreferences) : DataWriter<AuthenticatedUser> {
    override fun write(value: AuthenticatedUser, success: (AuthenticatedUser) -> Unit) {
        if(sharedPrefs.edit().putString("username", value.email)
        .putString("token", value.token).commit()) {
            success(value);
        }
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
