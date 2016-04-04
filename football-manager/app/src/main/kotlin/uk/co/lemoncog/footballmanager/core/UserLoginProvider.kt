package uk.co.lemoncog.footballmanager.android

import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataProvider
import uk.co.lemoncog.footballmanager.core.LoginFailure

class UserLoginProvider : DataProvider<AuthenticatedUser, LoginFailure> {
    override fun get(success: (AuthenticatedUser) -> Unit, failure: (LoginFailure) -> Unit) {
        success(AuthenticatedUser("e3409875d642bcab5afc8d3695644938", "testfootballaccount@test.com"))
    }
}