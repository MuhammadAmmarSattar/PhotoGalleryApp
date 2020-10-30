package com.daniyal.basicappimpl.di

import com.daniyal.basicappimpl.utils.AppConfigUtils
import com.example.basearchitecture.common.Utils.SecurityManager
import com.example.basearchitecture.common.Utils.SessionManager
import com.example.basearchitecture.common.Utils.interceptors.HeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkingModule {
    //Network Providers
    @Provides
    fun provideBaseUrl() = AppConfigUtils.BASE_URL

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectionSpecs(
                Arrays.asList(
                    ConnectionSpec.MODERN_TLS,
                    ConnectionSpec.COMPATIBLE_TLS
                )
            )
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cache(null)
            .build()

    }
//        if (RestConfig.DEBUG) { // debug ON
//            val logger = HttpLoggingInterceptor()
//            logger.level = HttpLoggingInterceptor.Level.BODY
//            OkHttpClient.Builder()
//                .addInterceptor(logger)
//                .readTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
//                .connectTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
//                .build()
//        } else // debug OFF
//            OkHttpClient.Builder()
//                .readTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(AppConfigUtils.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
