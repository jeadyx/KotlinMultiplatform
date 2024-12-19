package cn.kotlinmultiplatform.jeady.service

import cn.kotlinmultiplatform.jeady.pages.Product
import cn.kotlinmultiplatform.jeady.pages.ProductType

class ProductService private constructor() {
    private val products = mutableListOf<Product>()

    init {
        // Development Tools
        products.addAll(listOf(
            Product(
                name = "IntelliJ IDEA",
                description = "JetBrains 旗舰 IDE，提供强大的 Kotlin Multiplatform 开发支持",
                type = ProductType.APP,
                imageUrl = "app_logo",
                url = "https://www.jetbrains.com/idea/",
                tags = listOf("IDE", "开发工具", "JetBrains"),
                category = "开发工具",
                downloadCount = "10M+",
                rating = 4.8f
            ),
            Product(
                name = "Android Studio",
                description = "基于 IntelliJ 的 Android 官方 IDE，支持 KMP 开发",
                type = ProductType.APP,
                imageUrl = "app_logo",
                url = "https://developer.android.com/studio",
                tags = listOf("IDE", "Android", "开发工具"),
                category = "开发工具",
                downloadCount = "50M+",
                rating = 4.7f
            ),
            Product(
                name = "AppCode",
                description = "JetBrains 的 iOS/macOS IDE，支持 KMP 开发",
                type = ProductType.APP,
                imageUrl = "app_logo",
                url = "https://www.jetbrains.com/objc/",
                tags = listOf("IDE", "iOS", "开发工具"),
                category = "开发工具",
                downloadCount = "1M+",
                rating = 4.6f
            ),
            Product(
                name = "KMM Plugin",
                description = "Kotlin Multiplatform Mobile 的官方 IDE 插件",
                type = ProductType.APP,
                imageUrl = "app_logo",
                url = "https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile",
                tags = listOf("插件", "KMM", "开发工具"),
                category = "开发工具",
                downloadCount = "500K+",
                rating = 4.5f
            ),
            Product(
                name = "Compose Multiplatform IDE Support",
                description = "Compose Multiplatform 的官方 IDE 支持插件",
                type = ProductType.APP,
                imageUrl = "app_logo",
                url = "https://plugins.jetbrains.com/plugin/16541-compose-multiplatform-ide-support",
                tags = listOf("插件", "Compose", "UI"),
                category = "开发工具",
                downloadCount = "200K+",
                rating = 4.4f
            ),
            // ... 更多开发工具
        ))

        // Efficiency Tools
        products.addAll(listOf(
            Product(
                name = "Kotlin Symbol Processing",
                description = "Kotlin 符号处理工具，用于生成代码",
                type = ProductType.TOOL,
                imageUrl = "app_logo",
                url = "https://github.com/google/ksp",
                tags = listOf("KSP", "代码生成", "注解处理"),
                category = "效率工具",
                downloadCount = "1M+",
                rating = 4.7f
            ),
            Product(
                name = "SQLDelight",
                description = "类型安全的跨平台 SQLite 数据库工具",
                type = ProductType.TOOL,
                imageUrl = "app_logo",
                url = "https://github.com/cashapp/sqldelight",
                tags = listOf("数据库", "SQL", "跨平台"),
                category = "效率工具",
                downloadCount = "500K+",
                rating = 4.6f
            ),
            Product(
                name = "Kotlin Serialization",
                description = "Kotlin 的官方序列化工具",
                type = ProductType.LIBRARY,
                imageUrl = "app_logo",
                url = "https://github.com/Kotlin/kotlinx.serialization",
                tags = listOf("序列化", "JSON", "跨平台"),
                category = "效率工具",
                downloadCount = "2M+",
                rating = 4.8f
            ),
            // ... 更多效率工具
        ))

        // Learning Tools
        products.addAll(listOf(
            Product(
                name = "Kotlin Playground",
                description = "在线 Kotlin 代码编辑器和运行环境",
                type = ProductType.WEBSITE,
                imageUrl = "app_logo",
                url = "https://play.kotlinlang.org/",
                tags = listOf("在线编辑器", "学习", "示例"),
                category = "学习工具",
                downloadCount = "1M+",
                rating = 4.5f
            ),
            Product(
                name = "KMP Sample Apps",
                description = "官方 KMP 示例应用集合",
                type = ProductType.WEBSITE,
                imageUrl = "app_logo",
                url = "https://github.com/Kotlin/kmm-samples",
                tags = listOf("示例", "学习", "KMM"),
                category = "学习工具",
                downloadCount = "100K+",
                rating = 4.4f
            ),
            // ... 更多学习工具
        ))

        // Official Websites
        products.addAll(listOf(
            Product(
                name = "Kotlin 官网",
                description = "Kotlin 编程语言官方网站",
                type = ProductType.WEBSITE,
                imageUrl = "app_logo",
                url = "https://kotlinlang.org/",
                tags = listOf("官方", "文档", "教程"),
                category = "官方网站",
                downloadCount = "10M+",
                rating = 4.9f
            ),
            Product(
                name = "KMP 官方文档",
                description = "Kotlin Multiplatform 官方文档",
                type = ProductType.WEBSITE,
                imageUrl = "app_logo",
                url = "https://kotlinlang.org/docs/multiplatform.html",
                tags = listOf("文档", "教程", "KMP"),
                category = "官方网站",
                downloadCount = "5M+",
                rating = 4.8f
            ),
            // ... 更多官方网站
        ))

        // Community Websites
        products.addAll(listOf(
            Product(
                name = "Kotlin Discussions",
                description = "Kotlin 官方讨论社区",
                type = ProductType.WEBSITE,
                imageUrl = "app_logo",
                url = "https://discuss.kotlinlang.org/",
                tags = listOf("社区", "讨论", "问答"),
                category = "社区网站",
                downloadCount = "1M+",
                rating = 4.6f
            ),
            Product(
                name = "Kotlin Blog",
                description = "Kotlin 官方博客",
                type = ProductType.WEBSITE,
                imageUrl = "app_logo",
                url = "https://blog.jetbrains.com/kotlin/",
                tags = listOf("博客", "新闻", "更新"),
                category = "社区网站",
                downloadCount = "500K+",
                rating = 4.7f
            ),
            // ... 更多社区网站
        ))

        // UI Libraries
        products.addAll(listOf(
            Product(
                name = "Compose Multiplatform",
                description = "声明式 UI 框架，支持多平台",
                type = ProductType.LIBRARY,
                imageUrl = "app_logo",
                url = "https://github.com/JetBrains/compose-multiplatform",
                tags = listOf("UI", "跨平台", "Compose"),
                category = "UI库",
                downloadCount = "1M+",
                rating = 4.8f
            ),
            Product(
                name = "Material Design Components",
                description = "Material Design 组件库",
                type = ProductType.LIBRARY,
                imageUrl = "app_logo",
                url = "https://github.com/material-components/material-components-android",
                tags = listOf("UI", "Material", "组件"),
                category = "UI库",
                downloadCount = "5M+",
                rating = 4.7f
            ),
            // ... 更多UI库
        ))

        // Network Libraries
        products.addAll(listOf(
            Product(
                name = "Ktor",
                description = "Kotlin 的异步网络框架",
                type = ProductType.LIBRARY,
                imageUrl = "app_logo",
                url = "https://github.com/ktorio/ktor",
                tags = listOf("网络", "HTTP", "异步"),
                category = "网络库",
                downloadCount = "2M+",
                rating = 4.8f
            ),
            Product(
                name = "Kotlin Coroutines",
                description = "Kotlin 协程库",
                type = ProductType.LIBRARY,
                imageUrl = "app_logo",
                url = "https://github.com/Kotlin/kotlinx.coroutines",
                tags = listOf("协程", "异步", "并发"),
                category = "网络库",
                downloadCount = "5M+",
                rating = 4.9f
            ),
            // ... 更多网络库
        ))

        // Utility Libraries
        products.addAll(listOf(
            Product(
                name = "Koin",
                description = "Kotlin 的依赖注入框架",
                type = ProductType.LIBRARY,
                imageUrl = "app_logo",
                url = "https://github.com/InsertKoinIO/koin",
                tags = listOf("DI", "依赖注入", "框架"),
                category = "工具库",
                downloadCount = "1M+",
                rating = 4.7f
            ),
            Product(
                name = "Arrow",
                description = "Kotlin 的函数式编程库",
                type = ProductType.LIBRARY,
                imageUrl = "app_logo",
                url = "https://github.com/arrow-kt/arrow",
                tags = listOf("FP", "函数式", "工具"),
                category = "工具库",
                downloadCount = "500K+",
                rating = 4.6f
            ),
            // ... 更多工具库
        ))

        // Command Line Tools
        products.addAll(listOf(
            Product(
                name = "Kotlin CLI",
                description = "Kotlin 命令行编译器",
                type = ProductType.TOOL,
                imageUrl = "app_logo",
                url = "https://kotlinlang.org/docs/command-line.html",
                tags = listOf("CLI", "编译器", "命令行"),
                category = "命令行工具",
                downloadCount = "1M+",
                rating = 4.7f
            ),
            Product(
                name = "Gradle KMP Plugin",
                description = "Gradle Kotlin Multiplatform 插件",
                type = ProductType.TOOL,
                imageUrl = "app_logo",
                url = "https://plugins.gradle.org/plugin/org.jetbrains.kotlin.multiplatform",
                tags = listOf("Gradle", "构建", "插件"),
                category = "命令行��具",
                downloadCount = "2M+",
                rating = 4.8f
            ),
            // ... 更多命令行工具
        ))

        // IDE Plugins
        products.addAll(listOf(
            Product(
                name = "Kotlin Plugin",
                description = "IntelliJ IDEA 的 Kotlin 插件",
                type = ProductType.TOOL,
                imageUrl = "app_logo",
                url = "https://plugins.jetbrains.com/plugin/6954-kotlin",
                tags = listOf("插件", "IDE", "Kotlin"),
                category = "IDE插件",
                downloadCount = "10M+",
                rating = 4.9f
            ),
            Product(
                name = "Kotlin Android Extensions",
                description = "Android 开发的 Kotlin 扩展插件",
                type = ProductType.TOOL,
                imageUrl = "app_logo",
                url = "https://plugins.jetbrains.com/plugin/7654-kotlin-android-extensions",
                tags = listOf("插件", "Android", "扩展"),
                category = "IDE插件",
                downloadCount = "5M+",
                rating = 4.7f
            ),
            // ... 更多IDE插件
        ))

        // Build Tools
        products.addAll(listOf(
            Product(
                name = "Gradle",
                description = "强大的构建工具，支持 KMP",
                type = ProductType.TOOL,
                imageUrl = "app_logo",
                url = "https://gradle.org/",
                tags = listOf("构建", "自动化", "工具"),
                category = "构建工具",
                downloadCount = "10M+",
                rating = 4.8f
            ),
            Product(
                name = "Maven",
                description = "流行的构建工具，支持 Kotlin",
                type = ProductType.TOOL,
                imageUrl = "app_logo",
                url = "https://maven.apache.org/",
                tags = listOf("构建", "依赖管理", "工具"),
                category = "构建工具",
                downloadCount = "5M+",
                rating = 4.7f
            ),
            // ... 更多构建工具
        ))
    }

    fun getAllProducts(): List<Product> = products.toList()

    fun addProduct(product: Product) {
        products.add(product)
    }

    companion object {
        private var instance: ProductService? = null

        fun getInstance(): ProductService {
            if (instance == null) {
                instance = ProductService()
            }
            return instance!!
        }
    }
} 