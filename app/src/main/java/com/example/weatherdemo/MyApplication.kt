package com.example.weatherdemo

import android.app.Application
import android.content.Context

class MyApplication : Application(){

    companion object{
        const val TOKEN = "bMolU0HAODYei4BH" // 填入你申请到的令牌值
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}