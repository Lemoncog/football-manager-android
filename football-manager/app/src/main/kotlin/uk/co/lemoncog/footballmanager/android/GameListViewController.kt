package uk.co.lemoncog.footballmanager.android

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.util.convertGameModelToViewModel

interface GameListClickedListener {
    fun gameViewClicked(position: Int, gameViewModel: GameViewModel);
}

class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val gameView = GameView(itemView);

}

class GameListAdapter() : RecyclerView.Adapter<GameListViewHolder>() {
    var data : Array<GameViewModel> = emptyArray();
    var gameListClickedListener: GameListClickedListener? = null;


    override fun onBindViewHolder(holder: GameListViewHolder?, position: Int) {
        holder!!.gameView.show(data[position])
        holder!!.gameView.setAcceptClickedListener { gameListClickedListener?.gameViewClicked(position, data[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GameListViewHolder? {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.view_game, parent, false);
        val viewHolder = GameListViewHolder(view);
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.count();
    }
}

class GameListPresenter(val view: StatefulView<GameListViewModel>, val dataProvider: DataProvider<GameListModel>) {

    fun onReady() {
        refresh();
    }

    private fun gameModelToGameListViewModel(gameListModel: GameListModel) : GameListViewModel {

        val games = mutableListOf<GameViewModel>();

        for(gameModel: GameModel in gameListModel.games) {
            games.add(convertGameModelToViewModel(gameModel));
        }

        return GameListViewModel(games.toTypedArray());
    }

    fun refresh() {
        var success = { gameModel: GameListModel ->  view.show(gameModelToGameListViewModel(gameModel)) }
        var failure = {};

        dataProvider.get(success, failure);
    }
}

class GameListModelDataProvider : DataProvider<GameListModel> {
    override fun get(success: (GameListModel) -> Unit, failure: () -> Unit) {
        val dummyList = mutableListOf<GameModel>();

        GameModelDataProvider().get({ gameModel : GameModel -> dummyList.add(gameModel);
            GameModelDataProvider().get({ gameModel : GameModel -> dummyList.add(gameModel);
                GameModelDataProvider().get({ gameModel: GameModel -> dummyList.add(gameModel)

                    success(GameListModel(dummyList.toTypedArray()));
                }, {});
            }, {}) }, {});
    }
}

class GameListViewController(val recyclerView: RecyclerView, val adapter: GameListAdapter, val layoutManager: LinearLayoutManager) : StatefulView<GameListViewModel>, GameListClickedListener {
    val gameListPresenter : GameListPresenter = GameListPresenter(this, GameListModelDataProvider());
    val gameRequestController = GameRequestController(GameReplyDataProvider());

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

        gameRequestController.requestToPlay({
            gameListPresenter.refresh();
        }, {
        });
    }
}