package com.zz.myapplication.network

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.zz.debugger.withFlipper
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException


internal object NetworkUtils {
    var okHttpClient = OkHttpClient.Builder()
        .withFlipper()
        .build()

    private const val ENDPOINT = "https://api.github.com/repos/square/okhttp/contributors"
    private val MOSHI: Moshi = Moshi.Builder().build()
    private val CONTRIBUTORS_JSON_ADAPTER: JsonAdapter<List<Contributor>> = MOSHI.adapter(
        Types.newParameterizedType(MutableList::class.java, Contributor::class.java)
    )

    fun sendDemoRequest(callback: NetworkCallback) {
        // Create request for remote resource.
        val request = Request.Builder().url(ENDPOINT).build()
        // Execute the request and retrieve the response.
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // 请求失败的处理逻辑
                println("Request failed: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                // 请求成功的处理逻辑
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    println("Response: $responseBody")
                } else {
                    println("Response failed with code: ${response.code}")
                }
            }
        })
            // Deserialize HTTP response to concrete type.
//            val body: ResponseBody? = response.body
//            val contributors = body?.source()?.let { CONTRIBUTORS_JSON_ADAPTER.fromJson(it) }
//            callback.onContributorsReturn(contributors)
        }
}

internal interface NetworkCallback {
    fun onContributorsReturn(contributors: List<Contributor>?)
}

@JsonClass(generateAdapter = true)
internal data class Contributor(
    var login: String? = null,
    var contributions: Int = 0
)
