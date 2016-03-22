package uk.co.lemoncog.footballmanager.core

class GamePresenter(val view: StatefulView<GameViewModel>, val dataProvider: DataProvider<GameModel>) {
    val actionListeners = mutableListOf<ActionListener>();

    init {
        view.setAcceptClickedListener { onAcceptClicked() }
    }

    fun onReady() {
        refresh();
    }

    private fun gameModelTogameViewModel(gameModel: GameModel) : GameViewModel {
        return GameViewModel("Sam");
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