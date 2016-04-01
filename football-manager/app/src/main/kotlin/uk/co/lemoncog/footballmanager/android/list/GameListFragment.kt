package uk.co.lemoncog.footballmanager.android.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.android.UserLoginProvider
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser

class GameListFragment : Fragment() {

    lateinit var gameViewController : GameListViewController;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.game_list_fragment, container, false);

        val recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView;
        val adapter = GameListAdapter();
        val layoutManager = LinearLayoutManager(context);

        //Look at this NPE waiting to happen!
        UserLoginProvider().get( { authenticatedUser: AuthenticatedUser ->
            gameViewController = GameListViewController(authenticatedUser, fragmentManager, recyclerView, adapter, layoutManager)
        }, {})

        return view;
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onStart() {
        super.onStart()

        gameViewController.onResume();
    }

    override fun onStop() {
        super.onStop()

        gameViewController.onPause();
    }
}

