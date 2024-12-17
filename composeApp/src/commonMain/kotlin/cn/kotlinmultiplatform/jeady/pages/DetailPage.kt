package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class DetailInfo(
    val title: String,
    val description: String,
    val content: String,
    val features: List<String>,
    val links: Map<String, String>
)

@Composable
fun DetailPage(
    itemId: String,
    onBackClick: () -> Unit
) {
    var detailInfo by remember { mutableStateOf<DetailInfo?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // 模拟从网络加载数据
    LaunchedEffect(itemId) {
        detailInfo = fetchDetailInfo(itemId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(detailInfo?.title ?: "加载中...") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "返回")
                    }
                }
            )
        }
    ) { padding ->
        if (detailInfo == null) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = detailInfo!!.description,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary
                    )
                }

                item {
                    Text(
                        text = detailInfo!!.content,
                        style = MaterialTheme.typography.body1
                    )
                }

                item {
                    Text(
                        text = "主要特性",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    detailInfo!!.features.forEach { feature ->
                        Text(
                            text = "• $feature",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }

                item {
                    Text(
                        text = "相关链接",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    detailInfo!!.links.forEach { (title, url) ->
                        Text(
                            text = "• $title: $url",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

private suspend fun fetchDetailInfo(itemId: String): DetailInfo {
    // 模拟网络延迟
    kotlinx.coroutines.delay(1000)
    
    return when (itemId) {
        "kmm" -> DetailInfo(
            title = "Kotlin Multiplatform Mobile",
            description = "使用 Kotlin 开发跨平台应用的现代解决方案",
            content = "Kotlin Multiplatform Mobile (KMM) 是 JetBrains 推出的跨平台移动应用开发解决方案。它允许开发者使用 Kotlin 语言开发 Android 和 iOS 应用，共享业务逻辑代码的同时保持原生用户界面。",
            features = listOf(
                "共享业务逻辑代码",
                "保持原生用户界面",
                "支持现有项目集成",
                "强大的工具支持",
                "完整的测试支持"
            ),
            links = mapOf(
                "官方文档" to "https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html",
                "GitHub" to "https://github.com/JetBrains/kotlin",
                "示例项目" to "https://github.com/JetBrains/KotlinMultiplatformSamples"
            )
        )
        "compose" -> DetailInfo(
            title = "Jetpack Compose",
            description = "Android 现代化 UI 开发工具包",
            content = "Jetpack Compose 是用于构建原生 Android UI 的现代工具包。它使用声明式 UI 模型，让您可以通过调用 Composable 函数来描述应用程序的 UI。",
            features = listOf(
                "声明式 UI 设计",
                "强大的状态管理",
                "内置动画支持",
                "Material Design 组件",
                "与现有 View 系统互操作"
            ),
            links = mapOf(
                "官方文档" to "https://developer.android.com/jetpack/compose",
                "GitHub" to "https://github.com/androidx/androidx",
                "示例项目" to "https://github.com/android/compose-samples"
            )
        )
        "flutter" -> DetailInfo(
            title = "Flutter",
            description = "Google 的跨平台应用开发框架",
            content = "Flutter 是 Google 的开源框架，用于构建美观、原生编译的多平台应用。使用 Dart 语言和丰富的组件库，可以快速开发高性能应用。",
            features = listOf(
                "热重载开发体验",
                "丰富的 Widget 库",
                "高性能渲染引擎",
                "跨平台一致性",
                "自定义设计灵活性"
            ),
            links = mapOf(
                "官方网站" to "https://flutter.dev",
                "GitHub" to "https://github.com/flutter/flutter",
                "Pub.dev" to "https://pub.dev"
            )
        )
        "swiftui" -> DetailInfo(
            title = "SwiftUI",
            description = "Apple 原生的声明式 UI 框架",
            content = "SwiftUI 是 Apple 推出的现代化 UI 框架，用于在所有 Apple 平台上构建用户界面。它采用声明式语法，并与 Swift 语言深度集成。",
            features = listOf(
                "声明式语法",
                "实时预览",
                "自动适配深色模式",
                "无障碍支持",
                "跨 Apple 平台"
            ),
            links = mapOf(
                "开发者文档" to "https://developer.apple.com/xcode/swiftui/",
                "示例代码" to "https://developer.apple.com/documentation/swiftui/",
                "WWDC 视频" to "https://developer.apple.com/videos/swiftui"
            )
        )
        "react-native" -> DetailInfo(
            title = "React Native",
            description = "使用 React 构建原生应用",
            content = "React Native 是 Facebook 开发的开源框架，让您可以使用 JavaScript 和 React 构建原生移动应用。它允许开发者使用相同的代码库构建 iOS 和 Android 应用。",
            features = listOf(
                "使用 JavaScript 开发",
                "热重载支持",
                "原生性能",
                "大型社区支持",
                "丰富的第三方库"
            ),
            links = mapOf(
                "官方文档" to "https://reactnative.dev",
                "GitHub" to "https://github.com/facebook/react-native",
                "Expo" to "https://expo.dev"
            )
        )
        else -> throw IllegalArgumentException("Unknown item id: $itemId")
    }
} 