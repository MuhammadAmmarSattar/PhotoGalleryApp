package com.app.projectname.ui.preLogin.gallery.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.projectname.R
import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto
import com.app.projectname.databinding.FragmentFavouriteBinding
import com.app.projectname.databinding.FragmentGalleryBinding
import com.app.projectname.ui.base.BaseFragment
import com.app.projectname.ui.preLogin.gallery.favourite.adapter.FavouriteAdapter
import com.app.projectname.ui.preLogin.gallery.favourite.viewmodel.FavouriteViewModel
import com.app.projectname.ui.preLogin.gallery.search.adapter.PhotoPagingAdapter
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {
    private val favouriteViewModel : FavouriteViewModel by viewModels()
    private lateinit var photoPagingAdapter: FavouriteAdapter
    @Inject
    lateinit var glide: RequestManager
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    //test test
    ) = FragmentFavouriteBinding.inflate(inflater,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUiEvents(favouriteViewModel)
        rvSetUp()
        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        favouriteViewModel.photo.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                photoPagingAdapter.submitList(it)
            }
        })

    }
    private fun rvSetUp(){
        photoPagingAdapter = FavouriteAdapter(glide,
            object : FavouriteAdapter.PhotoClickListener {
                override fun onPhotoItemListener(galleryPhoto: GalleryPhoto) {
                }
            })
        binding.rvFav.apply {
            itemAnimator = null
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = photoPagingAdapter
        }
    }

}