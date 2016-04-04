package uk.co.lemoncog.footballmanager.core

interface GameLoginNavigation {
    fun navigateToGameScreen();
    fun navigateToLoginScreen();
}

class GameLoginController(val userProvider: DataProvider<AuthenticatedUser, LoginFailure>, val gameLoginNavigation: GameLoginNavigation) {
    fun onReady() {
        userProvider.get({ gameLoginNavigation.navigateToGameScreen() }, { gameLoginNavigation.navigateToLoginScreen() })
    }
}