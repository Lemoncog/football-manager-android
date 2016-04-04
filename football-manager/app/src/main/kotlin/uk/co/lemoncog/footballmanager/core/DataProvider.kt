package uk.co.lemoncog.footballmanager.core

interface DataProvider<T, F> {
    fun get(success: (T) -> Unit, failure: (F) -> Unit);
}