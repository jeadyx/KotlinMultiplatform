package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.kotlinmultiplatform.jeady.icons.Category
import cn.kotlinmultiplatform.jeady.icons.Cloud
import cn.kotlinmultiplatform.jeady.icons.NewReleases
import cn.kotlinmultiplatform.jeady.icons.Psychology
import cn.kotlinmultiplatform.jeady.icons.Web
import cn.kotlinmultiplatform.jeady.icons.Whatshot
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.compose_hero
import kotlinmultiplatform.composeapp.generated.resources.flutter_hero
import kotlinmultiplatform.composeapp.generated.resources.react_hero
import kotlinmultiplatform.composeapp.generated.resources.tech_hero
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

data class RecommendItem(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val tags: List<String>,
    val iconRes: DrawableResource,
    val isHot: Boolean = false,
    val onClick: () -> Unit
)

data class CarouselItem(
    val title: String,
    val description: String,
    val iconRes: DrawableResource
)

data class TechCategory(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val color: Color
)

private val techCategories = listOf(
    TechCategory(
        id = "mobile",
        name = "移动开发",
        icon = Icons.Filled.Phone,
        color = Color(0xFF2196F3)
    ),
    TechCategory(
        id = "web",
        name = "Web开发",
        icon = Icons.Filled.Web,
        color = Color(0xFF4CAF50)
    ),
    TechCategory(
        id = "ai",
        name = "人工智能",
        icon = Icons.Filled.Psychology,
        color = Color(0xFFE91E63)
    ),
    TechCategory(
        id = "cloud",
        name = "云原生",
        icon = Icons.Filled.Cloud,
        color = Color(0xFF9C27B0)
    )
)

// 添加 Kotlin 品牌色
private object KotlinColors {
    val Primary = Color(0xFF7F52FF)  // Kotlin 主色
    val Secondary = Color(0xFF587EF7) // 辅助色
    val Background = Color(0xFFF4F4F4) // 背景色
    val Surface = Color.White
    val Code = Color(0xFF27282C)  // 代码背景色
    
    // 添加渐变色
    val GradientColors = listOf(
        Color(0xFF7F52FF),  // 主色
        Color(0xFF587EF7),  // 辅助色
        Color(0xFF2196F3)   // 第三色
    )
}

// 修改 CardDimensions 对象
private object CardDimensions {
    val Width = 260.dp
    val Height = 180.dp  // 添加固定高度
    val IconSize = 36.dp
    val Padding = 16.dp
    val Spacing = 12.dp
    val CornerRadius = 4.dp
    val Elevation = 1.dp
}

// 更新渐变色配置
private object CarouselColors {
    val Kotlin = listOf(
        Color(0xFF1C1B1F),  // Kotlin 官网的深色背景
        Color(0xFF2E2D30)   // 稍微浅一点的深色
    )
    
    val Compose = listOf(
        Color(0xFF23232B),  // 深蓝灰色
        Color(0xFF2B2B35)   // 稍微带点蓝色的深色
    )
    
    val OpenSource = listOf(
        Color(0xFF1F1F24),  // 深色
        Color(0xFF27272D)   // 稍微浅一点的深色
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun RecommendationsPage(
    onNavigateToDetail: (String) -> Unit
) {
    val carouselItems = listOf(
        CarouselItem(
            title = "Kotlin Multiplatform",
            description = "一次编写，处处运行\n让跨平台开发更简单",
            iconRes = Res.drawable.tech_hero
        ),
        CarouselItem(
            title = "Compose Multiplatform",
            description = "现代化声明式UI\n为所有平台构建精美应用",
            iconRes = Res.drawable.compose_hero
        ),
        CarouselItem(
            title = "开源生态系统",
            description = "丰富的开源库和工具\n助力开发效率提升",
            iconRes = Res.drawable.flutter_hero
        )
    )
    
    val recommendations = listOf(
        // 移动开发分类
        RecommendItem(
            id = "kmm",
            title = "Kotlin Multiplatform Mobile",
            description = "使用 Kotlin 开发跨平台应用的现代解决方案",
            category = "移动开发",
            tags = listOf("Kotlin", "Mobile", "跨平台"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("kmm") }
        ),
        RecommendItem(
            id = "flutter",
            title = "Flutter",
            description = "Google 跨平台应用开发框架",
            category = "移动开发",
            tags = listOf("Dart", "Mobile", "跨平台"),
            iconRes = Res.drawable.flutter_hero,
            isHot = true,
            onClick = { onNavigateToDetail("flutter") }
        ),
        RecommendItem(
            id = "react-native",
            title = "React Native",
            description = "使用 React 建原生应用",
            category = "移动开发",
            tags = listOf("JavaScript", "React", "跨平台"),
            iconRes = Res.drawable.react_hero,
            onClick = { onNavigateToDetail("react-native") }
        ),

        // Web开发分类
        RecommendItem(
            id = "nextjs",
            title = "Next.js",
            description = "React 应用开发框架",
            category = "Web开发",
            tags = listOf("React", "SSR", "前端"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("nextjs") }
        ),
        RecommendItem(
            id = "vue",
            title = "Vue.js",
            description = "渐进式 JavaScript 框架",
            category = "Web开发",
            tags = listOf("JavaScript", "Vue", "前端"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("vue") }
        ),
        RecommendItem(
            id = "svelte",
            title = "Svelte",
            description = "构建用户界面的编译器",
            category = "Web开发",
            tags = listOf("JavaScript", "编译器", "UI"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("svelte") }
        ),

        // 人工智能分类
        RecommendItem(
            id = "tensorflow",
            title = "TensorFlow",
            description = "开源机器学习平台",
            category = "人工智能",
            tags = listOf("AI", "机器学习", "深度学习"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("tensorflow") }
        ),
        RecommendItem(
            id = "pytorch",
            title = "PyTorch",
            description = "开源机器学习框架",
            category = "人工智能",
            tags = listOf("AI", "深度学习", "Python"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("pytorch") }
        ),
        RecommendItem(
            id = "huggingface",
            title = "Hugging Face",
            description = "AI 模型和工具平台",
            category = "人工智能",
            tags = listOf("AI", "NLP", "模型库"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("huggingface") }
        ),

        // 云原生分类
        RecommendItem(
            id = "kubernetes",
            title = "Kubernetes",
            description = "容器编排平台",
            category = "云原生",
            tags = listOf("容器编排", "DevOps", "云原生"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("kubernetes") }
        ),
        RecommendItem(
            id = "docker",
            title = "Docker",
            description = "开发、部署和运行应用程序的开放平台",
            category = "云原生",
            tags = listOf("容器化", "DevOps", "部署"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("docker") }
        ),
        RecommendItem(
            id = "istio",
            title = "Istio",
            description = "服务网格平台",
            category = "云原生",
            tags = listOf("微服务", "服务网格", "云原生"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("istio") }
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
    
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // 轮播图部分
        item {
            ImageCarousel(carouselItems)
        }
        
        // 技术领域分类
        item {
            CategorySection(
                categories = techCategories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )
        }
        
        // 热门推荐
        item {
            HotSection(
                items = recommendations.filter { it.isHot },
                onItemClick = onNavigateToDetail
            )
        }
        
        // 最新发布
        item {
            NewReleaseSection(
                items = recommendations.take(5),
                onItemClick = onNavigateToDetail
            )
        }
        
        // 分类推荐
        if (selectedCategory != null) {
            item {
                CategoryRecommendations(
                    category = selectedCategory!!,
                    items = recommendations.filter { it.category == selectedCategory },
                    onItemClick = onNavigateToDetail
                )
            }
        }
    }
}

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
            CarouselPage(
                item = items[page],
                index = page  // 传递页面索引
            )
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
private fun CarouselPage(
    item: CarouselItem,
    index: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = when (index) {
                        0 -> CarouselColors.Kotlin
                        1 -> CarouselColors.Compose
                        else -> CarouselColors.OpenSource
                    },
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左侧图标
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .padding(end = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(item.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )
            }
            
            // 中间文本内容
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.5).sp
                    ),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { /* 处理点击 */ },
                    color = KotlinColors.Primary,
                    elevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "开始使用",
                            color = Color.White,
                            style = MaterialTheme.typography.button.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            
            // 右侧图标
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .padding(start = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(item.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        // 装饰性元素
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.03f),
                            Color.Transparent
                        ),
                        center = Offset(0f, 0f),
                        radius = 1200f
                    )
                )
        )
    }
}

@Composable
private fun RecommendationCard(item: RecommendItem) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { item.onClick() },
        color = KotlinColors.Surface,
        elevation = 1.dp  // 降低阴影
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 图标
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = KotlinColors.Primary.copy(alpha = 0.1f)
                ) {
                    Image(
                        painter = painterResource(item.iconRes),
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                
                // 标题和描述
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.5).sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 标签
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item.tags.forEach { tag ->
                    Surface(
                        color = KotlinColors.Primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(4.dp)  // 更小的圆角
                    ) {
                        Text(
                            text = tag,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.caption,
                            color = KotlinColors.Primary
                        )
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
private fun TagsRow(
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .height(32.dp)  // 固定高度
    ) {
        tags.forEach { tag ->
            Surface(
                color = KotlinColors.Primary.copy(alpha = 0.05f),
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = KotlinColors.Primary.copy(alpha = 0.1f)
                )
            ) {
                Text(
                    text = tag,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = KotlinColors.Primary
                )
            }
        }
    }
}

@Composable
private fun CategorySection(
    categories: List<TechCategory>,
    selectedCategory: String?,
    onCategorySelected: (String?) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "技术领域",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            categories.forEach { category ->
                CategoryCard(
                    category = category,
                    selected = category.id == selectedCategory,
                    onClick = { 
                        onCategorySelected(
                            if (category.id == selectedCategory) null 
                            else category.id
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryCard(
    category: TechCategory,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))  // 更小的圆角
            .clickable(onClick = onClick),
        color = if (selected) KotlinColors.Primary else KotlinColors.Surface,
        elevation = if (selected) 0.dp else 1.dp,  // 降低阴影
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) 
                KotlinColors.Primary 
            else 
                KotlinColors.Primary.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = null,
                tint = if (selected) Color.White else KotlinColors.Primary,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = category.name,
                style = MaterialTheme.typography.button,
                color = if (selected) Color.White else KotlinColors.Primary
            )
        }
    }
}

@Composable
private fun HotSection(
    items: List<RecommendItem>,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(CardDimensions.Padding)
    ) {
        SectionHeader(
            title = "热门推荐",
            icon = Icons.Default.Whatshot
        )
        
        Spacer(modifier = Modifier.height(CardDimensions.Spacing))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(CardDimensions.Spacing),
            contentPadding = PaddingValues(horizontal = CardDimensions.Padding)
        ) {
            items(items) { item ->
                HotItemCard(item = item, onClick = { onItemClick(item.id) })
            }
        }
    }
}

@Composable
private fun NewReleaseSection(
    items: List<RecommendItem>,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SectionHeader(
            title = "最新发布",
            icon = Icons.Default.NewReleases
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(items) { item ->
                NewReleaseItemCard(item = item, onClick = { onItemClick(item.id) })
            }
        }
    }
}

@Composable
private fun CategoryRecommendations(
    category: String,
    items: List<RecommendItem>,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SectionHeader(
            title = "分类推荐",
            icon = Icons.Default.Category
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(items) { item ->
                CategoryRecommendationItemCard(item = item, onClick = { onItemClick(item.id) })
            }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun HotItemCard(
    item: RecommendItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(CardDimensions.Width)
            .height(CardDimensions.Height)  // 添加固定高度
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(CardDimensions.CornerRadius),
        elevation = CardDimensions.Elevation,
        border = BorderStroke(
            width = 1.dp,
            color = KotlinColors.Primary.copy(alpha = 0.1f)
        ),
        color = KotlinColors.Surface
    ) {
        Column(
            modifier = Modifier
                .padding(CardDimensions.Padding)
                .fillMaxHeight()  // 填充整个高度
        ) {
            // 头部信息
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(CardDimensions.IconSize + CardDimensions.Spacing)  // 固定头部高度
            ) {
                Surface(
                    modifier = Modifier.size(CardDimensions.IconSize),
                    shape = RoundedCornerShape(CardDimensions.CornerRadius),
                    color = KotlinColors.Primary.copy(alpha = 0.05f)
                ) {
                    Image(
                        painter = painterResource(item.iconRes),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(CardDimensions.Spacing))
                Column {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = "热门",
                        style = MaterialTheme.typography.caption,
                        color = KotlinColors.Primary
                    )
                }
            }

            // 描述文本
            Text(
                text = item.description,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.weight(1f)  // 使用剩余空间
            )

            // 标签行
            TagsRow(
                tags = item.tags,
                modifier = Modifier.height(32.dp)  // 固定标签行高度
            )
        }
    }
}

@Composable
private fun NewReleaseItemCard(
    item: RecommendItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(CardDimensions.Width)
            .height(CardDimensions.Height)  // 添加固定高度
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(CardDimensions.CornerRadius),
        elevation = CardDimensions.Elevation,
        border = BorderStroke(
            width = 1.dp,
            color = KotlinColors.Secondary.copy(alpha = 0.1f)
        ),
        color = KotlinColors.Surface
    ) {
        Column(
            modifier = Modifier
                .padding(CardDimensions.Padding)
                .fillMaxHeight()  // 填充整个高度
        ) {
            // 头部信息
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(CardDimensions.IconSize + CardDimensions.Spacing)  // 固定头部高度
            ) {
                Surface(
                    modifier = Modifier.size(CardDimensions.IconSize),
                    shape = RoundedCornerShape(CardDimensions.CornerRadius),
                    color = KotlinColors.Secondary.copy(alpha = 0.05f)
                ) {
                    Image(
                        painter = painterResource(item.iconRes),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(CardDimensions.Spacing))
                Column {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = "新发布",
                        style = MaterialTheme.typography.caption,
                        color = KotlinColors.Secondary
                    )
                }
            }

            // 描述文本
            Text(
                text = item.description,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.weight(1f)  // 使用剩余空间
            )

            // 标签行
            TagsRow(
                tags = item.tags,
                modifier = Modifier.height(32.dp)  // 固定标签行高度
            )
        }
    }
}

@Composable
private fun CategoryRecommendationItemCard(
    item: RecommendItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(CardDimensions.Width)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(CardDimensions.CornerRadius),
        elevation = CardDimensions.Elevation,
        border = BorderStroke(
            width = 1.dp,
            color = Color.Gray.copy(alpha = 0.1f)
        ),
        color = KotlinColors.Surface
    ) {
        Column(
            modifier = Modifier.padding(CardDimensions.Padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(CardDimensions.IconSize),
                    shape = RoundedCornerShape(CardDimensions.CornerRadius),
                    color = KotlinColors.Primary.copy(alpha = 0.05f)
                ) {
                    Image(
                        painter = painterResource(item.iconRes),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(CardDimensions.Spacing))
                Column {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                    CategoryChip(item.category)
                }
            }
            Spacer(modifier = Modifier.height(CardDimensions.Spacing))
            Text(
                text = item.description,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(CardDimensions.Spacing))
            TagsRow(item.tags)
        }
    }
} 