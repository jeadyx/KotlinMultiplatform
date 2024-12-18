package cn.kotlinmultiplatform.jeady.utils

import kotlinx.datetime.Clock

object IdGenerator {
    private var counter = 0
    
    fun generateId(): String {
        counter++
        return "${Clock.System.now().toEpochMilliseconds()}_$counter"
    }
} 