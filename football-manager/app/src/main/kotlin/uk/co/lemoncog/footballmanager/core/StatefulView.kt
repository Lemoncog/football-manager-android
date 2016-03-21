package uk.co.lemoncog.footballmanager.core

interface StatefulView<T> {
    fun setAcceptClickedListener(clicked : () -> Unit);
    fun show(viewData: T);
}