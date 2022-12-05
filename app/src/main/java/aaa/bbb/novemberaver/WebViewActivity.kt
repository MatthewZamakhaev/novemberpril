package aaa.bbb.novemberaver

import aaa.bbb.novemberaver.databinding.ActivityWebViewBinding
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.google.firebase.remoteconfig.FirebaseRemoteConfig


class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    private var initialUrl = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressed()

        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
        when (intent.getStringExtra("refer")) {
            remoteConfig.getString("ref1") -> initialUrl = remoteConfig.getString("url1")
            remoteConfig.getString("ref2") -> initialUrl = remoteConfig.getString("url2")
            remoteConfig.getString("ref3") -> initialUrl = remoteConfig.getString("url3")
            remoteConfig.getString("ref4") -> initialUrl = remoteConfig.getString("url4")
            remoteConfig.getString("ref5") -> initialUrl = remoteConfig.getString("url5")
            remoteConfig.getString("ref6") -> initialUrl = remoteConfig.getString("url6")
            remoteConfig.getString("ref7") -> initialUrl = remoteConfig.getString("url7")
            remoteConfig.getString("ref8") -> initialUrl = remoteConfig.getString("url8")
        }
        val editor = getSharedPreferences("Data", MODE_PRIVATE).edit()
        val prefs = getSharedPreferences("Data", MODE_PRIVATE)
        val saved_url = prefs.getString("saved_url", null)

        val myWebView: WebView = binding.wV1
        myWebView.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                if (saved_url == null){
                    editor.putString("saved_url", request?.url.toString()).apply()
                }
                return true
            }
        }
        saved_url?.let {
            myWebView.loadUrl(it)
        }?:myWebView.loadUrl(initialUrl)
        binding.wV1.webChromeClient = WebChromeClient()
        binding.wV1.settings.javaScriptEnabled = true
        binding.wV1.settings.domStorageEnabled = true
        binding.wV1.settings.databaseEnabled = true


        binding.bNav.selectedItemId = R.id.trading
        binding.bNav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.signals -> {
                    val intent = Intent(this, SignalTwoActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }

        binding.imageView64.setOnClickListener {
            binding.wV1.reload()
        }

        binding.imageView65.setOnClickListener {
            binding.wV1.goBack()
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && binding.wV1.canGoBack()) {
            binding.wV1.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

    }
}
