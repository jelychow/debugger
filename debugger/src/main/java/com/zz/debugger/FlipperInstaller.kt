package com.zz.debugger

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import okhttp3.OkHttpClient

class FlipperInstaller : ContentProvider() {
    override fun onCreate(): Boolean {
        SoLoader.init(context?.applicationContext, false)
        val client = AndroidFlipperClient.getInstance(context?.applicationContext)
        client.addPlugin(InspectorFlipperPlugin(context?.applicationContext, DescriptorMapping.withDefaults()))
        client.addPlugin(networkFlipperPlugin)
        client.start()
        return true
    }

    companion object {
        val networkFlipperPlugin = NetworkFlipperPlugin();
        fun install(client: OkHttpClient) {
            client.newBuilder().addInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin)).build()
//            client.interceptors.apply { (this as MutableList).add(FlipperOkhttpInterceptor(networkFlipperPlugin)) }
        }
    }



    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}

fun OkHttpClient.Builder.withFlipper() = addInterceptor(FlipperOkhttpInterceptor(FlipperInstaller.networkFlipperPlugin))