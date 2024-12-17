package cn.kotlinmultiplatform.jeady.platform

import cn.kotlinmultiplatform.jeady.platform.UrlHandler
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun getPlatformUrlHandler(): UrlHandler = object : UrlHandler {
    override fun openUrl(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(nsUrl)
        }
    }
} 