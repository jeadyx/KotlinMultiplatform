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
import cn.kotlinmultiplatform.jeady.pages.HelpPage
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
    object Help : Screen("help")
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
        val urlHandler = getPlatformUrlHandler()

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
                    },
                    onNavigateToHelp = {
                        currentScreen = Screen.Help
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
            Screen.Help -> {
                HelpPage(
                    urlHandler = urlHandler
                )
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
    onBugAdd: () -> Unit,
    onNavigateToHelp: () -> Unit
) {
    var currentPage by remember { mutableStateOf(0) }
    val urlHandler = getPlatformUrlHandler()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        Surface(
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.app_logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Kotlin Multiplatform",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isLoggedIn) {
                        IconButton(onClick = onLogout) {
                            Icon(Icons.Default.ExitToApp, contentDescription = "退出登录")
                        }
                    } else {
                        Button(
                            onClick = onLoginClick,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary
                            )
                        ) {
                            Text("登录")
                        }
                    }
                }
            }
        }

        // Navigation Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(
                onClick = { currentPage = 0 },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentPage == 0) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            ) {
                Text("工具箱")
            }
            TextButton(
                onClick = { currentPage = 1 },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentPage == 1) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            ) {
                Text("博客")
            }
            TextButton(
                onClick = { currentPage = 2 },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentPage == 2) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            ) {
                Text("开源")
            }
            TextButton(
                onClick = { currentPage = 3 },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentPage == 3) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            ) {
                Text("推荐")
            }
            TextButton(
                onClick = { currentPage = 4 },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentPage == 4) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            ) {
                Text("问题")
            }
            TextButton(
                onClick = { currentPage = 5 },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentPage == 5) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            ) {
                Text("发布")
            }
            TextButton(
                onClick = { currentPage = 6 },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (currentPage == 6) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            ) {
                Text("关于")
            }
        }

        // Content
        Box(modifier = Modifier.weight(1f)) {
            when (currentPage) {
                0 -> ToolboxPage(urlHandler = urlHandler)
                1 -> BlogPage(
                    onPostClick = { postId -> onNavigateToDetail(postId, "blog") }
                )
                2 -> OpenSourcePage(
                    urlHandler
                )
                3 -> RecommendationsPage(
                    urlHandler = urlHandler,
                    onNavigateToDetail = onNavigateToDetail)
                4 -> BugsPage(
                    bugs = bugs,
                    onEdit = onBugEdit,
                    onDelete = onBugDelete,
                    onAdd = onBugAdd,
                    onNavigateToBugDetail = onNavigateToBugDetail
                )
                5 -> PublishingPage(
                    urlHandler = urlHandler,
                    onNavigateToHelp = onNavigateToHelp
                )
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