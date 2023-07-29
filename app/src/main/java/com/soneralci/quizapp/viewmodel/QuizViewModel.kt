package com.soneralci.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.soneralci.quizapp.model.QuestionsList
import com.soneralci.quizapp.repository.QuizRepository

class QuizViewModel : ViewModel() {

    var repository : QuizRepository = QuizRepository()

    lateinit var questionsLiveData : LiveData<QuestionsList>

    init {
        questionsLiveData = repository.getQuestionsFromAPI()
    }

    fun getQuestionsFromLiveData() : LiveData<QuestionsList>{
        return questionsLiveData
    }

}