package cn.kotlinmultiplatform.jeady.icons.tech

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.kotlin
import kotlinmultiplatform.composeapp.generated.resources.compose
import kotlinmultiplatform.composeapp.generated.resources.material

object TechIcons {
    @Composable
    fun kotlin(): Painter = painterResource(Res.drawable.kotlin)
    
    @Composable
    fun compose(): Painter = painterResource(Res.drawable.compose)
    
    @Composable
    fun material(): Painter = painterResource(Res.drawable.material)
} 