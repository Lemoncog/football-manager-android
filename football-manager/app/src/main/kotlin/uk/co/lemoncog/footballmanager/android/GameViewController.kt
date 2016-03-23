package uk.co.lemoncog.footballmanager.android

import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.core.*

class GameViewController(val gameId: Long) {
    lateinit var gamePresenter: GamePresenter;
    val gameRequestController = GameRequestController(UserLoginProvider());

    fun attachView(viewGroup: ViewGroup) {
        gamePresenter = GamePresenter(GameView(viewGroup), GameModelDataProvider());

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