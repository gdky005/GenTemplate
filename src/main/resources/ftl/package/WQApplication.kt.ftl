package ${packageName}

import ${packageName}.MainActivity
import com.zkteam.sdk.base.ZKBaseApplication
import com.zkteam.ui.components.ZKUIManager

class ${proDir}Application : ZKBaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ZKUIManager.instance.setMainActivity(MainActivity::class.java)
    }
}