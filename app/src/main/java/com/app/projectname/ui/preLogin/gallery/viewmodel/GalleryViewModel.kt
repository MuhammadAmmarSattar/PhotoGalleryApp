package com.app.projectname.ui.preLogin.gallery.viewmodel

import android.app.Application
import com.app.projectname.R
import com.app.projectname.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(application: Application):BaseViewModel(application) {
    val tabTitles = mutableListOf(
        R.string.album,
        R.string.favourite
    )
}