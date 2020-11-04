package com.daniyal.basicappimpl.ui.home

import android.Manifest
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.RequestManager
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseAuthenticationActivity
import com.daniyal.basicappimpl.ui.home.viewmodels.MainViewModel
import com.daniyal.basicappimpl.utils.LocaleContainer
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseAuthenticationActivity() {

    @Inject
    lateinit var glide: RequestManager
    private val mainViewModel: MainViewModel by viewModels()

    override fun baseOnCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        _localeContainer.postValue(LocaleContainer.ARABIC)
        subscribeToObserver()

        runWithPermissions(Manifest.permission.CAMERA){
            //todo
        }

    }


    private fun subscribeToObserver() {
        mainViewModel.progressDialogController.observe(this) {
            if (it) {
                //Display Progress Bar
                customProgressDialog?.show()
            } else {
                //Hide Progress Bar
                customProgressDialog?.hide()
            }

        }
    }


}