package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.icons.BugReport
import cn.kotlinmultiplatform.jeady.icons.FilterList
import cn.kotlinmultiplatform.jeady.icons.Search
import cn.kotlinmultiplatform.jeady.icons.SortAscending
import cn.kotlinmultiplatform.jeady.icons.SortDescending
import cn.kotlinmultiplatform.jeady.model.Bug
import cn.kotlinmultiplatform.jeady.model.BugPriority
import cn.kotlinmultiplatform.jeady.model.BugStatus
import cn.kotlinmultiplatform.jeady.utils.IdGenerator
import kotlinx.datetime.Clock

@Composable
fun BugsPage(
    bugs: List<Bug>,
    onEdit: (Bug) -> Unit,
    onDelete: (String) -> Unit,
    onAdd: () -> Unit,
    onNavigateToBugDetail: (Bug) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var sortBy by remember { mutableStateOf(BugSortOption.CREATED_TIME) }
    var sortAscending by remember { mutableStateOf(false) }
    var filterStatus by remember { mutableStateOf<BugStatus?>(null) }
    var filterPriority by remember { mutableStateOf<BugPriority?>(null) }
    var showFilterMenu by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // 美化标题栏
        TopAppBar(
            title = { 
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Icon(
                        Icons.Filled.BugReport,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Bug 管理",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = Color.White
                    )
                }
            },
            backgroundColor = Color(0xFF2196F3),
            contentColor = Color.White,
            elevation = 4.dp,
            actions = {
                // 修改搜索框样式
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { 
                        Text(
                            "搜索 Bug...", 
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        ) 
                    },
                    modifier = Modifier
                        .width(280.dp), // 增加宽度 // 调整内边距
                    singleLine = true,
                    leadingIcon = { 
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        )
                    },
                    textStyle = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onSurface
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.95f),
                        unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
                        focusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                        cursorColor = MaterialTheme.colors.onSurface,
                        textColor = MaterialTheme.colors.onSurface
                    )
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                // 操作按钮美化
                IconButton(
                    onClick = { showFilterMenu = true },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        Icons.Filled.FilterList,
                        contentDescription = "筛选",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
                
                IconButton(
                    onClick = { sortAscending = !sortAscending },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        if (sortAscending) Icons.Filled.SortAscending else Icons.Filled.SortDescending,
                        contentDescription = "排序",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
                
                IconButton(
                    onClick = onAdd,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "添加",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            },
        )

        // 筛选条件显示
        AnimatedVisibility(
            visible = filterStatus != null || filterPriority != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (filterStatus != null) {
                    FilterChip(
                        text = "状态: ${filterStatus!!.toDisplayString()}",
                        onRemove = { filterStatus = null }
                    )
                }
                if (filterPriority != null) {
                    FilterChip(
                        text = "优先级: ${filterPriority!!.toDisplayString()}",
                        onRemove = { filterPriority = null }
                    )
                }
            }
        }

        // Bug 列表
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val filteredBugs = bugs
                .filter { 
                    (searchQuery.isEmpty() || it.title.contains(searchQuery, ignoreCase = true) || 
                     it.description.contains(searchQuery, ignoreCase = true)) &&
                    (filterStatus == null || it.status == filterStatus) &&
                    (filterPriority == null || it.priority == filterPriority)
                }
                .sortedWith(
                    when (sortBy) {
                        BugSortOption.CREATED_TIME -> compareBy<Bug> { it.createdAt }
                        BugSortOption.UPDATED_TIME -> compareBy<Bug> { it.updatedAt }
                        BugSortOption.PRIORITY -> compareBy<Bug> { it.priority.ordinal }
                        BugSortOption.STATUS -> compareBy<Bug> { it.status.ordinal }
                    }.let { if (sortAscending) it else it.reversed() }
                )

            items(filteredBugs) { bug ->
                BugCard(
                    bug = bug,
                    onEdit = { onEdit(bug) },
                    onDelete = { onDelete(bug.id) },
                    onSelect = { onNavigateToBugDetail(bug) }
                )
            }
        }
    }

    // 筛选菜单
    if (showFilterMenu) {
        FilterDialog(
            currentStatus = filterStatus,
            currentPriority = filterPriority,
            currentSortBy = sortBy,
            onDismiss = { showFilterMenu = false },
            onApply = { status, priority, sort ->
                filterStatus = status
                filterPriority = priority
                sortBy = sort
                showFilterMenu = false
            }
        )
    }
}

// 排序选项
enum class BugSortOption {
    CREATED_TIME,
    UPDATED_TIME,
    PRIORITY,
    STATUS
}

@Composable
private fun FilterChip(
    text: String,
    onRemove: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.primary.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text)
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "移除筛选",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun FilterDialog(
    currentStatus: BugStatus?,
    currentPriority: BugPriority?,
    currentSortBy: BugSortOption,
    onDismiss: () -> Unit,
    onApply: (BugStatus?, BugPriority?, BugSortOption) -> Unit
) {
    var status by remember { mutableStateOf(currentStatus) }
    var priority by remember { mutableStateOf(currentPriority) }
    var sortBy by remember { mutableStateOf(currentSortBy) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("筛选和排序") },
        text = {
            Column {
                Text("状态", style = MaterialTheme.typography.subtitle1)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BugStatus.values().forEach { s ->
                        FilterOption(
                            text = s.toDisplayString(),
                            selected = status == s,
                            onClick = { status = if (status == s) null else s }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("优先级", style = MaterialTheme.typography.subtitle1)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BugPriority.values().forEach { p ->
                        FilterOption(
                            text = p.toDisplayString(),
                            selected = priority == p,
                            onClick = { priority = if (priority == p) null else p }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("排序", style = MaterialTheme.typography.subtitle1)
                Column {
                    BugSortOption.values().forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { sortBy = option }
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = sortBy == option,
                                onClick = { sortBy = option }
                            )
                            Text(
                                text = when (option) {
                                    BugSortOption.CREATED_TIME -> "创建时间"
                                    BugSortOption.UPDATED_TIME -> "更新时间"
                                    BugSortOption.PRIORITY -> "优先级"
                                    BugSortOption.STATUS -> "状态"
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onApply(status, priority, sortBy) }) {
                Text("应用")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    )
}

@Composable
private fun FilterOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (selected) 
            MaterialTheme.colors.primary 
        else 
            MaterialTheme.colors.primary.copy(alpha = 0.1f),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// 扩展函数
fun BugStatus.toDisplayString() = when(this) {
    BugStatus.OPEN -> "待处理"
    BugStatus.IN_PROGRESS -> "处理中"
    BugStatus.RESOLVED -> "已解决"
    BugStatus.CLOSED -> "已关闭"
}

fun BugPriority.toDisplayString() = when(this) {
    BugPriority.LOW -> "低"
    BugPriority.MEDIUM -> "中"
    BugPriority.HIGH -> "高"
    BugPriority.CRITICAL -> "紧急"
}

@Composable
private fun BugCard(
    bug: Bug,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = bug.title,
                    style = MaterialTheme.typography.h6
                )
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "编辑")
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "删除")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = bug.description,
                style = MaterialTheme.typography.body1
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 状态标签
                Surface(
                    color = when(bug.status) {
                        BugStatus.OPEN -> Color(0xFFE57373)
                        BugStatus.IN_PROGRESS -> Color(0xFF64B5F6)
                        BugStatus.RESOLVED -> Color(0xFF81C784)
                        BugStatus.CLOSED -> Color(0xFF90A4AE)
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = when(bug.status) {
                            BugStatus.OPEN -> "待处理"
                            BugStatus.IN_PROGRESS -> "处理中"
                            BugStatus.RESOLVED -> "已解决"
                            BugStatus.CLOSED -> "已关闭"
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Color.White
                    )
                }
                
                // 优先级标签
                Surface(
                    color = when(bug.priority) {
                        BugPriority.LOW -> Color(0xFF90A4AE)
                        BugPriority.MEDIUM -> Color(0xFFFFA726)
                        BugPriority.HIGH -> Color(0xFFEF5350)
                        BugPriority.CRITICAL -> Color(0xFFD32F2F)
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = when(bug.priority) {
                            BugPriority.LOW -> "低"
                            BugPriority.MEDIUM -> "中"
                            BugPriority.HIGH -> "高"
                            BugPriority.CRITICAL -> "紧急"
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun BugDialog(
    bug: Bug?,
    onDismiss: () -> Unit,
    onSave: (String, String, BugPriority, BugStatus, List<String>) -> Unit
) {
    var title by remember { mutableStateOf(bug?.title ?: "") }
    var description by remember { mutableStateOf(bug?.description ?: "") }
    var priority by remember { mutableStateOf(bug?.priority ?: BugPriority.MEDIUM) }
    var status by remember { mutableStateOf(bug?.status ?: BugStatus.OPEN) }
    var tags by remember { mutableStateOf(bug?.tags ?: listOf()) }
    
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("基本信息", "状态与优先级")

    AlertDialog(
        modifier = Modifier.width(400.dp),
        onDismissRequest = onDismiss,
        title = { 
            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    if (bug == null) "添加 Bug" else "编辑 Bug",
                    style = MaterialTheme.typography.h6
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(top = 16.dp)
            ) {
                Surface(
                    color = MaterialTheme.colors.surface,
                    elevation = 1.dp
                ) {
                    TabRow(
                        selectedTabIndex = selectedTab,
                        backgroundColor = Color.Transparent,
                        contentColor = MaterialTheme.colors.primary,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                                color = MaterialTheme.colors.primary,
                            )
                        }
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTab == index,
                                onClick = { selectedTab = index },
                                text = { 
                                    Text(
                                        text = title,
                                        style = MaterialTheme.typography.body1.copy(
                                            fontWeight = if (selectedTab == index) 
                                                FontWeight.Medium 
                                            else 
                                                FontWeight.Normal
                                        ),
                                        color = if (selectedTab == index)
                                            MaterialTheme.colors.primary
                                        else
                                            MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                                    )
                                }
                            )
                        }
                    }
                }
                when (selectedTab) {
                    0 -> {
                        // 基本信息标签页
                        Card(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            elevation = 0.dp,
                            backgroundColor = MaterialTheme.colors.surface
                        ) {
                            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                                OutlinedTextField(
                                    value = title,
                                    onValueChange = { title = it },
                                    label = { Text("标题") },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true
                                )
                                
                                Spacer(modifier = Modifier.height(16.dp))
                                
                                OutlinedTextField(
                                    value = description,
                                    onValueChange = { description = it },
                                    label = { Text("描述") },
                                    modifier = Modifier.fillMaxWidth().weight(1f),
                                    minLines = 3
                                )
                            }
                        }
                    }
                    1 -> {
                        // 状态与优先级标签页
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = 0.dp,
                            backgroundColor = MaterialTheme.colors.surface
                        ) {
                            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                                Text("优先级", style = MaterialTheme.typography.subtitle1)
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    BugPriority.values().forEach { p ->
                                        PriorityChip(
                                            priority = p,
                                            selected = priority == p,
                                            onClick = { priority = p }
                                        )
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(24.dp))
                                
                                Text("状态", style = MaterialTheme.typography.subtitle1)
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    BugStatus.values().forEach { s ->
                                        StatusChip(
                                            status = s,
                                            selected = status == s,
                                            onClick = { status = s }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onDismiss) {
                    Text("取消")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { 
                        if (title.isNotBlank()) {
                            onSave(title, description, priority, status, tags)
                        }
                    },
                    enabled = title.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    )
                ) {
                    Text("保存")
                }
            }
        }
    )
}

@Composable
fun PriorityChip(
    priority: BugPriority,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (selected) {
            when(priority) {
                BugPriority.LOW -> Color(0xFF90A4AE)
                BugPriority.MEDIUM -> Color(0xFFFFA726)
                BugPriority.HIGH -> Color(0xFFEF5350)
                BugPriority.CRITICAL -> Color(0xFFD32F2F)
            }
        } else {
            MaterialTheme.colors.surface
        },
        border = BorderStroke(
            1.dp,
            when(priority) {
                BugPriority.LOW -> Color(0xFF90A4AE)
                BugPriority.MEDIUM -> Color(0xFFFFA726)
                BugPriority.HIGH -> Color(0xFFEF5350)
                BugPriority.CRITICAL -> Color(0xFFD32F2F)
            }
        ),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = priority.toDisplayString(),
            color = if (selected) Color.White else {
                when(priority) {
                    BugPriority.LOW -> Color(0xFF90A4AE)
                    BugPriority.MEDIUM -> Color(0xFFFFA726)
                    BugPriority.HIGH -> Color(0xFFEF5350)
                    BugPriority.CRITICAL -> Color(0xFFD32F2F)
                }
            },
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun StatusChip(
    status: BugStatus,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (selected) {
            when(status) {
                BugStatus.OPEN -> Color(0xFFE57373)
                BugStatus.IN_PROGRESS -> Color(0xFF64B5F6)
                BugStatus.RESOLVED -> Color(0xFF81C784)
                BugStatus.CLOSED -> Color(0xFF90A4AE)
            }
        } else {
            MaterialTheme.colors.surface
        },
        border = BorderStroke(
            1.dp,
            when(status) {
                BugStatus.OPEN -> Color(0xFFE57373)
                BugStatus.IN_PROGRESS -> Color(0xFF64B5F6)
                BugStatus.RESOLVED -> Color(0xFF81C784)
                BugStatus.CLOSED -> Color(0xFF90A4AE)
            }
        ),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = status.toDisplayString(),
            color = if (selected) Color.White else {
                when(status) {
                    BugStatus.OPEN -> Color(0xFFE57373)
                    BugStatus.IN_PROGRESS -> Color(0xFF64B5F6)
                    BugStatus.RESOLVED -> Color(0xFF81C784)
                    BugStatus.CLOSED -> Color(0xFF90A4AE)
                }
            },
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

private fun getSampleBugs(): List<Bug> = listOf(
    Bug(
        id = IdGenerator.generateId(),
        title = "Kotlin Coroutines 存泄漏问题",
        description = """
            在 Android 应用中使用 Kotlin Coroutines 时，如果在 ViewModel 中启动协程但没有正确取消，
            会导致内存泄漏。特别是在使用 viewModelScope 时，需要确保所有协程在 ViewModel 清理时都被取消。
            
            复现步骤：
            1. 在 ViewModel 中使用 GlobalScope 启动协程
            2. 旋转屏幕或返回上一页面
            3. 观察内存使用情况
        """.trimIndent(),
        status = BugStatus.OPEN,
        priority = BugPriority.HIGH,
        createdAt = Clock.System.now().toEpochMilliseconds(),
        updatedAt = Clock.System.now().toEpochMilliseconds(),
        tags = listOf("coroutines", "memory-leak", "android")
    ),
    
    Bug(
        id = IdGenerator.generateId(),
        title = "Kotlin Multiplatform 编译错误",
        description = """
            在 Kotlin Multiplatform 项目中，iOS 目标平台编译失败，错误信息：
            "Execution failed for task ':composeApp:linkDebugFrameworkIosX64'"
            
            环境信息：
            - Kotlin 1.9.0
            - Xcode 14.3
            - macOS 13.4
            
            已尝试清理项目和重新构建，但问题仍然存在。
        """.trimIndent(),
        status = BugStatus.IN_PROGRESS,
        priority = BugPriority.CRITICAL,
        createdAt = Clock.System.now().toEpochMilliseconds(),
        updatedAt = Clock.System.now().toEpochMilliseconds(),
        tags = listOf("kotlin-multiplatform", "ios", "compilation")
    ),
    
    Bug(
        id = IdGenerator.generateId(),
        title = "Flow collect 在 Android 上的生命��期问题",
        description = """
            使用 Flow.collect 收集数据时，如果在 Activity/Fragment 的 onCreate 中直接调用，
            可能会导致在配置更改（如屏幕旋转）时重复收集数据。
            
            建议解决方案：
            1. 使用 lifecycleScope.launch 
            2. 在正确的生命周期阶段收集
            3. 使用 repeatOnLifecycle 或 flowWithLifecycle
        """.trimIndent(),
        status = BugStatus.RESOLVED,
        priority = BugPriority.MEDIUM,
        createdAt = Clock.System.now().toEpochMilliseconds(),
        updatedAt = Clock.System.now().toEpochMilliseconds(),
        tags = listOf("flow", "android", "lifecycle")
    ),
    
    Bug(
        id = IdGenerator.generateId(),
        title = "Compose 重组优化问题",
        description = """
            在使用 Jetpack Compose 时，由于重组规则理解不当导致不必要的重组，影响性能。
            
            问题代码：
            @Composable
            fun MyComposable(items: List<Item>) {
                items.forEach { item ->
                    ItemRow(item)  // 每次重组都会重新创建所有项
                }
            }
            
            应该使用 LazyColumn 和 items API 来优化性能。
        """.trimIndent(),
        status = BugStatus.OPEN,
        priority = BugPriority.MEDIUM,
        createdAt = Clock.System.now().toEpochMilliseconds(),
        updatedAt = Clock.System.now().toEpochMilliseconds(),
        tags = listOf("compose", "performance", "recomposition")
    ),
    
    Bug(
        id = IdGenerator.generateId(),
        title = "Kotlin 序列化问题",
        description = """
            使用 kotlinx.serialization 时，对于包含默认参数的数据类，序列化后再反序列化可能丢失默认值。
            
            示例代码：
            @Serializable
            data class User(
                val name: String,
                val age: Int = 0  // 默认值不能在某些情况下丢失
            )
            
            需要确保正确使用 @Required 或 @Optional 注解。
        """.trimIndent(),
        status = BugStatus.CLOSED,
        priority = BugPriority.LOW,
        createdAt = Clock.System.now().toEpochMilliseconds(),
        updatedAt = Clock.System.now().toEpochMilliseconds(),
        tags = listOf("serialization", "data-class")
    )
) 