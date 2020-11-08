package com.daniyal.basicappimpl.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daniyal.basicappimpl.data.repository.base.BaseRepository
import com.daniyal.basicappimpl.infrastructure.ApplicationEntry
import com.daniyal.basicappimpl.utils.ProgressDialog
import com.squareup.otto.Bus

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding, R : BaseRepository> : Fragment() {

    protected lateinit var application: ApplicationEntry
    protected var isBusRegistered: Boolean = false
    protected lateinit var bus: Bus
    protected lateinit var activity: Activity
    protected var customProgressDialog: ProgressDialog? = null
    protected lateinit var viewModel: VM


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
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
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
    protected abstract fun getViewModel(): Class<VM>
    protected abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): DB
    protected abstract fun getFragmentRepository(): R


    override fun onDestroy() {
        super.onDestroy()
        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }
    }

}