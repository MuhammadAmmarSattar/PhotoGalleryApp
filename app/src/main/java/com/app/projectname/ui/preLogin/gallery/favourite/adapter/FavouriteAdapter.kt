package com.app.projectname.ui.preLogin.gallery.favourite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto
import com.app.projectname.databinding.ItemPhotosLayoutBinding
import com.bumptech.glide.RequestManager

class FavouriteAdapter (val glide: RequestManager, val callback: PhotoClickListener) :
    ListAdapter<GalleryPhoto, FavouriteAdapter.PhotoViewHolder>(PhotosDiffCallback()) {


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
        fun bind(GalleryPhoto: GalleryPhoto) {
            glide.load(GalleryPhoto.previewURL).into(binding.imgPhoto)


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
    }
}



