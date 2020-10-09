package com.example.stackoverflow.repositories

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.stackoverflow.dal.RequestManager
import com.example.stackoverflow.dal.interfaces.IQuestionsRepository
import com.example.stackoverflow.dal.models.Questions
import com.google.gson.GsonBuilder
import javax.inject.Inject

class QuestionsRepository @Inject constructor() : IQuestionsRepository {
    override fun getQuestions(context: Context, listener: Response.Listener<Questions>, errorListener: Response.ErrorListener) {
        // Get a RequestQueue
        val queue = RequestManager.getInstance(
            context
        ).requestQueue

        val baseUrl = "https://api.stackexchange.com/2.2/"
        val questionsBaseUrl = "questions"
        val fromDate = "1601596800"
        val orderBy = "desc"
        val sort = "activity"
        val site = "stackoverflow"
        val filter = "withBody"
        val questionsUrl = String.format("%s%s?fromDate=%s&page=1&pagesize=100&order=%s&sort=%s&site=%s&filter=%s", baseUrl, questionsBaseUrl, fromDate, orderBy, sort, site, filter)

        val stringRequest = StringRequest(
            Request.Method.GET, questionsUrl,
            Response.Listener<String> { response ->
                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()
                var questions: Questions = gson.fromJson(response, Questions::class.java)
//                questions.items = questions.items.filter { question -> question.acceptedAnswerID != null && question.answerCount > 1 }

                listener.onResponse(questions)
            },
            Response.ErrorListener { error ->
                errorListener.onErrorResponse(error)
                println(error.message)
            })

        RequestManager.getInstance(
            context
        ).addToRequestQueue(stringRequest)
    }
}