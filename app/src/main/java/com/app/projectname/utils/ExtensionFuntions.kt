package com.app.projectname.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import okhttp3.RequestBody
import okio.Buffer
import timber.log.Timber
import java.io.IOException

fun requestBodyToString(request: RequestBody?): String? {
    return try {
        val buffer = Buffer()
        if (request != null) request.writeTo(buffer) else return ""
        buffer.readUtf8()
    } catch (e: IOException) {
        "IO Exception"
    }

}

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}
fun <A : Activity> Activity.killSessionAndStartNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
        finish()
    }
}

fun <A : Activity> Fragment.startNewActivity(activity: Class<A>) {
    Intent(getActivity(), activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

}
fun Fragment.safeNavigateFromNavController(directions: NavDirections) {
    val navController = findNavController()
    val destination = navController.currentDestination as FragmentNavigator.Destination
    //current visible fragment == fragment that is firing navigation
    if (javaClass.name == destination.className) {
        navController.navigate(directions)
    } else {
        Timber.e("Invalid navigation detected")
    }
}

fun <T : Any, V : RecyclerView.ViewHolder> PagingDataAdapter<T, V>.withLoadStateAdapters(
    header: LoadStateAdapter<*>,
    footer: LoadStateAdapter<*>,
    callback: (showPlaceHolder: Boolean) -> Unit
): ConcatAdapter {
    addLoadStateListener { loadStates ->
        header.loadState = loadStates.refresh
        footer.loadState = loadStates.append
        if (loadStates.source.refresh is LoadState.NotLoading &&
            loadStates.append.endOfPaginationReached &&
            itemCount < 1
        ) {
            callback(true)
        } else if (loadStates.source.refresh is LoadState.Error &&
            itemCount < 1
        ) {
            callback(true)
        } else {
            callback(false)
        }
    }

    return ConcatAdapter(header, this, footer)
}


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.show(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ImageView.setTint(@ColorRes color:Int) {
    backgroundTintList = context.resources.getColorStateList(color)
}
