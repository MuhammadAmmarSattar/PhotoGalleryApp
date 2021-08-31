package com.app.projectname.ui.preLogin.gallery.search.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.app.projectname.databinding.FragmentImageDetailBinding
import com.app.projectname.ui.base.BaseFragment
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageDetailFragment : BaseFragment<FragmentImageDetailBinding>() {
    @Inject
    lateinit var glide: RequestManager
    private val args : ImageDetailFragmentArgs by navArgs()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentImageDetailBinding.inflate(inflater,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        glide.load(args.galleryPhotoObject?.previewURL).into(binding.imgZoom)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }
}