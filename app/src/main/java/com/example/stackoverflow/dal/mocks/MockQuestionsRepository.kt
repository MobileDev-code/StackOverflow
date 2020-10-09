package com.example.stackoverflow.dal.mocks

import android.content.Context
import com.android.volley.Response
import com.example.stackoverflow.dal.interfaces.IQuestionsRepository
import com.example.stackoverflow.dal.models.Question
import com.example.stackoverflow.dal.models.Questions
import javax.inject.Inject
import kotlin.reflect.full.createInstance

class MockQuestionsRepository @Inject constructor() : IQuestionsRepository {
    override fun getQuestions(context: Context, listener: Response.Listener<Questions>, errorListener: Response.ErrorListener) {
        var questions = Questions::class.createInstance()
        var listQuestions = ArrayList<Question>()
        var question: Question? = Question::class.createInstance()
        question?.apply {
            title = "Placeholder question"
            body = "Body text"
            answerCount = 2
            acceptedAnswerID = 1
        }
        question?.let { listQuestions.add(it) }
        questions?.items = listQuestions
        listener.onResponse(questions)
    }
}