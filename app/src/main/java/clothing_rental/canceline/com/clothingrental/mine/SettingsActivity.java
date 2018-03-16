package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by kingShin on 2018/3/2.
 */
@Route(path="/mine/Settings")
public class SettingsActivity extends Activity {
    private Button btn_exit;
    private Button btn_exitID;
    private Button btn_new;
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

        btn_new = findViewById(R.id.new_btn);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<BmobObject>goodss = new ArrayList<BmobObject>();
                Goods goods1=insert(001,"LV city steamer中号手袋",28400,10,5);
                goodss.add(goods1);
                Goods goods2=insert(002,"路易威登-lockme-bucket-手袋",38200, 8, 5);
                goodss.add(goods2);
                Goods goods3=insert(003, "路易威登-lockme-ii-bb-手袋", 18600, 6, 5);
                goodss.add(goods3);
                Goods goods4=insert(004,"路易威登-marignan-邮差包", 20800, 6,5);
                goodss.add(goods4);
                Goods goods5=insert(005,"路易威登-montaigne-中号手袋",9800, 2,5);
                goodss.add(goods5);
                Goods goods6=insert(006,"路易威登-petite-malle-手袋",10800, 4,5);
                goodss.add(goods6);
                Goods goods7=insert(007,"路易威登-saint-sulpice-bb-手袋",25600,12,5);
                goodss.add(goods7);
                Goods goods8=insert(1008,"路易威登-tuileries-手袋",30800,20,5);
                goodss.add(goods8);
                Goods goods9=insert(1009,"路易威登-twist-中号手袋",14800,12,5);
                goodss.add(goods9);
                Goods goods10=insert(1010,"路易威登-very-tote-拉链手袋",12800,16,5);
                goodss.add(goods10);

            }
        });
    }

    private Goods insert(Integer number, String name, Integer price, Integer rental_price, Integer stock) {
        Goods goods = new Goods();
        Integer number1;
        String name1;
        Integer price1;
        Integer rental_price1;
        Integer stock1;
        //BmobFile pic1;
        number1 = number;
        name1 = name;
        price1 = price;
        rental_price1 = rental_price;
        stock1 = stock;
        //pic1 = pic;
        goods.setNumber(number1);
        goods.setName(name1);
        goods.setPrice(price1);
        goods.setRental_price(rental_price1);
        goods.setStock(stock1);
        return goods;

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
