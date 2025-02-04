package cn.kotlinmultiplatform.jeady.platform

import android.content.Intent
import android.net.Uri
import cn.kotlinmultiplatform.jeady.MainActivity
import cn.kotlinmultiplatform.jeady.platform.UrlHandler

actual fun getPlatformUrlHandler(): UrlHandler = object : UrlHandler {
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        MainActivity.instance?.startActivity(intent)
    }
}