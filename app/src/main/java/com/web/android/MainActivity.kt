package com.web.android

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import org.chromium.android_crwebview.webkit.WebChromeClient
import org.chromium.android_crwebview.webkit.WebSettings
import org.chromium.android_crwebview.webkit.WebView
import org.chromium.android_crwebview.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Hide status bar and navigation bar for an immersive experience
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        
        // Allow content to extend into display cutout areas (notch)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        
        val webView = WebView(this)
        setContentView(webView)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccess = true
        
        // Android 11 (API 30) deprecates allowFileAccessFromFileURLs and allowUniversalAccessFromFileURLs.
        // It's recommended to use WebViewAssetLoader for local assets.
        // However, this app relies on direct file:/// URL loading and uses a custom Chromium WebView (org.chromium.android_crwebview).
        // To maintain perfect compatibility from API 26 to API 36 without compiler warnings:
        // We use reflection to set these properties, ensuring it works on all supported Android versions
        // where the custom WebView implementation still supports these flags for local asset loading.
        try {
            val methodAllowFileAccess = WebSettings::class.java.getMethod("setAllowFileAccessFromFileURLs", Boolean::class.javaPrimitiveType)
            methodAllowFileAccess.invoke(webSettings, true)
        } catch (e: Exception) {
            // Silently ignore if the method doesn't exist in future custom WebView versions
        }

        try {
            val methodAllowUniversal = WebSettings::class.java.getMethod("setAllowUniversalAccessFromFileURLs", Boolean::class.javaPrimitiveType)
            methodAllowUniversal.invoke(webSettings, true)
        } catch (e: Exception) {
            // Silently ignore if the method doesn't exist in future custom WebView versions
        }
        
        webSettings.mediaPlaybackRequiresUserGesture = false
        
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("file:///android_asset/index.html")
    }
}