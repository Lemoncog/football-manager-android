package uk.co.lemoncog.footballmanager.core

import uk.co.lemoncog.footballmanager.androidcosofretrofit.GameReplyDataProvider

class GameRequestController(val authenticatedUser: AuthenticatedUser) {
    fun requestToPlay(id: Long, success: (GameRequestReply) -> Unit, failure: (Throwable) -> Unit) {
            val replyDataProvider = GameReplyDataProvider(id, authenticatedUser);

            replyDataProvider.get({ gameRequestReply : GameRequestReply -> success(gameRequestReply) }, { throwable : Throwable  -> failure(throwable) });
    }
}