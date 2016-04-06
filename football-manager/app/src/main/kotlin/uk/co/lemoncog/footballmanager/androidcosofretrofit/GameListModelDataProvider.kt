package uk.co.lemoncog.footballmanager.androidcosofretrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.lemoncog.footballmanager.android.services.GameService
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.adapters.serverGameListModelToGameListModel

class GameListModelDataProvider(val authenticatedUser: AuthenticatedUser) : DataProvider<GameListModel, Throwable> {
    override fun get(success: (GameListModel) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder().baseUrl("https://footballmanagerapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        val call = retrofit.create<GameService>(GameService::class.java).listGames(authenticatedUser.token);

        call.enqueue(object: Callback<List<ServerGameModel>> {
            override fun onResponse(call: Call<List<ServerGameModel>>, response: Response<List<ServerGameModel>>) {
                val body = response.body();
                success(serverGameListModelToGameListModel(body));
            }

            override fun onFailure(call: Call<List<ServerGameModel>>, t: Throwable) {
                failure(t);
            }
        });

    }
}