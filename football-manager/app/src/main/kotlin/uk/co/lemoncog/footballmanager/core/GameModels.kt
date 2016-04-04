package uk.co.lemoncog.footballmanager.core

import java.util.*

//Server models
class ServerGameListModel(var games: List<ServerGameModel>);
//data class ServerGameListSingleModel(val id: Long, val name: String, val description: String, val date: String, val created_at: String, val updated_at: String, val replies: Array<ServerGameReply>);
data class ServerGameModel(val id: Long, val name: String, val description: String, val date: String, val created_at: String, val updated_at: String, val replies : Array<ServerGameReply>);
data class ServerGameReply(val id: Long, val user: String, val created_at: String, val updated_at: String);
data class ServerGamePostReply(val status: String);

//Buisness Models
data class GameListModel(val games: Array<GameModel>);
//data class GameListSingleModel(val id: Long, val name: String, val description: String, val date: Date, val created_at: Date, val updated_at: Date, val replies: Array<GameReply>);
data class GameModel(val id: Long, val name: String, val description: String, val date: Date, val created_at: Date, val updated_at: Date, val replies : Array<GameReply>);
data class GameReply(val id: Long, val user: String, val created_at: Date);
data class GameRequestReply(val status: String);

//testfootballaccount@test.com
//test12345
data class User(val userName: String,val password: String);
data class AuthenticatedUser(val token: String, val email : String);
data class LoginFailure(val reason: String);

val FailureNoAccount = "FAILURE_NO_ACCOUNT";



//UI Models
data class GameViewModel(val id: Long, val name: String, val description: String, val dateWhen: String, val userInGame: Boolean, val replyCount: Int);
data class GameListViewModel(val games: Array<GameViewModel>);
