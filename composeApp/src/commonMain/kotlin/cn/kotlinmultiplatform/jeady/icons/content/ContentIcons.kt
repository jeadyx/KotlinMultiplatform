package cn.kotlinmultiplatform.jeady.icons.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.article
import kotlinmultiplatform.composeapp.generated.resources.book

object ContentIcons {
    @Composable
    fun article(): Painter = painterResource(Res.drawable.article)
    
    @Composable
    fun book(): Painter = painterResource(Res.drawable.book)
} 