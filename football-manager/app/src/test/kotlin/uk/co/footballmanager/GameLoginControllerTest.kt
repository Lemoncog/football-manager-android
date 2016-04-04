package uk.co.footballmanager

import org.junit.Test
import org.junit.Assert.*
import uk.co.lemoncog.footballmanager.core.*


class GameLoginControllerTest : GameLoginNavigation {

    var navigatedToGameScreen : Boolean = false;
    var navigatedToLoginScreen : Boolean = false;

    override fun navigateToGameScreen() {
        navigatedToGameScreen = true;
    }

    fun createSuccessfulLoginProvider(): DataProvider<AuthenticatedUser, LoginFailure> {
        return object: DataProvider<AuthenticatedUser, LoginFailure> {
            override fun get(success: (AuthenticatedUser) -> Unit, failure: (LoginFailure) -> Unit) {
                success(AuthenticatedUser("someTokenOkay", "someemail@email.com"))
            }
        }
    }

    fun createFailureLoginProvider(): DataProvider<AuthenticatedUser, LoginFailure> {
        return object: DataProvider<AuthenticatedUser, LoginFailure> {
            override fun get(success: (AuthenticatedUser) -> Unit, failure: (LoginFailure) -> Unit) {
                failure(LoginFailure(FailureNoAccount));
            }
        }
    }

    @Test
    fun givenUserAlreadyHasAccount_NavigateToGameScreen() {
        val gameLoginController = GameLoginController(createSuccessfulLoginProvider(), this)

        gameLoginController.onReady();

        assertTrue(navigatedToGameScreen);
    }

    @Test
    fun givenUserHasNoAccount_NavigateToLoginScreen() {
        val gameLoginController = GameLoginController(createFailureLoginProvider(), this);

        gameLoginController.onReady();

        assertTrue(navigatedToLoginScreen)
    }

}