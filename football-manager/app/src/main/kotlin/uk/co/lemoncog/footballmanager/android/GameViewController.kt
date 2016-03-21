package uk.co.lemoncog.footballmanager.android

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import uk.co.lemoncog.footballmanager.R
import uk.co.lemoncog.footballmanager.core.GameModelDataProvider
import uk.co.lemoncog.footballmanager.core.GamePresenter
import uk.co.lemoncog.footballmanager.core.GameViewModel
import uk.co.lemoncog.footballmanager.core.StatefulView

class GameViewController() {

    lateinit var gamePresenter: GamePresenter;

    class GameView(root: ViewGroup) : StatefulView<GameViewModel> {
        val title : TextView = root.findViewById(R.id.game_title) as TextView;

        override fun show(viewData: GameViewModel) {
            title.text = viewData.name;
        }
    }


    fun attachView(viewGroup: ViewGroup) {
        gamePresenter = GamePresenter(GameView(viewGroup), GameModelDataProvider());

    }

    fun detachView() {
    }

    fun onResume() {
        gamePresenter.onReady();
    }

    fun onPause() {
    }

}