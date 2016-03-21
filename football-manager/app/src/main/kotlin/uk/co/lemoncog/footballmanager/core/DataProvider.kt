package uk.co.lemoncog.footballmanager.core

interface DataProvider<T> {
    fun get(success: (T) -> Unit, failure: () -> Unit);
}