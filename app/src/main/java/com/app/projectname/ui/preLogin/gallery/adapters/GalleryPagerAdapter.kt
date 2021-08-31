package com.app.projectname.ui.preLogin.gallery.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.projectname.ui.preLogin.gallery.GalleryFragment
import com.app.projectname.ui.preLogin.gallery.album.AlbumFragment
import com.app.projectname.ui.preLogin.gallery.favourite.FavouriteFragment

class GalleryPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AlbumFragment()
            }
            1 -> {
                FavouriteFragment()
            }
            else -> {
                AlbumFragment()
            }
        }
    }
}