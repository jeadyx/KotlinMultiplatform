package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun BlogPage() {
    LazyColumn {
        items(10) { index ->
            Text("博客文章 $index")
        }
    }
} 