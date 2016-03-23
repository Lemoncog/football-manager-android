package uk.co.lemoncog.footballmanager.android

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.Interceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.util.convertSingleGameModelToViewModel
import uk.co.lemoncog.footballmanager.core.util.parseServerDate

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

        for(gameModel: GameListSingleModel in gameListModel.games) {
            games.add(convertSingleGameModelToViewModel(gameModel));
        }

        return GameListViewModel(games.toTypedArray());
    }

    fun refresh() {
        var success = { gameModel: GameListModel ->  view.show(gameModelToGameListViewModel(gameModel)) }
        var failure = {};

        dataProvider.get(success, failure);
    }
}

interface GameService {
    @GET("games.json")
    fun listGames(@Header("X-API-TOKEN") token: String) : Call<List<ServerGameListSingleModel>>;
}

class GameListModelDataProvider(val authenticatedUser: AuthenticatedUser) : DataProvider<GameListModel> {
    override fun get(success: (GameListModel) -> Unit, failure: () -> Unit) {
        val retrofit = Retrofit.Builder().baseUrl("https://footballmanagerapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        val call = retrofit.create<GameService>(GameService::class.java).listGames(authenticatedUser.token);

        call.enqueue(object: Callback<List<ServerGameListSingleModel>> {
            override fun onResponse(call: Call<List<ServerGameListSingleModel>>, response: Response<List<ServerGameListSingleModel>>) {

                //Got a separation from Server to our Domain, server can change and the effect is handled here.
                val body = response.body();
                val gameList = mutableListOf<GameListSingleModel>();
                for(serverGame in body) {
                    gameList.add(GameListSingleModel(serverGame.id, serverGame.name, serverGame.description, parseServerDate(serverGame.date), parseServerDate(serverGame.created_at), parseServerDate(serverGame.updated_at), serverGame.replies_count));
                }
                success(GameListModel(gameList.toTypedArray()));
            }

            override fun onFailure(call: Call<List<ServerGameListSingleModel>>, t: Throwable) {
                throw t;
            }
        });

    }
}

class GameListViewController(val authenticatedUser: AuthenticatedUser, val recyclerView: RecyclerView, val adapter: GameListAdapter, val layoutManager: LinearLayoutManager) : StatefulView<GameListViewModel>, GameListClickedListener {
    val gameListPresenter : GameListPresenter = GameListPresenter(this, GameListModelDataProvider(authenticatedUser));
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