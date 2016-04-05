package uk.co.footballmanager

import org.junit.Assert.*;
import org.junit.Test
import uk.co.lemoncog.footballmanager.core.LoginModel


interface LoginView {
    fun getUsername() : String;
    fun getPassword() : String;
}

class LoginPresenter(val loginView: LoginView) {

    var loginActionListener: ((LoginModel) -> Unit)? = null;

    fun onReady() {
    }

    fun loginPressed() {
        loginActionListener?.invoke(LoginModel(loginView.getUsername(), loginView.getPassword()));
    }
}

class LoginPresenterTest : LoginView {
    var stubUsername = "";
    var stubPassword = "";

    override fun getUsername(): String {
        return stubUsername;
    }

    override fun getPassword(): String {
        return stubPassword;
    }

    @Test
    @Throws(Exception::class)
    fun givenUserEntersUserNameAndPassword_WhenUserPressesEnter_ThenLoginAttemptEventIsEmitted() {
        stubUsername = "Jason";
        stubPassword = "jasonPassword";

        val loginPresenter = LoginPresenter(this);
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
        stubUsername = "anotherUsername";
        stubPassword = "anotherPassword";

        val loginPresenter = LoginPresenter(this);
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