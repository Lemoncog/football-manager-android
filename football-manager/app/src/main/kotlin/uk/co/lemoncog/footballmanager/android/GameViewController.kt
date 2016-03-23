package uk.co.lemoncog.footballmanager.android

import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.core.*

class GameViewController() {
    lateinit var gamePresenter: GamePresenter;
    val gameRequestController = null;//GameRequestController(GameReplyDataProvider(1, authenticatedUser));

    fun attachView(viewGroup: ViewGroup) {
        gamePresenter = GamePresenter(GameView(viewGroup), GameModelDataProvider());

        gamePresenter.actionListeners.add(object: ActionListener {
            override fun onTrigger() {
//                gameRequestController.requestToPlay({
//                    userIsAllowedToPlay();
//                }, {
//                    userIsNotAllowedToPlay();
//                });
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