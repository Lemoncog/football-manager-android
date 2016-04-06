package uk.co.lemoncog.footballmanager.android.list

import uk.co.lemoncog.footballmanager.core.*
import uk.co.lemoncog.footballmanager.core.adapters.convertGameModelToViewModel

class GameListPresenter(val view: ShowableDataView<GameListViewModel>,val authenticatedUser: AuthenticatedUser, val dataProvider: DataProvider<GameListModel, Throwable>) {

    fun onReady() {
        refresh();
    }

    private fun gameModelToGameListViewModel(gameListModel: GameListModel) : GameListViewModel {

        val games = mutableListOf<GameViewModel>();

        for(gameModel: GameModel in gameListModel.games) {
            games.add(convertGameModelToViewModel(gameModel, authenticatedUser));
        }

        return GameListViewModel(games.toTypedArray());
    }

    fun refresh() {
        var success = { gameModel: GameListModel ->  view.show(gameModelToGameListViewModel(gameModel)) }
        var failure = { throwable: Throwable -> view.showError(throwable); };

        dataProvider.get(success, failure);
    }
}