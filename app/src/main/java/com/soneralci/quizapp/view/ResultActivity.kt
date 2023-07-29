package com.soneralci.quizapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.soneralci.quizapp.R
import com.soneralci.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding : ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_result)

        binding.txtAnswer.text = "Your Score is: "+MainActivity.result + "/"+MainActivity.totalQuestions

        binding.btnBack.setOnClickListener(){
            var intent = Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}