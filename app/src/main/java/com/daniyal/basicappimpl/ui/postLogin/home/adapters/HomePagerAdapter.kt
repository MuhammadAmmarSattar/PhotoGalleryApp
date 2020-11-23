package com.daniyal.basicappimpl.ui.postLogin.home.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daniyal.basicappimpl.ui.postLogin.feeds.FeedsFragment
import com.daniyal.basicappimpl.ui.postLogin.notifications.NotificationFragment

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FeedsFragment()
            }
            1 -> {
                NotificationFragment()
            }
            2 -> {
                FeedsFragment()
            }
            else -> {
                NotificationFragment()
            }
        }
    }
}