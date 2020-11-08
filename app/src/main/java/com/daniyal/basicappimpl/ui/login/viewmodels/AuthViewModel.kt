package com.daniyal.basicappimpl.ui.login.viewmodels

import android.app.Application
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.ui.base.BaseViewModel

class AuthViewModel(val photoRepository: PhotoRepository):BaseViewModel() {
}