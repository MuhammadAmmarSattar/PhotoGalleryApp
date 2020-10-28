package com.example.basearchitecture.common.Utils

import okhttp3.RequestBody
import okio.Buffer
import java.io.IOException

fun requestBodyToString(request: RequestBody?): String? {
    return try {
        val buffer = Buffer()
        if (request != null) request.writeTo(buffer) else return ""
        buffer.readUtf8()
    } catch (e: IOException) {
        "IO Exception"
    }

}
