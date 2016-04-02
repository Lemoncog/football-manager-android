package uk.co.lemoncog.footballmanager.core.adapters

import uk.co.lemoncog.footballmanager.core.*
import java.text.SimpleDateFormat
import java.util.*


fun serverGameListModelToGameListModel(serverModelList: List<ServerGameModel>) : GameListModel {
    val gameList = mutableListOf<GameModel>();
    for(serverGame in serverModelList) {
        gameList.add(serverGameModelToGameModel(serverGame));
    }

    return GameListModel(gameList.toTypedArray());
}

fun serverGameModelToGameModel(serverGame: ServerGameModel) : GameModel {
    val replies = mutableListOf<GameReply>()
    for(reply in serverGame.replies) {
        replies.add(GameReply(reply.id, reply.user, parseServerDate(reply.created_at)));
    }

    return GameModel(serverGame.id, serverGame.name, serverGame.description, parseServerDate(serverGame.date), parseServerDate(serverGame.created_at), parseServerDate(serverGame.updated_at), replies.toTypedArray())
}

fun parseServerDate(stringDate: String) : Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    return dateFormat.parse(stringDate);
}

fun convertGameModelToViewModel(gameModel: GameModel, user: AuthenticatedUser) : GameViewModel {
    val sdf = SimpleDateFormat("EEE, d MMM yyyy, hh:mm aaa");
    val prettyDate = sdf.format(gameModel.date);

    val inGame = gameModel.replies.filter { it.user == user.email }.count() > 0;

    return GameViewModel(gameModel.id, gameModel.name, gameModel.description, prettyDate, inGame, gameModel.replies.count());
}