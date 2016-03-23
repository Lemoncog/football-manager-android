package uk.co.lemoncog.footballmanager.android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataProvider
import uk.co.lemoncog.footballmanager.core.GameLoginController
import uk.co.lemoncog.footballmanager.core.GameLoginNavigation

class MainActivity : AppCompatActivity(), GameLoginNavigation {
    class UserLoginProvider : DataProvider<AuthenticatedUser> {
        override fun get(success: (AuthenticatedUser) -> Unit, failure: () -> Unit) {
            success(AuthenticatedUser("e3409875d642bcab5afc8d3695644938"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameLoginController = GameLoginController(UserLoginProvider(), this);

        gameLoginController.onReady();
    }

    override fun navigateToGameScreen() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, GameListFragment(), "game_frag").commit();
    }
}
