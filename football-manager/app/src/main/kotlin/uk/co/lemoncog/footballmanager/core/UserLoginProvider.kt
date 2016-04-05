package uk.co.lemoncog.footballmanager.android

import android.content.Context
import android.content.SharedPreferences
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataProvider
import uk.co.lemoncog.footballmanager.core.LoginFailure

class UserLoginProvider(val sharedPreferences: SharedPreferences) : DataProvider<AuthenticatedUser, LoginFailure> {
    override fun get(success: (AuthenticatedUser) -> Unit, failure: (LoginFailure) -> Unit) {

        if(sharedPreferences.contains("username")) {

            val username = sharedPreferences.getString("username", "");
            val token = sharedPreferences.getString("token", "");

            success(AuthenticatedUser(token, username));
        } else {
            failure(LoginFailure("no token"));
        }
    }
}