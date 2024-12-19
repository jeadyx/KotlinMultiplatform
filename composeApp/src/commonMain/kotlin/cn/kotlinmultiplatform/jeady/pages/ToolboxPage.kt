package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.icons.Brush
import cn.kotlinmultiplatform.jeady.icons.Chat
import cn.kotlinmultiplatform.jeady.icons.Code
import cn.kotlinmultiplatform.jeady.icons.Document
import cn.kotlinmultiplatform.jeady.icons.FormatPaint
import cn.kotlinmultiplatform.jeady.icons.Gradient
import cn.kotlinmultiplatform.jeady.icons.Http
import cn.kotlinmultiplatform.jeady.icons.Image
import cn.kotlinmultiplatform.jeady.icons.Link
import cn.kotlinmultiplatform.jeady.icons.Mic
import cn.kotlinmultiplatform.jeady.icons.Music
import cn.kotlinmultiplatform.jeady.icons.Palette
import cn.kotlinmultiplatform.jeady.icons.PhotoLibrary
import cn.kotlinmultiplatform.jeady.icons.PictureAsPdf
import cn.kotlinmultiplatform.jeady.icons.Psychology
import cn.kotlinmultiplatform.jeady.icons.Schedule
import cn.kotlinmultiplatform.jeady.icons.Slideshow
import cn.kotlinmultiplatform.jeady.icons.Speed
import cn.kotlinmultiplatform.jeady.icons.TextFields
import cn.kotlinmultiplatform.jeady.icons.Timer
import cn.kotlinmultiplatform.jeady.icons.Transform
import cn.kotlinmultiplatform.jeady.icons.Video
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
    UTILITY,
    AI
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
        ),
        Tool("ChatGPT", "OpenAI开发的对话式AI模型", "https://chat.openai.com", Icons.Default.Code, ToolCategory.AI),
        Tool("Claude", "Anthropic开发的AI助手", "https://claude.ai", Icons.Default.Code, ToolCategory.AI),
        Tool("Midjourney", "AI图像生成工具", "https://www.midjourney.com", Icons.Default.Image, ToolCategory.AI),
        Tool("DALL-E", "OpenAI的AI图像生成工具", "https://labs.openai.com", Icons.Default.Image, ToolCategory.AI),
        Tool("Stable Diffusion", "开源AI图像生成模型", "https://stability.ai", Icons.Default.Image, ToolCategory.AI),
        Tool("Gemini", "Google的AI模型", "https://gemini.google.com", Icons.Default.Code, ToolCategory.AI),
        Tool("Copy.ai", "AI文案写作助手", "https://www.copy.ai", Icons.Default.Edit, ToolCategory.AI),
        Tool("Jasper", "AI内容创作平台", "https://www.jasper.ai", Icons.Default.Edit, ToolCategory.AI),
        Tool("Notion AI", "集成AI功能的笔记工具", "https://www.notion.so", Icons.Default.Edit, ToolCategory.AI),
        Tool("Grammarly", "AI写作纠错工具", "https://www.grammarly.com", Icons.Default.Edit, ToolCategory.AI),
        Tool("Synthesia", "AI视频生成平台", "https://www.synthesia.io", Icons.Default.Video, ToolCategory.AI),
        Tool("RunwayML", "AI视频编辑工具", "https://runway.ml", Icons.Default.Video, ToolCategory.AI),
        Tool("Descript", "AI音视频编辑工具", "https://www.descript.com", Icons.Default.Video, ToolCategory.AI),
        Tool("Otter.ai", "AI会议记录工具", "https://otter.ai", Icons.Default.Mic, ToolCategory.AI),
        Tool("Murf", "AI语音生成工具", "https://murf.ai", Icons.Default.Mic, ToolCategory.AI),
        Tool("Eleven Labs", "AI语音克隆工具", "https://elevenlabs.io", Icons.Default.Mic, ToolCategory.AI),
        Tool("GitHub Copilot", "AI代码助手", "https://github.com/features/copilot", Icons.Default.Code, ToolCategory.AI),
        Tool("Tabnine", "AI代码补全工具", "https://www.tabnine.com", Icons.Default.Code, ToolCategory.AI),
        Tool("Codeium", "免费AI代码助手", "https://codeium.com", Icons.Default.Code, ToolCategory.AI),
        Tool("Hugging Face", "AI模型开发平台", "https://huggingface.co", Icons.Default.Code, ToolCategory.AI),
        Tool("Leonardo.ai", "AI艺术创作平台", "https://leonardo.ai", Icons.Default.Brush, ToolCategory.AI),
        Tool("Firefly", "Adobe的AI创意套件", "https://www.adobe.com/firefly", Icons.Default.Brush, ToolCategory.AI),
        Tool("Canva AI", "AI设计助手", "https://www.canva.com", Icons.Default.Brush, ToolCategory.AI),
        Tool("Tome", "AI演示文稿工具", "https://tome.app", Icons.Default.Edit, ToolCategory.AI),
        Tool("Beautiful.ai", "AI幻灯片制作", "https://www.beautiful.ai", Icons.Default.Slideshow, ToolCategory.AI),
        Tool("Gamma", "AI文档创作工具", "https://gamma.app", Icons.Default.Document, ToolCategory.AI),
        Tool("Anthropic Claude", "企业级AI助手", "https://www.anthropic.com", Icons.Default.Code, ToolCategory.AI),
        Tool("Perplexity AI", "AI搜索引擎", "https://www.perplexity.ai", Icons.Default.Search, ToolCategory.AI),
        Tool("You.com", "AI搜索助手", "https://you.com", Icons.Default.Search, ToolCategory.AI),
        Tool("Poe", "AI聊天工具集合", "https://poe.com", Icons.Default.Chat, ToolCategory.AI),
        Tool("Character.ai", "AI角色扮演对话", "https://character.ai", Icons.Default.Chat, ToolCategory.AI),
        Tool("Replika", "AI伴侣聊天", "https://replika.ai", Icons.Default.Chat, ToolCategory.AI),
        Tool("AutoDraw", "Google的AI绘画工具", "https://www.autodraw.com", Icons.Default.Brush, ToolCategory.AI),
        Tool("Lensa", "AI头像生成器", "https://prisma-ai.com/lensa", Icons.Default.Image, ToolCategory.AI),
        Tool("Soundraw", "AI音乐创作工具", "https://soundraw.io", Icons.Default.Music, ToolCategory.AI),
        Tool(
            "GitHub",
            "代码托管和协作平台",
            "https://github.com",
            Icons.Default.Code,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "Stack Overflow",
            "程序员问答社区",
            "https://stackoverflow.com",
            Icons.Default.Code,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "CodePen",
            "在线代码编辑器和前端开发环境",
            "https://codepen.io",
            Icons.Default.Code,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "Can I Use",
            "浏览器特性兼容性查询",
            "https://caniuse.com",
            Icons.Default.Search,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "DevDocs",
            "各种编程语言和框架的文档集合",
            "https://devdocs.io",
            Icons.Default.Document,
            ToolCategory.DEVELOPMENT
        ),
        Tool(
            "Figma",
            "专业的在线设计工具",
            "https://www.figma.com",
            Icons.Default.Brush,
            ToolCategory.DESIGN
        ),
        Tool(
            "Dribbble",
            "设计师作品分享平台",
            "https://dribbble.com",
            Icons.Default.Image,
            ToolCategory.DESIGN
        ),
        Tool(
            "Behance",
            "创意作品展示平台",
            "https://www.behance.net",
            Icons.Default.Image,
            ToolCategory.DESIGN
        ),
        Tool(
            "Coolors",
            "配色方案生成器",
            "https://coolors.co",
            Icons.Default.Palette,
            ToolCategory.DESIGN
        ),
        Tool(
            "Unsplash",
            "免费高质量图片资源",
            "https://unsplash.com",
            Icons.Default.Image,
            ToolCategory.DESIGN
        ),
        Tool(
            "Notion",
            "多功能笔记和协作工具",
            "https://www.notion.so",
            Icons.Default.Edit,
            ToolCategory.PRODUCTIVITY
        ),
        Tool(
            "Trello",
            "项目管理和任务追踪工具",
            "https://trello.com",
            Icons.Default.Schedule,
            ToolCategory.PRODUCTIVITY
        ),
        Tool(
            "Asana",
            "团队项目管理平台",
            "https://asana.com",
            Icons.Default.Schedule,
            ToolCategory.PRODUCTIVITY
        ),
        Tool(
            "Todoist",
            "待办事项和任务管理工具",
            "https://todoist.com",
            Icons.Default.Schedule,
            ToolCategory.PRODUCTIVITY
        ),
        Tool(
            "Evernote",
            "笔记和信息管理工具",
            "https://evernote.com",
            Icons.Default.Edit,
            ToolCategory.PRODUCTIVITY
        ),
        Tool(
            "WeTransfer",
            "大文件传输服务",
            "https://wetransfer.com",
            Icons.Default.Transform,
            ToolCategory.UTILITY
        ),
        Tool(
            "Speedtest",
            "网络速度测试工具",
            "https://www.speedtest.net",
            Icons.Default.Speed,
            ToolCategory.UTILITY
        ),
        Tool(
            "Wolfram Alpha",
            "智能计算和知识引擎",
            "https://www.wolframalpha.com",
            Icons.Default.Search,
            ToolCategory.UTILITY
        ),
        Tool(
            "Wayback Machine",
            "网页历史存档查看器",
            "https://web.archive.org",
            Icons.Default.Timer,
            ToolCategory.UTILITY
        ),
        Tool(
            "Down Detector",
            "网站服务状态监控",
            "https://downdetector.com",
            Icons.Default.Speed,
            ToolCategory.UTILITY
        ),
        Tool(
            "RunwayML",
            "AI视频编辑和生成工具",
            "https://runwayml.com",
            Icons.Default.Video,
            ToolCategory.AI
        ),
        Tool(
            "Jasper",
            "AI文案写作助手",
            "https://www.jasper.ai",
            Icons.Default.Edit,
            ToolCategory.AI
        ),
        Tool(
            "Synthesia",
            "AI视频生成平台",
            "https://www.synthesia.io",
            Icons.Default.Video,
            ToolCategory.AI
        ),
        Tool(
            "Otter.ai",
            "AI会议记录和转录工具",
            "https://otter.ai",
            Icons.Default.Mic,
            ToolCategory.AI
        ),
        Tool(
            "Grammarly",
            "AI写作纠错和改进工具",
            "https://www.grammarly.com",
            Icons.Default.Edit,
            ToolCategory.AI
        )
    )

    var showSearch by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    // Group tools by category
    val filteredTools = if (searchQuery.isEmpty()) {
        tools
    } else {
        tools.filter { tool ->
            tool.name.contains(searchQuery, ignoreCase = true) ||
            tool.description.contains(searchQuery, ignoreCase = true)
        }
    }
    val groupedTools = filteredTools.groupBy { it.category }

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(groupedTools.entries.toList().sortedBy { (category, _) ->
                when(category) {
                    ToolCategory.AI -> 0
                    ToolCategory.DEVELOPMENT -> 1
                    ToolCategory.DESIGN -> 2
                    ToolCategory.PRODUCTIVITY -> 3
                    ToolCategory.UTILITY -> 4
                }
            }) { (category, toolsInCategory) ->
                if (toolsInCategory.isNotEmpty()) {
                    Column {
                        // Category header with accent color
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = when(category) {
                                ToolCategory.DEVELOPMENT -> Color(0xFF2196F3).copy(alpha = 0.1f)
                                ToolCategory.DESIGN -> Color(0xFFE91E63).copy(alpha = 0.1f)
                                ToolCategory.PRODUCTIVITY -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                                ToolCategory.UTILITY -> Color(0xFFFF9800).copy(alpha = 0.1f)
                                ToolCategory.AI -> Color(0xFF9C27B0).copy(alpha = 0.1f)
                            },
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    val categoryColor = when(category) {
                                        ToolCategory.DEVELOPMENT -> Color(0xFF2196F3)
                                        ToolCategory.DESIGN -> Color(0xFFE91E63)
                                        ToolCategory.PRODUCTIVITY -> Color(0xFF4CAF50)
                                        ToolCategory.UTILITY -> Color(0xFFFF9800)
                                        ToolCategory.AI -> Color(0xFF9C27B0)
                                    }
                                    
                                    Icon(
                                        imageVector = when(category) {
                                            ToolCategory.DEVELOPMENT -> Icons.Default.Code
                                            ToolCategory.DESIGN -> Icons.Default.Brush
                                            ToolCategory.PRODUCTIVITY -> Icons.Default.Speed
                                            ToolCategory.UTILITY -> Icons.Default.Build
                                            ToolCategory.AI -> Icons.Default.Psychology
                                        },
                                        contentDescription = null,
                                        tint = categoryColor,
                                        modifier = Modifier.padding(end = 4.dp)
                                    )

                                    Text(
                                        text = when(category) {
                                            ToolCategory.DEVELOPMENT -> "开发工具"
                                            ToolCategory.DESIGN -> "设计工具"
                                            ToolCategory.PRODUCTIVITY -> "生产力工具"
                                            ToolCategory.UTILITY -> "实用工具"
                                            ToolCategory.AI -> "AI工具"
                                        },
                                        style = MaterialTheme.typography.h6,
                                        color = categoryColor
                                    )
                                }

                                Text(
                                    text = "${toolsInCategory.size}",
                                    style = MaterialTheme.typography.caption,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                                )
                            }
                        }

                        // Tools grid for this category
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 160.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.heightIn(50.dp, 350.dp),
                            userScrollEnabled = true,
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            items(toolsInCategory) { tool ->
                                ToolCard(tool = tool, onClick = { urlHandler.openUrl(tool.url) })
                            }
                        }
                    }
                }
            }
        }

        // Floating search button and search box
        Surface(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            elevation = if (showSearch) 8.dp else 0.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .width(if (showSearch) 320.dp else 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = { 
                        showSearch = !showSearch
                        if (!showSearch) searchQuery = ""
                    }
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
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("搜索工具...") },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
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
            .height(130.dp),
        elevation = 4.dp,
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
                    ToolCategory.AI -> Color(0xFF9C27B0)
                },
                modifier = Modifier.padding(bottom = 4.dp)
            )
            
            Text(
                text = tool.name,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
            
            Text(
                text = tool.description,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                maxLines = 2
            )
        }
    }
} 