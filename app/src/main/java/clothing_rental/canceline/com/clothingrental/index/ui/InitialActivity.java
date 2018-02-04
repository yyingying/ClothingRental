package clothing_rental.canceline.com.clothingrental.index.ui;


import android.os.Handler;
import android.os.Bundle;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;

public class InitialActivity extends BaseActivity {


    private final Handler mainThread = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innitial);

        mainThread.postDelayed(delayToMain, 2000);
    }

    private Runnable delayToMain = new Runnable() {
        @Override
        public void run() {
            Router.navTo("/main/index");
        }
    };

    @Override
    protected void onDestroy() {
        mainThread.removeCallbacks(delayToMain);
        super.onDestroy();
    }
}
