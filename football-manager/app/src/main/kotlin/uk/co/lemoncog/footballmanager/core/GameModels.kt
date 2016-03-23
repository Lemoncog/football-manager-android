package uk.co.lemoncog.footballmanager.core

import java.util.*

data class GameListModel(val games: Array<GameModel>);
data class GameModel(val id: Long, val name: String, val description: String, val date: Date, val createdAt: Date, val updatedAt: Date, val replies: Array<GameReply>);
data class GameReply(val id: Long, val name: String, val createdAt: Date);

data class GameViewModel(val name: String, val description: String, val dateWhen: String, val userInGame: Boolean, val replyCount: Int);
data class GameListViewModel(val games: Array<GameViewModel>);