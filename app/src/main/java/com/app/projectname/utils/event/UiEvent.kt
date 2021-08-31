package com.app.projectname.utils.event

import androidx.navigation.NavDirections

sealed class UiEvent {
    class ShowLoader(val show: Boolean) : UiEvent()
    class ShowToast(val message: String) : UiEvent()
    class ShowSnackbar(val message: String, val action: (() -> Unit)? = null) : UiEvent()
    class ShowAlert(val title: String, val message: String) : UiEvent()
    class NavigateByDirections(val navDirections: NavDirections) : UiEvent()

}