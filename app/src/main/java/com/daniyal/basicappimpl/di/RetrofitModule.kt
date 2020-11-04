package com.daniyal.basicappimpl.di

import com.daniyal.basicappimpl.AppConstants
import com.daniyal.basicappimpl.BuildConfig
import com.example.basearchitecture.common.Utils.interceptors.DecryptionInterceptor
import com.example.basearchitecture.common.Utils.interceptors.EncryptionInterceptor
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
object RetrofitModule {
    //Network Providers
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor,
            headerInterceptor: HeaderInterceptor,
            encryptionInterceptor: EncryptionInterceptor,
            decryptionInterceptor: DecryptionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(encryptionInterceptor)
                .addInterceptor(decryptionInterceptor)
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
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}
