package uk.co.lemoncog.footballmanager.android

import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.util.convertSingleGameModelToViewModel

class GameListPresenter(val view: StatefulView<GameListViewModel>, val dataProvider: DataProvider<GameListModel>) {

    fun onReady() {
        refresh();
    }

    private fun gameModelToGameListViewModel(gameListModel: GameListModel) : GameListViewModel {

        val games = mutableListOf<GameViewModel>();

        for(gameModel: GameListSingleModel in gameListModel.games) {
            games.add(convertSingleGameModelToViewModel(gameModel));
        }

        return GameListViewModel(games.toTypedArray());
    }

    fun refresh() {
        var success = { gameModel: GameListModel ->  view.show(gameModelToGameListViewModel(gameModel)) }
        var failure = {};

        dataProvider.get(success, failure);
    }
}