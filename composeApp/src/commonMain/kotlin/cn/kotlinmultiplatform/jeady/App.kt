package cn.kotlinmultiplatform.jeady

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.pages.AboutPage
import cn.kotlinmultiplatform.jeady.pages.BlogPage
import cn.kotlinmultiplatform.jeady.pages.BugsPage
import cn.kotlinmultiplatform.jeady.pages.DetailPage
import cn.kotlinmultiplatform.jeady.pages.OpenSourcePage
import cn.kotlinmultiplatform.jeady.pages.RecommendationsPage
import kotlinmultiplatform.composeapp.generated.resources.NotoSansSC_Bold
import kotlinmultiplatform.composeapp.generated.resources.NotoSansSC_Regular
import kotlinmultiplatform.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

sealed class Screen {
    object Home : Screen()
    data class Detail(val itemId: String) : Screen()
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var selectedTab by remember { mutableStateOf(1) }
    
    val notoSansSCFamily = FontFamily(
        Font(Res.font.NotoSansSC_Regular, FontWeight.Normal),
        Font(Res.font.NotoSansSC_Bold, FontWeight.Bold)
    )
    
    MaterialTheme(
        typography = Typography(
            defaultFontFamily = notoSansSCFamily
        )
    ) {
        when (val screen = currentScreen) {
            is Screen.Home -> Navigation(
                selectedTab = selectedTab,
                onSelectedTabChange = { newTab -> 
                    selectedTab = newTab
                },
                onNavigateToDetail = { itemId ->
                    currentScreen = Screen.Detail(itemId)
                }
            )
            is Screen.Detail -> DetailPage(
                itemId = screen.itemId,
                onBackClick = {
                    currentScreen = Screen.Home
                }
            )
        }
    }
}

@Composable
fun Navigation(
    selectedTab: Int,
    onSelectedTabChange: (Int) -> Unit,
    onNavigateToDetail: (String) -> Unit
) {
    val tabs = listOf("推荐", "博客", "Bugs", "开源", "关于")

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title, modifier = Modifier.padding(vertical = 16.dp)) },
                    selected = selectedTab == index,
                    onClick = { onSelectedTabChange(index) }
                )
            }
        }
        when (selectedTab) {
            0 -> RecommendationsPage(onNavigateToDetail)
            1 -> BlogPage(
                onPostClick = { postId ->
                    onNavigateToDetail(postId)
                }
            )
            2 -> BugsPage()
            3 -> OpenSourcePage()
            4 -> AboutPage()
        }
    }
}