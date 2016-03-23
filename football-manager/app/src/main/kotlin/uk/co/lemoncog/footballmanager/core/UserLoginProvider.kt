package uk.co.lemoncog.footballmanager.android

import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataProvider

class UserLoginProvider : DataProvider<AuthenticatedUser> {
    override fun get(success: (AuthenticatedUser) -> Unit, failure: () -> Unit) {
        success(AuthenticatedUser("e3409875d642bcab5afc8d3695644938"))
    }
}