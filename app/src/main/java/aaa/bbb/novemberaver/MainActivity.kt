package aaa.bbb.novemberaver

import aaa.bbb.novemberaver.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var progressStatus = 0
    var questionNo = 0
    private var handler = Handler()
    var questions = listOf("What is your trading experience? \n\n " +
            "A) Newbie \n\n B) Experienced \n\n C) Guru",
        "Do you have Quotex account? \n\n " +
                "A) Yes \n\n B) No",
        "What are your trading hours? \n\n " +
                "A) 6AM - 12PM \n\n B) 12PM - 6PM \n\n C) 6PM - 6AM",
        "What are your trading hours? \n\n " +
                "A) 6AM - 12PM \n\n B) 12PM - 6PM \n\n C) 6PM - 6AM")
    var rightAnswers = listOf(1, 3, 2, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bt1.setOnClickListener {
            showToast(1)
            thread()
        }

        binding.bt2.setOnClickListener {
            showToast(2)
            thread()
        }

        binding.bt3.setOnClickListener {
            showToast(3)
            thread()
        }
    }
        fun thread() {
            Thread(Runnable {
                if (progressStatus < 66){

                    progressStatus +=33

                    Thread.sleep(10)

                    handler.post {
                        binding.progressBar.progress = progressStatus
                    }
                } else if (progressStatus < 33) {
                    progressStatus +=33

                    Thread.sleep(10)

                    handler.post {
                        binding.progressBar.progress = progressStatus
                    }
                } else if (progressStatus < 100){

                    progressStatus +=34

                    Thread.sleep(10)

                    handler.post {
                        binding.progressBar.progress = progressStatus
                    }
                }
            }).start()
        }
        fun showToast(answer: Int) {
            if (answer== rightAnswers[questionNo]) {
                updateQuestion()
            } else {
                updateQuestion()
            }

        }

        fun updateQuestion() {
            questionNo += 1
            binding.textView2.text = questions[questionNo]
            when(questionNo) {
                3 -> finally()
            }
    }
        fun finally(){
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
        }
}