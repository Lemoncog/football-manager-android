package uk.co.lemoncog.footballmanager.core

class GameRequestController(val dataProvider: DataProvider<GameReply>) {
    fun requestToPlay(success: (GameReply) -> Unit, failure: () -> Unit) {
        dataProvider.get({ gameReply : GameReply -> success(gameReply) }, { failure() });
    }
}