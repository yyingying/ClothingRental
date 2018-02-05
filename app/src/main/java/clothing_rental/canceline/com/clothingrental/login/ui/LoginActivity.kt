package clothing_rental.canceline.com.clothingrental.login.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import clothing_rental.canceline.com.clothingrental.R
import clothing_rental.canceline.com.clothingrental.RentalActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerButton.setOnClickListener({
            val intent = Intent(this, RegisterActivity::class.java)
            /*连接组件，packagecontext为上下文，class是要连文件的class*/
            startActivity(intent)
        })

        loginButton.setOnClickListener({

            finish();
        })
    }
}
