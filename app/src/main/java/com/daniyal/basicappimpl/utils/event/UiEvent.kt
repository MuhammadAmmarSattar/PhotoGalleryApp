package com.daniyal.basicappimpl.utils.event

sealed class UiEvent {
    class ShowLoader(val show: Boolean) : UiEvent()
    class ShowToast(val message: String) : UiEvent()
    class ShowAlert(val title: String, val message: String) : UiEvent()
}