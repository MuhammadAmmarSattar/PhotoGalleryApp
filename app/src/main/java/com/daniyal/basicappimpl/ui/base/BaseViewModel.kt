package com.daniyal.basicappimpl.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniyal.basicappimpl.utils.event.Event
import com.daniyal.basicappimpl.utils.event.UiEvent

abstract class BaseViewModel() : ViewModel() {
    private val _uiEventsLiveData = MutableLiveData<Event<UiEvent>>()
    val uiEvents: LiveData<Event<UiEvent>> = _uiEventsLiveData


    fun showLoader(show: Boolean) {
        _uiEventsLiveData.value = Event(UiEvent.ShowLoader(show))
    }

    fun showAlert(title: String = "Alert", message: String) {
        _uiEventsLiveData.value = Event(UiEvent.ShowAlert(title = title, message = message))
    }

    fun showToast(message: String) {
        _uiEventsLiveData.value = Event(UiEvent.ShowToast(message))

    }
}