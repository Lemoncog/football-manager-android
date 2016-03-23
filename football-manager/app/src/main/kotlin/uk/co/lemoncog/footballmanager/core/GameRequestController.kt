package uk.co.lemoncog.footballmanager.core

import uk.co.lemoncog.footballmanager.android.UserLoginProvider

class GameRequestController(val userProvider: UserLoginProvider) {
    fun requestToPlay(id: Long, success: (GameReply) -> Unit, failure: () -> Unit) {

        userProvider.get({ authenticatedUser: AuthenticatedUser ->
            val replyDataProvider = GameReplyDataProvider(id, authenticatedUser);

            replyDataProvider.get({ gameReply : GameReply -> success(gameReply) }, { failure() });
        },{ });
    }
}