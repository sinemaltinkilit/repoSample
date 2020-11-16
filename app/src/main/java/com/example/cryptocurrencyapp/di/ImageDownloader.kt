package com.example.cryptocurrencyapp.di

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.net.URL

class ImageDownloader(val imageUrl: String, val listener: DownloadListener) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg params: String?): Bitmap {
        //val imageUrl = params[0]
        val url = URL(imageUrl)
        val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        return bmp
    }

    override fun onPostExecute(result: Bitmap) {
        listener.onDownloadComplete(true, result)
    }

    interface DownloadListener {
        fun onDownloadComplete(download: Boolean, rslt: Bitmap)
    }
}