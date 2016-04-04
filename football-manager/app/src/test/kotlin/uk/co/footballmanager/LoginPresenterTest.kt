package uk.co.footballmanager

import org.junit.Assert.*;
import org.junit.Test
import uk.co.lemoncog.footballmanager.core.LoginModel


class LoginPresenterTest {

    class LoginPresenter {

        var loginActionListener: ((LoginModel) -> Unit)? = null;

        fun onReady() {
        }

        fun loginPressed() {
            loginActionListener?.invoke(LoginModel("Jason", "jasonPassword"));
        }
    }

    @Test
    @Throws(Exception::class)
    fun givenUserEntersUserNameAndPassword_WhenUserPressesEnter_ThenLoginAttemptEventIsEmitted() {
        val loginPresenter = LoginPresenter();
        var loginEventEmitted = false;

        loginPresenter.loginActionListener = { loginModel: LoginModel ->

            assertEquals(loginModel.username, "Jason");
            assertEquals(loginModel.password, "jasonPassword");


            loginEventEmitted = true;
        }
        loginPresenter.loginPressed();

        assertTrue("Expected LoginEvent to be emitted", loginEventEmitted);
    }

    @Test
    @Throws(Exception::class)
    fun givenUserEntersUserNameAndPassword_WhenUserPressesEnter_ThenLoginAttemptEventIsEmittedAndDataValidated() {
        val loginPresenter = LoginPresenter();
        var loginEventEmitted = false;

        loginPresenter.loginActionListener = { loginModel: LoginModel ->

            assertEquals(loginModel.username, "anotherUsername");
            assertEquals(loginModel.password, "anotherPassword");


            loginEventEmitted = true;
        }
        loginPresenter.loginPressed();

        assertTrue("Expected LoginEvent to be emitted", loginEventEmitted);
    }
}