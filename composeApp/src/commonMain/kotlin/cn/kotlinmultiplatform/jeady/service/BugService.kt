package cn.kotlinmultiplatform.jeady.service

import cn.kotlinmultiplatform.jeady.model.Bug
import cn.kotlinmultiplatform.jeady.model.BugPriority
import cn.kotlinmultiplatform.jeady.model.BugStatus
import cn.kotlinmultiplatform.jeady.utils.IdGenerator
import kotlinx.datetime.Clock

class BugService private constructor() {
    private val bugs = mutableListOf<Bug>()

    init {
        // 添加示例 bug 数据
        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin Coroutines 内存泄漏问题",
            description = """
                在 Android 应用中使用 Kotlin Coroutines 时，如果在 ViewModel 中启动协程但没有正确取消，
                会导致内存泄漏。特别是在使用 viewModelScope 时，需要确保所有协程在 ViewModel 清理时都被取消。
                
                复现步骤：
                1. 在 ViewModel 中使用 GlobalScope 启动协程
                2. 旋转屏幕或返回上一页面
                3. 观察内存使用情况
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("coroutines", "memory-leak", "android")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Compose Multiplatform iOS 编译错误",
            description = """
                在 Kotlin Multiplatform 项目中，iOS 目标平台的 Compose Multiplatform 编译失败。
                错误信息显示找不到 Kotlin/Native 运行时库。
                
                环境：
                - Kotlin 1.9.20
                - Compose Multiplatform 1.5.10
                - Xcode 15.0
                
                复现步骤：
                1. 创建新的 KMP 项目并添加 Compose Multiplatform 依赖
                2. 运行 iOS 目标编译
            """.trimIndent(),
            status = BugStatus.IN_PROGRESS,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("compose", "ios", "compilation")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP SQLDelight 数据同步问题",
            description = """
                在使用 SQLDelight 进行跨平台数据持久化时，Android 和 iOS 平台之间的数据同步出现不一致。
                特别是在处理复杂的数据关系和外键约束时。
                
                复现步骤：
                1. 在 Android 端更新数据
                2. 在 iOS 端观察数据变化
                3. 发现部分关联数据未正确同步
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("sqldelight", "database", "sync")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin Flow 在 iOS 平台崩溃",
            description = """
                在 KMP 项目中，使用 Kotlin Flow 收集数据时，iOS 平台偶发性崩溃。
                崩溃日志显示与内存管理和线程切换相关。
                
                复现步骤：
                1. 创建 Flow 并在 iOS 端收集数据
                2. 快速切换页面或进行频繁操作
                3. 应用随机崩溃
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.CRITICAL,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("flow", "ios", "crash")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Ktor 客户端 SSL 握手失败",
            description = """
                在 KMP 项目中使用 Ktor 客户端进行 HTTPS 请求时，某些 Android 设备上出现 SSL 握手失败。
                错误信息：javax.net.ssl.SSLHandshakeException
                
                环境：
                - Ktor 2.3.5
                - Android API 21-33
                
                复现步骤：
                1. 配置 Ktor 客户端
                2. 发起 HTTPS 请求
                3. 在特定 Android 设备上测试
            """.trimIndent(),
            status = BugStatus.IN_PROGRESS,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("ktor", "network", "android", "ssl")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin 序列化性能问题",
            description = """
                在处理大量数据时，kotlinx.serialization 的性能显著低于其他序列化库。
                特别是在处理嵌套的复杂对象时，序列化和反序列化耗时过长。
                
                复现步骤：
                1. 创建包含多层嵌套的数据类
                2. 使用 kotlinx.serialization 进行序列化
                3. 对比其他序列化库的性能
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("serialization", "performance")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Compose Desktop 内存泄漏",
            description = """
                在 Compose Desktop 应用中，长时间运行后发现内存使用持续增长。
                特别是在频繁切换界面和处理图片资源时。
                
                复现步骤：
                1. 运行 Compose Desktop 应用
                2. 频繁切换包含图片的界面
                3. 监控内存使用情况
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("compose", "desktop", "memory-leak")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 资源打包问题",
            description = """
                在 KMP 项目中，使用 resource() 函数访问资源文件时，某些平台无法正确加载资源。
                特别是在处理非文本类型的资源文件时。
                
                复现步骤：
                1. 添加图片等资源文件
                2. 使用 resource() 函数加载
                3. 在不同平台测试资源访问
            """.trimIndent(),
            status = BugStatus.RESOLVED,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("resources", "assets")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin Native 并发问题",
            description = """
                在 Kotlin/Native 中使用多线程时，出现状态共享和冻结对象相关的问题。
                特别是在处理可变状态和回调时。
                
                复现步骤：
                1. 创建共享的可变状态
                2. 在多个线程中访问
                3. 观察不一致的行为
            """.trimIndent(),
            status = BugStatus.IN_PROGRESS,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("native", "concurrency", "threading")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 测试覆盖率统计失败",
            description = """
                在 KMP 项目中，使用 kover 统计测试覆盖率时，无法正确合并多平台的覆盖率报告。
                导致覆盖率数据不准确。
                
                复现步骤：
                1. 配置 kover 插件
                2. 运行多平台测试
                3. 生成覆盖率报告
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.LOW,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("testing", "coverage", "kover")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Compose Navigation 动画问题",
            description = """
                在 Compose Multiplatform 项目中，使用导航组件时，页面切换动画在某些情况下不流畅或丢失。
                特别是在处理深层嵌套导航时。
                
                复现步骤：
                1. 实现嵌套导航结构
                2. 配置页面切换动画
                3. 快速连续导航操作
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("compose", "navigation", "animation")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 依赖版本冲突",
            description = """
                在 KMP 项目中，不同模块使用不同版本的依赖库导致编译错误。
                特别是在处理传递依赖时。
                
                复现步骤：
                1. 添加多个依赖库
                2. 检查依赖树
                3. 观察版本冲突
            """.trimIndent(),
            status = BugStatus.RESOLVED,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("gradle", "dependencies")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin 协程作用域泄漏",
            description = """
                在使用协程作用域时，如果没有正确处理取消操作，会导致协程泄漏。
                特别是在使用自定义作用域时。
                
                复现步骤：
                1. 创建自定义协程作用域
                2. 启动长时间运行的协程
                3. 监控协程的生命周期
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("coroutines", "memory-leak")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP iOS 符号剥离问题",
            description = """
                在发布 iOS 应用时，某些 Kotlin 框架的调试符号被错误剥离，
                导致崩溃报告中缺少有效的堆栈信息。
                
                复现步骤：
                1. 构建发布版本
                2. 检查 dSYM 文件
                3. 验证崩溃报告
            """.trimIndent(),
            status = BugStatus.IN_PROGRESS,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("ios", "debugging", "crash-reporting")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Compose 状态恢复问题",
            description = """
                在 Compose Multiplatform 应用中，进程重建后状态恢复不完整。
                特别是在处理复杂的界面状态时。
                
                复现步骤：
                1. 保存复杂的界面状态
                2. 触发进程重建
                3. 检查状态恢复情况
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("compose", "state-management")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 构建缓存失效",
            description = """
                在大型 KMP 项目中，Gradle 构建缓存频繁失效，
                导致构建时间显著增加。
                
                复现步骤：
                1. 启用 Gradle 构建缓存
                2. 执行清理构建
                3. 监控缓存命中率
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("gradle", "build", "performance")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin 反射性能问题",
            description = """
                在 KMP 项目中使用 Kotlin 反射时，特别是在 Native 平台上，
                性能显著下降。
                
                复现步骤：
                1. 使用反射API
                2. 执行性能测试
                3. 比较不同平台的性能
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.LOW,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("reflection", "performance", "native")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 网络请求超时处理",
            description = """
                在 KMP 项目中，网络请求超时处理在不同平台表现不一致。
                特别是在处理长连接时。
                
                复现步骤：
                1. 配置网络请求超时
                2. 模拟网络延迟
                3. 测试超时行为
            """.trimIndent(),
            status = BugStatus.IN_PROGRESS,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("network", "timeout", "cross-platform")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Compose 手势冲突",
            description = """
                在 Compose Multiplatform 中，嵌套的可滚动组件之间存在手势冲突。
                影响用户交互体验。
                
                复现步骤：
                1. 创建嵌套的可滚动布局
                2. 测试滚动手势
                3. 观察手势冲突
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("compose", "gesture", "ui")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 本地化资源加载",
            description = """
                在 KMP 项目中，本地化资源在某些平台加载失败。
                特别是在处理非拉丁字符时。
                
                复现步骤：
                1. 添加多语言资源
                2. 切换语言设置
                3. 验证资源加载
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("localization", "resources")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin 序列化循环引用",
            description = """
                使用 kotlinx.serialization 序列化包含循环引用的对象时崩溃。
                需要实现自定义序列化逻辑。
                
                复现步骤：
                1. 创建循环引用对象
                2. 尝试序列化
                3. 处理序列化异常
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("serialization", "data-structure")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 权限处理不一致",
            description = """
                在 KMP 项目中，不同平台的权限请求和处理逻辑不一致。
                需要统一权限处理接口。
                
                复现步骤：
                1. 实现权限请求
                2. 在多平台测试
                3. 处理权限回调
            """.trimIndent(),
            status = BugStatus.IN_PROGRESS,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("permissions", "cross-platform")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Compose 性能优化",
            description = """
                在处理大量数据时，Compose UI 的渲染性能下降明显。
                需要实现虚拟化列表和性能优化。
                
                复现步骤：
                1. 加载大量数据
                2. 滚动列表
                3. 监控性能指标
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("compose", "performance", "optimization")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 文件系统访问",
            description = """
                在 KMP 项目中，文件系统访问权限和路径处理在不同平台表现不一致。
                需要统一文件操作接口。
                
                复现步骤：
                1. 实现文件操作
                2. 测试文件权限
                3. 验证路径处理
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("file-system", "cross-platform")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin 协程取消传播",
            description = """
                在复杂的协程层级结构中，取消操作未能正确传播到所有子协程。
                导致资源泄漏。
                
                复现步骤：
                1. 创建协程层级
                2. 触发取消操作
                3. 监控协程状态
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("coroutines", "cancellation")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 蓝牙连接处理",
            description = """
                在 KMP 项目中，蓝牙连接和数据传输在不同平台的实现不一致。
                需要统一蓝牙操作接口。
                
                复现步骤：
                1. 实现蓝牙连接
                2. 测试数据传输
                3. 处理连接状态
            """.trimIndent(),
            status = BugStatus.IN_PROGRESS,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("bluetooth", "cross-platform", "connectivity")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Compose 主题切换问题",
            description = """
                在 Compose Multiplatform 中，动态切换主题时部分组件样式未更新。
                需要优化主题传播机制。
                
                复现步骤：
                1. 实现主题切换
                2. 测试组件样式
                3. 验证主题一致性
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("compose", "theming", "ui")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 推送通知集成",
            description = """
                在 KMP 项目中，推送通知的处理和显示在不同平台表现不一致。
                需要统一推送通知接口。
                
                复现步骤：
                1. 配置推送服务
                2. 处理通知回调
                3. 测试通知显示
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("push-notification", "cross-platform")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Kotlin 内联类性能",
            description = """
                在大规模使用内联类的项目中，编译时间显著增加。
                需要优化内联类的使用。
                
                复现步骤：
                1. 定义多个内联类
                2. 测试编译时间
                3. 分析性能影响
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.LOW,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("inline-classes", "performance", "compilation")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "KMP 图片处理优化",
            description = """
                在 KMP 项目中，图片处理和缓存策略在不同平台效率不一致。
                需要优化图片处理流程。
                
                复现步骤：
                1. 实现图片加载
                2. 测试缓存机制
                3. 监控内存使用
            """.trimIndent(),
            status = BugStatus.IN_PROGRESS,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("image-processing", "performance", "cross-platform")
        ))

        addBug(Bug(
            id = IdGenerator.generateId(),
            title = "Compose 动画性能",
            description = """
                在复杂的 Compose 动画中，出现掉帧和卡顿现象。
                需要优化动画性能。
                
                复现步骤：
                1. 实现复杂动画
                2. 测试动画流畅度
                3. 监控帧率
            """.trimIndent(),
            status = BugStatus.OPEN,
            priority = BugPriority.HIGH,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = listOf("compose", "animation", "performance")
        ))
    }

    companion object {
        private val instance = BugService()
        fun getInstance() = instance
    }

    fun getAllBugs(): List<Bug> = bugs.toList()

    fun getBugById(id: String): Bug? = bugs.find { it.id == id }

    fun addBug(bug: Bug) {
        bugs.add(bug)
    }

    fun updateBug(bug: Bug) {
        val index = bugs.indexOfFirst { it.id == bug.id }
        if (index != -1) {
            bugs[index] = bug
        }
    }

    fun deleteBug(id: String) {
        bugs.removeAll { it.id == id }
    }

    fun createNewBug(): Bug {
        return Bug(
            id = IdGenerator.generateId(),
            title = "",
            description = "",
            status = BugStatus.OPEN,
            priority = BugPriority.MEDIUM,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            tags = emptyList()
        )
    }
} 