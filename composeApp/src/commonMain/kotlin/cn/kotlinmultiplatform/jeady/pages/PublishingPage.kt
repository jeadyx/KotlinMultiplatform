package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.icons.action.Help
import cn.kotlinmultiplatform.jeady.icons.device.Android
import cn.kotlinmultiplatform.jeady.icons.device.Apple
import cn.kotlinmultiplatform.jeady.platform.UrlHandler
import cn.kotlinmultiplatform.jeady.service.ProductService

data class AppInfo(
    val name: String = "",
    val packageName: String = "",
    val version: String = "",
    val description: String = "",
    val shortDescription: String = "",
    val website: String = "",
    val privacyPolicy: String = "",
    val termsOfService: String = "",
    val supportEmail: String = "",
    val category: String = "",
    val price: String = "",
    val screenshots: List<String> = emptyList(),
    val icon: String = ""
)

@Composable
fun PublishingPage(
    urlHandler: UrlHandler,
    onNavigateToHelp: () -> Unit,
    onClose: () -> Unit
) {
    var selectedPlatform by remember { mutableStateOf<String?>(null) }
    var appInfo by remember { mutableStateOf(AppInfo()) }

    val productService = remember { ProductService.getInstance() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("应用发布") },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Default.Close, contentDescription = "关闭")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToHelp) {
                        Icon(Icons.Default.Help, contentDescription = "帮助")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "选择发布平台",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PlatformCard(
                        title = "Google Play",
                        icon = Icons.Default.Android,
                        selected = selectedPlatform == "android",
                        onClick = { selectedPlatform = "android" }
                    )
                    PlatformCard(
                        title = "App Store",
                        icon = Icons.Default.Apple,
                        selected = selectedPlatform == "ios",
                        onClick = { selectedPlatform = "ios" }
                    )
                }
            }

            if (selectedPlatform != null) {
                item {
                    Text(
                        text = "应用信息",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                item {
                    AppInfoForm(
                        appInfo = appInfo,
                        onAppInfoChange = { appInfo = it }
                    )
                }

                item {
                    Button(
                        onClick = {
                            val product = Product(
                                name = appInfo.name,
                                description = appInfo.description,
                                type = if (selectedPlatform == "android") ProductType.APP else ProductType.APP,
                                imageUrl = appInfo.icon,
                                url = appInfo.website,
                                tags = listOf(selectedPlatform ?: ""),
                                category = appInfo.category
                            )
                            productService.addProduct(product)
                            onClose()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("发布应用")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RowScope.PlatformCard(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 2.dp,
            color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
        ),
        modifier = Modifier
            .weight(1f)
            .height(120.dp)
            .padding(4.dp),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
private fun AppInfoForm(
    appInfo: AppInfo,
    onAppInfoChange: (AppInfo) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = appInfo.name,
            onValueChange = { onAppInfoChange(appInfo.copy(name = it)) },
            label = { Text("应用名称") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = appInfo.packageName,
            onValueChange = { onAppInfoChange(appInfo.copy(packageName = it)) },
            label = { Text("包名") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = appInfo.version,
            onValueChange = { onAppInfoChange(appInfo.copy(version = it)) },
            label = { Text("版本号") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = appInfo.description,
            onValueChange = { onAppInfoChange(appInfo.copy(description = it)) },
            label = { Text("详细描述") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            maxLines = 5
        )

        OutlinedTextField(
            value = appInfo.shortDescription,
            onValueChange = { onAppInfoChange(appInfo.copy(shortDescription = it)) },
            label = { Text("简短描述") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = appInfo.website,
            onValueChange = { onAppInfoChange(appInfo.copy(website = it)) },
            label = { Text("网") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = appInfo.privacyPolicy,
            onValueChange = { onAppInfoChange(appInfo.copy(privacyPolicy = it)) },
            label = { Text("隐私政策网址") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = appInfo.termsOfService,
            onValueChange = { onAppInfoChange(appInfo.copy(termsOfService = it)) },
            label = { Text("服务条款网址") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = appInfo.supportEmail,
            onValueChange = { onAppInfoChange(appInfo.copy(supportEmail = it)) },
            label = { Text("支持邮箱") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = appInfo.category,
            onValueChange = { onAppInfoChange(appInfo.copy(category = it)) },
            label = { Text("应用类别") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = appInfo.price,
            onValueChange = { onAppInfoChange(appInfo.copy(price = it)) },
            label = { Text("价格") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        // TODO: Add screenshot upload functionality
        Button(
            onClick = { /* TODO: Implement screenshot upload */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("添加截图")
        }

        // TODO: Add icon upload functionality
        Button(
            onClick = { /* TODO: Implement icon upload */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("上传应用图标")
        }
    }
} 