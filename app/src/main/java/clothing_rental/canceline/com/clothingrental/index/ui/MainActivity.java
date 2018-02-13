package clothing_rental.canceline.com.clothingrental.index.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import cn.bmob.v3.Bmob;

@Route(path = "/main/index")
public class MainActivity extends BaseActivity {


    private ViewPager vpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpager = findViewById(R.id.vpager);
        RadioGroup group = findViewById(R.id.rg_tab_bar);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpager.setCurrentItem(0);
                        break;
                    case R.id.rb_all:
                        vpager.setCurrentItem(1);
                        break;
                    case R.id.rb_mine:
                        vpager.setCurrentItem(2);
                        break;
                }
            }
        });

        vpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new MyFragment1();
                    case 1:
                        return new MyFragment2();
                    default:
                        return new MyFragment3();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

    }
}
