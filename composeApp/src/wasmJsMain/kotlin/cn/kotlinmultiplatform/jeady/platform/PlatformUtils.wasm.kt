package cn.kotlinmultiplatform.jeady.platform

import kotlinx.browser.window

actual fun getPlatformUrlHandler(): UrlHandler = object : UrlHandler {
    override fun openUrl(url: String) {
        window.open(url, "_blank")
    }
} 