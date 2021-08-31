package com.app.projectname.data.repository.photos.di

import com.app.projectname.data.repository.ApplicationDatabase
import com.app.projectname.data.repository.photos.local.gallery.GalleryDao
import com.app.projectname.data.repository.photos.remote.service.GalleryPhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
@Module
@InstallIn(ViewModelComponent::class)
object GalleryPhotoModule {
    @Provides
    fun providePhotoService(retrofit: Retrofit): GalleryPhotoService =
        retrofit.create(GalleryPhotoService::class.java)


    @Provides
    fun provideGalleryPhotoDao(appDatabase: ApplicationDatabase): GalleryDao =
        appDatabase.galleryPhotoDao()
}