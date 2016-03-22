package uk.co.lemoncog.footballmanager.core

import uk.co.lemoncog.footballmanager.core.util.parseServerDate

val firstGameReply = GameReply(1, "ross.wilson@bbc.co.uk", parseServerDate("2016-03-21T13:10:38.996Z"));
val secondGameReply =GameReply(2, "lemoncog@gmail.co.uk", parseServerDate("2016-03-21T13:12:09.197Z"));
var gameModel = GameModel(1,
        "Ross play with me",
        "YAY",
        parseServerDate("2016-03-17T09:15:00.000Z"),
        parseServerDate("2016-03-17T09:16:00.803Z"),
        parseServerDate("2016-03-17T09:16:00.803Z"),
        arrayOf(firstGameReply, secondGameReply));

class GameModelDataProvider : DataProvider<GameModel> {
    override fun get(success: (GameModel) -> Unit, failure: () -> Unit) {
        success(gameModel);
    }
}