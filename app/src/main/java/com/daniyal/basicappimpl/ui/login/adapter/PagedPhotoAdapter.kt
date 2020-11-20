package com.daniyal.basicappimpl.ui.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basearchitecture.common.repository.photo.local.Photo
import com.example.basearchitecture.databinding.PhotoItemBinding

class PagedPhotoAdapter(diffCallback: DiffUtil.ItemCallback<Photo> = UserComparator) :
    PagingDataAdapter<Photo, PagedPhotoAdapter.PhotoViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class PhotoViewHolder(private val itemBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(photo: Photo?) {
            photo?.run {
                itemBinding.id.text = id
            }
        }
    }
}

object UserComparator : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}