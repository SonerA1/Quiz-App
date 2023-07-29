package com.soneralci.quizapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soneralci.quizapp.R
import com.soneralci.quizapp.databinding.ActivityMainBinding
import com.soneralci.quizapp.model.Question
import com.soneralci.quizapp.model.QuestionsList
import com.soneralci.quizapp.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var quizViewModel: QuizViewModel
    lateinit var questionsList: List<Question>

    companion object{
        var result = 0
        var totalQuestions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Reseting the scores
        result = 0
        totalQuestions = 0


        //getting the response
        quizViewModel = ViewModelProvider(this)
            .get(QuizViewModel::class.java)

        //Display the First Question
        GlobalScope.launch (Dispatchers.Main){
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer {
                if(it.size>0){
                    questionsList = it
                    Log.i("TAGY","This the 1st question : ${questionsList[0]}")

                    binding.apply {
                        txtQuestion.text = questionsList!![0].question
                        radio1.text = questionsList!![0].option1
                        radio2.text = questionsList!![0].option2
                        radio3.text = questionsList!![0].option3
                        radio4.text = questionsList!![0].option4
                    }
                }
            })
        }

        //Adding Funcionality Next Btn
        var i = 1
        binding.apply {
            btnNext.setOnClickListener(View.OnClickListener {
                val selectedOption = radioGroup?.checkedRadioButtonId

                if (selectedOption != -1){
                    val radbutton = findViewById<View>(selectedOption!!)as RadioButton

                    questionsList.let {
                        if (i<it.size){

                            //Getting number of quesitons
                            totalQuestions = it.size
                            if (radbutton.text.toString().equals(it[i-1].correct_option)){
                                result++
                                txtResult?.text = "Correct Answer : $result"
                            }
                            //Display the next Questions
                            txtQuestion.text = "Question ${i+1}: "+ it[i].question
                            radio1.text = it[i].option1
                            radio2.text = it[i].option2
                            radio3.text = it[i].option3
                            radio4.text = it[i].option4

                            //Check last questions
                            if (i == it.size!!.minus(1)){
                                btnNext.text = "FINISH"
                            }
                            radioGroup?.clearCheck()
                            i++

                        }else{
                            if (radbutton.text.toString().equals(it[i-1].correct_option)){
                                result++
                                txtResult?.text = "Correct Answer : $result"
                            }else{

                            }
                            val intent = Intent(this@MainActivity,ResultActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{


                    Toast.makeText(this@MainActivity,"Please select One Option",Toast.LENGTH_LONG).show()
                }


            })
        }


    }
}