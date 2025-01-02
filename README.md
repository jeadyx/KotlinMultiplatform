# Kotlin Multiplatform Demo

这是一个使用 Kotlin Multiplatform 构建的演示项目，展示了如何使用 Kotlin 和 Compose Multiplatform 创建跨平台应用。

## 特性

- 使用 Kotlin Multiplatform 进行跨平台开发
- 使用 Compose Multiplatform 构建用户界面
- 支持 Android、iOS、Desktop 和 Web 平台
- 使用 WebAssembly 进行 Web 部署

## 构建和运行

### 前提条件

- JDK 17 或更高版本
- Android Studio Arctic Fox 或更高版本
- Xcode 12 或更高版本（用于 iOS 构建）
- Node.js 和 npm（用于 Web 构建）

### 构建步骤

1. 克隆仓库：
   ```bash
   git clone https://github.com/jeadyx/KotlinMultiplatform.git
   ```

2. 进入项目目录：
   ```bash
   cd KotlinMultiplatform
   ```

3. 构建项目：
   ```bash
   ./gradlew build
   ```

### 运行

- Web 版本（开发模式）：
  ```bash
  ./gradlew wasmJsBrowserDevelopmentRun
  ```

- Web 版本（生产模式）：
  ```bash
  ./gradlew wasmJsBrowserProductionWebpack
  ```

- Android 版本：
  使用 Android Studio 打开项目并运行

- Desktop 版本：
  ```bash
  ./gradlew :composeApp:run
  ```

## 部署

项目使用 GitHub Actions 自动部署到 GitHub Pages。每次推送到 main 分支时都会触发部署。

## 许可证

本项目采用 MIT 许可证。详见 [LICENSE](LICENSE) 文件。