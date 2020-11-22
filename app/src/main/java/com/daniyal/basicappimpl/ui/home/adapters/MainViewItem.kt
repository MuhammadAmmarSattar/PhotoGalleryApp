package com.daniyal.basicappimpl.ui.home.adapters

import android.view.View
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.data.repository.photo.remote.response.PhotoDTO
import com.daniyal.basicappimpl.ui.base.BaseAdapter
import com.daniyal.basicappimpl.ui.callbacks.GroupieInterface
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.item_example_row.view.*

class MainViewItem(
    private val photo: PhotoDTO,
    private val groupieInterface: GroupieInterface<PhotoDTO>
) : BaseAdapter(R.layout.item_example_row) {

    override fun initBinding(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            root.title.text = photo.desc
            root?.let {
                it.setOnLongClickListener {
                    groupieInterface.invokeOnLongItemClick(viewHolder.item)
                    true
                }
            }
        }
    }

    override fun initBinding(viewHolder: View, position: Int) {
        TODO("Not yet implemented")
    }


}