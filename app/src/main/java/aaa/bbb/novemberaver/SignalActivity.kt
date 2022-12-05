package aaa.bbb.novemberaver

import aaa.bbb.novemberaver.databinding.ActivitySignalBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import java.lang.Math.random

class SignalActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignalBinding
    val loopImages = listOf(R.drawable.green, R.drawable.red)
    private val images1 = listOf(R.id.imageView, R.id.imageView4, R.id.imageView8, R.id.imageView12,
        R.id.imageView16, R.id.imageView20, R.id.imageView24, R.id.imageView28, R.id.imageView32,
        R.id.imageView36, R.id.imageView40, R.id.imageView44, R.id.imageView48,
        R.id.imageView52, R.id.imageView56, R.id.imageView60)
    val images2 = listOf(R.id.imageView1, R.id.imageView5, R.id.imageView9, R.id.imageView13,
        R.id.imageView17, R.id.imageView21, R.id.imageView25, R.id.imageView29, R.id.imageView33,
        R.id.imageView37, R.id.imageView41, R.id.imageView45, R.id.imageView49,
        R.id.imageView53, R.id.imageView57, R.id.imageView61)
    val images3 = listOf(R.id.imageView2, R.id.imageView6, R.id.imageView10, R.id.imageView14,
        R.id.imageView18, R.id.imageView22, R.id.imageView26, R.id.imageView30, R.id.imageView34,
        R.id.imageView38, R.id.imageView42, R.id.imageView46, R.id.imageView50,
        R.id.imageView54, R.id.imageView58, R.id.imageView62)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignalBinding.inflate(layoutInflater)
        setContentView(binding.root)

            for (i in images1) {
                startSlider1(i)
            }
            for (i in images2) {
            startSlider2(i)
            }
            for (i in images3) {
            startSlider3(i)
            }
    }
    private fun startSlider1(i: Int) {
        Handler().apply {
            val runnable = object : Runnable {
                var index = 0
                var imageView = findViewById<ImageView>(i)
                override fun run() {
                    imageView.setImageResource(loopImages.random())
                    index++
                    if (index > loopImages.size - 1) {
                        index = 0
                    }
                    postDelayed(this, 60000)
                }
            }
            postDelayed(runnable, 60000)
        }
    }

    private fun startSlider2(i: Int) {
        Handler().apply {
            val runnable = object : Runnable {
                var index = 0
                var imageView = findViewById<ImageView>(i)
                override fun run() {
                    imageView.setImageResource(loopImages.random())
                    index++
                    if (index > loopImages.size - 1) {
                        index = 0
                    }
                    postDelayed(this, 120000)
                }
            }
            postDelayed(runnable, 120000)
        }
    }
    private fun startSlider3(i: Int) {
        Handler().apply {
            val runnable = object : Runnable {
                var index = 0
                var imageView = findViewById<ImageView>(i)
                override fun run() {
                    imageView.setImageResource(loopImages.random())
                    index++
                    if (index > loopImages.size - 1) {
                        index = 0
                    }
                    postDelayed(this, 300000)
                }
            }
            postDelayed(runnable, 300000)
        }
    }

}