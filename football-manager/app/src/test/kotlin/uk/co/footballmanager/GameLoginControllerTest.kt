package uk.co.footballmanager

import org.junit.Test
import org.junit.Assert.*
import uk.co.lemoncog.footballmanager.core.DataProvider

interface GameLoginNavigation {
    fun navigateToGameScreen();
}

class GameLoginControllerTest : GameLoginNavigation {

    var navigatedToGameScreen : Boolean = false;

    override fun navigateToGameScreen() {
        navigatedToGameScreen = true;
    }

    //testfootballaccount@test.com
    //test12345
    data class User(val userName: String,val password: String);
    data class AuthenticatedUser(val token: String);

    class GameLoginController(val userProvider: DataProvider<AuthenticatedUser>, val gameLoginNavigation: GameLoginNavigation) {
        fun onReady() {
            userProvider.get({ gameLoginNavigation.navigateToGameScreen() }, {})
        }
    }

    fun createSuccessfulLoginProvider(): DataProvider<AuthenticatedUser> {
        return object: DataProvider<AuthenticatedUser> {
            override fun get(success: (AuthenticatedUser) -> Unit, failure: () -> Unit) {
                success(AuthenticatedUser("someTokenOkay"))
            }
        }
    }

    @Test
    fun givenUserAlreadyHasAccount_NavigateToGameScreen() {
        val gameLoginController = GameLoginController(createSuccessfulLoginProvider(), this)

        gameLoginController.onReady();

        assertTrue(navigatedToGameScreen);
    }

}