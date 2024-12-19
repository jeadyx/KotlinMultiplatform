package cn.kotlinmultiplatform.jeady.icons.social

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.wechat
import kotlinmultiplatform.composeapp.generated.resources.qq
import kotlinmultiplatform.composeapp.generated.resources.bilibili
import kotlinmultiplatform.composeapp.generated.resources.juejin

object SocialIcons {
    @Composable
    fun wechat(): Painter = painterResource(Res.drawable.wechat)
    
    @Composable
    fun qq(): Painter = painterResource(Res.drawable.qq)
    
    @Composable
    fun bilibili(): Painter = painterResource(Res.drawable.bilibili)
    
    @Composable
    fun juejin(): Painter = painterResource(Res.drawable.juejin)
} 