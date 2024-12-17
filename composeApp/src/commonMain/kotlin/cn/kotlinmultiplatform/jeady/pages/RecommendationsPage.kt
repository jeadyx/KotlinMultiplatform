package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.kotlin_multiplatform
import kotlinmultiplatform.composeapp.generated.resources.jetpack_compose
import androidx.compose.foundation.interaction.collectIsPressedAsState

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
    val description: String
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecommendationsPage(
    onNavigateToDetail: (String) -> Unit
) {
    val carouselItems = listOf(
        CarouselItem("Kotlin多平台开发", "一次编写，到处运行"),
        CarouselItem("现代化UI框架", "使用Compose构建跨平台应用"),
        CarouselItem("开源技术", "拥抱开源，共建生态")
    )
    
    val recommendations = listOf(
        RecommendItem(
            title = "Kotlin Multiplatform Mobile",
            description = "使用 Kotlin 开发跨平台应用的现代解决方案",
            category = "跨平台开发",
            tags = listOf("Kotlin", "Mobile", "跨平台"),
            iconRes = Res.drawable.kotlin_multiplatform,
            onClick = { onNavigateToDetail("kmm") }
        ),
        RecommendItem(
            title = "Jetpack Compose",
            description = "Android 现代化 UI 开发工具包",
            category = "UI框",
            tags = listOf("Android", "UI", "声明式"),
            iconRes = Res.drawable.jetpack_compose,
            onClick = { onNavigateToDetail("compose") }
        ),
        RecommendItem(
            title = "Flutter",
            description = "Google 的跨平台应用开发框架",
            category = "跨平台开发",
            tags = listOf("Dart", "Mobile", "跨平台"),
            iconRes = Res.drawable.kotlin_multiplatform,
            onClick = { onNavigateToDetail("flutter") }
        ),
        RecommendItem(
            title = "SwiftUI",
            description = "Apple 原生的声明式 UI 框架",
            category = "UI框架",
            tags = listOf("Swift", "iOS", "声明式"),
            iconRes = Res.drawable.jetpack_compose,
            onClick = { onNavigateToDetail("swiftui") }
        ),
        RecommendItem(
            title = "React Native",
            description = "使用 React 构建原生应用",
            category = "跨平台开发",
            tags = listOf("JavaScript", "React", "跨平台"),
            iconRes = Res.drawable.kotlin_multiplatform,
            onClick = { onNavigateToDetail("react-native") }
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
        
        items(recommendations) { item ->
            RecommendationCard(item)
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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp)
    ) {
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
        // 底部大阴影
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
                        Icon(
                            painter = painterResource(item.iconRes),
                            contentDescription = item.title,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(32.dp),
                            tint = MaterialTheme.colors.primary
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