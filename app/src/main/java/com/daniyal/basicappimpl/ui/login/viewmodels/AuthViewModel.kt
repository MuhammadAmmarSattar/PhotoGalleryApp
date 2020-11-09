package com.daniyal.basicappimpl.ui.login.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.daniyal.basicappimpl.data.repository.photo.PhotoRepository
import com.daniyal.basicappimpl.ui.base.BaseViewModel

class AuthViewModel @ViewModelInject constructor(val photoRepository: PhotoRepository):BaseViewModel() {
}