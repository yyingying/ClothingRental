package clothing_rental.canceline.com.clothingrental.base.util

import android.util.Log
import clothing_rental.canceline.com.clothingrental.BuildConfig

/**
 * Created by 张宇 on 2018/2/4.
 * E-mail: zhangyu4@yy.com
 * YY: 909017428
 */

object L : Logger(shouldLog = { priority ->
    when (priority) {
        Log.INFO, Log.ERROR -> true
        else -> BuildConfig.DEBUG
    }
})

open class Logger(
        private val globalTag: String = "default",
        /**
         * Toggle to dictate whether a message should be logged
         */
        private val shouldLog: (priority: Int) -> Boolean = { true }) {

    fun v(tag: String = globalTag, message: () -> Any?) = log(Log.VERBOSE, tag, message)

    fun i(tag: String = globalTag, message: () -> Any?) = log(Log.INFO, tag, message)

    fun d(tag: String = globalTag, message: () -> Any?) = log(Log.DEBUG, tag, message)

    fun w(tag: String = globalTag, message: () -> Any?) = log(Log.WARN, tag, message)

    fun e(tag: String = globalTag, message: () -> Any?) = log(Log.ERROR, tag, message)

    fun wtf(tag: String = globalTag, message: () -> Any?) = log(Log.ASSERT, tag, message)

    open fun log(priority: Int, tag: String, message: () -> Any?, error: Throwable? = null) {
        if (shouldLog(priority))
            logImpl(priority, tag, message()?.toString(), error)
    }

    protected open fun logImpl(priority: Int, tag: String, message: String?, t: Throwable?) {
        when (priority) {
            Log.VERBOSE -> Log.v(tag, message)
            Log.INFO -> Log.i(tag, message)
            Log.DEBUG -> Log.d(tag, message)
            Log.WARN -> Log.w(tag, message)
            else -> Log.e(tag, message, t)
        }
    }
}