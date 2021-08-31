package com.app.projectname.ui.preLogin.gallery.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.projectname.R
import com.app.projectname.databinding.FragmentAlbumBinding
import com.app.projectname.ui.base.BaseFragment
import com.app.projectname.ui.preLogin.gallery.GalleryFragment
import com.app.projectname.ui.preLogin.gallery.GalleryFragmentDirections
import com.app.projectname.utils.Constants.Bundle.CARS
import com.app.projectname.utils.Constants.Bundle.LANDSCAPE
import com.app.projectname.utils.Constants.Bundle.PAINTING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : BaseFragment<FragmentAlbumBinding>(),View.OnClickListener {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAlbumBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgCars.setOnClickListener(this)
        binding.imgLandscape.setOnClickListener(this)
        binding.imgPainting.setOnClickListener(this)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imgCars ->{
                (parentFragment as GalleryFragment).navigateByDirections(
                    GalleryFragmentDirections.actionGalleryFragmentToSearchImageFragment(keyword = CARS))
            }
            R.id.imgLandscape ->{
                (parentFragment as GalleryFragment).navigateByDirections(
                    GalleryFragmentDirections.actionGalleryFragmentToSearchImageFragment(keyword = LANDSCAPE))
            }
            R.id.imgPainting ->{
                (parentFragment as GalleryFragment).navigateByDirections(
                    GalleryFragmentDirections.actionGalleryFragmentToSearchImageFragment(keyword = PAINTING))
            }
        }
    }
}