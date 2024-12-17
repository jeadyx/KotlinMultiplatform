package cn.kotlinmultiplatform.jeady.platform

interface UrlHandler {
    fun openUrl(url: String)
}

expect fun getPlatformUrlHandler(): UrlHandler