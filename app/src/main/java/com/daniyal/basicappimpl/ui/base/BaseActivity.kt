package com.daniyal.basicappimpl.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.daniyal.basicappimpl.infrastructure.ApplicationEntry
import com.daniyal.basicappimpl.utils.LocaleContainer
import com.daniyal.basicappimpl.utils.ProgressDialog
import com.daniyal.basicappimpl.utils.event.UiEvent
import com.squareup.otto.Bus
import timber.log.Timber


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
            val event = it.getContentIfNotHandled()
            event!!.run {
                when (event) {
                    is UiEvent.ShowAlert -> {
                        showAlert(event.message)
                    }
                    is UiEvent.ShowToast -> {
                        showToast(event.message)
                    }
                    is UiEvent.ShowLoader -> {
                        showLoader(event.show)
                    }
                }
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this@BaseActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun showAlert(message: String) {
    }

    private fun showLoader(show: Boolean) {
        if (show) {
            customProgressDialog?.show()
        } else {
            customProgressDialog?.hide()
        }
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