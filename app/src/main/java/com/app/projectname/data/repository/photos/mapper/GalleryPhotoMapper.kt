package com.app.projectname.data.repository.photos.mapper

import com.app.projectname.data.repository.photos.local.gallery.GalleryPhoto
import com.app.projectname.data.repository.photos.remote.response.GalleryPhotoDTO


fun transformIntoPhotoGallery(galleryPhotoDTO: GalleryPhotoDTO) =
    galleryPhotoDTO.let {
        GalleryPhoto(
            id = it.id,
            previewURL = it.previewURL,
            likes = it.likes,
            tags = it.tags,
            type = it.type,
            user = it.user,
            userImageURL = it.userImageURL
        )
    }
