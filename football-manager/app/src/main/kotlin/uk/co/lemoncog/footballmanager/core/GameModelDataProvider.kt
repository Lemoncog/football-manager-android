package uk.co.lemoncog.footballmanager.core

import uk.co.lemoncog.footballmanager.core.util.parseServerDate

class GameModelDataProvider : DataProvider<GameModel> {
    override fun get(success: (GameModel) -> Unit, failure: () -> Unit) {
        val gameModel = GameModel(1, "Ross play with me", "YAY", parseServerDate("2016-03-17T09:15:00.000Z"),  parseServerDate("2016-03-17T09:16:00.803Z"),  parseServerDate("2016-03-17T09:16:00.803Z"));

        success(gameModel);
    }
}