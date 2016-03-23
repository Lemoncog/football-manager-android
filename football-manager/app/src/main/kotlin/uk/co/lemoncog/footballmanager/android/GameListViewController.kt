package uk.co.lemoncog.footballmanager.android

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import uk.co.lemoncog.footballmanager.androidcosofretrofit.GameListModelDataProvider
import uk.co.lemoncog.footballmanager.core.*

interface GameListClickedListener {
    fun gameViewClicked(position: Int, gameViewModel: GameViewModel);
}

class GameListViewController(val authenticatedUser: AuthenticatedUser, val recyclerView: RecyclerView, val adapter: GameListAdapter, val layoutManager: LinearLayoutManager) : StatefulView<GameListViewModel>, GameListClickedListener {
    val gameListPresenter : GameListPresenter = GameListPresenter(this, GameListModelDataProvider(authenticatedUser));
    val gameRequestController = GameRequestController(UserLoginProvider());

    init {
        adapter.gameListClickedListener = this;
        recyclerView.layoutManager = layoutManager;
        recyclerView.adapter = adapter;
    }

    override fun setAcceptClickedListener(clicked: () -> Unit) {
        throw UnsupportedOperationException()
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

        Log.v("GameListViewController", "gameViewClicked: $position")

        gameRequestController.requestToPlay(gameViewModel.id,{
            gameListPresenter.refresh();
        }, {
        });
    }
}