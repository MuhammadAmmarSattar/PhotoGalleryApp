package com.daniyal.basicappimpl.ui.base

import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.daniyal.basicappimpl.infrastructure.ApplicationEntry
import com.daniyal.basicappimpl.utils.ProgressDialog
import com.daniyal.basicappimpl.utils.event.EventUtilFunctions.showAlert
import com.daniyal.basicappimpl.utils.event.EventUtilFunctions.showLoader
import com.daniyal.basicappimpl.utils.event.EventUtilFunctions.showToast
import com.daniyal.basicappimpl.utils.event.UiEvent
import com.squareup.otto.Bus


abstract class BaseActivity : LocalizationActivity() {
    lateinit var applicationEntry: ApplicationEntry
    protected lateinit var bus: Bus
    protected var isBusRegistered: Boolean = false
    private var customProgressDialog: ProgressDialog? = null


//    var _localeContainer: MutableLiveData<LocaleContainer> = MutableLiveData()
//    private var localeContainer: LiveData<LocaleContainer> = _localeContainer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationEntry = application as ApplicationEntry
        bus = applicationEntry.bus
        bus.register(this)
        isBusRegistered = true
//        subscribeToObserver()
        customProgressDialog = ProgressDialog(this)
//        _localeContainer.postValue(LocaleContainer.ENGLISH)
    }

    // for array.length
//    private fun subscribeToObserver() {
//        localeContainer.observe(this) { locale ->
//            when (locale) {
//                LocaleContainer.ARABIC -> {
//                    Toast.makeText(this, LocaleContainer.ARABIC.name, Toast.LENGTH_LONG).show()
//                }
//                LocaleContainer.URDU -> {
//                    Toast.makeText(this, LocaleContainer.URDU.name, Toast.LENGTH_LONG).show()
//                }
//                else -> {
//                    //Default Locale Will Be English
//                    Toast.makeText(this, LocaleContainer.ENGLISH.name, Toast.LENGTH_LONG).show()
//
//                }
//            }
//        }
//
//    }

    fun subscribeUiEvents(baseViewModel: BaseViewModel) {
        baseViewModel.uiEvents.observe(this, {
            it.getContentIfNotHandled()
                ?.let { event ->
                    when (event) {
                        is UiEvent.ShowAlert -> {
                            showAlert(event.message)
                        }
                        is UiEvent.ShowToast -> {
                            showToast(event.message,this)
                        }
                        is UiEvent.ShowLoader -> {
                            showLoader(event.show,customProgressDialog)
                        }
                    }
                }
        })
    }


    override fun onDestroy() {
        super.onDestroy()

        if (customProgressDialog != null) {
            customProgressDialog = null
        }

        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }

    }

    override fun finish() {
        super.finish()
        if (customProgressDialog != null) {
            customProgressDialog = null
        }
        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }
    }
}