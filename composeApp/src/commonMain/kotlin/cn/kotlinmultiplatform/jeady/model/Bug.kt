package cn.kotlinmultiplatform.jeady.model

data class Bug(
    val id: String,
    val title: String,
    val description: String,
    val status: BugStatus,
    val priority: BugPriority,
    val createdAt: Long,
    val updatedAt: Long,
    val assignee: String? = null,
    val tags: List<String> = emptyList()
)

enum class BugStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED
}

enum class BugPriority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
} 