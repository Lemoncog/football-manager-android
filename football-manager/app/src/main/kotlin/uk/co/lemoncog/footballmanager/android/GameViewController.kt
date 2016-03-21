package uk.co.lemoncog.footballmanager.android

import android.view.ViewGroup
import android.widget.TextView
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.*

class GameViewController() {
    lateinit var gamePresenter: GamePresenter;
    val gameRequestController = GameRequestController(GameReplyDataProvider());

    class GameView(root: ViewGroup) : StatefulView<GameViewModel> {
        val title : TextView = root.findViewById(R.id.game_title) as TextView;
        val acceptView = root.findViewById(R.id.game_accept);
        var acceptClicked : (() -> Unit)? = null;

        init {
            acceptView.setOnClickListener {  acceptClicked?.invoke() }
        }

        override fun setAcceptClickedListener(clicked: () -> Unit) {
            acceptClicked = clicked;
        }

        override fun show(viewData: GameViewModel) {
            title.text = viewData.name;
        }
    }

    fun attachView(viewGroup: ViewGroup) {
        gamePresenter = GamePresenter(GameView(viewGroup), GameModelDataProvider());

        gamePresenter.actionListeners.add(object: ActionListener {
            override fun onTrigger() {
                gameRequestController.requestToPlay({}, {});
            }
        })

    }

    fun detachView() {
    }

    fun onResume() {
        gamePresenter.onReady();
    }

    fun onPause() {
    }

}