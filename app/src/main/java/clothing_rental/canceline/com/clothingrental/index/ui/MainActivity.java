package clothing_rental.canceline.com.clothingrental.index.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;

@Route(path = "/main/index")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
