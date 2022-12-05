package aaa.bbb.novemberaver

import aaa.bbb.novemberaver.databinding.ActivitySplashBinding
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private var progressStatus = 0
    private var handler = Handler()
    private var url = ""

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        Thread(Runnable {
            while (progressStatus < 100){

                progressStatus +=1

                Thread.sleep(50)

                handler.post {
                    binding.progressBar.progress = progressStatus
                    binding.textView.text = "$progressStatus%"
                }
            }
        }).start()

        Handler().postDelayed({
            if (url.isEmpty()) {
            val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                url = "first"
                val pref: SharedPreferences =
                    getSharedPreferences("Go", MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("2", url).apply()
                finish()
            } else {
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000)
    }

    private fun init(){
        val pref: SharedPreferences =
            getSharedPreferences("Go", MODE_PRIVATE)
        url = pref.getString("2", url)!!
    }
}