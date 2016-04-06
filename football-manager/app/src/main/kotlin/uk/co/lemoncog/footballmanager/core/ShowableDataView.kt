package uk.co.lemoncog.footballmanager.core


interface ShowableDataView<T> {
    fun show(viewData: T);
    fun showError(throwable: Throwable);
}