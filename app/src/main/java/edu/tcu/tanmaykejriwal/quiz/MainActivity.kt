package edu.tcu.tanmaykejriwal.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var name = findViewById<TextInputEditText>(R.id.name_et)

        var startbtn = findViewById<Button>(R.id.startButton)

        startbtn.setOnClickListener{
            goToTheQuestion(name)}
        name.setOnEditorActionListener { _, actionId, _ ->
            if(actionId==EditorInfo.IME_ACTION_GO){
                goToTheQuestion(name)
                true
            }else false
        }
    }

    private fun goToTheQuestion(name:TextInputEditText){
        if(name.text.toString().isEmpty()){
            Toast.makeText(this,
                "Please enter your name", Toast.LENGTH_LONG).show();
        }
        else{
            val intent = Intent(this,QuestionActivity::class.java)
            intent.putExtra("username",name.text.toString())
            startActivity(intent)
            finish()
        }
    }
}