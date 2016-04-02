package uk.co.lemoncog.footballmanager.androidcosofretrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.lemoncog.footballmanager.android.services.GameService
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.adapters.parseServerDate

class GameListModelDataProvider(val authenticatedUser: AuthenticatedUser) : DataProvider<GameListModel> {
    override fun get(success: (GameListModel) -> Unit, failure: () -> Unit) {
        val retrofit = Retrofit.Builder().baseUrl("https://footballmanagerapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        val call = retrofit.create<GameService>(GameService::class.java).listGames(authenticatedUser.token);

        call.enqueue(object: Callback<List<ServerGameModel>> {
            override fun onResponse(call: Call<List<ServerGameModel>>, response: Response<List<ServerGameModel>>) {

                //Got a separation from Server to our Domain, server can change and the effect is handled here.
                val body = response.body();
                success(serverGameModelToGameModel(body));
            }

            override fun onFailure(call: Call<List<ServerGameModel>>, t: Throwable) {
                throw t;
            }
        });

    }

    fun serverGameModelToGameModel(serverModelList: List<ServerGameModel>) : GameListModel {
        val gameList = mutableListOf<GameModel>();
        for(serverGame in serverModelList) {

            val replies = mutableListOf<GameReply>()
            for(reply in serverGame.replies) {
                replies.add(GameReply(reply.id, reply.user, parseServerDate(reply.created_at)));
            }

            gameList.add(GameModel(serverGame.id, serverGame.name, serverGame.description, parseServerDate(serverGame.date), parseServerDate(serverGame.created_at), parseServerDate(serverGame.updated_at), replies.toTypedArray()));
        }

        return GameListModel(gameList.toTypedArray());
    }
}