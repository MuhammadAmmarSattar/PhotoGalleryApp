package com.daniyal.basicappimpl.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniyal.basicappimpl.utils.event.Event
import com.daniyal.basicappimpl.utils.event.SingleLiveEvent
import com.daniyal.basicappimpl.utils.event.UiEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val uiEventsLiveData = MutableLiveData<Event<UiEvent>>()

    val uiEvents: LiveData<Event<UiEvent>>
        get() = uiEventsLiveData


    fun showLoader(show: Boolean) {
        uiEventsLiveData.value = Event(UiEvent.ShowLoader(show))
    }

    fun showAlert(title: String = "Alert", message: String) {
        uiEventsLiveData.value = Event(UiEvent.ShowAlert(title = title, message = message))
    }

    fun showToast(message: String) {
        uiEventsLiveData.value = Event(UiEvent.ShowToast(message))

    }
}