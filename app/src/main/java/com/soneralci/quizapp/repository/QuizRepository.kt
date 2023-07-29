package com.soneralci.quizapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soneralci.quizapp.model.QuestionsList
import com.soneralci.quizapp.retrofit.QuestionsAPI
import com.soneralci.quizapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository {

    val questionsAPI : QuestionsAPI

    init {
        questionsAPI = RetrofitInstance().getRetrofitInstance()
            .create(QuestionsAPI::class.java)

    }

    fun getQuestionsFromAPI() : LiveData<QuestionsList>{
        var data = MutableLiveData<QuestionsList>()

        var questionsList : QuestionsList
        GlobalScope.launch(Dispatchers.IO) {

            //Returning the response <QuestionsList>
            val response = questionsAPI.getQuestions()

            if (response != null){
                //saving the data to list

                questionsList = response.body()!!

                data.postValue(questionsList)
                Log.i("TAGY","" + data.value)
            }

        }
        return data
    }

}