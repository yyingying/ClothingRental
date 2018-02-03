package clothing_rental.canceline.com.clothingrental;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class innitialActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innitial);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                handler.postDelayed(this,2000);
            }
        };

        handler.postDelayed(runnable,2000);
        handler.removeCallbacks(runnable);
    }
}
