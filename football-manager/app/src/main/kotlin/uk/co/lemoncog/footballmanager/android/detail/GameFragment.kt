package uk.co.lemoncog.footballmanager.android.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.android.UserLoginProvider
import uk.co.lemoncog.footballmanager.android.account.getAccountSharedPrefs
import uk.co.lemoncog.footballmanager.androidcosofretrofit.GameModelDataProvider
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.GamePresenter


fun createFragment(gameID: Long) : Fragment {
    val gameFragment = GameFragment();
    val gameArguments = Bundle();
    gameArguments.putLong("BUNDLE_KEY_GAME_ID", gameID);
    gameFragment.arguments = gameArguments;

    return gameFragment;
}

class GameFragment : Fragment() {
    lateinit var gameViewController : GameViewController;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.game_fragment, container, false) as View;

        var gameId = arguments.getLong("BUNDLE_KEY_GAME_ID");

        UserLoginProvider(getAccountSharedPrefs(activity)).get( { authenticatedUser: AuthenticatedUser ->
            val gamePresenter = GamePresenter(GameView(view), authenticatedUser, GameModelDataProvider(gameId, authenticatedUser));
            gameViewController = GameViewController(gameId, authenticatedUser, gamePresenter);
        }, {})

        return view;
    }

    override fun onResume() {
        super.onResume()

        gameViewController.onResume();
    }

    override fun onPause() {
        super.onPause()

        gameViewController.onPause();
    }
}

