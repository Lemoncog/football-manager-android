package uk.co.footballmanager

import org.junit.Test
import org.junit.Assert.*
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataProvider
import uk.co.lemoncog.footballmanager.core.GameLoginController
import uk.co.lemoncog.footballmanager.core.GameLoginNavigation


class GameLoginControllerTest : GameLoginNavigation {

    var navigatedToGameScreen : Boolean = false;

    override fun navigateToGameScreen() {
        navigatedToGameScreen = true;
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