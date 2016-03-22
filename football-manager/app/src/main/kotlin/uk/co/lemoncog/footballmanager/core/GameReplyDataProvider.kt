package uk.co.lemoncog.footballmanager.core

import uk.co.lemoncog.footballmanager.core.util.parseServerDate
import java.util.*


class GameReplyDataProvider : DataProvider<GameReply> {
    override fun get(success: (GameReply) -> Unit, failure: () -> Unit) {


        gameModel = GameModel(1,
                "Ross gimme an API to call plx plx",
                "YAY",
                parseServerDate("2016-03-17T09:15:00.000Z"),
                parseServerDate("2016-03-17T09:16:00.803Z"),
                parseServerDate("2016-03-17T09:16:00.803Z"), emptyArray());


        success(GameReply(0, "Timmeh", Date()));
    }
}