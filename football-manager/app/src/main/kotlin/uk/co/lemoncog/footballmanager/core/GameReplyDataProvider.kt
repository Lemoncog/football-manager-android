package uk.co.lemoncog.footballmanager.core

import java.util.*


class GameReplyDataProvider : DataProvider<GameReply> {
    override fun get(success: (GameReply) -> Unit, failure: () -> Unit) {
        success(GameReply(0, "Timmeh", Date()));
    }
}