package uk.co.lemoncog.footballmanager.android

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.*
import java.text.SimpleDateFormat

class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val gameView = GameView(itemView);

}

class GameListAdapter : RecyclerView.Adapter<GameListViewHolder>() {
    var data : Array<GameViewModel> = emptyArray();

    override fun onBindViewHolder(holder: GameListViewHolder?, position: Int) {
        holder!!.gameView.show(data[position])
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
            val sdf = SimpleDateFormat("EEE, d MMM yyyy, hh:mm aaa");
            val prettyDate = sdf.format(gameModel.date);

            games.add(GameViewModel(gameModel.name, gameModel.description, prettyDate, gameModel.replies.count()));
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

class GameListViewController : StatefulView<GameListViewModel> {
    override fun setAcceptClickedListener(clicked: () -> Unit) {
        throw UnsupportedOperationException()
    }

    override fun show(viewData: GameListViewModel) {
        adapter.data = viewData.games;
        adapter.notifyDataSetChanged();
    }

    lateinit var recyclerView: RecyclerView;
    lateinit var layoutManager: RecyclerView.LayoutManager;
    val adapter : GameListAdapter = GameListAdapter();
    lateinit var gameListPresenter : GameListPresenter;

    fun attachView(viewGroup: ViewGroup) {
        recyclerView = viewGroup.findViewById(R.id.recycler_view) as RecyclerView;

        layoutManager = LinearLayoutManager(viewGroup.context);
        recyclerView.layoutManager = layoutManager;
        recyclerView.adapter = adapter;

        gameListPresenter = GameListPresenter(this, GameListModelDataProvider())
    }

    fun detachView() {
     }

    fun onResume() {
        gameListPresenter.onReady();
     }

    fun onPause() {
    }
}