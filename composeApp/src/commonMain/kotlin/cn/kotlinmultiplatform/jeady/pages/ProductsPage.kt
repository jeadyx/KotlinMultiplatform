package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cn.kotlinmultiplatform.jeady.icons.action.Publish
import cn.kotlinmultiplatform.jeady.platform.UrlHandler
import kotlinmultiplatform.composeapp.generated.resources.Res
import kotlinmultiplatform.composeapp.generated.resources.app_logo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

data class Category(
    val name: String,
    val type: ProductType? = null,
    val subCategories: List<Category> = emptyList()
)

data class Product(
    val name: String,
    val description: String,
    val type: ProductType,
    val imageUrl: String,
    val url: String,
    val tags: List<String>,
    val downloadCount: String = "1000+",
    val rating: Float = 4.5f,
    val category: String
)

enum class ProductType {
    APP, WEBSITE, LIBRARY, TOOL
}

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun ProductsPage(
    urlHandler: UrlHandler,
    onPublishClick: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var expandedCategory by remember { mutableStateOf<Category?>(null) }

    val categories = remember {
        listOf(
            Category("全部"),
            Category("应用", ProductType.APP, listOf(
                Category("开发工具"),
                Category("效率工具"),
                Category("学习工具")
            )),
            Category("网站", ProductType.WEBSITE, listOf(
                Category("官方网站"),
                Category("社区网站"),
                Category("学习网站")
            )),
            Category("库", ProductType.LIBRARY, listOf(
                Category("UI库"),
                Category("网络库"),
                Category("工具库")
            )),
            Category("工具", ProductType.TOOL, listOf(
                Category("命令行工具"),
                Category("IDE插件"),
                Category("构建工具")
            ))
        )
    }

    val products = remember {
        listOf(
            Product(
                name = "Kotlin Multiplatform 助手",
                description = "一个帮助开发者快速上手 Kotlin Multiplatform 的工具，提供丰富的示例代码和最佳实践。",
                type = ProductType.APP,
                imageUrl = "app_logo",
                url = "https://github.com/JetBrains/kotlin",
                tags = listOf("Kotlin", "Multiplatform", "开发工具"),
                category = "开发工具"
            ),
            Product(
                name = "KMP 开发者社区",
                description = "专注于 Kotlin Multiplatform 技术的开发者社区，分享经验、解决问题、共同成长。",
                type = ProductType.WEBSITE,
                imageUrl = "app_logo",
                url = "https://kotlinlang.org/docs/multiplatform.html",
                tags = listOf("社区", "技术交流", "资源分享"),
                category = "社区网站"
            ),
            // Add more products here
        )
    }

    Row(modifier = Modifier.fillMaxSize()) {
        // Left sidebar
        Surface(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight(),
            elevation = 1.dp,
            color = MaterialTheme.colors.surface
        ) {
            LazyColumn(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                items(categories) { category ->
                    CategoryItem(
                        category = category,
                        selectedCategory = selectedCategory,
                        expandedCategory = expandedCategory,
                        onCategoryClick = { selectedCategory = it },
                        onExpandClick = { expandedCategory = if (expandedCategory == it) null else it }
                    )
                }
            }
        }

        // Right content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCategory?.name ?: "全部",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                
                Button(
                    onClick = onPublishClick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Publish,
                        contentDescription = "发布",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("发布")
                }
            }

            // Products grid
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 240.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val filteredProducts = products.filter { product ->
                    when {
                        selectedCategory == null || selectedCategory?.name == "全部" -> true
                        selectedCategory?.type != null -> product.type == selectedCategory?.type
                        else -> product.category == selectedCategory?.name
                    }
                }
                
                items(filteredProducts) { product ->
                    ProductCard(
                        product = product,
                        onClick = { urlHandler.openUrl(product.url) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: Category,
    selectedCategory: Category?,
    expandedCategory: Category?,
    onCategoryClick: (Category) -> Unit,
    onExpandClick: (Category) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCategoryClick(category) }
                .background(
                    if (selectedCategory == category)
                        MaterialTheme.colors.primary.copy(alpha = 0.1f)
                    else
                        Color.Transparent
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.body1,
                color = if (selectedCategory == category)
                    MaterialTheme.colors.primary
                else
                    MaterialTheme.colors.onSurface
            )
            if (category.subCategories.isNotEmpty()) {
                IconButton(
                    onClick = { onExpandClick(category) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Text(
                        text = if (expandedCategory == category) "−" else "+",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
        
        if (expandedCategory == category) {
            category.subCategories.forEach { subCategory ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCategoryClick(subCategory) }
                        .background(
                            if (selectedCategory == subCategory)
                                MaterialTheme.colors.primary.copy(alpha = 0.1f)
                            else
                                Color.Transparent
                        )
                        .padding(start = 32.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = subCategory.name,
                        style = MaterialTheme.typography.body2,
                        color = if (selectedCategory == subCategory)
                            MaterialTheme.colors.primary
                        else
                            MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = onClick),
        elevation = 1.dp
    ) {
        Column {
            // Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(MaterialTheme.colors.surface)
            ) {
                Image(
                    painter = painterResource(Res.drawable.app_logo),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.caption,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Stats row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "下载: ${product.downloadCount}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "评分: ${product.rating}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primary
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Tags
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    product.tags.take(3).forEach { tag ->
                        Surface(
                            modifier = Modifier.clip(RoundedCornerShape(2.dp)),
                            color = MaterialTheme.colors.primary.copy(alpha = 0.1f)
                        ) {
                            Text(
                                text = tag,
                                color = MaterialTheme.colors.primary,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
} 