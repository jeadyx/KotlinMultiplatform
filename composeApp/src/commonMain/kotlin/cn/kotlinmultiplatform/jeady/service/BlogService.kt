package cn.kotlinmultiplatform.jeady.service

import cn.kotlinmultiplatform.jeady.model.BlogPost
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class BlogService private constructor() {
    private val posts = mutableListOf<BlogPost>()

    init {
        // 添加示例博客文章
        addPost(BlogPost(
            id = "1",
            title = "Kotlin Multiplatform 入门指南",
            content = """
                # Kotlin Multiplatform 入门指南
                
                Kotlin Multiplatform 是一个强大的框架，允许开发者使用单一代码库构建跨平台应用。
                
                ## 主要优势
                
                1. 代码共享
                2. 原生性能
                3. 灵活性
                
                ## 开始使用
                
                首先，你需要设置开发环境...
            """.trimIndent(),
            summary = "了解如何使用 Kotlin Multiplatform 开发跨平台应用的完整指南",
            author = "技术团队",
            category = "技术",
            tags = listOf("Kotlin", "跨平台", "移动开发", "教程"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 10
        ))

        addPost(BlogPost(
            id = "2",
            title = "现代UI设计趋势",
            content = """
                # 2024年UI设计趋势
                
                设计领域正在经历快速的变革，新的趋势不断涌现。
                
                ## 主要趋势
                
                1. 新拟态设计
                2. 微动效
                3. 深色模式
                
                ## 实践建议
                
                在采用这些趋势时...
            """.trimIndent(),
            summary = "探索2024年最新的UI设计趋势和最佳实践",
            author = "设计团队",
            category = "设计",
            tags = listOf("UI", "设计趋势", "用户体验"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 8
        ))

        addPost(BlogPost(
            id = "3",
            title = "软件开发中的思维模式",
            content = """
                # 软件开发中的思维模式
                
                在软件开发过程中，正确的思维模式比技术本身更重要。
                
                ## 关键思维模式
                
                1. 系统思维
                2. 抽象思维
                3. 工程思维
                
                ## 实际应用
                
                让我们看看这些思维模式如何应用到实际项目中...
            """.trimIndent(),
            summary = "探讨软件开发中的关键思维模式及其实际应用",
            author = "架构师",
            category = "思考",
            tags = listOf("思维方式", "软件开发", "方法论"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 12
        ))

        addPost(BlogPost(
            id = "4",
            title = "远程工作的日常",
            content = """
                # 远程工作的日常
                
                远程工作已经成为很多人的常态，如何平衡工作和生活？
                
                ## 日常安排
                
                1. 固定作息
                2. 专注时间
                3. 运动健身
                
                ## 实用工具
                
                这些工具可以帮助提高远程工作效率...
            """.trimIndent(),
            summary = "分享远程工作的日常安排和效率提升技巧",
            author = "生活博主",
            category = "生活",
            tags = listOf("远程工作", "生活方式", "效率"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 6
        ))

        addPost(BlogPost(
            id = "5",
            title = "Compose Multiplatform 实战",
            content = """
                # Compose Multiplatform 实战指南
                
                Compose Multiplatform 让跨平台UI开发变得更简单。
                
                ## 核心概念
                
                1. 声明式UI
                2. 状态管理
                3. 组件复用
                
                ## 实战示例
                
                让我们通过一个实际项目来学习...
            """.trimIndent(),
            summary = "深入探讨 Compose Multiplatform 的实际应用",
            author = "技术团队",
            category = "技术",
            tags = listOf("Compose", "UI开发", "跨平台"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 15
        ))
    }

    companion object {
        private val instance = BlogService()
        fun getInstance() = instance
    }

    fun getAllPosts(): List<BlogPost> = posts.toList()

    fun getPostById(id: String): BlogPost? = posts.find { it.id == id }

    fun addPost(post: BlogPost) {
        posts.add(post)
    }

    fun updatePost(post: BlogPost) {
        val index = posts.indexOfFirst { it.id == post.id }
        if (index != -1) {
            posts[index] = post
        }
    }

    fun deletePost(id: String) {
        posts.removeAll { it.id == id }
    }

    fun createNewPost(): BlogPost {
        return BlogPost(
            id = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString(),
            title = "",
            content = "",
            summary = "",
            author = "当前用户", // TODO: 实现用户系统
            category = "技术",
            tags = emptyList(),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 1
        )
    }
} 