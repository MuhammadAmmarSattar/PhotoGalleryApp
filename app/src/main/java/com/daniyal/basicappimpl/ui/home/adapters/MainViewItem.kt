package com.daniyal.basicappimpl.ui.home.adapters

import android.view.View
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.data.repository.photo.remote.response.PhotoDTO
import com.daniyal.basicappimpl.ui.base.BaseAdapter
import com.daniyal.basicappimpl.ui.callbacks.GroupieInterface
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import kotlinx.android.synthetic.main.item_example_row.view.*

class MainViewItem(
    private val item: PhotoDTO,
    private val groupieInterface: GroupieInterface<PhotoDTO>
) : BaseAdapter(R.layout.item_example_row) {

    override fun initBinding(viewHolder: View, position: Int) {

        viewHolder.apply {
            title.text = item.desc
            itemRoot?.let {
                setOnClickListener {
                    groupieInterface.invokeSingleItemClick(item, position)
                }
            }
        }


    }



}