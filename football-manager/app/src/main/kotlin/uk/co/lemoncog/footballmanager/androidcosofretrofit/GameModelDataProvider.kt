package uk.co.lemoncog.footballmanager.androidcosofretrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.lemoncog.footballmanager.android.services.GameService
import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.adapters.parseServerDate
import uk.co.lemoncog.footballmanager.core.adapters.serverGameModelToGameModel

val firstGameReply = GameReply(1, "ross.wilson@bbc.co.uk", parseServerDate("2016-03-21T13:10:38.996Z"));
val secondGameReply = GameReply(2, "lemoncog@gmail.co.uk", parseServerDate("2016-03-21T13:12:09.197Z"));
var gameModel = GameModel(1,
        "Ross play with me",
        "YAY",
        parseServerDate("2016-03-17T09:15:00.000Z"),
        parseServerDate("2016-03-17T09:16:00.803Z"),
        parseServerDate("2016-03-17T09:16:00.803Z"),
        arrayOf(firstGameReply, secondGameReply));


class GameModelDataProvider(val id: Long, val authenticatedUser: AuthenticatedUser) : DataProvider<GameModel, Throwable> {
    override fun get(success: (GameModel) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder().baseUrl("https://footballmanagerapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        val call = retrofit.create<GameService>(GameService::class.java).game(authenticatedUser.token, "$id");

        call.enqueue(object: Callback<ServerGameModel> {
            override fun onResponse(call: Call<ServerGameModel>, response: Response<ServerGameModel>) {
                val body = response.body();
                success(serverGameModelToGameModel(body));
            }

            override fun onFailure(call: Call<ServerGameModel>, t: Throwable) {
                failure(t);
            }
        })
    }
}