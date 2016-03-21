package uk.co.lemoncog.footballmanager

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*



data class GameModel(val name: String);
data class GameViewModel(val name: String);

interface StatefulView<T> {
    fun show(viewData: T);
}

class GamePresenter(val view: StatefulView<GameViewModel>,val dataProvider: DataProvider<GameModel>) {
    fun onReady() {
        var success = { gameModel: GameModel ->  view.show(gameModelTogameViewModel(gameModel)) }
        var failure = {};

        dataProvider.get(success, failure);
    }

    fun gameModelTogameViewModel(gameModel: GameModel) : GameViewModel {
        return GameViewModel("Sam");
    }
}

interface DataProvider<T> {
    fun get(success: (T) -> Unit, failure: () -> Unit);
}

class TestDataProvider() : DataProvider<GameModel> {
    override fun get(success: (GameModel) -> Unit, failure: () -> Unit) {
        success(GameModel("Jessica"));
    }
}


class GamePresenterTest : StatefulView<GameViewModel> {

    var showCalled: Boolean = false;

    override fun show(viewData: GameViewModel) {
        showCalled = true;
    }

    @Test
    @Throws(Exception::class)
    fun givenAGameIsAvailable_ThenDisplayLatestGame() {
        val dataProvider = TestDataProvider();
        val presenter = GamePresenter(this, dataProvider);

        presenter.onReady();

        assertTrue("Expected show to be called", showCalled);
    }
}