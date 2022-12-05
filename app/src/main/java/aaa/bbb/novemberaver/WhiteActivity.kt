package aaa.bbb.novemberaver

import aaa.bbb.novemberaver.databinding.ActivityWhiteBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent

class WhiteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWhiteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhiteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressed()

        binding.button7.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        binding.button8.setOnClickListener {
            val intent = Intent(this, SignalActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {

    }
}