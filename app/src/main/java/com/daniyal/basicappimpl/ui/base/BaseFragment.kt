package com.daniyal.basicappimpl.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.daniyal.basicappimpl.infrastructure.ApplicationEntry
import com.daniyal.basicappimpl.utils.ProgressDialog
import com.daniyal.basicappimpl.utils.event.EventUtilFunctions
import com.daniyal.basicappimpl.utils.event.UiEvent
import com.squareup.otto.Bus

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    protected lateinit var application: ApplicationEntry
    protected var isBusRegistered: Boolean = false
    protected lateinit var bus: Bus
    protected lateinit var activity: Activity
    protected var customProgressDialog: ProgressDialog? = null


    // data binding
    private lateinit var dataBinding: DB
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
        dataBinding = getFragmentBinding(inflater, container)
//        dataBinding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ViewModel is set as Binding Variable
        dataBinding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

    }

//    protected abstract fun getFragmentLayout(): Int

    //
    protected abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): DB


    override fun onDestroy() {
        super.onDestroy()
        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }
    }

    fun subscribeUiEvents(baseViewModel: BaseViewModel) {
        baseViewModel.uiEvents.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()
                ?.let { event ->
                    when (event) {
                        is UiEvent.ShowAlert -> {
                            EventUtilFunctions.showAlert(event.message)
                        }
                        is UiEvent.ShowToast -> {
                            EventUtilFunctions.showToast(event.message, activity)
                        }
                        is UiEvent.ShowLoader -> {
                            EventUtilFunctions.showLoader(event.show, customProgressDialog)
                        }
                    }
                }
        })
    }



}