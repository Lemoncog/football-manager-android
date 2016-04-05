package uk.co.lemoncog.footballmanager.core.account

interface LoginView {
    fun setLoginClickedListener(clicked: () -> Unit);
    fun getUsername() : String;
    fun getPassword() : String;
}