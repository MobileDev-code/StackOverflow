package com.example.stackoverflow.dal.di

import com.example.stackoverflow.dal.mocks.MockQuestionsRepository
import com.example.stackoverflow.repositories.QuestionsRepository
import dagger.Component

// @Component makes Dagger create a graph of dependencies
@Component
interface ApplicationGraph {
    fun repository(): QuestionsRepository
}