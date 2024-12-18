package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.kotlinmultiplatform.jeady.icons.Category
import cn.kotlinmultiplatform.jeady.icons.NewReleases
import cn.kotlinmultiplatform.jeady.icons.Psychology
import cn.kotlinmultiplatform.jeady.icons.Whatshot
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.compose_hero
import kotlinmultiplatform.composeapp.generated.resources.flutter_hero
import kotlinmultiplatform.composeapp.generated.resources.tech_hero
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs
import kotlin.math.min

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
        id = "popular_blogs",
        name = "热门博客",
        icon = Icons.Filled.Whatshot,
        color = Color(0xFFE91E63)
    ),
    TechCategory(
        id = "open_source",
        name = "开源趋势",
        icon = Icons.Filled.Category,
        color = Color(0xFF4CAF50)
    ),
    TechCategory(
        id = "ai_tools",
        name = "AI工具",
        icon = Icons.Filled.Psychology,
        color = Color(0xFF2196F3)
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
        Color(0xFF1B1B3D),  // 深蓝色
        Color(0xFF2E2D5C)   // 稍微浅一点的蓝色
    )
    
    val Compose = listOf(
        Color(0xFF1C3238),  // 深青色
        Color(0xFF2B4850)   // 稍微浅一点的青色
    )
    
    val OpenSource = listOf(
        Color(0xFF3B1F36),  // 深紫色
        Color(0xFF4D2B47)   // 稍微浅一点的紫色
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun RecommendationsPage(
    onNavigateToDetail: (String, String) -> Unit
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
        ),
        CarouselItem(
            title = "跨平台开发",
            description = "Android、iOS、Desktop\n一套代码，多端运行",
            iconRes = Res.drawable.tech_hero
        ),
        CarouselItem(
            title = "原生性能",
            description = "编译为原生代码\n获得最佳运行性能",
            iconRes = Res.drawable.compose_hero
        )
    )
    
    val recommendations = listOf(
        // 热门博客分类
        RecommendItem(
            id = "kotlin-blog",
            title = "Kotlin 官方博客",
            description = "Kotlin 语言最新动态和技术分享",
            category = "热门博客",
            tags = listOf("Kotlin", "官方", "技术"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("kotlin-blog", "recommendation") }
        ),
        RecommendItem(
            id = "jetbrains-blog",
            title = "JetBrains 博客",
            description = "JetBrains 工具和技术更新",
            category = "热门博客",
            tags = listOf("JetBrains", "工具", "IDE"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("jetbrains-blog", "recommendation") }
        ),
        RecommendItem(
            id = "android-blog",
            title = "Android 开发者博客",
            description = "Android 平台最新资讯",
            category = "热门博客",
            tags = listOf("Android", "移动开发", "Google"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("android-blog", "recommendation") }
        ),

        // 源趋势分类
        RecommendItem(
            id = "compose-multiplatform",
            title = "Compose Multiplatform",
            description = "跨平台 UI 框架",
            category = "开源趋势",
            tags = listOf("UI", "跨平台", "Kotlin"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("compose-multiplatform", "recommendation") }
        ),
        RecommendItem(
            id = "ktor",
            title = "Ktor",
            description = "Kotlin 异步 Web 框架",
            category = "开源趋势",
            tags = listOf("Web", "异步", "服务器"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("ktor", "recommendation") }
        ),
        RecommendItem(
            id = "arrow-kt",
            title = "Arrow",
            description = "Kotlin 函数式编程库",
            category = "开源趋势",
            tags = listOf("FP", "函数式", "工具库"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("arrow-kt", "recommendation") }
        ),

        // AI工具分类
        RecommendItem(
            id = "cursor",
            title = "Cursor",
            description = "AI 驱动的代码编辑器",
            category = "AI工具",
            tags = listOf("IDE", "AI", "编程"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("cursor", "recommendation") }
        ),
        RecommendItem(
            id = "github-copilot",
            title = "GitHub Copilot",
            description = "AI 配对编程助手",
            category = "AI工具",
            tags = listOf("AI", "编程", "自动完成"),
            iconRes = Res.drawable.tech_hero,
            isHot = true,
            onClick = { onNavigateToDetail("github-copilot", "recommendation") }
        ),
        RecommendItem(
            id = "tabnine",
            title = "Tabnine",
            description = "AI 代码补全工具",
            category = "AI工具",
            tags = listOf("AI", "补全", "效率"),
            iconRes = Res.drawable.tech_hero,
            onClick = { onNavigateToDetail("tabnine", "recommendation") }
        )
    )
    
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // 轮播图分
        item {
            ImageCarousel(carouselItems)
        }
        
        // 热门推荐
        item {
            HotSection(
                items = recommendations.filter { it.isHot },
                onItemClick = { id -> onNavigateToDetail(id, "recommendation") }
            )
        }
        
        // 最新发布
        item {
            NewReleaseSection(
                items = recommendations.take(3),
                onItemClick = { id -> onNavigateToDetail(id, "recommendation") }
            )
        }
    }
}

@Composable
private fun ImageCarousel(items: List<CarouselItem>) {
    val currentIndex = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    
    // 添加动画状态
    val animatedIndex = remember { Animatable(0f) }
    // 主tab的透明度动画状态
    val mainCardAlpha = remember { Animatable(0f) }
    
    // 初始动画 - 只为主tab添加
    LaunchedEffect(Unit) {
        mainCardAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )
    }
    
    // 自动轮播
    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)
            val nextIndex = (currentIndex.value + 1) % items.size
            animatedIndex.animateTo(
                targetValue = nextIndex.toFloat(),
                animationSpec = tween(
                    durationMillis = 600,
                    easing = FastOutSlowInEasing
                )
            )
            currentIndex.value = nextIndex
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 显示三个卡片：当前、前一个和后一个
            for (offset in -1..1) {
                val index = (currentIndex.value + offset + items.size) % items.size
                val item = items[index]
                
                val animatedOffset = (animatedIndex.value - currentIndex.value + offset)
                val scale = 0.7f + (1f - min(1f, abs(animatedOffset))) * 0.3f
                // 只有主卡片使用动画透明度，其他卡片使用固定透明度
                val alpha = if (offset == 0) {
                    0.3f + (1f - min(1f, abs(animatedOffset))) * 0.7f * mainCardAlpha.value
                } else {
                    0.3f + (1f - min(1f, abs(animatedOffset))) * 0.7f
                }
                
                CarouselCard(
                    item = item,
                    isCurrent = offset == 0,
                    animatedScale = scale,
                    animatedAlpha = alpha,
                    animatedOffset = animatedOffset,
                    colors = when(index % 3) {
                        0 -> CarouselColors.Kotlin
                        1 -> CarouselColors.Compose
                        else -> CarouselColors.OpenSource
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .clickable {
                            scope.launch {
                                animatedIndex.animateTo(
                                    targetValue = index.toFloat(),
                                    animationSpec = tween(
                                        durationMillis = 600,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                                currentIndex.value = index
                            }
                        }
                )
            }
        }

        // 指示器部分保持不变...
    }
}

@Composable
private fun CarouselCard(
    item: CarouselItem,
    isCurrent: Boolean,
    animatedScale: Float,
    animatedAlpha: Float,
    animatedOffset: Float,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(vertical = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .scale(if(isCurrent) 1f else 0.8f)
            .graphicsLayer {
                shadowElevation = if (isCurrent) 8f else 2f
                shape = RoundedCornerShape(16.dp)
                clip = true
                ambientShadowColor = Color.Black.copy(alpha = 0.1f)
                spotShadowColor = Color.Black.copy(alpha = 0.1f)
            }
            .background(
                brush = Brush.verticalGradient(colors)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = if (isCurrent) 32.dp else 16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Category,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(if (isCurrent) 48.dp else 32.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = item.title,
                style = if (isCurrent) MaterialTheme.typography.h5 else MaterialTheme.typography.h6,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = if (isCurrent) MaterialTheme.typography.body1 else MaterialTheme.typography.body2,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                maxLines = if (isCurrent) 2 else 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = KotlinColors.Primary,
                    modifier = Modifier.clickable { /* TODO: Add action */ }
                ) {
                    Text(
                        text = "开始使用",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color.White.copy(alpha = 0.15f),
                    modifier = Modifier.clickable { /* TODO: Add action */ }
                ) {
                    Text(
                        text = "了解更多",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

// 添加辅助函数
private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + (stop - start) * fraction
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
                        text = "发布",
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