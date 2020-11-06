package com.app.demo.activity

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.app.demo.di.networkModule
import com.app.demo.di.repos
import com.app.demo.di.viewModels
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext



class DemoApp:Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(viewModels, networkModule, repos))



    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onTerminate() {

        super.onTerminate()
    }
}