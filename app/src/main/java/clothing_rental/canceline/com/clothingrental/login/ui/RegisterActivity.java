package clothing_rental.canceline.com.clothingrental.login.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import clothing_rental.canceline.com.clothingrental.data_base.Person;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import okhttp3.Route;

public class RegisterActivity extends BaseActivity {

    private Button register_btn;
    private TextView agreement_txt;
    private EditText account_editxt;
    private EditText password_editxt;
    private EditText pwd_confirmed_editxt;
    private boolean account_test;
    private boolean pwd_test;
    private boolean confirmed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_btn = (Button)findViewById(R.id.registerButton);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();

                if(confirmed==false){
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不相同，请确认密码",Toast.LENGTH_LONG).show();}
                else{
                    if (account_test==false){
                        Toast.makeText(RegisterActivity.this,"用户名不符合要求，请重新输入",Toast.LENGTH_LONG).show();}
                else {
                    if (pwd_test==false){
                        Toast.makeText(RegisterActivity.this,"密码不符合要求，请重新输入",Toast.LENGTH_LONG).show();
                    }else {
                        Person person = new Person();
                        person.setAccount(account_editxt.getText().toString());
                        person.setPassword(password_editxt.getText().toString());
                        person.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if(e==null){
                                    Toast.makeText(RegisterActivity.this,"sucess",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(RegisterActivity.this,"用户名已存在，请重新输入",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                }
            }
        });

        agreement_txt = (TextView)findViewById(R.id.agreementText);
        agreement_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/login/agreement");
            }
        });
    }

    private void initData(){
        account_editxt = (EditText)findViewById(R.id.registerUserNameEditText);
        password_editxt = (EditText)findViewById(R.id.registePasswordEditText);

        Pattern z1_ = Pattern.compile("^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$");//判断是否都成立，都包含返回true
        account_test = z1_.matcher(account_editxt.getText().toString()).matches();
        pwd_test = z1_.matcher(password_editxt.getText().toString()).matches();

        pwd_confirmed_editxt = (EditText)findViewById(R.id.registePasswordConfirmEditText);
        confirmed = pwd_confirmed_editxt.getText().toString().equals(password_editxt.getText().toString());
    }


}
