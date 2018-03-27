package clothing_rental.canceline.com.clothingrental.login.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;
import java.util.regex.Pattern;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import clothing_rental.canceline.com.clothingrental.data_base.Person;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by kingShin on 2018/3/26.
 */

@Route(path = "/login/passwordModifyActivity")
public class PasswordModifyActivity extends BaseActivity {
    private EditText account;
    private EditText password;
    private EditText password_confirm;
    private Button password_modify_btn;
    private Boolean test;
    private Button clo_btn;
    private boolean pwd_test;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_modify);

        clo_btn = findViewById(R.id.exit_btn);
        clo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        password_modify_btn = findViewById(R.id.passwordModifyButton);
        password_modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
                if(pwd_test == false){
                    Toast.makeText(PasswordModifyActivity.this,"密码不符合规范，请重新确认密码",Toast.LENGTH_LONG).show();
                } else{
                    if (test == false){
                        Toast.makeText(PasswordModifyActivity.this,"两次密码输入不相同，请确认密码",Toast.LENGTH_LONG).show();
                    }else{
                        final BmobQuery<Person> personBmobQuery = new BmobQuery<>();
                        personBmobQuery.addWhereEqualTo("account",account.getText().toString());
                        personBmobQuery.findObjects(new FindListener<Person>() {
                            @Override
                            public void done(List<Person> list, BmobException e) {
                                if(e==null){
                                    if(list.size()==0){
                                        Toast.makeText(PasswordModifyActivity.this,"用户名不存在，请重新输入",Toast.LENGTH_LONG).show();
                                    }else {
                                        Person person = new Person();
                                        person.setPassword(password.getText().toString());
                                        person.update(list.get(0).getObjectId(),new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if(e==null){
                                                    Toast.makeText(PasswordModifyActivity.this,"密码修改成功",Toast.LENGTH_LONG).show();
                                                    finish();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void init() {
        account = findViewById(R.id.modifyUserNameEditText);
        password = findViewById(R.id.modifyPasswordEditText);
        password_confirm = findViewById(R.id.modifyPasswordConfirmEditText);
        test = password.getText().toString().equals(password_confirm.getText().toString());

        Pattern z1_ = Pattern.compile("^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$");//判断是否都成立，都包含返回true
        pwd_test = z1_.matcher(password.getText().toString()).matches();
    }
}
