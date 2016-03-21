package uk.co.lemoncog.footballmanager.core

import java.util.*

data class GameModel(val id: Long, val name: String, val description: String, val date: Date, val createdAt : Date, val updatedAt: Date);
data class GameViewModel(val name: String);
data class GameReply(val id: Int);
