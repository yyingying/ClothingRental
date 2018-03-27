package clothing_rental.canceline.com.clothingrental.login.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.data_base.Adress;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.data_base.Person;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by kingShin on 2018/3/16.
 */

@Route(path = "/login/input")
public class LoginActivity0 extends AppCompatActivity {
    private EditText account_editxt;
    private EditText password_editxt;
    private Button Login_btn;
    private Button Register_btn;
    private TextView agreement;
    private Boolean test;
    private TextView visitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login_btn = (Button)findViewById(R.id.loginButton);
        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account_editxt = (EditText)findViewById(R.id.userNameEditText);
                password_editxt = (EditText)findViewById(R.id.passwordEditText);

                BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
                bmobQuery.addWhereEqualTo("account",account_editxt.getText().toString());
                bmobQuery.findObjects(new FindListener<Person>() {
                    @Override
                    public void done(List<Person> list, BmobException e) {
                        if(e==null){
                            test = password_editxt.getText().toString().equals(list.get(0).getPassword());
                            if(list.size()==0){
                                Toast.makeText(LoginActivity0.this,"用户名不存在，请确认输入或注册",Toast.LENGTH_LONG).show();
                            }else{
                                if(test==true){
                                    Router.navTo("/main/index");
                                    LoginUtil.setPerson(list.get(0));
                                    LoginUtil.setState(true);
                                    //给地址找一个值
                                    BmobQuery<Adress> adressBmobQuery = new BmobQuery<>();
                                    adressBmobQuery.addWhereEqualTo("userID",LoginUtil.getPersonID());
                                    adressBmobQuery.findObjects(new FindListener<Adress>() {
                                        @Override
                                        public void done(List<Adress> list, BmobException e) {
                                            if(e==null){
                                                if(list.size()!=0){
                                                    LoginUtil.setAdress(list.get(0));
                                                }
                                            }
                                        }
                                    });
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity0.this,"密码错误，请重 新输入",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
            }
        });

        Register_btn = (Button)findViewById(R.id.registerButton_login);
        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/login/register");
                finish();
            }
        });

        agreement = (TextView)findViewById(R.id.agreementText);
        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/login/agreement");
            }
        });

        visitor = findViewById(R.id.visitorTextView);
        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/main/index");
                finish();
            }
        });
    }
}
