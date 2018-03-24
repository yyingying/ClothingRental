package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import clothing_rental.canceline.com.clothingrental.R;

/**
 * Created by kingShin on 2018/3/2.
 */

@Route(path="/mine/Certification")
public class CertificationActivity extends Activity {
    private Button btn_exit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_certification);
//
//        btn_exit = findViewById(R.id.exit_btn);
//        btn_exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }
}
