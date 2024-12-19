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
            title = "KMP 项目架构最佳实践",
            content = """
                # KMP 项目架构最佳实践
                
                如何构建一个可维护的 KMP 项目？本文将分享最佳实践和经验。
                
                ## 核心原则
                
                1. 模块化设计
                2. 依赖注入
                3. 清晰的层次结构
                
                ## 实战经验
                
                基于实际项目分享...
            """.trimIndent(),
            summary = "深入探讨 KMP 项目的架构设计和最佳实践",
            author = "架构师",
            category = "技术",
            tags = listOf("Kotlin", "架构", "最佳实践"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 15
        ))

        addPost(BlogPost(
            id = "3",
            title = "Kotlin 协程深度解析",
            content = """
                # Kotlin 协程深度解析
                
                协程是 Kotlin 最强大的特性之一，让我们深入理解它。
                
                ## 核心概念
                
                1. 挂起函数
                2. 协程作用域
                3. 调度器
                
                ## 实际应用
                
                通过实例学习协程...
            """.trimIndent(),
            summary = "深入理解 Kotlin 协程的工作原理和使用方法",
            author = "技术专家",
            category = "技术",
            tags = listOf("Kotlin", "协程", "并发"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 20
        ))

        addPost(BlogPost(
            id = "4",
            title = "KMP 数据持久化方案",
            content = """
                # KMP 数据持久化方案
                
                探讨 KMP 项目中的数据持久化解决方案。
                
                ## 主流方案
                
                1. SQLDelight
                2. Realm
                3. 文件存储
                
                ## 方案对比
                
                各方案的优劣分析...
            """.trimIndent(),
            summary = "比较不同的 KMP 数据持久化方案",
            author = "数据库专家",
            category = "技术",
            tags = listOf("KMP", "数据库", "存储"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 12
        ))

        addPost(BlogPost(
            id = "5",
            title = "Compose Multiplatform UI 设计",
            content = """
                # Compose Multiplatform UI 设计
                
                使用 Compose Multiplatform 创建优雅的跨平台 UI。
                
                ## 设计原则
                
                1. 响应式设计
                2. 主题系统
                3. 组件复用
                
                ## 实战技巧
                
                实用的 UI 开发技巧...
            """.trimIndent(),
            summary = "学习如何使用 Compose Multiplatform 创建优秀的用户界面",
            author = "UI专家",
            category = "设计",
            tags = listOf("Compose", "UI", "设计"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 15
        ))

        addPost(BlogPost(
            id = "6",
            title = "KMP 测试策略",
            content = """
                # KMP 测试策略
                
                如何为 KMP 项目建立完善的测试体系？
                
                ## 测试类型
                
                1. 单元测试
                2. 集成测试
                3. UI测试
                
                ## 最佳实践
                
                测试策略和实施方法...
            """.trimIndent(),
            summary = "建立 KMP 项目的测试体系",
            author = "测试专家",
            category = "技术",
            tags = listOf("测试", "KMP", "质量"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 10
        ))

        addPost(BlogPost(
            id = "7",
            title = "KMP 性能优化指南",
            content = """
                # KMP 性能优化指南
                
                提升 KMP 应用的性能和响应速度。
                
                ## 优化方向
                
                1. 内存管理
                2. 并发处理
                3. UI渲染
                
                ## 实战技巧
                
                具体的优化方法...
            """.trimIndent(),
            summary = "全面的 KMP 应用性能优化指南",
            author = "性能专家",
            category = "技术",
            tags = listOf("性能", "优化", "KMP"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 18
        ))

        addPost(BlogPost(
            id = "8",
            title = "KMP 网络层设计",
            content = """
                # KMP 网络层设计
                
                构建可靠的跨平台网络请求层。
                
                ## 技术选型
                
                1. Ktor Client
                2. Retrofit
                3. 自定义实现
                
                ## 架构设计
                
                网络层的详细设计...
            """.trimIndent(),
            summary = "探讨 KMP 项目中网络层的设计和实现",
            author = "网络专家",
            category = "技术",
            tags = listOf("网络", "KMP", "架构"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 14
        ))

        addPost(BlogPost(
            id = "9",
            title = "KMP 依赖注入实践",
            content = """
                # KMP 依赖注入实践
                
                在 KMP 项目中实现依赖注入。
                
                ## 框架选择
                
                1. Koin
                2. Kodein
                3. 自定义DI
                
                ## 实现方案
                
                详细的实现步骤...
            """.trimIndent(),
            summary = "学习在 KMP 项目中实现依赖注入",
            author = "架构师",
            category = "技术",
            tags = listOf("DI", "KMP", "架构"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 12
        ))

        addPost(BlogPost(
            id = "10",
            title = "KMP 状态管理方案",
            content = """
                # KMP 状态管理方案
                
                探讨 KMP 项目中的状态管理方案。
                
                ## 主流方案
                
                1. MVI
                2. MVVM
                3. Redux
                
                ## 实战对比
                
                各方案的优劣分析...
            """.trimIndent(),
            summary = "比较不同的 KMP 状态管理方案",
            author = "架构师",
            category = "技术",
            tags = listOf("状态管理", "KMP", "架构"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 16
        ))

        addPost(BlogPost(
            id = "11",
            title = "KMP 模块化实践",
            content = """
                # KMP 模块化实践
                
                如何实现 KMP 项目的模块化？
                
                ## 模块设计
                
                1. 功能模块
                2. 基础设施
                3. 业务模块
                
                ## 最佳实践
                
                模块化的具体实现...
            """.trimIndent(),
            summary = "学习 KMP 项目的模块化设计和实现",
            author = "架构师",
            category = "技术",
            tags = listOf("模块化", "KMP", "架构"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 13
        ))

        addPost(BlogPost(
            id = "12",
            title = "KMP 安全最佳实践",
            content = """
                # KMP 安全最佳实践
                
                保护你的 KMP 应用安全。
                
                ## 安全领域
                
                1. 数据加密
                2. 网络安全
                3. 存储安全
                
                ## 实施方案
                
                具体的安全措施...
            """.trimIndent(),
            summary = "了解 KMP 应用的安全防护措施",
            author = "安全专家",
            category = "技术",
            tags = listOf("安全", "KMP", "最佳实践"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 11
        ))

        addPost(BlogPost(
            id = "13",
            title = "KMP CI/CD 实践",
            content = """
                # KMP CI/CD 实践
                
                搭建 KMP 项目的持续集成和部署流程。
                
                ## 工具链
                
                1. GitHub Actions
                2. Jenkins
                3. GitLab CI
                
                ## 实现步骤
                
                详细的配置方法...
            """.trimIndent(),
            summary = "学习配置 KMP 项目的 CI/CD 流程",
            author = "DevOps工程师",
            category = "技术",
            tags = listOf("CI/CD", "KMP", "自动化"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 14
        ))

        addPost(BlogPost(
            id = "14",
            title = "KMP 日志系统设计",
            content = """
                # KMP 日志系统设计
                
                构建跨平台的日志收集系统。
                
                ## 系统设计
                
                1. 日志级别
                2. 存储策略
                3. 上报机制
                
                ## 实现方案
                
                具体的实现步骤...
            """.trimIndent(),
            summary = "设计和实现 KMP 的日志系统",
            author = "系统工程师",
            category = "技术",
            tags = listOf("日志", "KMP", "系统设计"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 12
        ))

        addPost(BlogPost(
            id = "15",
            title = "KMP 多语言支持",
            content = """
                # KMP 多语言支持
                
                实现 KMP 应用的国际化。
                
                ## 实现方案
                
                1. 资源管理
                2. 动态切换
                3. 默认语言
                
                ## 最佳实践
                
                国际化的具体实现...
            """.trimIndent(),
            summary = "为 KMP 应用添加多语言支持",
            author = "国际化专家",
            category = "技术",
            tags = listOf("国际化", "KMP", "多语言"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 10
        ))

        addPost(BlogPost(
            id = "16",
            title = "KMP 深色模式实现",
            content = """
                # KMP 深色模式实现
                
                为 KMP 应用添加深色模式支持。
                
                ## 实现要点
                
                1. 主题系统
                2. 动态切换
                3. 资源适配
                
                ## 具体步骤
                
                实现的详细过程...
            """.trimIndent(),
            summary = "学习在 KMP 中实现深色模式",
            author = "UI工程师",
            category = "技术",
            tags = listOf("UI", "KMP", "深色模式"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 9
        ))

        addPost(BlogPost(
            id = "17",
            title = "KMP 动画实现指南",
            content = """
                # KMP 动画实现指南
                
                在 KMP 项目中实现流畅的动画效果。
                
                ## 动画类型
                
                1. 属性动画
                2. 转场动画
                3. 手势动画
                
                ## 实现方法
                
                详细的动画实现...
            """.trimIndent(),
            summary = "学习在 KMP 中实现各种动画效果",
            author = "动画专家",
            category = "技术",
            tags = listOf("动画", "KMP", "UI"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 13
        ))

        addPost(BlogPost(
            id = "18",
            title = "KMP 手势处理",
            content = """
                # KMP 手势处理
                
                实现跨平台的手势识别和处理。
                
                ## 手势类型
                
                1. 点击事件
                2. 滑动手势
                3. 缩放手势
                
                ## 实现方案
                
                手势处理的具体实现...
            """.trimIndent(),
            summary = "学习处理 KMP 应用中的各种手势",
            author = "交互专家",
            category = "技术",
            tags = listOf("手势", "KMP", "交互"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 11
        ))

        addPost(BlogPost(
            id = "19",
            title = "KMP 图片处理",
            content = """
                # KMP 图片处理
                
                跨平台图片处理解决方案。
                
                ## 功能实现
                
                1. 图片加载
                2. 图片缓存
                3. 图片转换
                
                ## 技术方案
                
                详细的实现方法...
            """.trimIndent(),
            summary = "学习在 KMP 中处理图片资源",
            author = "多媒体专家",
            category = "技术",
            tags = listOf("图片", "KMP", "多媒体"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 12
        ))

        addPost(BlogPost(
            id = "20",
            title = "KMP 推送通知",
            content = """
                # KMP 推送通知
                
                实现跨平台的推送通知系统。
                
                ## 实现方案
                
                1. 本地通知
                2. 远程推送
                3. 通知管理
                
                ## 具体步骤
                
                推送系统的实现...
            """.trimIndent(),
            summary = "学习在 KMP 中实现推送通知",
            author = "系统工程师",
            category = "技术",
            tags = listOf("推送", "KMP", "通知"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 14
        ))

        addPost(BlogPost(
            id = "21",
            title = "KMP 蓝牙通信",
            content = """
                # KMP 蓝牙通信
                
                实现跨平台的蓝牙通信功能。
                
                ## 功能实现
                
                1. 设备扫描
                2. 连接管理
                3. 数据传输
                
                ## 实现方案
                
                具体的实现步骤...
            """.trimIndent(),
            summary = "学习在 KMP 中实现蓝牙通信",
            author = "硬件工程师",
            category = "技术",
            tags = listOf("蓝牙", "KMP", "通信"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 15
        ))

        addPost(BlogPost(
            id = "22",
            title = "KMP 地图集成",
            content = """
                # KMP 地图集成
                
                在 KMP 项目中集成地图功能。
                
                ## 实现方案
                
                1. 地图显示
                2. 位置服务
                3. 路径规划
                
                ## 集成步骤
                
                详细的实现过程...
            """.trimIndent(),
            summary = "学习在 KMP 中集成地图功能",
            author = "地图专家",
            category = "技术",
            tags = listOf("地图", "KMP", "位置服务"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 13
        ))

        addPost(BlogPost(
            id = "23",
            title = "KMP 文件处理",
            content = """
                # KMP 文件处理
                
                跨平台文件操作解决方案。
                
                ## 功能实现
                
                1. 文件读写
                2. 文件管理
                3. 文件共享
                
                ## 实现方案
                
                具体的实现方法...
            """.trimIndent(),
            summary = "学习在 KMP 中处理文件操作",
            author = "系统工程师",
            category = "技术",
            tags = listOf("文件", "KMP", "存储"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 11
        ))

        addPost(BlogPost(
            id = "24",
            title = "KMP 音视频处理",
            content = """
                # KMP 音视频处理
                
                实现跨平台的音视频功能。
                
                ## 功能实现
                
                1. 音频播放
                2. 视频播放
                3. 媒体控制
                
                ## 实现方案
                
                详细的实现步骤...
            """.trimIndent(),
            summary = "学习在 KMP 中处理音视频",
            author = "多媒体专家",
            category = "技术",
            tags = listOf("音视频", "KMP", "多媒体"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 16
        ))

        addPost(BlogPost(
            id = "25",
            title = "KMP 权限管理",
            content = """
                # KMP 权限管理
                
                实现跨平台的权限管理系统。
                
                ## 功能实现
                
                1. 权限请求
                2. 权限检查
                3. 权限处理
                
                ## 实现方案
                
                具体的实现方法...
            """.trimIndent(),
            summary = "学习在 KMP 中管理应用权限",
            author = "系统工程师",
            category = "技术",
            tags = listOf("权限", "KMP", "系统"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 10
        ))

        addPost(BlogPost(
            id = "26",
            title = "KMP WebView 集成",
            content = """
                # KMP WebView 集成
                
                在 KMP 项目中集成 WebView。
                
                ## 功能实现
                
                1. 网页加载
                2. JS交互
                3. 混合开发
                
                ## 实现方案
                
                详细的实现步骤...
            """.trimIndent(),
            summary = "学习在 KMP 中集成 WebView",
            author = "Web工程师",
            category = "技术",
            tags = listOf("WebView", "KMP", "混合开发"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 12
        ))

        addPost(BlogPost(
            id = "27",
            title = "KMP 应用升级",
            content = """
                # KMP 应用升级
                
                实现跨平台的应用升级功能。
                
                ## 功能实现
                
                1. 版本检查
                2. 下载更新
                3. 安装部署
                
                ## 实现方案
                
                具体的实现步骤...
            """.trimIndent(),
            summary = "学习在 KMP 中实现应用升级",
            author = "系统工程师",
            category = "技术",
            tags = listOf("升级", "KMP", "系统"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 11
        ))

        addPost(BlogPost(
            id = "28",
            title = "KMP 崩溃处理",
            content = """
                # KMP 崩溃处理
                
                实现跨平台的崩溃收集和处理。
                
                ## 功能实现
                
                1. 崩溃捕获
                2. 日志收集
                3. 错误分析
                
                ## 实现方案
                
                详细的实现方法...
            """.trimIndent(),
            summary = "学习在 KMP 中处理应用崩溃",
            author = "质量工程师",
            category = "技术",
            tags = listOf("崩溃", "KMP", "质量"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 13
        ))

        addPost(BlogPost(
            id = "29",
            title = "KMP 性能监控",
            content = """
                # KMP 性能监控
                
                实现跨平台的性能监控系统。
                
                ## 功能实现
                
                1. 性能指标
                2. 数据收集
                3. 分析报告
                
                ## 实现方案
                
                具体的实现步骤...
            """.trimIndent(),
            summary = "学习在 KMP 中监控应用性能",
            author = "性能工程师",
            category = "技术",
            tags = listOf("性能", "KMP", "监控"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 14
        ))

        addPost(BlogPost(
            id = "30",
            title = "KMP 安全加密",
            content = """
                # KMP 安全加密
                
                实现跨平台的数据加密功能。
                
                ## 功能实现
                
                1. 数据加密
                2. 密钥管理
                3. 安全存储
                
                ## 实现方案
                
                详细的实现步骤...
            """.trimIndent(),
            summary = "学习在 KMP 中实现数据加密",
            author = "安全工程师",
            category = "技术",
            tags = listOf("加密", "KMP", "安全"),
            publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            readingTime = 12
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