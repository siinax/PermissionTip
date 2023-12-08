package com.kedaya.permissiondemo

import android.app.Application
import com.kedaya.permissiontip.SharedUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedUtils.init(this)
    }

}