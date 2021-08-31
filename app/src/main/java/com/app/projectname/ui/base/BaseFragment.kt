package com.app.projectname.ui.base

import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.app.projectname.infrastructure.ApplicationEntry
import com.app.projectname.utils.ProgressDialog
import com.app.projectname.utils.event.EventUtilFunctions
import com.app.projectname.utils.event.UiEvent
import com.squareup.otto.Bus
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() ,LocationListener{

    protected lateinit var application: ApplicationEntry
    protected var isBusRegistered: Boolean = false
    protected lateinit var bus: Bus
    protected lateinit var activity: Activity
    protected var customProgressDialog: ProgressDialog? = null
    protected lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>


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
        groupAdapter = GroupAdapter()


    }
    override fun onLocationChanged(location: Location) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = getFragmentBinding(inflater, container)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ViewModel is set as Binding Variable
        dataBinding.apply { lifecycleOwner = viewLifecycleOwner }
    }

    protected abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): DB


    override fun onDestroy() {
        super.onDestroy()
        if (isBusRegistered) {
            bus.unregister(this)
            isBusRegistered = false
        }
        groupAdapter.clear()

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
                            showLoader(event.show)
                        }
                        is UiEvent.ShowSnackbar -> {
                            EventUtilFunctions.showSnackbar(
                                requireView(),
                                event.message,
                                event.action
                            )
                        }
                        is UiEvent.NavigateByDirections -> {
                            navigateByDirections(event.navDirections)
                        }
                    }
                }
        })
    }

    fun navigateByDirections(navDirections: NavDirections) {
        EventUtilFunctions.navigateByDirections(this, navDirections)
    }
    fun showLoader(show: Boolean) {
        EventUtilFunctions.showLoader(show, customProgressDialog)
    }
    protected fun back() {
        hideKeyboard(activity)
        activity.onBackPressed()
    }

    open fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}