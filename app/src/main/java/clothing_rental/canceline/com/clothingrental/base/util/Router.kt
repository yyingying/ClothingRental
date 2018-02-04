package clothing_rental.canceline.com.clothingrental.base.util

import android.net.Uri
import clothing_rental.canceline.com.clothingrental.app.App
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by 张宇 on 2018/2/4.
 * E-mail: zhangyu4@yy.com
 * YY: 909017428
 */
object Router {
    @JvmStatic
    fun navTo(path: String) = ARouter.getInstance().build(path).navigation(App.currentContext)

    @JvmStatic
    fun navTo(uri: Uri) = ARouter.getInstance().build(uri).navigation(App.currentContext)
}