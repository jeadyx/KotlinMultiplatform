package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.kotlin_multiplatform
import kotlinmultiplatform.composeapp.generated.resources.jetpack_compose

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
fun RecommendationsPage() {
    val carouselItems = listOf(
        CarouselItem("Kotlin多平台开发", "一次编写，到处运行"),
        CarouselItem("现代化UI框架", "使用Compose构建跨平台应用"),
        CarouselItem("开源技术", "拥抱开源，共建生态")
    )
    
    val recommendations = listOf(
        RecommendItem(
            "Kotlin Multiplatform Mobile",
            "使用 Kotlin 开发跨平台应用的现代解决方案",
            "跨平台开发",
            listOf("Kotlin", "Mobile", "跨平台"),
            Res.drawable.kotlin_multiplatform
        ) {
            println("Clicked: KMM")
        },
        RecommendItem(
            "Jetpack Compose",
            "Android 现代化 UI 开发工具包",
            "UI框架",
            listOf("Android", "UI", "声明式"),
            Res.drawable.jetpack_compose
        ) {
            println("Clicked: Compose")
        },
        RecommendItem(
            "Flutter",
            "Google 的跨平台应用开发框架",
            "跨平台开发",
            listOf("Dart", "Mobile", "跨平台"),
            Res.drawable.kotlin_multiplatform
        ) {
            println("Clicked: Flutter")
        },
        RecommendItem(
            "SwiftUI",
            "Apple 原生的声明式 UI 框架",
            "UI框架",
            listOf("Swift", "iOS", "声明式"),
            Res.drawable.jetpack_compose
        ) {
            println("Clicked: SwiftUI")
        },
        RecommendItem(
            "React Native",
            "使用 React 构建原生应用",
            "跨平台开发",
            listOf("JavaScript", "React", "跨平台"),
            Res.drawable.kotlin_multiplatform
        ) {
            println("Clicked: React Native")
        }
    )
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { 
                item.onClick() 
            },
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box {
            // 背景装饰
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 40.dp, y = (-40).dp)
                    .background(
                        MaterialTheme.colors.primary.copy(alpha = 0.05f),
                        CircleShape
                    )
            )
            
            Row(
                modifier = Modifier.padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 图标部分
                Surface(
                    modifier = Modifier.size(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colors.primary.copy(alpha = 0.1f)
                ) {
                    Icon(
                        painter = painterResource(item.iconRes),
                        contentDescription = item.title,
                        modifier = Modifier
                            .padding(12.dp)
                            .size(32.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
                
                // 内容部分
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // 标题行
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colors.primary
                        )
                        CategoryChip(item.category)
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 描述
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.body1,
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

@Composable
private fun CategoryChip(category: String) {
    Surface(
        color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Medium
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
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "#$tag",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
} 