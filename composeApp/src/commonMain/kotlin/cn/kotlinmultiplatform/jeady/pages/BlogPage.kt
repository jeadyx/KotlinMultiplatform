package cn.kotlinmultiplatform.jeady.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class BlogPost(
    val id: String,
    val title: String,
    val summary: String,
    val content: String,
    val author: String,
    val category: String,
    val tags: List<String>,
    val publishDate: LocalDateTime,
    val readingTime: Int // 分钟
)

data class BlogCategory(
    val name: String,
    val color: Color
)

@Composable
fun BlogPage(
    onPostClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<BlogCategory?>(null) }
    var isEditorVisible by remember { mutableStateOf(false) }
    var currentEditPost by remember { mutableStateOf<BlogPost?>(null) }
    
    val categories = listOf(
        BlogCategory("技术", Color(0xFF2196F3)),
        BlogCategory("设计", Color(0xFFE91E63)),
        BlogCategory("思考", Color(0xFF4CAF50)),
        BlogCategory("生活", Color(0xFFFF9800))
    )
    
    val posts = remember {
        listOf(
            BlogPost(
                id = "1",
                title = "Kotlin Multiplatform 开发实践",
                summary = "本文将分享在使用 Kotlin Multiplatform 开发跨平台应用时的经验和最佳实践...",
                content = "详细内容",
                author = "张三",
                category = "技术",
                tags = listOf("Kotlin", "跨平台", "移动开发"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 10
            ),
            BlogPost(
                id = "2",
                title = "现代化 UI 设计趋势",
                summary = "探讨2024年UI设计的新趋势，包括Neumorphism、玻璃态设计等新兴设计风格...",
                content = "详细内容",
                author = "李四",
                category = "设计",
                tags = listOf("UI", "设计趋势", "用户体验"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 8
            ),
            BlogPost(
                id = "3",
                title = "程序员的成长之路",
                summary = "分享一个程序员从初级到高级的成长经历，以及在这个过程中的思考和感悟...",
                content = "详细内容",
                author = "王五",
                category = "思考",
                tags = listOf("职业发展", "经验分享", "技术成长"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 15
            ),
            BlogPost(
                id = "4",
                title = "SwiftUI 与 Jetpack Compose 对比",
                summary = "深入分析两大现代声明式UI框架的异同，探讨它们在实际项目中的应用场景和优劣势...",
                content = "详细内容",
                author = "赵六",
                category = "技术",
                tags = listOf("SwiftUI", "Compose", "跨平台"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 12
            ),
            BlogPost(
                id = "5",
                title = "设计系统的构建之道",
                summary = "如何从零开始构建一个完整的设计系统？本文将分享设计系统的规划、实施和维护经验...",
                content = "详细内容",
                author = "钱七",
                category = "设计",
                tags = listOf("设计系统", "组件库", "规范"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 20
            ),
            BlogPost(
                id = "6",
                title = "远程工作的效率之道",
                summary = "在后疫情时代，远程办公已成为新常态。如何提高远程工作效率？如何保持工作与生活的平衡？...",
                content = "详细内容",
                author = "孙八",
                category = "生活",
                tags = listOf("远程办公", "效率", "工作方式"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 8
            ),
            BlogPost(
                id = "7",
                title = "AI 驱动的代码生成实践",
                summary = "探索 AI 在软件开发中的应用，从代码补全到自动化测试，AI 如何提升开发效率...",
                content = "详细内容",
                author = "周九",
                category = "技术",
                tags = listOf("AI", "代码生成", "效率工具"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 15
            ),
            BlogPost(
                id = "8",
                title = "写给新手设计师的建议",
                summary = "从个人经验出发，分享设计师职业发展的经验和建议，帮助新手快速成长...",
                content = "详细内容",
                author = "吴十",
                category = "思考",
                tags = listOf("设计师", "职业发展", "经验分享"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 10
            ),
            BlogPost(
                id = "9",
                title = "构建高性能的跨平台应用",
                summary = "探讨在跨平台开发中如何保持应用的高性能，包括存优化、渲染优化等技术细节...",
                content = "详细内容",
                author = "郑十一",
                category = "技术",
                tags = listOf("性能优化", "跨平台", "最佳实践"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 18
            ),
            BlogPost(
                id = "10",
                title = "我的极简主义生活实践",
                summary = "分享如何将极简主义理念应用到生活中，提升生活品质，减少物质和精神的负担...",
                content = "详细内容",
                author = "陈十二",
                category = "生活",
                tags = listOf("极简主义", "生活方式", "心得体会"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 12
            ),
            BlogPost(
                id = "11",
                title = "函数式编程在现代开发中的应用",
                summary = "探讨函数式编程范式在现代软件开发中的实际应用场景和优势...",
                content = "详细内容",
                author = "林一",
                category = "技术",
                tags = listOf("函数式编程", "软件架构", "最佳实践"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 15
            ),
            BlogPost(
                id = "12",
                title = "移动端动画设计指南",
                summary = "深入浅出地讲解移动应用中动画设计的原则和实现方法...",
                content = "详细内容",
                author = "黄二",
                category = "设计",
                tags = listOf("动画设计", "交互设计", "用户体验"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 12
            ),
            BlogPost(
                id = "13",
                title = "微服务架构实践总结",
                summary = "分享在大规模微服务架构项目中的经验和教训...",
                content = "详细内容",
                author = "赵三",
                category = "技术",
                tags = listOf("微服务", "架构设计", "分布式系统"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 20
            ),
            BlogPost(
                id = "14",
                title = "设计师的自我提升之路",
                summary = "如何在设计领域持续学习和成长，保持创造力和竞争力...",
                content = "详细内容",
                author = "钱四",
                category = "思考",
                tags = listOf("职业发展", "设计思维", "学习方法"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 10
            ),
            BlogPost(
                id = "15",
                title = "响应式设计最佳实践",
                summary = "探讨现代响应式设计的技巧和注意事项...",
                content = "详细内容",
                author = "孙五",
                category = "设计",
                tags = listOf("响应式设计", "前端开发", "用户体验"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 15
            ),
            BlogPost(
                id = "16",
                title = "云原生应用开发指南",
                summary = "详细介绍云原生应用的开发流程、最佳实践和常见陷阱...",
                content = "详细内容",
                author = "周六",
                category = "技术",
                tags = listOf("云原生", "DevOps", "微服务"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 25
            ),
            BlogPost(
                id = "17",
                title = "设计系统的演进之路",
                summary = "从0到1构建设计系统，以及如何持续优化和维护...",
                content = "详细内容",
                author = "吴七",
                category = "设计",
                tags = listOf("设计系统", "组件库", "设计规范"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 18
            ),
            BlogPost(
                id = "18",
                title = "程序员的心理健康",
                summary = "探讨程序员群体常见的心理问题及其解决方案...",
                content = "详细内容",
                author = "郑八",
                category = "生活",
                tags = listOf("心理健康", "工作压力", "生活平衡"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 12
            ),
            BlogPost(
                id = "19",
                title = "GraphQL 实战经验",
                summary = "分享在大型项目中使用 GraphQL 的经验和最佳实践...",
                content = "详细内容",
                author = "王九",
                category = "技术",
                tags = listOf("GraphQL", "API", "后端开发"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 20
            ),
            BlogPost(
                id = "20",
                title = "设计师的工具箱",
                summary = "推荐一些提升设计效率的工具和插件...",
                content = "详细内容",
                author = "李十",
                category = "设计",
                tags = listOf("设计工具", "效率", "工作流"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 10
            ),
            BlogPost(
                id = "21",
                title = "WebAssembly 开发实践",
                summary = "探索 WebAssembly 在现代 Web 应用中的应用场景和实践经验...",
                content = "详细内容",
                author = "张十一",
                category = "技术",
                tags = listOf("WebAssembly", "性能优化", "Web开发"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 22
            ),
            BlogPost(
                id = "22",
                title = "远程团队管理之道",
                summary = "如何有效管理和协调远程开发团队，提升团队效率...",
                content = "详细内容",
                author = "刘十二",
                category = "思考",
                tags = listOf("团队管理", "远程办公", "协作"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 15
            ),
            BlogPost(
                id = "23",
                title = "移动应用性能优化",
                summary = "深入探讨移动应用的性能优化技巧和工具...",
                content = "详细内容",
                author = "陈十三",
                category = "技术",
                tags = listOf("性能优化", "移动开发", "Android", "iOS"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 25
            ),
            BlogPost(
                id = "24",
                title = "设计师的职业规划",
                summary = "探讨设计师的职业发展路径和规划方法...",
                content = "详细内容",
                author = "林十四",
                category = "思考",
                tags = listOf("职业发展", "设计", "规划"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 12
            ),
            BlogPost(
                id = "25",
                title = "Kotlin 协程最佳实践",
                summary = "深入理解 Kotlin 协程，及其在实际项目中的应用...",
                content = "详细内容",
                author = "黄十五",
                category = "技术",
                tags = listOf("Kotlin", "协程", "并发编程"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 20
            ),
            BlogPost(
                id = "26",
                title = "数字游民的生活方式",
                summary = "分享如何通过远程工作实现数字游民生活...",
                content = "详细内容",
                author = "赵十六",
                category = "生活",
                tags = listOf("数字游民", "远程工作", "生活方式"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 15
            ),
            BlogPost(
                id = "27",
                title = "React Native 与 Flutter 对比",
                summary = "深入分析两大跨平台开发框架的优劣势...",
                content = "详细内容",
                author = "钱十七",
                category = "技术",
                tags = listOf("跨平台开发", "移动开发", "框架对比"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 18
            ),
            BlogPost(
                id = "28",
                title = "设计师的阅读清单",
                summary = "推荐一些值得设计师阅读的书籍和文章...",
                content = "详细内容",
                author = "孙十八",
                category = "设计",
                tags = listOf("阅读", "学习资源", "设计"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 10
            ),
            BlogPost(
                id = "29",
                title = "DevOps 实践指南",
                summary = "从零开始搭建 DevOps 工作流程的实践指南...",
                content = "详细内容",
                author = "周十九",
                category = "技术",
                tags = listOf("DevOps", "CI/CD", "自动化"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 25
            ),
            BlogPost(
                id = "30",
                title = "设计思维工作坊",
                summary = "如何组织和运营设计思维工作坊...",
                content = "详细内容",
                author = "吴二十",
                category = "设计",
                tags = listOf("设计思维", "工作坊", "团队协作"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 15
            ),
            BlogPost(
                id = "31",
                title = "区块链应用开发实践",
                summary = "探索区块链技术在实际应用中的落地方案...",
                content = "详细内容",
                author = "郑二一",
                category = "技术",
                tags = listOf("区块链", "智能合约", "去中心化"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 22
            ),
            BlogPost(
                id = "32",
                title = "设计师的创意之源",
                summary = "分享如何获取设计灵感和保持创造力...",
                content = "详细内容",
                author = "王二二",
                category = "设计",
                tags = listOf("创意", "灵感", "设计方法"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 12
            ),
            BlogPost(
                id = "33",
                title = "技术债务管理",
                summary = "如何有效管理和消除技术债务...",
                content = "详细内容",
                author = "李二三",
                category = "技术",
                tags = listOf("技术债务", "重构", "代码质量"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 18
            ),
            BlogPost(
                id = "34",
                title = "设计评审最佳实践",
                summary = "如何组织高效的设计评审会议...",
                content = "详细内容",
                author = "张二四",
                category = "设计",
                tags = listOf("设计评审", "团队协作", "流程优化"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 15
            ),
            BlogPost(
                id = "35",
                title = "5G 时代的应用开发",
                summary = "探讨 5G 技术对应用开发的影响和机遇...",
                content = "详细内容",
                author = "刘二五",
                category = "技术",
                tags = listOf("5G", "物联网", "边缘计算"),
                publishDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                readingTime = 20
            )
        )
    }
    
    var currentPage by remember { mutableStateOf(0) }
    val itemsPerPage = 8
    
    Box(modifier = Modifier.fillMaxSize()) {
        if (isEditorVisible) {
            MarkdownEditor(
                post = currentEditPost,
                onSave = { /* TODO: Implement save functionality */ },
                onClose = { 
                    isEditorVisible = false 
                    currentEditPost = null
                }
            )
        } else {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { 
                            currentEditPost = null
                            isEditorVisible = true 
                        }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "添加文章")
                    }
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    // 搜索栏
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it }
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 分类选择
                    CategorySelector(
                        categories = categories,
                        selectedCategory = selectedCategory,
                        onCategorySelect = { selectedCategory = it }
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // 博客列表
                    val filteredPosts = posts.filter {
                        (selectedCategory == null || it.category == selectedCategory?.name) &&
                        (searchQuery.isEmpty() || it.title.contains(searchQuery, ignoreCase = true) ||
                            it.summary.contains(searchQuery, ignoreCase = true))
                    }
                    
                    val totalPages = (filteredPosts.size + itemsPerPage - 1) / itemsPerPage
                    val currentPagePosts = filteredPosts
                        .drop(currentPage * itemsPerPage)
                        .take(itemsPerPage)

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        BlogList(
                            posts = currentPagePosts,
                            onPostClick = onPostClick,
                            onEditClick = { post ->
                                currentEditPost = post
                                isEditorVisible = true
                            }
                        )
                        
                        // 分页控制
                        if (filteredPosts.size > itemsPerPage) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = { if (currentPage > 0) currentPage-- },
                                    enabled = currentPage > 0
                                ) {
                                    Text("上一页")
                                }
                                
                                Text(
                                    text = "${currentPage + 1} / $totalPages",
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                                
                                TextButton(
                                    onClick = { if (currentPage < totalPages - 1) currentPage++ },
                                    enabled = currentPage < totalPages - 1
                                ) {
                                    Text("下一页")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        placeholder = { Text("搜索文章...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "搜索") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun CategorySelector(
    categories: List<BlogCategory>,
    selectedCategory: BlogCategory?,
    onCategorySelect: (BlogCategory?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 全部分类选项
        CategoryChip(
            category = null,
            isSelected = selectedCategory == null,
            onClick = { onCategorySelect(null) }
        )
        
        categories.forEach { category ->
            CategoryChip(
                category = category,
                isSelected = category == selectedCategory,
                onClick = { onCategorySelect(category) }
            )
        }
    }
}

@Composable
private fun CategoryChip(
    category: BlogCategory?,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = if (isSelected) {
            category?.color ?: MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.surface
        },
        contentColor = if (isSelected) {
            Color.White
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.clickable(onClick = onClick),
        elevation = if (isSelected) 4.dp else 1.dp
    ) {
        Text(
            text = category?.name ?: "全部",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.body2.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}

@Composable
private fun BlogList(
    posts: List<BlogPost>,
    onPostClick: (String) -> Unit,
    onEditClick: (BlogPost) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(posts) { post ->
            BlogCard(
                post = post,
                onClick = { onPostClick(post.id) },
                onEditClick = { onEditClick(post) }
            )
        }
    }
}

@Composable
private fun BlogCard(
    post: BlogPost,
    onClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextButton(onClick = onEditClick) {
                        Text("编辑")
                    }
                    CategoryLabel(post.category)
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // 摘要
            Text(
                text = post.summary,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 标签和阅读时间
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 标签
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    post.tags.take(3).forEach { tag ->
                        TagChip(tag)
                    }
                }
                
                // 阅读时间
                Text(
                    text = "${post.readingTime} min read",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
private fun CategoryLabel(category: String) {
    Surface(
        color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun TagChip(tag: String) {
    Surface(
        color = MaterialTheme.colors.secondary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "#$tag",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.secondary
        )
    }
} 