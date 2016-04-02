package uk.co.lemoncog.footballmanager.android.list

import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.adapters.convertSingleGameModelToViewModel

class GameListPresenter(val view: ShowableDataView<GameListViewModel>, val dataProvider: DataProvider<GameListModel>) {

    fun onReady() {
        refresh();
    }

    private fun gameModelToGameListViewModel(gameListModel: GameListModel) : GameListViewModel {

        val games = mutableListOf<GameViewModel>();

        for(gameModel: GameModel in gameListModel.games) {
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