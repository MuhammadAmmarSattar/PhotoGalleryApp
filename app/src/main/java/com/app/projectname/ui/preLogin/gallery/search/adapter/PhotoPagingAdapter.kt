package com.app.projectname.ui.preLogin.gallery.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.projectname.R
import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto
import com.app.projectname.databinding.ItemPhotosLayoutBinding
import com.bumptech.glide.RequestManager

class PhotoPagingAdapter (val glide: RequestManager,val callback: PhotoClickListener) :
    PagingDataAdapter<GalleryPhoto, PhotoPagingAdapter.PhotoViewHolder>(PhotosDiffCallback()) {


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            ItemPhotosLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    inner class PhotoViewHolder(val binding: ItemPhotosLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(galleryPhoto: GalleryPhoto) {
            setAnimation(binding.root.rootView,position,itemView.context)

            glide.load(galleryPhoto.previewURL).into(binding.imgPhoto)
            binding.cardView.setOnClickListener {
                callback.onPhotoItemListener(galleryPhoto)
            }
            binding.imgFav.setOnClickListener {
                callback.onFavouriteItemListener(galleryPhoto)
            }

        }
    }
    class PhotosDiffCallback : DiffUtil.ItemCallback<GalleryPhoto>() {
        override fun areItemsTheSame(oldItem: GalleryPhoto, newItem: GalleryPhoto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GalleryPhoto, newItem: GalleryPhoto): Boolean =
            oldItem == newItem
    }
    interface PhotoClickListener {
        fun onPhotoItemListener(galleryPhoto: GalleryPhoto)
        fun onFavouriteItemListener(galleryPhoto: GalleryPhoto)
    }

    private fun setAnimation(viewToAnimate: View, position: Int, context: Context) {
        // If the bound view wasn't previously displayed on screen, it's animated
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
        viewToAnimate.startAnimation(animation)
    }
}



