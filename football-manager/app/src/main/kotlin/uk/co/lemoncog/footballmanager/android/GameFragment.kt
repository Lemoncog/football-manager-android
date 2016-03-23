package uk.co.lemoncog.footballmanager.android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser


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
        val view = inflater?.inflate(R.layout.game_fragment, container, false);

        var gameId = arguments.getLong("BUNDLE_KEY_GAME_ID");

        UserLoginProvider().get( { authenticatedUser: AuthenticatedUser ->
            gameViewController = GameViewController(gameId, authenticatedUser);
        }, {})
        gameViewController.attachView(view!!.findViewById(R.id.latest_game_view) as ViewGroup);

        return view;
    }

    override fun onDestroyView() {
        super.onDestroyView()

        gameViewController.detachView();
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

