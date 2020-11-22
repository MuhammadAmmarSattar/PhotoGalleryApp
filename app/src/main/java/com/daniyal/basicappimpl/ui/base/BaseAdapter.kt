package com.daniyal.basicappimpl.ui.base

import android.view.View
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

abstract class BaseAdapter(private val resLayout: Int) : Item<GroupieViewHolder>() {

    override fun getLayout() = resLayout

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        initBinding(viewHolder.itemView, position)
    }

    protected abstract fun initBinding(viewHolder: View, position: Int)

}