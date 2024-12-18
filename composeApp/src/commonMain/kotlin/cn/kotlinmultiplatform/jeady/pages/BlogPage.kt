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
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import cn.kotlinmultiplatform.jeady.model.BlogPost
import cn.kotlinmultiplatform.jeady.service.BlogService

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
    
    val blogService = remember { BlogService.getInstance() }
    var posts by remember { mutableStateOf(blogService.getAllPosts()) }
    
    val categories = listOf(
        BlogCategory("技术", Color(0xFF2196F3)),
        BlogCategory("设计", Color(0xFFE91E63)),
        BlogCategory("思考", Color(0xFF4CAF50)),
        BlogCategory("生活", Color(0xFFFF9800))
    )
    
    Box(modifier = Modifier.fillMaxSize()) {
        if (isEditorVisible) {
            MarkdownEditor(
                post = currentEditPost,
                onSave = { post -> 
                    if (currentEditPost == null) {
                        blogService.addPost(post)
                    } else {
                        blogService.updatePost(post)
                    }
                    posts = blogService.getAllPosts()
                    isEditorVisible = false
                    currentEditPost = null
                },
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
                            currentEditPost = blogService.createNewPost()
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
                    
                    BlogList(
                        posts = filteredPosts,
                        onPostClick = onPostClick,
                        onEditClick = { post ->
                            currentEditPost = post
                            isEditorVisible = true
                        }
                    )
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
                    TextButton(
                        onClick = { 
                            onEditClick()
                        },
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClickLabel = "Edit",
                            onClick = {
                                onEditClick()
                            },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                    ) {
                        Text("编辑")
                    }
                    CategoryLabel(post.category)
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = post.summary,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    post.tags.take(3).forEach { tag ->
                        TagChip(tag)
                    }
                }
                
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