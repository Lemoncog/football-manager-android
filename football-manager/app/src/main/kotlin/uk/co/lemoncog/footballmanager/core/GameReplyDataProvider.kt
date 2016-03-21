package uk.co.lemoncog.footballmanager.core


class GameReplyDataProvider : DataProvider<GameReply> {
    override fun get(success: (GameReply) -> Unit, failure: () -> Unit) {
        success(GameReply(0));
    }
}