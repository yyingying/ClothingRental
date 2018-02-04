package clothing_rental.canceline.com.clothingrental.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import clothing_rental.canceline.com.clothingrental.BuildConfig
import clothing_rental.canceline.com.clothingrental.base.util.L
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by 张宇 on 2018/2/4.
 * E-mail: zhangyu4@yy.com
 * YY: 909017428
 */
class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var currentContext: Context? = null
            set(value) {
                when (value) {
                    null -> currentActivity = null
                    is BaseActivity -> currentActivity = value
                    else -> L.e { "current Activity is not a super class of BaseActivity!" }
                }
                field = value
            }

        var currentActivity: BaseActivity? = null

    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
                currentContext = activity
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
                currentContext = null
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                currentContext = activity
            }

        })
    }
}