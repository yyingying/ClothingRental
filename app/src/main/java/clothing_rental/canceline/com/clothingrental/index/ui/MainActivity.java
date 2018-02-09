package clothing_rental.canceline.com.clothingrental.index.ui;

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

@Route(path = "/main/index")
public class MainActivity extends BaseActivity {

    private Button button_home;
    private Button button_all;
    private Button button_mine;

    ViewPager vpager = findViewById(R.id.vpager);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_home=findViewById(R.id.rb_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpager.setCurrentItem(0);
            }
        });
        button_all=findViewById(R.id.rb_all);
        button_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpager.setCurrentItem(1);
            }
        });
        button_mine=findViewById(R.id.rb_mine);
        button_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpager.setCurrentItem(2);
            }
        });

//        RadioGroup group = findViewById(R.id.rg_tab_bar);

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
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId) {
//            case R.id.rb_home:
//                vpager.setCurrentItem(0);
//                break;
//            case R.id.rb_all:
//                vpager.setCurrentItem(1);
//                break;
//            case R.id.rb_mine:
//                vpager.setCurrentItem(2);
//                break;
//        }
//    }