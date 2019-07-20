package com.zkteam.livedata.bus

import com.zkteam.livedata.bus.MainActivity
import com.zkteam.sdk.base.ZKBaseApplication
import com.zkteam.ui.components.ZKUIManager

class WQApplication : ZKBaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ZKUIManager.instance.setMainActivity(MainActivity::class.java)
    }
}