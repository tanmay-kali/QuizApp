package edu.tcu.tanmaykejriwal.quiz

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private val questions = Constants.getQuestions().shuffled()
    private var questionIdx =0
    private val optionTVs = mutableListOf<TextView>()
    private var selectedOptionIdx = -1
    private var answerReveal = false
    private var correctanswer = ""
    private var chosen: TextView? =null;
    private var score= 0
    private var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        name = intent.getStringExtra("username").toString()
        println(name)
        findViewById<ProgressBar>(R.id.bar).max = questions.size
        findViewById<TextView>(R.id.question_tv).text =questions[0].question
        findViewById<ImageView>(R.id.flag_iv).setImageResource(R.drawable.ic_flag_of_australia)
        findViewById<Button>(R.id.button).setOnClickListener{movealong()}
        createQuestion();




    }

    private fun createQuestion(){
        findViewById<Button>(R.id.button).setOnClickListener{movealong()}
        findViewById<Button>(R.id.button).text="Submit"
        chosen = null;
        var ind = questionIdx
        var getOptions = questions[ind].options.shuffled()
        var optionll = findViewById<LinearLayout>(R.id.option_ll)
        var temp = ind+1
        findViewById<ProgressBar>(R.id.bar).progress=temp
        findViewById<TextView>(R.id.barNumber).text= temp.toString()+ "/10"
        findViewById<ImageView>(R.id.flag_iv).setImageResource(questions[ind].image)
        correctanswer=questions[ind].correctAnswer
        optionll.removeAllViews()
        for(item in getOptions){
            val opt=createOption(item)
            optionll.addView(opt)
        }
        questionIdx++
    }

    private fun movealong(){

        if(chosen==null){
            Toast.makeText(this, "Please make a selection", Toast.LENGTH_SHORT).show()
        }

        else{
            if(chosen!!.text==correctanswer){
                chosen!!.setBackgroundResource(R.drawable.correct_option_bg)
                score++
            }
            else{
                chosen!!.setBackgroundResource(R.drawable.wrong_option_bg)
                for (optionTv in optionTVs){
                    if(optionTv.text==correctanswer){
                        optionTv.setBackgroundResource(R.drawable.correct_option_bg)
                    }
                }
            }

            for (optionTv in optionTVs){
                optionTv.setOnClickListener(null)
            }
            if (questionIdx>=questions.size){
                goToResult()
            }
            else {
                findViewById<Button>(R.id.button).text = "Go to Next Question"
                findViewById<Button>(R.id.button).setOnClickListener { createQuestion() }
            }
        }


    }

    private fun goToResult(){
        val intent = Intent(this,ResultActivity::class.java)
        intent.putExtra("score",score.toString())
        intent.putExtra("name",name)
        startActivity(intent)
        finish()
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
        optionTVs.add(optionTv)
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
        chosen= selectedOption

    }


}