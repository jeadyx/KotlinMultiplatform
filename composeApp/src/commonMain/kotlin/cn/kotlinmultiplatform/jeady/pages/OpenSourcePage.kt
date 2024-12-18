package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.icons.CustomCode
import cn.kotlinmultiplatform.jeady.icons.CustomGitHub
import cn.kotlinmultiplatform.jeady.icons.ForkCount
import cn.kotlinmultiplatform.jeady.icons.StarCount
import cn.kotlinmultiplatform.jeady.platform.UrlHandler

data class OpenSourceProject(
    val id: String,
    val name: String,
    val description: String,
    val language: String,
    val stars: Int,
    val forks: Int,
    val githubUrl: String,
    val demoUrl: String?,
    val tags: List<String>
)

@Composable
fun OpenSourcePage(urlHandler: UrlHandler) {
    val allProjects = remember {
        listOf(
            OpenSourceProject(
                id = "kmm-blog",
                name = "Kotlin Multiplatform Blog",
                description = "使用 Kotlin Multiplatform 和 Compose Multiplatform 开发的现代化博客应用，支持多平台部署。",
                language = "Kotlin",
                stars = 128,
                forks = 32,
                githubUrl = "https://github.com/yourusername/kotlin-multiplatform-blog",
                demoUrl = "https://demo.example.com",
                tags = listOf("Kotlin", "Compose", "Multiplatform", "Blog")
            ),
            OpenSourceProject(
                id = "markdown-editor",
                name = "Markdown Editor",
                description = "一个轻量级的 Markdown 编辑器组件，支持实时预览和自定义主题。",
                language = "Kotlin",
                stars = 86,
                forks = 24,
                githubUrl = "https://github.com/yourusername/markdown-editor",
                demoUrl = null,
                tags = listOf("Markdown", "Editor", "Compose")
            ),
            OpenSourceProject(
                id = "kmp-ui",
                name = "KMP UI Components",
                description = "为 Kotlin Multiplatform 项目提供的现代化 UI 组件库，包含丰富的自定义组件。",
                language = "Kotlin",
                stars = 256,
                forks = 64,
                githubUrl = "https://github.com/yourusername/kmp-ui-components",
                demoUrl = "https://ui.example.com",
                tags = listOf("UI", "Components", "Design System")
            ),
            OpenSourceProject(
                id = "kotlin-ds",
                name = "Kotlin Data Structures",
                description = "高性能的 Kotlin 数据结构实现，支持多平台。",
                language = "Kotlin",
                stars = 432,
                forks = 89,
                githubUrl = "https://github.com/yourusername/kotlin-data-structures",
                demoUrl = null,
                tags = listOf("Data Structures", "Algorithms", "Performance")
            ),
            OpenSourceProject(
                id = "kmp-network",
                name = "KMP Network",
                description = "Kotlin Multiplatform 网络请求库，支持协程和响应式编程。",
                language = "Kotlin",
                stars = 765,
                forks = 143,
                githubUrl = "https://github.com/yourusername/kmp-network",
                demoUrl = "https://network.example.com",
                tags = listOf("Network", "Coroutines", "HTTP")
            ),
            OpenSourceProject(
                id = "compose-charts",
                name = "Compose Charts",
                description = "基于 Compose Multiplatform 的现代化图表库。",
                language = "Kotlin",
                stars = 543,
                forks = 98,
                githubUrl = "https://github.com/yourusername/compose-charts",
                demoUrl = "https://charts.example.com",
                tags = listOf("Charts", "Visualization", "Compose")
            ),
            OpenSourceProject(
                id = "kmp-state",
                name = "KMP State Management",
                description = "Kotlin Multiplatform 状态管理库，支持 MVI 架构。",
                language = "Kotlin",
                stars = 876,
                forks = 167,
                githubUrl = "https://github.com/yourusername/kmp-state",
                demoUrl = null,
                tags = listOf("State Management", "MVI", "Architecture")
            ),
            OpenSourceProject(
                id = "compose-forms",
                name = "Compose Forms",
                description = "声明式表单构建库，基于 Compose Multiplatform。",
                language = "Kotlin",
                stars = 432,
                forks = 87,
                githubUrl = "https://github.com/yourusername/compose-forms",
                demoUrl = "https://forms.example.com",
                tags = listOf("Forms", "Validation", "Compose")
            ),
            OpenSourceProject(
                id = "kmp-testing",
                name = "KMP Testing",
                description = "Kotlin Multiplatform 测试工具集，支持单元测试和UI测试。",
                language = "Kotlin",
                stars = 345,
                forks = 76,
                githubUrl = "https://github.com/yourusername/kmp-testing",
                demoUrl = null,
                tags = listOf("Testing", "Unit Tests", "UI Tests")
            ),
            OpenSourceProject(
                id = "compose-nav",
                name = "Compose Navigation",
                description = "Compose Multiplatform 导航库，支持深层链接。",
                language = "Kotlin",
                stars = 654,
                forks = 123,
                githubUrl = "https://github.com/yourusername/compose-navigation",
                demoUrl = "https://navigation.example.com",
                tags = listOf("Navigation", "Deep Links", "Compose")
            ),
            OpenSourceProject(
                id = "kmp-analytics",
                name = "KMP Analytics",
                description = "跨平台应用分析库，支持多种分析服务集成。",
                language = "Kotlin",
                stars = 234,
                forks = 45,
                githubUrl = "https://github.com/yourusername/kmp-analytics",
                demoUrl = null,
                tags = listOf("Analytics", "Tracking", "Integration")
            ),
            OpenSourceProject(
                id = "compose-animations",
                name = "Compose Animations",
                description = "丰富的 Compose Multiplatform 动画库。",
                language = "Kotlin",
                stars = 567,
                forks = 98,
                githubUrl = "https://github.com/yourusername/compose-animations",
                demoUrl = "https://animations.example.com",
                tags = listOf("Animations", "UI", "Motion")
            ),
            OpenSourceProject(
                id = "kmp-storage",
                name = "KMP Storage",
                description = "跨平台数据存储解决方案，支持多种存储方式。",
                language = "Kotlin",
                stars = 432,
                forks = 87,
                githubUrl = "https://github.com/yourusername/kmp-storage",
                demoUrl = null,
                tags = listOf("Storage", "Database", "Cache")
            ),
            OpenSourceProject(
                id = "compose-gestures",
                name = "Compose Gestures",
                description = "手势识别和处理库，为 Compose Multiplatform 应用提供丰富的交互体验。",
                language = "Kotlin",
                stars = 345,
                forks = 67,
                githubUrl = "https://github.com/yourusername/compose-gestures",
                demoUrl = "https://gestures.example.com",
                tags = listOf("Gestures", "Interaction", "Touch")
            ),
            OpenSourceProject(
                id = "kmp-auth",
                name = "KMP Auth",
                description = "跨平台身份认证库，支持多种认证方式和提供者。",
                language = "Kotlin",
                stars = 678,
                forks = 123,
                githubUrl = "https://github.com/yourusername/kmp-auth",
                demoUrl = null,
                tags = listOf("Authentication", "Security", "OAuth")
            )
        )
    }

    var currentPage by remember { mutableStateOf(0) }
    val itemsPerPage = 5
    val totalPages = (allProjects.size + itemsPerPage - 1) / itemsPerPage
    val currentPageProjects = allProjects.drop(currentPage * itemsPerPage).take(itemsPerPage)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "开源项目",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(currentPageProjects) { project ->
                ProjectCard(project, urlHandler)
            }
        }

        // 分页控制
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { if (currentPage > 0) currentPage-- },
                enabled = currentPage > 0
            ) {
                Text("上一页")
            }
            
            Text(
                text = "${currentPage + 1} / $totalPages",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            TextButton(
                onClick = { if (currentPage < totalPages - 1) currentPage++ },
                enabled = currentPage < totalPages - 1
            ) {
                Text("下一页")
            }
        }
    }
}

@Composable
private fun ProjectCard(project: OpenSourceProject, urlHandler: UrlHandler) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Filled.CustomCode,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        text = project.name,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Text(
                    text = project.language,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = project.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 标签
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                project.tags.forEach { tag ->
                    Surface(
                        color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = tag,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 项目统计和链接
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.StarCount,
                            contentDescription = "Stars",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${project.stars}",
                            style = MaterialTheme.typography.body2
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ForkCount,
                            contentDescription = "Forks",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${project.forks}",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(onClick = { urlHandler.openUrl(project.githubUrl) }) {
                        Icon(
                            imageVector = Icons.Filled.CustomGitHub,
                            contentDescription = "Github",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                    if (project.demoUrl != null) {
                        IconButton(onClick = { urlHandler.openUrl(project.demoUrl) }) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Stars",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
        }
    }
} 