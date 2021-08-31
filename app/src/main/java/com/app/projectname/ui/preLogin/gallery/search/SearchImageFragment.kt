package com.app.projectname.ui.preLogin.gallery.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.app.projectname.R
import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto
import com.app.projectname.databinding.FragmentSearchImageBinding
import com.app.projectname.ui.base.BaseFragment
import com.app.projectname.ui.component.defaultLoadStateAdapter.DefaultLoadStateAdapter
import com.app.projectname.ui.preLogin.gallery.search.adapter.PhotoPagingAdapter
import com.app.projectname.ui.preLogin.gallery.search.viewmodel.SearchImageViewModel
import com.app.projectname.utils.show
import com.app.projectname.utils.withLoadStateAdapters
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SearchImageFragment : BaseFragment<FragmentSearchImageBinding>(),View.OnClickListener {
    @Inject
    lateinit var glide: RequestManager
    private lateinit var photoPagingAdapter: PhotoPagingAdapter
    private val args : SearchImageFragmentArgs by navArgs()
    private val searchImageViewModel : SearchImageViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentSearchImageBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUiEvents(searchImageViewModel)
        binding.let {
            it.imgBack.setOnClickListener(this)
            it.viewmodel = searchImageViewModel
            it.fragment = this
            it.args =args
        }

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        rvSetUp()
        subscribeToObservables()
        searchImgs()

    }
    private fun subscribeToObservables() {
        searchImageViewModel.searchItem.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    showLoader(false)
                    photoPagingAdapter.submitData(it)
                }
        }
    }

    private fun rvSetUp(){
        val spanCount = 2
        val footerAdapter = DefaultLoadStateAdapter()
        val headerAdapter = DefaultLoadStateAdapter()
            photoPagingAdapter = PhotoPagingAdapter(glide,
                object : PhotoPagingAdapter.PhotoClickListener {
                    override fun onPhotoItemListener(galleryPhoto: GalleryPhoto) {
                        navigateByDirections(
                            SearchImageFragmentDirections.actionSearchImageFragmentToImageDetailFragment(
                                galleryPhotoObject = galleryPhoto
                            )
                        )
                    }

                    override fun onFavouriteItemListener(galleryPhoto: GalleryPhoto) {
                        searchImageViewModel.showToast("Favourite..")
                        lifecycleScope.launch {
                            searchImageViewModel.insertItem(galleryPhoto)
                        }
                    }
                })
        binding.rvSearch.apply {
            itemAnimator = null
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            adapter = photoPagingAdapter.withLoadStateAdapters(
                footer = footerAdapter,
                header = headerAdapter,
                callback = {
                    binding.tvPlaceHolder.show(it)
                }
            )
        }
    }
    private fun searchImgs(){
        binding.editSearchView.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                showLoader(true)
                searchImageViewModel.getPhotos(binding.editSearchView.text.toString().trim())
                hideKeyboard(activity)
                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imgBack ->{
                back()
            }
        }
    }
}