package aaa.bbb.novemberaver

import aaa.bbb.novemberaver.databinding.ActivityQuizBinding
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import kotlin.system.exitProcess

class QuizActivity : AppCompatActivity() {
    var questionNo = 0
    private var questions = listOf("If your broker has 100x margin ratio, how much do you need in your account to have position $10000? \n\n " +
            "A) $10 000 \n\n B) $100 \n\n C) $1000",
        "What is PIP? \n\n " +
                "A) A movement in the price that is less than 10 points \n\n B) A movement in the price of 100 points \n\n C) A one-cent movement in the price of USD",
        "What does it mean if you have a SHORT position? \n\n " +
                "A) You have made a loss on a position \n\n B) You are expecting the base currency to move lower \n\n C) You have sold the base currency and bought the counter currency",
        "What is the name of REVERSAL pattern? \n\n " +
                "A) Delfin \n\n B) Triangle \n\n C) Tripple bottom",
        "What is number one indicator? \n\n " +
                "A) Stochastic \n\n B) Price itself \n\n C) Momentum",
        "What is number one indicator? \n\n " +
                "A) Stochastic \n\n B) Price itself \n\n C) Momentum")
    var rightAnswers = listOf(2, 1, 3, 3, 2, 3)
    var a = 0
    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            showToast(1)
        }

        binding.button3.setOnClickListener {
            showToast(2)
        }

        binding.button4.setOnClickListener {
            showToast(3)
        }
        binding.iV4.setOnClickListener {
            val intent = Intent(this, WhiteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun showToast(answer: Int) {
        if (answer== rightAnswers[questionNo]) {
            updateQuestion()
            a++
            scores(a)
        } else {
            updateQuestion()
        }

    }

    fun updateQuestion() {
        questionNo += 1
        binding.textView7.text = questions[questionNo]
        when(questionNo) {
            5 -> finally()
        }
    }
    @SuppressLint("SetTextI18n")
    fun scores(n: Int){
        binding.textView9.text = "Score: $n"
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    fun finally(){
        binding.textView7.gravity = Gravity.CENTER
        binding.textView7.text = "You finished quiz! \n\n Congratulations!"
        binding.button2.visibility = View.INVISIBLE
        binding.button3.text = "Restart"
        binding.button3.setOnClickListener {
            val intent = Intent(this, WhiteActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.button4.visibility = View.INVISIBLE
    }
}