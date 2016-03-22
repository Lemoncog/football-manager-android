package uk.co.lemoncog.footballmanager.core

import java.text.SimpleDateFormat

class GamePresenter(val view: StatefulView<GameViewModel>, val dataProvider: DataProvider<GameModel>) {
    val actionListeners = mutableListOf<ActionListener>();

    init {
        view.setAcceptClickedListener { onAcceptClicked() }
    }

    fun onReady() {
        refresh();
    }

    private fun gameModelTogameViewModel(gameModel: GameModel) : GameViewModel {
        val sdf = SimpleDateFormat("EEE, d MMM yyyy, hh:mm aaa");
        val prettyDate = sdf.format(gameModel.date);

        return GameViewModel(gameModel.name, gameModel.description, prettyDate, gameModel.replies.count());
    }

    fun onAcceptClicked() {
        for (listener in actionListeners) {
            listener.onTrigger();
        }
    }

    fun refresh() {
        var success = { gameModel: GameModel ->  view.show(gameModelTogameViewModel(gameModel)) }
        var failure = {};

        dataProvider.get(success, failure);
    }
}