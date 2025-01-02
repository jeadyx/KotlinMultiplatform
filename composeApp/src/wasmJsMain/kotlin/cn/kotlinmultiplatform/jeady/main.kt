package cn.kotlinmultiplatform.jeady

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val root = document.getElementById("root")
        ?: throw IllegalStateException("Root element not found")
    ComposeViewport(root) {
        App()
    }
}