package com.app.projectname.data

import com.app.projectname.utils.Constants
import okhttp3.ResponseBody

// used among repositories, interactors and viewmodels so it shouldn't be a part of data package
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val errorMessage: String = Constants.Error.SOMETHING_WENT_WRONG
    ) : Result<Nothing>()
}