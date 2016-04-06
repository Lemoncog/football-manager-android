package uk.co.lemoncog.footballmanager.android.account

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.android.list.GameListFragment
import uk.co.lemoncog.footballmanager.core.GameLoginNavigation
import uk.co.lemoncog.footballmanager.core.account.LoginPresenter

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
