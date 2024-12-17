package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.kotlinmultiplatform.jeady.icons.CustomBugReport
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class BugReport(
    val id: String,
    val title: String,
    val description: String,
    val status: BugStatus,
    val priority: BugPriority,
    val reportedAt: LocalDateTime,
    val reportedBy: String
)

enum class BugStatus(val label: String, val color: Color) {
    OPEN("待处理", Color(0xFFE57373)),
    IN_PROGRESS("处理中", Color(0xFFFFB74D)),
    FIXED("已修复", Color(0xFF81C784)),
    CLOSED("已关闭", Color(0xFF90A4AE))
}

enum class BugPriority(val label: String, val color: Color) {
    HIGH("高", Color(0xFFE57373)),
    MEDIUM("中", Color(0xFFFFB74D)),
    LOW("低", Color(0xFF81C784))
}

@Composable
fun BugsPage() {
    val bugs = remember {
        listOf(
            BugReport(
                id = "BUG-001",
                title = "Wasm 平台 Markdown 预览功能无法使用",
                description = "在 Wasm 平台上，Markdown 预览功能出现依赖错误，需要重新设计实现方案。",
                status = BugStatus.FIXED,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "张三"
            ),
            BugReport(
                id = "BUG-002",
                title = "博客编辑器保存按钮响应延迟",
                description = "在编辑博客文章时，点击保存按钮后有明显的延迟，需要优化保存逻辑。",
                status = BugStatus.IN_PROGRESS,
                priority = BugPriority.MEDIUM,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "李四"
            ),
            BugReport(
                id = "BUG-003",
                title = "移动端界面适配问题",
                description = "在小屏幕设备上，部分界面元素显示不完整，需要优化响应式布局。",
                status = BugStatus.OPEN,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "王五"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 标题
        Text(
            text = "问题追踪",
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // 统计信息
        BugStatistics(bugs)

        Spacer(modifier = Modifier.height(24.dp))

        // Bug列表
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(bugs) { bug ->
                BugCard(bug)
            }
        }
    }
}

@Composable
private fun BugStatistics(bugs: List<BugReport>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BugStatus.values().forEach { status ->
            val count = bugs.count { it.status == status }
            StatCard(
                label = status.label,
                count = count,
                color = status.color,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun StatCard(
    label: String,
    count: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 2.dp,
        backgroundColor = color.copy(alpha = 0.1f)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.h4,
                color = color,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = color
            )
        }
    }
}

@Composable
private fun BugCard(bug: BugReport) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.CustomBugReport,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        text = bug.id,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primary
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatusChip(bug.status)
                    PriorityChip(bug.priority)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = bug.title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = bug.description,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "报告人：${bug.reportedBy}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "报告时间：${bug.reportedAt}",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Composable
private fun StatusChip(status: BugStatus) {
    Surface(
        color = status.color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = status.label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.caption,
            color = status.color
        )
    }
}

@Composable
private fun PriorityChip(priority: BugPriority) {
    Surface(
        color = priority.color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = priority.label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.caption,
            color = priority.color
        )
    }
} 