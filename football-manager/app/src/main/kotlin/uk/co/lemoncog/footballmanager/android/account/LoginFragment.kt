package uk.co.lemoncog.footballmanager.android.account

import android.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LoginViewController {
    fun onResume() {
    }

    fun onPause() {
    }
}

class LoginFragment : Fragment() {

    lateinit var loginViewController: LoginViewController;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.login_fragment, container, false) as View;

        loginViewController = LoginViewController();

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
