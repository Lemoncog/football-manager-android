package uk.co.lemoncog.footballmanager.android.account

import android.content.Context
import android.content.SharedPreferences
import uk.co.lemoncog.footballmanager.core.AuthenticatedUser
import uk.co.lemoncog.footballmanager.core.DataWriter

fun getAccountSharedPrefs(context: Context) : SharedPreferences {
    return context.getSharedPreferences("USER_STORAGE", Context.MODE_PRIVATE);
}

class AuthenticatedUserWriter(val sharedPrefs: SharedPreferences) : DataWriter<AuthenticatedUser> {
    override fun write(value: AuthenticatedUser, success: (AuthenticatedUser) -> Unit) {
        if(sharedPrefs.edit().putString("username", value.email)
        .putString("token", value.token).commit()) {
            success(value);
        }
    }
}