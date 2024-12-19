package cn.kotlinmultiplatform.jeady

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.Typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.components.AppDialog
import cn.kotlinmultiplatform.jeady.model.Bug
import cn.kotlinmultiplatform.jeady.pages.AboutPage
import cn.kotlinmultiplatform.jeady.pages.BlogPage
import cn.kotlinmultiplatform.jeady.pages.BugDetailPage
import cn.kotlinmultiplatform.jeady.pages.BugDialog
import cn.kotlinmultiplatform.jeady.pages.BugsPage
import cn.kotlinmultiplatform.jeady.pages.DetailPage
import cn.kotlinmultiplatform.jeady.pages.LoginPage
import cn.kotlinmultiplatform.jeady.pages.OpenSourcePage
import cn.kotlinmultiplatform.jeady.pages.PublishingPage
import cn.kotlinmultiplatform.jeady.pages.RecommendationsPage
import cn.kotlinmultiplatform.jeady.pages.RegisterPage
import cn.kotlinmultiplatform.jeady.pages.ToolboxPage
import cn.kotlinmultiplatform.jeady.platform.getPlatformUrlHandler
import cn.kotlinmultiplatform.jeady.service.BugService
import cn.kotlinmultiplatform.jeady.utils.IdGenerator
import kotlinmultiplatform.composeapp.generated.resources.NotoSansSC_Bold
import kotlinmultiplatform.composeapp.generated.resources.NotoSansSC_Regular
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.app_logo
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{itemId}/{source}") {
        fun createRoute(itemId: String, source: String) = "detail/$itemId/$source"
    }
    object BugDetail : Screen("bug-detail/{bugId}") {
        fun createRoute(bugId: String) = "bug-detail/$bugId"
    }
}

@Composable
fun App() {
    val notoSansSCFamily = FontFamily(
        Font(Res.font.NotoSansSC_Regular, FontWeight.Normal),
        Font(Res.font.NotoSansSC_Bold, FontWeight.Bold)
    )
    
    MaterialTheme(
        typography = Typography(
            defaultFontFamily = notoSansSCFamily
        )
    ) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
        var selectedItemId by remember { mutableStateOf<String?>(null) }
        var isLoggedIn by remember { mutableStateOf(false) }
        var selectedTab by remember { mutableStateOf(0) }
        var showLoginDialog by remember { mutableStateOf(false) }
        var showRegisterDialog by remember { mutableStateOf(false) }
        var showBugEditDialog by remember { mutableStateOf(false) }
        var selectedBug by remember { mutableStateOf<Bug?>(null) }
        
        val bugService = remember { BugService.getInstance() }
        var bugs by remember { mutableStateOf(bugService.getAllBugs()) }

        // 主界面
        when (currentScreen) {
            Screen.Home -> {
                Navigation(
                    selectedTab = selectedTab,
                    onSelectedTabChange = { newTab -> 
                        selectedTab = newTab
                    },
                    onNavigateToDetail = { itemId, source ->
                        selectedItemId = itemId
                        currentScreen = Screen.Detail
                    },
                    isLoggedIn = isLoggedIn,
                    onLoginClick = {
                        showLoginDialog = true
                    },
                    onLogout = {
                        isLoggedIn = false
                    },
                    onNavigateToBugDetail = { bug ->
                        selectedBug = bug
                        currentScreen = Screen.BugDetail
                    },
                    bugs = bugs,
                    onBugEdit = { bug ->
                        selectedBug = bug
                        showBugEditDialog = true
                    },
                    onBugDelete = { bugId ->
                        bugService.deleteBug(bugId)
                        bugs = bugService.getAllBugs()
                    },
                    onBugAdd = {
                        selectedBug = null
                        showBugEditDialog = true
                    }
                )
            }
            Screen.Detail -> {
                selectedItemId?.let { itemId ->
                    DetailPage(
                        itemId = itemId,
                        source = if (selectedTab == 1) "blog" else "recommendation",
                        onBackClick = {
                            currentScreen = Screen.Home
                        }
                    )
                }
            }
            Screen.BugDetail -> {
                selectedBug?.let { bug ->
                    BugDetailPage(
                        bug = bug,
                        onNavigateBack = {
                            currentScreen = Screen.Home
                        },
                        onEdit = { editBug ->
                            showBugEditDialog = true
                        }
                    )
                }
            }
        }

        // 登录对话框
        if (showLoginDialog) {
            AppDialog(
                onDismissRequest = { showLoginDialog = false }
            ) {
                LoginPage(
                    onLoginSuccess = {
                        isLoggedIn = true
                        showLoginDialog = false
                    },
                    onRegisterClick = {
                        showLoginDialog = false
                        showRegisterDialog = true
                    }
                )
            }
        }

        // 注册对话框
        if (showRegisterDialog) {
            AppDialog(
                onDismissRequest = { showRegisterDialog = false },
                onBackClick = {
                    showRegisterDialog = false
                    showLoginDialog = true
                }
            ) {
                RegisterPage(
                    onRegisterSuccess = {
                        showRegisterDialog = false
                        showLoginDialog = true
                    },
                    onBackToLogin = {
                        showRegisterDialog = false
                        showLoginDialog = true
                    }
                )
            }
        }

        // 添加/编辑 Bug 对话框
        if (showBugEditDialog) {
            BugDialog(
                bug = selectedBug,
                onDismiss = {
                    showBugEditDialog = false
                },
                onSave = { title, description, priority, status, tags ->
                    if (selectedBug != null) {
                        val updatedBug = selectedBug!!.copy(
                            title = title,
                            description = description,
                            priority = priority,
                            status = status,
                            tags = tags,
                            updatedAt = Clock.System.now().toEpochMilliseconds()
                        )
                        bugService.updateBug(updatedBug)
                        // 更新 selectedBug，这样详情页面也会更新
                        selectedBug = updatedBug
                    } else {
                        val newBug = Bug(
                            id = IdGenerator.generateId(),
                            title = title,
                            description = description,
                            priority = priority,
                            status = status,
                            tags = tags,
                            createdAt = Clock.System.now().toEpochMilliseconds(),
                            updatedAt = Clock.System.now().toEpochMilliseconds()
                        )
                        bugService.addBug(newBug)
                    }
                    bugs = bugService.getAllBugs()
                    showBugEditDialog = false
                }
            )
        }
    }
}

@Composable
fun Navigation(
    selectedTab: Int,
    onSelectedTabChange: (Int) -> Unit,
    onNavigateToDetail: (String, String) -> Unit,
    isLoggedIn: Boolean,
    onLoginClick: () -> Unit,
    onLogout: () -> Unit,
    onNavigateToBugDetail: (Bug) -> Unit,
    bugs: List<Bug>,
    onBugEdit: (Bug) -> Unit,
    onBugDelete: (String) -> Unit,
    onBugAdd: () -> Unit
) {
    val tabs = listOf("推荐", "博客", "问答", "工具箱", "开源", "发布", "关于")

    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部栏
        Surface(
            elevation = 4.dp,
            color = MaterialTheme.colors.surface
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo
                Image(
                    painter = painterResource(Res.drawable.app_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(24.dp))

                // 导航标签
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    tabs.forEachIndexed { index, title ->
                        TabItem(
                            title = title,
                            selected = selectedTab == index,
                            onClick = { onSelectedTabChange(index) }
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // 登录/登出按钮
                if (isLoggedIn) {
                    IconButton(
                        onClick = onLogout,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "登出",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                } else {
                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier.height(32.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("登录")
                    }
                }
            }
        }

        // 内容区域
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> RecommendationsPage(urlHandler = getPlatformUrlHandler()) { itemId, source ->
                    onNavigateToDetail(itemId, source)
                }
                1 -> BlogPage(
                    onPostClick = { postId ->
                        onNavigateToDetail(postId, "blog")
                    }
                )
                2 -> BugsPage(
                    bugs = bugs,
                    onEdit = onBugEdit,
                    onDelete = onBugDelete,
                    onAdd = onBugAdd,
                    onNavigateToBugDetail = onNavigateToBugDetail
                )
                3 -> ToolboxPage(urlHandler = getPlatformUrlHandler())
                4 -> OpenSourcePage(urlHandler = getPlatformUrlHandler())
                5 -> PublishingPage(urlHandler = getPlatformUrlHandler())
                6 -> AboutPage()
            }
        }
    }
}

@Composable
private fun TabItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.height(32.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (selected) 
                MaterialTheme.colors.primary.copy(alpha = 0.1f)
            else 
                MaterialTheme.colors.surface,
            contentColor = if (selected) 
                MaterialTheme.colors.primary
            else 
                MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.button
        )
    }
}