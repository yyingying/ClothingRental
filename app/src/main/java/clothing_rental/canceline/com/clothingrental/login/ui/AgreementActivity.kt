package clothing_rental.canceline.com.clothingrental.login.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import clothing_rental.canceline.com.clothingrental.R
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_agreement2.*

@Route(path = "/login/agreement")
class AgreementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agreement2)

        closeBtn.setOnClickListener { finish() }
    }
}
