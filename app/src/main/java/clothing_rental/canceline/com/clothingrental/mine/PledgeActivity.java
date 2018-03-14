package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;

/**
 * Created by kingShin on 2018/3/1.
 */

@Route(path="/mine/Pledge")

public class PledgeActivity extends Activity {
    private Button btn_exit;
    private TextView text_pledgeStatement;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge);

        text_pledgeStatement = findViewById(R.id.pledgeStatementText);
        text_pledgeStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.navTo("/mine/PledgeStatement");
            }
        });

        btn_exit = findViewById(R.id.exit_btn);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
