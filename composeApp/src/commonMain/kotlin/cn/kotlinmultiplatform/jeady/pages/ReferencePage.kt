package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.platform.UrlHandler

enum class ReferenceSection(val title: String, val description: String) {
    OPEN_SOURCE("开源项目", "发现优质的 Kotlin Multiplatform 开源项目"),
    TOOLBOX("工具箱", "常用工具和资源集合"),
    PRODUCTS("成品展示", "优秀的 KMP 应用和网站展示")
}

@Composable
fun ReferenceHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primary,
                        MaterialTheme.colors.primary.copy(alpha = 0.8f)
                    )
                )
            )
    ) {
        // 背景装饰
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val path = Path()
            val width = size.width
            val height = size.height
            
            // 创建波浪形装饰
            path.moveTo(0f, height * 0.7f)
            path.cubicTo(
                width * 0.3f, height * 0.6f,
                width * 0.7f, height * 0.8f,
                width, height * 0.7f
            )
            path.lineTo(width, height)
            path.lineTo(0f, height)
            path.close()
            
            drawPath(
                path = path,
                color = Color.White.copy(alpha = 0.1f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "技术参考",
                style = MaterialTheme.typography.h4,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "探索 Kotlin Multiplatform 开发的最佳实践和技术细节",
                style = MaterialTheme.typography.subtitle1,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatCard(
                    count = "50+",
                    label = "技术文档"
                )
                StatCard(
                    count = "100+",
                    label = "代码示例"
                )
                StatCard(
                    count = "20+",
                    label = "最佳实践"
                )
            }
        }
    }
}

@Composable
private fun StatCard(
    count: String,
    label: String
) {
    Card(
        modifier = Modifier
            .padding(4.dp),
        backgroundColor = Color.White.copy(alpha = 0.15f),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count,
                style = MaterialTheme.typography.h6,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.caption,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun ReferencePage(
    urlHandler: UrlHandler,
    onSectionClick: (ReferenceSection) -> Unit
) {
    var selectedSection by remember { mutableStateOf<ReferenceSection?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ReferenceHeader()

        // Sections
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(ReferenceSection.values()) { section ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clickable { onSectionClick(section) },
                    elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = section.title,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = section.description,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        }
    }
}
