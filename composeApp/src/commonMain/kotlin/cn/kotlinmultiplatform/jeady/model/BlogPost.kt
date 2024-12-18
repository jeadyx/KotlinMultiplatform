package cn.kotlinmultiplatform.jeady.model

import kotlinx.datetime.LocalDateTime

data class BlogPost(
    val id: String,
    val title: String,
    val content: String,
    val summary: String,
    val author: String,
    val category: String,
    val tags: List<String>,
    val publishDate: LocalDateTime,
    val readingTime: Int // 分钟
) 