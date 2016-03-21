package uk.co.lemoncog.footballmanager.core

interface StatefulView<T> {
    fun show(viewData: T);
}