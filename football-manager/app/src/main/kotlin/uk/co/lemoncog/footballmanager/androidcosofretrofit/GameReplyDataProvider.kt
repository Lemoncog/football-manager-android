package uk.co.lemoncog.footballmanager.androidcosofretrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.lemoncog.footballmanager.android.services.GameService
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataProvider
import uk.co.lemoncog.footballmanager.core.GameReply


class GameReplyDataProvider(val id: Long, val authenticatedUser: AuthenticatedUser) : DataProvider<GameReply> {
    override fun get(success: (GameReply) -> Unit, failure: () -> Unit) {
        val retrofit = Retrofit.Builder().baseUrl("https://footballmanagerapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        val call = retrofit.create<GameService>(GameService::class.java).reply(authenticatedUser.token, "$id");
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                var i = 0;
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                throw t;
            }

        });
    }
}