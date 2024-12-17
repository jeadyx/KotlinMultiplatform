package cn.kotlinmultiplatform.jeady

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import cn.kotlinmultiplatform.jeady.pages.*

import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("科技网站") })
        },
        content = {
            Navigation()
        }
    )
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