package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.icons.CustomCode
import cn.kotlinmultiplatform.jeady.icons.CustomGitHub
import cn.kotlinmultiplatform.jeady.icons.ForkCount
import cn.kotlinmultiplatform.jeady.icons.StarCount
import cn.kotlinmultiplatform.jeady.platform.UrlHandler
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

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
                id = "kotlin",
                name = "Kotlin",
                description = "JetBrains 开发的现代编程语言，支持多平台开发，与 Java 完全互操作。",
                language = "Kotlin",
                stars = 45800,
                forks = 5500,
                githubUrl = "https://github.com/JetBrains/kotlin",
                demoUrl = "https://kotlinlang.org",
                tags = listOf("Programming Language", "JVM", "Android", "Multiplatform")
            ),
            OpenSourceProject(
                id = "compose-multiplatform",
                name = "Compose Multiplatform",
                description = "JetBrains 的声明式 UI 框架，用于构建跨平台应用程序的现代界面。",
                language = "Kotlin",
                stars = 13200,
                forks = 890,
                githubUrl = "https://github.com/JetBrains/compose-multiplatform",
                demoUrl = "https://www.jetbrains.com/lp/compose-multiplatform/",
                tags = listOf("UI Framework", "Multiplatform", "Desktop", "Web")
            ),
            OpenSourceProject(
                id = "ktor",
                name = "Ktor",
                description = "JetBrains 开发的异步 Web 框架，使用 Kotlin 构建连接的应用程序。",
                language = "Kotlin",
                stars = 11500,
                forks = 960,
                githubUrl = "https://github.com/ktorio/ktor",
                demoUrl = "https://ktor.io",
                tags = listOf("Web Framework", "Async", "HTTP", "Server")
            ),
            OpenSourceProject(
                id = "androidide",
                name = "AndroidIDE",
                description = "在 Android 设备上开发 Android 应用的 IDE，支持 Kotlin 和 Java。",
                language = "Kotlin",
                stars = 6300,
                forks = 450,
                githubUrl = "https://github.com/AndroidIDEOfficial/AndroidIDE",
                demoUrl = "https://androidide.com",
                tags = listOf("IDE", "Android", "Development", "Mobile")
            ),
            OpenSourceProject(
                id = "koin",
                name = "Koin",
                description = "实用的 Kotlin 依赖注入框架，专注于简洁性和实用性。",
                language = "Kotlin",
                stars = 7800,
                forks = 580,
                githubUrl = "https://github.com/InsertKoinIO/koin",
                demoUrl = "https://insert-koin.io",
                tags = listOf("DI", "Framework", "Multiplatform")
            ),
            OpenSourceProject(
                id = "arrow",
                name = "Arrow",
                description = "Kotlin 的函数式编程库，提供一系列强大的数据类型和工具。",
                language = "Kotlin",
                stars = 5900,
                forks = 440,
                githubUrl = "https://github.com/arrow-kt/arrow",
                demoUrl = "https://arrow-kt.io",
                tags = listOf("Functional Programming", "Library", "Type Safety")
            ),
            OpenSourceProject(
                id = "sqldelight",
                name = "SQLDelight",
                description = "Square 开发的 SQL 工具，为 Kotlin Multiplatform 提供类型安全的数据库访问。",
                language = "Kotlin",
                stars = 5400,
                forks = 390,
                githubUrl = "https://github.com/cashapp/sqldelight",
                demoUrl = "https://cashapp.github.io/sqldelight/",
                tags = listOf("Database", "SQL", "Multiplatform")
            ),
            OpenSourceProject(
                id = "detekt",
                name = "Detekt",
                description = "Kotlin 的静态代码分析工具，帮助保持代码质量和一致性。",
                language = "Kotlin",
                stars = 4900,
                forks = 670,
                githubUrl = "https://github.com/detekt/detekt",
                demoUrl = "https://detekt.dev",
                tags = listOf("Static Analysis", "Code Quality", "Linter")
            ),
            OpenSourceProject(
                id = "kotlinx-coroutines",
                name = "Kotlinx Coroutines",
                description = "Kotlin 协程库，提供异步编程支持。",
                language = "Kotlin",
                stars = 11200,
                forks = 1500,
                githubUrl = "https://github.com/Kotlin/kotlinx.coroutines",
                demoUrl = "https://kotlinlang.org/docs/coroutines-overview.html",
                tags = listOf("Async", "Coroutines", "Concurrency")
            ),
            OpenSourceProject(
                id = "exposed",
                name = "Exposed",
                description = "JetBrains 的 Kotlin SQL 框架，提供类型安全的 DSL。",
                language = "Kotlin",
                stars = 7300,
                forks = 640,
                githubUrl = "https://github.com/JetBrains/Exposed",
                demoUrl = "https://github.com/JetBrains/Exposed/wiki",
                tags = listOf("Database", "ORM", "SQL", "DSL")
            ),
            OpenSourceProject(
                id = "coil",
                name = "Coil",
                description = "现代的 Android 图片加载库，专为 Kotlin 协程优化。",
                language = "Kotlin",
                stars = 9400,
                forks = 620,
                githubUrl = "https://github.com/coil-kt/coil",
                demoUrl = "https://coil-kt.github.io/coil/",
                tags = listOf("Image Loading", "Android", "Coroutines")
            ),
            OpenSourceProject(
                id = "moko",
                name = "MOKO",
                description = "一套完整的 Kotlin Multiplatform 移动开发工具库。",
                language = "Kotlin",
                stars = 1800,
                forks = 140,
                githubUrl = "https://github.com/icerockdev/moko",
                demoUrl = "https://moko.icerock.dev/",
                tags = listOf("Mobile", "Multiplatform", "Libraries")
            ),
            OpenSourceProject(
                id = "decompose",
                name = "Decompose",
                description = "Kotlin Multiplatform 导航和生命周期库。",
                language = "Kotlin",
                stars = 2900,
                forks = 180,
                githubUrl = "https://github.com/arkivanov/Decompose",
                demoUrl = "https://arkivanov.github.io/Decompose/",
                tags = listOf("Navigation", "Lifecycle", "Multiplatform")
            ),
            OpenSourceProject(
                id = "voyager",
                name = "Voyager",
                description = "Compose Multiplatform 的导航和屏幕管理库。",
                language = "Kotlin",
                stars = 2600,
                forks = 160,
                githubUrl = "https://github.com/adrielcafe/voyager",
                demoUrl = "https://voyager.adriel.cafe/",
                tags = listOf("Navigation", "Compose", "Multiplatform")
            ),
            OpenSourceProject(
                id = "multiplatform-settings",
                name = "Multiplatform Settings",
                description = "Kotlin Multiplatform 的键值存储库。",
                language = "Kotlin",
                stars = 1500,
                forks = 120,
                githubUrl = "https://github.com/russhwolf/multiplatform-settings",
                demoUrl = "https://russhwolf.github.io/multiplatform-settings/",
                tags = listOf("Storage", "Settings", "Multiplatform")
            ),
            OpenSourceProject(
                id = "essenty",
                name = "Essenty",
                description = "Kotlin Multiplatform 的核心工具库集合。",
                language = "Kotlin",
                stars = 850,
                forks = 65,
                githubUrl = "https://github.com/arkivanov/Essenty",
                demoUrl = "https://arkivanov.github.io/Essenty/",
                tags = listOf("Lifecycle", "State Saving", "Multiplatform")
            ),
            OpenSourceProject(
                id = "molecule",
                name = "Molecule",
                description = "在 Compose Multiplatform 中构建状态管理的库。",
                language = "Kotlin",
                stars = 1200,
                forks = 85,
                githubUrl = "https://github.com/cashapp/molecule",
                demoUrl = "https://cashapp.github.io/molecule/",
                tags = listOf("State Management", "Compose", "Multiplatform")
            ),
            OpenSourceProject(
                id = "kotlinx-serialization",
                name = "Kotlinx Serialization",
                description = "Kotlin 的官方序列化框架。",
                language = "Kotlin",
                stars = 4200,
                forks = 420,
                githubUrl = "https://github.com/Kotlin/kotlinx.serialization",
                demoUrl = "https://kotlinlang.org/docs/serialization.html",
                tags = listOf("Serialization", "JSON", "Multiplatform")
            ),
            OpenSourceProject(
                id = "kotlinx-datetime",
                name = "Kotlinx DateTime",
                description = "Kotlin 的跨平台日期时间库。",
                language = "Kotlin",
                stars = 1800,
                forks = 140,
                githubUrl = "https://github.com/Kotlin/kotlinx-datetime",
                demoUrl = "https://kotlinlang.org/docs/datetime.html",
                tags = listOf("DateTime", "Multiplatform", "Library")
            ),
            OpenSourceProject(
                id = "ksp",
                name = "Kotlin Symbol Processing",
                description = "Kotlin 的符号��理 API，用于生成代码。",
                language = "Kotlin",
                stars = 3200,
                forks = 280,
                githubUrl = "https://github.com/google/ksp",
                demoUrl = "https://kotlinlang.org/docs/ksp-overview.html",
                tags = listOf("Annotation Processing", "Code Generation", "Compiler")
            )
        )
    }

    var showSearch by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    
    // Filter projects based on search query
    val filteredProjects = remember(searchQuery, allProjects) {
        if (searchQuery.isEmpty()) {
            allProjects
        } else {
            allProjects.filter { project ->
                project.name.contains(searchQuery, ignoreCase = true) ||
                project.description.contains(searchQuery, ignoreCase = true) ||
                project.tags.any { it.contains(searchQuery, ignoreCase = true) } ||
                project.githubUrl.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    var currentPage by remember { mutableStateOf(0) }
    val itemsPerPage = 5
    val totalPages = (filteredProjects.size + itemsPerPage - 1) / itemsPerPage
    val currentPageProjects = filteredProjects.drop(currentPage * itemsPerPage).take(itemsPerPage)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(currentPageProjects) { project ->
                    ProjectCard(
                        project = project,
                        urlHandler = urlHandler,
                        onTagClick = { tag ->
                            searchQuery = tag
                            showSearch = true
                            currentPage = 0
                        }
                    )
                }
            }

            // 分页控制
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { 
                        currentPage = 0  // Reset to first page when search changes
                        if (currentPage > 0) currentPage-- 
                    },
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

        // Floating search button and search box
        Surface(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            elevation = if (showSearch) 8.dp else 0.dp,
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colors.surface
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = if (showSearch) 8.dp else 0.dp)
                    .width(if (showSearch) 320.dp else 48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = { 
                        showSearch = !showSearch
                        if (!showSearch) {
                            searchQuery = ""
                            currentPage = 0
                        }
                    },
                    modifier = Modifier
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = if (showSearch) Icons.Default.Close else Icons.Default.Search,
                        contentDescription = if (showSearch) "关闭搜索" else "搜索",
                        tint = MaterialTheme.colors.primary
                    )
                }

                if (showSearch) {
                    TextField(
                        value = searchQuery,
                        onValueChange = { 
                            searchQuery = it
                            currentPage = 0
                        },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester),
                        placeholder = { 
                            Text(
                                "搜索项目...",
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                            ) 
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colors.primary
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { 
                                showSearch = false
                            }
                        )
                    )

                    LaunchedEffect(showSearch) {
                        if (showSearch) {
                            focusRequester.requestFocus()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectCard(
    project: OpenSourceProject,
    urlHandler: UrlHandler,
    onTagClick: (String) -> Unit
) {
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
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.clickable { onTagClick(tag) }
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