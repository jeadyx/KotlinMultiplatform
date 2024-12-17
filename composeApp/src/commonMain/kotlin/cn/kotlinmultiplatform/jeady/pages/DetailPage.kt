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
                    - 降维护成本

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
                description = "探讨2024年UI设计的新趋势，括Neumorphism、玻璃态设计等新兴设计风格",
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
                    "暗色模式设计",
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
                    - 夯实基础知识
                    - 熟练掌握工具
                    - 参与实际项目

                    2. 架构思维培养
                    - 系统设计能力
                    - 代码重构经验
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
                    - 启时间

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
                title = "设计系统的构建之",
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
                description = "在后疫情时代，远程办公已成为新常态��如何提高远程工作效率？如何保持工作与生活的平衡？",
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
                    "环境配置南",
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
                    - 智能代码补全
                    - 自动重构建议
                    - 测试用例生成

                    2. 开发辅助
                    - 代码审查
                    - Bug 预测
                    - 性能优化建议

                    3. 文档生成
                    - 注释生成
                    - API 文档
                    - 变更���明

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
                title = "构建高性能的跨平台应用",
                description = "探讨在跨平台开发中如何保持应用的高性能，包括内存优化、渲染优化等技术细节",
                content = """
                    跨平台应用的性能优化是一个永恒的话题，让我们深入探讨一些关键技术：

                    1. 性能基础
                    - 启动优
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
                ),
                author = "陈十二",
                publishDate = "2024-01-24",
                readingTime = 12,
                category = "生活",
                tags = listOf("极简主义", "生活方式", "心得体会")
            )
            "11" -> DetailInfo(
                title = "函数式编程在现代开发中的应用",
                description = "探讨函数式编程范式在现代软件开发中的实际应用场景和优势",
                content = """
                    函数式编程正在成为现代软件开发中不可或缺的一部分：

                    1. 基础概念
                    - 纯函数
                    - 不可变性
                    - 高阶函数

                    2. 实践应用
                    - 状态管理
                    - 并发编程
                    - 错误处理

                    3. 性能优化
                    - 惰性求值
                    - 内存优化
                    - 并行计算

                    4. 工程实践
                    - 代码组织
                    - 测试策略
                    - 重构技巧
                """.trimIndent(),
                features = listOf(
                    "函数式编程基础",
                    "实践应用案例",
                    "性能优化技巧",
                    "工程化实践",
                    "最佳实践��结"
                ),
                links = mapOf(
                    "函数式编程" to "https://www.scala-lang.org",
                    "实践指南" to "https://mostly-adequate.gitbook.io",
                    "示例代码" to "https://github.com/topics/functional-programming"
                ),
                author = "林一",
                publishDate = "2024-01-25",
                readingTime = 15,
                category = "技术",
                tags = listOf("函数式编程", "软件架构", "最佳实践")
            )
            "12" -> DetailInfo(
                title = "云原生应用开发指南",
                description = "深入探讨云原生应用开发的核心概念、最佳实践和工具链",
                content = """
                    云原生已经成为现代应用开发的标准范式：

                    1. 核心理念
                    - 微服务架构
                    - 容器化部署
                    - 自动化运维

                    2. 技术栈选择
                    - 容器编排
                    - 服务网格
                    - 监控告警

                    3. 开发流程
                    - CI/CD 实践
                    - 测试策略
                    - 发布管理

                    4. 最佳实践
                    - 高可用设计
                    - 弹性伸缩
                    - 故障恢复
                """.trimIndent(),
                features = listOf(
                    "云原生架构",
                    "容器化部署",
                    "微服务设计",
                    "DevOps实践",
                    "监控运维"
                ),
                links = mapOf(
                    "Kubernetes" to "https://kubernetes.io",
                    "Docker" to "https://www.docker.com",
                    "云原生基金会" to "https://www.cncf.io"
                ),
                author = "王云",
                publishDate = "2024-01-26",
                readingTime = 18,
                category = "技术",
                tags = listOf("云原生", "微服务", "DevOps")
            )
            "13" -> DetailInfo(
                title = "移动应用性能优化实战",
                description = "全面介绍移动应用性能优化的方法论和实践技巧",
                content = """
                    性能优化是移动应用成功的关键因素：

                    1. 启动优化
                    - 冷启动优化
                    - 热启动优化
                    - ��加载策略

                    2. 内存管理
                    - 内存泄漏检测
                    - 缓存策略
                    - GC优化

                    3. 渲染性能
                    - UI渲染优化
                    - 列表优化
                    - 动画优化

                    4. 网络优化
                    - 请求优化
                    - 缓存策略
                    - 弱网优化
                """.trimIndent(),
                features = listOf(
                    "启动速度优化",
                    "内存管理优化",
                    "渲染性能优化",
                    "网络性能优化",
                    "性能监控"
                ),
                links = mapOf(
                    "Android性能优化" to "https://developer.android.com/topic/performance",
                    "iOS性能优化" to "https://developer.apple.com/documentation/xcode/improving-your-app-s-performance",
                    "性能工具" to "https://perfetto.dev"
                ),
                author = "李性能",
                publishDate = "2024-01-27",
                readingTime = 20,
                category = "技术",
                tags = listOf("性能优化", "移动开发", "用户体验")
            )
            "14" -> DetailInfo(
                title = "深入理解设计模式",
                description = "通过实际案例深入讲解常用设计模式的应用场景和实现方法",
                content = """
                    设计模式是解决软件设计中常见问题的最佳实践：

                    1. 创建型模式
                    - 单例模式
                    - 工厂模式
                    - 建造者模式

                    2. 结构型模式
                    - 适配器模式
                    - 装饰器模式
                    - 代理模式

                    3. 行为型模式
                    - 观察者模式
                    - 策略模式
                    - 命令模式

                    4. 实践应用
                    - 模式选择
                    - 实现技巧
                    - 注意事项
                """.trimIndent(),
                features = listOf(
                    "设计模式原理",
                    "实际应用案例",
                    "代码实现",
                    "最佳实践",
                    "常见陷阱"
                ),
                links = mapOf(
                    "设计模式" to "https://refactoring.guru/design-patterns",
                    "示例代码" to "https://github.com/topics/design-patterns",
                    "在线课程" to "https://www.coursera.org/learn/design-patterns"
                ),
                author = "张模式",
                publishDate = "2024-01-28",
                readingTime = 25,
                category = "技术",
                tags = listOf("设计模式", "软件设计", "架构")
            )
            "15" -> DetailInfo(
                title = "WebAssembly 开发实践",
                description = "探索 WebAssembly 在现代 Web 开发中的应用和最佳实践",
                content = """
                    WebAssembly 正在改变 Web 应用的开发方式：

                    1. 基础概念
                    - WASM 原理
                    - 内存模型
                    - 性能特性

                    2. 开发工具
                    - 编译工具链
                    - 调试工具
                    - 性能分析

                    3. 应用场景
                    - 游戏开发
                    - 图像处理
                    - 音视频处理

                    4. 最佳实践
                    - 性能优化
                    - 内存管理
                    - 错误处理
                """.trimIndent(),
                features = listOf(
                    "WASM基础",
                    "工具链使用",
                    "性能优化",
                    "实战案例",
                    "调试技巧"
                ),
                links = mapOf(
                    "WebAssembly" to "https://webassembly.org",
                    "Emscripten" to "https://emscripten.org",
                    "WASM工具" to "https://wasmtime.dev"
                ),
                author = "吴汇编",
                publishDate = "2024-01-29",
                readingTime = 22,
                category = "技术",
                tags = listOf("WebAssembly", "Web开发", "性能优化")
            )
            "16" -> DetailInfo(
                title = "GraphQL API 设计与实践",
                description = "深入探讨 GraphQL API 的设计原则、最佳实践和性能优化",
                content = """
                    GraphQL 正在改变API开发的方式：

                    1. 核心概念
                    - Schema 设计
                    - 类型系统
                    - 解析器实现

                    2. 性能优化
                    - 查询优化
                    - 缓存策略
                    - N+1问题解决

                    3. 安全考虑
                    - 认证授权
                    - 查询复杂度
                    - 限流策略

                    4. 最佳实践
                    - API版本管理
                    - 错误处理
                    - 文档生成
                """.trimIndent(),
                features = listOf(
                    "Schema设计",
                    "性能优化",
                    "安全实践",
                    "工具使用",
                    "最佳实践"
                ),
                links = mapOf(
                    "GraphQL官网" to "https://graphql.org",
                    "Apollo" to "https://www.apollographql.com",
                    "GraphQL工具" to "https://github.com/chentsulin/awesome-graphql"
                ),
                author = "李图",
                publishDate = "2024-01-30",
                readingTime = 16,
                category = "技术",
                tags = listOf("GraphQL", "API设计", "后端开发")
            )
            "17" -> DetailInfo(
                title = "移动应用安全开发指南",
                description = "全面介绍移动应用安全开发的原则、实践和常见漏洞防范",
                content = """
                    安全性是移动应用开发中不可忽视的关键要素：

                    1. 数据安全
                    - 数据加密
                    - 安全存储
                    - 传输安全

                    2. 认证授权
                    - 身份认证
                    - 访问控制
                    - 会话管理

                    3. 代码安全
                    - 混淆加固
                    - 漏洞防护
                    - 安全检测

                    4. 运行时安全
                    - 环境检测
                    - 完整性校验
                    - 调试防护
                """.trimIndent(),
                features = listOf(
                    "安全开发实践",
                    "漏洞防护",
                    "加密技术",
                    "安全测试",
                    "合规要求"
                ),
                links = mapOf(
                    "OWASP Mobile" to "https://owasp.org/www-project-mobile-security/",
                    "安全开发" to "https://developer.android.com/training/articles/security-tips",
                    "iOS安全" to "https://developer.apple.com/documentation/security"
                ),
                author = "张安全",
                publishDate = "2024-01-31",
                readingTime = 19,
                category = "技术",
                tags = listOf("安全开发", "移动开发", "最佳实践")
            )
            "18" -> DetailInfo(
                title = "深度学习在移动端的实践",
                description = "探索深度学习模型在移动应用中的部署和优化技术",
                content = """
                    将深度学习应用到移动端面临诸多挑战：

                    1. 模型优化
                    - 模型量
                    - 模型裁剪
                    - 知识蒸馏

                    2. 性能优化
                    - 推理加速
                    - 内存优化
                    - 功耗控制

                    3. 框架选择
                    - TensorFlow Lite
                    - PyTorch Mobile
                    - Core ML

                    4. 实践案例
                    - 图像识别
                    - 自然语言处理
                    - 语音识别
                """.trimIndent(),
                features = listOf(
                    "模型优化",
                    "性能调优",
                    "框架使用",
                    "实战案例",
                    "最佳实践"
                ),
                links = mapOf(
                    "TensorFlow Lite" to "https://www.tensorflow.org/lite",
                    "PyTorch Mobile" to "https://pytorch.org/mobile",
                    "Core ML" to "https://developer.apple.com/machine-learning"
                ),
                author = "王智能",
                publishDate = "2024-02-01",
                readingTime = 21,
                category = "技术",
                tags = listOf("深度学习", "移动开发", "AI应用")
            )
            "19" -> DetailInfo(
                title = "响应式编程实战指南",
                description = "深入理解响应式编程范式，掌握RxJava、Reactor等框架的使用",
                content = """
                    响应式编程正在改变我们处理异步数据流的方式：

                    1. 基础概念
                    - 响应式流
                    - 背压处理
                    - 操作符

                    2. 实践应用
                    - 异步处理
                    - 并发控制
                    - 错误处理

                    3. 性能优化
                    - 线程调度
                    - 内存管理
                    - 性能监控

                    4. 框架使用
                    - RxJava
                    - Reactor
                    - Combine
                """.trimIndent(),
                features = listOf(
                    "响应式原理",
                    "框架使用",
                    "实战技巧",
                    "性能优化",
                    "最佳实践"
                ),
                links = mapOf(
                    "ReactiveX" to "http://reactivex.io",
                    "Project Reactor" to "https://projectreactor.io",
                    "RxJava" to "https://github.com/ReactiveX/RxJava"
                ),
                author = "刘响应",
                publishDate = "2024-02-02",
                readingTime = 17,
                category = "技术",
                tags = listOf("响应式编程", "异步编程", "并发控制")
            )
            "20" -> DetailInfo(
                title = "微前端架构实践",
                description = "探索微前端架构的设计原则、实现方案和最佳实践",
                content = """
                    微前端是一种新型的前端架构模式：

                    1. 核心概念
                    - 应用拆分
                    - 独立部署
                    - 技术栈无关

                    2. 实现方案
                    - 基座模式
                    - 路由分发
                    - 沙箱隔离

                    3. 通信机制
                    - 状态共享
                    - 事件总线
                    - 数据通信

                    4. 工程实践
                    - 构建优化
                    - 部署策略
                    - 监控方案
                """.trimIndent(),
                features = listOf(
                    "架构设计",
                    "实现方案",
                    "通信机制",
                    "工程化实践",
                    "性能优化"
                ),
                links = mapOf(
                    "Single-SPA" to "https://single-spa.js.org",
                    "Qiankun" to "https://qiankun.umijs.org",
                    "Micro Frontends" to "https://micro-frontends.org"
                ),
                author = "陈微",
                publishDate = "2024-02-03",
                readingTime = 20,
                category = "技术",
                tags = listOf("微前端", "前端架构", "工程化")
            )
            "21" -> DetailInfo(
                title = "服务网格技术实践",
                description = "深入探讨服务网格技术在微服务架构中的应用和最佳实践",
                content = """
                    服务网格为微服务架构提供了强大的基础设施层：

                    1. 核心功能
                    - 流量管理
                    - 安全通信
                    - 可观测性

                    2. 实现原理
                    - Sidecar模式
                    - 控制平面
                    - 数据平面

                    3. 部署策略
                    - 渐进式迁移
                    - 版本管理
                    - 故障处理

                    4. 性能优化
                    - 资源配置
                    - 网络优化
                    - 监控告警
                """.trimIndent(),
                features = listOf(
                    "服务网格原理",
                    "部署策略",
                    "性能优化",
                    "最佳实践",
                    "故障处理"
                ),
                links = mapOf(
                    "Istio" to "https://istio.io",
                    "Linkerd" to "https://linkerd.io",
                    "Service Mesh" to "https://servicemesh.io"
                ),
                author = "李网格",
                publishDate = "2024-02-04",
                readingTime = 23,
                category = "技术",
                tags = listOf("服务网格", "微服务", "云原生")
            )
            "22" -> DetailInfo(
                title = "Flutter状态管理最佳实践",
                description = "探讨Flutter应用中各种状态管理方案的优劣及实践经验",
                content = """
                    状态管理是Flutter应用开发中的关键挑战：

                    1. 状��管理方案
                    - Provider
                    - Riverpod
                    - Bloc
                    - GetX

                    2. 应用场景
                    - 简单状态
                    - 复杂状态
                    - 全局状态
                    - 本地持久化

                    3. 性能考虑
                    - 重建优化
                    - 内存管理
                    - 响应速度

                    4. 最佳实践
                    - 架构设计
                    - 代码组织
                    - 测试策略
                """.trimIndent(),
                features = listOf(
                    "状态管理方案",
                    "性能优化",
                    "架构设计",
                    "测试策略",
                    "最佳实践"
                ),
                links = mapOf(
                    "Provider" to "https://pub.dev/packages/provider",
                    "Riverpod" to "https://riverpod.dev",
                    "Bloc" to "https://bloclibrary.dev"
                ),
                author = "张状态",
                publishDate = "2024-02-05",
                readingTime = 18,
                category = "技术",
                tags = listOf("Flutter", "状态管理", "移动开发")
            )
            "23" -> DetailInfo(
                title = "Kotlin协程高级应用",
                description = "深入探讨Kotlin协程的高级特性和实践技巧",
                content = """
                    Kotlin协程提供了强大的异步编程能力：

                    1. 基础概念
                    - 协程作用域
                    - 调度器
                    - 上下文

                    2. 高级特性
                    - 流
                    - 通道
                    - 异常处理

                    3. 性能优化
                    - 并发控制
                    - 内存优化
                    - 取消机制

                    4. 实践应用
                    - 网络请求
                    - 后台任务
                    - UI更新
                """.trimIndent(),
                features = listOf(
                    "协程基础",
                    "高级特性",
                    "性能优化",
                    "实践应用",
                    "最佳实践"
                ),
                links = mapOf(
                    "Kotlin协程" to "https://kotlinlang.org/docs/coroutines-overview.html",
                    "协程示例" to "https://github.com/Kotlin/kotlinx.coroutines",
                    "实践指南" to "https://kotlin.github.io/kotlinx.coroutines/"
                ),
                author = "王协程",
                publishDate = "2024-02-06",
                readingTime = 21,
                category = "技术",
                tags = listOf("Kotlin", "协程", "异步编程")
            )
            "24" -> DetailInfo(
                title = "持续集成与持续部署实践",
                description = "探讨现代软件开发中CI/CD的最佳实践和工具链",
                content = """
                    CI/CD是现代软件开发流程中不可或缺的一环：

                    1. 持续集成
                    - 自动化构建
                    - 代码质量
                    - 单元测试

                    2. 持续部署
                    - 环境管理
                    - 部署策略
                    - 回滚机制

                    3. 工具链
                    - Jenkins
                    - GitLab CI
                    - GitHub Actions

                    4. 最佳实践
                    - 流程优化
                    - 安全集成
                    - 监控反馈
                """.trimIndent(),
                features = listOf(
                    "CI/CD流程",
                    "工具使用",
                    "最佳实践",
                    "自动化测试",
                    "部署策略"
                ),
                links = mapOf(
                    "Jenkins" to "https://www.jenkins.io",
                    "GitLab CI" to "https://docs.gitlab.com/ee/ci/",
                    "GitHub Actions" to "https://github.com/features/actions"
                ),
                author = "陈部署",
                publishDate = "2024-02-07",
                readingTime = 19,
                category = "技术",
                tags = listOf("CI/CD", "DevOps", "自动化")
            )
            "25" -> DetailInfo(
                title = "移动应用测试自动化",
                description = "全面介绍移动应用测试自动化的策略、工具和最佳实践",
                content = """
                    测试自动化是保证应用质量的关键：

                    1. 测试策略
                    - 单元测试
                    - 集成测试
                    - UI测试

                    2. 自动化工具
                    - Appium
                    - Espresso
                    - XCTest

                    3. 持续测试
                    - 测试流程
                    - 报告生成
                    - 问题跟踪

                    4. 最佳实践
                    - 测试用例设计
                    - 测试数据管理
                    - 环境配置
                """.trimIndent(),
                features = listOf(
                    "测试策略",
                    "工具使用",
                    "持续测试",
                    "最佳实践",
                    "质量保证"
                ),
                links = mapOf(
                    "Appium" to "https://appium.io",
                    "Espresso" to "https://developer.android.com/training/testing/espresso",
                    "XCTest" to "https://developer.apple.com/documentation/xctest"
                ),
                author = "刘测试",
                publishDate = "2024-02-08",
                readingTime = 17,
                category = "技术",
                tags = listOf("测试自动化", "质量保证", "移动开发")
            )
            "26" -> DetailInfo(
                title = "区块链应用开发实践",
                description = "探索区块链技术在实际应用中的开发和落地实践",
                content = """
                    区块链技术正在改变传统应用的开发方式：

                    1. 基础架构
                    - 共识机制
                    - 智能合约
                    - 分布式存储

                    2. 开发框架
                    - Ethereum
                    - Hyperledger
                    - Solana

                    3. 安全考虑
                    - 合约审计
                    - 密钥管理
                    - 攻击防护

                    4. 性能优化
                    - 交易处理
                    - 数���存储
                    - 网络通信
                """.trimIndent(),
                features = listOf(
                    "区块链原理",
                    "智能合约",
                    "安全开发",
                    "性能优化",
                    "最佳实践"
                ),
                links = mapOf(
                    "Ethereum" to "https://ethereum.org",
                    "Hyperledger" to "https://www.hyperledger.org",
                    "Web3.js" to "https://web3js.readthedocs.io"
                ),
                author = "吴链",
                publishDate = "2024-02-09",
                readingTime = 24,
                category = "技术",
                tags = listOf("区块链", "智能合约", "Web3")
            )
            "27" -> DetailInfo(
                title = "微服务监控与可观测性",
                description = "深入探讨微服务架构中的监控、追踪和日志管理",
                content = """
                    可观测性是微服务架构的重要支柱：

                    1. 监控指标
                    - 系统指标
                    - 业务指标
                    - 性能指标

                    2. 分布式追踪
                    - 链路追踪
                    - 性能分析
                    - 异常定位

                    3. 日志管理
                    - 集中式日志
                    - 日志分析
                    - 告警策略

                    4. 可视化
                    - 监控面板
                    - 追踪视图
                    - 报表统计
                """.trimIndent(),
                features = listOf(
                    "监控系统",
                    "链路追踪",
                    "日志管理",
                    "告警策略",
                    "可视化分析"
                ),
                links = mapOf(
                    "Prometheus" to "https://prometheus.io",
                    "Grafana" to "https://grafana.com",
                    "Jaeger" to "https://www.jaegertracing.io"
                ),
                author = "张监控",
                publishDate = "2024-02-10",
                readingTime = 20,
                category = "技术",
                tags = listOf("微服务", "监控", "可观测性")
            )
            "28" -> DetailInfo(
                title = "移动应用架构演进",
                description = "探讨移动应用架构的发展历程和最佳实践",
                content = """
                    移动应用架构在不断演进：

                    1. 传统架构
                    - MVC
                    - MVP
                    - MVVM

                    2. 现代架构
                    - Clean Architecture
                    - 组件化
                    - 插件化

                    3. 响应式架构
                    - 单向数据流
                    - 状态管理
                    - 副作用处理

                    4. 跨平台架构
                    - 混合开发
                    - 原生开发
                    - 跨平台框架
                """.trimIndent(),
                features = listOf(
                    "架构演进",
                    "设计模式",
                    "最佳实践",
                    "性能优化",
                    "开发效率"
                ),
                links = mapOf(
                    "Clean Architecture" to "https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html",
                    "Android架构" to "https://developer.android.com/topic/architecture",
                    "iOS架构" to "https://developer.apple.com/documentation/swift/swift_standard_library/architectural_design"
                ),
                author = "李架构",
                publishDate = "2024-02-11",
                readingTime = 22,
                category = "技术",
                tags = listOf("移动开发", "架构设计", "最佳实践")
            )
            "29" -> DetailInfo(
                title = "容器化应用开发与部署",
                description = "全面介绍容器技术在现代应用开发和部署中的应用",
                content = """
                    容器化正在改变应用的开发和部署方式：

                    1. 容器基础
                    - Docker原理
                    - 镜像管理
                    - 网络配置

                    2. 容器编排
                    - Kubernetes
                    - Docker Swarm
                    - 服务发现

                    3. 持续部署
                    - 自动化构建
                    - 镜像发布
                    - 滚动更新

                    4. 最佳实践
                    - 镜像优化
                    - 安全加固
                    - 监控管理
                """.trimIndent(),
                features = listOf(
                    "容器技术",
                    "编排管理",
                    "自动化部署",
                    "性能优化",
                    "安全实践"
                ),
                links = mapOf(
                    "Docker" to "https://www.docker.com",
                    "Kubernetes" to "https://kubernetes.io",
                    "容器安全" to "https://docs.docker.com/engine/security/"
                ),
                author = "王容器",
                publishDate = "2024-02-12",
                readingTime = 19,
                category = "技术",
                tags = listOf("容器化", "Docker", "Kubernetes")
            )
            "30" -> DetailInfo(
                title = "边缘计算与物联网开发",
                description = "探索边缘计算在物联网应用中的实践和最佳方案",
                content = """
                    边缘计算为物联网带来新的可能：

                    1. 边缘计算
                    - 架构设计
                    - 数据处理
                    - 实时响应

                    2. 物联网协议
                    - MQTT
                    - CoAP
                    - WebSocket

                    3. 设备管理
                    - 设备接入
                    - 远程控制
                    - 固件更新

                    4. 数据处理
                    - 实时分析
                    - 本地存储
                    - 云端同步
                """.trimIndent(),
                features = listOf(
                    "边缘计算",
                    "协议实现",
                    "设备管理",
                    "数据处理",
                    "安全防护"
                ),
                links = mapOf(
                    "EdgeX" to "https://www.edgexfoundry.org",
                    "MQTT" to "https://mqtt.org",
                    "IoT安全" to "https://owasp.org/www-project-internet-of-things/"
                ),
                author = "陈边缘",
                publishDate = "2024-02-13",
                readingTime = 21,
                category = "技术",
                tags = listOf("边缘计算", "物联网", "实时处理")
            )
            "31" -> DetailInfo(
                title = "低代码平台开发实践",
                description = "探索低代码平台的设计理念、架构实现和最佳实践",
                content = """
                    低代码平台正在改变软件开发的方式：

                    1. 平台架构
                    - 可视化设计
                    - 组件体系
                    - 数据流转

                    2. 核心功能
                    - 页面设计器
                    - 流程编排
                    - 数据模型

                    3. 扩展机制
                    - 自定义组件
                    - 插件系统
                    - API集成

                    4. 最佳实践
                    - 性能优化
                    - 安全控制
                    - 版本管理
                """.trimIndent(),
                features = listOf(
                    "平台架构",
                    "核心功能",
                    "扩展机制",
                    "最佳实践",
                    "性能优化"
                ),
                links = mapOf(
                    "OutSystems" to "https://www.outsystems.com",
                    "Mendix" to "https://www.mendix.com",
                    "Power Platform" to "https://powerplatform.microsoft.com"
                ),
                author = "王低码",
                publishDate = "2024-02-14",
                readingTime = 18,
                category = "技术",
                tags = listOf("低代码", "可视化开发", "效率工具")
            )
            "32" -> DetailInfo(
                title = "数据可视化开发指南",
                description = "深入探讨数据可视化的设计原则、技术实现和最佳实践",
                content = """
                    数据可视化是数据分析的重要手段：

                    1. 可视化原理
                    - 图表类型
                    - 交互设计
                    - 布局排版

                    2. 技术实现
                    - SVG绘制
                    - Canvas性能
                    - WebGL加速

                    3. 框架选择
                    - ECharts
                    - D3.js
                    - Three.js

                    4. 最佳实践
                    - 性能优化
                    - 响应式设计
                    - 主题定制
                """.trimIndent(),
                features = listOf(
                    "可视化原理",
                    "技术实现",
                    "框架使用",
                    "性能优化",
                    "最佳实践"
                ),
                links = mapOf(
                    "ECharts" to "https://echarts.apache.org",
                    "D3.js" to "https://d3js.org",
                    "Three.js" to "https://threejs.org"
                ),
                author = "李可视",
                publishDate = "2024-02-15",
                readingTime = 20,
                category = "技术",
                tags = listOf("数据可视化", "前端开发", "图表绘制")
            )
            "33" -> DetailInfo(
                title = "实时音视频开发实践",
                description = "探索实时音视频应用的开发技术和最佳实践",
                content = """
                    实时音视频技术正在改变通信方式：

                    1. 基础架构
                    - 媒体采集
                    - 编解码
                    - 传输协议

                    2. 核心技术
                    - 音频处理
                    - 视频处理
                    - 网络传输

                    3. 质量优化
                    - 延迟控制
                    - 丢包恢复
                    - 带宽适应

                    4. 最佳实践
                    - 性能优化
                    - 弱网优化
                    - 电量优化
                """.trimIndent(),
                features = listOf(
                    "音视频处理",
                    "网络传输",
                    "质量优化",
                    "性能优化",
                    "最佳实践"
                ),
                links = mapOf(
                    "WebRTC" to "https://webrtc.org",
                    "FFmpeg" to "https://ffmpeg.org",
                    "音视频开发" to "https://developer.mozilla.org/en-US/docs/Web/API/WebRTC_API"
                ),
                author = "张音视",
                publishDate = "2024-02-16",
                readingTime = 22,
                category = "技术",
                tags = listOf("音视频", "实时通信", "多媒体")
            )
            "34" -> DetailInfo(
                title = "跨平台桌面应用开发",
                description = "探讨使用现代技术栈开发跨平台桌面应用的最佳实践",
                content = """
                    跨平台桌面应用开发正在变得越来越重要：

                    1. 技术选型
                    - Electron
                    - Tauri
                    - Flutter Desktop

                    2. 架构设计
                    - 主进程
                    - 渲染进程
                    - 进程通信

                    3. 性能优化
                    - 启动优化
                    - 内存管理
                    - 资源使用

                    4. 最佳实践
                    - 安全防护
                    - 自动更新
                    - 打包发布
                """.trimIndent(),
                features = listOf(
                    "技术选型",
                    "架构设计",
                    "性能优化",
                    "安全防护",
                    "最佳实践"
                ),
                links = mapOf(
                    "Electron" to "https://www.electronjs.org",
                    "Tauri" to "https://tauri.app",
                    "Flutter Desktop" to "https://flutter.dev/desktop"
                ),
                author = "刘桌面",
                publishDate = "2024-02-17",
                readingTime = 19,
                category = "技术",
                tags = listOf("桌面应用", "跨平台", "Electron")
            )
            "35" -> DetailInfo(
                title = "5G时代的应用开发",
                description = "探讨5G技术对应用开发的影响和机遇",
                content = """
                    5G技术正在改变应用开发的方式：

                    1. 技术特点
                    - 高带宽
                    - 低延迟
                    - 大连接

                    2. 应用场景
                    - 实时通信
                    - 云游戏
                    - AR/VR应用

                    3. 开发策略
                    - 网络优化
                    - 带宽管理
                    - 性能调优

                    4. 未来展望
                    - 边缘计算
                    - 物联网集成
                    - 智能应用
                """.trimIndent(),
                features = listOf(
                    "5G技术",
                    "应用场景",
                    "开发策略",
                    "性能优化",
                    "未来展望"
                ),
                links = mapOf(
                    "5G标准" to "https://www.3gpp.org",
                    "开发指南" to "https://developer.android.com/guide/topics/connectivity/5g",
                    "技术博客" to "https://www.qualcomm.com/5g"
                ),
                author = "陈5G",
                publishDate = "2024-02-18",
                readingTime = 21,
                category = "技术",
                tags = listOf("5G", "移动通信", "物联网")
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
            description = "Android 现代 UI 开发工具包",
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
            content = "Flutter 是 Google 的开源框架，用于构建美观、原生编译的多平台应用。使用 Dart 语言和丰富的组件库，以速开发高性能应用。",
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