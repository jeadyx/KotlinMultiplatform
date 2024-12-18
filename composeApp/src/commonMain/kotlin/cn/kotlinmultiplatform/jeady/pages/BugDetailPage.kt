package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.model.Bug

@Composable
fun BugDetailPage(
    bug: Bug,
    onNavigateBack: () -> Unit,
    onEdit: (Bug) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部栏
        TopAppBar(
            title = { 
                Text(
                    "Bug 详情",
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "返回",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = { onEdit(bug) }) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "编辑",
                        tint = Color.White
                    )
                }
            },
            backgroundColor = Color(0xFF2196F3),
            elevation = 4.dp
        )

        // 内容区域
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Bug 标题
            Text(
                text = bug.title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 状态和优先级
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatusChip(status = bug.status, selected = true, onClick = {})
                PriorityChip(priority = bug.priority, selected = true, onClick = {})
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 描述
            Text(
                text = "描述",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bug.description,
                style = MaterialTheme.typography.body1
            )
            
            if (bug.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                
                // 标签
                Text(
                    text = "标签",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    bug.tags.forEach { tag ->
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colors.primary.copy(alpha = 0.1f)
                        ) {
                            Text(
                                text = tag,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
        }
    }
} 