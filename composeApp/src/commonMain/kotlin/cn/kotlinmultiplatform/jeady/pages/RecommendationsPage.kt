package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.compose_hero
import kotlinmultiplatform.composeapp.generated.resources.flutter_hero
import kotlinmultiplatform.composeapp.generated.resources.react_hero
import kotlinmultiplatform.composeapp.generated.resources.swift_hero
import kotlinmultiplatform.composeapp.generated.resources.tech_hero
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

data class RecommendItem(
    val title: String,
    val description: String,
    val category: String,
    val tags: List<String>,
    val iconRes: DrawableResource,
    val onClick: () -> Unit
)

data class CarouselItem(
    val title: String,
    val description: String,
    val iconRes: DrawableResource
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun RecommendationsPage(
    onNavigateToDetail: (String) -> Unit
) {
    val carouselItems = listOf(
        CarouselItem(
            "Kotlin多平台开发",
            "一次编写，到处运行",
            Res.drawable.tech_hero
        ),
        CarouselItem(
            "现代化UI框架",
            "使用Compose构建跨平台应用",
            Res.drawable.compose_hero
        ),
        CarouselItem(
            "开源技术",
            "拥抱开源，共建生态",
            Res.drawable.flutter_hero
        )
    )
    
    val recommendations = listOf(
        RecommendItem(
            title = "Kotlin Multiplatform Mobile",
            description = "使用 Kotlin 开发平台应用的现代解决方案",
            category = "跨平台开发",
            tags = listOf("Kotlin", "Mobile", "跨平台"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("kmm") }
        ),
        RecommendItem(
            title = "Jetpack Compose",
            description = "Android 现代化 UI 开发工具包",
            category = "UI框架",
            tags = listOf("Android", "UI", "声明式"),
            iconRes = Res.drawable.compose_hero,
            onClick = { onNavigateToDetail("compose") }
        ),
        RecommendItem(
            title = "Flutter",
            description = "Google 的跨平台应用开发框架",
            category = "跨平台开发",
            tags = listOf("Dart", "Mobile", "跨平台"),
            iconRes = Res.drawable.flutter_hero,
            onClick = { onNavigateToDetail("flutter") }
        ),
        RecommendItem(
            title = "SwiftUI",
            description = "Apple 原生的声明式 UI 框架",
            category = "UI框架",
            tags = listOf("Swift", "iOS", "声明式"),
            iconRes = Res.drawable.swift_hero,
            onClick = { onNavigateToDetail("swiftui") }
        ),
        RecommendItem(
            title = "React Native",
            description = "使用 React 构建原生应用",
            category = "跨平台开发",
            tags = listOf("JavaScript", "React", "跨平台"),
            iconRes = Res.drawable.react_hero,
            onClick = { onNavigateToDetail("react-native") }
        ),
        RecommendItem(
            title = "Vue.js",
            description = "渐进式 JavaScript 框架",
            category = "前端开发",
            tags = listOf("JavaScript", "Vue", "前端"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("vue") }
        ),
        RecommendItem(
            title = "Spring Boot",
            description = "简化 Spring 应用开发的框架",
            category = "后端开发",
            tags = listOf("Java", "Spring", "后端"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("spring-boot") }
        ),
        RecommendItem(
            title = "TensorFlow",
            description = "开源机器学习平台",
            category = "人工智能",
            tags = listOf("AI", "机器学习", "深度学习"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("tensorflow") }
        ),
        RecommendItem(
            title = "Docker",
            description = "开发、部署和运行应用程序的开放平台",
            category = "DevOps",
            tags = listOf("容器化", "DevOps", "部署"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("docker") }
        ),
        RecommendItem(
            title = "GraphQL",
            description = "API 查询语言和运行时",
            category = "后端开发",
            tags = listOf("API", "查询语言", "后端"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("graphql") }
        ),
        RecommendItem(
            title = "Next.js",
            description = "React 应用开发框架",
            category = "前端开发",
            tags = listOf("React", "SSR", "前端"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("nextjs") }
        ),
        RecommendItem(
            title = "Kubernetes",
            description = "容器编排平台",
            category = "DevOps",
            tags = listOf("容器编排", "DevOps", "云原生"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("kubernetes") }
        ),
        RecommendItem(
            title = "PyTorch",
            description = "开源机器学习库",
            category = "人工智能",
            tags = listOf("AI", "深度学习", "Python"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("pytorch") }
        ),
        RecommendItem(
            title = "Angular",
            description = "TypeScript 开发平台",
            category = "前端开发",
            tags = listOf("TypeScript", "前端", "框架"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("angular") }
        ),
        RecommendItem(
            title = "Redis",
            description = "开源内存数据存储",
            category = "数据库",
            tags = listOf("缓存", "数据库", "性能"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("redis") }
        ),
        RecommendItem(
            title = "Electron",
            description = "构建跨平台桌面应用",
            category = "桌面开发",
            tags = listOf("JavaScript", "桌面", "跨平台"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("electron") }
        ),
        RecommendItem(
            title = "FastAPI",
            description = "现代、快速的 Web 框架",
            category = "后端开发",
            tags = listOf("Python", "API", "异步"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("fastapi") }
        ),
        RecommendItem(
            title = "Unity",
            description = "跨平台游戏引擎",
            category = "游戏开发",
            tags = listOf("游戏", "C#", "3D"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("unity") }
        ),
        RecommendItem(
            title = "PostgreSQL",
            description = "开源对象关系数据库",
            category = "数据库",
            tags = listOf("数据库", "SQL", "关系型"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("postgresql") }
        ),
        RecommendItem(
            title = "Rust",
            description = "系统编程语言",
            category = "系统开发",
            tags = listOf("系统", "性能", "安全"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("rust") }
        ),
        RecommendItem(
            title = "Svelte",
            description = "构建用户界面的编译器",
            category = "前端开发",
            tags = listOf("JavaScript", "编译器", "UI"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("svelte") }
        ),
        RecommendItem(
            title = "MongoDB",
            description = "文档数据库",
            category = "数据库",
            tags = listOf("NoSQL", "文档", "数据库"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("mongodb") }
        ),
        RecommendItem(
            title = "Deno",
            description = "JavaScript 和 TypeScript 运行时",
            category = "后端开发",
            tags = listOf("JavaScript", "TypeScript", "运行时"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("deno") }
        ),
        RecommendItem(
            title = "Unreal Engine",
            description = "专业游戏引擎",
            category = "游戏开发",
            tags = listOf("游戏", "C++", "3D"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("unreal") }
        ),
        RecommendItem(
            title = "Tauri",
            description = "构建跨平台应用的工具包",
            category = "桌面开发",
            tags = listOf("Rust", "跨平台", "桌面"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("tauri") }
        ),
        RecommendItem(
            title = "NestJS",
            description = "Node.js 服务端框架",
            category = "后端开发",
            tags = listOf("TypeScript", "Node.js", "后端"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("nestjs") }
        ),
        RecommendItem(
            title = "Godot",
            description = "开源游戏引擎",
            category = "游戏开发",
            tags = listOf("游戏", "开源", "2D/3D"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("godot") }
        ),
        RecommendItem(
            title = "Elasticsearch",
            description = "分布式搜索引擎",
            category = "数据库",
            tags = listOf("搜索", "分析", "分布式"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("elasticsearch") }
        ),
        RecommendItem(
            title = "Astro",
            description = "现代静态站点生成器",
            category = "前端开发",
            tags = listOf("静态站点", "性能", "前端"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("astro") }
        ),
        RecommendItem(
            title = "Go",
            description = "云原生编程语言",
            category = "后端开发",
            tags = listOf("并发", "性能", "云原生"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("go") }
        )
    )
    
    val listState = rememberLazyListState()
    
    // 监听滚位置变化
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                // 保存滚动位置到本地
                // 这里可以使用其他方式保存状态
            }
    }
    
    var currentPage by remember { mutableStateOf(0) }
    val itemsPerPage = 10
    val totalPages = (recommendations.size + itemsPerPage - 1) / itemsPerPage
    val currentPageItems = recommendations.drop(currentPage * itemsPerPage).take(itemsPerPage)
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = listState
    ) {
        item {
            ImageCarousel(carouselItems)
        }
        
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        
        items(currentPageItems) { item ->
            RecommendationCard(item)
        }
        
        item {
            if (recommendations.size > itemsPerPage) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable(enabled = currentPage > 0) {
                                if (currentPage > 0) currentPage--
                            },
                        color = if (currentPage > 0)
                            MaterialTheme.colors.primary
                        else
                            MaterialTheme.colors.primary.copy(alpha = 0.5f)
                    ) {
                        Text(
                            "上一页",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                    
                    Text(
                        "${currentPage + 1} / $totalPages",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable(enabled = currentPage < totalPages - 1) {
                                if (currentPage < totalPages - 1) currentPage++
                            },
                        color = if (currentPage < totalPages - 1)
                            MaterialTheme.colors.primary
                        else
                            MaterialTheme.colors.primary.copy(alpha = 0.5f)
                    ) {
                        Text(
                            "下一页",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImageCarousel(items: List<CarouselItem>) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val coroutineScope = rememberCoroutineScope()
    
    // 自动轮播
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            coroutineScope.launch {
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % items.size
                )
            }
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            CarouselPage(items[page])
        }
        
        // 器
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(items.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index)
                                MaterialTheme.colors.primary
                            else
                                MaterialTheme.colors.primary.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}

@Composable
private fun CarouselPage(item: CarouselItem) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 背景图片
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.primary.copy(alpha = 0.1f))
        ) {
            Image(
                painter = painterResource(item.iconRes),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentScale = ContentScale.Fit
            )
        }
        
        // 内容覆盖层
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.surface.copy(alpha = 0.7f),
                            MaterialTheme.colors.surface.copy(alpha = 0.85f)
                        )
                    )
                )
        )
        
        // 文字内容
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
private fun RecommendationCard(item: RecommendItem) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // 部大阴影
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = if (isPressed) 6.dp else 8.dp),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colors.primary.copy(alpha = if (isPressed) 0.1f else 0.08f),
            elevation = if (isPressed) 3.dp else 2.dp
        ) {
            Spacer(modifier = Modifier.height(100.dp))
        }
        
        // 中间阴影
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = if (isPressed) 3.dp else 4.dp),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colors.primary.copy(alpha = if (isPressed) 0.12f else 0.1f),
            elevation = if (isPressed) 4.dp else 3.dp
        ) {
            Spacer(modifier = Modifier.height(100.dp))
        }
        
        // 主卡片
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = if (isPressed) 2.dp else 0.dp)
                .clip(RoundedCornerShape(24.dp))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { 
                    item.onClick() 
                },
            elevation = if (isPressed) 5.dp else 4.dp,
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(24.dp)
        ) {
            Box {
                // 渐变背景装饰
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = 40.dp, y = (-40).dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    MaterialTheme.colors.primary.copy(alpha = 0.08f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.04f),
                                    MaterialTheme.colors.primary.copy(alpha = 0.01f)
                                )
                            ),
                            shape = CircleShape
                        )
                )
                
                Row(
                    modifier = Modifier.padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // 图标部分 - 移除阴影
                    Surface(
                        modifier = Modifier.size(64.dp),
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colors.primary.copy(alpha = 0.1f)
                    ) {
                        Image(
                            painter = painterResource(item.iconRes),
                            contentDescription = item.title,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(32.dp)
                        )
                    }
                    
                    // 内容部分
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        // 标题
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.h6.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = (-0.5).sp
                                ),
                                color = MaterialTheme.colors.primary
                            )
                            CategoryChip(item.category)
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // 描述
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.body1.copy(
                                lineHeight = 22.sp
                            ),
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                            modifier = Modifier.padding(end = 32.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // 标签
                        TagsRow(item.tags)
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryChip(category: String) {
    Surface(
        color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primary.copy(alpha = 0.2f)
        )
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp
            )
        )
    }
}

@Composable
private fun TagsRow(tags: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        tags.forEach { tag ->
            Surface(
                color = MaterialTheme.colors.secondary.copy(alpha = 0.08f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "#$tag",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp
                    )
                )
            }
        }
    }
} 