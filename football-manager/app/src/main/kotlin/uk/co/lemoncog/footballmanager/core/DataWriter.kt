package uk.co.lemoncog.footballmanager.core

interface DataWriter<T> {
    fun write(value : T, success: (T) -> Unit);
}