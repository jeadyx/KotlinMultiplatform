package cn.kotlinmultiplatform.jeady

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.components.AppDialog
import cn.kotlinmultiplatform.jeady.icons.content.ContentIcons
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
import cn.kotlinmultiplatform.jeady.pages.ProductsPage
import cn.kotlinmultiplatform.jeady.pages.PublishingPage
import cn.kotlinmultiplatform.jeady.pages.RecommendationsPage
import cn.kotlinmultiplatform.jeady.pages.ReferencePage
import cn.kotlinmultiplatform.jeady.pages.ReferenceSection
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
    object Articles : Screen("articles")
    object Detail : Screen("detail/{itemId}/{source}") {
        fun createRoute(itemId: String, source: String) = "detail/$itemId/$source"
    }
    object BugDetail : Screen("bug-detail/{bugId}") {
        fun createRoute(bugId: String) = "bug-detail/$bugId"
    }
    object Help : Screen("help")
    object Reference : Screen("reference")
    object OpenSource : Screen("reference/opensource")
    object Toolbox : Screen("reference/toolbox")
    object Products : Screen("reference/products")
    object About : Screen("about")
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
        var currentPage by remember { mutableStateOf(0) }
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
        var selectedItemId by remember { mutableStateOf<String?>(null) }
        var isLoggedIn by remember { mutableStateOf(false) }
        var selectedTab by remember { mutableStateOf(0) }
        var showLoginDialog by remember { mutableStateOf(false) }
        var showRegisterDialog by remember { mutableStateOf(false) }
        var showBugEditDialog by remember { mutableStateOf(false) }
        var selectedBug by remember { mutableStateOf<Bug?>(null) }
        var showPublishingPage by remember { mutableStateOf(false) }
        
        val bugService = remember { BugService.getInstance() }
        var bugs by remember { mutableStateOf(bugService.getAllBugs()) }
        val urlHandler = getPlatformUrlHandler()

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
                    },
                    showPublishingPage = showPublishingPage,
                    onShowPublishingPageChange = { newShowPublishingPage ->
                        showPublishingPage = newShowPublishingPage
                    }
                )
            }
            Screen.Articles -> {
                BlogPage(
                    onPostClick = { postId -> 
                        selectedItemId = postId
                        currentScreen = Screen.Detail
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
                    urlHandler = urlHandler,
                    onBackClick = {
                        currentScreen = Screen.Home
                    }
                )
            }
            Screen.Reference -> {
                ReferencePage(
                    urlHandler = urlHandler,
                    onSectionClick = { section ->
                        when (section) {
                            ReferenceSection.OPEN_SOURCE -> currentScreen = Screen.OpenSource
                            ReferenceSection.TOOLBOX -> currentScreen = Screen.Toolbox
                            ReferenceSection.PRODUCTS -> currentScreen = Screen.Products
                        }
                    }
                )
            }
            Screen.OpenSource -> {
                OpenSourcePage(
                    urlHandler = urlHandler,
                    onBackClick = {
                        currentScreen = Screen.Reference
                    }
                )
            }
            Screen.Toolbox -> {
                ToolboxPage(
                    urlHandler = urlHandler,
                    onBackClick = {
                        currentScreen = Screen.Reference
                    }
                )
            }
            Screen.Products -> {
                ProductsPage(
                    urlHandler = urlHandler,
                    onShowPublishingPageChange = { showPublishingPage = true },
                    onBackClick = {
                        currentScreen = Screen.Reference
                    }
                )
            }
            Screen.About -> {
                AboutPage()
            }
        }

        // Login dialog
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

        // Register dialog
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

        // Bug edit dialog
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
    onNavigateToHelp: () -> Unit,
    showPublishingPage: Boolean,
    onShowPublishingPageChange: (Boolean) -> Unit
) {
    var currentPage by remember { mutableStateOf(0) }
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    val urlHandler = getPlatformUrlHandler()

    if (showPublishingPage) {
        PublishingPage(
            urlHandler = urlHandler,
            onNavigateToHelp = onNavigateToHelp,
            onClose = { onShowPublishingPageChange(false) }
        )
        return
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Combined Header and Navigation with max width constraint
        Surface(
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .widthIn(max = 1200.dp), // 限制最大宽度
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Logo and (conditional) title
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(0.2f, fill = false)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.app_logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        
                        BoxWithConstraints {
                            if (maxWidth > 600.dp) {
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Kotlin Multiplatform",
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    // Navigation tabs in the middle
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(0.6f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TabItem("推荐", currentPage == 0) { currentPage = 0 }
                        TabItem("博客", currentPage == 1) { currentPage = 1 }
                        TabItem("问题", currentPage == 2) { currentPage = 2 }
                        TabItem("参考", currentPage == 3) { currentPage = 3 }
                        TabItem("关于", currentPage == 4) { currentPage = 4 }
                    }

                    // Login/Logout button
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(0.2f, fill = false)
                    ) {
                        if (isLoggedIn) {
                            IconButton(
                                onClick = onLogout,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    Icons.Default.ExitToApp,
                                    contentDescription = "退出登录",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        } else {
                            Button(
                                onClick = onLoginClick,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.primary
                                ),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Text("登录", style = MaterialTheme.typography.button)
                            }
                        }
                    }
                }
            }
        }

        // Content
        Box(
            modifier = Modifier.weight(1f)
        ) {
            when (currentPage) {
                0 -> RecommendationsPage(
                    urlHandler = urlHandler,
                    onNavigateToDetail = onNavigateToDetail)
                1 -> BlogPage(
                    onPostClick = { postId -> onNavigateToDetail(postId, "blog") }
                )
                2 -> BugsPage(
                    bugs = bugs,
                    onEdit = onBugEdit,
                    onDelete = onBugDelete,
                    onAdd = onBugAdd,
                    onNavigateToBugDetail = onNavigateToBugDetail
                )
                3 -> when (currentScreen) {
                    Screen.Reference -> ReferencePage(
                        urlHandler = urlHandler,
                        onSectionClick = { section ->
                            when (section) {
                                ReferenceSection.OPEN_SOURCE -> currentScreen = Screen.OpenSource
                                ReferenceSection.TOOLBOX -> currentScreen = Screen.Toolbox
                                ReferenceSection.PRODUCTS -> currentScreen = Screen.Products
                            }
                        }
                    )
                    Screen.OpenSource -> OpenSourcePage(
                        urlHandler = urlHandler,
                        onBackClick = { currentScreen = Screen.Reference }
                    )
                    Screen.Toolbox -> ToolboxPage(
                        urlHandler = urlHandler,
                        onBackClick = { currentScreen = Screen.Reference }
                    )
                    Screen.Products -> ProductsPage(
                        urlHandler = urlHandler,
                        onShowPublishingPageChange = { onShowPublishingPageChange(true) },
                        onBackClick = { currentScreen = Screen.Reference }
                    )
                    else -> ReferencePage(
                        urlHandler = urlHandler,
                        onSectionClick = { section ->
                            when (section) {
                                ReferenceSection.OPEN_SOURCE -> currentScreen = Screen.OpenSource
                                ReferenceSection.TOOLBOX -> currentScreen = Screen.Toolbox
                                ReferenceSection.PRODUCTS -> currentScreen = Screen.Products
                            }
                        }
                    )
                }
                4 -> AboutPage()
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
        modifier = Modifier
            .height(32.dp)
            .padding(horizontal = 2.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (selected) 
                MaterialTheme.colors.primary 
            else 
                MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
            backgroundColor = if (selected)
                MaterialTheme.colors.primary.copy(alpha = 0.1f)
            else
                MaterialTheme.colors.surface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.button,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@Composable
private fun NavigationBar(
    currentRoute: String,
    onRouteSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavigationItem(
            icon = Icons.Filled.Home,
            label = "首页",
            selected = currentRoute == Screen.Home.route,
            onClick = { onRouteSelected(Screen.Home.route) }
        )
        NavigationItem(
            painter = ContentIcons.article(),
            label = "文章",
            selected = currentRoute == Screen.Articles.route,
            onClick = { onRouteSelected(Screen.Articles.route) }
        )
        NavigationItem(
            painter = ContentIcons.book(),
            label = "参考",
            selected = currentRoute == Screen.Reference.route,
            onClick = { onRouteSelected(Screen.Reference.route) }
        )
        NavigationItem(
            icon = Icons.Filled.Info,
            label = "关于",
            selected = currentRoute == Screen.About.route,
            onClick = { onRouteSelected(Screen.About.route) }
        )
    }
}

@Composable
private fun NavigationItem(
    icon: ImageVector? = null,
    painter: Painter? = null,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier.height(44.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when {
                    icon != null -> Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (selected) {
                            MaterialTheme.colors.primary
                        } else {
                            MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        },
                        modifier = Modifier.size(24.dp)
                    )
                    painter != null -> Icon(
                        painter = painter,
                        contentDescription = label,
                        tint = if (selected) {
                            MaterialTheme.colors.primary
                        } else {
                            MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        },
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = label,
                    style = MaterialTheme.typography.caption,
                    color = if (selected) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    }
                )
            }
        }
        
        // 选中指示器
        if (selected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(2.dp)
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp)
                    )
            )
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}