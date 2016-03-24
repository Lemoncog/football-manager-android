package uk.co.lemoncog.footballmanager.core

interface StatefulView<T> : ShowableDataView<T> {
    fun setAcceptClickedListener(clicked : () -> Unit);
    fun setViewClickedListener(clicked: () -> Unit)
}