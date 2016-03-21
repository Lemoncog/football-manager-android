package uk.co.lemoncog.footballmanager.core

import java.util.*

//{"id":1,"name":"Ross play with me","description":"YAY","date":"2016-03-17T09:15:00.000Z","created_at":"2016-03-17T09:16:00.803Z","updated_at":"2016-03-17T09:16:00.803Z"}
data class GameModel(val id: Long, val name: String, val description: String, val date: Date, val createdAt : Date, val updatedAt: Date);
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
        success(GameModel(0, "Jessica", "JessicaDescr", Date(), Date(), Date()));
    }
}

