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
            ),
            BugReport(
                id = "BUG-004",
                title = "图片加载失败",
                description = "在某些情况下，推荐页面的图片无法正常加载，需要添加错误处理和重试机制。",
                status = BugStatus.IN_PROGRESS,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "赵六"
            ),
            BugReport(
                id = "BUG-005",
                title = "性能优化",
                description = "页面首次加载时间过长，需要进行性能优化和资源压缩。",
                status = BugStatus.OPEN,
                priority = BugPriority.MEDIUM,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "钱七"
            ),
            BugReport(
                id = "BUG-006",
                title = "字体显示异常",
                description = "在某些平台上中文字体显示不正确，需要检查字体配置和加载逻辑。",
                status = BugStatus.FIXED,
                priority = BugPriority.LOW,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "孙八"
            ),
            BugReport(
                id = "BUG-007",
                title = "内存泄漏",
                description = "长时间使用后内存占用持续增加，需要排查内存泄漏问题。",
                status = BugStatus.IN_PROGRESS,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "周九"
            ),
            BugReport(
                id = "BUG-008",
                title = "导航栈错误",
                description = "在特定操作序列下导航栈状态错误，导致返回按钮行为异常。",
                status = BugStatus.OPEN,
                priority = BugPriority.MEDIUM,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "吴十"
            ),
            BugReport(
                id = "BUG-009",
                title = "状态管理问题",
                description = "多个组件之间的状态同步存在问题，需要重构状态管理逻辑。",
                status = BugStatus.IN_PROGRESS,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "郑十一"
            ),
            BugReport(
                id = "BUG-010",
                title = "主题切换异常",
                description = "动态切换主题时部分组件样式未更新，需要检查主题系统实现。",
                status = BugStatus.FIXED,
                priority = BugPriority.LOW,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "王十二"
            ),
            BugReport(
                id = "BUG-011",
                title = "键盘处理问题",
                description = "在移动端输入时键盘弹出导致布局错乱，需要优化键盘处理逻辑。",
                status = BugStatus.OPEN,
                priority = BugPriority.MEDIUM,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "李十三"
            ),
            BugReport(
                id = "BUG-012",
                title = "动画卡顿",
                description = "复杂列表滚动时动画卡顿，需要优化渲染性能。",
                status = BugStatus.IN_PROGRESS,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "张十四"
            ),
            BugReport(
                id = "BUG-013",
                title = "数据持久化失败",
                description = "在某些情况下本地数据保存失败，需要增加错误处理和重试机制。",
                status = BugStatus.OPEN,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "刘十五"
            ),
            BugReport(
                id = "BUG-014",
                title = "路由参数丢失",
                description = "页面刷新后路由参数丢失，需要优化路由状态保持机制。",
                status = BugStatus.FIXED,
                priority = BugPriority.MEDIUM,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "陈十六"
            ),
            BugReport(
                id = "BUG-015",
                title = "搜索性能问题",
                description = "大量数据时搜索响应缓慢，需要优化搜索算法和索引机制。",
                status = BugStatus.IN_PROGRESS,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "杨十七"
            ),
            BugReport(
                id = "BUG-016",
                title = "图片缓存问题",
                description = "图片缓存策略不当导致内存占用过高，需要优化缓存机制。",
                status = BugStatus.OPEN,
                priority = BugPriority.MEDIUM,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "黄十八"
            ),
            BugReport(
                id = "BUG-017",
                title = "字体加载延迟",
                description = "自定义字体加载时间过长，导致文字闪烁，需要优化字体加载策略。",
                status = BugStatus.IN_PROGRESS,
                priority = BugPriority.LOW,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "赵十九"
            ),
            BugReport(
                id = "BUG-018",
                title = "暗色主题适配",
                description = "部分组件在暗色主题下对比度不足，需要调整配色方案。",
                status = BugStatus.OPEN,
                priority = BugPriority.MEDIUM,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "钱二十"
            ),
            BugReport(
                id = "BUG-019",
                title = "动画内存泄漏",
                description = "反复播放动画导致内存泄漏，需要检查动画资源释放机制。",
                status = BugStatus.IN_PROGRESS,
                priority = BugPriority.HIGH,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "孙二一"
            ),
            BugReport(
                id = "BUG-020",
                title = "滚动位置丢失",
                description = "切换页面后返回，滚动位置未能正确恢复，需要实现滚动位置保持。",
                status = BugStatus.FIXED,
                priority = BugPriority.LOW,
                reportedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                reportedBy = "周二二"
            )
        )
    }

    var currentPage by remember { mutableStateOf(0) }
    val itemsPerPage = 5
    val totalPages = (bugs.size + itemsPerPage - 1) / itemsPerPage
    val currentPageBugs = bugs.drop(currentPage * itemsPerPage).take(itemsPerPage)

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
        Column(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(currentPageBugs) { bug ->
                    BugCard(bug)
                }
            }
            
            if (bugs.size > itemsPerPage) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { if (currentPage > 0) currentPage-- },
                        enabled = currentPage > 0
                    ) {
                        Text("上一页")
                    }
                    
                    Text(
                        text = "${currentPage + 1} / $totalPages",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    
                    TextButton(
                        onClick = { if (currentPage < totalPages - 1) currentPage++ },
                        enabled = currentPage < totalPages - 1
                    ) {
                        Text("下一页")
                    }
                }
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