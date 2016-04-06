package uk.co.lemoncog.footballmanager.android.list

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.android.UserLoginProvider
import uk.co.lemoncog.footballmanager.android.account.getAccountSharedPrefs
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.LoginFailure

fun showToastFor(context : Context, throwable: Throwable) {
    Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show();
}

fun showToastFor(context: Context, loginFailure: LoginFailure) {
    Toast.makeText(context, loginFailure.reason, Toast.LENGTH_LONG).show();
}

class GameListFragment : Fragment() {

    lateinit var gameViewController : GameListViewController;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.game_list_fragment, container, false);

        val recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView;
        val adapter = GameListAdapter();
        val layoutManager = LinearLayoutManager(context);

        //Look at this NPE waiting to happen!
        UserLoginProvider(getAccountSharedPrefs(activity)).get( { authenticatedUser: AuthenticatedUser ->
            gameViewController = GameListViewController(authenticatedUser, context, fragmentManager, recyclerView, adapter, layoutManager)
        }, {
            loginFailure : LoginFailure -> showToastFor(activity, loginFailure);
        })

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

