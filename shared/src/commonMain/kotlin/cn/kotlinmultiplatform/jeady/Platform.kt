package cn.kotlinmultiplatform.jeady

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform