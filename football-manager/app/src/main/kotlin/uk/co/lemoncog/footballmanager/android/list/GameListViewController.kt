package uk.co.lemoncog.footballmanager.android.list

import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.android.detail.createFragment
import uk.co.lemoncog.footballmanager.androidcosofretrofit.GameListModelDataProvider
import uk.co.lemoncog.footballmanager.core.*

interface GameListClickedListener {
    fun gameViewClicked(position: Int, gameViewModel: GameViewModel);
    fun acceptClicked(position: Int, gameViewModel: GameViewModel)
}

class GameListViewController(val authenticatedUser: AuthenticatedUser, val fragmentManager: FragmentManager, val recyclerView: RecyclerView, val adapter: GameListAdapter, val layoutManager: LinearLayoutManager) : ShowableDataView<GameListViewModel>, GameListClickedListener {
    val gameListPresenter : GameListPresenter = GameListPresenter(this, GameListModelDataProvider(authenticatedUser));
    val gameRequestController = GameRequestController(authenticatedUser);

    init {
        adapter.gameListClickedListener = this;
        recyclerView.layoutManager = layoutManager;
        recyclerView.adapter = adapter;
    }

    override fun show(viewData: GameListViewModel) {
        adapter.data = viewData.games;
        adapter.notifyDataSetChanged();
    }

    fun onResume() {
        gameListPresenter.onReady();
     }

    fun onPause() {
    }

    override fun gameViewClicked(position: Int, gameViewModel: GameViewModel) {
        fragmentManager.beginTransaction().addToBackStack("to_game_view").replace(R.id.fragment_container, createFragment(gameViewModel.id)).commit();
    }

    override fun acceptClicked(position: Int, gameViewModel: GameViewModel) {
        gameRequestController.requestToPlay(gameViewModel.id,{
            gameListPresenter.refresh();
        }, {
        });
    }
}