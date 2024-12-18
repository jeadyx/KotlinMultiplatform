package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.icons.Brush
import cn.kotlinmultiplatform.jeady.icons.Code
import cn.kotlinmultiplatform.jeady.icons.FormatPaint
import cn.kotlinmultiplatform.jeady.icons.Gradient
import cn.kotlinmultiplatform.jeady.icons.Http
import cn.kotlinmultiplatform.jeady.icons.Image
import cn.kotlinmultiplatform.jeady.icons.Link
import cn.kotlinmultiplatform.jeady.icons.Palette
import cn.kotlinmultiplatform.jeady.icons.PhotoLibrary
import cn.kotlinmultiplatform.jeady.icons.PictureAsPdf
import cn.kotlinmultiplatform.jeady.icons.Schedule
import cn.kotlinmultiplatform.jeady.icons.Speed
import cn.kotlinmultiplatform.jeady.icons.TextFields
import cn.kotlinmultiplatform.jeady.icons.Timer
import cn.kotlinmultiplatform.jeady.icons.Transform
import cn.kotlinmultiplatform.jeady.platform.UrlHandler

data class Tool(
    val name: String,
    val description: String,
    val url: String,
    val icon: ImageVector,
    val category: ToolCategory
)

enum class ToolCategory {
    DEVELOPMENT,
    DESIGN,
    PRODUCTIVITY,
    UTILITY
}

@Composable
fun ToolboxPage(urlHandler: UrlHandler) {
    val tools = listOf(
        Tool(
            "JSON 格式化",
            "在线 JSON 格式化工具，支持压缩和美化",
            "https://jsonformatter.org/",
            Icons.Default.Code,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "Base64 编解码",
            "在线 Base64 编码和解码工具",
            "https://www.base64encode.org/",
            Icons.Default.Transform,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "正则表达式测试",
            "在线正则表达式测试和调试工具",
            "https://regex101.com/",
            Icons.Default.Search,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "颜色选择器",
            "在线颜色选择和转换工具",
            "https://colorpicker.me/",
            Icons.Default.Palette,
            ToolCategory.DESIGN
        ),
        Tool(
            "图标库",
            "Material Design 图标库",
            "https://fonts.google.com/icons",
            Icons.Default.Image,
            ToolCategory.DESIGN
        ),
        Tool(
            "Markdown 编辑器",
            "在线 Markdown 编辑和预览工具",
            "https://stackedit.io/",
            Icons.Default.Edit,
            ToolCategory.PRODUCTIVITY
        ),
        Tool(
            "时间戳转换",
            "Unix 时间戳转换工具",
            "https://www.epochconverter.com/",
            Icons.Default.Timer,
            ToolCategory.UTILITY
        ),
        Tool(
            "URL 编解码",
            "在线 URL 编码和解码工具",
            "https://www.urlencoder.org/",
            Icons.Default.Link,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "JWT 解码器",
            "在线 JWT token 解码工具",
            "https://jwt.io/",
            Icons.Default.Lock,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "SVG 编辑器",
            "在线 SVG 编辑和优化工具",
            "https://vectr.com/",
            Icons.Default.Brush,
            ToolCategory.DESIGN
        ),
        Tool(
            "代码美化",
            "各种语言的代码格式化工具",
            "https://beautifier.io/",
            Icons.Default.FormatPaint,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "CSS 渐变生成器",
            "在线 CSS 渐变背景生成器",
            "https://cssgradient.io/",
            Icons.Default.Gradient,
            ToolCategory.DESIGN
        ),
        Tool(
            "Cron 表达式生成器",
            "在线 Cron 表达式生成和验证工具",
            "https://crontab.guru/",
            Icons.Default.Schedule,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "图片压缩",
            "在线图片压缩工具",
            "https://tinypng.com/",
            Icons.Default.PhotoLibrary,
            ToolCategory.UTILITY
        ),
        Tool(
            "PDF 工具",
            "在线 PDF 转换和编辑工具",
            "https://www.ilovepdf.com/",
            Icons.Default.PictureAsPdf,
            ToolCategory.UTILITY
        ),
        Tool(
            "字体预览",
            "Google Fonts 字体预览",
            "https://fonts.google.com/",
            Icons.Default.TextFields,
            ToolCategory.DESIGN
        ),
        Tool(
            "API 测试",
            "在线 API 测试工具",
            "https://hoppscotch.io/",
            Icons.Default.Http,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "Git 命令生成器",
            "常用 Git 命令生成工具",
            "https://gitexplorer.com/",
            Icons.Default.Code,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "在线绘图工具",
            "流程图、UML 等在线绘图工具",
            "https://app.diagrams.net/",
            Icons.Default.Create,
            ToolCategory.PRODUCTIVITY
        ),
        Tool(
            "性能测试",
            "网站性能测试工具",
            "https://www.webpagetest.org/",
            Icons.Default.Speed,
            ToolCategory.UTILITY
        )
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "开发工具箱",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tools) { tool ->
                ToolCard(tool = tool, onClick = { urlHandler.openUrl(tool.url) })
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ToolCard(
    tool: Tool,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = 2.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = tool.icon,
                contentDescription = null,
                tint = when(tool.category) {
                    ToolCategory.DEVELOPMENT -> Color(0xFF2196F3)
                    ToolCategory.DESIGN -> Color(0xFFE91E63)
                    ToolCategory.PRODUCTIVITY -> Color(0xFF4CAF50)
                    ToolCategory.UTILITY -> Color(0xFFFF9800)
                }
            )
            
            Text(
                text = tool.name,
                style = MaterialTheme.typography.subtitle1
            )
            
            Text(
                text = tool.description,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                maxLines = 2
            )
        }
    }
} 