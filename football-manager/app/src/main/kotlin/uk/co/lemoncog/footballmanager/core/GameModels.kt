package uk.co.lemoncog.footballmanager.core

import java.util.*

//Server models
class ServerGameListModel(var games: List<ServerGameListSingleModel>);
data class ServerGameListSingleModel(val id: Long, val name: String, val description: String, val date: String, val created_at: String, val updated_at: String, val replies_count: Int);
data class ServerGameModel(val id: Long, val name: String, val description: String, val date: String, val created_at: String, val updated_at: String, val replies : Array<ServerGameReply>);
data class ServerGameReply(val id: Long, val user: String, val created_at: String, val updated_at: String);

//Buisness Models
data class GameListModel(val games: Array<GameListSingleModel>);
data class GameListSingleModel(val id: Long, val name: String, val description: String, val date: Date, val created_at: Date, val updated_at: Date, val replies_count: Int);
data class GameModel(val id: Long, val name: String, val description: String, val date: Date, val created_at: Date, val updated_at: Date, val replies : Array<GameReply>);
data class GameReply(val id: Long, val user: String, val created_at: Date);

//testfootballaccount@test.com
//test12345
data class User(val userName: String,val password: String);
data class AuthenticatedUser(val token: String);



//UI Models
data class GameViewModel(val name: String, val description: String, val dateWhen: String, val userInGame: Boolean, val replyCount: Int);
data class GameListViewModel(val games: Array<GameViewModel>);
