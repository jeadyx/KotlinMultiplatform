package cn.kotlinmultiplatform.jeady.model

data class User(
    val email: String,
    val username: String,
    val token: String = ""
) 