package uk.co.lemoncog.footballmanager.android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.GameListViewModel
import uk.co.lemoncog.footballmanager.core.GameViewModel
import uk.co.lemoncog.footballmanager.core.StatefulView

class GameListFragment : Fragment(){

    lateinit var gameViewController : GameListViewController;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.game_list_fragment, container, false);

        val recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView;
        val adapter = GameListAdapter();
        val layoutManager = LinearLayoutManager(context);

        //Look at this NPE waiting to happen!
        MainActivity.UserLoginProvider().get( { authenticatedUser: AuthenticatedUser ->
            gameViewController = GameListViewController(authenticatedUser, recyclerView, adapter, layoutManager)
        }, {})

        return view;
    }

    override fun onDestroyView() {
        super.onDestroyView()
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

