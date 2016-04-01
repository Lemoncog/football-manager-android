package uk.co.lemoncog.footballmanager.android.detail

import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.androidcosofretrofit.GameModelDataProvider
import uk.co.lemoncog.footballmanager.core.*

class GameViewController(val gameId: Long, val authenticatedUser: AuthenticatedUser, val gamePresenter: GamePresenter) {
    val gameRequestController = GameRequestController(authenticatedUser);
    init {
        gamePresenter.actionListeners.add(object: ActionListener {
            override fun onTrigger() {
                gameRequestController.requestToPlay(gameId, {
                    userIsAllowedToPlay();
                }, {
                    userIsNotAllowedToPlay();
                });
            }
        })
    }

    private fun userIsNotAllowedToPlay() {

    }

    private fun userIsAllowedToPlay() {
        gamePresenter.refresh();
    }

    fun onResume() {
        gamePresenter.onReady();
    }

    fun onPause() {
    }

}