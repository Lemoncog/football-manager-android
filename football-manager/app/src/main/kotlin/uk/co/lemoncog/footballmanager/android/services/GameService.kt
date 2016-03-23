package uk.co.lemoncog.footballmanager.android.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import uk.co.lemoncog.footballmanager.core.ServerGameListSingleModel

interface GameService {
    @GET("games.json")
    fun listGames(@Header("X-API-TOKEN") token: String) : Call<List<ServerGameListSingleModel>>;

   // http://localhost:3000/games/1/reply
    @POST("games/{id}/reply")
    fun reply(@Header("X-API-TOKEN") token: String, @Path("id") id: String) : Call<Unit>;
}