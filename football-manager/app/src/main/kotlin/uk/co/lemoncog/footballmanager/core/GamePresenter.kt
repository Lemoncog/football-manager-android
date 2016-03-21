package uk.co.lemoncog.footballmanager.core

class GamePresenter(val view: StatefulView<GameViewModel>, val dataProvider: DataProvider<GameModel>) {
    val actionListeners = mutableListOf<ActionListener>();

    fun onReady() {
        var success = { gameModel: GameModel ->  view.show(gameModelTogameViewModel(gameModel)) }
        var failure = {};

        dataProvider.get(success, failure);
    }

    fun gameModelTogameViewModel(gameModel: GameModel) : GameViewModel {
        return GameViewModel("Sam");
    }

    fun onAcceptClicked() {
        for (listener in actionListeners) {
            listener.onTrigger();
        }
    }
}