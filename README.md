# web4droid

[English](#english) | [中文](#中文)

<h2 id="english">English</h2>

### Project Introduction
**web4droid** is a lightweight Android wrapper container utilizing a custom Chromium WebView core. It is designed to smoothly run Web Native apps on mobile devices. By integrating an independent Chromium engine instead of relying on the system's default WebView, it ensures high performance, consistency, and better compatibility across different Android versions.

### Features
* **Custom Chromium Core**: Uses `crwebview` (`org.chromium.android_crwebview`) instead of the system WebView, avoiding fragmentation and compatibility issues.
* **Support for Web Native apps**: Fully supports running various web-based applications and game engines, including but not limited to:
  * H5 Web App
  * RPG Maker MV/MZ
  * Browser Woditor
* **Immersive Fullscreen Experience**: Automatically hides the system status bar and navigation bar, with full support for display cutouts (notches).
* **Local Asset Loading**: Full support for loading core files via the `file:///android_asset/` protocol, with file read/write permissions for save data management.

### System Requirements
* **OS**: Android 8.0 (API level 26) or higher.
* **Architecture**: `arm64-v8a` (default configuration).

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/web4droid.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Build and run the project on your Android device or emulator.

### Usage Tutorial
To run your **Web Native apps** within the container:

1. Place your web application files (HTML, JS, CSS, assets) into the `app/src/main/assets/` directory.
2. Ensure your main entry point is named `index.html`.
3. Build the APK.

**Minimal Runnable Example:**
Create an `index.html` in `app/src/main/assets/`:
```html
<!DOCTYPE html>
<html>
<head>
    <title>Web Native App</title>
</head>
<body>
    <h1>Hello web4droid!</h1>
    <script>
        console.log("Web Native App is running.");
    </script>
</body>
</html>
```

### FAQ
**Q: Why is the app size larger than a standard WebView app?**
A: Because it bundles a custom Chromium engine (`crwebview`) to ensure consistent rendering performance across all devices.

**Q: How do I enable debug logging?**
A: You can inspect logs using `logcat` in Android Studio or Chrome DevTools via `chrome://inspect` (if debugging is enabled).

### License
This project is licensed under the Mozilla Public License Version 2.0 - see the `LICENSE` file for details.

### Acknowledgements
Special thanks to [wuruxu/crwebview](https://github.com/wuruxu/crwebview) for providing the Chromium WebView SDK support.

---

<h2 id="中文">中文</h2>

### 项目简介
**web4droid** 是一个基于定制 Chromium WebView 内核的轻量级 Android 容器应用，专为在移动设备上流畅运行 Web 原生应用 设计。通过集成独立的 Chromium 引擎，取代系统默认的 WebView，以在不同的 Android 版本中提供了高性能、一致性和更好的兼容性。

### 功能特性
* **独立 Chromium 内核**：使用 `crwebview`（`org.chromium.android_crwebview`）替代系统自带 WebView，避免了系统版本碎片化导致的兼容性问题。
* **支持 Web 原生应用**：完美支持运行各类基于 Web 的应用和游戏引擎，具体支持范围包括但不限于：
  * H5 Web App
  * RPG Maker MV/MZ
  * Browser Woditor
* **沉浸式全屏体验**：自动隐藏系统状态栏和导航栏，并全面适配刘海屏。
* **本地化资源加载**：完全支持通过 `file:///android_asset/` 协议加载核心文件，并支持文件读写权限（可用于存档数据管理）。

### 系统要求
* **操作系统**：Android 8.0 (API level 26) 及以上版本。
* **CPU 架构**：`arm64-v8a`（默认配置）。

### 安装步骤
1. 克隆本项目代码库：
   ```bash
   git clone https://github.com/yourusername/web4droid.git
   ```
2. 使用 Android Studio 打开该项目。
3. 等待 Gradle 同步完成。
4. 编译并在 Android 设备或模拟器上运行。

### 使用教程
要在容器中运行你的 **Web 原生应用（Web Native apps）**：

1. 将你的 Web 应用文件（HTML、JS、CSS、资源文件）放入 `app/src/main/assets/` 目录中。
2. 确保主入口文件为 `index.html`。
3. 编译 APK。

**最小可运行示例：**
在 `app/src/main/assets/` 目录下创建 `index.html`：
```html
<!DOCTYPE html>
<html>
<head>
    <title>Web 原生应用</title>
</head>
<body>
    <h1>你好，web4droid！</h1>
    <script>
        console.log("Web 原生应用正在运行。");
    </script>
</body>
</html>
```

### 常见问题
**Q：为什么应用的体积比使用原生 WebView 的应用大？**
A：因为本项目内置了独立的 Chromium 引擎（`crwebview`），以确保在所有设备上都能获得一致且高效的渲染性能。

**Q：如何查看调试日志？**
A：你可以在 Android Studio 中使用 `logcat` 查看日志，或者（在开启调试的情况下）通过 Chrome 浏览器的 `chrome://inspect` 进行调试。

### 许可证声明
本项目基于 Mozilla Public License Version 2.0 协议开源 - 详情请参阅 `LICENSE` 文件。

### 致谢
特别感谢 [wuruxu/crwebview](https://github.com/wuruxu/crwebview) 为本项目提供 Chromium WebView 支持。
