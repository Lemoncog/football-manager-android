package uk.co.lemoncog.footballmanager.androidcosofretrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.lemoncog.footballmanager.android.services.GameService
import uk.co.lemoncog.footballmanager.core.*

class LoginStepsProvider(val username: String, val password: String) : DataProvider<AuthenticatedUser, LoginFailure> {
    override fun get(success: (AuthenticatedUser) -> Unit, failure: (LoginFailure) -> Unit) {

        val retrofit = Retrofit.Builder().baseUrl("https://footballmanagerapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        val call = retrofit.create<GameService>(GameService::class.java).login(username, password);

        call.enqueue(object : Callback<ServerLoginReply> {
            override fun onResponse(call: Call<ServerLoginReply>, response: Response<ServerLoginReply>) {

                if(response.body().status.equals(ServerLoginSuccess)) {
                    success(AuthenticatedUser(response.body().token, username));
                } else
                {
                    failure(LoginFailure(response.body().status));
                }
            }

            override fun onFailure(call: Call<ServerLoginReply>, t: Throwable) {
                failure(LoginFailure(t.message as String));
            }
        });

    }
}