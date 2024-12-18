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
import cn.kotlinmultiplatform.jeady.pages.AboutPage
import cn.kotlinmultiplatform.jeady.pages.BlogPage
import cn.kotlinmultiplatform.jeady.pages.BugsPage
import cn.kotlinmultiplatform.jeady.pages.DetailPage
import cn.kotlinmultiplatform.jeady.pages.LoginPage
import cn.kotlinmultiplatform.jeady.pages.OpenSourcePage
import cn.kotlinmultiplatform.jeady.pages.RecommendationsPage
import cn.kotlinmultiplatform.jeady.pages.RegisterPage
import cn.kotlinmultiplatform.jeady.platform.getPlatformUrlHandler
import kotlinmultiplatform.composeapp.generated.resources.NotoSansSC_Bold
import kotlinmultiplatform.composeapp.generated.resources.NotoSansSC_Regular
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.app_logo
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: String) = "detail/$itemId"
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

        // 主界面
        when (currentScreen) {
            Screen.Home -> {
                Navigation(
                    selectedTab = selectedTab,
                    onSelectedTabChange = { newTab -> 
                        selectedTab = newTab
                    },
                    onNavigateToDetail = { itemId ->
                        selectedItemId = itemId
                        currentScreen = Screen.Detail
                    },
                    isLoggedIn = isLoggedIn,
                    onLoginClick = {
                        showLoginDialog = true
                    },
                    onLogout = {
                        isLoggedIn = false
                    }
                )
            }
            Screen.Detail -> {
                selectedItemId?.let { itemId ->
                    DetailPage(
                        itemId = itemId,
                        onBackClick = {
                            currentScreen = Screen.Home
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
    }
}

@Composable
fun Navigation(
    selectedTab: Int,
    onSelectedTabChange: (Int) -> Unit,
    onNavigateToDetail: (String) -> Unit,
    isLoggedIn: Boolean,
    onLoginClick: () -> Unit,
    onLogout: () -> Unit
) {
    val tabs = listOf("推荐", "博客", "Bugs", "开源", "关于")

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
                0 -> RecommendationsPage(onNavigateToDetail)
                1 -> BlogPage(
                    onPostClick = { postId ->
                        onNavigateToDetail(postId)
                    }
                )
                2 -> BugsPage()
                3 -> OpenSourcePage(urlHandler = getPlatformUrlHandler())
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