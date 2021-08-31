package com.app.projectname.ui.preLogin.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.projectname.databinding.FragmentGalleryBinding
import com.app.projectname.ui.base.BaseFragment
import com.app.projectname.ui.preLogin.gallery.adapters.GalleryPagerAdapter
import com.app.projectname.ui.preLogin.gallery.viewmodel.GalleryViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {
    private val galleryViewModel : GalleryViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGalleryBinding.inflate(inflater,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUiEvents(galleryViewModel)
        initViewPager()

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }
    private fun initViewPager() {
        binding.pager.adapter = GalleryPagerAdapter(this)
        binding.pager.isUserInputEnabled = false
        TabLayoutMediator(
            binding.tabLayout, binding.pager
        ) { tab: TabLayout.Tab, position: Int ->
            val tabTitle = galleryViewModel.tabTitles[position]
            tab.text = getString(tabTitle)
        }.attach()
    }
}