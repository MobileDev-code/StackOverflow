package com.example.stackoverflow.dal.interfaces

import android.content.Context
import com.android.volley.Response
import com.example.stackoverflow.dal.models.Questions

interface IQuestionsRepository {
    fun getQuestions(context: Context, listener: Response.Listener<Questions>, errorListener: Response.ErrorListener)
}