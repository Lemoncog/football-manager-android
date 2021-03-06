package uk.co.lemoncog.footballmanager.android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.android.account.LoginFragment
import uk.co.lemoncog.footballmanager.android.account.getAccountSharedPrefs
import uk.co.lemoncog.footballmanager.android.list.GameListFragment
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataProvider
import uk.co.lemoncog.footballmanager.core.GameLoginController
import uk.co.lemoncog.footballmanager.core.GameLoginNavigation

class MainActivity : AppCompatActivity(), GameLoginNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameLoginController = GameLoginController(UserLoginProvider(getAccountSharedPrefs(this)), this);

        if(savedInstanceState == null) {
            gameLoginController.onReady();
        }
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, LoginFragment(), "login_frag").commit();
    }

    override fun navigateToGameScreen() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, GameListFragment(), "game_frag").commit();
    }
}
