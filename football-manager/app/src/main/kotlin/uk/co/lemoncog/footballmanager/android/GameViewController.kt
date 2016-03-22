package uk.co.lemoncog.footballmanager.android

import android.view.ViewGroup
import uk.co.lemoncog.footballmanager.core.*

class GameViewController() {
    lateinit var gamePresenter: GamePresenter;
    val gameRequestController = GameRequestController(GameReplyDataProvider());

    fun attachView(viewGroup: ViewGroup) {
        gamePresenter = GamePresenter(GameView(viewGroup), GameModelDataProvider());

        gamePresenter.actionListeners.add(object: ActionListener {
            override fun onTrigger() {
                gameRequestController.requestToPlay({
                    userIsAllowedToPlay();
                }, {
                });
            }
        })
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