package uk.co.lemoncog.footballmanager.android.services

import retrofit2.Call
import retrofit2.http.*
import uk.co.lemoncog.footballmanager.core.ServerGameModel
import uk.co.lemoncog.footballmanager.core.ServerGamePostReply
import uk.co.lemoncog.footballmanager.core.ServerGameReply
import uk.co.lemoncog.footballmanager.core.ServerLoginReply

interface GameService {
    @GET("games.json")
    fun listGames(@Header("X-API-TOKEN") token: String) : Call<List<ServerGameModel>>;

    @GET("games/{id}.json")
    fun game(@Header("X-API-TOKEN") token: String, @Path("id") id: String) : Call<ServerGameModel>

   // http://localhost:3000/games/1/reply
    @POST("games/{id}/reply.json")
    fun reply(@Header("X-API-TOKEN") token: String, @Path("id") id: String) : Call<ServerGamePostReply>;

    @POST("api_auth")
    fun login(@Query("email") username: String, @Query("password") password: String) : Call<ServerLoginReply>;
}