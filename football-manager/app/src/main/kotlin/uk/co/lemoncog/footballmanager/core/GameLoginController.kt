package uk.co.lemoncog.footballmanager.core

interface GameLoginNavigation {
    fun navigateToGameScreen();
}

class GameLoginController(val userProvider: DataProvider<AuthenticatedUser>, val gameLoginNavigation: GameLoginNavigation) {
    fun onReady() {
        userProvider.get({ gameLoginNavigation.navigateToGameScreen() }, {})
    }
}