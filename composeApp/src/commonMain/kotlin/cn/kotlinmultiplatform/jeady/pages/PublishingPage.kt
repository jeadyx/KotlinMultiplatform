package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.kotlinmultiplatform.jeady.icons.Code
import cn.kotlinmultiplatform.jeady.icons.Web
import cn.kotlinmultiplatform.jeady.icons.action.OpenInNew
import cn.kotlinmultiplatform.jeady.icons.device.Android
import cn.kotlinmultiplatform.jeady.icons.device.Apple
import cn.kotlinmultiplatform.jeady.icons.device.DesktopWindows
import cn.kotlinmultiplatform.jeady.platform.UrlHandler

data class PublishStep(
    val title: String,
    val description: String,
    val command: String? = null,
    val documentationUrl: String? = null
)

data class PublishSection(
    val title: String,
    val icon: ImageVector,
    val description: String,
    val steps: List<PublishStep>
)

@Composable
fun PublishingPage(urlHandler: UrlHandler) {
    val sections = listOf(
        PublishSection(
            title = "网站发布",
            icon = Icons.Default.Web,
            description = "将您的 KMP 网站发布到各种平台",
            steps = listOf(
                PublishStep(
                    title = "构建网站",
                    description = "使用 Gradle 构建网站项目",
                    command = "./gradlew :composeApp:jsBrowserProductionWebpack"
                ),
                PublishStep(
                    title = "GitHub Pages",
                    description = "将网站部署到 GitHub Pages",
                    documentationUrl = "https://pages.github.com/"
                ),
                PublishStep(
                    title = "Vercel",
                    description = "使用 Vercel 托管您的网站",
                    documentationUrl = "https://vercel.com/docs"
                ),
                PublishStep(
                    title = "Netlify",
                    description = "在 Netlify 上部署网站",
                    documentationUrl = "https://docs.netlify.com/"
                )
            )
        ),
        PublishSection(
            title = "Android 应用",
            icon = Icons.Default.Android,
            description = "发布您的 Android 应用到应用商店",
            steps = listOf(
                PublishStep(
                    title = "构建 APK",
                    description = "生成发布版本的 APK",
                    command = "./gradlew :composeApp:assembleRelease"
                ),
                PublishStep(
                    title = "签名配置",
                    description = "配置应用签名",
                    documentationUrl = "https://developer.android.com/studio/publish/app-signing"
                ),
                PublishStep(
                    title = "Google Play",
                    description = "发布到 Google Play 商店",
                    documentationUrl = "https://play.google.com/console/"
                )
            )
        ),
        PublishSection(
            title = "iOS 应用",
            icon = Icons.Default.Apple,
            description = "发布您的 iOS 应用到 App Store",
            steps = listOf(
                PublishStep(
                    title = "构建 IPA",
                    description = "生成 iOS 应用包",
                    command = "./gradlew :composeApp:iosDeployRelease"
                ),
                PublishStep(
                    title = "证书配置",
                    description = "配置开发者证书和描述文件",
                    documentationUrl = "https://developer.apple.com/documentation/xcode/distributing-your-app-for-beta-testing-and-releases"
                ),
                PublishStep(
                    title = "App Store",
                    description = "发布到 App Store",
                    documentationUrl = "https://developer.apple.com/app-store/submissions/"
                )
            )
        ),
        PublishSection(
            title = "桌面应用",
            icon = Icons.Default.DesktopWindows,
            description = "发布您的桌面应用",
            steps = listOf(
                PublishStep(
                    title = "构建桌面应用",
                    description = "生成桌面应用安装包",
                    command = "./gradlew :composeApp:packageReleaseDistributionForCurrentOS"
                ),
                PublishStep(
                    title = "Microsoft Store",
                    description = "发布到 Microsoft Store",
                    documentationUrl = "https://docs.microsoft.com/windows/apps/publish/"
                ),
                PublishStep(
                    title = "Mac App Store",
                    description = "发布到 Mac App Store",
                    documentationUrl = "https://developer.apple.com/macos/submit/"
                )
            )
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sections.size) { index ->
            PublishSectionCard(sections[index], urlHandler)
        }
    }
}

@Composable
private fun PublishSectionCard(section: PublishSection, urlHandler: UrlHandler) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary.copy(alpha = 0.1f)),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = section.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = section.description,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            // Steps
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                section.steps.forEach { step ->
                    PublishStepItem(step, urlHandler)
                }
            }
        }
    }
}

@Composable
private fun PublishStepItem(step: PublishStep, urlHandler: UrlHandler) {
    Column {
        Text(
            text = step.title,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = step.description,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            step.command?.let { command ->
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colors.surface,
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary.copy(alpha = 0.1f)),
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = command,
                            style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.sp
                        )
                    }
                }
            }
            
            step.documentationUrl?.let { url ->
                TextButton(
                    onClick = { urlHandler.openUrl(url) },
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("查看文档")
                }
            }
        }
    }
} 