package uk.co.lemoncog.footballmanager.core

import uk.co.lemoncog.footballmanager.androidcosofretrofit.GameReplyDataProvider

class GameRequestController(val authenticatedUser: AuthenticatedUser) {
    fun requestToPlay(id: Long, success: (GameReply) -> Unit, failure: () -> Unit) {
            val replyDataProvider = GameReplyDataProvider(id, authenticatedUser);

            replyDataProvider.get({ gameReply : GameReply -> success(gameReply) }, { failure() });
    }
}