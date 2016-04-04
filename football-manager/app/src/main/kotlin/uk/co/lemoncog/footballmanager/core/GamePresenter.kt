package uk.co.lemoncog.footballmanager.core

import uk.co.lemoncog.footballmanager.core.adapters.convertGameModelToViewModel


class GamePresenter(val view: StatefulView<GameViewModel>, val authenticatedUser: AuthenticatedUser, val dataProvider: DataProvider<GameModel, Throwable>) {
    val actionListeners = mutableListOf<ActionListener>();

    init {
        view.setAcceptClickedListener { onAcceptClicked() }
    }

    fun onReady() {
        refresh();
    }

    fun onAcceptClicked() {
        for (listener in actionListeners) {
            listener.onTrigger();
        }
    }

    fun refresh() {
        var success = { gameModel: GameModel ->  view.show(convertGameModelToViewModel(gameModel, authenticatedUser)) }
        var failure = { throwable : Throwable ->};

        dataProvider.get(success, failure);
    }
}