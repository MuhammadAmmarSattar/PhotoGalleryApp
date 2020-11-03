package com.daniyal.basicappimpl.data

// used among repositories, interactors and viewmodels so it shouldn't be a part of data package
sealed class Result<out R> {
    data class Success<out T>(val data: T, val message: String = "Request Success") : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}