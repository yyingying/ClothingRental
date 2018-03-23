package clothing_rental.canceline.com.clothingrental.index.ui;


import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import clothing_rental.canceline.com.clothingrental.data_base.Adress;
import clothing_rental.canceline.com.clothingrental.data_base.Person;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class InitialActivity extends BaseActivity {

    private final Handler mainThread = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innitial);

//        SharedPreferences preferences = getSharedPreferences("first_open",MODE_PRIVATE);
//        boolean isFirstIn = preferences.getBoolean("is_first_open", true);
//
//        if (isFirstIn)
//        {
//            //第一次进入时先把first_open置为false以便后来进入时进行判断，除此之外，还可以写入第一次进入时苏要执行的动作
//            preferences = getSharedPreferences("first_open", MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putBoolean("is_first_open", false);
//            editor.commit();
//            mainThread.postDelayed(delayToLogin, 2000);
//
//        }
//        else {
//            mainThread.postDelayed(delayToMain, 2000);}

        boolean isLogin = LoginUtil.isLogin();
        if (isLogin)
        {
            mainThread.postDelayed(delayToMain, 2000);

        }
        else {
            mainThread.postDelayed(delayToLogin, 2000);}

    }
    //获得sharedPreference对象
    //对数据实现增删改查
    // 添加editor.putString(key,value);
    // 删除editor.remove(key);
    // 添加String result = sharedPreference.getString(key1,key2);
    // key1是要查询的键，返回对应的值，当键不存在时，返回key2作为结果
    // 添加editor.clear();

    Runnable delayToMain = new Runnable() {
            @Override
            public void run() {
                Router.navTo("/main/index");
                finish();
            }
        };

    Runnable delayToLogin = new Runnable() {
        @Override
        public void run() {
            Router.navTo("/login/input");
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        mainThread.removeCallbacks(delayToMain);
        //mainThread.removeCallbacks(delayToLogin);
        super.onDestroy();
    }
}
