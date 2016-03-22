package uk.co.lemoncog.footballmanager.core

import uk.co.lemoncog.footballmanager.core.util.convertGameModelToViewModel

class GamePresenter(val view: StatefulView<GameViewModel>, val dataProvider: DataProvider<GameModel>) {
    val actionListeners = mutableListOf<ActionListener>();

    init {
        view.setAcceptClickedListener { onAcceptClicked() }
    }

    fun onReady() {
        refresh();
    }

    private fun gameModelTogameViewModel(gameModel: GameModel) : GameViewModel {
       return convertGameModelToViewModel(gameModel);
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