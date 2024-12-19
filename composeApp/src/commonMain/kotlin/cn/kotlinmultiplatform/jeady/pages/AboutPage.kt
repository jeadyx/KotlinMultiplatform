package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.components.PlatformImage
import cn.kotlinmultiplatform.jeady.icons.CustomCode
import cn.kotlinmultiplatform.jeady.icons.social.SocialIcons
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.composemultiplatform

data class TeamMember(
    val name: String,
    val role: String,
    val description: String,
    val email: String,
    val github: String
)

data class Technology(
    val name: String,
    val description: String,
    val color: Color
)

data class AppVersion(
    val version: String,
    val buildNumber: String,
    val lastUpdated: String,
    val status: String
)

@Composable
fun AboutPage() {
    val teamMembers = remember {
        listOf(
            TeamMember(
                name = "张三",
                role = "全栈开发工程师",
                description = "负责项目架构设计和核心功能开发，对 Kotlin Multiplatform 有深入研究。",
                email = "zhangsan@example.com",
                github = "zhangsan"
            ),
            TeamMember(
                name = "李四",
                role = "UI/UX 设计师",
                description = "负责项目的用户界面设计和交互体验优化，擅长现代化 UI 设计。",
                email = "lisi@example.com",
                github = "lisi"
            ),
            TeamMember(
                name = "王五",
                role = "移动端开发工程师",
                description = "专注于移动端开发和性能优化，有丰富的跨平台开发经验。",
                email = "wangwu@example.com",
                github = "wangwu"
            )
        )
    }

    val technologies = remember {
        listOf(
            Technology(
                name = "Kotlin Multiplatform",
                description = "使用 Kotlin 开发跨平台应用的代解决方案",
                color = Color(0xFF7F52FF)
            ),
            Technology(
                name = "Jetpack Compose",
                description = "现代化的声明式 UI 开发框架",
                color = Color(0xFF4285F4)
            ),
            Technology(
                name = "Material Design",
                description = "Google 的设计系统，提供现代化的用户界面组件",
                color = Color(0xFF00BCD4)
            )
        )
    }

    val appVersion = remember {
        AppVersion(
            version = "1.0.0",
            buildNumber = "235",
            lastUpdated = "2024-03-21",
            status = "Beta"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // 顶部 Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            // 渐变背景
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primary.copy(alpha = 0.8f)
                            )
                        )
                    )
            )

            // 装饰性波浪
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path()
                val width = size.width
                val height = size.height
                
                path.moveTo(0f, height * 0.7f)
                path.cubicTo(
                    width * 0.3f, height * 0.6f,
                    width * 0.7f, height * 0.8f,
                    width, height * 0.7f
                )
                path.lineTo(width, height)
                path.lineTo(0f, height)
                path.close()
                
                drawPath(
                    path = path,
                    color = Color.White.copy(alpha = 0.1f)
                )
            }

            // Header 内容
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    PlatformImage(
                        resource = Res.drawable.composemultiplatform,
                        contentDescription = "项目Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Kotlin Multiplatform 博客",
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "现代化的跨平台开发解决方案",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 8.dp)
                )

                // 版本信息
                VersionBadgeCompact(appVersion)
            }
        }

        // 主要内容区域
        Column(
            modifier = Modifier
                .offset(y = (-30).dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colors.surface)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 关于本站
            AboutSection()
            
            // 社交媒体
            SocialSection()
            
            // 技术栈
            TechnologySection(technologies)
            
            // 团队成员
            TeamSection(teamMembers)
        }
    }
}

@Composable
private fun VersionBadgeCompact(version: AppVersion) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = Color.White.copy(alpha = 0.15f),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            
            Text(
                text = "v${version.version}",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )
            
            Text(
                text = "•",
                color = Color.White.copy(alpha = 0.5f)
            )
            
            Text(
                text = version.status,
                color = when(version.status) {
                    "Beta" -> Color(0xFFFFA726)
                    "Alpha" -> Color(0xFFEF5350)
                    else -> Color(0xFF66BB6A)
                },
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AboutSection() {
    ContentCard(title = "关于本站") {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 技术栈标签
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
            ) {
                TechTag("Kotlin Multiplatform")
                TechTag("Compose Multiplatform")
                TechTag("Material Design 3")
            }
            
            // 主要功能
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "主要功能",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    BulletPoint("跨平台博客系统")
                    BulletPoint("支持 Android、iOS、Desktop 和 Web")
                    BulletPoint("现代化的 Material Design 3 界面")
                    BulletPoint("响应式设计，适配多种屏幕尺寸")
                }
            }
            
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f))
            
            // 链接
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LinkRow(
                    label = "技术支持",
                    content = "Cursor",
                    onClick = { /* TODO */ }
                )
                LinkRow(
                    label = "开源地址",
                    content = "github.com/jeadyx/kotlin-multiplatform",
                    onClick = { /* TODO */ }
                )
            }
        }
    }
}

@Composable
private fun ContentCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            content()
        }
    }
}

@Composable
private fun LinkRow(
    label: String,
    content: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label：",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = content,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}

@Composable
private fun TechnologyCard(
    technology: Technology,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 2.dp,
        backgroundColor = technology.color.copy(alpha = 0.1f)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.CustomCode,
                contentDescription = null,
                tint = technology.color,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = technology.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = technology.color
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = technology.description,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
private fun TeamMemberCard(member: TeamMember) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = member.name,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = member.role,
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.primary
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = { /* TODO: 打开GitHub */ }
                    ) {
                        Icon(SocialIcons.wechat(), contentDescription = "GitHub")
                    }
                    IconButton(
                        onClick = { /* TODO: 发送邮件 */ }
                    ) {
                        Icon(SocialIcons.qq(), contentDescription = "Email")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = member.description,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
private fun SocialButton(
    painter: Painter,
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .clip(CircleShape)
                .background(color.copy(alpha = 0.1f))
                .size(48.dp)
        ) {
            Icon(
                painter = painter,
                contentDescription = label,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ContactInfo(
    painter: Painter,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun VersionBadge(version: AppVersion) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // 版本号和状态行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 版本号部分
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "版本信息",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface
                    )
                }

                // 状态标签
                Card(
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = when(version.status) {
                        "Beta" -> Color(0xFFFFA726).copy(alpha = 0.1f)
                        "Alpha" -> Color(0xFFEF5350).copy(alpha = 0.1f)
                        else -> Color(0xFF66BB6A).copy(alpha = 0.1f)
                    },
                    elevation = 0.dp
                ) {
                    Text(
                        text = version.status,
                        style = MaterialTheme.typography.caption,
                        color = when(version.status) {
                            "Beta" -> Color(0xFFFFA726)
                            "Alpha" -> Color(0xFFEF5350)
                            else -> Color(0xFF66BB6A)
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 详细信息行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 版本号
                Column {
                    Text(
                        text = "版本号",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "v${version.version}",
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Medium
                    )
                }

                // 构建号
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "构建号",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "#${version.buildNumber}",
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Medium
                    )
                }

                // 更新日期
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "最后更新",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = version.lastUpdated,
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun TechTag(text: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f),
        elevation = 0.dp
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun BulletPoint(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary.copy(alpha = 0.6f))
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SocialSection() {
    ContentCard(title = "关注我们") {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialButton(
                painter = SocialIcons.wechat(),
                label = "微信",
                color = Color(0xFF07C160)
            ) { /* TODO */ }
            
            SocialButton(
                painter = SocialIcons.qq(),
                label = "QQ群",
                color = Color(0xFF12B7F5)
            ) { /* TODO */ }
            
            SocialButton(
                painter = SocialIcons.bilibili(),
                label = "Bilibili",
                color = Color(0xFFFF6699)
            ) { /* TODO */ }
            
            SocialButton(
                painter = SocialIcons.juejin(),
                label = "掘金",
                color = Color(0xFF1E80FF)
            ) { /* TODO */ }
        }
    }
}

@Composable
private fun TechnologySection(technologies: List<Technology>) {
    ContentCard(title = "技术栈") {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            technologies.forEach { tech ->
                TechnologyCard(
                    technology = tech,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun TeamSection(teamMembers: List<TeamMember>) {
    ContentCard(title = "团队成员") {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            teamMembers.forEach { member ->
                TeamMemberCard(member)
            }
        }
    }
} 