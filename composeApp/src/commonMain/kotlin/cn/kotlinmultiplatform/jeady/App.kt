package cn.kotlinmultiplatform.jeady

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import cn.kotlinmultiplatform.jeady.pages.AboutPage
import cn.kotlinmultiplatform.jeady.pages.BlogPage
import cn.kotlinmultiplatform.jeady.pages.BugsPage
import cn.kotlinmultiplatform.jeady.pages.RecommendationsPage
import kotlinmultiplatform.composeapp.generated.resources.NotoSansSC_Bold
import kotlinmultiplatform.composeapp.generated.resources.NotoSansSC_Regular
import kotlinmultiplatform.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

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
        Navigation()
    }
}

@Composable
fun Navigation() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("推荐", "博客", "Bugs", "关于")

    Column {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTab == index,
                    onClick = { selectedTab = index }
                )
            }
        }
        when (selectedTab) {
            0 -> RecommendationsPage()
            1 -> BlogPage()
            2 -> BugsPage()
            3 -> AboutPage()
        }
    }
}