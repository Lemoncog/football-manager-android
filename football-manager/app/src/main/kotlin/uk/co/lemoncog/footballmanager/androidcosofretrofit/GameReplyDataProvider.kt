package uk.co.lemoncog.footballmanager.androidcosofretrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.lemoncog.footballmanager.android.services.GameService
import uk.co.lemoncog.footballmanager.core.*


class GameReplyDataProvider(val id: Long, val authenticatedUser: AuthenticatedUser) : DataProvider<GameRequestReply> {
    override fun get(success: (GameRequestReply) -> Unit, failure: () -> Unit) {
        val retrofit = Retrofit.Builder().baseUrl("https://footballmanagerapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        val call = retrofit.create<GameService>(GameService::class.java).reply(authenticatedUser.token, "$id");
        call.enqueue(object : Callback<ServerGamePostReply> {
            override fun onResponse(call: Call<ServerGamePostReply>, response: Response<ServerGamePostReply>) {
                success(GameRequestReply(response.body().status));
            }

            override fun onFailure(call: Call<ServerGamePostReply>, t: Throwable) {
                failure();
            }

        });
    }
}