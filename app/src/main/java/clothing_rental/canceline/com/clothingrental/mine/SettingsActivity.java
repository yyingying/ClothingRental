package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;

/**
 * Created by kingShin on 2018/3/2.
 */
@Route(path="/mine/Settings")
public class SettingsActivity extends Activity {
    private Button btn_exit;
    private Button btn_exitID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btn_exit = findViewById(R.id.exit_btn);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_exitID = findViewById(R.id.exitID_btn);
        btn_exitID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog(view);
            }
        });
    }

    public void showdialog(View view){
        AlertDialog.Builder alertdialogbuider = new AlertDialog.Builder(this);
        alertdialogbuider.setMessage("您确定要退出账号？");
        alertdialogbuider.setPositiveButton("确定",click1);
        alertdialogbuider.setNegativeButton("取消",click2);
        AlertDialog alertDialog1 = alertdialogbuider.create();
        alertDialog1.show();
    }

    private DialogInterface.OnClickListener click1 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            finish();
            Router.navTo("/login/input");
        }
    };
    private DialogInterface.OnClickListener click2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    };
}
