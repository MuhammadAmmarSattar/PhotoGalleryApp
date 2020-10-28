package com.example.basearchitecture.common.Utils.interceptors

import com.example.basearchitecture.common.Utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class HeaderInterceptor @Inject constructor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Accept-Version", "v1")
            .addHeader("Authorization", sessionManager.authorizationToken)
            .build()
        //chain.proceed() calls next interceptor if chained in okHttpClientbuilder

        return chain.proceed(request)
    }

}