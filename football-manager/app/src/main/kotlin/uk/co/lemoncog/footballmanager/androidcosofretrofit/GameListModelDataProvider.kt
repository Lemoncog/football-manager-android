package uk.co.lemoncog.footballmanager.androidcosofretrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.lemoncog.footballmanager.android.services.GameService
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.util.parseServerDate

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