package cn.kotlinmultiplatform.jeady.platform

import java.awt.Desktop
import java.net.URI

actual fun getPlatformUrlHandler(): UrlHandler = object : UrlHandler {
    override fun openUrl(url: String) {
        try {
            Desktop.getDesktop().browse(URI(url))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
} 