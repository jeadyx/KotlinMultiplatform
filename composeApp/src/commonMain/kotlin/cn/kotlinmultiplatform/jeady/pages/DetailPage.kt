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
import cn.kotlinmultiplatform.jeady.service.BlogService

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
    source: String = "blog",
    onBackClick: () -> Unit
) {
    var detailInfo by remember { mutableStateOf<DetailInfo?>(null) }

    // 根据来源加载不同的数据
    LaunchedEffect(itemId, source) {
        detailInfo = when (source) {
            "blog" -> fetchBlogDetailInfo(itemId)
            "recommendation" -> fetchRecommendationDetailInfo(itemId)
            else -> throw IllegalArgumentException("Unknown source: $source")
        }
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 文章头部信息
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
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

private suspend fun fetchBlogDetailInfo(itemId: String): DetailInfo {
    // 从博客服务获取数据
    val blogService = BlogService.getInstance()
    val post = blogService.getPostById(itemId) ?: throw IllegalArgumentException("Blog post not found: $itemId")
    
    return DetailInfo(
        title = post.title,
        description = post.summary,
        content = post.content,
        features = emptyList(),
        links = emptyMap(),
        author = post.author,
        publishDate = post.publishDate.toString(),
        readingTime = post.readingTime,
        category = post.category,
        tags = post.tags
    )
}

private suspend fun fetchRecommendationDetailInfo(itemId: String): DetailInfo {
    // 模拟网络延迟
    kotlinx.coroutines.delay(500)
    
    // 这里返回推荐内容的详细信息
    return DetailInfo(
        title = "Kotlin Multiplatform 详情",
        description = "用 Kotlin 开发跨平台��用的现代解决方案",
        content = """
            # Kotlin Multiplatform
            
            Kotlin Multiplatform 是一个强大的跨平台开发框架，它允许你使用单一语言开发多个平台的应用。
            
            ## 主要特点
            
            - 共享业务逻辑
            - 平台特定API支持
            - 优秀的开发体验
            
            ## 支持的平台
            
            - Android
            - iOS
            - Desktop
            - Web
            - Server
        """.trimIndent(),
        features = listOf(
            "跨平台开发",
            "代码共享",
            "原生性能",
            "灵活性"
        ),
        links = mapOf(
            "官方文档" to "https://kotlinlang.org/docs/multiplatform.html",
            "GitHub" to "https://github.com/Kotlin/kotlin-multiplatform",
            "示例项目" to "https://github.com/Kotlin/kmm-sample"
        )
    )
} 