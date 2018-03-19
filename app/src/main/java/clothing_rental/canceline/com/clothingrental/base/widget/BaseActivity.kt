package clothing_rental.canceline.com.clothingrental.base.widget

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by 张宇 on 2018/2/4.
 * E-mail: zhangyu4@yy.com
 * YY: 909017428
 */
abstract class BaseActivity : RxAppCompatActivity() {
    val context: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ARouter.getInstance().inject(this)
    }
}