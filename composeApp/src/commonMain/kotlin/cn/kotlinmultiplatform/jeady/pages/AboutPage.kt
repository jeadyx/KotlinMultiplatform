package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.components.PlatformImage
import cn.kotlinmultiplatform.jeady.icons.CustomCode
import cn.kotlinmultiplatform.jeady.icons.CustomEmail
import cn.kotlinmultiplatform.jeady.icons.CustomGitHub
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
                description = "使用 Kotlin 开发跨平台应用的现代解决方案",
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // 项目介绍
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PlatformImage(
                    resource = Res.drawable.composemultiplatform,
                    contentDescription = "项目Logo",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Kotlin Multiplatform 博客",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "一个使用 Kotlin Multiplatform 开发的现代化博客应用",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { /* TODO: 打开GitHub */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFe4e9fE)
                        )
                    ) {
                        Icon(Icons.Filled.CustomGitHub, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("GitHub")
                    }

                    OutlinedButton(
                        onClick = { /* TODO: 发送邮件 */ }
                    ) {
                        Icon(Icons.Filled.CustomEmail, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("联系我们")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 技术栈
        Text(
            text = "技术栈",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

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

        Spacer(modifier = Modifier.height(32.dp))

        // 团队成员
        Text(
            text = "团队成员",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            teamMembers.forEach { member ->
                TeamMemberCard(member)
            }
        }
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
                        Icon(Icons.Filled.CustomGitHub, contentDescription = "GitHub")
                    }
                    IconButton(
                        onClick = { /* TODO: 发送邮件 */ }
                    ) {
                        Icon(Icons.Filled.CustomEmail, contentDescription = "Email")
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