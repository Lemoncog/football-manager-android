package uk.co.lemoncog.footballmanager.android

import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.androidcosofretrofit.GameModelDataProvider
import uk.co.lemoncog.footballmanager.core.*

class GameViewController(val gameId: Long, val authenticatedUser: AuthenticatedUser) {
    lateinit var gamePresenter: GamePresenter;
    val gameRequestController = GameRequestController(authenticatedUser);

    fun attachView(viewGroup: ViewGroup) {
        gamePresenter = GamePresenter(GameView(viewGroup), GameModelDataProvider(gameId, authenticatedUser));

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

    fun detachView() {
    }

    fun onResume() {
        gamePresenter.onReady();
    }

    fun onPause() {
    }

}