package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.icons.CustomCode
import cn.kotlinmultiplatform.jeady.icons.CustomGitHub
import cn.kotlinmultiplatform.jeady.icons.CustomVisibility

data class OpenSourceProject(
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
fun OpenSourcePage() {
    val projects = remember {
        listOf(
            OpenSourceProject(
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
                name = "KMP UI Components",
                description = "为 Kotlin Multiplatform 项目提供的现代化 UI 组件库，包含丰富的自定义组件。",
                language = "Kotlin",
                stars = 256,
                forks = 64,
                githubUrl = "https://github.com/yourusername/kmp-ui-components",
                demoUrl = "https://ui.example.com",
                tags = listOf("UI", "Components", "Design System")
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "开源项目",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(projects) { project ->
            ProjectCard(project)
        }
    }
}

@Composable
private fun ProjectCard(project: OpenSourceProject) {
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
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.CustomGitHub,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${project.stars}",
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Text(
                        text = "${project.forks} forks",
                        style = MaterialTheme.typography.caption
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { /* TODO: 打开 GitHub */ },
                        modifier = Modifier.height(36.dp)
                    ) {
                        Icon(
                            Icons.Filled.CustomGitHub,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("GitHub")
                    }

                    if (project.demoUrl != null) {
                        Button(
                            onClick = { /* TODO: 打开演示 */ },
                            modifier = Modifier.height(36.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary
                            )
                        ) {
                            Icon(
                                Icons.Filled.CustomVisibility,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("演示")
                        }
                    }
                }
            }
        }
    }
} 