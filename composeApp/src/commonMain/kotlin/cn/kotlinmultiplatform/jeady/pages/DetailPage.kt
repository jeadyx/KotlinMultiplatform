package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import cn.kotlinmultiplatform.jeady.icons.CustomLink
import cn.kotlinmultiplatform.jeady.icons.CustomSchedule
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DetailInfo(
    val title: String,
    val description: String,
    val content: String,
    val features: List<String>,
    val links: Map<String, String>,
    val author: String = "",
    val publishDate: String = "",
    val readingTime: Int = 0,
    val category: String = "",
    val tags: List<String> = emptyList()
)

@Composable
fun DetailPage(
    itemId: String,
    onBackClick: () -> Unit
) {
    var detailInfo by remember { mutableStateOf<DetailInfo?>(null) }

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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 文章头部信息
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = detailInfo!!.title,
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // 分类和标签
                        if (detailInfo!!.category.isNotEmpty() || detailInfo!!.tags.isNotEmpty()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (detailInfo!!.category.isNotEmpty()) {
                                    CategoryChip(detailInfo!!.category)
                                }
                                detailInfo!!.tags.take(3).forEach { tag ->
                                    TagChip(tag)
                                }
                            }
                        }
                        
                        // 作者信息和发布时间
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (detailInfo!!.author.isNotEmpty()) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Person,
                                        contentDescription = "作者",
                                        modifier = Modifier.size(16.dp),
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Text(
                                        text = detailInfo!!.author,
                                        style = MaterialTheme.typography.subtitle2
                                    )
                                }
                            }
                            
                            if (detailInfo!!.publishDate.isNotEmpty()) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        Icons.Default.DateRange,
                                        contentDescription = "发布日期",
                                        modifier = Modifier.size(16.dp),
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Text(
                                        text = detailInfo!!.publishDate,
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                            }
                            
                            if (detailInfo!!.readingTime > 0) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        Icons.Default.CustomSchedule,
                                        contentDescription = "阅读时间",
                                        modifier = Modifier.size(16.dp),
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Text(
                                        text = "${detailInfo!!.readingTime} min",
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                            }
                        }
                    }
                }

                // 描述
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 2.dp,
                        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f)
                    ) {
                        Text(
                            text = detailInfo!!.description,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }

                // 主要内容
                item {
                    Text(
                        text = detailInfo!!.content,
                        style = MaterialTheme.typography.body1,
                        lineHeight = 24.sp
                    )
                }

                // 主要特性
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 2.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "主要特性",
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary
                            )
                            detailInfo!!.features.forEach { feature ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = null,
                                        tint = MaterialTheme.colors.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = feature,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }
                        }
                    }
                }

                // 相关链接
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 2.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "相关链接",
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary
                            )
                            detailInfo!!.links.forEach { (title, url) ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable { /* TODO: 处理链接点击 */ }
                                ) {
                                    Icon(
                                        Icons.Default.CustomLink,
                                        contentDescription = null,
                                        tint = MaterialTheme.colors.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = title,
                                        style = MaterialTheme.typography.body1,
                                        color = MaterialTheme.colors.primary
                                    )
                                }
                            }
                        }
                    }
                }
                
                // 底部间距
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
private fun CategoryChip(category: String) {
    Surface(
        color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun TagChip(tag: String) {
    Surface(
        color = MaterialTheme.colors.secondary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = "#$tag",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.secondary
        )
    }
}

private suspend fun fetchDetailInfo(itemId: String): DetailInfo {
    // 模拟网络延迟
    kotlinx.coroutines.delay(1000)
    
    // 处理博客文章
    if (itemId.matches(Regex("\\d+"))) {
        return when (itemId) {
            "1" -> DetailInfo(
                title = "Kotlin Multiplatform 开发实践",
                description = "本文将分享在使用 Kotlin Multiplatform 开发跨平台应用时的经验和最佳实践",
                content = """
                    在当今移动应用开发领域，跨平台开发方案越来越受到关注。Kotlin Multiplatform Mobile (KMM) 作为个强大的跨平台开发框架，提供了许多优势：

                    1. 代码共享
                    - 业务逻辑可以在 Android 和 iOS 平台间共享
                    - 减少重复代码，提高开发效率
                    - 降��维护成本

                    2. 性能优势
                    - 接编译为原生代码
                    - 无运行时开销
                    - 更好的内存管理

                    3. 开发体验
                    - 强大的 IDE 支持
                    - 热重载
                    - 丰富的调试工具

                    4. 实践建议
                    - 从小功能开始尝试
                    - 逐步迁移现有代码
                    - 保持模块化设计
                    - 编写单元测试
                """.trimIndent(),
                features = listOf(
                    "代码共享策略",
                    "性能优化技巧",
                    "调试和测试方法",
                    "实际项目经验",
                    "最佳实践总结"
                ),
                links = mapOf(
                    "KMM 官方文档" to "https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html",
                    "示例代码" to "https://github.com/JetBrains/KotlinMultiplatformSamples",
                    "相关博客" to "https://blog.jetbrains.com/kotlin/"
                ),
                author = "张三",
                publishDate = "2024-01-15",
                readingTime = 10,
                category = "技术",
                tags = listOf("Kotlin", "跨平台", "移动开发")
            )
            "2" -> DetailInfo(
                title = "现代化 UI 设计趋势",
                description = "探讨2024年UI设计的新趋势，包括Neumorphism、玻璃态设计等新兴设计风格",
                content = """
                    2024年的UI设计领域正在经历一场革新，新的设计趋势不断涌现：

                    1. Neumorphism（新拟物设计）
                    - 柔和的阴影效果
                    - 真实感的质地表现
                    - 适度的深浅对比

                    2. 玻璃态设计（Glassmorphism）
                    - 磨砂玻璃效果
                    - 半透明层叠
                    - 景深表现

                    3. 暗色模式优化
                    - 护眼配色方案
                    - 层次感处理
                    - 关键信息突出

                    4. 响应式动效
                    - 流畅的渡动画
                    - 交互反馈
                    - 微动效设计
                """.trimIndent(),
                features = listOf(
                    "新拟物设计实践",
                    "玻璃态效果应用",
                    "暗��模式设计",
                    "动效设计指南",
                    "配色方案推荐"
                ),
                links = mapOf(
                    "设计灵感" to "https://dribbble.com",
                    "设计资源" to "https://www.figma.com/community",
                    "教程分享" to "https://www.youtube.com/c/DesignCourse"
                ),
                author = "李四",
                publishDate = "2024-01-18",
                readingTime = 8,
                category = "设计",
                tags = listOf("UI", "设计趋势", "用户体验")
            )
            "3" -> DetailInfo(
                title = "程序员的成长之路",
                description = "分享一个程序员从初级到高级的成长经历，以及在这过程中的思考和感悟",
                content = """
                    作为一名程序员，职业发展道路上有许多重要的里程碑：

                    1. 技术积累阶段
                    - 夯实基础知
                    - 熟练掌握具
                    - 参与实际项目

                    2. 架构思维培养
                    - 系统设计能力
                    - 代码重构验
                    - 性能优化意识

                    3. 团队协作能力
                    - 代码审查
                    - 技术共享
                    - 项目管理

                    4. 持续学习
                    - 关注新技术
                    - 阅读源码
                    - 参与开源项目
                """.trimIndent(),
                features = listOf(
                    "技术学习路线",
                    "架构设计思维",
                    "团队协作技巧",
                    "职业规划建议",
                    "学习方法分享"
                ),
                links = mapOf(
                    "技术社区" to "https://github.com",
                    "学习资源" to "https://www.coursera.org",
                    "技术博客" to "https://medium.com/tag/programming"
                )
            )
            "4" -> DetailInfo(
                title = "SwiftUI 与 Jetpack Compose 对比",
                description = "深入分析两大现代声明式UI框架的异同，探讨它们在实际项目中的应用场景和优劣势",
                content = """
                    现代声明式UI框架正在改变移动应用开发的方式。本文将从多个角度对比 SwiftUI 和 Jetpack Compose：

                    1. 开发范式
                    - 声明式 vs 命令式
                    - 状态管理方式
                    - 生命周期处理

                    2. 性能表现
                    - 渲染机制
                    - 内存占用
                    - 启动时间

                    3. 开发体验
                    - 工具支持
                    - 热重载
                    - 调试能力

                    4. 生态系统
                    - 组件库
                    - 第三方支持
                    - 社区活跃度
                """.trimIndent(),
                features = listOf(
                    "深入框架原理",
                    "性能对比分析",
                    "最佳实践指南",
                    "迁移策略建议",
                    "案例研究"
                ),
                links = mapOf(
                    "SwiftUI 文档" to "https://developer.apple.com/xcode/swiftui/",
                    "Compose 文档" to "https://developer.android.com/jetpack/compose",
                    "示例项目" to "https://github.com/topics/ui-framework"
                )
            )
            "5" -> DetailInfo(
                title = "设计系统的构建之道",
                description = "如何从零开始构建一个完整的设计系统？本文将分享设计系统的规划实施和维护经验",
                content = """
                    设计系统是现代产品设计中不可或缺的一部分，它能确保产品设计的一致性和效率：

                    1. 设计系统基础
                    - 设计原则制定
                    - 组件库规划
                    - 设计标准化

                    2. 技术实现
                    - 组件开发
                    - 样式管理
                    - 版本控制

                    3. 团队协作
                    - 设计开发协作
                    - 文档管理
                    - 反馈机制

                    4. 持续优化
                    - 使用分析
                    - 性能监控
                    - 迭代更新
                """.trimIndent(),
                features = listOf(
                    "设计系统规划",
                    "组件库开发",
                    "团队协作流程",
                    "维护更新机制",
                    "实践案例分析"
                ),
                links = mapOf(
                    "Material Design" to "https://material.io/design",
                    "Ant Design" to "https://ant.design",
                    "设计系统实例" to "https://designsystemsrepo.com"
                )
            )
            "6" -> DetailInfo(
                title = "远程工作的效率之道",
                description = "在后疫情时代，远程办公已成为新常态。如何提高远程工作效率？如何保持工作与生活的平衡？",
                content = """
                    远程工作已经成为一种新常态，如何在这种模式下保持高效率是每个人都需要思考的问题：

                    1. 工作环境布置
                    - 专业的办公设备
                    - 舒适的工作空间
                    - 稳定的网络环境

                    2. 时间管理
                    - 明确的工作界限
                    - 科学的休息安排
                    - 高效的会议控制

                    3. 沟通协作
                    - 异步沟通技巧
                    - 文档驱动开发
                    - 团队同步机制

                    4. 自我管理
                    - 目标设定
                    - 进度跟踪
                    - 成果展示
                """.trimIndent(),
                features = listOf(
                    "环境配置指南",
                    "时间管理方法",
                    "沟通协作技巧",
                    "自我管理策略",
                    "工具推荐"
                ),
                links = mapOf(
                    "远程工作指南" to "https://about.gitlab.com/company/culture/all-remote/",
                    "效率工具" to "https://www.notion.so",
                    "团队协作" to "https://slack.com"
                )
            )
            "7" -> DetailInfo(
                title = "AI 驱动的代码生成实践",
                description = "探索 AI 在软件开发中的应用，从代码补全到自动化测试，AI 如何提升开发效率",
                content = """
                    AI 技术正在深刻改变软件开发的方式，让我们看看它能为开发者带来哪些改变：

                    1. 代码生成
                    - 智能代码补���
                    - 自动重构建议
                    - 测试用例生成

                    2. 开发辅助
                    - 代码审查
                    - Bug 预测
                    - 性能优化建议

                    3. 文档生成
                    - 注释生成
                    - API 文档
                    - 变更说明

                    4. 未来展望
                    - 自然语言编程
                    - 智能调试
                    - 自动化部署
                """.trimIndent(),
                features = listOf(
                    "AI 工具应用",
                    "效率提升方法",
                    "最佳实践分享",
                    "未来趋势分析",
                    "案例研究"
                ),
                links = mapOf(
                    "GitHub Copilot" to "https://github.com/features/copilot",
                    "OpenAI API" to "https://openai.com/api/",
                    "AI 开发工具" to "https://cursor.sh"
                )
            )
            "8" -> DetailInfo(
                title = "写给新手设计师的建议",
                description = "从个人经验出发，分享设计师职业发展的经验和建议，帮助新手快速成长",
                content = """
                    成为一名优秀的设计师需要持续的学习和积累，这里是一些重要的建议：

                    1. 基础技能
                    - 设计理论
                    - 工具掌握
                    - 版式设计

                    2. 设计思维
                    - 用户思维
                    - 商业思维
                    - 系统思维

                    3. 项目实践
                    - 个人项目
                    - 实战练习
                    - 作品集打造

                    4. 职业发展
                    - 技能提升
                    - 人脉建设
                    - 行业洞察
                """.trimIndent(),
                features = listOf(
                    "入门指南",
                    "技能提升路线",
                    "实战经验分享",
                    "资源推荐",
                    "常见问题解答"
                ),
                links = mapOf(
                    "设计社区" to "https://www.behance.net",
                    "学习资源" to "https://www.skillshare.com",
                    "设计工具" to "https://www.figma.com"
                )
            )
            "9" -> DetailInfo(
                title = "构建高���能的跨平台应用",
                description = "探讨在跨平台开发中如何保持应用的高性能，包括内存优化、渲染优化等技术细节",
                content = """
                    跨平台应用的性能优化是一个永恒的话题，让我们深入探讨一些关键技术：

                    1. 性能基础
                    - 启动优化
                    - 内存管理
                    - 渲染性能

                    2. 架构优化
                    - 模块化设计
                    - 懒加载策略
                    - 缓存机制

                    3. 网络优化
                    - 请求合并
                    - 离线缓存
                    - 断点续传

                    4. 监控分析
                    - 性能指标
                    - 监控系统
                    - 优化工具
                """.trimIndent(),
                features = listOf(
                    "性能优化方法",
                    "架构设计技巧",
                    "监控系统搭建",
                    "调试工具使用",
                    "案例分析"
                ),
                links = mapOf(
                    "性能工具" to "https://developer.chrome.com/docs/devtools/",
                    "监控平台" to "https://firebase.google.com/products/performance",
                    "最佳实践" to "https://web.dev/performance-optimizing-content-efficiency/"
                )
            )
            "10" -> DetailInfo(
                title = "我的极简主义生活实践",
                description = "分享如何将极简主义理念应用到生活，提升生活品质，减少物质和精神的负担",
                content = """
                    极简主义不仅是一种生活方式，更是一种人生哲学：

                    1. 物质层面
                    - 断舍离实践
                    - 物品管理
                    - 空间利用

                    2. 时间管理
                    - 优先级设定
                    - 专注工作
                    - 休闲安排

                    3. 心理建设
                    - 压力管理
                    - 情绪控制
                    - 心灵成长

                    4. 社交关系
                    - 关系筛选
                    - 深度交往
                    - 边界设定
                """.trimIndent(),
                features = listOf(
                    "极简理念",
                    "实践方法",
                    "心得体会",
                    "效果反馈",
                    "建议分享"
                ),
                links = mapOf(
                    "极简主义" to "https://www.theminimalists.com",
                    "生活方式" to "https://zenhabits.net",
                    "实践指南" to "https://www.becomingminimalist.com"
                )
            )
            else -> throw IllegalArgumentException("Unknown blog post id: $itemId")
        }
    }
    
    // 处理推荐项目
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
                "声明式 UI ��计",
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
            content = "Flutter 是 Google 的开源框架，用于构建美观、原生编译的多平台应用。使用 Dart 语言和丰富的组件库，可以速开发高性能应用。",
            features = listOf(
                "热重载开发体验",
                "丰富的 Widget 库",
                "高性能渲染引擎",
                "跨平台一致性",
                "定义设计灵活性"
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
            content = "React Native 是 Facebook 开发的开源框架，让您可以使用 JavaScript 和 React 构建原生移动应用。它允许开发者用相同的代码库构建 iOS 和 Android 应用。",
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