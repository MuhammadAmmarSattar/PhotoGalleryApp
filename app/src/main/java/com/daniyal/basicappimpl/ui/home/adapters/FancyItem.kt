package com.daniyal.basicappimpl.ui.home.adapters

import android.view.View
import androidx.annotation.ColorInt
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.item_example_fancy.view.*

class FancyItem(@ColorInt private val color: Int,
                private val number: Int): BaseAdapter(R.layout.item_example_fancy){


    override fun initBinding(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.root.apply {
            item_fancy_cardView.setCardBackgroundColor(color)
            item_fancy_number.text = number.toString()
        }

    }

    override fun initBinding(viewHolder: View, position: Int) {
        TODO("Not yet implemented")
    }


    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 3
}