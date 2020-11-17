package com.daniyal.basicappimpl.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniyal.basicappimpl.utils.event.Event
import com.daniyal.basicappimpl.utils.event.UiEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiEventsLiveData = MutableLiveData<Event<UiEvent>>()
    val uiEvents: LiveData<Event<UiEvent>> = _uiEventsLiveData


    fun showLoader(show: Boolean) {
        _uiEventsLiveData.postValue(Event(UiEvent.ShowLoader(show)))
    }

    fun showAlert(title: String = "Alert", message: String) {
        _uiEventsLiveData.postValue(Event(UiEvent.ShowAlert(title = title, message = message)))
    }

    fun showToast(message: String) {
//        _uiEventsLiveData.value = Event(UiEvent.ShowToast(message))
        _uiEventsLiveData.postValue(Event(UiEvent.ShowToast(message)))

    }
}