package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cn.kotlinmultiplatform.jeady.components.MarkdownPreview
import cn.kotlinmultiplatform.jeady.icons.CustomCode
import cn.kotlinmultiplatform.jeady.icons.CustomFormatBold
import cn.kotlinmultiplatform.jeady.icons.CustomFormatItalic
import cn.kotlinmultiplatform.jeady.icons.CustomFormatListBulleted
import cn.kotlinmultiplatform.jeady.icons.CustomLink
import cn.kotlinmultiplatform.jeady.icons.CustomTitle
import cn.kotlinmultiplatform.jeady.icons.CustomVisibility
import cn.kotlinmultiplatform.jeady.model.BlogPost
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun MarkdownEditor(
    post: BlogPost?,
    onSave: (BlogPost) -> Unit,
    onClose: () -> Unit
) {
    var title by remember { mutableStateOf(post?.title ?: "") }
    var content by remember { mutableStateOf(post?.content ?: "") }
    var category by remember { mutableStateOf(post?.category ?: "技术") }
    var tags by remember { mutableStateOf(post?.tags?.joinToString(", ") ?: "") }
    var summary by remember { mutableStateOf(post?.summary ?: "") }
    var isPreviewMode by remember { mutableStateOf(false) }
    var showMetadataDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (post == null) "新建文章" else "编辑文章") },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Default.Close, contentDescription = "关闭")
                    }
                },
                actions = {
                    // 预览切换按钮
                    IconButton(
                        onClick = { isPreviewMode = !isPreviewMode },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            if (isPreviewMode) Icons.Default.Edit else Icons.Filled.CustomVisibility,
                            contentDescription = if (isPreviewMode) "编辑" else "预览",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                    
                    Button(
                        onClick = { showMetadataDialog = true },
                        enabled = content.isNotEmpty(),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.onPrimary,
                            contentColor = MaterialTheme.colors.primary,
                            disabledBackgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                            disabledContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.38f)
                        )
                    ) {
                        Text("保存")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (!isPreviewMode) {
                // Markdown工具栏
                MarkdownToolbar(
                    onBoldClick = { content += "**粗体文本**" },
                    onItalicClick = { content += "*斜体文本*" },
                    onHeadingClick = { content += "\n### 标题" },
                    onLinkClick = { content += "[链接文本](https://example.com)" },
                    onListClick = { content += "\n- 列表项" },
                    onCodeClick = { content += "\n```\n代码块\n```" }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // 内容区域
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isPreviewMode) {
                    MarkdownPreview(
                        content = content,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                } else {
                    BasicTextField(
                        value = content,
                        onValueChange = { content = it },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface,
                            lineHeight = 1.5.em
                        )
                    )
                }
            }
        }
    }

    if (showMetadataDialog) {
        Dialog(onDismissRequest = { showMetadataDialog = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "文章信息",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("标题") },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.subtitle1,
                        shape = RoundedCornerShape(8.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedTextField(
                            value = category,
                            onValueChange = { category = it },
                            label = { Text("分类") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        
                        OutlinedTextField(
                            value = tags,
                            onValueChange = { tags = it },
                            label = { Text("标签 (逗号分隔)") },
                            modifier = Modifier.weight(2f),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = summary,
                        onValueChange = { summary = it },
                        label = { Text("摘要") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2,
                        shape = RoundedCornerShape(8.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = { showMetadataDialog = false }
                        ) {
                            Text("取消")
                        }
                        
                        Button(
                            onClick = {
                                val newPost = BlogPost(
                                    id = post?.id ?: Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString(),
                                    title = title,
                                    content = content,
                                    summary = summary,
                                    category = category,
                                    tags = tags.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                                    author = "当前用户", // TODO: 实现用户系统
                                    publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                                    readingTime = (content.length / 500).coerceAtLeast(1) // 假设每500字符需要1分钟阅读
                                )
                                onSave(newPost)
                                showMetadataDialog = false
                            },
                            enabled = title.isNotEmpty() && content.isNotEmpty(),
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text("确认")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MarkdownToolbar(
    onBoldClick: () -> Unit,
    onItalicClick: () -> Unit,
    onHeadingClick: () -> Unit,
    onLinkClick: () -> Unit,
    onListClick: () -> Unit,
    onCodeClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ToolbarButton(
                icon = Icons.Filled.CustomFormatBold,
                contentDescription = "粗体",
                onClick = onBoldClick
            )
            ToolbarButton(
                icon = Icons.Filled.CustomFormatItalic,
                contentDescription = "斜体",
                onClick = onItalicClick
            )
            ToolbarButton(
                icon = Icons.Filled.CustomTitle,
                contentDescription = "标题",
                onClick = onHeadingClick
            )
            ToolbarButton(
                icon = Icons.Filled.CustomLink,
                contentDescription = "链接",
                onClick = onLinkClick
            )
            ToolbarButton(
                icon = Icons.Filled.CustomFormatListBulleted,
                contentDescription = "列表",
                onClick = onListClick
            )
            ToolbarButton(
                icon = Icons.Filled.CustomCode,
                contentDescription = "代码",
                onClick = onCodeClick
            )
        }
    }
}

@Composable
private fun ToolbarButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(4.dp)),
        color = MaterialTheme.colors.surface
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                icon,
                contentDescription = contentDescription,
                tint = MaterialTheme.colors.onSurface.copy(alpha = 0.87f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
} 