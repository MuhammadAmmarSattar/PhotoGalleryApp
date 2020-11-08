package com.daniyal.basicappimpl.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.daniyal.basicappimpl.infrastructure.ApplicationEntry
import com.daniyal.basicappimpl.utils.ProgressDialog
import com.daniyal.basicappimpl.utils.event.UiEvent
import com.squareup.otto.Bus
import timber.log.Timber

abstract class BaseFragment<DataBinding : ViewDataBinding> : Fragment() {

    protected lateinit var application: ApplicationEntry
    protected var isBusRegistered: Boolean = false
    protected lateinit var bus: Bus
    protected lateinit var activity: Activity
    protected var customProgressDialog: ProgressDialog? = null


    // data binding
    private lateinit var dataBinding: DataBinding
    protected val binding get() = dataBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = (context as Activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = activity.application as ApplicationEntry
        bus = application.bus
        bus.register(this)
        isBusRegistered = true
        customProgressDialog = ProgressDialog(activity)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // View is created using layout Id
        dataBinding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ViewModel is set as Binding Variable
        dataBinding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

    }

    protected abstract fun getFragmentLayout(): Int

    fun subscribeUiEvents(baseViewModel: BaseViewModel) {
        baseViewModel.uiEvents.observe(viewLifecycleOwner, {
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
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }
    }

}