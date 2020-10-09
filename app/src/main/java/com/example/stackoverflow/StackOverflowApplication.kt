package com.example.stackoverflow

import android.app.Application

class StackOverflowApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        lateinit var instance: StackOverflowApplication
            private set
    }
}