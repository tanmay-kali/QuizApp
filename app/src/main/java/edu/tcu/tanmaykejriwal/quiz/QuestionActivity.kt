package edu.tcu.tanmaykejriwal.quiz

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private val questions = Constants.getQuestions().shuffled()
    private var questionIdx = 0
    private val optionTVs = mutableListOf<TextView>()
    private var selectedOptionIdx = -1
    private var answerReveal = false
    private var correctanswer = 0
    private var correctText =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val username = intent.getIntExtra("username",0)
        findViewById<ProgressBar>(R.id.bar).max = questions.size
        findViewById<TextView>(R.id.question_tv).text = "WHICH COUNTRY DOES THIS FLAG BELONG TO"
        findViewById<ImageView>(R.id.flag_iv).setImageResource(R.drawable.ic_flag_of_australia)
        findViewById<Button>(R.id.button).setOnClickListener(this)

        var optionll = findViewById<LinearLayout>(R.id.option_ll)

        val optionTv = TextView(this);
        val layoutparams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutparams.topMargin = dpToPx(16);
        optionTv.layoutParams = layoutparams
        optionTv.gravity = Gravity.CENTER
        optionTv.setPadding(dpToPx(16),dpToPx(16),dpToPx(16),dpToPx(16))
        optionTv.textSize = 16F
        optionTv.text = "TEST"
        optionTv.setTextColor(ContextCompat.getColor(this, R.color.gray))
        optionTv.typeface = Typeface.DEFAULT
        optionTv.setBackgroundResource(R.drawable.default_option_bg)
        val secopt=createOption("TK")
        val thirdopt=createOption("TM")

        optionll.addView(optionTv)
        optionll.addView(secopt)
        optionll.addView(thirdopt)

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
    private fun dpToPx(dp: Int): Int{
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5).toInt()
    }

    private fun createOption(text:String):TextView{
        val layoutparams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutparams.topMargin = dpToPx(16);
        val optionTv = TextView(this);

        optionTv.layoutParams = layoutparams
        optionTv.gravity = Gravity.CENTER
        optionTv.setPadding(dpToPx(16),dpToPx(16),dpToPx(16),dpToPx(16))
        optionTv.textSize = 16F
        optionTv.text = text
        optionTv.setTextColor(ContextCompat.getColor(this, R.color.gray))
        optionTv.typeface = Typeface.DEFAULT
        optionTv.setBackgroundResource(R.drawable.default_option_bg)
        optionTv.setOnClickListener{setOptionView(optionTv)}
        return optionTv

    }

    private fun setOptionView(selectedOption: TextView) {
        for (optionTv in optionTVs){
            optionTv.setTextColor(ContextCompat.getColor(this,R.color.gray))
            optionTv.typeface = Typeface.DEFAULT
            optionTv.setBackgroundResource(R.drawable.default_option_bg)
        }
        selectedOption.setTextColor(ContextCompat.getColor(this,R.color.black))
        selectedOption.typeface = Typeface.DEFAULT_BOLD
        selectedOption.setBackgroundResource(R.drawable.selected_option_bg)

    }


}