package cn.kotlinmultiplatform.jeady.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun PlatformImage(
    resource: DrawableResource,
    contentDescription: String,
    modifier: Modifier,
    contentScale: ContentScale
) {
    Image(
        painter = painterResource(resource),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
} 