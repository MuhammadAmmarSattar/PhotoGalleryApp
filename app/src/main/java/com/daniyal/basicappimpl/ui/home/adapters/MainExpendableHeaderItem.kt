package com.daniyal.basicappimpl.ui.home.adapters

import android.view.View
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseAdapter
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import kotlinx.android.synthetic.main.item_example_header_row.view.*

class MainExpendableHeaderItem(private val title: String) : BaseAdapter(R.layout.item_example_header_row), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup


    override fun initBinding(viewHolder: View, position: Int) {
        viewHolder.item_expandable_header_title.text = title
        viewHolder.item_expandable_header_icon.setImageResource(getRotatedIconResId())

        viewHolder.item_expandable_header_root.setOnClickListener {
            expandableGroup.onToggleExpanded()
            viewHolder.item_expandable_header_icon.setImageResource(getRotatedIconResId())
        }

    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    private fun getRotatedIconResId() =
        if (expandableGroup.isExpanded) R.drawable.ic_keyboard_arrow_up_black_24dp
        else R.drawable.ic_keyboard_arrow_down_black_24dp
}