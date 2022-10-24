package edu.tcu.tanmaykejriwal.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

private var name = ""
private var score = ""
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        name= intent.getStringExtra("name").toString()
        score = intent.getStringExtra("score").toString()

        var emoji = findViewById<ImageView>(R.id.emoji)
        var scoreText =  findViewById<TextView>(R.id.score)
        var textM =  findViewById<TextView>(R.id.congrats)
        var tryagain =  findViewById<Button>(R.id.tryagain)

        if(score.toInt()>5){
            emoji.setImageResource(R.drawable.ic_trophy)
            scoreText.text="You scored "+ score+" out of 10"
            textM.text="Congratulations, "+ name
        }
        else{
            emoji.setImageResource(R.drawable.ic_sweat_face)
            scoreText.text="You scored "+ score+" out of 10"
            textM.text="Good Luck Next Time, "+ name
        }
        tryagain.setOnClickListener{
            gotoStart()
        }

    }
    private fun gotoStart(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}
